package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * NoFriendDao
 *
 * @Author wanggaian
 * @Date 2019/5/29 23:51
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {

    public NoFriend findByUseridAndAndFriendid(String userid, String friendid);
}
