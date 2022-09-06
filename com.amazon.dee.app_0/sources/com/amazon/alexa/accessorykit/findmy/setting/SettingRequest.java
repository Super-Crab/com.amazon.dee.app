package com.amazon.alexa.accessorykit.findmy.setting;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class SettingRequest {
    public static SettingRequest create(String str, String str2) {
        return new AutoValue_SettingRequest(str, str2);
    }

    public abstract String getAccountId();

    public abstract String getDeviceType();
}
