package com.juliomesquita.cdc.shared.controllers;

import com.juliomesquita.cdc.shared.utils.Filter;

import java.util.List;

public record FilterRequest(List<Filter> filters) {
}
