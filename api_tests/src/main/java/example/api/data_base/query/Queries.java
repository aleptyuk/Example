package example.api.data_base.query;

public enum Queries {
    SELECT_ALL_FROM_USER_PROFILE("select*from user_profile");

    private final String queryType;

    Queries(String queryType) {
        this.queryType = queryType;
    }

    public String getValue() {
        return queryType;
    }
}
