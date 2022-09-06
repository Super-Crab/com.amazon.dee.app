package androidx.core.os;

import android.content.Context;
import android.os.Build;
import android.os.UserManager;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public class UserManagerCompat {
    private UserManagerCompat() {
    }

    public static boolean isUserUnlocked(@NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }
}
