package javax.portlet;

import java.util.Collection;
/* loaded from: classes3.dex */
public interface RenderResponse extends MimeResponse {
    @Override // javax.portlet.MimeResponse
    void setContentType(String str);

    void setNextPossiblePortletModes(Collection<PortletMode> collection);

    void setTitle(String str);
}
