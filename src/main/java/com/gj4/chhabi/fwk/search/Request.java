package com.gj4.chhabi.fwk.search;

import java.util.List;

/**
 * @author Krunal Lukhi
 * @since 11/08/24
 */
public class Request {

    private String key;
    private List<Filter> filters;
    private PageInfo pageInfo;
    private SortInfo sortInfo;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public SortInfo getSortInfo() {
        return sortInfo;
    }

    public void setSortInfo(SortInfo sortInfo) {
        this.sortInfo = sortInfo;
    }
}
