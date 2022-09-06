package javax.servlet;

import java.util.Enumeration;
/* loaded from: classes3.dex */
public interface FilterConfig {
    String getFilterName();

    String getInitParameter(String str);

    Enumeration getInitParameterNames();

    ServletContext getServletContext();
}
