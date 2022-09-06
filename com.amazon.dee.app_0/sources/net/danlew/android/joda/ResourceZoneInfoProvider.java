package net.danlew.android.joda;

import android.content.Context;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import net.danlew.android.joda.R;
import org.joda.time.DateTimeZone;
import org.joda.time.tz.Provider;
/* loaded from: classes4.dex */
public class ResourceZoneInfoProvider implements Provider {
    private final Map<String, Object> iZoneInfoMap;
    private Context mAppContext;

    public ResourceZoneInfoProvider(Context context) throws IOException {
        if (context != null) {
            this.mAppContext = context.getApplicationContext();
            this.iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
            return;
        }
        throw new IllegalArgumentException("Context must not be null");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0032 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.joda.time.DateTimeZone loadZoneData(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            java.io.InputStream r1 = r5.openResource(r6)     // Catch: java.lang.Throwable -> L1d java.io.IOException -> L20
            org.joda.time.DateTimeZone r2 = org.joda.time.tz.DateTimeZoneBuilder.readFrom(r1, r6)     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
            java.util.Map<java.lang.String, java.lang.Object> r3 = r5.iZoneInfoMap     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
            java.lang.ref.SoftReference r4 = new java.lang.ref.SoftReference     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
            r3.put(r6, r4)     // Catch: java.lang.Throwable -> L19 java.io.IOException -> L1b
            if (r1 == 0) goto L18
            r1.close()     // Catch: java.io.IOException -> L18
        L18:
            return r2
        L19:
            r6 = move-exception
            goto L30
        L1b:
            r2 = move-exception
            goto L22
        L1d:
            r6 = move-exception
            r1 = r0
            goto L30
        L20:
            r2 = move-exception
            r1 = r0
        L22:
            r5.uncaughtException(r2)     // Catch: java.lang.Throwable -> L19
            java.util.Map<java.lang.String, java.lang.Object> r2 = r5.iZoneInfoMap     // Catch: java.lang.Throwable -> L19
            r2.remove(r6)     // Catch: java.lang.Throwable -> L19
            if (r1 == 0) goto L2f
            r1.close()     // Catch: java.io.IOException -> L2f
        L2f:
            return r0
        L30:
            if (r1 == 0) goto L35
            r1.close()     // Catch: java.io.IOException -> L35
        L35:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: net.danlew.android.joda.ResourceZoneInfoProvider.loadZoneData(java.lang.String):org.joda.time.DateTimeZone");
    }

    private static Map<String, Object> loadZoneInfoMap(InputStream inputStream) throws IOException {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            readZoneInfoMap(dataInputStream, concurrentHashMap);
            concurrentHashMap.put(Constants.UTC, new SoftReference(DateTimeZone.UTC));
            return concurrentHashMap;
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    private InputStream openResource(String str) throws IOException {
        if (this.mAppContext != null) {
            String tzResource = ResUtils.getTzResource(str);
            int identifier = ResUtils.getIdentifier(R.raw.class, tzResource);
            if (identifier != 0) {
                return this.mAppContext.getResources().openRawResource(identifier);
            }
            throw new IOException(GeneratedOutlineSupport1.outline77("Resource not found: \"", str, "\" (resName: \"", tzResource, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED));
        }
        throw new RuntimeException("Need to call JodaTimeAndroid.init() before using joda-time-android");
    }

    private static void readZoneInfoMap(DataInputStream dataInputStream, Map<String, Object> map) throws IOException {
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        String[] strArr = new String[readUnsignedShort];
        for (int i = 0; i < readUnsignedShort; i++) {
            strArr[i] = dataInputStream.readUTF().intern();
        }
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        for (int i2 = 0; i2 < readUnsignedShort2; i2++) {
            try {
                map.put(strArr[dataInputStream.readUnsignedShort()], strArr[dataInputStream.readUnsignedShort()]);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new IOException("Corrupt zone info map");
            }
        }
    }

    @Override // org.joda.time.tz.Provider
    public Set<String> getAvailableIDs() {
        return new TreeSet(this.iZoneInfoMap.keySet());
    }

    @Override // org.joda.time.tz.Provider
    public DateTimeZone getZone(String str) {
        Object obj;
        if (str == null || (obj = this.iZoneInfoMap.get(str)) == null) {
            return null;
        }
        if (str.equals(obj)) {
            return loadZoneData(str);
        }
        if (obj instanceof SoftReference) {
            DateTimeZone dateTimeZone = (DateTimeZone) ((SoftReference) obj).get();
            return dateTimeZone != null ? dateTimeZone : loadZoneData(str);
        }
        return getZone((String) obj);
    }

    protected void uncaughtException(Exception exc) {
        exc.printStackTrace();
    }
}
