package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.validate;

import android.content.Context;
import com.android.tools.r8.GeneratedOutlineSupport1;
@Deprecated
/* loaded from: classes13.dex */
public class PermissionValidator {
    private final String permission;

    public PermissionValidator(String str) {
        this.permission = str;
    }

    private boolean hasPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public void validate(Context context) {
        if (hasPermission(context, this.permission)) {
            return;
        }
        throw new RuntimeException(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.permission, " permission is not granted."));
    }
}
