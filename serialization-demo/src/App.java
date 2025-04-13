import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws Exception {
        Path currentDir = Paths.get("").toAbsolutePath();
        System.out.println(currentDir);


        // Method 2: Using Paths (more modern)
        Path currentDirPath = Paths.get("").toAbsolutePath();
        Path configPath = currentDirPath.resolve("README.md");
        String currentDir2 = configPath.toString();

        System.out.println("Current directory: " + currentDir2);
    }
}
