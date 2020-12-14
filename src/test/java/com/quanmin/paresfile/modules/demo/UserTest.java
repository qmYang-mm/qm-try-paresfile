package com.quanmin.paresfile.modules.demo;

import com.quanmin.paresfile.TryParesfileApplication;
import com.quanmin.paresfile.modules.demo.entity.User;
import com.quanmin.paresfile.modules.demo.entity.UserInfo;
import com.quanmin.paresfile.modules.demo.entity.UserInfoCopy;
import com.quanmin.paresfile.modules.demo.service.IUserInfoCopyService;
import com.quanmin.paresfile.modules.demo.service.IUserInfoService;
import com.quanmin.paresfile.modules.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author Yangquanmin
 * @since 2020-10-27 13:49
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TryParesfileApplication.class)
public class UserTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IUserInfoCopyService userInfoCopyService;

    private Random random = new Random();


    @Test
    public void testGetUserStatus(){
        // 获得用户数据
        long startTime = System.currentTimeMillis();
        List<UserInfo> userInfos = userInfoService.getUserInfoStatusList();
        long endTime = System.currentTimeMillis();
        long m = (endTime - startTime);
        log.info("共计记录数：" + userInfos.size());
        log.info("共计时间：" + m + "耗秒");
    }

    @Test
    public void testGetUserCopyStatus(){
        // 获得用户数据 -- copy
        long startTime = System.currentTimeMillis();
        List<UserInfo> userInfos = userInfoCopyService.getUserInfoCopyStatusList();
        long endTime = System.currentTimeMillis();
        long m = (endTime - startTime);
        log.info("共计记录数：" + userInfos.size());
        log.info("共计时间：" + m + "耗秒");
    }



    @Test
    @Transactional
    public void testInsertUserMore() {
        long startTime = System.currentTimeMillis();
        // 1. 加入假数据
        for (int j = 0; j < 1; j++) {
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                User user = new User();
                String userName = getUserName();
                user.setUserName(userName);
                user.setUserRemark(getUserRemark(userName));
                users.add(user);
            }
            //保存一次 集合
            userService.saveBatch(users);
        }
        long endTime = System.currentTimeMillis();
        long m = (endTime - startTime) / 1000;
        log.info("共计时间：" + m + "秒");
    }

    @Test
    public void testCreatedUserInfo() {
        // 插入用户信息条数
        // 1百万中 除三除尽的
        long startTime = System.currentTimeMillis();
        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 1; i < 1000000; i++) {
            if (i % 3 == 0) {
                // 插入
                UserInfo userInfo = new UserInfo();
                userInfo.setInfoId(i);
                userInfo.setInfoDetail(getUserName());
                userInfo.setInfoStatus(getUserInfoStatus());
                userInfos.add(userInfo);
            }
        }
        userInfoService.saveBatch(userInfos);
        long endTime = System.currentTimeMillis();
        long m = (endTime - startTime) / 1000;
        log.info("共计时间：" + m + "秒");
    }

    @Test
    public void testCreatedUserInfoCopy() {
        // 插入用户信息条数
        // 1百万中 除三除尽的
        long startTime = System.currentTimeMillis();
        List<UserInfoCopy> userInfoCopies = new ArrayList<>();
        for (int i = 1; i < 1000000; i++) {
            if (i % 3 == 0) {
                // 插入
                UserInfoCopy userInfoCopy = new UserInfoCopy();
                userInfoCopy.setInfoCopyId(i);
                userInfoCopy.setInfoCopyDetail(getUserName());
                userInfoCopy.setInfoCopyStatus(getUserInfoStatus());
                userInfoCopies.add(userInfoCopy);
            }
        }
        userInfoCopyService.saveBatch(userInfoCopies);
        long endTime = System.currentTimeMillis();
        long m = (endTime - startTime) / 1000;
        log.info("共计时间：" + m + "秒");
    }


    @Test
    public void getUserCount() {
        int count = userService.count();
        System.out.println("用户人数：" + count);
    }

    @Test
    public void getUserInfoCount() {
        int count = userInfoService.count();
        System.out.println("用户信息数量：" + count);
    }

    @Test
    public void getUserInfoCopyCount() {
        int count = userInfoCopyService.count();
        System.out.println("用户信息拷贝数量：" + count);
    }

    // @Test
    public String getUserName() {
        String replace = UUID.randomUUID().toString().replace("-", "");
        // log.info(replace);
        return replace.substring(8, 32);
        // log.info(substring);
    }

    private String getUserRemark(String string) {
        return string + string + string;
    }

    public int getUserInfoStatus() {
        return random.nextInt(4) + 1;
    }
}
