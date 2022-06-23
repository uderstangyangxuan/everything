package com.maohulu.custom.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author huliu
 * @since 2022/4/14 10:43
 */
public class ByteBufferDemo {
    public static void main(String[] args) {
        demo1();
    }

    static void demo1() {
        String msg = "java技术爱好者，起飞！";
        //创建一个固定大小的buffer(返回的是HeapByteBuffer)
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = msg.getBytes();

        // 看一下初始时4个核心变量的值
        System.out.println("初始时-->limit--->"+byteBuffer.limit());
        System.out.println("初始时-->position--->"+byteBuffer.position());
        System.out.println("初始时-->capacity--->"+byteBuffer.capacity());
        System.out.println("初始时-->mark--->" + byteBuffer.mark());

        //写入数据到Buffer中
        byteBuffer.put(bytes);

        // 看一下初始时4个核心变量的值
        System.out.println("put完之后-->limit--->"+byteBuffer.limit());
        System.out.println("put完之后-->position--->"+byteBuffer.position());
        System.out.println("put完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("put完之后-->mark--->" + byteBuffer.mark());

        //切换成读模式，关键一步
        byteBuffer.flip();

        System.out.println("flip完之后-->limit--->"+byteBuffer.limit());
        System.out.println("flip完之后-->position--->"+byteBuffer.position());
        System.out.println("flip完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("flip完之后-->mark--->" + byteBuffer.mark());

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

        System.out.println("get完之后-->limit--->"+byteBuffer.limit());
        System.out.println("get完之后-->position--->"+byteBuffer.position());
        System.out.println("get完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("get完之后-->mark--->" + byteBuffer.mark());

        //打印结果
        System.out.println(new String(tempByte));//java技术爱好者，起飞！

        byteBuffer.clear();

        System.out.println("clear完之后-->limit--->"+byteBuffer.limit());
        System.out.println("clear完之后-->position--->"+byteBuffer.position());
        System.out.println("clear完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("clear完之后-->mark--->" + byteBuffer.mark());
        // clear之后就回到了初始的状态 limit 1027 position 0 capacity 1024 mark [pos=0 lim=1024 cap=1024]
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
