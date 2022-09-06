package com.amazon.alexa.enrollment.unified.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
/* loaded from: classes7.dex */
public interface SettingsProvider {
    void isUVREnrolled(@NonNull ResultCallback<Boolean> resultCallback);

    boolean isUVREnrolled();

    void removeUVRModel(@NonNull ResponseCallback responseCallback);
}
