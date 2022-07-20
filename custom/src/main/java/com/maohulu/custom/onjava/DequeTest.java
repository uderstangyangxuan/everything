package com.maohulu.custom.onjava;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayDeque;

/**
 * @author huliu
 * @description use 'ArrayDeque' replace Stack in Java
 * @since 2022/4/14 10:43
 */
public class DequeTest {
    public static void main(String[] args) {
        stackTest();
    }

    private static void stackTest() {
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String s : "My first array deque".split(" ")) {
            stack.push(s);
        }

        while (CollUtil.isNotEmpty(stack)) {
            System.out.println(stack.pop() + " ");
        }
    }
}
