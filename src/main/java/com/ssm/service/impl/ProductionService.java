package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.ProductionMapper;
import com.ssm.pojo.Production;
import com.ssm.service.IProductionService;

@Service("productionService")
public class ProductionService implements IProductionService {

	@Resource
	private ProductionMapper productionDao;

	@Override
	public Production getProductionById(int id) {
		return productionDao.selectByPrimaryKey(id);
	}

	@Override
	public int addProduction(Production production) {
		return productionDao.insertSelective(production);
	}

	@Override
	public int updateProduction(Production production) {
		return productionDao.updateByPrimaryKeySelective(production);
	}

	@Override
	public int deleteProduction(int id) {
		return productionDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Production> getAllProduction() {
		return productionDao.selectAll();
	}

}
