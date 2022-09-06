package amazon.speech.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
/* loaded from: classes.dex */
public class SecureSettingsHelper implements ISettingsUtil {
    private static final String TAG = "SecureSettingsHelper";
    private ContentResolver mResolver;

    public SecureSettingsHelper(Context context) {
        this.mResolver = context.getContentResolver();
    }

    @Override // amazon.speech.util.ISettingsUtil
    public int getInt(String str, int i) {
        return Settings.Secure.getInt(this.mResolver, str, i);
    }

    public int getIntForUser(String str, int i, int i2) {
        try {
            return ((Integer) Class.forName("android.provider.Settings$Secure").getMethod("getIntForUser", ContentResolver.class, String.class, Integer.TYPE, Integer.TYPE).invoke(null, this.mResolver, str, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
        } catch (ReflectiveOperationException unused) {
            android.util.Log.e(TAG, "could not invoke getIntForUser method, returning default value");
            return i;
        }
    }

    public String getString(String str) {
        return Settings.Secure.getString(this.mResolver, str);
    }

    public Uri getUriFor(String str) {
        return Settings.Secure.getUriFor(str);
    }

    @Override // amazon.speech.util.ISettingsUtil
    public boolean putInt(String str, int i) {
        return Settings.Secure.putInt(this.mResolver, str, i);
    }

    public boolean putIntForUser(String str, int i, int i2) {
        try {
            return ((Boolean) Class.forName("android.provider.Settings$Secure").getMethod("putIntForUser", ContentResolver.class, String.class, Integer.TYPE, Integer.TYPE).invoke(null, this.mResolver, str, Integer.valueOf(i), Integer.valueOf(i2))).booleanValue();
        } catch (ReflectiveOperationException unused) {
            android.util.Log.e(TAG, "could not invoke putIntForUser method");
            return false;
        }
    }

    public void putString(String str, String str2) {
        Settings.Secure.putString(this.mResolver, str, str2);
    }
}
