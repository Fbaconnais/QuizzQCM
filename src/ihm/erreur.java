package ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/erreur", "/collaborateur/erreur" })
public class erreur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/erreur/affichageMessageErreur.jsp").forward(request, response);
		;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp;
		String url = "";
		if (((String) request.getSession().getAttribute("profilCon")) != null) {
			switch ((String) request.getSession().getAttribute("profilCon")) {
			case "candidat libre":
			case "stagiaire":
				disp = request.getRequestDispatcher("/candidat");
				break;
			case "formateur":

			case "administrateur":

			case "cellule de recrutement":
			case "responsable de formation":
				disp = request.getRequestDispatcher("/collaborateur");
				break;
			default:
				disp = request.getRequestDispatcher("/login");
			}
		} else {
			disp = request.getRequestDispatcher("/login");
		}
		disp.forward(request, response);

	}

}
