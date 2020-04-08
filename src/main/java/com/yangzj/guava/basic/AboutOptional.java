package com.yangzj.guava.basic;

import com.google.common.base.Optional;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * description: Optional帮助明确null的语义
 * 通过get获取值时，如果引用为null就会报错，这会变相地要求你处理好空值 （不然报错给你看:）
 * 要不然就是用or，当为引用为null时指定特定值 ～
 *
 * @author yangzj
 * @date 2020/04/07
 */
public class AboutOptional {

    public static void main(String[] args) throws IOException {
        // 读取单个字节
        /*int input1 = System.in.read();
        System.out.println(input1);*/

        // 字节流 - 字符流 - 缓冲流 （缓冲是为了减少解码器调用）【适配器模式，BufferedInputStream为装饰器模式】
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input2 = br.readLine();
        System.out.println(input2);
        if ("test".equals(input2)) {
            input2 = null;
        }

        // of和get都不允许引用为null否则报错
        /*Optional<String> a = Optional.of(input2);
        System.out.println(a.get());*/

        // fromNullable允许引用为null
        Optional<String> b = Optional.fromNullable(input2);
        // System.out.println(b.get());
        System.out.println(b.or("it's null."));
        if (b.isPresent()) {
            System.out.println("it's not null.");
        }

    }

}
