package amazon.speech.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
/* loaded from: classes.dex */
public class SystemSettingsHelper {
    private ContentResolver mResolver;

    public SystemSettingsHelper(Context context) {
        this.mResolver = context.getContentResolver();
    }

    public float getFloat(String str, float f) {
        return Settings.System.getFloat(this.mResolver, str, f);
    }

    public int getInt(String str, int i) {
        return Settings.System.getInt(this.mResolver, str, i);
    }

    public String getString(String str) {
        return Settings.System.getString(this.mResolver, str);
    }

    public Uri getUriFor(String str) {
        return Settings.System.getUriFor(str);
    }

    public boolean putFloat(String str, float f) {
        return Settings.System.putFloat(this.mResolver, str, f);
    }

    public boolean putInt(String str, int i) {
        return Settings.System.putInt(this.mResolver, str, i);
    }
}
