package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.IRoleDao;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.RoleDao;
import vn.iotstar.dao.impl.UserDao;
import vn.iotstar.entity.Role;
import vn.iotstar.services.IRoleService;

public class RoleServiceImpl implements IRoleService {

	public IRoleDao roleDao = new RoleDao();
	@Override
	public Role findById(int id) {
		return roleDao.findById(id);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public List<Role> findAll(int page, int pagesize) {
		return roleDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return roleDao.count();
	}

}
