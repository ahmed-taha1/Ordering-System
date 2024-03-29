package OrderingSystem.Authentication.Service;

import OrderingSystem.Authentication.Controller.RequestsBodyRecords;
import OrderingSystem.Customer.Entities.Customer;
import OrderingSystem.Customer.DataAccess.ICustomersDataAccess;
import OrderingSystem.OrderingSystemApplication;

import java.util.regex.Pattern;

public class AuthenticationService {
    private static final ICustomersDataAccess customersDataAccess = OrderingSystemApplication.getCustomersDataAccess();

    public static boolean login(RequestsBodyRecords.LoginCustomerDataBodyRequest data){
        Customer quiredCustomer = customersDataAccess.getCustomerByEmail(data.email());
        if(quiredCustomer == null || !quiredCustomer.getPassword().equals(data.password())){
            return false;
        }
        OrderingSystemApplication.setActiveUser(quiredCustomer);
        return true;
    }

    public static boolean register(RequestsBodyRecords.RegisterCustomerDataBodyRequest data){
        boolean isEmailExistBefore = (customersDataAccess.getCustomerByEmail(data.email()) != null);
        boolean isAddressNull = (data.address() == null);
        boolean isBalanceInvalid = (data.balance() <= 0);
        boolean isPasswordInvalid = !passwordPattern.matcher(data.password()).matches();
        boolean isEmailInvalid = !emailPattern.matcher(data.email()).matches();
        if(isEmailExistBefore || isAddressNull || isBalanceInvalid || isPasswordInvalid || isEmailInvalid){
            return false;
        }
        Customer newCustomer = new Customer(data.name(), data.email(), data.password(), data.phoneNumber(), data.balance(), data.address());
        customersDataAccess.insertCustomerToDB(newCustomer);
        return true;
    }


    /** password validation requirements
     * (?=.*[0-9]): At least one digit.
     * (?=.*[a-z]): At least one lowercase letter.
     * (?=.*[A-Z]): At least one uppercase letter.
     * (?=.*[@#$%^&+=!]): At least one special character (you can customize this set).
     * (?=\S+$): No whitespaces allowed.
     * .{8,}: At least 8 characters in total.
     */
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    /** email validation requirements
     * ^[a-zA-Z0-9_+&*-]+: Starts with one or more alphanumeric characters or special characters (underscore, plus, ampersand, asterisk, hyphen).
     * (?:\\.[a-zA-Z0-9_+&*-]+)*: Followed by zero or more groups of a dot and alphanumeric characters.
     * /@(?:[a-zA-Z0-9-]+\\.)+: Followed by an at symbol (@) and one or more groups of alphanumeric characters or hyphens followed by a dot.
     * [a-zA-Z]{2,7}$: Ends with two to seven alphabetic characters.
     */
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

}
