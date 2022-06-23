package com.maohulu.custom.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
public class SocketChannelDemo {
    public static void main(String[] args) {
        try {
            methodA();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void methodA() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);

        serverSocketChannel.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            while (socketChannel.read(byteBuffer) != -1) {
                //打印结果
                System.out.println(new String(byteBuffer.array()));
                //清空缓冲区
                byteBuffer.clear();
            }
        }
    }
}
