package vti.com.service.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vti.com.Constants.OPERATOR;
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
                new Expression(field, OPERATOR.NOT_EQUALS, value.getEquals()));
        }

        if (value.getGreaterThan() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.GREATER_THAN, value.getEquals()));
        }

        if (value.getGreaterThanOrEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, value.getEquals()));
        }

        if (value.getLessThan() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.LESS_THAN, value.getEquals()));
        }

        if (value.getLessThanOrEquals() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, value.getEquals()));
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
                new Expression(field, OPERATOR.NOT_EQUALS, value.getEquals()));
        }

        if (value.getContains() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.CONTAINS, value.getEquals()));
        }

        if (value.getNotContains() != null) {
            return new CustomSpecification<>(
                new Expression(field, OPERATOR.NOT_CONTAINS, value.getEquals()));
        }

        return null;
    }
}
