import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class CustomLogger {
    static {
        try (InputStream configReader = new FileInputStream("src/main/resources/logger.properties")) {
            LogManager.getLogManager().readConfiguration(configReader);
        } catch (IOException e) {
            System.out.println("Не удалось настроить конфигурацию логгера " + e);
        }
    }
}
