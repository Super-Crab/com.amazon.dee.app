package javax.servlet.http;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/* loaded from: classes3.dex */
public abstract class HttpServlet extends GenericServlet implements Serializable {
    private static final String HEADER_IFMODSINCE = "If-Modified-Since";
    private static final String HEADER_LASTMOD = "Last-Modified";
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_TRACE = "TRACE";
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);

    private static Method[] getAllDeclaredMethods(Class cls) {
        if (cls.equals(HttpServlet.class)) {
            return null;
        }
        Method[] allDeclaredMethods = getAllDeclaredMethods(cls.getSuperclass());
        Method[] declaredMethods = cls.getDeclaredMethods();
        if (allDeclaredMethods == null || allDeclaredMethods.length <= 0) {
            return declaredMethods;
        }
        Method[] methodArr = new Method[allDeclaredMethods.length + declaredMethods.length];
        System.arraycopy(allDeclaredMethods, 0, methodArr, 0, allDeclaredMethods.length);
        System.arraycopy(declaredMethods, 0, methodArr, allDeclaredMethods.length, declaredMethods.length);
        return methodArr;
    }

    private void maybeSetLastModified(HttpServletResponse httpServletResponse, long j) {
        if (!httpServletResponse.containsHeader("Last-Modified") && j >= 0) {
            httpServletResponse.setDateHeader("Last-Modified", j);
        }
    }

    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_delete_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doHead(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        NoBodyResponse noBodyResponse = new NoBodyResponse(httpServletResponse);
        doGet(httpServletRequest, noBodyResponse);
        noBodyResponse.setContentLength();
    }

    protected void doOptions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Method[] allDeclaredMethods;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        for (Method method : getAllDeclaredMethods(getClass())) {
            if (method.getName().equals("doGet")) {
                z = true;
                z2 = true;
            }
            if (method.getName().equals("doPost")) {
                z3 = true;
            }
            if (method.getName().equals("doPut")) {
                z4 = true;
            }
            if (method.getName().equals("doDelete")) {
                z5 = true;
            }
        }
        String str = null;
        if (z) {
            str = "GET";
        }
        if (z2) {
            str = str == null ? METHOD_HEAD : GeneratedOutlineSupport1.outline72(str, ", HEAD");
        }
        if (z3) {
            str = str == null ? "POST" : GeneratedOutlineSupport1.outline72(str, ", POST");
        }
        if (z4) {
            str = str == null ? "PUT" : GeneratedOutlineSupport1.outline72(str, ", PUT");
        }
        if (z5) {
            str = str == null ? "DELETE" : GeneratedOutlineSupport1.outline72(str, ", DELETE");
        }
        String outline72 = str == null ? METHOD_TRACE : GeneratedOutlineSupport1.outline72(str, ", TRACE");
        httpServletResponse.setHeader("Allow", outline72 == null ? METHOD_OPTIONS : GeneratedOutlineSupport1.outline72(outline72, ", OPTIONS"));
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_post_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_put_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doTrace(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String str;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TRACE ");
        outline107.append(httpServletRequest.getRequestURI());
        outline107.append(" ");
        outline107.append(httpServletRequest.getProtocol());
        String sb = outline107.toString();
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            sb = sb + "\r\n" + ((String) headerNames.nextElement()) + RealTimeTextConstants.COLON_SPACE + httpServletRequest.getHeader(str);
        }
        String outline72 = GeneratedOutlineSupport1.outline72(sb, "\r\n");
        int length = outline72.length();
        httpServletResponse.setContentType("message/http");
        httpServletResponse.setContentLength(length);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.print(outline72);
        outputStream.close();
    }

    protected long getLastModified(HttpServletRequest httpServletRequest) {
        return -1L;
    }

    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {
            long lastModified = getLastModified(httpServletRequest);
            long j = -1;
            if (lastModified == -1) {
                doGet(httpServletRequest, httpServletResponse);
                return;
            }
            try {
                j = httpServletRequest.getDateHeader("If-Modified-Since");
            } catch (IllegalArgumentException unused) {
            }
            if (j < (lastModified / 1000) * 1000) {
                maybeSetLastModified(httpServletResponse, lastModified);
                doGet(httpServletRequest, httpServletResponse);
                return;
            }
            httpServletResponse.setStatus(304);
        } else if (method.equals(METHOD_HEAD)) {
            maybeSetLastModified(httpServletResponse, getLastModified(httpServletRequest));
            doHead(httpServletRequest, httpServletResponse);
        } else if (method.equals("POST")) {
            doPost(httpServletRequest, httpServletResponse);
        } else if (method.equals("PUT")) {
            doPut(httpServletRequest, httpServletResponse);
        } else if (method.equals("DELETE")) {
            doDelete(httpServletRequest, httpServletResponse);
        } else if (method.equals(METHOD_OPTIONS)) {
            doOptions(httpServletRequest, httpServletResponse);
        } else if (method.equals(METHOD_TRACE)) {
            doTrace(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendError(501, MessageFormat.format(lStrings.getString("http.method_not_implemented"), method));
        }
    }

    @Override // javax.servlet.GenericServlet, javax.servlet.Servlet
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            service((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        } catch (ClassCastException unused) {
            throw new ServletException("non-HTTP request or response");
        }
    }
}
