package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Video;

public interface IVideoService {
	void insert(Video video);

	void update(Video video);

	void delete(String videoid) throws Exception;

	Video findById(String videoid);

	List<Video> findAll();

	public List<Video> findAll(int page, int pagesize);

	public int count();
}
