package com.amazon.alexa.accessory.speechapi.csm.reportstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.speechapi.csm.ambient_sound.CsmDirective;
import com.amazon.alexa.accessory.speechapi.csm.ambient_sound.CsmDirectiveKt;
import com.amazon.alexa.accessory.speechapi.csm.metrics.MetricsConstants;
import com.amazon.alexa.accessory.speechapi.events.AccessoryEventSender;
import com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.client.metrics.thirdparty.MetricsServiceConstants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* compiled from: CsmReportAccessoryStateDirectiveReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001c\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\fH\u0002J\u0018\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u000fH\u0002J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/reportstate/CsmReportAccessoryStateDirectiveReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "accessoryEventSender", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryEventSender;", "accessoryStateReportCallback", "Lcom/amazon/alexa/accessory/speechapi/csm/reportstate/CsmReportAccessoryStateDirectiveReceiver$AccessoryStateReportCallback;", "featureChecker", "Lcom/amazon/alexa/accessory/utils/feature/FeatureChecker;", "reportGenerator", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator;", "getTokenFromPayload", "", "payload", "handlePreConditions", "", "directive", "Lcom/amazon/alexa/accessory/speechapi/csm/ambient_sound/CsmDirective;", "isValidDirectiveHeader", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "recordCounterMetric", "metricName", "recordEventMetric", "eventOccurred", "recordException", "throwable", "", "AccessoryStateReportCallback", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmReportAccessoryStateDirectiveReceiver extends BroadcastReceiver {
    public static final Companion Companion = new Companion(null);
    private static final String DIRECTIVE_NAME = "ReportAccessoryState";
    private static final String DIRECTIVE_NAMESPACE = "Alexa.IOComponents";
    private static final String INTENT_ACTION = "com.amazon.speech.Alexa.IOComponents_ReportAccessoryState";
    private static final String TAG = "CsmReportAccessoryStateDirectiveReceiver:";
    private static final String TOKEN = "token";
    private AccessoryEventSender accessoryEventSender;
    private AccessoryStateReportCallback accessoryStateReportCallback = new AccessoryStateReportCallback();
    private FeatureChecker featureChecker;
    private AccessoryStateReportGenerator reportGenerator;

    /* compiled from: CsmReportAccessoryStateDirectiveReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/reportstate/CsmReportAccessoryStateDirectiveReceiver$AccessoryStateReportCallback;", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator$StateReportCallback;", "(Lcom/amazon/alexa/accessory/speechapi/csm/reportstate/CsmReportAccessoryStateDirectiveReceiver;)V", "onError", "", "throwable", "", "onResult", "token", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class AccessoryStateReportCallback implements AccessoryStateReportGenerator.StateReportCallback {
        public AccessoryStateReportCallback() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator.StateReportCallback
        public void onError(@NotNull Throwable throwable) {
            Intrinsics.checkParameterIsNotNull(throwable, "throwable");
            Logger.e("CsmReportAccessoryStateDirectiveReceiver: AccessoryStateReportCallback: onError() callback received", throwable);
            CsmReportAccessoryStateDirectiveReceiver.this.recordEventMetric("ReportAccessoryStateGenerator", false);
        }

        @Override // com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator.StateReportCallback
        public void onResult(@NotNull String token) {
            Intrinsics.checkParameterIsNotNull(token, "token");
            Logger.d("CsmReportAccessoryStateDirectiveReceiver: AccessoryStateReportCallback: onResult() callback received with token= " + token);
            AccessoryEventSender accessoryEventSender = CsmReportAccessoryStateDirectiveReceiver.this.accessoryEventSender;
            if (accessoryEventSender != null) {
                accessoryEventSender.sendEvent(token, "UNKNOWN");
            }
            CsmReportAccessoryStateDirectiveReceiver.this.recordEventMetric("ReportAccessoryStateGenerator", true);
        }
    }

    /* compiled from: CsmReportAccessoryStateDirectiveReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/reportstate/CsmReportAccessoryStateDirectiveReceiver$Companion;", "", "()V", "DIRECTIVE_NAME", "", "DIRECTIVE_NAMESPACE", MetricsServiceConstants.INTENT_ACTION, "TAG", "TOKEN", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CsmReportAccessoryStateDirectiveReceiver() {
        if (CsmReportAccessoryStateDependencyHolder.INSTANCE.hasAccessoryStateReportGenerator()) {
            this.reportGenerator = CsmReportAccessoryStateDependencyHolder.INSTANCE.getAccessoryStateReportGenerator();
        }
        if (CsmReportAccessoryStateDependencyHolder.INSTANCE.hasAccessoryEventSender()) {
            this.accessoryEventSender = CsmReportAccessoryStateDependencyHolder.INSTANCE.getAccessoryEventSender();
        }
        if (CsmReportAccessoryStateDependencyHolder.INSTANCE.hasFeatureChecker()) {
            this.featureChecker = CsmReportAccessoryStateDependencyHolder.INSTANCE.getFeatureChecker();
        }
    }

    private final String getTokenFromPayload(String str) {
        if (str == null) {
            Intrinsics.throwNpe();
        }
        String string = new JSONObject(str).getString("token");
        Intrinsics.checkExpressionValueIsNotNull(string, "payloadJson.getString(TOKEN)");
        return string;
    }

    private final boolean handlePreConditions(CsmDirective csmDirective) {
        if (this.reportGenerator == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmReportAccessoryStateDirectiveReceiver: couldn't process directive ");
            outline107.append(csmDirective.getName());
            outline107.append(" since reportGenerator is null");
            Logger.e(outline107.toString());
            recordCounterMetric("ReportAccessoryStateDirectiveProcessing:reportGeneratorNull");
            return false;
        } else if (this.accessoryEventSender == null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("CsmReportAccessoryStateDirectiveReceiver: couldn't process directive ");
            outline1072.append(csmDirective.getName());
            outline1072.append(" since accessoryEventSender is null");
            Logger.e(outline1072.toString());
            recordCounterMetric("ReportAccessoryStateDirectiveProcessing:accessoryEventSenderNull");
            return false;
        } else if (this.featureChecker == null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("CsmReportAccessoryStateDirectiveReceiver: couldn't process directive ");
            outline1073.append(csmDirective.getName());
            outline1073.append(" since featureChecker is null");
            Logger.e(outline1073.toString());
            recordCounterMetric("ReportAccessoryStateDirectiveProcessing:featureCheckerNull");
            return false;
        } else if (isValidDirectiveHeader(csmDirective)) {
            return true;
        } else {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("CsmReportAccessoryStateDirectiveReceiver: couldn't process directive ");
            outline1074.append(csmDirective.getName());
            outline1074.append(" due to malformed header");
            Logger.e(outline1074.toString());
            recordCounterMetric("ReportAccessoryStateDirectiveProcessing:invalidHeader");
            return false;
        }
    }

    private final boolean isValidDirectiveHeader(CsmDirective csmDirective) {
        return Intrinsics.areEqual(DIRECTIVE_NAME, csmDirective.getName()) && Intrinsics.areEqual("Alexa.IOComponents", csmDirective.getNamespace());
    }

    private final void recordCounterMetric(String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str, "alexa_accessories", 1.0d, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordEventMetric(String str, boolean z) {
        GeneratedOutlineSupport1.outline171(str, "alexa_accessories", z, null);
    }

    private final void recordException(Throwable th) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCriticalEvent(MetricsConstants.ReportState.REPORT_STATE_DIRECTIVE_PROCESSING_ERROR, "alexa_accessories", th.getClass().getSimpleName(), th);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        AccessoryStateReportGenerator accessoryStateReportGenerator;
        Logger.d("CsmReportAccessoryStateDirectiveReceiver: onReceive for intent: " + intent);
        if (!Intrinsics.areEqual(INTENT_ACTION, intent != null ? intent.getAction() : null)) {
            Logger.e("CsmReportAccessoryStateDirectiveReceiver: Unexpected intent received " + intent);
            recordEventMetric("ReportAccessoryStateDirective", false);
            return;
        }
        CsmDirective csmDirective = CsmDirectiveKt.toCsmDirective(intent);
        if (!handlePreConditions(csmDirective)) {
            recordEventMetric("ReportAccessoryStateDirective", false);
            return;
        }
        FeatureChecker featureChecker = this.featureChecker;
        if (featureChecker == null) {
            Intrinsics.throwNpe();
        }
        if (!featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_ANDROID_ACCESSORY_STATE_REPORT)) {
            Logger.d("CsmReportAccessoryStateDirectiveReceiver: AccessoryStateReport weblab is not enabled.");
            return;
        }
        try {
            String tokenFromPayload = getTokenFromPayload(csmDirective.getPayload());
            AccessoryStateReportCallback accessoryStateReportCallback = this.accessoryStateReportCallback;
            if (accessoryStateReportCallback != null && (accessoryStateReportGenerator = this.reportGenerator) != null) {
                accessoryStateReportGenerator.getAccessoryStateReport(tokenFromPayload, accessoryStateReportCallback);
            }
            recordEventMetric("ReportAccessoryStateDirective", true);
        } catch (Exception e) {
            Logger.e("CsmReportAccessoryStateDirectiveReceiver: Error while processing directive", e);
            recordEventMetric("ReportAccessoryStateDirective", false);
            recordException(e);
        }
    }
}
