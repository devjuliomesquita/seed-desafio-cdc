package com.juliomesquita.cdc.book.application.book.usecases.findpartial;

import com.juliomesquita.cdc.book.domain.entities.Book;
import com.juliomesquita.cdc.book.domain.repositories.BookRepository;
import com.juliomesquita.cdc.shared.repositories.SpecificationUtils;
import com.juliomesquita.cdc.shared.utils.Pagination;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class FindPartialUseCaseImpl extends FindPartialUseCase {
    private final BookRepository bookRepository;

    public FindPartialUseCaseImpl(final BookRepository bookRepository) {
        this.bookRepository = Objects.requireNonNull(bookRepository, "bookRepository");
    }

    @Override
    public Pagination<BookPartialResponse> execute(final SearchQuery searchQuery) {
        final PageRequest pageRequest = searchQuery.toPageRequest();
        final Specification<Book> specification = SpecificationUtils.build(searchQuery);

        final Page<Book> pageable = this.bookRepository.findAll(specification, pageRequest);
        return Pagination.create(
            pageable.get().map(BookPartialResponse::from).toList(), searchQuery.currentPage(), searchQuery.itemsPerPage(), pageable.getTotalElements()
        );
    }
}
