package com.jjoe64.shiroexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		request.setAttribute("currentEmail", getCurrentUserEmail());

		org.apache.shiro.subject.Subject currentUser = SecurityUtils
				.getSubject();
		request.setAttribute("adminAccess", currentUser.isPermitted("admin:access"));

		// draw JSP
		try {
			request.getRequestDispatcher("/includes/hello.jsp").include(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	public String getCurrentUserEmail() {
		org.apache.shiro.subject.Subject currentUser = SecurityUtils
				.getSubject();

		if (currentUser.isAuthenticated()) {
			String mail = (String) currentUser.getSession().getAttribute(
					"username");
			return mail;
		} else {
			return null;
		}
	}

}
