package androidx.core.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutInfoCompatSaver;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class ShortcutManagerCompat {
    @VisibleForTesting
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
    @VisibleForTesting
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
    private static volatile ShortcutInfoCompatSaver<?> sShortcutInfoCompatSaver;

    /* renamed from: androidx.core.content.pm.ShortcutManagerCompat$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static class AnonymousClass1 extends BroadcastReceiver {
        final /* synthetic */ IntentSender val$callback;

        AnonymousClass1(IntentSender intentSender) {
            this.val$callback = intentSender;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                this.val$callback.sendIntent(context, 0, null, null, null);
            } catch (IntentSender.SendIntentException unused) {
            }
        }
    }

    private ShortcutManagerCompat() {
    }

    public static boolean addDynamicShortcuts(@NonNull Context context, @NonNull List<ShortcutInfoCompat> list) {
        int i = Build.VERSION.SDK_INT;
        ArrayList arrayList = new ArrayList();
        for (ShortcutInfoCompat shortcutInfoCompat : list) {
            arrayList.add(shortcutInfoCompat.toShortcutInfo());
        }
        if (!((ShortcutManager) context.getSystemService(ShortcutManager.class)).addDynamicShortcuts(arrayList)) {
            return false;
        }
        getShortcutInfoSaverInstance(context).addShortcuts(list);
        return true;
    }

    @NonNull
    public static Intent createShortcutResultIntent(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat) {
        int i = Build.VERSION.SDK_INT;
        Intent createShortcutResultIntent = ((ShortcutManager) context.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcutInfoCompat.toShortcutInfo());
        if (createShortcutResultIntent == null) {
            createShortcutResultIntent = new Intent();
        }
        return shortcutInfoCompat.addToIntent(createShortcutResultIntent);
    }

    @NonNull
    public static List<ShortcutInfoCompat> getDynamicShortcuts(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        List<ShortcutInfo> dynamicShortcuts = ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getDynamicShortcuts();
        ArrayList arrayList = new ArrayList(dynamicShortcuts.size());
        for (ShortcutInfo shortcutInfo : dynamicShortcuts) {
            arrayList.add(new ShortcutInfoCompat.Builder(context, shortcutInfo).build());
        }
        return arrayList;
    }

    public static int getMaxShortcutCountPerActivity(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getMaxShortcutCountPerActivity();
    }

    private static ShortcutInfoCompatSaver getShortcutInfoSaverInstance(Context context) {
        if (sShortcutInfoCompatSaver == null) {
            int i = Build.VERSION.SDK_INT;
            try {
                sShortcutInfoCompatSaver = (ShortcutInfoCompatSaver) Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context);
            } catch (Exception unused) {
            }
            if (sShortcutInfoCompatSaver == null) {
                sShortcutInfoCompatSaver = new ShortcutInfoCompatSaver.NoopImpl();
            }
        }
        return sShortcutInfoCompatSaver;
    }

    public static boolean isRequestPinShortcutSupported(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).isRequestPinShortcutSupported();
    }

    public static void removeAllDynamicShortcuts(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).removeAllDynamicShortcuts();
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
    }

    public static boolean requestPinShortcut(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat, @Nullable IntentSender intentSender) {
        int i = Build.VERSION.SDK_INT;
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcutInfoCompat.toShortcutInfo(), intentSender);
    }

    public static boolean updateShortcuts(@NonNull Context context, @NonNull List<ShortcutInfoCompat> list) {
        int i = Build.VERSION.SDK_INT;
        ArrayList arrayList = new ArrayList();
        for (ShortcutInfoCompat shortcutInfoCompat : list) {
            arrayList.add(shortcutInfoCompat.toShortcutInfo());
        }
        if (!((ShortcutManager) context.getSystemService(ShortcutManager.class)).updateShortcuts(arrayList)) {
            return false;
        }
        getShortcutInfoSaverInstance(context).addShortcuts(list);
        return true;
    }

    public void removeDynamicShortcuts(@NonNull Context context, @NonNull List<String> list) {
        int i = Build.VERSION.SDK_INT;
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).removeDynamicShortcuts(list);
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
    }
}
