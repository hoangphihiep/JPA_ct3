package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.impl.VideoDao;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoService;

public class VideoServiceImpl implements IVideoService {

	public IVideoDao viDao = new VideoDao();

	@Override
	public void insert(Video video) {
		Video vide = this.findById(video.getVideoId());
		if (vide == null) {
			// Chưa có danh mục với ID này, nên tiếp tục thêm mới
			viDao.insert(video);
		}
	}

	@Override
	public void update(Video video) {
		Video cate = this.findById(video.getVideoId());
		if (cate != null) {
			viDao.update(video);
		}
	}

	@Override
	public void delete(String videoid) throws Exception {
		Video cate = new Video();
		cate = viDao.findById(videoid);
		if (cate != null) {
			viDao.delete(videoid);
		}	
	}

	@Override
	public Video findById(String videoid) {
		return viDao.findById(videoid);
	}

	@Override
	public List<Video> findAll() {
		return viDao.findAll();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return viDao.findAll(page, pagesize);
	}

	@Override
	public int count() {
		return viDao.count();
	}

	
}
