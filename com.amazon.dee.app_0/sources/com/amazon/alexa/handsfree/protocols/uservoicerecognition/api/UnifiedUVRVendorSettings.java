package com.amazon.alexa.handsfree.protocols.uservoicerecognition.api;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
/* loaded from: classes8.dex */
public interface UnifiedUVRVendorSettings extends UVRVendorSettings {
    void isSpeakerIDEnrolled(@NonNull ResultCallback<Boolean> resultCallback);
}
