package gov.nara.um.util;

public final class UmMappings {
    public static final String TASKS = "tasks";

    public static final String USERS = "users";

    public static final String USERS_BUSINESSUNITS = "users/tasks";

    public static final String BUSINESS_UNITS_USERS = "/tasks/users";


    public static final String BUSINESS_UNITS_CONFIGURATIONS = "/task-configurations";

    public static final String BUSINESS_UNITS_CONFIGURATIONS_PREFERENCES = "/task-config-preferences";
    // discoverability

    public static final class Singular {
        public static final String BUSINESSUNIT = "task";
        public static final String USER = "user";
    }
    public static final String AUTHENTICATION = "authentication";

    private UmMappings() {
        throw new AssertionError();
    }

    // API




}
