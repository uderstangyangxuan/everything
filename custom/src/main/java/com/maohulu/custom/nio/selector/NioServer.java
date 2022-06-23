package com.maohulu.custom.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        // 打开一个ServerSocketChannel并绑定地址
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        serverSocketChannel.bind(address);

        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //打开一个Selector,把serverSocketChannel注册进来，监听连接事件
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端的连接
        while (true) {
            // 等待3秒，（返回0相当于没有事件）如果没有事件，则跳过
            if (selector.select(3000) == 0) {
                System.out.println("服务器等待3s，没有连接");
                continue;
            }
            // 如果有事件selector.select(3000)>0的情况,获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {
                    ByteBuffer buffer;
                    try (SocketChannel socketChannel = (SocketChannel) selectionKey.channel()) {
                        buffer = (ByteBuffer) selectionKey.attachment();
                        socketChannel.read(buffer);
                    }
                    System.out.println("from 客户端：" + new String(buffer.array()));
                }
                iterator.remove();
            }
        }
    }
}
