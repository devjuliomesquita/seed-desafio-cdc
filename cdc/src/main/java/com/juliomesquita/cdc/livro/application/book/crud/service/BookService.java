package com.juliomesquita.cdc.livro.application.book.crud.service;

import com.juliomesquita.cdc.livro.application.book.crud.dtos.BookRequest;
import com.juliomesquita.cdc.livro.application.book.crud.dtos.BookResponse;
import com.juliomesquita.cdc.livro.application.book.crud.mapper.BookMapper;
import com.juliomesquita.cdc.livro.domain.entities.Author;
import com.juliomesquita.cdc.livro.domain.entities.Book;
import com.juliomesquita.cdc.livro.domain.entities.Category;
import com.juliomesquita.cdc.livro.domain.repositories.AuthorRepository;
import com.juliomesquita.cdc.livro.domain.repositories.BookRepository;
import com.juliomesquita.cdc.livro.domain.valueobjects.BookInfo;
import com.juliomesquita.cdc.livro.domain.valueobjects.ISBN;
import com.juliomesquita.cdc.shared.exceptions.ResourceNotFoundException;
import com.juliomesquita.cdc.shared.services.GenericService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BookService extends GenericService<Book, BookRequest, BookResponse, BookRepository, BookMapper> {
    @PersistenceContext
    private EntityManager entityManager;

    public BookService(final BookRepository repository, final BookMapper mapper) {
        super(repository, mapper);
    }

    @Transactional("jpaTransactionManager")
    @Override
    public BookResponse create(final BookRequest request) {
        final Book book = createInstanceBook(request);
        final Book bookSaved = this.repository.save(book);
        return this.mapper.toResponse(bookSaved);
    }

    @Transactional("jpaTransactionManager")
    @Override
    public BookResponse update(final UUID id, final BookRequest request) {
        final Book instanceBook = this.createInstanceBook(request);

        final Book bookRecover = this.repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id))
            .update(instanceBook.getInfo(), instanceBook.getIsbn(), instanceBook.getCategory(), instanceBook.getAuthor());

        final Book bookSaved = this.repository.save(bookRecover);
        return this.mapper.toResponse(bookSaved);
    }

    private Book createInstanceBook(final BookRequest request) {
        final ISBN isbn = ISBN.of(request.isbn());
        final BookInfo bookInfo = BookInfo.of(
            request.title(), request.abstractText(), request.summary(), request.price(), request.numberOfPages(), request.publicationDate());
        final Category category = entityManager.getReference(Category.class, request.categoryId());
        final Author author = entityManager.getReference(Author.class, request.authorId());

        return Book.create(bookInfo, isbn, category, author);
    }
}
