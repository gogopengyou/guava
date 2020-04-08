package com.yangzj.guava.collections;

import com.google.common.collect.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * description:
 * Multiset可以统计词频，可看作是无序的ArrayList，重复元素可同时留存
 * guava collections tool:可初始化 可分区 可反转 可交集 可并集 子集 笛卡尔积 Lists\Sets\Maps\...
 * 酷酷的Maps和MultiMaps
 *
 * @author yangzj
 * @date 2020/04/08
 */
public class AboutMulti {

    public static void main(String[] args) {

        Multiset<String> mset = HashMultiset.create(Sets.newHashSet("aaa", "bbb", "ccc"));
        mset.add("bbb");

        mset.forEach(System.out::println);
        System.out.println("###" + mset.count("bbb"));

        List<Integer> list1 = Lists.newArrayList(1,2,3,4,5,6);
        System.out.println(list1);
        System.out.println(Lists.partition(list1, 3));
        System.out.println(Lists.reverse(list1));

        // List转Map
        Order order1 = Order.builder().orderId("1111").money(new BigDecimal(100.0)).quantity(3).build();
        Order order2 = Order.builder().orderId("2222").money(new BigDecimal(500.0)).quantity(1).build();
        Order order3 = Order.builder().orderId("3333").money(new BigDecimal(600.0)).quantity(1).build();
        List<Order> orders = Lists.newArrayList(order1, order2, order3);
        ImmutableMap<String, Order> map = Maps.uniqueIndex(orders, o -> o.getOrderId());
        map.forEach((k, v) -> {
            System.out.println(k+"="+v);
        });

        // List转Map 2
        ImmutableMultimap<Integer, Order> map2 = Multimaps.index(orders, o->o.getQuantity());
        map2.forEach((k, v) -> {
            System.out.println(k+"="+v);
        });
    }

}

@Data
@Builder
class Order {
    private String orderId;

    private Integer quantity;

    private BigDecimal money;
}
