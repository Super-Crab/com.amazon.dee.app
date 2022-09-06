package javax.portlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
/* loaded from: classes3.dex */
public interface ClientDataRequest extends PortletRequest {
    String getCharacterEncoding();

    int getContentLength();

    String getContentType();

    String getMethod();

    InputStream getPortletInputStream() throws IOException;

    BufferedReader getReader() throws UnsupportedEncodingException, IOException;

    void setCharacterEncoding(String str) throws UnsupportedEncodingException;
}
