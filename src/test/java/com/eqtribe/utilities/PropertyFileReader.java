package com.eqtribe.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author rsadhula
 */
public class PropertyFileReader {

	/**
	 * Properties.
	 */
	public static Properties ObjRepoProp;
	public static Properties TextProp;

	/**
	 * Load Property File.
	 * 
	 **/
	public static void loadProprtyFile() {

		ObjRepoProp = new Properties();
		TextProp = new Properties();

		try {

			// Reading/loading the ObjectRepository property file.
			ObjRepoProp.load(new FileInputStream("./Config/ObjectRepository.properties"));
			// Reading/loading the Text property file
			//TextProp.load(new FileInputStream("./Config/Text_Appbuilder.properties"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
