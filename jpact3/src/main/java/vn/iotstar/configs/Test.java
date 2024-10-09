package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Role;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		Role role = new Role();
		role.setRolename("USER");
		try
		{
			trans.begin();
			enma.persist(role);
			trans.commit();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			enma.close();
		}
	}
}
