package javax.portlet;

import java.util.Enumeration;
import java.util.Map;
/* loaded from: classes3.dex */
public interface ResourceRequest extends ClientDataRequest {
    public static final String ETAG = "portlet.ETag";

    String getCacheability();

    String getETag();

    Map<String, String[]> getPrivateRenderParameterMap();

    String getResourceID();

    @Override // javax.portlet.PortletRequest
    String getResponseContentType();

    @Override // javax.portlet.PortletRequest
    Enumeration<String> getResponseContentTypes();
}
