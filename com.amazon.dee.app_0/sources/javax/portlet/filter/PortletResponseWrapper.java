package javax.portlet.filter;

import javax.portlet.PortletResponse;
import javax.servlet.http.Cookie;
import org.w3c.dom.Element;
/* loaded from: classes3.dex */
public class PortletResponseWrapper implements PortletResponse {
    PortletResponse response;

    private PortletResponseWrapper() {
    }

    @Override // javax.portlet.PortletResponse
    public void addProperty(String str, String str2) {
        this.response.addProperty(str, str2);
    }

    @Override // javax.portlet.PortletResponse
    public Element createElement(String str) {
        return this.response.createElement(str);
    }

    @Override // javax.portlet.PortletResponse
    public String encodeURL(String str) {
        return this.response.encodeURL(str);
    }

    @Override // javax.portlet.PortletResponse
    public String getNamespace() {
        return this.response.getNamespace();
    }

    /* renamed from: getResponse */
    public PortletResponse mo10374getResponse() {
        return this.response;
    }

    @Override // javax.portlet.PortletResponse
    public void setProperty(String str, String str2) {
        this.response.setProperty(str, str2);
    }

    public void setResponse(PortletResponse portletResponse) {
        if (portletResponse != null) {
            this.response = portletResponse;
            return;
        }
        throw new IllegalArgumentException("Response is null");
    }

    public PortletResponseWrapper(PortletResponse portletResponse) {
        if (portletResponse != null) {
            this.response = portletResponse;
            return;
        }
        throw new IllegalArgumentException("Response is null");
    }

    @Override // javax.portlet.PortletResponse
    public void addProperty(String str, Element element) {
        this.response.addProperty(str, element);
    }

    @Override // javax.portlet.PortletResponse
    public void addProperty(Cookie cookie) {
        this.response.addProperty(cookie);
    }
}
