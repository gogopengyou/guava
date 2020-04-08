package com.yangzj.guava;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * description: Funnel 漏斗
 *
 * @author yangzj
 * @date 2020/04/08
 */
public class GuavaHash {

    public static void main(String[] args) {
        // Person对象分解并做相应hash计算 【Hash漏斗】
        Funnel<Person> personFunnel = new Funnel<Person>() {
            @Override
            public void funnel(Person person, PrimitiveSink into) {
                into
                        .putInt(person.id)
                        .putString(person.firstName, Charsets.UTF_8)
                        .putString(person.lastName, Charsets.UTF_8)
                        .putInt(person.birthYear);
            }
        };

        Person p1 = Person.builder().id(1).firstName("zhang").lastName("san").birthYear(1990).build();
        Person p2 = Person.builder().id(2).firstName("li").lastName("si").birthYear(1991).build();
        Person p3 = Person.builder().id(3).firstName("wang").lastName("wu").birthYear(1992).build();
        List<Person> friendsList = Lists.newArrayList(p1, p2, p3);
        // fpp默认误检率0.03   expectedInsertions为预期插入数量，设置小了误报率增高
        // 关注预设位数numBits，受fpp和expectedInsertions共同影响，误检率设置越低相应位数就越多，为了减少冲突
        // 预期插入数据越多，理论上碰撞的概率也越大，所以需要相应预设的位数也会变多
        BloomFilter<Person> friends = BloomFilter.create(personFunnel, 500, 0.01);
        for (Person friend : friendsList) {
            friends.put(friend);
        }

        boolean flag = friends.mightContain(p3);
        if (flag) {
            System.out.println("It's right.99%");
        } else {
            System.out.println("It's wrong.1%");
        }
    }

}

@Data
@Builder
class Person {
    int id;
    String firstName;
    String lastName;
    int birthYear;
}