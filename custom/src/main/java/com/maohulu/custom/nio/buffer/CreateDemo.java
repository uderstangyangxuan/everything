package com.maohulu.custom.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
public class CreateDemo {
    public static void main(String[] args) {
        demo1();
    }

    static void demo1() {
        String msg = "java技术爱好者，起飞！";
        //创建一个固定大小的buffer(返回的是HeapByteBuffer)
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = msg.getBytes();
        //写入数据到Buffer中
        byteBuffer.put(bytes);
        //切换成读模式，关键一步
        byteBuffer.flip();
        //创建一个临时数组，用于存储获取到的数据
        byte[] tempByte = new byte[bytes.length];
        int i = 0;
        //如果还有数据，就循环。循环判断条件
        while (byteBuffer.hasRemaining()) {
            //获取byteBuffer中的数据
            byte b = byteBuffer.get();
            //放到临时数组中
            tempByte[i] = b;
            i++;
        }
        //打印结果
        System.out.println(new String(tempByte));//java技术爱好者，起飞！
    }

    static void createHeapByteBuffer() {
        // 创建堆内内存块HeapByteBuffer
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);

        String msg = "java技术爱好者";
        // 包装一个byte[]数组获得一个Buffer，实际类型是HeapByteBuffer
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(msg.getBytes());

    }

    static void createDirectByteBuffer() {
        //创建堆外内存块DirectByteBuffer
        ByteBuffer byteBuffer3 = ByteBuffer.allocateDirect(1024);
    }
}
