package javax.portlet.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.portlet.ActionRequest;
/* loaded from: classes3.dex */
public class ActionRequestWrapper extends PortletRequestWrapper implements ActionRequest {
    ActionRequest request;

    public ActionRequestWrapper(ActionRequest actionRequest) {
        super(actionRequest);
        this.request = actionRequest;
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

    @Override // javax.portlet.ClientDataRequest
    public String getMethod() {
        return this.request.getMethod();
    }

    @Override // javax.portlet.ClientDataRequest
    public InputStream getPortletInputStream() throws IOException {
        return this.request.getPortletInputStream();
    }

    @Override // javax.portlet.ClientDataRequest
    public BufferedReader getReader() throws UnsupportedEncodingException, IOException {
        return this.request.getReader();
    }

    @Override // javax.portlet.ClientDataRequest
    public void setCharacterEncoding(String str) throws UnsupportedEncodingException {
        this.request.setCharacterEncoding(str);
    }

    public void setRequest(ActionRequest actionRequest) {
        if (actionRequest != null) {
            this.request = actionRequest;
            return;
        }
        throw new IllegalArgumentException("Request is null");
    }

    @Override // javax.portlet.filter.PortletRequestWrapper
    /* renamed from: getRequest */
    public ActionRequest mo10373getRequest() {
        return this.request;
    }
}
