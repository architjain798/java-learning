
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    static int counter = 0;

    int empId;
    String empName;
    double empSalary;

    String empUserName;
    transient String empUserPwd;

    public Employee(int empId, String empName, double empSalary, String empUserName, String empUserPwd) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empUserName = empUserName;
        this.empUserPwd = empUserPwd;
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
}
