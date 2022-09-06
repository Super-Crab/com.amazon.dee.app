package com.amazon.alexa.accessorykit.findmy.setting;

import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface SettingProvider {
    Single<SettingResponse> querySetting(SettingRequest settingRequest);
}
