package com.menjin.opener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class OpenerController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${opener.ip}")
	private String openerIP;
	
	@Value("${opener.port}")
	private Integer openerPort;
	
	
	private SocketChannel createSocketChannel(String hostName, int port) throws IOException{
		SocketChannel sChannel = SocketChannel.open();
		sChannel.configureBlocking(true);
		boolean connection = sChannel.connect(new InetSocketAddress(hostName, port));
		
		if (connection){
			System.out.println("Connect OK..");
		} else {
			System.out.println("Fail connection..");
		}
		return sChannel;
	}
	
	
	public void checkOpenerStatus(){
		String openerIP="14.154.156.146";
		int openerPort = 5000;
		String sendContent = "off1";
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			buf.clear();
			
			SocketChannel channel = createSocketChannel(openerIP, openerPort);
			if (channel.isConnected()){
				System.out.println("Connected");
			}
//			while (!channel.finishConnect()){
//				System.out.println("Waiting connect..");
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			buf.put(sendContent.getBytes());
//			buf.flip();
//			int result = channel.write(buf);
//			buf.clear();
//			if (result > 0){
//				System.out.println("已经写入" + result + "个字节");
//			}
//			channel.read(buf);
			
			int result = -1;
			buf.clear();
			
			channel.read(buf);
			buf.flip();
			while (buf.remaining() > 0){
				System.out.println((char)buf.get());
			}
			buf.clear();
			buf.put(sendContent.getBytes());
			buf.flip();
			result = channel.write(buf);
			if (result > 0){
				System.out.println("已经写入" + result + "个字节");
			}
			buf.clear();
			channel.read(buf);
			buf.flip();
			while (buf.remaining() > 0){
				System.out.println((char)buf.get());
			}
			buf.clear();
			channel.close();
			
		} catch (UnknownHostException e) {
			logger.error("Connect opener error", e);
		} catch (IOException e) {
			logger.error("Connect opener error", e);
		}
		
		
		
	}
	
	public static void main(String[] args){
		OpenerController open = new OpenerController();
		
		open.checkOpenerStatus();
	}
}
