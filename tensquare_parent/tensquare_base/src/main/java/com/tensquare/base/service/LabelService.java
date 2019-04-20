package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * LabelService
 *
 * @Author wanggaian
 * @Date 2019/4/20 11:04
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {

            /**
             *
             * @param root 将条件封装到哪一个根对象 where 类名 = label.getId()
             * @param query 查询语句 一般不用 比如 groupBy orderBy
             * @param cb 条件封装对象 返回null则不需要
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // 先new一个list存放
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"); // where labelname = "%小明%"
                    list.add(predicate);
                }
                if (!StringUtils.isEmpty(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState()); // where state = 1
                    list.add(predicate);
                }
                Predicate[] arr = new Predicate[list.size()];
                list.toArray(arr); // 将list对象转换成数组存放到arr
                return cb.and(arr); // where labelname = %小明% and state = 1
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        // 分页设置 默认从第零页开始 -1 从1开始
        Pageable pageable = PageRequest.of(page - 1, size);
        return labelDao.findAll(new Specification<Label>() {

            /**
             *
             * @param root 将条件封装到哪一个根对象 where 类名 = label.getId()
             * @param query 查询语句 一般不用 比如 groupBy orderBy
             * @param cb 条件封装对象 返回null则不需要
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // 先new一个list存放
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"); // where labelname = "%小明%"
                    list.add(predicate);
                }
                if (!StringUtils.isEmpty(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState()); // where state = 1
                    list.add(predicate);
                }
                Predicate[] arr = new Predicate[list.size()];
                list.toArray(arr); // 将list对象转换成数组存放到arr
                return cb.and(arr); // where labelname = %小明% and state = 1
            }
        }, pageable);
    }
}
