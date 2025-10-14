package com.juliomesquita.cdc.livro.application.book.usecases.findpartial;

import com.juliomesquita.cdc.shared.utils.Pagination;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import com.juliomesquita.cdc.shared.utils.UseCase;

public abstract class FindPartialUseCase extends UseCase<SearchQuery, Pagination<BookPartialResponse>> {
}
