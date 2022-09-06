package org.bouncycastle.i18n;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
/* loaded from: classes4.dex */
public class MissingEntryException extends RuntimeException {
    private String debugMsg;
    protected final String key;
    protected final ClassLoader loader;
    protected final Locale locale;
    protected final String resource;

    public MissingEntryException(String str, String str2, String str3, Locale locale, ClassLoader classLoader) {
        super(str);
        this.resource = str2;
        this.key = str3;
        this.locale = locale;
        this.loader = classLoader;
    }

    public MissingEntryException(String str, Throwable th, String str2, String str3, Locale locale, ClassLoader classLoader) {
        super(str, th);
        this.resource = str2;
        this.key = str3;
        this.locale = locale;
        this.loader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.loader;
    }

    public String getDebugMsg() {
        if (this.debugMsg == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not find entry ");
            outline107.append(this.key);
            outline107.append(" in resource file ");
            outline107.append(this.resource);
            outline107.append(" for the locale ");
            outline107.append(this.locale);
            outline107.append(".");
            this.debugMsg = outline107.toString();
            ClassLoader classLoader = this.loader;
            if (classLoader instanceof URLClassLoader) {
                URL[] uRLs = ((URLClassLoader) classLoader).getURLs();
                this.debugMsg = GeneratedOutlineSupport1.outline91(new StringBuilder(), this.debugMsg, " The following entries in the classpath were searched: ");
                for (int i = 0; i != uRLs.length; i++) {
                    this.debugMsg += uRLs[i] + " ";
                }
            }
        }
        return this.debugMsg;
    }

    public String getKey() {
        return this.key;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public String getResource() {
        return this.resource;
    }
}
