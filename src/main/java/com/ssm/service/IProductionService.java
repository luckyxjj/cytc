package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Production;

public interface IProductionService {
	public Production getProductionById(int id);

	public int addProduction(Production production);

	public int updateProduction(Production production);

	public int deleteProduction(int id);

	public List<Production> getAllProduction();
}
