package com.amazon.dee.app.elements;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public interface ReactFeature extends Parcelable {
    @NonNull
    String getAppName();

    @NonNull
    String getInstanceId();

    @Nullable
    Bundle getLaunchOptions();
}
