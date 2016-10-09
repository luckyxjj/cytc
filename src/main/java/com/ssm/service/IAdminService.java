package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Admin;

public interface IAdminService {
	public Admin getAdminById(int id);
	public int addAdmin(Admin admin);
	public int updateAdmin(Admin admin);
    public int deleteAdmin(int id);	
    public List<Admin> getAllAdmin();
    public int loginValidate(Admin admin);
}
