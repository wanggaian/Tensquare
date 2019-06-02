package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * FriendDao
 *
 * @Author wanggaian
 * @Date 2019/5/29 23:51
 */
public interface FriendDao extends JpaRepository<Friend, String> {

    public Friend findByUseridAndAndFriendid(String userid, String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike=? WHERE userid=? AND friendid=?", nativeQuery = true)
    public void updateIslike(String islike, String userid, String friendid);

    @Modifying
    @Query(value = "delete from tb_friend where userid=? and friendid=?", nativeQuery = true)
    public void deletefriend(String userid, String friendid);
}
