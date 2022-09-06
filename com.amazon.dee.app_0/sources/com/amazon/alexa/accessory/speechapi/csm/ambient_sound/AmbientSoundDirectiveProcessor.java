package com.amazon.alexa.accessory.speechapi.csm.ambient_sound;

import android.content.Intent;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.speechapi.AmbientSoundDispatcher;
import com.amazon.alexa.accessory.speechapi.csm.metrics.MetricsConstants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: AmbientSoundDirectiveProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/ambient_sound/AmbientSoundDirectiveProcessor;", "", "()V", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AmbientSoundDirectiveProcessor {
    private static final String ALGORITHM_KEY = "algorithm";
    public static final Companion Companion = new Companion(null);
    private static final String DIRECTIVE_NAME = "SetAmbientSoundProcessingMode";
    private static final String DIRECTIVE_NAMESPACE = "Alexa.AudioSignal.ActiveNoiseControl";
    private static final String TAG = "AmbientSoundDirectiveProcessor:";

    /* compiled from: AmbientSoundDirectiveProcessor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/ambient_sound/AmbientSoundDirectiveProcessor$Companion;", "", "()V", "ALGORITHM_KEY", "", "DIRECTIVE_NAME", "DIRECTIVE_NAMESPACE", "TAG", "getAlgorithmFromPayload", "Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher$Algorithm;", "payload", "handleIntent", "", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "isValidDirectiveHeader", "", "directive", "Lcom/amazon/alexa/accessory/speechapi/csm/ambient_sound/CsmDirective;", "recordDirectiveProcessingMetric", "metricName", "recordDirectiveProcessingTime", "startTimeNanos", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        private final AmbientSoundDispatcher.Algorithm getAlgorithmFromPayload(String str) throws JSONException, IllegalArgumentException {
            String algorithm = new JSONObject(str).getString(AmbientSoundDirectiveProcessor.ALGORITHM_KEY);
            AmbientSoundDispatcher.Algorithm.Companion companion = AmbientSoundDispatcher.Algorithm.Companion;
            Intrinsics.checkExpressionValueIsNotNull(algorithm, "algorithm");
            AmbientSoundDispatcher.Algorithm fromString = companion.fromString(algorithm);
            if (fromString != null) {
                return fromString;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unexpected algorithm in payload: ", algorithm));
        }

        private final boolean isValidDirectiveHeader(CsmDirective csmDirective) {
            return Intrinsics.areEqual(AmbientSoundDirectiveProcessor.DIRECTIVE_NAME, csmDirective.getName()) && Intrinsics.areEqual(AmbientSoundDirectiveProcessor.DIRECTIVE_NAMESPACE, csmDirective.getNamespace());
        }

        private final void recordDirectiveProcessingMetric(String str) {
            GeneratedOutlineSupport1.outline171(str, "alexa_accessories", true, null);
        }

        private final void recordDirectiveProcessingTime(long j) {
            long convert = TimeUnit.MILLISECONDS.convert(System.nanoTime() - j, TimeUnit.NANOSECONDS);
            Logger.d("AmbientSoundDirectiveProcessor: Time taken to process the directive " + convert);
            AccessoryMetricsServiceHolder.getInstance().get().recordTime(MetricsConstants.AmbientSound.CSM_DIRECTIVE_PROCESSING_TIME, "alexa_accessories", convert, null);
        }

        public final void handleIntent(@NotNull Intent intent) {
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            long nanoTime = System.nanoTime();
            Preconditions.mainThread();
            recordDirectiveProcessingMetric(MetricsConstants.AmbientSound.CSM_DIRECTIVE_PROCESSING_START);
            try {
                if (!CsmAmbientSoundDispatcherInstance.INSTANCE.hasInstance()) {
                    Logger.e("AmbientSoundDirectiveProcessor: couldn't handle directive as dispatcher is null");
                    recordDirectiveProcessingMetric(MetricsConstants.AmbientSound.CSM_DIRECTIVE_PROCESSING_NO_INSTANCE);
                    return;
                }
                CsmDirective csmDirective = CsmDirectiveKt.toCsmDirective(intent);
                Logger.d("AmbientSoundDirectiveProcessor: received a directive: " + csmDirective);
                if (!isValidDirectiveHeader(csmDirective)) {
                    Logger.e("AmbientSoundDirectiveProcessor: couldn't handle directive due to malformed header");
                    recordDirectiveProcessingMetric(MetricsConstants.AmbientSound.CSM_DIRECTIVE_PROCESSING_INVALID_HEADER);
                    return;
                }
                boolean dispatch = CsmAmbientSoundDispatcherInstance.INSTANCE.get().dispatch(getAlgorithmFromPayload(csmDirective.getPayload()));
                Logger.d("AmbientSoundDirectiveProcessor: handled directive : " + dispatch);
                recordDirectiveProcessingMetric(MetricsConstants.AmbientSound.CSM_DIRECTIVE_PROCESSING);
                recordDirectiveProcessingTime(nanoTime);
            } catch (Exception e) {
                Logger.e("AmbientSoundDirectiveProcessor: failed to parse or handle directive from intent " + intent, e);
                recordDirectiveProcessingMetric(MetricsConstants.AmbientSound.CSM_DIRECTIVE_PROCESSING_ERROR);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
