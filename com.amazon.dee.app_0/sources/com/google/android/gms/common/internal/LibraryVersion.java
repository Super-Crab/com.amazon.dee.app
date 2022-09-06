package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
@KeepForSdk
/* loaded from: classes2.dex */
public class LibraryVersion {
    private static final GmsLogger zzel = new GmsLogger(TarazedMetricsHelper.LIB_VERSION_KEY, "");
    private static LibraryVersion zzem = new LibraryVersion();
    private ConcurrentHashMap<String, String> zzen = new ConcurrentHashMap<>();

    @VisibleForTesting
    protected LibraryVersion() {
    }

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzem;
    }

    @KeepForSdk
    public String getVersion(@NonNull String str) {
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzen.containsKey(str)) {
            return this.zzen.get(str);
        }
        Properties properties = new Properties();
        String str2 = null;
        try {
            InputStream resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", str));
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
                str2 = properties.getProperty("version", null);
                GmsLogger gmsLogger = zzel;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12 + String.valueOf(str2).length());
                sb.append(str);
                sb.append(" version is ");
                sb.append(str2);
                gmsLogger.v(TarazedMetricsHelper.LIB_VERSION_KEY, sb.toString());
            } else {
                GmsLogger gmsLogger2 = zzel;
                String valueOf = String.valueOf(str);
                gmsLogger2.e(TarazedMetricsHelper.LIB_VERSION_KEY, valueOf.length() != 0 ? "Failed to get app version for libraryName: ".concat(valueOf) : new String("Failed to get app version for libraryName: "));
            }
        } catch (IOException e) {
            GmsLogger gmsLogger3 = zzel;
            String valueOf2 = String.valueOf(str);
            gmsLogger3.e(TarazedMetricsHelper.LIB_VERSION_KEY, valueOf2.length() != 0 ? "Failed to get app version for libraryName: ".concat(valueOf2) : new String("Failed to get app version for libraryName: "), e);
        }
        if (str2 == null) {
            zzel.d(TarazedMetricsHelper.LIB_VERSION_KEY, ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
            str2 = "UNKNOWN";
        }
        this.zzen.put(str, str2);
        return str2;
    }
}
