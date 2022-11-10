package com.lzhdy.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzhdy
 * @date 2022/11/7
 * @apiNote
 */
public class BeanCopyUtils {
    /**
     * 单个Bean的拷贝
     * @param source ：需要进行拷贝的Bean
     * @param clazz ：拷贝成另一个Bean的字节码
     * @return <v> : 拷贝结果
     */
    public static <V> V beanCopy(Object source, Class<V> clazz){
        V result = null;
        try {
            result = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 多个Bean的拷贝
     * @param source ：需要进行拷贝的Bean列表
     * @param clazz ：拷贝成另一个Bean的字节码
     * @return <v> : 拷贝结果
     */
    public static <V> List<V> beansCopyList(List<?> source, Class<V> clazz){
        List<V> result = new ArrayList<>();
        for (Object o : source) {
            V beanCopy = beanCopy(o, clazz);
            result.add(beanCopy);
        }
        return result;
    }
}
