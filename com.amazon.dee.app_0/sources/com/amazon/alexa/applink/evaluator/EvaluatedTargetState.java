package com.amazon.alexa.applink.evaluator;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class EvaluatedTargetState {
    public static EvaluatedTargetState create(@NonNull Target target, @Nullable Intent intent, @NonNull InstallStatus installStatus, boolean z, boolean z2) {
        return new AutoValue_EvaluatedTargetState(target, intent, installStatus, z, z2);
    }

    public abstract boolean canLaunch();

    public abstract InstallStatus installStatus();

    public abstract boolean isLaunchedInTarget();

    @Nullable
    public abstract Intent launchIntent();

    public abstract Target target();
}
