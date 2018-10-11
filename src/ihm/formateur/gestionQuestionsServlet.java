package ihm.formateur;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.ThemeManager;
import bo.BeanGeneral;
import bo.Theme;

/**
 * Servlet implementation class gestionQuestionsServlet
 */
@WebServlet("/collaborateur/formateur/questions")
public class gestionQuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	ThemeManager TMger = ThemeManager.getMger();
    	List<Theme> listeThemes = null;
    	try {
			listeThemes = TMger.selectAll();
		} catch (BLLException e) {
			e.printStackTrace();
		}
    	request.setAttribute("themes", listeThemes);
    	request.getRequestDispatcher("/WEB-INF/jsp/collaborateur/formateur/gestionQuestions.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
