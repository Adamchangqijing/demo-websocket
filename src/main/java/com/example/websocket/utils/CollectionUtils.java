package com.example.websocket.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 集合工具类
 */
public class CollectionUtils {


    /**
     *
     * @param collection
     * @return false 为空  true 为非空
     */
    public static boolean isNotBlank(Collection collection){
        if (collection == null || collection.size() == 0){
            return false;
        }
        return true;
    }

    /**
     * LIST 简单去重
     * @param list
     */
    public static void toDuplicateList(List list){
        HashSet hashSet = new HashSet();
        hashSet.addAll(list);
        list.clear();
        list.addAll(hashSet);
    }

    /**
     * 将LIST 按指定的长度截取
     * @param tList
     * @param sub
     * @param <T>
     * @return
     */
    public static <T> List<T>[] subArrays(List<T> tList,int sub){
        if (sub <= 0){
            return null;
        }
        int size = tList.size();
        int nums= size/sub;
        nums = size%sub == 0?nums:nums+1;
        List<T>[] subs = new List[nums];
        for (int i = 0 ;i<nums;i++){
            int start = i*sub;
            int end = (i+1)*sub;
            if (end>size){
                end=size;
            }
            subs[i] = tList.subList(start, end);
        }
        return subs;
    }

    public static <T> void convertCollection(T[] tArr, Collection<T> collection) {
        int index = 0;
        for (T t : collection) {
            tArr[index] = t;
            index++;
        }
    }

    /**
     *
     * @param <T>
     * @return
     */
    public static <T> List<T> conerCollection(T... tArr){
        List<T> ts = new ArrayList<T>();
        for (T t : tArr) {
            ts.add(t);
        }
        return ts;
    }



    public static <T> List<T> arrToList(T ... ts){
        List<T> tList = new ArrayList<>(ts.length);
        for (T t : ts) {
            tList.add(t);
        }
        return tList;
    }



    public static <T> List<Object> converList(Collection<T> ts){
        List<Object> objects = new ArrayList<Object>();
        for (T t : ts) {
            objects.add(t);
        }
        return objects;
    }

    /**
     * 转换数组
     */
    public static <T extends Object> T[] convertCollection(Collection<T> collection){
        Object[] ts = new Object[collection.size()];
        int index=0;
        for (T t : collection) {
            ts[index] = t;
            index++;
        }
        return (T[]) ts;
    }

    /**
     * 转换数组
     */
    public static <T extends String> T[] convertCollectionString(Collection<T> collection){
        String[] ts = new String[collection.size()];
        int index=0;
        for (T t : collection) {
            ts[index] = t;
            index++;
        }
        return (T[]) ts;
    }


    public static String join(Collection<String> collection,String flag){
        if (!isNotBlank(collection))return "";
        String str = "";
        for (String s : collection) {
            str = str+","+s;
        }
        if (flag.length()>0){
            str = str.substring(flag.length(),str.length());
        }
        return str;
    }

}
