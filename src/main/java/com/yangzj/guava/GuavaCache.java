package com.yangzj.guava;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * description: Guava Cache几个重要操作或特性：
 * put get 回调get
 * 回收策略 监听 统计(命中率等) asMap视图
 * weak key&value 无强引用时可被gc回收
 *
 * @author yangzj
 * @date 2020/04/08
 */
public class GuavaCache {

    public static void main(String[] args) throws ExecutionException {

        RemovalListener<String, String> listener = new RemovalListener<String, String>() {
            @Override
            public void onRemoval(RemovalNotification<String, String> notification) {
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        };

        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .removalListener(listener)
                .recordStats()
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        System.out.println("第一个值：" + cache.getIfPresent("key1"));
        System.out.println("第二个值：" + cache.getIfPresent("key2"));
        System.out.println("第三个值：" + cache.getIfPresent("key3"));

        // 回调get (有则取，无则填充)
        cache.get("key4", () -> "value4");
        System.out.println(cache.getIfPresent("key4"));

        System.out.println(cache.stats());

        // 自动加载cache，缓存中无key按规则自行加载～类似于统一执行回调get
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return key + "'s value";
                    }
                });
        System.out.println(loadingCache.get("key-A"));
        System.out.println(loadingCache.get("key-B"));
        System.out.println(loadingCache.asMap());
    }

}
