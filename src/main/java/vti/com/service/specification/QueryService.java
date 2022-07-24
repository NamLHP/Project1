package vti.com.service.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vti.com.Constants.OPERATOR;
import vti.com.filter.DateFilter;
import vti.com.filter.IntegerFilter;
import vti.com.filter.LongFilter;
import vti.com.filter.StringFilter;

@Component
public class QueryService<T> {

    public Specification<T> buildLongFilter(String field, LongFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }

        if (value.getNotEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }

        if (value.getGreaterThan() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.GREATER_THAN, value.getGreaterThan()));
        }

        if (value.getGreaterThanOrEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, value.getGreaterThanOrEquals()));
        }

        if (value.getLessThan() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.LESS_THAN, value.getLessThan()));
        }

        if (value.getLessThanOrEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, value.getLessThanOrEquals()));
        }
        return null;
    }

    public Specification<T> buildStringFilter(String field, StringFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }

        if (value.getNotEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }

        if (value.getContains() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.CONTAINS, value.getContains()));
        }

        if (value.getNotContains() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.NOT_CONTAINS, value.getNotContains()));
        }

        return null;
    }

    public Specification<T> buildIntegerFilter(String field, IntegerFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }

        if (value.getNotEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }

        if (value.getGreaterThan() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.GREATER_THAN, value.getGreaterThan()));
        }

        if (value.getGreaterThanOrEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, value.getGreaterThanOrEquals()));
        }

        if (value.getLessThan() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.LESS_THAN, value.getLessThan()));
        }

        if (value.getLessThanOrEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, value.getLessThanOrEquals()));
        }
        return null;
    }


    public Specification<T> buildDateFilter(String field, DateFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }

        if (value.getNotEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }

        if (value.getGreaterThan() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.GREATER_THAN, value.getGreaterThan()));
        }

        if (value.getGreaterThanOrEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, value.getGreaterThanOrEquals()));
        }

        if (value.getLessThan() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.LESS_THAN, value.getLessThan()));
        }

        if (value.getLessThanOrEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, value.getLessThanOrEquals()));
        }
        return null;
    }
}
