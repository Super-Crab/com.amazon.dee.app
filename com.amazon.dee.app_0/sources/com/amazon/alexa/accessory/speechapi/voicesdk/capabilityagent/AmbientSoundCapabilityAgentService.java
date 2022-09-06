package com.amazon.alexa.accessory.speechapi.voicesdk.capabilityagent;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.speechapi.AmbientSoundDispatcher;
import com.amazon.alexa.accessory.speechapi.voicesdk.ambient_sound.VoxAmbientSoundDispatcherInstance;
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
/* compiled from: AmbientSoundCapabilityAgentService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0010H\u0002J\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u0018H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/capabilityagent/AmbientSoundCapabilityAgentService;", "Lcom/amazon/alexa/api/AlexaCapabilityAgentService;", "()V", "ambientSoundDispatcher", "Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher;", CamerasRouteParameter.CAPABILITIES, "", "Lcom/amazon/alexa/api/AlexaCapability;", "getAlgorithmFromPayload", "Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher$Algorithm;", "payload", "Lcom/amazon/alexa/api/AlexaPayload;", "getCapabilities", "init", "", "isValidHeader", "", "alexaHeader", "Lcom/amazon/alexa/api/AlexaHeader;", "onCreate", "process", "alexaDirective", "Lcom/amazon/alexa/api/AlexaDirective;", "readInputJson", "", "resourceId", "", "recordAmbientSoundProcessing", "metricName", "success", "recordAmbientSoundProcessingError", "Companion", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AmbientSoundCapabilityAgentService extends AlexaCapabilityAgentService {
    private static final String ALGORITHM = "algorithm";
    private static final String CAPABILITY_JSON_FILE_NAME = "ambient_sound_capability";
    public static final Companion Companion = new Companion(null);
    private static final String DIRECTIVE_NAME = "SetAmbientSoundProcessingMode";
    private static final String DIRECTIVE_NAME_SPACE = "Alexa.AudioSignal.ActiveNoiseControl";
    private static final String EMPTY_STRING = "";
    private static final String FILE_TYPE = "raw";
    private static final String INTERFACE = "interface";
    private static final String TAG = "speechapi.AmbientSoundCapabilityAgentService:";
    private static final String VERSION = "version";
    private AmbientSoundDispatcher ambientSoundDispatcher;
    private Set<? extends AlexaCapability> capabilities;

    /* compiled from: AmbientSoundCapabilityAgentService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/capabilityagent/AmbientSoundCapabilityAgentService$Companion;", "", "()V", "ALGORITHM", "", "CAPABILITY_JSON_FILE_NAME", "DIRECTIVE_NAME", "DIRECTIVE_NAME_SPACE", "EMPTY_STRING", "FILE_TYPE", "INTERFACE", "TAG", "VERSION", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final AmbientSoundDispatcher.Algorithm getAlgorithmFromPayload(AlexaPayload alexaPayload) throws JSONException, IllegalArgumentException {
        if (alexaPayload != null) {
            String algorithm = new JSONObject(alexaPayload.getPayload()).getString(ALGORITHM);
            AmbientSoundDispatcher.Algorithm.Companion companion = AmbientSoundDispatcher.Algorithm.Companion;
            Intrinsics.checkExpressionValueIsNotNull(algorithm, "algorithm");
            AmbientSoundDispatcher.Algorithm fromString = companion.fromString(algorithm);
            if (fromString == null) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unexpected algorithm in payload: ", algorithm));
            }
            return fromString;
        }
        throw new JSONException("AlexaPayload is null");
    }

    private final void init() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("speechapi.AmbientSoundCapabilityAgentService: createCapability packageName: ");
        outline107.append(getPackageName());
        Logger.d(outline107.toString());
        int identifier = getResources().getIdentifier(CAPABILITY_JSON_FILE_NAME, FILE_TYPE, getPackageName());
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
                Logger.d("speechapi.AmbientSoundCapabilityAgentService: AlexaCapability: " + string + Chars.SPACE + string2);
                AlexaCapability create = AlexaCapability.create(string, string2);
                Intrinsics.checkExpressionValueIsNotNull(create, "AlexaCapability.create(interfaceName, version)");
                linkedHashSet.add(create);
            }
        } catch (JSONException e) {
            Logger.e("speechapi.AmbientSoundCapabilityAgentService: exception parsing JSON", e);
        }
        this.capabilities = linkedHashSet;
    }

    private final boolean isValidHeader(AlexaHeader alexaHeader) {
        return alexaHeader != null && Intrinsics.areEqual(DIRECTIVE_NAME, alexaHeader.getName()) && Intrinsics.areEqual(DIRECTIVE_NAME_SPACE, alexaHeader.getNamespace());
    }

    private final String readInputJson(int i) {
        Logger.d("speechapi.AmbientSoundCapabilityAgentService: reading JSON with resourceId: " + i);
        try {
            InputStream openRawResource = getResources().openRawResource(i);
            Intrinsics.checkExpressionValueIsNotNull(openRawResource, "resources.openRawResource(resourceId)");
            InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            String readText = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            return readText;
        } catch (Exception e) {
            Logger.e("speechapi.AmbientSoundCapabilityAgentService: exception reading  device_capabilities data:", e);
            return "";
        }
    }

    private final void recordAmbientSoundProcessing(String str, boolean z) {
        GeneratedOutlineSupport1.outline171(str, "alexa_accessories", z, null);
    }

    private final void recordAmbientSoundProcessingError(String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str, "alexa_accessories", 1.0d, null);
    }

    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    @NotNull
    public Set<AlexaCapability> getCapabilities() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("speechapi.AmbientSoundCapabilityAgentService: getCapabilities: ");
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
        Logger.d("speechapi.AmbientSoundCapabilityAgentService: onCreate");
        if (VoxAmbientSoundDispatcherInstance.INSTANCE.hasInstance()) {
            this.ambientSoundDispatcher = VoxAmbientSoundDispatcherInstance.INSTANCE.get();
        }
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.AlexaCapabilityAgentService
    public boolean process(@NotNull AlexaDirective alexaDirective) {
        Intrinsics.checkParameterIsNotNull(alexaDirective, "alexaDirective");
        Preconditions.notNull(alexaDirective, "alexaDirective");
        Logger.d("speechapi.AmbientSoundCapabilityAgentService: processing directive: NameSpace: " + alexaDirective.getNamespace() + " Name: " + alexaDirective.getName());
        if (this.ambientSoundDispatcher == null) {
            Logger.e("speechapi.AmbientSoundCapabilityAgentService: couldn't handle directive as dispatcher is null");
            recordAmbientSoundProcessingError("VoxAmbientSoundProcessing:dispatcherNull");
            return false;
        }
        Logger.d("speechapi.AmbientSoundCapabilityAgentService: received a directive: " + alexaDirective);
        try {
            if (!isValidHeader(alexaDirective.getAlexaHeader())) {
                Logger.d("speechapi.AmbientSoundCapabilityAgentService: couldn't handle directive due to malformed header");
                recordAmbientSoundProcessingError("VoxAmbientSoundProcessing:invalidHeader");
                return false;
            }
            AmbientSoundDispatcher.Algorithm algorithmFromPayload = getAlgorithmFromPayload(alexaDirective.getAlexaPayload());
            AmbientSoundDispatcher ambientSoundDispatcher = this.ambientSoundDispatcher;
            if (ambientSoundDispatcher == null) {
                Intrinsics.throwNpe();
            }
            boolean dispatch = ambientSoundDispatcher.dispatch(algorithmFromPayload);
            Logger.d("speechapi.AmbientSoundCapabilityAgentService: handled directive: " + dispatch);
            recordAmbientSoundProcessing("VoxAmbientSoundProcessing:success", dispatch);
            return dispatch;
        } catch (Exception e) {
            Logger.e("speechapi.AmbientSoundCapabilityAgentService: failed to handle alexaDirective " + alexaDirective, e);
            recordAmbientSoundProcessingError("VoxAmbientSoundProcessing:failure");
            return false;
        } finally {
            stopSelf();
        }
    }
}
