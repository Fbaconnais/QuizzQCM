package ihm;

import java.io.IOException;
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
		String profil = (String) request.getSession().getAttribute("profilCon");
		
		switch (profil) {
		case "formateur":
			request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/accueilFormateur.jsp").forward(request, response);
			break;
		case "administrateur":
			request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/accueilAdmin.jsp").forward(request, response);
			break;
		case "cellule de recrutement":
		case "responsable de formation":
			request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/accueilResponsable.jsp").forward(request,
					response);
			break;
		default:
			request.getRequestDispatcher("/WEB-INF/jsp/erreur/autorisation.jsp").forward(request, response);

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
