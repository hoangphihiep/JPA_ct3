package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.impl.CategoryDao;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

	public ICategoryDao cateDao = new CategoryDao();

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Category findById(int id) {
		return cateDao.findById(id);
	}

	@Override
	public void insert(Category category) {
		Category cate = this.findById(category.getCategoryId());
		if (cate == null) {
			// Chưa có danh mục với ID này, nên tiếp tục thêm mới
			cateDao.insert(category);
		} else if (cate.getCategoryname() == null) {
			// Nếu danh mục đã tồn tại nhưng chưa có tên, có thể xử lý logic theo nhu cầu
			cateDao.insert(category);
		}
	}

	@Override
	public void update(Category category) {
		Category cate = this.findById(category.getCategoryId());
		if (cate != null) {
			cateDao.update(category);
		}
	}

	@Override
	public void delete(int id) throws Exception {
		Category cate = new Category();
		cate = cateDao.findById(id);
		if (cate != null) {
			cateDao.delete(id);
		}

	}

//	@Override
//	public Category findByName(String name) {
//		return cateDao.findByName(name);
//	}

	@Override
	public List<Category> findByCategoryname(String keyword) {
		return cateDao.findByCategoryname(keyword);
	}

}
