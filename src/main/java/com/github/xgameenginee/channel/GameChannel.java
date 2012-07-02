package com.github.xgameenginee.channel;

import org.jboss.netty.channel.group.DefaultChannelGroup;

import com.github.xgameenginee.buffer.GameDownBuffer;
import com.github.xgameenginee.core.Connection;

public class GameChannel {
	private DefaultChannelGroup channel;
	
	public GameChannel(String name) {
		channel = new DefaultChannelGroup(name);
	}
	
	public boolean addConnection(Connection c) {
		return channel.add(c.getChannelHandlerContext().getChannel());
	}
	
	public boolean removeConnection(Connection c) {
		return channel.remove(c.getChannelHandlerContext().getChannel());
	}
	
	public void broadcast(Connection except, GameDownBuffer buffer) {
		channel.remove(except.getChannelHandlerContext().getChannel());
		channel.write(buffer.getChannelBuffer());
		channel.add(except.getChannelHandlerContext().getChannel());
	}
	
	public void broadcast(GameDownBuffer buffer) {
		channel.write(buffer.getChannelBuffer());
	}
}
