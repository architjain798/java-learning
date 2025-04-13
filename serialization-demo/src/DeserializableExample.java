
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeserializableExample {

    public static void main(String[] args) throws IOException {
        Path currentDirPath = Paths.get("").toAbsolutePath();
        Path configPath = currentDirPath.resolve("src").resolve("sample.txt");
        String currentDir2 = configPath.toString();

        Employee.counter = 100;

        try (FileInputStream fin = new FileInputStream(currentDir2); ObjectInputStream ob = new ObjectInputStream(fin)) {
            Employee e = (Employee) ob.readObject();
            System.out.println(e.getEmpId());
            System.out.println(e.getEmpName());
            System.out.println(e.getEmpSalary());
            System.out.println(e.getEmpUserName());
            // The empUserPwd field is transient and will not be serialized, so it will always print null.
            System.out.println(e.getEmpUserPwd());
            System.out.println(e.counter);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
