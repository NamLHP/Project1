package vti.com.service.specification;
import vti.com.Constants.ACCOUNT;
import vti.com.Constants.OPERATOR;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import vti.com.entity.Account;

@Data
@AllArgsConstructor
public class AccountSpecification implements Specification<Account> {

    private Expression expression;

    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {

        if (expression.getField().equals(ACCOUNT.USERNAME)) {
            if (expression.getOperator().equals(OPERATOR.CONTAINS)) {
                return criteriaBuilder
                    .like(root.get(expression.getField()), "%" + expression.getValue() + "%");
            }
            if (expression.getOperator().equals(OPERATOR.EQUALS)) {
                return criteriaBuilder
                    .equal(root.get(expression.getField()), expression.getValue());
            }
        }

        if (expression.getField().equals(ACCOUNT.ID)) {
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
