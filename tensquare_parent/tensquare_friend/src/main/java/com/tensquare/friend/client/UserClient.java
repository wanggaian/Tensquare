package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UserClient
 *
 * @Author wanggaian
 * @Date 2019/5/30 23:42
 */
@Component
@FeignClient(name = "tensquare-user")
public interface UserClient {

    @RequestMapping(value = "/user/{userid}/{friendid}/{x}", method = RequestMethod.PUT)
    public void updatefollowcountandfanscount(@PathVariable String userid, @PathVariable String friendid, @PathVariable int x);
}
