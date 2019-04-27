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

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class EnterpriseService {

	@Autowired
	private EnterpriseDao enterpriseDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查找热门企业
	 * @param ishot
	 * @return
	 */
	public List<Enterprise> hotlist(String ishot) {
		return enterpriseDao.findByIshot(ishot);
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Enterprise> findAll() {
		return enterpriseDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Enterprise> findSearch(Map whereMap, int page, int size) {
		Specification<Enterprise> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return enterpriseDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Enterprise> findSearch(Map whereMap) {
		Specification<Enterprise> specification = createSpecification(whereMap);
		return enterpriseDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Enterprise findById(String id) {
		return enterpriseDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param enterprise
	 */
	public void add(Enterprise enterprise) {
		enterprise.setId( idWorker.nextId()+"" );
		enterpriseDao.save(enterprise);
	}

	/**
	 * 修改
	 * @param enterprise
	 */
	public void update(Enterprise enterprise) {
		enterpriseDao.save(enterprise);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		enterpriseDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Enterprise> createSpecification(Map searchMap) {

		return new Specification<Enterprise>() {

			@Override
			public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
