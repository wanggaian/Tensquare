package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * SpitService
 *
 * @Author wanggaian
 * @Date 2019/5/11 23:45
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date());
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setShare(0);
        spit.setVisits(0);
        spit.setState("1");
        if (!StringUtils.isEmpty(spit.getParentid())) {
            // 有父id的
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentid(String parentid, int page, int size) {
        Pageable pageable = PageRequest.of(page -1, size);
        return spitDao.findByParentid(parentid, pageable);
    }

    public void thumbup(String spitid) {
        // 方法- 效率低 与DB两次交互
//        Spit spit = spitDao.findById(spitid).get();
//        spit.setThumbup((spit.getThumbup() == null ? 0 : spit.getThumbup()) + 1);
//        spitDao.save(spit);

        // 方法二 原生mongo sql db.spit.update({"_id","1"},{$inc:{"thumbup":NumberInt(1)}})

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitid));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
