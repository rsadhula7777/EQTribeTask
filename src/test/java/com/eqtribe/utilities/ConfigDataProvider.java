package com.eqtribe.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author rsadhula
 */
public class ConfigDataProvider {

	Properties pro;

	public ConfigDataProvider() {

		File src = new File("./Config/Config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Not able to load the config file >>" + e.getMessage());
		}
	}

	public String getDataFromConfig(String keyToSearch) {

		return pro.getProperty(keyToSearch);

	}

	public String getBrowser() {

		return pro.getProperty("browser");

	}

	public String getURL() {

		return pro.getProperty("url");

	}

	public String getUserName() {

		return pro.getProperty("username");

	}

	public String getPassword() {

		return pro.getProperty("password");

	}

}
