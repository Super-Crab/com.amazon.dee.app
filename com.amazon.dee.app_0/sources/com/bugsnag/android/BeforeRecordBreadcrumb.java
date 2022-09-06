package com.bugsnag.android;

import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public interface BeforeRecordBreadcrumb {
    boolean shouldRecord(@NonNull Breadcrumb breadcrumb);
}
