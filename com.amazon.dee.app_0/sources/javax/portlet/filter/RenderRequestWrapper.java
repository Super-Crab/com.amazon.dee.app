package javax.portlet.filter;

import javax.portlet.RenderRequest;
/* loaded from: classes3.dex */
public class RenderRequestWrapper extends PortletRequestWrapper implements RenderRequest {
    RenderRequest request;

    public RenderRequestWrapper(RenderRequest renderRequest) {
        super(renderRequest);
        this.request = renderRequest;
    }

    @Override // javax.portlet.RenderRequest
    public String getETag() {
        return this.request.getETag();
    }

    public void setRequest(RenderRequest renderRequest) {
        if (renderRequest != null) {
            this.request = renderRequest;
            return;
        }
        throw new IllegalArgumentException("Request is null");
    }

    @Override // javax.portlet.filter.PortletRequestWrapper
    /* renamed from: getRequest  reason: collision with other method in class */
    public RenderRequest mo10373getRequest() {
        return this.request;
    }
}
