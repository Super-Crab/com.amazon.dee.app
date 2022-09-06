package amazon.speech.util;

import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import com.dee.app.metrics.MetricsConstants;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes.dex */
public class RuntimeDeviceTypeHelper {
    private static final String ADAPTIVE_VOLUME_ARCUS_KEY = "enable_adaptive_volume";
    private static final String AMAZON_DEVICE_MANUFACTURER = "Amazon";
    private static final String BUILD_CONFIGURATION_PROPERTY = "ro.build.configuration";
    private static final String CP_AUTHORITY = "amazon.speech.sim.util.provider.arcus";
    private static final String CP_KEY = "arcus_key";
    private static final String CP_PATH = "arcus_path";
    private static final String CP_VALUE = "arcus_value";
    private static int STREAM_TTS = 0;
    private static final String SYSTEM_PROPERTIES_CLASS = "android.os.SystemProperties";
    private static final String TABLET_BUILD_CONFIGURATION = "tablet";
    private static final String TAG = "RuntimeDeviceTypeHelper";
    static String sBuildManufacturer = Build.MANUFACTURER;
    private static String sCurrentBuildConfiguration;

    static {
        try {
            sCurrentBuildConfiguration = String.valueOf(Class.forName(SYSTEM_PROPERTIES_CLASS).getMethod(MetricsConstants.Method.CACHE_GET, String.class).invoke(null, BUILD_CONFIGURATION_PROPERTY));
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            android.util.Log.e(TAG, "Could not determine product, falling back to default", e);
        }
        try {
            STREAM_TTS = ((Integer) AudioManager.class.getDeclaredField("STREAM_TTS").get(null)).intValue();
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            android.util.Log.e(TAG, "Could not access STREAM_TTS. Assigning STREAM_TTS = STREAM_SYSTEM", e2);
            STREAM_TTS = 1;
        }
    }

    static String getDeviceType() {
        return sCurrentBuildConfiguration;
    }

    @Deprecated
    public static int getTtsStreamType() {
        return (!isDeviceAmazon() || isDeviceATablet()) ? 3 : 1;
    }

    public static boolean isDeviceATablet() {
        return TABLET_BUILD_CONFIGURATION.equals(sCurrentBuildConfiguration);
    }

    public static boolean isDeviceAmazon() {
        return "Amazon".equals(sBuildManufacturer);
    }

    public static void setDeviceType(String str) {
        try {
            if (Build.USER.equals(Build.TYPE)) {
                return;
            }
        } catch (NullPointerException e) {
            android.util.Log.e(TAG, "NPE while checking build type.", e);
        }
        sCurrentBuildConfiguration = str;
    }

    private static boolean shouldUseTtsStream(Context context) {
        Cursor query = context.getContentResolver().query(new Uri.Builder().scheme("content").authority(CP_AUTHORITY).path(CP_PATH).build(), null, ADAPTIVE_VOLUME_ARCUS_KEY, null, null);
        if (query != null) {
            try {
                if (query.getCount() > 0) {
                    query.moveToFirst();
                    do {
                        String string = query.getString(query.getColumnIndex(CP_KEY));
                        boolean equals = "true".equals(query.getString(query.getColumnIndex(CP_VALUE)));
                        if (string.equals(ADAPTIVE_VOLUME_ARCUS_KEY) && equals) {
                            android.util.Log.i(TAG, "Use stream TTS");
                            query.close();
                            return true;
                        }
                    } while (query.moveToNext());
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        query.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
            return false;
        }
        return false;
    }

    public static int getTtsStreamType(Context context) {
        if (!shouldUseTtsStream(context) || isDeviceATablet()) {
            return (!isDeviceAmazon() || isDeviceATablet()) ? 3 : 1;
        }
        return STREAM_TTS;
    }
}
