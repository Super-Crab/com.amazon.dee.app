package com.amazon.alexa.api.compat.metrics;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: UserPerceivedLatencyListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyListener;", "", "onLatencyData", "", AccessoryMetricsConstants.DIALOG_TURN_ID, "", "data", "Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyData;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface UserPerceivedLatencyListener {
    void onLatencyData(@Nullable String str, @Nullable UserPerceivedLatencyData userPerceivedLatencyData);
}
