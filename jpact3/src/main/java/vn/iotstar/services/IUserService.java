package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.User;

public interface IUserService {
	void insert(User user);

	void update(User user);

	void delete(int id) throws Exception;

	User findById(int id);

	List<User> findAll();

	List<User> findByUsername(String username);

	public List<User> findAll(int page, int pagesize);

	public int count();
}
