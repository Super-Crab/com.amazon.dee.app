package com.amazon.alexa.voice.handsfree.decider.setup;

import android.content.Intent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public interface PermissionStrategy {
    void execute(@NonNull PermissionDelegate permissionDelegate, @NonNull PermissionMetricRecorder permissionMetricRecorder, @NonNull Intent intent, @NonNull String str, @Nullable String str2);

    void setupNegativeButtonOnClickListener(@NonNull View view, @NonNull PermissionDelegate permissionDelegate, @NonNull PermissionMetricRecorder permissionMetricRecorder);

    void setupPositiveButtonOnClickListener(@NonNull View view, @NonNull PermissionDelegate permissionDelegate, @NonNull PermissionMetricRecorder permissionMetricRecorder, @Nullable String str);
}
