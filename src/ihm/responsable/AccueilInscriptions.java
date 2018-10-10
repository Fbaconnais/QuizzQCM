package ihm.responsable;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.Promotion;
import dal.DAOFactory;
import dal.DAOPromotion;
import dal.DAOUtilisateur;

@WebServlet("/collaborateur/responsable/accinscription")
public class AccueilInscriptions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("messageValidation", null);
		request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/responsable/accueilinscription.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
