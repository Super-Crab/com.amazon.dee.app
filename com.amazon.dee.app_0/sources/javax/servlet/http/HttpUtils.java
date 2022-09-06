package javax.servlet.http;

import com.amazon.alexa.biloba.utils.WebConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.servlet.ServletInputStream;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class HttpUtils {
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);

    public static StringBuffer getRequestURL(HttpServletRequest httpServletRequest) {
        StringBuffer stringBuffer = new StringBuffer();
        String scheme = httpServletRequest.getScheme();
        int serverPort = httpServletRequest.getServerPort();
        String requestURI = httpServletRequest.getRequestURI();
        stringBuffer.append(scheme);
        stringBuffer.append("://");
        stringBuffer.append(httpServletRequest.getServerName());
        if ((scheme.equals("http") && serverPort != 80) || (scheme.equals("https") && serverPort != 443)) {
            stringBuffer.append(JsonReaderKt.COLON);
            stringBuffer.append(httpServletRequest.getServerPort());
        }
        stringBuffer.append(requestURI);
        return stringBuffer;
    }

    private static String parseName(String str, StringBuffer stringBuffer) {
        int i = 0;
        stringBuffer.setLength(0);
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt == '%') {
                int i2 = i + 1;
                try {
                    stringBuffer.append((char) Integer.parseInt(str.substring(i2, i + 3), 16));
                    i += 2;
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException();
                } catch (StringIndexOutOfBoundsException unused2) {
                    String substring = str.substring(i);
                    stringBuffer.append(substring);
                    if (substring.length() == 2) {
                        i = i2;
                    }
                }
            } else if (charAt != '+') {
                stringBuffer.append(charAt);
            } else {
                stringBuffer.append(Chars.SPACE);
            }
            i++;
        }
        return stringBuffer.toString();
    }

    public static Hashtable parsePostData(int i, ServletInputStream servletInputStream) {
        if (i <= 0) {
            return new Hashtable();
        }
        if (servletInputStream != null) {
            byte[] bArr = new byte[i];
            int i2 = 0;
            do {
                try {
                    int read = servletInputStream.read(bArr, i2, i - i2);
                    if (read <= 0) {
                        throw new IllegalArgumentException(lStrings.getString("err.io.short_read"));
                    }
                    i2 += read;
                } catch (IOException e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            } while (i - i2 > 0);
            try {
                return parseQueryString(new String(bArr, 0, i, "8859_1"));
            } catch (UnsupportedEncodingException e2) {
                throw new IllegalArgumentException(e2.getMessage());
            }
        }
        throw new IllegalArgumentException();
    }

    public static Hashtable parseQueryString(String str) {
        String[] strArr;
        if (str != null) {
            Hashtable hashtable = new Hashtable();
            StringBuffer stringBuffer = new StringBuffer();
            StringTokenizer stringTokenizer = new StringTokenizer(str, WebConstants.UriConstants.AMPERSAND_KEY);
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                int indexOf = nextToken.indexOf(61);
                if (indexOf != -1) {
                    String parseName = parseName(nextToken.substring(0, indexOf), stringBuffer);
                    String parseName2 = parseName(nextToken.substring(indexOf + 1, nextToken.length()), stringBuffer);
                    if (hashtable.containsKey(parseName)) {
                        String[] strArr2 = (String[]) hashtable.get(parseName);
                        strArr = new String[strArr2.length + 1];
                        for (int i = 0; i < strArr2.length; i++) {
                            strArr[i] = strArr2[i];
                        }
                        strArr[strArr2.length] = parseName2;
                    } else {
                        strArr = new String[]{parseName2};
                    }
                    hashtable.put(parseName, strArr);
                } else {
                    throw new IllegalArgumentException();
                }
            }
            return hashtable;
        }
        throw new IllegalArgumentException();
    }
}
