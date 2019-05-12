package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * SpitDao
 *
 * @Author wanggaian
 * @Date 2019/5/11 23:38
 */
public interface SpitDao extends MongoRepository<Spit, String> {

    /**
     * 通过parentID 分页查询
     */
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
