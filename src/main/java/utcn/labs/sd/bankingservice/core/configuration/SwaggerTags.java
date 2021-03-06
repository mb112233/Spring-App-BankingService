package utcn.labs.sd.bankingservice.core.configuration;

import springfox.documentation.service.Tag;

public final class SwaggerTags {

    public static final String BANKING_SERVICE_TAG = "Banking Service Overview";
    public static final Tag BANKING_SERVICE = new Tag(BANKING_SERVICE_TAG,
            "All Banking related resources", 0);


    public static final String ACCOUNT_TAG = "Account";
    public static final Tag ACCOUNT = new Tag(ACCOUNT_TAG, "Resource: Account");

    public static final String CLIENT_TAG = "Client";
    public static final Tag CLIENT = new Tag(CLIENT_TAG, "Resource: Client");

    public static final String EMPLOYEE_TAG = "Employee";
    public static final Tag EMPLOYEE = new Tag(EMPLOYEE_TAG, "Resource: Employee");

    /**
     * Don't allow class instantiation
     */
    private SwaggerTags() {
        // hide from instantiation
    }
}
