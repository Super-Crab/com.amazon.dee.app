package com.amazon.alexa.accessory.speechapi.csm;

import amazon.speech.model.AlexaStateHelper;
import android.content.Context;
import android.provider.Settings;
import com.amazon.alexa.accessory.internal.util.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaAvailabilityHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/AlexaAvailabilityHelper;", "", "()V", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaAvailabilityHelper {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AlexaAvailabilityHelper:";

    /* compiled from: AlexaAvailabilityHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/AlexaAvailabilityHelper$Companion;", "", "()V", "TAG", "", "getAlexaAvailabilityValue", "", "context", "Landroid/content/Context;", "getAlexaAvailabilityValue$AlexaAccessoryAndroid_speech_api_csm_release", "isAlexaUnavailableDueToUnsupportedMarketPlace", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public final int getAlexaAvailabilityValue$AlexaAccessoryAndroid_speech_api_csm_release(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return Settings.Secure.getInt(context.getContentResolver(), AlexaStateHelper.ALEXA_AVAILABILITY, 0);
        }

        public final boolean isAlexaUnavailableDueToUnsupportedMarketPlace(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            int alexaAvailabilityValue$AlexaAccessoryAndroid_speech_api_csm_release = getAlexaAvailabilityValue$AlexaAccessoryAndroid_speech_api_csm_release(context);
            Logger.d("AlexaAvailabilityHelper: Value for ALEXA_AVAILABILITY is " + alexaAvailabilityValue$AlexaAccessoryAndroid_speech_api_csm_release);
            return (alexaAvailabilityValue$AlexaAccessoryAndroid_speech_api_csm_release & 8) == 8;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
