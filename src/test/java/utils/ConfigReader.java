package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("env.properties")) {
            if (input == null) {
                throw new RuntimeException("env.properties tidak ditemukan di resources");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Gagal membaca env.properties", e);
        }
    }

    public static String get(String key) {
        String value = props.getProperty(key);
        return value == null ? null : value.trim().replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
    }

    public static String getUrl(String key) {
        String url = get(key);
        if (url == null || url.isEmpty()) throw new RuntimeException(key + "tidak boleh kosong");
        if (!url.matches("^https?://.*")) url = "https://" + url;
        return url;
    }

    public static int getInt(String key) {
        String value = get(key);
        if (value == null || value.isEmpty())
            throw new RuntimeException(key + " tidak boleh kosong");
        return Integer.parseInt(value);
    }
}
