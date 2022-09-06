package com.amazon.alexa.accessory.speechapi.voicesdk.capabilityagent;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.speechapi.events.AccessoryEventSender;
import com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.api.AlexaCapability;
import com.amazon.alexa.api.AlexaCapabilityAgentService;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ReportAccessoryStateCapabilityAgentService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 '2\u00020\u0001:\u0002&'B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u0012\u0010\u001a\u001a\u00020\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0018H\u0016J\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0010H\u0002J\u0018\u0010$\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u0014H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/capabilityagent/ReportAccessoryStateCapabilityAgentService;", "Lcom/amazon/alexa/api/AlexaCapabilityAgentService;", "()V", "accessoryEventSender", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryEventSender;", "accessoryStateReportCallback", "Lcom/amazon/alexa/accessory/speechapi/voicesdk/capabilityagent/ReportAccessoryStateCapabilityAgentService$AccessoryStateReportCallback;", CamerasRouteParameter.CAPABILITIES, "", "Lcom/amazon/alexa/api/AlexaCapability;", "featureChecker", "Lcom/amazon/alexa/accessory/utils/feature/FeatureChecker;", "reportGenerator", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator;", "getCapabilities", "getTokenFromPayload", "", "payload", "Lcom/amazon/alexa/api/AlexaPayload;", "handlePreConditions", "", "alexaDirective", "Lcom/amazon/alexa/api/AlexaDirective;", "initCapability", "", "initDependencies", "isValidHeader", "alexaHeader", "Lcom/amazon/alexa/api/AlexaHeader;", "onCreate", "process", "readInputJson", "resourceId", "", "recordCounterMetric", "metricName", "recordEventMetric", "eventOccurred", "AccessoryStateReportCallback", "Companion", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ReportAccessoryStateCapabilityAgentService extends AlexaCapabilityAgentService {
    private static final String CAPABILITY_FILE_NAME = "ama_io_components_capability";
    public static final Companion Companion = new Companion(null);
    private static final String DIRECTIVE_NAME = "ReportAccessoryState";
    private static final String DIRECTIVE_NAME_SPACE = "Alexa.IOComponents";
    private static final String EMPTY_STRING = "";
    private static final String FILE_TYPE = "raw";
    private static final String INTERFACE = "interface";
    private static final String TAG = "ReportAccessoryStateCapabilityAgentService:";
    private static final String TOKEN = "token";
    private static final String VERSION = "version";
    private AccessoryEventSender accessoryEventSender;
    private AccessoryStateReportCallback accessoryStateReportCallback;
    private Set<? extends AlexaCapability> capabilities;
    private FeatureChecker featureChecker;
    private AccessoryStateReportGenerator reportGenerator;

    /* compiled from: ReportAccessoryStateCapabilityAgentService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/capabilityagent/ReportAccessoryStateCapabilityAgentService$AccessoryStateReportCallback;", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator$StateReportCallback;", "(Lcom/amazon/alexa/accessory/speechapi/voicesdk/capabilityagent/ReportAccessoryStateCapabilityAgentService;)V", "onError", "", "throwable", "", "onResult", "token", "", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class AccessoryStateReportCallback implements AccessoryStateReportGenerator.StateReportCallback {
        public AccessoryStateReportCallback() {
        }

        @Override // com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator.StateReportCallback
        public void onError(@NotNull Throwable throwable) {
            Intrinsics.checkParameterIsNotNull(throwable, "throwable");
            Logger.e("ReportAccessoryStateCapabilityAgentService: AccessoryStateReportCallback: onError() callback received", throwable);
            ReportAccessoryStateCapabilityAgentService.this.recordEventMetric("ReportAccessoryStateGenerator", false);
        }

        @Override // com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator.StateReportCallback
        public void onResult(@NotNull String token) {
            Intrinsics.checkParameterIsNotNull(token, "token");
            Logger.d("ReportAccessoryStateCapabilityAgentService: AccessoryStateReportCallback: onResult() callback received with token= " + token);
            AccessoryEventSender accessoryEventSender = ReportAccessoryStateCapabilityAgentService.this.accessoryEventSender;
            if (accessoryEventSender != null) {
                accessoryEventSender.sendEvent(token, "UNKNOWN");
            }
            ReportAccessoryStateCapabilityAgentService.this.recordEventMetric("ReportAccessoryStateGenerator", true);
        }
    }

    /* compiled from: ReportAccessoryStateCapabilityAgentService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/capabilityagent/ReportAccessoryStateCapabilityAgentService$Companion;", "", "()V", "CAPABILITY_FILE_NAME", "", "DIRECTIVE_NAME", "DIRECTIVE_NAME_SPACE", "EMPTY_STRING", "FILE_TYPE", "INTERFACE", "TAG", "TOKEN", "VERSION", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final String getTokenFromPayload(AlexaPayload alexaPayload) {
        if (alexaPayload != null) {
            String token = new JSONObject(alexaPayload.getPayload()).getString("token");
            Intrinsics.checkExpressionValueIsNotNull(token, "token");
            return token;
        }
        throw new JSONException("AlexaPayload is null");
    }

    private final boolean handlePreConditions(AlexaDirective alexaDirective) {
        if (this.reportGenerator == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReportAccessoryStateCapabilityAgentService: couldn't process directive ");
            outline107.append(alexaDirective.getName());
            outline107.append(" since reportGenerator is null");
            Logger.e(outline107.toString());
            recordCounterMetric("ReportAccessoryStateDirectiveProcessing:reportGeneratorNull");
            return false;
        } else if (this.accessoryEventSender == null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("ReportAccessoryStateCapabilityAgentService: couldn't process directive ");
            outline1072.append(alexaDirective.getName());
            outline1072.append(" since accessoryEventSender is null");
            Logger.e(outline1072.toString());
            recordCounterMetric("ReportAccessoryStateDirectiveProcessing:accessoryEventSenderNull");
            return false;
        } else if (this.featureChecker == null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ReportAccessoryStateCapabilityAgentService: couldn't process directive ");
            outline1073.append(alexaDirective.getName());
            outline1073.append(" since featureChecker is null");
            Logger.e(outline1073.toString());
            recordCounterMetric("ReportAccessoryStateDirectiveProcessing:featureCheckerNull");
            return false;
        } else if (isValidHeader(alexaDirective.getAlexaHeader())) {
            return true;
        } else {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ReportAccessoryStateCapabilityAgentService: couldn't process directive ");
            outline1074.append(alexaDirective.getName());
            outline1074.append(" due to malformed header");
            Logger.e(outline1074.toString());
            recordCounterMetric("ReportAccessoryStateDirectiveProcessing:invalidHeader");
            return false;
        }
    }

    private final void initCapability() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReportAccessoryStateCapabilityAgentService: initCapability packageName: ");
        outline107.append(getPackageName());
        Logger.d(outline107.toString());
        int identifier = getResources().getIdentifier(CAPABILITY_FILE_NAME, FILE_TYPE, getPackageName());
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            JSONArray jSONArray = new JSONArray(readInputJson(identifier));
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("interface");
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(INTERFACE)");
                String string2 = jSONObject.getString("version");
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(VERSION)");
                Logger.d("ReportAccessoryStateCapabilityAgentService: AlexaCapability: " + string + Chars.SPACE + string2);
                AlexaCapability create = AlexaCapability.create(string, string2);
                Intrinsics.checkExpressionValueIsNotNull(create, "AlexaCapability.create(interfaceName, version)");
                linkedHashSet.add(create);
            }
        } catch (JSONException e) {
            Logger.e("ReportAccessoryStateCapabilityAgentService: exception parsing JSON", e);
            recordCounterMetric("ReportAccessoryStateCapabilityInitialization:parseError");
        }
        this.capabilities = linkedHashSet;
    }

    private final void initDependencies() {
        this.accessoryStateReportCallback = new AccessoryStateReportCallback();
        if (VoxReportAccessoryStateDependencyHolder.INSTANCE.hasAccessoryStateReportGenerator()) {
            this.reportGenerator = VoxReportAccessoryStateDependencyHolder.INSTANCE.getAccessoryStateReportGenerator();
        }
        if (VoxReportAccessoryStateDependencyHolder.INSTANCE.hasAccessoryEventSender()) {
            this.accessoryEventSender = VoxReportAccessoryStateDependencyHolder.INSTANCE.getAccessoryEventSender();
        }
        if (VoxReportAccessoryStateDependencyHolder.INSTANCE.hasFeatureChecker()) {
            this.featureChecker = VoxReportAccessoryStateDependencyHolder.INSTANCE.getFeatureChecker();
        }
    }

    private final boolean isValidHeader(AlexaHeader alexaHeader) {
        return alexaHeader != null && Intrinsics.areEqual(DIRECTIVE_NAME, alexaHeader.getName()) && Intrinsics.areEqual("Alexa.IOComponents", alexaHeader.getNamespace());
    }

    private final String readInputJson(int i) {
        Logger.d("ReportAccessoryStateCapabilityAgentService: reading JSON with resourceId: " + i);
        try {
            InputStream openRawResource = getResources().openRawResource(i);
            Intrinsics.checkExpressionValueIsNotNull(openRawResource, "resources.openRawResource(resourceId)");
            InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            String readText = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            return readText;
        } catch (Exception e) {
            Logger.e("ReportAccessoryStateCapabilityAgentService: exception reading capability data:", e);
            recordCounterMetric("ReportAccessoryStateCapabilityInitialization:readError");
            return "";
        }
    }

    private final void recordCounterMetric(String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str, "alexa_accessories", 1.0d, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordEventMetric(String str, boolean z) {
        GeneratedOutlineSupport1.outline171(str, "alexa_accessories", z, null);
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    @NotNull
    public Set<AlexaCapability> getCapabilities() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReportAccessoryStateCapabilityAgentService: getCapabilities: ");
        Set<? extends AlexaCapability> set = this.capabilities;
        if (set == null) {
            Intrinsics.throwUninitializedPropertyAccessException(CamerasRouteParameter.CAPABILITIES);
        }
        outline107.append(set.size());
        Logger.d(outline107.toString());
        Set set2 = this.capabilities;
        if (set2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(CamerasRouteParameter.CAPABILITIES);
        }
        return set2;
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        Logger.d("ReportAccessoryStateCapabilityAgentService: onCreate");
        initDependencies();
        initCapability();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public boolean process(@NotNull AlexaDirective alexaDirective) {
        AccessoryStateReportGenerator accessoryStateReportGenerator;
        Intrinsics.checkParameterIsNotNull(alexaDirective, "alexaDirective");
        Preconditions.notNull(alexaDirective, "alexaDirective");
        Logger.d("ReportAccessoryStateCapabilityAgentService: processing directive: NameSpace: " + alexaDirective.getNamespace() + " Name: " + alexaDirective.getName());
        if (!handlePreConditions(alexaDirective)) {
            recordEventMetric("ReportAccessoryStateDirective", false);
            return false;
        }
        FeatureChecker featureChecker = this.featureChecker;
        if (featureChecker == null) {
            Intrinsics.throwNpe();
        }
        if (!featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_ANDROID_ACCESSORY_STATE_REPORT)) {
            Logger.d("ReportAccessoryStateCapabilityAgentService: AccessoryStateReport weblab is not enabled.");
            return true;
        }
        try {
            String tokenFromPayload = getTokenFromPayload(alexaDirective.getAlexaPayload());
            AccessoryStateReportCallback accessoryStateReportCallback = this.accessoryStateReportCallback;
            if (accessoryStateReportCallback != null && (accessoryStateReportGenerator = this.reportGenerator) != null) {
                accessoryStateReportGenerator.getAccessoryStateReport(tokenFromPayload, accessoryStateReportCallback);
            }
            recordEventMetric("ReportAccessoryStateDirective", true);
            return true;
        } catch (Exception e) {
            Logger.e("ReportAccessoryStateCapabilityAgentService: Error while processing directive", e);
            recordEventMetric("ReportAccessoryStateDirective", false);
            return false;
        }
    }
}
