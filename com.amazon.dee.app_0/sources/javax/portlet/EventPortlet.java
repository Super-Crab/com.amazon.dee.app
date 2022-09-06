package javax.portlet;

import java.io.IOException;
/* loaded from: classes3.dex */
public interface EventPortlet {
    void processEvent(EventRequest eventRequest, EventResponse eventResponse) throws PortletException, IOException;
}
