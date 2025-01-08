package gov.nara.um.util;

public final class UmMappings {
    public static final String TASKS = "tasks";

    public static final String USERS = "users";

    public static final String USER_TASKS = "user/tasks";

    public static final String TASK_USERS = "/task/users";


    public static final String TASKS_CONFIGURATIONS = "/task-configurations";

    public static final String BUSINESS_UNITS_CONFIGURATIONS_PREFERENCES = "/task-config-preferences";
    // discoverability

    public static final class Singular {
        public static final String TASK = "task";
        public static final String USER = "user";
    }
    public static final String AUTHENTICATION = "authentication";

    private UmMappings() {
        throw new AssertionError();
    }

    // API




}
