package utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ConfigUtils {

	public static String getConfig(String property) {
		String configPath = "/config/config.properties";
		return getConfig(property, configPath);
	}

	public static String getConfig(String property, String configPath) {
		InputStream is = ConfigUtils.class.getResourceAsStream(configPath);
		if (is == null)
			return "";
		Properties p = new Properties();
		try {
			p.load(new InputStreamReader(is, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String gameName = p.getProperty(property);
		return gameName == null ? "" : gameName;
	}
 
}
