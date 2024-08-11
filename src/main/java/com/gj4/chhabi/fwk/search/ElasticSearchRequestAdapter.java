package com.gj4.chhabi.fwk.search;

import com.google.common.collect.Lists;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Order;
import org.springframework.data.elasticsearch.core.query.Query;

/**
 * @author Krunal Lukhi
 * @since 11/08/24
 */
public class ElasticSearchRequestAdapter {

    private static ElasticSearchRequestAdapter INSTANCE;

    private ElasticSearchRequestAdapter() {

    }

    public static ElasticSearchRequestAdapter getInstance() {
        if (INSTANCE == null) {
            return INSTANCE = new ElasticSearchRequestAdapter();
        }
        return INSTANCE;
    }

    public Query adapt(Request request) {
        Criteria criteria = new Criteria();
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        addFilters(criteriaQuery, request);
        addSortInfo(criteriaQuery, request);
        addPageInfo(criteriaQuery, request);
        return criteriaQuery;
    }

    /***********************************************************************************************************
     *                                           PRIVATE METHODS                                                                            *
     ***********************************************************************************************************/

    private void addPageInfo(CriteriaQuery criteriaQuery, Request request) {
        PageInfo pageInfo = request.getPageInfo();
        if (pageInfo == null) {
            pageInfo = new PageInfo(0, 10);
        }
        criteriaQuery.setPageable(PageRequest.of(pageInfo.getPage(), pageInfo.getSize()));
    }

    private void addSortInfo(CriteriaQuery criteriaQuery, Request request) {
        SortInfo sortInfo = request.getSortInfo();
        SortInfo.Order order = sortInfo.getOrder();
        switch (order) {
            case ASC -> {
                criteriaQuery.addSort(Sort.by(Lists.newArrayList(new Order(Sort.Direction.ASC, sortInfo.getField()))));
                break;
            }
            case DESC -> {
                criteriaQuery.addSort(Sort.by(Lists.newArrayList(new Order(Sort.Direction.DESC, sortInfo.getField()))));
                break;
            }
            default -> {
                throw new IllegalArgumentException("Order not allowed " + order.name());
            }
        }
    }

    private void addFilters(CriteriaQuery criteriaQuery, Request request) {

    }

}
