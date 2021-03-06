package vti.com.service.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import vti.com.Constants.DEPARTMENT;
import vti.com.Constants.OPERATOR;
import vti.com.entity.Department;

public class DepartmentSpecification implements Specification<Department> {

    private Expression expression;

    public DepartmentSpecification(Expression expression) {
    }

    @Override
    public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {

        if (expression.getField().equals(DEPARTMENT.NAME)) {
            if (expression.getOperator().equals(OPERATOR.CONTAINS)) {
                return criteriaBuilder
                    .like(root.get(expression.getField()), "%" + expression.getValue() + "%");
            }
            if (expression.getOperator().equals(OPERATOR.EQUALS)) {
                return criteriaBuilder
                    .equal(root.get(expression.getField()), expression.getValue());
            }
        }

        if (expression.getField().equals(DEPARTMENT.ID)) {
            if (expression.getOperator().equals(OPERATOR.GREATER_THAN)) {
                return criteriaBuilder.greaterThan(root.get(expression.getField()),
                    Long.valueOf((String) expression.getValue()));
            }
            if (expression.getOperator().equals(OPERATOR.LESS_THAN)) {
                return criteriaBuilder.lessThan(root.get(expression.getField()),
                    Long.valueOf((String) expression.getValue()));
            }
            if (expression.getOperator().equals(OPERATOR.EQUALS)) {
                return criteriaBuilder.equal(root.get(expression.getField()),
                    Long.valueOf((String) expression.getValue()));
            }
        }

        return null;
    }
}
