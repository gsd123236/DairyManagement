package employee_management.com.Dto;

public class CompanyDto {

    private int c_id;
    private String company_Name;
    private String owner_name;
    private String phone_Number;


    public CompanyDto(int c_id, String company_Name, String owner_name, String phone_Number) {
        this.c_id = c_id;
        this.company_Name = company_Name;
        this.owner_name = owner_name;
        this.phone_Number = phone_Number;
    }
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

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }
}

