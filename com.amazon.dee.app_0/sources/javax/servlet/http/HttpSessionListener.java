package javax.servlet.http;

import java.util.EventListener;
/* loaded from: classes3.dex */
public interface HttpSessionListener extends EventListener {
    void sessionCreated(HttpSessionEvent httpSessionEvent);

    void sessionDestroyed(HttpSessionEvent httpSessionEvent);
}
