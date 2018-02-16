package cn.util;

import java.util.List;

//import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	//Redis服务器IP
	  private static String ADDR = "192.168.159.10";
//	   private static String ADDR = "101.200.148.83";
	   //Redis的端口号
	   private static int PORT = 6379;
//	   private static int PORT = 6380;
	   
	   //访问密码
	   private static String AUTH = "123456";
//	   private static String AUTH = "37lion@redisserCentOS83";
	   
	   //可用连接实例的最大数目，默认值为8；
	   //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	   private static int MAX_ACTIVE = 1024;
	   
	   //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	   private static int MAX_IDLE = 500;
	   
	   //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	   private static int MAX_WAIT = 1000;
	   
	   private static int TIMEOUT = 1000;
	   
	   //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	   private static boolean TEST_ON_BORROW = true;
	   
	   private static JedisPool jedisPool = null;
	   

	   private static void initialPool(){
		   try {
	           JedisPoolConfig config = new JedisPoolConfig();
	           config.setMaxActive(MAX_ACTIVE);
	           config.setMaxIdle(MAX_IDLE);
	           config.setMaxWait(MAX_WAIT);
	           config.setTestOnBorrow(TEST_ON_BORROW);
	           jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
	           //jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	    }
	   private static synchronized void poolInit() {
	        if (jedisPool == null) {  
	            initialPool();
	        }
	    }
	   /**
	    * 获取Jedis实例
	    * @return
	    */
	   public synchronized static Jedis getJedis() {
		   if (jedisPool == null) {  
	            poolInit();
	        }
	        Jedis jedis = null;
	        try {  
	            if (jedisPool != null) {  
	                jedis = jedisPool.getResource(); 
	            }
	        } catch (Exception e) {  
	            e.printStackTrace();
	            returnResource(jedis); 
	        }
	        return jedis;
	   }
	   
	   /**
	    * 释放jedis资源
	    * @param jedis
	    */
	   public static void returnResource(final Jedis jedis) {
		   if (jedis != null && jedisPool !=null) {
	            jedisPool.returnResource(jedis);
	        }
	   }
	   public List<String> getValues(Jedis jedis,String... keys) {
		    return jedis.mget(keys);
		  }
	   /**
	    * 获取系统参数ID对应名称
	    * @param jedis
	    * @param key 关键字
	    * @param id 
	    * @return
	    */
//	   public String getSysValues(Jedis jedis,String key,String id) {
//		   try{
//	      jedis.select(6);
//		  String jsonsr =  jedis.get(key+id);
//		  JSONObject json = new JSONObject(jsonsr);
//		  String name =json.getString("name");
//		   return name;
//	   } catch (Exception e) {
//			 String jsonsr =  jedis.get(key+id);
//			System.out.println(jsonsr);
//			return "";
//		}
//	   }
	   
	   /**
	    * 获取系统参数ID对应名称
	 * @param jedis
	 * @param key 关键+id一起
	 * @return
	 */
//	public String getValues(Jedis jedis, String key){
//		   try {
//			   String jsonsr =  jedis.get(key);
//			   JSONObject json = new JSONObject(jsonsr);
//			   String name =json.getString("name");
//			   return name;
//		} catch (Exception e) {
//			 String jsonsr =  jedis.get(key);
//			System.out.println(jsonsr);
//			return "";
//		}
//		  
//	   }
	   
	   
	/**
	 *  获取系统参数ID对应行业职能地区等级
	 * @param jedis
	 * @param key
	 * @param id
	 * @return
	 */
//	public String getSysParentid(Jedis jedis, String key, String id) {
//
//		String jsonsr = jedis.get(key + id);
//		JSONObject json = new JSONObject(jsonsr);
//		String parentid = json.getString("parentid");
//		return parentid;
//	}
//	   /**
//	    * 获取系统参数ID行业职能地区等级
//	 * @param jedis
//	 * @param key
//	 * @param id
//	 * @return
//	 */
//	public String getSysLevel(Jedis jedis, String key, String id){
//		   
//		   String jsonsr = jedis.get(key + id);
//			JSONObject json = new JSONObject(jsonsr);
//			String level = json.getString("level");
//			return level;
//	   }
	
	
	   
	   /**
	    * 初始化系统参数
	    * @param jedis
	    * @param key
	    * @param id
	    * @return
	    */
//	   public String setSysValues(Jedis jedis,List<CategoryIndustryPO> re) {
//		   jedis.select(6);
//		   
//		   for (int i = 0; i < re.size(); i++) {
//			   CategoryIndustryPO po = re.get(i);
//			   HashMap<Object, Object> identity = new HashMap<>();
//			   identity.put("type", po.getValue());
//			   identity.put("name", po.getName());
//			   identity.put("keys", po.getKeys());
//			   
//			   JSONObject json = new JSONObject(identity);
//			   jedis.set(po.getKeys()+po.getValue(), json.toString());
//		   }
//		  
//		   return "suc";
//	   }
	   public Long deleteValues(Jedis jedis,String... keys) {
		    return jedis.del(keys);
		  }
	   public long calculateSize(Jedis jedis) {
		    return jedis.dbSize();
		  }

	public String getlistsizebyname(Jedis jedis, String keywords) {
		// TODO Auto-generated method stub
		return jedis.get(keywords+"??");
	}
	public static void main(String[] args) {
		RedisUtil ut=new RedisUtil();
		Jedis jedis=ut.getJedis();
		try {
			System.out.println(jedis);
			jedis.select(15);
			jedis.set("testKey", "testValue");
			System.out.println(jedis.get("testKey"));
			System.out.println(jedis.del("testKey"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ut.returnResource(jedis);
		}
	}
}