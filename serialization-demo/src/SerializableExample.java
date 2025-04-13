import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializableExample {
    public static void main(String[] args) throws IOException {
        Employee e1 = new Employee(1, "archit", 100);

        Path currentDirPath = Paths.get("").toAbsolutePath();
        Path configPath = currentDirPath.resolve("src").resolve("sample.txt");
        String currentDir2 = configPath.toString();

        FileOutputStream fos = new FileOutputStream(currentDir2);
        ObjectOutputStream b = new ObjectOutputStream(fos);
        b.writeObject(e1);
        System.out.println("Current directory: " + currentDir2);
    }
}
