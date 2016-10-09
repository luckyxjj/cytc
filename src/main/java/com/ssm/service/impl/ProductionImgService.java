package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.ProductionImgMapper;
import com.ssm.pojo.ProductionImg;
import com.ssm.service.IProductionImgService;

@Service("productionImgServcie")
public class ProductionImgService implements IProductionImgService {

	@Resource
	private ProductionImgMapper productionImgDao;

	@Override
	public ProductionImg getProductionImgById(int id) {
		return productionImgDao.selectByPrimaryKey(id);
	}

	@Override
	public int addProductionImg(ProductionImg productionImg) {
		return productionImgDao.insertSelective(productionImg);
	}

	@Override
	public int updateProductionImg(ProductionImg productionImg) {
		return productionImgDao.updateByPrimaryKeySelective(productionImg);
	}

	@Override
	public int deleteProductionImg(int id) {
		return productionImgDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<ProductionImg> getByProductionId(int id) {
		return productionImgDao.selectByProductionId(id);
	}

	@Override
	public int deleteByProductionId(int id) {
		return productionImgDao.deleteByProductionId(id);
	}

}
