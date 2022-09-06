package com.bugsnag.android;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public interface BeforeSend {
    boolean run(@NonNull Report report);
}
