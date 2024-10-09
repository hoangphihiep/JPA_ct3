package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IRoleDao;
import vn.iotstar.entity.Role;

public class RoleDao implements IRoleDao {

	@Override
	public Role findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Role role = enma.find(Role.class, id);
		return role; 
	}

	@Override
	public List<Role> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Role> query = enma.createNamedQuery("Role.findAll", Role.class);
		return query.getResultList();
	}

	@Override
	public List<Role> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Role> query = enma.createNamedQuery("Role.findAll", Role.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(r) FROM Role r";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

}
