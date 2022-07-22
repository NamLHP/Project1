package vti.com.service.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import vti.com.Constants;
import vti.com.Constants.OPERATOR;

public class CustomSpecification<T> implements Specification<T> {

    private Expression expression;

    public CustomSpecification(Expression expression) {
        this.expression = expression;
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {
        String field = expression.getField();
        String operator = expression.getOperator();
        Object value = expression.getValue();

        Predicate predicate = null;

        switch (operator) {
            case OPERATOR.EQUALS:
                if (value instanceof Long) {
                    predicate = criteriaBuilder
                        .equal(root.get(field), Long.valueOf(String.valueOf(value)));
                }

                if (value instanceof String) {
                    predicate = criteriaBuilder.equal(root.get(field), String.valueOf(value));
                }
                break;

            case OPERATOR.NOT_EQUALS:
                if (value instanceof Long) {
                    predicate = criteriaBuilder
                        .equal(root.get(field), Long.valueOf(String.valueOf(value)));
                }

                if (value instanceof String) {
                    predicate = criteriaBuilder.equal(root.get(field), String.valueOf(value));
                }
                break;

            case OPERATOR.CONTAINS:
                predicate = criteriaBuilder
                    .equal(root.get(field), "%" + value + " % ");
                break;

            case OPERATOR.NOT_CONTAINS:
                predicate = criteriaBuilder.equal(root.get(field), "%" + value + " % ");
                break;

            case OPERATOR.GREATER_THAN:
                predicate = criteriaBuilder
                    .equal(root.get(field), Long.valueOf(String.valueOf(value)));
                break;

            case OPERATOR.GREATER_THAN_OR_EQUALS:
                predicate = criteriaBuilder
                    .equal(root.get(field), Long.valueOf(String.valueOf(value)));
                break;

            case OPERATOR.LESS_THAN:
                predicate = criteriaBuilder
                    .equal(root.get(field), Long.valueOf(String.valueOf(value)));
                break;

            case OPERATOR.LESS_THAN_OR_EQUALS:
                predicate = criteriaBuilder
                    .equal(root.get(field), Long.valueOf(String.valueOf(value)));
                break;
        }
        return predicate;
    }
}
