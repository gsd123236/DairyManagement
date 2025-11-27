package employee_management.com.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
public class CompanyRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;
    private String company_Name;
    private String owner_name;
    @Email(message = "Invalid email format ")
    private String company_Email;
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String phone_Number;
    private String company_Address;
    private String Password;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getCompany_Name() {
        return company_Name;
    }

    public void setCompany_Name(String company_Name) {
        this.company_Name = company_Name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getCompany_Email() {
        return company_Email;
    }

    public void setCompany_Email(String company_Email) {
        this.company_Email = company_Email;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public String getCompany_Address() {
        return company_Address;
    }

    public void setCompany_Address(String company_Address) {
        this.company_Address = company_Address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
