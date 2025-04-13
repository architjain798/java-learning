import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializableExample {
    public static void main(String[] args) throws IOException {
        Address a1 = new Address("delhi", "india");

        Employee e1 = new Employee(1, "archit", 100,"archit123","pwd123",a1);
        Employee e2 = new Employee(2, "ajay", 200,"ajay987","pwdd987",a1);

        Employee.counter = 10;


        Path currentDirPath = Paths.get("").toAbsolutePath();
        Path configPath = currentDirPath.resolve("src").resolve("sample.txt");
        String filePath = configPath.toString();

        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream b = new ObjectOutputStream(fos)) {
            b.writeObject(e2);
            System.out.println("Current directory: " + filePath);
        }
    }
}
