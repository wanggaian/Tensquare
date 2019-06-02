package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * FriendService
 *
 * @Author wanggaian
 * @Date 2019/5/29 23:37
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userid, String friendid) {
        // 看是否存在
        Friend friend = friendDao.findByUseridAndAndFriendid(userid, friendid);
        if (friend != null) {
            return 0;
        }

        //
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);

        //
        if (friendDao.findByUseridAndAndFriendid(friendid, userid) != null) {
            friendDao.updateIslike("1", userid, friendid);
            friendDao.updateIslike("1", friendid, userid);
        }
        return 1;
    }

    public int addNoFriend(String userid, String friendid) {
        NoFriend noFriend = noFriendDao.findByUseridAndAndFriendid(userid, friendid);
        if (noFriend != null) {
            return 0;
        }

        noFriend = new NoFriend();
        noFriend.setFriendid(friendid);
        noFriend.setUserid(userid);
        noFriendDao.save(noFriend);
        return 1;
    }

    @Transactional
    public void deletefriend(String userid, String friendid) {
        friendDao.deletefriend(userid, friendid);
        friendDao.updateIslike("0", friendid, userid);
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
