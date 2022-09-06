package com.amazon.alexa.applink.evaluator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Target {
    public static Target create(@Nullable String str, @NonNull String str2, @NonNull TargetIdentifierType targetIdentifierType, boolean z) {
        return new AutoValue_Target(str, str2, targetIdentifierType, z);
    }

    @Nullable
    public abstract String catalogId();

    @NonNull
    public abstract String identifier();

    public abstract boolean isMandatoryToLaunchTarget();

    @NonNull
    public abstract TargetIdentifierType targetIdentifierType();
}
