package ihm.candidat;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.EpreuveManager;
import bo.Utilisateur;


@WebServlet("/epreuve")
public class EpreuveServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private List<bo.Epreuve> listeEpreuves;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EpreuveManager EpreuveMger = EpreuveManager.getMger();
		try {
			int id = ((Utilisateur)request.getSession().getAttribute("user")).getIdUtilisateur(); 
			listeEpreuves = EpreuveMger.selectAllByIDEtDate(id);
			request.getSession().setAttribute("listeEpreuves", listeEpreuves);
			request.getRequestDispatcher("/WEB-INF/jsp/candidat/choixEpreuve.jsp").forward(request, response);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
