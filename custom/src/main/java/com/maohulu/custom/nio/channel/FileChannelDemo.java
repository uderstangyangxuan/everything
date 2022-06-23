package com.maohulu.custom.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
public class FileChannelDemo {
    public static void main(String[] args) {
        try {
            methodA();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   /*
    FileChannel，读写文件中的数据。
    SocketChannel，通过TCP读写网络中的数据。
    ServerSocketChannel，监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
    DatagramChannel，通过UDP读写网络中的数据。

    Channel本身并不存储数据，只是负责数据的运输。必须要和Buffer一起使用。
    */

    /**
     *
     */
    public static void methodA() throws Exception{
        //获取文件输入流
        File file = new File("copy.txt");
        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();
        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream("copy1-1.txt");
        //从文件输出流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();

        //创建一个byteBuffer，小文件所以就直接一次读取，不分多次循环了
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        //把输入流通道的数据读取到缓冲区
        inputStreamChannel.read(byteBuffer);
        //切换成读模式
        byteBuffer.flip();
        //把数据从缓冲区写入到输出流通道
        outputStreamChannel.write(byteBuffer);

        //关闭通道
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();
    }
}
