package com.amazon.alexa.mosaic.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.TypedValue;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public final class ThemeUtil {
    public static final String DARK_THEME = "dark";
    public static final String LIGHT_THEME = "light";
    public static final String MOSAIC_THEME = "MOSAIC_THEME";
    public static final String MOSAIC_THEME_PREFERENCES = "MOSAIC_THEME_PREFERENCES";
    static final String STARTUP_STATE = "startupState";
    private static final String TAG = "ThemeUtil";
    private static String overriddenTheme;

    private ThemeUtil() {
    }

    private static void clearOverriddenTheme() {
        PersistentStorage create = ((PersistentStorage.Factory) GeneratedOutlineSupport1.outline20(PersistentStorage.Factory.class)).create(STARTUP_STATE);
        if (create != null) {
            create.edit().remove(ThemePreferenceSubscriber.THEME_PREFERENCE_STORAGE_KEY).commit();
        }
        overriddenTheme = null;
    }

    public static int getColorFromAttribute(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context == null || context.getTheme() == null || !context.getTheme().resolveAttribute(i, typedValue, true)) {
            return 0;
        }
        return typedValue.data;
    }

    public static Subscriber getThemePreferenceSubscriber(Context context) {
        return ThemePreferenceSubscriber.getInstance($$Lambda$ThemeUtil$a9ANJ3qq0TRA1DSCFhqY0GQi5rY.INSTANCE, ((PersistentStorage.Factory) GeneratedOutlineSupport1.outline20(PersistentStorage.Factory.class)).create(STARTUP_STATE));
    }

    public static void initTheme(@Nullable Context context) {
        int i;
        if (isValidThemingOSVersion()) {
            setOverriddenTheme();
            String str = overriddenTheme;
            if (str != null) {
                i = str.equals("light") ? 1 : 2;
                AppCompatDelegate.setDefaultNightMode(i);
            }
        }
        i = -1;
        AppCompatDelegate.setDefaultNightMode(i);
    }

    public static boolean isLightMode(Context context) {
        int i = context.getResources().getConfiguration().uiMode & 48;
        return i == 16 || i != 32;
    }

    private static boolean isValidThemingOSVersion() {
        return Build.VERSION.SDK_INT > 28;
    }

    public static void resetAppTheme() {
        if (overriddenTheme != null) {
            clearOverriddenTheme();
        }
    }

    public static int retrieveTheme(@NonNull Context context) {
        return context.getSharedPreferences(MOSAIC_THEME_PREFERENCES, 0).getInt(MOSAIC_THEME, R.style.Theme_Mosaic_Blue);
    }

    private static void saveTheme(@NonNull Context context, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(MOSAIC_THEME_PREFERENCES, 0).edit();
        edit.putInt(MOSAIC_THEME, i);
        edit.apply();
    }

    private static void setOverriddenTheme() {
        PersistentStorage create = ((PersistentStorage.Factory) GeneratedOutlineSupport1.outline20(PersistentStorage.Factory.class)).create(STARTUP_STATE);
        if (create != null) {
            String string = create.getString(ThemePreferenceSubscriber.THEME_PREFERENCE_STORAGE_KEY, "");
            char c = 65535;
            int hashCode = string.hashCode();
            if (hashCode != 3075958) {
                if (hashCode == 102970646 && string.equals("light")) {
                    c = 0;
                }
            } else if (string.equals("dark")) {
                c = 1;
            }
            if (c == 0) {
                overriddenTheme = "light";
            } else if (c != 1) {
                overriddenTheme = null;
            } else {
                overriddenTheme = "dark";
            }
        }
    }

    public static void setTheme(@NonNull Context context) {
        context.setTheme(R.style.Theme_Mosaic_Jasper);
    }

    public static void useBlueTheme(@NonNull Context context) {
        saveTheme(context, R.style.Theme_Mosaic_Blue);
    }

    public static void useJasperTheme(@NonNull Context context) {
        saveTheme(context, R.style.Theme_Mosaic_Jasper);
    }
}
