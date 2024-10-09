package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDao;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;

public class UserServiceImpl implements IUserService {

	public IUserDao userDao = new UserDao();
	@Override
	public void insert(User user) {
		User use = this.findById(user.getId());
		if (use == null) {
			// Chưa có danh mục với ID này, nên tiếp tục thêm mới
			userDao.insert(user);
		} 
	}

	@Override
	public void update(User user) {
		User use = this.findById(user.getId());
		if (use != null) {
			userDao.update(user);
		}
	}

	@Override
	public void delete(int id) throws Exception {
		User use = new User();
		use = userDao.findById(id);
		if (use != null) {
			userDao.delete(id);
		}	
	}

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<User> findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return userDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return userDao.count();
	}

}
