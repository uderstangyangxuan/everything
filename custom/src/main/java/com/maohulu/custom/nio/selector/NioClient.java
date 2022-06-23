package com.maohulu.custom.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
            socketChannel.configureBlocking(false);
            boolean connect = socketChannel.connect(inetSocketAddress);
            if (!connect) {
                //等待连接的过程中
                while (!socketChannel.finishConnect()) {
                    System.out.println("连接服务器需要时间，期间可以做其他事情...");
                }
            }

            String msg = "hello java技术爱好者！";
            ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
            //把byteBuffer数据写入到通道中
            socketChannel.write(byteBuffer);
            //让程序卡在这个位置，不关闭连接
            System.in.read();
        }
    }

}
