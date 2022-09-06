package com.amazon.alexa.devices.settings;

import com.amazon.alexa.devices.AlexaException;
/* loaded from: classes6.dex */
public interface SettingsCallback<T> {
    void onError(AlexaException alexaException);

    void onSuccess(T t);
}
