
import java.io.Serializable;

public class Employee implements Serializable {

    int empId;
    String empName;
    double empSalary;

    public Employee(int empId, String empName, double empSalary) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
    }
}
