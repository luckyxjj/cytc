package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.AdminMapper;
import com.ssm.pojo.Admin;
import com.ssm.service.IAdminService;

@Service("adminService")
public class AdminService implements IAdminService{
	@Resource
	public AdminMapper adminDao;

	public Admin getAdminById(int id) {
		return adminDao.selectByPrimaryKey(id);
	}

	public int addAdmin(Admin admin) {
		return adminDao.insertSelective(admin);
	}

	public int updateAdmin(Admin admin) {
		return adminDao.updateByPrimaryKeySelective(admin);
	}

	public int deleteAdmin(int id) {
		return adminDao.deleteByPrimaryKey(id);
	}

	public List<Admin> getAllAdmin() {
		return adminDao.selectAll();
	}

	public int loginValidate(Admin admin) {
		Object object =  adminDao.loginValidate(admin);
		if (object == null) {
			return 0;
		}
		return Integer.parseInt(object.toString());
	}

}
