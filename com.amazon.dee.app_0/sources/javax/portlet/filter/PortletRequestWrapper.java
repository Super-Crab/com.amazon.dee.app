package javax.portlet.filter;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.portlet.PortalContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.WindowState;
import javax.servlet.http.Cookie;
/* loaded from: classes3.dex */
public class PortletRequestWrapper implements PortletRequest {
    PortletRequest request;

    private PortletRequestWrapper() {
    }

    @Override // javax.portlet.PortletRequest
    public Object getAttribute(String str) {
        return this.request.getAttribute(str);
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<String> getAttributeNames() {
        return this.request.getAttributeNames();
    }

    @Override // javax.portlet.PortletRequest
    public String getAuthType() {
        return this.request.getAuthType();
    }

    @Override // javax.portlet.PortletRequest
    public String getContextPath() {
        return this.request.getContextPath();
    }

    @Override // javax.portlet.PortletRequest
    public Cookie[] getCookies() {
        return this.request.getCookies();
    }

    @Override // javax.portlet.PortletRequest
    public Locale getLocale() {
        return this.request.getLocale();
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<Locale> getLocales() {
        return this.request.getLocales();
    }

    @Override // javax.portlet.PortletRequest
    public String getParameter(String str) {
        return this.request.getParameter(str);
    }

    @Override // javax.portlet.PortletRequest
    public Map<String, String[]> getParameterMap() {
        return this.request.getParameterMap();
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<String> getParameterNames() {
        return this.request.getParameterNames();
    }

    @Override // javax.portlet.PortletRequest
    public String[] getParameterValues(String str) {
        return this.request.getParameterValues(str);
    }

    @Override // javax.portlet.PortletRequest
    public PortalContext getPortalContext() {
        return this.request.getPortalContext();
    }

    @Override // javax.portlet.PortletRequest
    public PortletMode getPortletMode() {
        return this.request.getPortletMode();
    }

    @Override // javax.portlet.PortletRequest
    public PortletSession getPortletSession() {
        return this.request.getPortletSession();
    }

    @Override // javax.portlet.PortletRequest
    public PortletPreferences getPreferences() {
        return this.request.getPreferences();
    }

    @Override // javax.portlet.PortletRequest
    public Map<String, String[]> getPrivateParameterMap() {
        return this.request.getPrivateParameterMap();
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<String> getProperties(String str) {
        return this.request.getProperties(str);
    }

    @Override // javax.portlet.PortletRequest
    public String getProperty(String str) {
        return this.request.getProperty(str);
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<String> getPropertyNames() {
        return this.request.getPropertyNames();
    }

    @Override // javax.portlet.PortletRequest
    public Map<String, String[]> getPublicParameterMap() {
        return this.request.getPublicParameterMap();
    }

    @Override // javax.portlet.PortletRequest
    public String getRemoteUser() {
        return this.request.getRemoteUser();
    }

    /* renamed from: getRequest */
    public PortletRequest mo10373getRequest() {
        return this.request;
    }

    @Override // javax.portlet.PortletRequest
    public String getRequestedSessionId() {
        return this.request.getRequestedSessionId();
    }

    @Override // javax.portlet.PortletRequest
    public String getResponseContentType() {
        return this.request.getResponseContentType();
    }

    @Override // javax.portlet.PortletRequest
    public Enumeration<String> getResponseContentTypes() {
        return this.request.getResponseContentTypes();
    }

    @Override // javax.portlet.PortletRequest
    public String getScheme() {
        return this.request.getScheme();
    }

    @Override // javax.portlet.PortletRequest
    public String getServerName() {
        return this.request.getServerName();
    }

    @Override // javax.portlet.PortletRequest
    public int getServerPort() {
        return this.request.getServerPort();
    }

    @Override // javax.portlet.PortletRequest
    public Principal getUserPrincipal() {
        return this.request.getUserPrincipal();
    }

    @Override // javax.portlet.PortletRequest
    public String getWindowID() {
        return this.request.getWindowID();
    }

    @Override // javax.portlet.PortletRequest
    public WindowState getWindowState() {
        return this.request.getWindowState();
    }

    @Override // javax.portlet.PortletRequest
    public boolean isPortletModeAllowed(PortletMode portletMode) {
        return this.request.isPortletModeAllowed(portletMode);
    }

    @Override // javax.portlet.PortletRequest
    public boolean isRequestedSessionIdValid() {
        return this.request.isRequestedSessionIdValid();
    }

    @Override // javax.portlet.PortletRequest
    public boolean isSecure() {
        return this.request.isSecure();
    }

    @Override // javax.portlet.PortletRequest
    public boolean isUserInRole(String str) {
        return this.request.isUserInRole(str);
    }

    @Override // javax.portlet.PortletRequest
    public boolean isWindowStateAllowed(WindowState windowState) {
        return this.request.isWindowStateAllowed(windowState);
    }

    @Override // javax.portlet.PortletRequest
    public void removeAttribute(String str) {
        this.request.removeAttribute(str);
    }

    @Override // javax.portlet.PortletRequest
    public void setAttribute(String str, Object obj) {
        this.request.setAttribute(str, obj);
    }

    public void setRequest(PortletRequest portletRequest) {
        if (portletRequest != null) {
            this.request = portletRequest;
            return;
        }
        throw new IllegalArgumentException("Request is null");
    }

    public PortletRequestWrapper(PortletRequest portletRequest) {
        if (portletRequest != null) {
            this.request = portletRequest;
            return;
        }
        throw new IllegalArgumentException("Request is null");
    }

    @Override // javax.portlet.PortletRequest
    public PortletSession getPortletSession(boolean z) {
        return this.request.getPortletSession(z);
    }
}
