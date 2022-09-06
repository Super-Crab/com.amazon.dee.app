package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class DeliveryFailureException extends Exception {
    private static final long serialVersionUID = 1501477209400426470L;

    public DeliveryFailureException(@NonNull String str) {
        super(str);
    }

    public DeliveryFailureException(@NonNull String str, @Nullable Throwable th) {
        super(str, th);
    }
}
