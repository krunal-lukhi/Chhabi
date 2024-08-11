package com.gj4.chhabi.fwk.search;

import java.util.List;

/**
 * @author Krunal Lukhi
 * @since 11/08/24
 */
public class Filter {
    private String field;
    private FilterType filterType;
    private List<Object> values;
    private List<Filter> filters;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}
