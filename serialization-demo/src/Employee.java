
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    static int counter = 0;

    int empId;
    String empName;
    double empSalary;

    String empUserName;
    transient String empUserPwd;

    Address address;

    public Employee(int empId, String empName, double empSalary, String empUserName, String empUserPwd,Address address) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empUserName = empUserName;
        this.empUserPwd = empUserPwd;
        this.address = address;
    }

    public int getEmpId() {
        return empId;
    }

    public String getEmpUserName() {
        return empUserName;
    }

    public String getEmpUserPwd() {
        return empUserPwd;
    }

    public String getEmpName() {
        return empName;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public Address getAddress() {
        return address;
    }
    
}
