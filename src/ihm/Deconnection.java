package ihm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Utilisateur;

@WebServlet("/deconnection")
public class Deconnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		HashMap<String, Boolean> connections = (HashMap<String, Boolean>) (this.getServletContext()
				.getAttribute("connections"));
		if (request.getSession().getAttribute("user") != null) {
			String email = ((Utilisateur) request.getSession().getAttribute("user")).getEmail();
			connections.replace(email, false);
			ServletContext application = this.getServletContext();
			application.setAttribute("connections", connections);
		}
		request.getSession().removeAttribute("profilCon");
		request.getSession().removeAttribute("user");

		response.sendRedirect("login");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
