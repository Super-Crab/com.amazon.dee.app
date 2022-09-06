package javax.portlet.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.portlet.ResourceRequest;
/* loaded from: classes3.dex */
public class ResourceRequestWrapper extends PortletRequestWrapper implements ResourceRequest {
    ResourceRequest request;

    public ResourceRequestWrapper(ResourceRequest resourceRequest) {
        super(resourceRequest);
        this.request = resourceRequest;
    }

    @Override // javax.portlet.ResourceRequest
    public String getCacheability() {
        return this.request.getCacheability();
    }

    @Override // javax.portlet.ClientDataRequest
    public String getCharacterEncoding() {
        return this.request.getCharacterEncoding();
    }

    @Override // javax.portlet.ClientDataRequest
    public int getContentLength() {
        return this.request.getContentLength();
    }

    @Override // javax.portlet.ClientDataRequest
    public String getContentType() {
        return this.request.getContentType();
    }

    @Override // javax.portlet.ResourceRequest
    public String getETag() {
        return this.request.getETag();
    }

    @Override // javax.portlet.ClientDataRequest
    public String getMethod() {
        return this.request.getMethod();
    }

    @Override // javax.portlet.ClientDataRequest
    public InputStream getPortletInputStream() throws IOException {
        return this.request.getPortletInputStream();
    }

    @Override // javax.portlet.ResourceRequest
    public Map<String, String[]> getPrivateRenderParameterMap() {
        return this.request.getPrivateParameterMap();
    }

    @Override // javax.portlet.ClientDataRequest
    public BufferedReader getReader() throws UnsupportedEncodingException, IOException {
        return this.request.getReader();
    }

    @Override // javax.portlet.ResourceRequest
    public String getResourceID() {
        return this.request.getResourceID();
    }

    @Override // javax.portlet.ClientDataRequest
    public void setCharacterEncoding(String str) throws UnsupportedEncodingException {
        this.request.setCharacterEncoding(str);
    }

    public void setRequest(ResourceRequest resourceRequest) {
        if (resourceRequest != null) {
            this.request = resourceRequest;
            return;
        }
        throw new IllegalArgumentException("Request is null");
    }

    @Override // javax.portlet.filter.PortletRequestWrapper
    /* renamed from: getRequest  reason: collision with other method in class */
    public ResourceRequest mo10373getRequest() {
        return this.request;
    }
}
