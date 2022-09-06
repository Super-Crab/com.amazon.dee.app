package javax.servlet.http;

import java.util.Enumeration;
/* loaded from: classes3.dex */
public interface HttpSessionContext {
    Enumeration getIds();

    HttpSession getSession(String str);
}
