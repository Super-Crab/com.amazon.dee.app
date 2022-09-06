package javax.portlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
/* loaded from: classes3.dex */
public interface BaseURL {
    void addProperty(String str, String str2);

    Map<String, String[]> getParameterMap();

    void setParameter(String str, String str2);

    void setParameter(String str, String[] strArr);

    void setParameters(Map<String, String[]> map);

    void setProperty(String str, String str2);

    void setSecure(boolean z) throws PortletSecurityException;

    String toString();

    void write(Writer writer) throws IOException;

    void write(Writer writer, boolean z) throws IOException;
}
