package com.tensquare.recruit.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class RecruitService {

	@Autowired
	private RecruitDao recruitDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 推荐职位
	 * @return
	 */
	public List<Recruit> recommend() {
		return recruitDao.findTop6ByStateOrderByCreatetimeDesc("2");
	}

	/**
	 * 最新职位
	 */
	public List<Recruit> newlist() {
		return recruitDao.findTop6ByStateNotOrderByCreatetimeDesc("0");
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Recruit> findAll() {
		return recruitDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Recruit> findSearch(Map whereMap, int page, int size) {
		Specification<Recruit> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return recruitDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Recruit> findSearch(Map whereMap) {
		Specification<Recruit> specification = createSpecification(whereMap);
		return recruitDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Recruit findById(String id) {
		return recruitDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param recruit
	 */
	public void add(Recruit recruit) {
		recruit.setId( idWorker.nextId()+"" );
		recruitDao.save(recruit);
	}

	/**
	 * 修改
	 * @param recruit
	 */
	public void update(Recruit recruit) {
		recruitDao.save(recruit);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		recruitDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Recruit> createSpecification(Map searchMap) {

		return new Specification<Recruit>() {

			@Override
			public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
