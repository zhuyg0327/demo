package com.demo.controller;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        QueryString queryString = new QueryString();
        int n = queryString.getMaxChildenStr("pwwkew");
        int a = queryString.test("abcabaertg");
        System.out.println(n);
        System.out.println(a);
    }

}

class QueryString {
    public int getMaxChildenStr(String str) {
        //判断传参是否为空
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        //最大子串长度
        int max = 0;
        int count = 0;
        //定义一个map
        Map<Character, Integer> map = new HashMap<>();

        int i = 0;
        for (int j = 0; j < str.length(); ) {
            char key = str.charAt(j);
            //按顺序遍历，查看map里面有没有这个字符
            if (!map.containsKey(key)) {
                map.put(key, j);
                count++;
                //每往map里面添加一个数据，j+1
                j++;
            } else {
                //如果出现重复的需要清空之前map里面的值，然后重新参与计算
                //获取当前的最大值
                max = max > count ? max : count;
                //清空map，count从头开始计算
                map.clear();
                count = 0;
            }
        }
        return max > count ? max : count;//" "或"a"
    }

    public int test(String str) {
//        if(StringUtils.isEmpty(str)){
//            return 0;
//        }
//        Map<Character,Integer> map=new HashMap<>();
//        int max=0;
//        int count=0;
//        int i=0;
//        for(int j=0;j<str.length();){
//            char t=str.charAt(j);
//            if(!map.containsKey(t)){
//                map.put(t,j);
//                count++;
//                j++;
//            }else{
//                max=max>count?max:count;
//                map.clear();
//                count=0;
//            }
//        }
//        return max>count?max:count;
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int maxNum = 0;
        int count = 0;
        int i = 0;
        for (int j = 0; j < str.length(); ) {
            char flag = str.charAt(j);
            if (!map.containsKey(flag)) {
                map.put(flag, j);
                count++;
                j++;
            } else {
                maxNum = maxNum > count ? maxNum : count;
                map.clear();
                count = 0;
            }

        }
        return maxNum > count ? maxNum : count;
    }

}
