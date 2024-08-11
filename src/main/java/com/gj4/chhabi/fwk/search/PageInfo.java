package com.gj4.chhabi.fwk.search;

/**
 * @author Krunal Lukhi
 * @since 11/08/24
 */
public class PageInfo {

    private int page;
    private int size;

    public PageInfo() {
    }

    public PageInfo(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
