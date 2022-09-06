package javax.servlet;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
/* loaded from: classes3.dex */
public abstract class GenericServlet implements Servlet, ServletConfig, Serializable {
    private transient ServletConfig config;

    @Override // javax.servlet.Servlet
    public void destroy() {
    }

    @Override // javax.servlet.ServletConfig
    public String getInitParameter(String str) {
        return getServletConfig().getInitParameter(str);
    }

    @Override // javax.servlet.ServletConfig
    public Enumeration getInitParameterNames() {
        return getServletConfig().getInitParameterNames();
    }

    @Override // javax.servlet.Servlet
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override // javax.servlet.ServletConfig
    public ServletContext getServletContext() {
        return getServletConfig().getServletContext();
    }

    @Override // javax.servlet.Servlet
    public String getServletInfo() {
        return "";
    }

    @Override // javax.servlet.ServletConfig
    public String getServletName() {
        return this.config.getServletName();
    }

    public void init() throws ServletException {
    }

    @Override // javax.servlet.Servlet
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        init();
    }

    public void log(String str) {
        ServletContext servletContext = getServletContext();
        servletContext.log(getServletName() + RealTimeTextConstants.COLON_SPACE + str);
    }

    @Override // javax.servlet.Servlet
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;

    public void log(String str, Throwable th) {
        ServletContext servletContext = getServletContext();
        servletContext.log(getServletName() + RealTimeTextConstants.COLON_SPACE + str, th);
    }
}
