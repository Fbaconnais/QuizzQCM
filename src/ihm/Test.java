package ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.BLLException;
import bll.QuestionTirageManager;

@WebServlet("/test")
public class Test extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionTirageManager qtMger = QuestionTirageManager.getMger();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			qtMger.genererTest(id);
			request.getRequestDispatcher("/WEB-INF/jsp/candidat/test.jsp").forward(request, response);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
