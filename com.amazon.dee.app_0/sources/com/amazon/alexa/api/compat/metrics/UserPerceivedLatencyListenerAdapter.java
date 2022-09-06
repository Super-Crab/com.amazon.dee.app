package com.amazon.alexa.api.compat.metrics;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.api.AlexaUserPerceivedLatencyListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UserPerceivedLatencyListenerAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyListenerAdapter;", "Lcom/amazon/alexa/api/AlexaUserPerceivedLatencyListener;", "compatListener", "Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyListener;", "(Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyListener;)V", "getCompatListener", "()Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyListener;", "onLatencyData", "", AccessoryMetricsConstants.DIALOG_TURN_ID, "", "data", "Lcom/amazon/alexa/api/UserPerceivedLatencyData;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class UserPerceivedLatencyListenerAdapter implements AlexaUserPerceivedLatencyListener {
    @NotNull
    private final UserPerceivedLatencyListener compatListener;

    public UserPerceivedLatencyListenerAdapter(@NotNull UserPerceivedLatencyListener compatListener) {
        Intrinsics.checkParameterIsNotNull(compatListener, "compatListener");
        this.compatListener = compatListener;
    }

    @NotNull
    public final UserPerceivedLatencyListener getCompatListener() {
        return this.compatListener;
    }

    @Override // com.amazon.alexa.api.AlexaUserPerceivedLatencyListener
    public void onLatencyData(@Nullable String str, @Nullable com.amazon.alexa.api.UserPerceivedLatencyData userPerceivedLatencyData) {
        this.compatListener.onLatencyData(str, userPerceivedLatencyData != null ? UserPerceivedLatencyData.Companion.convertToCompat(userPerceivedLatencyData) : null);
    }
}
