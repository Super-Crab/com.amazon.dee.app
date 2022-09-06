package amazon.speech.util;

import amazon.speech.util.DebugUtil;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class AndroidDsnProvider implements DsnProvider {
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.SIM, AndroidDsnProvider.class);
    private static Method sGetSerial;

    static {
        try {
            sGetSerial = Class.forName("android.os.Build").getMethod("getSerial", null);
        } catch (ReflectiveOperationException e) {
            Log.w(TAG, "Could not use reflection to get getSerial method as Android version is under 26. Use old method Build.SERIAL instead.", e);
            sGetSerial = null;
        }
    }

    @Override // amazon.speech.util.DsnProvider
    public String getDsn() {
        Method method = sGetSerial;
        if (method != null) {
            try {
                String str = (String) method.invoke(null, new Object[0]);
                if (str != null) {
                    return str;
                }
                Log.wtf(TAG, "Cannot find serial number.", new NoSuchFieldException());
            } catch (IllegalAccessException | InvocationTargetException e) {
                Log.e(TAG, "Exception getting serial number.", e);
            }
        }
        if (Build.VERSION.SDK_INT > 26) {
            Log.w(TAG, "Failed to find new DSN retrieval method, falling back to deprecated method.");
        }
        return Build.SERIAL;
    }
}
