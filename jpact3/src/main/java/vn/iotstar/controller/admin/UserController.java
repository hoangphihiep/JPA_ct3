package vn.iotstar.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Role;
import vn.iotstar.entity.User;
import vn.iotstar.services.IRoleService;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.RoleServiceImpl;
import vn.iotstar.services.impl.UserServiceImpl;

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
		}
	}
}
