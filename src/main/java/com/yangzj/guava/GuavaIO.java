package com.yangzj.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * description:
 *
 * @author yangzj
 * @date 2020/04/08
 */
public class GuavaIO {

    public static void main(String[] args) throws IOException {

        File file = new File("demo1.txt");
        File file2 = new File("demo2.txt");
        Files.write("aa bb cc aa ", file, Charsets.UTF_8);

        //Read the lines of a UTF-8 text file
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        lines.forEach(System.out::println);

        //Count distinct word occurrences in a file
        Multiset<String> wordOccurrences = HashMultiset.create(
                Splitter.on(CharMatcher.whitespace())
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));
        System.out.println(wordOccurrences);

        //sha256 a file
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha256());

        //Copy the data from a URL to a file
        URL url = new URL("https://www.baidu.com");
        Resources.asByteSource(url).copyTo(Files.asByteSink(file2));
    }
}
