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
		DispatcherType.ERROR }, urlPatterns = { "/collaborateur/responsable", "/collaborateur/responsable/",
				"/collaborateur/responsable/*" })
public class ResponsableFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		if (!(((HttpServletRequest) req).getSession().getAttribute("profilCon").equals("responsable de formation") || ((HttpServletRequest) req).getSession().getAttribute("profilCon").equals("cellule de recrutement"))) {
			((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath() + "/login");
		} else {
			chain.doFilter(((HttpServletRequest) req), ((HttpServletResponse) resp));
		}
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
