package javax.portlet;

import java.io.IOException;
/* loaded from: classes3.dex */
public interface ResourceServingPortlet {
    void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortletException, IOException;
}
