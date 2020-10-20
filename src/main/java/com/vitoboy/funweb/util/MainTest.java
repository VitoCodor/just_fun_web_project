package com.vitoboy.funweb.util;

import com.vitoboy.funweb.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Author: vito
 * @Date: 2020/10/13 14:19
 * @Version: 1.0
 */
public class MainTest {
    public static void main(String[] args) {
        testListStatistic();
        testListToMap();
    }


    public static void testListToMap() {
        List<UserInfo> userInfoList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserInfo users = new UserInfo();
            users.setUserId("user-" + i);
            users.setPassword(getRandomPwd(6));
            users.setAge(new Random().nextInt(30));
            users.setGender(new Random().nextInt(2));
            if (i < 5) {
                users.setEmail("google");
            } else {
                users.setEmail("163");
            }
            userInfoList.add(users);
        }

        System.out.println("user list is : " + userInfoList);

        Map<String, UserInfo> userInfoMap = userInfoList.stream().collect(Collectors.toMap(UserInfo::getUserId, userInfo -> userInfo));

        System.out.println("user map is : " + userInfoMap);

        Map<String, List<UserInfo>> userMapList = userInfoList.stream().collect(Collectors.groupingBy(UserInfo::getEmail));

        System.out.println("user map list is : " + userMapList);

    }

    public static String getRandomPwd(int len){
        String[] strings = new String[26];
        for (int i = 0; i < 26; i++) {
            strings[i] = (char)('a'+i) + "";
        }

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(26);
            stringBuilder.append(strings[index]);
        }

        return stringBuilder.toString();
    }


    public static void testListStatistic(){
        List<Integer> integerList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int it = random.nextInt(100);
            integerList.add(it);
        }

        System.out.println(integerList);

        Integer max = integerList.stream().mapToInt(Integer::intValue).max().getAsInt();

        System.out.println("max is : " + max);

        Integer min = integerList.stream().mapToInt(Integer::intValue).min().getAsInt();

        System.out.println("min is : " + min);

        Integer sum = integerList.stream().mapToInt(Integer::intValue).sum();

        System.out.println("total is : " + sum);
    }
}
