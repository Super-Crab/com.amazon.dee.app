package com.amazon.alexa.accessory.speechapi.events;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AccessoryStateReportGenerator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator;", "", "getAccessoryStateReport", "", "accessToken", "", "callback", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator$StateReportCallback;", "StateReportCallback", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AccessoryStateReportGenerator {

    /* compiled from: AccessoryStateReportGenerator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator$StateReportCallback;", "", "onError", "", "throwable", "", "onResult", "token", "", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface StateReportCallback {
        void onError(@NotNull Throwable th);

        void onResult(@NotNull String str);
    }

    void getAccessoryStateReport(@NotNull String str, @NotNull StateReportCallback stateReportCallback);
}
