package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * controller
 *
 * @Author wanggaian
 * @Date 2019/5/29 23:06
 */
@RestController
@RequestMapping(value = "/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            return new Result(false, StatusCode.LOGINERROR, "没有权限");
        }

        String userid = claims.getId();

        // 加好友
        if (type != null) {
            if (type.equals("1")) {
                // 好友
                int flag = friendService.addFriend(userid, friendid);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "请不要重复添加好友");
                }
                if (flag == 1) {
                    userClient.updatefollowcountandfanscount(userid, friendid, 1);
                    return new Result(true, StatusCode.OK, "添加成功");
                }
            } else if (type.equals("2")) {
                // 非好友
                int flag = friendService.addNoFriend(userid, friendid);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "请不要重复添加非好友");
                }
                if (flag == 1) {
                    return new Result(true, StatusCode.OK, "添加成功");
                }
            }
            return new Result(false, StatusCode.ERROR, "参数异常");
        } else {
            return new Result(false, StatusCode.ERROR, "参数异常");
        }
    }

    @RequestMapping(value = "/{friendid}", method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendid) {
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            return new Result(false, StatusCode.LOGINERROR, "没有权限");
        }

        String userid = claims.getId();
        friendService.deletefriend(userid, friendid);
        userClient.updatefollowcountandfanscount(userid, friendid, -1);
        return new Result(true, StatusCode.OK, "删除好友成功");
    }
}
