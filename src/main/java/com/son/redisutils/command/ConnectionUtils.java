package com.son.redisutils.command;

import redis.clients.jedis.Jedis;

public class ConnectionUtils {

	public static Jedis getConnection(final String host, final int port){
		return new Jedis (host, port);
	}
	
}
