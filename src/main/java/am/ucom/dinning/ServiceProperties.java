package am.ucom.dinning;

import java.io.IOException;
import java.util.Properties;

/**
 * Class for getting constant data for current environment...
 *
 * @author siranush
 */
public class ServiceProperties {

    public static final String IMAGE_SAVE_PATH_KEY = "image.save.path";

    private ServiceProperties() {
    }

    public static Properties getSettingsProperties() {
        Properties props = new Properties();
        try {
            props.load(ServiceProperties.class.getResourceAsStream("/cafeteria.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }

    /**
     * Ge path of the directory where the images must be saved...
     *
     * @return
     */
    public static String getImageSavePath() {
        return getSettingsProperties().getProperty(IMAGE_SAVE_PATH_KEY);
    }
}