package javax.portlet;

import java.io.IOException;
/* loaded from: classes3.dex */
public interface ActionResponse extends StateAwareResponse {
    void sendRedirect(String str) throws IOException;

    void sendRedirect(String str, String str2) throws IOException;
}
