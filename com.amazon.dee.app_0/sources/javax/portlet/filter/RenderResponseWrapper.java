package javax.portlet.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;
import javax.portlet.CacheControl;
import javax.portlet.PortletMode;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;
/* loaded from: classes3.dex */
public class RenderResponseWrapper extends PortletResponseWrapper implements RenderResponse {
    RenderResponse response;

    public RenderResponseWrapper(RenderResponse renderResponse) {
        super(renderResponse);
        this.response = renderResponse;
    }

    @Override // javax.portlet.MimeResponse
    public PortletURL createActionURL() throws IllegalStateException {
        return this.response.createActionURL();
    }

    @Override // javax.portlet.MimeResponse
    public PortletURL createRenderURL() throws IllegalStateException {
        return this.response.createRenderURL();
    }

    @Override // javax.portlet.MimeResponse
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

    @Override // javax.portlet.RenderResponse, javax.portlet.MimeResponse
    public void setContentType(String str) {
        this.response.setContentType(str);
    }

    @Override // javax.portlet.RenderResponse
    public void setNextPossiblePortletModes(Collection<PortletMode> collection) {
        this.response.setNextPossiblePortletModes(collection);
    }

    public void setResponse(RenderResponse renderResponse) {
        if (renderResponse != null) {
            this.response = renderResponse;
            return;
        }
        throw new IllegalArgumentException("Response is null");
    }

    @Override // javax.portlet.RenderResponse
    public void setTitle(String str) {
        this.response.setTitle(str);
    }

    @Override // javax.portlet.filter.PortletResponseWrapper
    /* renamed from: getResponse  reason: collision with other method in class */
    public RenderResponse mo10374getResponse() {
        return this.response;
    }
}
