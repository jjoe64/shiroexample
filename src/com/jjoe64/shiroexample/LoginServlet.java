package com.jjoe64.shiroexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.jjoe64.shiroexample.daos.UserDAO;
import com.jjoe64.shiroexample.models.User;
import com.jjoe64.shiroexample.util.HibernateUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		Session session = HibernateUtil.getSessionFactory().openSession();;
		try {
			session.beginTransaction();

			// test
			UserDAO userDAO = new UserDAO(session);
			User user = userDAO.getUserByEmail("test@test.test");

			request.setAttribute("user", user);

			// draw JSP
			try {
				request.getRequestDispatcher("/jsps/login.jsp").include(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			}
		} finally {
			session.getTransaction().commit();
			if (session.isOpen()) session.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
