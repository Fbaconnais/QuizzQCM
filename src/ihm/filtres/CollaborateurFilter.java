package ihm.filtres;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.ERROR }, urlPatterns = { "/collaborateur", "/collaborateur/", "/collaborateur/*" })
public class CollaborateurFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		if (((HttpServletRequest) req).getSession().getAttribute("profilCon") == null
				|| ((HttpServletRequest) req).getSession().getAttribute("profilCon").equals("erreur")) {
			((HttpServletRequest) req).getSession().setAttribute("musique", "alarme");
			((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath() +"/login");
		} else {
			String profil = (String) ((HttpServletRequest) req).getSession().getAttribute("profilCon");
			if (profil.equals("stagiaire") || profil.equals("candidat libre")) {
				((HttpServletRequest) req).getSession().setAttribute("musique", "alarme");
				((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath() +"/candidat");
			} else {
				chain.doFilter(((HttpServletRequest) req), (HttpServletResponse) resp);
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
