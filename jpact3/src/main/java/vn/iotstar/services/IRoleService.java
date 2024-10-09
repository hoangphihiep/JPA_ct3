package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Role;

public interface IRoleService {
	
	Role findById(int id);

	List<Role> findAll();

	public List<Role> findAll(int page, int pagesize);

	public int count();
}
