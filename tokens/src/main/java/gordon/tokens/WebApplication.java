package gordon.tokens;
/**
* @author Gordon
*/

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import redis.clients.jedis.Jedis;

public class WebApplication implements ServletContextListener {

	public static Properties properties 	= null;
	public static Jedis jedis			= null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		System.out.println("contextInitialized: " + dateFormat.format(new Date()));
		// Step1
		initProperties();
		// Step2
		initJedis();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	
	private void initProperties() {
		properties = new Properties();
	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
	    if (inputStream != null) {
	        try {
	        		properties.load(inputStream);
	        		System.out.println("properties: " + properties.size());		
	        		for(String key : properties.stringPropertyNames()) {
	        			  String value = properties.getProperty(key);
	        			  System.out.println(String.format("%s = %s", key, value));
	        		}
	        		
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	    		System.out.println("config.properties : Not Found");
	    }
	}
	
	private void initJedis() {
		try {
			jedis = new Jedis(properties.getProperty("Jedis", "localhost"));
			// test redis connection
			String result = jedis.setex("redisTest", 180, "test by gordon");
			// redis插入資料 key, 時效(秒), value
			System.out.println("Redis Connection:" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Redis Connection:" + e.toString());
		}
	}
}
