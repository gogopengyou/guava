package com.yangzj.guava.basic;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;
import lombok.Builder;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * description: 复杂比较器Ordering，流式规则
 * 优雅的Guava～
 * @author yangzj
 * @date 2020/04/07
 */
public class ComplexCompare {

    public static void main(String[] args) {

        Ordering<SortedDemo> order = Ordering.natural().nullsLast().onResultOf(t->t.sortedBy);

        SortedDemo s1 = SortedDemo.builder().sortedBy("aaa").other(111).build();
        SortedDemo s2 = SortedDemo.builder().sortedBy("bbb").other(222).build();
        SortedDemo s3 = SortedDemo.builder().sortedBy("ccc").other(333).build();
        SortedDemo s4 = SortedDemo.builder().sortedBy(null).other(444).build();
        List<SortedDemo> list = Lists.newArrayList(s3, s4, s1, s2);
        /*list.add(s3);
        list.add(s4);
        list.add(s1);
        list.add(s2);*/
        System.out.println(list);

        System.out.println(Ints.asList(1, 3, 4));
        System.out.println(Range.closed(1, 5));
        System.out.println(Range.closed(1, 5).upperEndpoint());

        System.out.println(order.greatestOf(list.iterator(), list.size()));
        System.out.println(order.leastOf(list.iterator(), list.size()));
        System.out.println(order.sortedCopy(list));

    }

}

@Data
@Builder
class SortedDemo {
    @Nullable String sortedBy;

    int other;
}
