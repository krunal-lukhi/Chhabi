package com.gj4.chhabi.fwk.search;

/**
 * @author Krunal Lukhi
 * @since 11/08/24
 */
public class SortInfo {
    private String field;
    private Order order;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public enum Order {
        ASC,
        DESC,
        ;
    }
}
