package javax.servlet.http;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import java.text.MessageFormat;
import java.util.ResourceBundle;
/* loaded from: classes3.dex */
public class Cookie implements Cloneable {
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);
    private static final String tspecials = ",; ";
    private String comment;
    private String domain;
    private String name;
    private String path;
    private boolean secure;
    private String value;
    private int maxAge = -1;
    private int version = 0;

    public Cookie(String str, String str2) {
        if (isToken(str) && !str.equalsIgnoreCase(MAPCookie.KEY_COMMENT) && !str.equalsIgnoreCase("Discard") && !str.equalsIgnoreCase(MAPCookie.KEY_DOMAIN) && !str.equalsIgnoreCase("Expires") && !str.equalsIgnoreCase("Max-Age") && !str.equalsIgnoreCase(MAPCookie.KEY_PATH) && !str.equalsIgnoreCase(MAPCookie.KEY_SECURE) && !str.equalsIgnoreCase("Version") && !str.startsWith("$")) {
            this.name = str;
            this.value = str2;
            return;
        }
        throw new IllegalArgumentException(MessageFormat.format(lStrings.getString("err.cookie_name_is_token"), str));
    }

    private boolean isToken(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt < ' ' || charAt >= 127 || tspecials.indexOf(charAt) != -1) {
                return false;
            }
        }
        return true;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getComment() {
        return this.comment;
    }

    public String getDomain() {
        return this.domain;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public boolean getSecure() {
        return this.secure;
    }

    public String getValue() {
        return this.value;
    }

    public int getVersion() {
        return this.version;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setDomain(String str) {
        this.domain = str.toLowerCase();
    }

    public void setMaxAge(int i) {
        this.maxAge = i;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setSecure(boolean z) {
        this.secure = z;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void setVersion(int i) {
        this.version = i;
    }
}
