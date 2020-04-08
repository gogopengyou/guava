package com.yangzj.guava.basic;

import java.util.Optional;

/**
 * description:
 *
 * @author yangzj
 * @date 2020/04/07
 */
public class JavaOptional {

    public static void main(String[] args) {

        // Optional<String> test = Optional.of(null);
        Optional<String> test = Optional.ofNullable(null);
        System.out.println(test.orElse("It's null"));

        if (test.isPresent()) {
            System.out.println("It's not null.");
        } else {
            System.out.println("null null null");
        }

    }

}
