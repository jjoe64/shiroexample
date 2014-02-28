package com.jjoe64.shiroexample;

import java.io.IOException;
import java.io.PrintWriter;

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
		PrintWriter writer = response.getWriter();

		writer.println("<html>");
		writer.println("<head><title>Hello World Servlet</title></head>");
		writer.println("<body>");
		writer.println("	<h1>Hello World from a Sevlet! This page is only visible to registrated users. You are "+getCurrentUserEmail()+"</h1>");
		writer.println("<body>");
		writer.println("</html>");

		writer.close();
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
