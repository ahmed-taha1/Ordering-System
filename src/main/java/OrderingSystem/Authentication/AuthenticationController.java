package OrderingSystem.Authentication;

import OrderingSystem.Customer.Customer;
import OrderingSystem.Customer.InMemoryCustomersDataAccess;
import OrderingSystem.Customer.ICustomersDataAccess;
import OrderingSystem.OrderingSystemApplication;
import OrderingSystem.Services.AuthenticationService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Pattern;

public class AuthenticationController {
    private static final ICustomersDataAccess customersDataAccess = InMemoryCustomersDataAccess.getInstance();

    public static boolean login(@RequestParam AuthenticationService.LoginCustomerDataBodyRequest data){
        Customer quiredCustomer = customersDataAccess.getCustomerByEmail(data.email());
        if(quiredCustomer == null || !quiredCustomer.getPassword().equals(data.password())){
            return false;
        }
        OrderingSystemApplication.activeUser = quiredCustomer;
        return true;
    }

    public static boolean register(@RequestParam AuthenticationService.RegisterCustomerDataBodyRequest data){
        boolean isEmailExistBefore = (customersDataAccess.getCustomerByEmail(data.email()) != null);
        boolean isAddressNull = (data.address() == null);
        boolean isBalanceInvalid = (data.balance() <= 0);
        boolean isPasswordInvalid = !passwordPattern.matcher(data.password()).matches();
        boolean isEmailInvalid = !emailPattern.matcher(data.email()).matches();
        if(isEmailExistBefore || isAddressNull || isBalanceInvalid || isPasswordInvalid || isEmailInvalid){
            return false;
        }
        Customer newCustomer = new Customer(data.email(), data.password(), data.phoneNumber(), data.balance(), data.address());
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
