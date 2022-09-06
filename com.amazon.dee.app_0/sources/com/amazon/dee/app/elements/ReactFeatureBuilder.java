package com.amazon.dee.app.elements;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public class ReactFeatureBuilder {
    @Nullable
    String appName;
    @Nullable
    String instanceId;
    @Nullable
    Bundle launchOptions;

    public ReactFeature build() {
        Preconditions.checkNotNull(this.instanceId, "Instance ID cannot be null");
        Preconditions.checkArgument(!TextUtils.isEmpty(this.appName), "AppName cannot be empty.");
        return new ReactFeatureImpl(this.instanceId, this.appName, this.launchOptions);
    }

    public ReactFeatureBuilder withAppName(@NonNull String str) {
        this.appName = str;
        return this;
    }

    public ReactFeatureBuilder withInstanceId(@NonNull String str) {
        this.instanceId = str;
        return this;
    }

    public ReactFeatureBuilder withLaunchOptions(@NonNull Bundle bundle) {
        this.launchOptions = bundle;
        return this;
    }
}
