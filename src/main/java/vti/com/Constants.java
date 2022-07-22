package vti.com;

public class Constants {
    public interface ACCOUNT {
        String ID = "id";
        String USERNAME = "userName";
        String FIRST_NAME = "firstName";
    }

    public interface DEPARTMENT {
        String ID = "id";
        String TOTAL_MEMBER = "totalMember";
        String TYPE = "type";
        String NAME = "name";
    }

    public interface OPERATOR {
        String EQUALS = "equals";
        String NOT_EQUALS = "notEquals";
        String CONTAINS = "contains";
        String NOT_CONTAINS = "notContains";
        String GREATER_THAN = "greaterThan";
        String GREATER_THAN_OR_EQUALS = "greaterThanOrEquals";
        String LESS_THAN = "lessThan";
        String LESS_THAN_OR_EQUALS = "lessThanOrEquals";
    }
}
