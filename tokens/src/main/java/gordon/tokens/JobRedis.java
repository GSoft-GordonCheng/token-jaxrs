package gordon.tokens;

import java.util.Properties;

import redis.clients.jedis.Jedis;

/**
* @author Gordon
*/
public class JobRedis implements IInitialized {
	
	private Jedis jedis = null;
	private String host = null;
	private Properties properties;
	private final String HOST = "localhost"; 
	public JobRedis(String host) {
		this(host, null);
	}
	
	public JobRedis(Properties properties) {
		this(null, properties);
	}

	public JobRedis(String host, Properties properties) {
		this.host = host;
		this.properties = properties;
		if (null==host) {
			this.host = HOST;
		}
	}

	@Override
	public String exec() {
		StringBuilder sb = new StringBuilder();
		try 
		{
			String hostString = this.properties.getProperty("Jedis", this.host);
			sb.append(String.format("Redis Connection: ", hostString));			
			if (null == jedis) {
				jedis = new Jedis(hostString);
			} else {
				if (true == jedis.isConnected()) {
					jedis.disconnect();
					sb.append("Redis Disconnect.");
				}
				jedis.close();
				sb.append("Redis Close.");
				jedis.connect();
				sb.append("Redis Connect.");
			}
			String status = jedis.setex(this.getClass().getName(), 180, "test by gordon");
			sb.append(String.format("Redis Testing: %s", status));
		} catch (Exception e) {
			e.printStackTrace();
			sb.append(String.format("Redis Error: %s", e.getMessage()));
		}
		return sb.toString();
	}
	
	public Jedis getJedis()
	{
		return this.jedis;
	}
}
