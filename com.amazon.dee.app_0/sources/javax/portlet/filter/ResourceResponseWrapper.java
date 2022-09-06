package javax.portlet.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import javax.portlet.CacheControl;
import javax.portlet.PortletURL;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
/* loaded from: classes3.dex */
public class ResourceResponseWrapper extends PortletResponseWrapper implements ResourceResponse {
    ResourceResponse response;

    public ResourceResponseWrapper(ResourceResponse resourceResponse) {
        super(resourceResponse);
        this.response = resourceResponse;
    }

    @Override // javax.portlet.ResourceResponse, javax.portlet.MimeResponse
    public PortletURL createActionURL() throws IllegalStateException {
        return this.response.createActionURL();
    }

    @Override // javax.portlet.ResourceResponse, javax.portlet.MimeResponse
    public PortletURL createRenderURL() throws IllegalStateException {
        return this.response.createRenderURL();
    }

    @Override // javax.portlet.ResourceResponse, javax.portlet.MimeResponse
    public ResourceURL createResourceURL() throws IllegalStateException {
        return this.response.createResourceURL();
    }

    @Override // javax.portlet.MimeResponse
    public void flushBuffer() throws IOException {
        this.response.flushBuffer();
    }

    @Override // javax.portlet.MimeResponse
    public int getBufferSize() {
        return this.response.getBufferSize();
    }

    @Override // javax.portlet.MimeResponse
    public CacheControl getCacheControl() {
        return this.response.getCacheControl();
    }

    @Override // javax.portlet.MimeResponse
    public String getCharacterEncoding() {
        return this.response.getCharacterEncoding();
    }

    @Override // javax.portlet.MimeResponse
    public String getContentType() {
        return this.response.getContentType();
    }

    @Override // javax.portlet.MimeResponse
    public Locale getLocale() {
        return this.response.getLocale();
    }

    @Override // javax.portlet.MimeResponse
    public OutputStream getPortletOutputStream() throws IOException {
        return this.response.getPortletOutputStream();
    }

    @Override // javax.portlet.MimeResponse
    public PrintWriter getWriter() throws IOException {
        return this.response.getWriter();
    }

    @Override // javax.portlet.MimeResponse
    public boolean isCommitted() {
        return this.response.isCommitted();
    }

    @Override // javax.portlet.MimeResponse
    public void reset() {
        this.response.reset();
    }

    @Override // javax.portlet.MimeResponse
    public void resetBuffer() {
        this.response.resetBuffer();
    }

    @Override // javax.portlet.MimeResponse
    public void setBufferSize(int i) {
        this.response.setBufferSize(i);
    }

    @Override // javax.portlet.ResourceResponse
    public void setCharacterEncoding(String str) {
        this.response.setCharacterEncoding(str);
    }

    @Override // javax.portlet.ResourceResponse
    public void setContentLength(int i) {
        this.response.setContentLength(i);
    }

    @Override // javax.portlet.MimeResponse
    public void setContentType(String str) {
        this.response.setContentType(str);
    }

    @Override // javax.portlet.ResourceResponse
    public void setLocale(Locale locale) {
        this.response.setLocale(locale);
    }

    public void setResponse(ResourceResponse resourceResponse) {
        if (resourceResponse != null) {
            this.response = resourceResponse;
            return;
        }
        throw new IllegalArgumentException("Response is null");
    }

    @Override // javax.portlet.filter.PortletResponseWrapper
    /* renamed from: getResponse  reason: collision with other method in class */
    public ResourceResponse mo10374getResponse() {
        return this.response;
    }
}
