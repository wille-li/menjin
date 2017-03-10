package com.menjin.opener.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.mapper.BaseCrudMapper;
import com.base.service.BaseServiceImpl;
import com.menjin.opener.mapper.OpenerMapper;
import com.menjin.opener.model.Opener;

@Service
public class OpenerServiceImpl extends BaseServiceImpl<Opener> implements OpenerService {
	
	@Autowired
	private OpenerMapper openerMapper;
	
	@Override
	public String getStatus(Opener opener) throws IOException {
		return sendSign(opener, SIGN_READ);
	}

	@Override
	public String open(Opener opener) throws IOException {
		return sendSign(opener, SIGN_ON);
	}

	@Override
	public String close(Opener opener) throws IOException {
		return sendSign(opener, SIGN_OFF);
	}

	@Override
	public BaseCrudMapper<Opener> init() {
		return openerMapper;
	}
	
	/**
	 * 发信号方法，只允许该服务适用。
	 * @param opener 发送继电器对象
	 * @param sign 发送信号
	 * @return
	 * @throws IOException 
	 */
	private String sendSign(Opener opener, String sign) throws IOException{
		// 1. 获取SocketChanel 连接；
		SocketChannel sChannel = createSocketChannel(opener.getIp(), opener.getPort());
		
		// 2. 获取继电器名字(连接成功Channel就有返回对应继电器的名称)；
		String openerName = opener.getName();
		
		// 2.1 判断继电器名字是否相同，不同则直接返回错误代码；
		if (openerName == null || 
				!openerName.equals(readFromChannel(sChannel))) {
			return RETURN_NOT_SAME_NAME;
		}
		// 3. 发出sign 对应信号；
		int writeSize = writeToChannel(sChannel, sign + opener.getNum());
		
		// 3.1 如果写入失败，返回错误代码
		if (writeSize <= 0){
			return RETURN_WRITE_FAIL;
		}
		// 4. 读取返回信号
		return readFromChannel(sChannel);
	}
	
	/**
	 * 获取连接channel
	 * @param hostName
	 * @param port
	 * @return
	 * @throws IOException
	 */
	private SocketChannel createSocketChannel(String hostName, int port) throws IOException{
		SocketChannel sChannel = SocketChannel.open();
		sChannel.configureBlocking(true);
		boolean connection = sChannel.connect(new InetSocketAddress(hostName, port));
		
		if (connection){
		    logger.info("Connect OK..");
			return sChannel;
		} else {
			logger.error("Fail connection..");
			return null;
		}
	}

	private int writeToChannel(SocketChannel channel, String sign) throws IOException{
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.clear();
		buf.put(sign.getBytes());
		buf.flip();
		int result = channel.write(buf);
		if (result > 0){
			logger.info("已经写入{}个字节", result);
		}
		buf.clear();
		return result;
	}
	
	/**
	 *  这个方法只适用于这个接口，所以不公开
	 * @param channel
	 * @return
	 * @throws IOException
	 */
	private String readFromChannel(SocketChannel channel) throws IOException{
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.clear();
		int resultSize = channel.read(buf);
		buf.flip();
		byte[] resultBytes = new byte[resultSize];
		buf.get(resultBytes, 0, resultSize);
		buf.clear();
		return new String(resultBytes, 0, resultSize, "UTF-8");
	}
}
