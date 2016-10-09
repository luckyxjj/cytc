package com.ssm.service;

import java.util.List;

import com.ssm.pojo.ProductionImg;

public interface IProductionImgService {
	public ProductionImg getProductionImgById(int id);

	public int addProductionImg(ProductionImg productionImg);

	public int updateProductionImg(ProductionImg productionImg);

	public int deleteProductionImg(int id);

	public List<ProductionImg> getByProductionId(int id);

	public int deleteByProductionId(int id);
}
