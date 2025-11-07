package base;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final String PROPERTIES_FILE = "test.properties";
    private static Properties properties;

    static {
        properties = loadProperties();
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE)) {

            if (input == null) {
                throw new RuntimeException(PROPERTIES_FILE + " не найден");
            }

            props.load(input);
            System.out.println("Properties загружены");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
