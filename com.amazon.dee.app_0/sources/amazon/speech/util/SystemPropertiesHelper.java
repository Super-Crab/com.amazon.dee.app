package amazon.speech.util;

import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class SystemPropertiesHelper {
    private static final String GET_BOOLEAN_METHOD = "getBoolean";
    private static final String GET_INT_METHOD = "getInt";
    private static final String GET_LONG_METHOD = "getLong";
    private static final String GET_METHOD = "get";
    public static final int PROP_NAME_MAX = 31;
    private static final String SYSTEM_PROPERTIES_CLASS = "android.os.SystemProperties";
    private static final String TAG = "SPCH_CONF-SystemPropertiesHelper";
    private static Method sGetBooleanMethod;
    private static Method sGetIntMethod;
    private static Method sGetLongMethod;
    private static Method sGetMethod;
    private static final Set<String> TRUE_BOOLEAN_PROPERTY_VALUE = new HashSet(Arrays.asList(ReactProperties.HereMapMarker.Y, "yes", "1", "true", "on"));
    private static final Set<String> FALSE_BOOLEAN_PROPERTY_VALUE = new HashSet(Arrays.asList(JsonReportFormat.COUNT, "no", "0", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE, "off"));

    static {
        try {
            Class<?> cls = Class.forName(SYSTEM_PROPERTIES_CLASS);
            sGetMethod = cls.getMethod("get", String.class);
            sGetLongMethod = cls.getMethod(GET_LONG_METHOD, String.class, Long.TYPE);
            sGetIntMethod = cls.getMethod(GET_INT_METHOD, String.class, Integer.TYPE);
            sGetBooleanMethod = cls.getMethod(GET_BOOLEAN_METHOD, String.class, Boolean.TYPE);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            android.util.Log.e(TAG, "Could not using reflection to get method", e);
            sGetMethod = null;
        }
    }

    public boolean getBoolean(String str, boolean z) {
        Method method = sGetBooleanMethod;
        if (method == null) {
            return z;
        }
        try {
            Object invoke = method.invoke(null, str, Boolean.valueOf(z));
            if (invoke != null) {
                return ((Boolean) invoke).booleanValue();
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            android.util.Log.e(TAG, "Could not invoke method, fall back to null");
        }
        return z;
    }

    public int getInt(String str, int i) {
        Method method = sGetIntMethod;
        if (method == null) {
            return i;
        }
        try {
            Object invoke = method.invoke(null, str, Integer.valueOf(i));
            if (invoke != null) {
                return ((Integer) invoke).intValue();
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            android.util.Log.e(TAG, "Could not invoke method, fall back to null");
        }
        return i;
    }

    public long getLong(String str, long j) {
        Method method = sGetLongMethod;
        if (method == null) {
            return j;
        }
        try {
            Object invoke = method.invoke(null, str, Long.valueOf(j));
            if (invoke != null) {
                return ((Integer) invoke).intValue();
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            android.util.Log.e(TAG, "Could not invoke method, fall back to null");
        }
        return j;
    }

    public String getProperty(String str) {
        Method method = sGetMethod;
        if (method != null) {
            try {
                Object invoke = method.invoke(null, str);
                if (invoke != null) {
                    return String.valueOf(invoke);
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                android.util.Log.e(TAG, "Could not invoke method, fall back to null");
            }
        }
        return null;
    }

    public boolean parseBooleanPropertyValue(String str, boolean z) {
        if (TRUE_BOOLEAN_PROPERTY_VALUE.contains(str)) {
            return true;
        }
        if (!FALSE_BOOLEAN_PROPERTY_VALUE.contains(str)) {
            return z;
        }
        return false;
    }
}
