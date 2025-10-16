package com.juliomesquita.cdc.book.application.book.crud.service;

import com.juliomesquita.cdc.book.application.book.crud.dtos.BookCreateRequest;
import com.juliomesquita.cdc.book.application.book.crud.dtos.BookResponse;
import com.juliomesquita.cdc.book.application.book.crud.dtos.BookUpdateRequest;
import com.juliomesquita.cdc.book.application.book.crud.mapper.BookMapper;
import com.juliomesquita.cdc.book.domain.entities.Author;
import com.juliomesquita.cdc.book.domain.entities.Book;
import com.juliomesquita.cdc.book.domain.entities.BookSummary;
import com.juliomesquita.cdc.book.domain.entities.Category;
import com.juliomesquita.cdc.book.domain.repositories.BookRepository;
import com.juliomesquita.cdc.book.domain.repositories.BookSummaryRepository;
import com.juliomesquita.cdc.book.domain.valueobjects.BookInfo;
import com.juliomesquita.cdc.book.domain.valueobjects.ISBN;
import com.juliomesquita.cdc.shared.exceptions.ResourceNotFoundException;
import com.juliomesquita.cdc.shared.services.GenericService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class BookService extends GenericService<Book, BookCreateRequest, BookUpdateRequest, BookResponse, BookRepository, BookMapper> {
    @PersistenceContext
    private EntityManager entityManager;
    private final BookSummaryRepository bookSummaryRepository;

    public BookService(final BookRepository repository, final BookMapper mapper, BookSummaryRepository bookSummaryRepository) {
        super(repository, mapper);
        this.bookSummaryRepository = bookSummaryRepository;
    }

    @Transactional("transactionManager")
    @Override
    public BookResponse create(final BookCreateRequest request) {
        final Book book = createInstanceBook(
            request.title(), request.abstractText(), request.price(), request.numberOfPages(),
            request.publicationDate(), request.isbn(), request.categoryId(), request.authorId()
        );
        final Book bookSaved = this.repository.save(book);

        try {
            BookSummary summaryDoc = BookSummary.create(bookSaved.getId(), request.summary());
            this.bookSummaryRepository.save(summaryDoc);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save book summary to MongoDB", e);
        }

        bookSaved.setSummary(request.summary());
        return this.mapper.toResponse(bookSaved);
    }

    @Transactional("transactionManager")
    @Override
    public BookResponse update(final UUID id, final BookUpdateRequest request) {
        final Book instanceBook = this.createInstanceBook(
            request.title(), request.abstractText(), request.price(), request.numberOfPages(),
            request.publicationDate(), request.isbn(), request.categoryId(), request.authorId()
        );

        final Book bookRecover = this.repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id))
            .update(instanceBook.getInfo(), instanceBook.getIsbn(), instanceBook.getCategory(), instanceBook.getAuthor());

        final Book bookSaved = this.repository.save(bookRecover);

        try {
            BookSummary summaryDoc = BookSummary.create(bookSaved.getId(), request.summary());
            this.bookSummaryRepository.save(summaryDoc);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update book summary in MongoDB", e);
        }

        bookSaved.setSummary(request.summary());
        return this.mapper.toResponse(bookSaved);
    }

    @Transactional(readOnly = true)
    @Override
    public BookResponse findById(UUID id) {
        Book book = this.repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));

        this.bookSummaryRepository.findById(id)
            .ifPresent(summary -> book.setSummary(summary.getSummary()));

        return this.mapper.toResponse(book);
    }

    private Book createInstanceBook(
        String title, String abstractText, BigDecimal price, Integer numberOfPages, LocalDate publicationDate,
        String isbn, UUID categoryId, UUID authorId
    ) {
        final ISBN isbnCreated = ISBN.of(isbn);
        final BookInfo bookInfo = BookInfo.of(title, abstractText, price, numberOfPages, publicationDate);
        final Category category = entityManager.getReference(Category.class, categoryId);
        final Author author = entityManager.getReference(Author.class, authorId);

        return Book.create(bookInfo, isbnCreated, category, author);
    }
}
