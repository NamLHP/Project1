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
        String CONTAINS = "contains";
        String GREATER_THAN = "greaterThan";
        String LESS_THAN = "lessThan";
    }
}
