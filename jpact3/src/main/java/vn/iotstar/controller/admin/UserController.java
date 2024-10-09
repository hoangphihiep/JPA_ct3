package vn.iotstar.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IRoleService;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.RoleServiceImpl;
import vn.iotstar.services.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/users", "/admin/user/add", "/admin/user/insert",
		"/admin/user/edit", "/admin/user/update", "/admin/user/delete", "/admin/user/search" })
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IUserService userService = new UserServiceImpl();
	public IRoleService roleService = new RoleServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/admin/users")) {
			List<User> list = userService.findAll();
			req.setAttribute("listuser", list);
			req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);
		}else if (url.contains("/admin/user/add")) {
			List<Role> list = roleService.findAll();
			req.setAttribute("listrole", list);
			req.getRequestDispatcher("/views/admin/user-add.jsp").forward(req, resp);
		}else if (url.contains("/admin/user/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			User user = userService.findById(id);
			req.setAttribute("use", user);
			List<Role> list = roleService.findAll();
			req.setAttribute("listrole", list);
			req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);
		}else {
			int id = Integer.parseInt(req.getParameter("id"));
			// xóa file củ
			User user = userService.findById(id);
			String fileold = user.getImages();
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
				userService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("/admin/user/insert")) {
			String username = req.getParameter("username");
			String fullname = req.getParameter("fullname");
			String createdDateStr = req.getParameter("creatdate");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String role = req.getParameter("rolename");
			String image = req.getParameter("image");

			Date createdDate = null;
			try {
				createdDate = Date.valueOf(createdDateStr);
			} catch (IllegalArgumentException e) {
				return;
			}
			int roleId = Integer.parseInt(role);
			Role rol = roleService.findById(roleId);
			User user = new User();
			user.setUsername(username);
			user.setFullname(fullname);
			user.setCreatDate(createdDate);
			user.setPhone(phone);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(rol);

			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();

			try {
				Part part = req.getPart("image1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);
					
					user.setImages(fname);
				} else if (image != null) {
					user.setImages(image);
				} else {
					user.setImages("avatar.png");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			userService.insert(user);

			resp.sendRedirect(req.getContextPath() + "/admin/users");
		}
		if (url.contains("/admin/user/update")) {
			String username = req.getParameter("username");
			String fullname = req.getParameter("fullname");
			String createdDateStr = req.getParameter("creatdate");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String role = req.getParameter("rolename");
			String image = req.getParameter("image");

			int id = Integer.parseInt(req.getParameter("userid"));
			Date createdDate = null;
			try {
				createdDate = Date.valueOf(createdDateStr);
			} catch (IllegalArgumentException e) {
				return;
			}
			int roleId = Integer.parseInt(role);
			Role rol = roleService.findById(roleId);
			User user = userService.findById(id);
			String fileold = user.getImages();
			user.setUsername(username);
			user.setFullname(fullname);
			user.setCreatDate(createdDate);
			user.setPhone(phone);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(rol);

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
				Part part = req.getPart("image1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);

					user.setImages(fname);
				} else if (image != null) {
					user.setImages(image);
				} else {
					user.setImages("avatar.png");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			userService.update(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users"); // trả về controller
		}
	}
}
