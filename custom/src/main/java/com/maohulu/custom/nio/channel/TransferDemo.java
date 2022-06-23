package com.maohulu.custom.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
public class TransferDemo {
    public static void main(String[] args) throws FileNotFoundException {
        // TransferTo 把源通道的数据传输到目的通道
        File file = new File("copy.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("copy-1.txt");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        FileOutputStream fileOutputStream2 = new FileOutputStream("copy-2.txt");
        FileChannel outputStreamChannel2 = fileOutputStream2.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        // 把输入流通道的数据读取到输出流的通道
        try {
            inputStreamChannel.transferTo(0, byteBuffer.limit(), outputStreamChannel);
            //把输入流通道的数据读取到输出流的通道
            outputStreamChannel2.transferFrom(inputStreamChannel,0,byteBuffer.limit());

            outputStreamChannel.close();
            inputStreamChannel.close();

            outputStreamChannel2.close();
            fileOutputStream2.close();

            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
