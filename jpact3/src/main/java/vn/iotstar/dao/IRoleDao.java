package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Role;

public interface IRoleDao {
	
	Role findById(int id);

	List<Role> findAll();

	public List<Role> findAll(int page, int pagesize);

	public int count();
}
