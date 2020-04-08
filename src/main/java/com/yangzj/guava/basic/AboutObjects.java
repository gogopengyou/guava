package com.yangzj.guava.basic;

import java.util.Objects;

/**
 * description:更优雅的判空、比较、排序
 *
 * @author yangzj
 * @date 2020/04/07
 */
public class AboutObjects {

    public static void main(String[] args) {

        System.out.println(Objects.equals("a", "a"));
        System.out.println(Objects.equals("a", null));
        System.out.println(Objects.equals(null, "a"));
        System.out.println(Objects.equals(null, null));

        int flag = Objects.compare("aa", "b", (a, b) -> {
            if (a.length() > b.length()) {
                return 1;
            } else if (a.length() < b.length()) {
                return -1;
            } else {
                return 0;
            }
        });
        System.out.println(flag);

        int h1 = Objects.hash("a", "b");
        int h2 = Objects.hash("b", "a");
        System.out.println(h1);
        System.out.println(h2);

        String[] arr1 = {"aaa", "bbb"};
        String[] arr2 = {"aaa", "bbb"};
        System.out.println(Objects.equals(arr1, arr2));
        // deepEquals可以比较数组
        System.out.println(Objects.deepEquals(arr1, arr2));

        System.out.println(Objects.isNull(null));
        System.out.println(Objects.nonNull(null));
        String temp = Objects.requireNonNull(null, "message");
        System.out.println(temp);
    }

}
