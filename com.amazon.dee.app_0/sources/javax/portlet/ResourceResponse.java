package javax.portlet;

import java.util.Locale;
/* loaded from: classes3.dex */
public interface ResourceResponse extends MimeResponse {
    public static final String HTTP_STATUS_CODE = "portlet.http-status-code";

    @Override // javax.portlet.MimeResponse
    PortletURL createActionURL();

    @Override // javax.portlet.MimeResponse
    PortletURL createRenderURL();

    @Override // javax.portlet.MimeResponse
    ResourceURL createResourceURL();

    void setCharacterEncoding(String str);

    void setContentLength(int i);

    void setLocale(Locale locale);
}
