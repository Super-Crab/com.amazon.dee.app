package androidx.appcompat.widget;

import android.os.Build;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class TooltipCompat {
    private TooltipCompat() {
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        view.setTooltipText(charSequence);
    }
}
