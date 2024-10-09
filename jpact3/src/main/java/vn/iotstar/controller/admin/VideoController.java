package vn.iotstar.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.services.impl.VideoServiceImpl;
import vn.iotstar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos","/admin/video/add","/admin/video/insert","/admin/video/edit","/admin/video/update","/admin/video/delete"})
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IVideoService videoService = new VideoServiceImpl();
	public ICategoryService cateService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/admin/videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}else if (url.contains("/admin/video/add")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		}else if (url.contains("/admin/video/edit")) {
			String id = req.getParameter("id");
			Video video = videoService.findById(id);
			req.setAttribute("vide", video);
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		}else {
			String id = req.getParameter("id");
			// xóa file củ
			Video video = videoService.findById(id);
			String fileold = video.getPoster();
			String uploadPath = Constant.DIR;
			if (fileold != null && !fileold.isEmpty()&& !fileold.startsWith("https")) {
				Path oldFilePath = Paths.get(uploadPath, fileold);
				if (Files.exists(oldFilePath)) {
					try {
						Files.delete(oldFilePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			try {
				videoService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/admin/video/insert")) {
			String videoID = req.getParameter("videoid");
			String videotitle = req.getParameter("videotitle");
			String videodescription = req.getParameter("videodescription");
			String videoview = req.getParameter("videoview");
			String videocategory = req.getParameter("videocategory");
			boolean status = Boolean.parseBoolean(req.getParameter("status"));
			String poster = req.getParameter("poster");

			int categoryId = Integer.parseInt(videocategory);
			Category category = cateService.findById(categoryId);
			Video video = new Video();
			video.setDescription(videodescription);
			video.setVideoId(videoID);
			video.setTitle(videotitle);
			video.setViews(videoview);
			video.setActive(status);
			video.setCategory(category);

			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();

			try {
				Part part = req.getPart("poster1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);

					video.setPoster(fname);
				} else if (poster != null) {
					video.setPoster(poster);
				} else {
					video.setPoster("avatar.png");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			videoService.insert(video);

			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
		if (url.contains("/admin/video/update")) {
			String videoID = req.getParameter("videoid");
			String videotitle = req.getParameter("videotitle");
			String videodescription = req.getParameter("videodescription");
			String videoview = req.getParameter("videoview");
			String videocategory = req.getParameter("videocategory");
			boolean status = Boolean.parseBoolean(req.getParameter("status"));
			String poster = req.getParameter("poster");

			int categoryId = Integer.parseInt(videocategory);
			Category category = cateService.findById(categoryId);

			Video video = videoService.findById(videoID);
			String fileold = video.getPoster();
			video.setDescription(videodescription);
			video.setVideoId(videoID);
			video.setTitle(videotitle);
			video.setViews(videoview);
			video.setActive(status);
			video.setCategory(category);

			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			
			
			// Xóa file cũ trước khi ghi file mới
			if (fileold != null && !fileold.isEmpty() && !fileold.startsWith("https")) {
			    Path oldFilePath = Paths.get(uploadPath, fileold);
			    if (Files.exists(oldFilePath)) {
			        try {
			            Files.delete(oldFilePath);
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			    }
			}
			try {
				Part part = req.getPart("poster1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);

					video.setPoster(fname);
				} else if (poster != null) {
					video.setPoster(poster);
				} else {
					video.setPoster("avatar.png");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos"); // trả về controller
		}
	}
}
