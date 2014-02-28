package com.jjoe64.shiroexample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.hibernate.Session;

import com.jjoe64.shiroexample.models.User;
import com.jjoe64.shiroexample.util.HibernateUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5733722323174731486L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// draw JSP
		try {
			request.getRequestDispatcher("/jsps/register.jsp").include(request,
					response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String pwd = request.getParameter("p");
		if (email == null || pwd == null) {
			request.setAttribute("message", "wrong parameters");
		} else {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			try {
				registrate(session, email, pwd);
				request.setAttribute("message", "user created. you can now <a href='login'>login</a>.");
			} finally {
				session.getTransaction().commit();
				if (session.isOpen()) session.close();
			}
		}

		// draw JSP
		try {
			request.getRequestDispatcher("/jsps/register.jsp").include(request,
					response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	private void generatePassword(User user, String plainTextPassword) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();

		// Now hash the plain-text password with the random salt and multiple
		// iterations and then Base64-encode the value (requires less space than
		// Hex):
		String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt,
				1024).toBase64();

		user.setPassword(hashedPasswordBase64);
		user.setSalt(salt.toString());
	}

	private void registrate(Session session, String email, String plainTextPassword) {
		User user = new User();
		user.setUsername(email);
		user.setEmail(email);

		generatePassword(user, plainTextPassword);
		session.save(user);

		System.err.println("User with email:" + user.getEmail()
				+ " hashedPassword:" + user.getPassword() + " salt:"
				+ user.getSalt());
	}

}
