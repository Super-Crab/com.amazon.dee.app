package com.amazon.alexa.mobilytics.identity;

import androidx.annotation.NonNull;
import java.util.Map;
/* loaded from: classes9.dex */
public interface MobilyticsEndpointPicker {
    <T> T getEndpoint(@NonNull Map<String, T> map);
}
