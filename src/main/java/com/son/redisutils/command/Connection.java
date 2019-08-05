package com.son.redisutils.command;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class Connection {

	private Jedis connection;
	
	public Connection(String host, int port){
		connection = new Jedis(host, port);
	}
	
	public Connection(String host, int port, int timeout){
		connection = new Jedis(host, port, timeout);
	}
	
	public boolean isConnected(){
		return this.connection.isConnected();
	}
	
	public Set<String> getKeys(String pattern){
		return connection.keys(pattern);
	}
	
	public String getValue(String key){
		return connection.get(key);
	}
	
	public void setValue(String key, String value){
		connection.set(key,value);
	}
	
	public void remove(String key){
		connection.del(key);
	}
	
	public void connect(){
		connection.connect();
	}
	
	public void closeConnection(){
		connection.close();
	}
}
