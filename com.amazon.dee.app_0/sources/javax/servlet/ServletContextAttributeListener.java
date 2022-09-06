package javax.servlet;

import java.util.EventListener;
/* loaded from: classes3.dex */
public interface ServletContextAttributeListener extends EventListener {
    void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent);

    void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent);

    void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent);
}
