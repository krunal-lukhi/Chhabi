package com.gj4.chhabi.fwk.search;

import com.google.common.collect.Lists;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Order;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

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
        List<Filter> filters = request.getFilters();
        for (Filter filter : filters) {
            Criteria criteria = createCriteria(filter);
            criteriaQuery.addCriteria(criteria);
        }
    }

    private Criteria createCriteria(Filter filter) {
        Criteria criteria = null;
        switch (filter.getFilterType()){
            case IN -> {
                criteria = Criteria.where(filter.getField()).in(filter.getValues());
            }
            case IS -> {
                criteria = Criteria.where(filter.getField()).is(filter.getValues().get(0));
            }
            case NIN -> {
                criteria = Criteria.where(filter.getField()).notIn(filter.getValues());
            }
            case NOT -> {
                criteria = Criteria.where(filter.getField()).not().is(filter.getValues().get(0));
            }
            case AND -> {
                for(Filter nestedFilter:filter.getFilters()){
                    if(criteria==null){
                        criteria=new Criteria();
                    }
                    criteria = criteria.and(createCriteria(nestedFilter));
                }
            }
            case OR -> {
                for(Filter nestedFilter:filter.getFilters()){
                    if(criteria==null){
                        criteria=new Criteria();
                    }
                    criteria = criteria.or(createCriteria(nestedFilter));
                }
            }
            case EXITS -> {
                criteria = Criteria.where(filter.getField()).exists();
            }
            case DOES_NOT_EXISTS -> {
                criteria = Criteria.where(filter.getField()).not().exists();
            }
        }
        return criteria;
    }
}
