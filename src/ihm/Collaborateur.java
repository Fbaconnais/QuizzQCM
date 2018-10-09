package ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/collaborateur")
public class Collaborateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp;
		request.getSession().setAttribute("messageValidation", null);
		if (request.getSession().getAttribute("user") == null) {
			disp = request.getRequestDispatcher("/login");
		} else {
			String profil = (String) request.getSession().getAttribute("profilCon");

			if (profil != null && !profil.equals("stagiaire") && !profil.equals("candidat libre")) {
				switch (profil) {
				case "formateur":
					disp = request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/accueilFormateur.jsp");
					break;
				case "administrateur":
					disp = request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/accueilAdmin.jsp");
					break;
				case "cellule de recrutement":
				case "responsable de formation":
					disp = request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/accueilResponsable.jsp");
					break;
				default:
					disp = request.getRequestDispatcher("/login");
				}
			} else {
				disp = request.getRequestDispatcher("/autorisation");

			}
		}
		disp.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
