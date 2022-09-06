package com.amazon.alexa.voice.tta.simba;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.voice.tta.dependencies.DependenciesModule;
import com.amazon.alexa.voice.tta.dependencies.DeviceInfo;
import com.amazon.alexa.voice.tta.dependencies.DeviceInformationProvider;
import com.amazon.deecomms.common.Constants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.android.gms.actions.SearchIntents;
import dagger.Component;
import java.net.SocketTimeoutException;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SimbaServiceClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ,2\u00020\u0001:\u0002,-B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010 \u001a\u00020\u0016H\u0002J\b\u0010!\u001a\u00020\u0016H\u0002J\b\u0010\"\u001a\u00020\u0016H\u0002J\b\u0010#\u001a\u00020\u0016H\u0016J\u0012\u0010$\u001a\u00020\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u000e\u0010%\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dJ\u0018\u0010&\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(H\u0002J \u0010)\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+2\u0006\u0010'\u001a\u00020(H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006."}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SimbaServiceClient;", "Landroid/app/IntentService;", "name", "", "(Ljava/lang/String;)V", "coralService", "Lcom/dee/app/http/CoralService;", "deviceInfo", "Lcom/amazon/alexa/voice/tta/dependencies/DeviceInfo;", "deviceInformationProvider", "Lcom/amazon/alexa/voice/tta/dependencies/DeviceInformationProvider;", "getDeviceInformationProvider", "()Lcom/amazon/alexa/voice/tta/dependencies/DeviceInformationProvider;", "setDeviceInformationProvider", "(Lcom/amazon/alexa/voice/tta/dependencies/DeviceInformationProvider;)V", "simbaClient", "Lcom/amazon/alexa/voice/tta/simba/SimbaClient;", "getSimbaClient", "()Lcom/amazon/alexa/voice/tta/simba/SimbaClient;", "setSimbaClient", "(Lcom/amazon/alexa/voice/tta/simba/SimbaClient;)V", "handleGetFrictivePrompts", "", "locale", "Ljava/util/Locale;", Constants.BUNDLE_RECEIVER_TAG, "Landroid/os/ResultReceiver;", "handleGetSearchRequest", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "handleGetSuggestionRequest", "handleUpdateInteraction", "initCoralService", "initDeviceInformation", "injectDependencies", "onCreate", "onHandleIntent", "onHandleWork", "sendError", "resultCode", "", "sendResult", "result", "Landroid/os/Bundle;", "Companion", "SimbaServiceComponent", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SimbaServiceClient extends IntentService {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SimbaServiceClient";
    private CoralService coralService;
    private DeviceInfo deviceInfo;
    @Inject
    @NotNull
    public DeviceInformationProvider deviceInformationProvider;
    @Inject
    @NotNull
    public SimbaClient simbaClient;

    /* compiled from: SimbaServiceClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJT\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004J&\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SimbaServiceClient$Companion;", "", "()V", "TAG", "", "fetchFricativePrompts", "", "context", "Landroid/content/Context;", Constants.BUNDLE_RECEIVER_TAG, "Landroid/os/ResultReceiver;", "locale", "Ljava/util/Locale;", "fetchSearchResults", SearchIntents.EXTRA_QUERY, "wasAvsResponseEmpty", "", "avsResponseToken", "promptId", "variant", "namespace", "fetchSuggestions", "updateInteraction", "resultId", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public final void fetchFricativePrompts(@NotNull Context context, @NotNull ResultReceiver receiver, @NotNull Locale locale) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(receiver, "receiver");
            Intrinsics.checkParameterIsNotNull(locale, "locale");
            Intent intent = new Intent(context, SimbaServiceClient.class);
            intent.putExtra("android.intent.extra.RESULT_RECEIVER", receiver);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SIMBA_ACTION, 4);
            intent.putExtra(SimbaServiceClientKt.EXTRA_REQUESTED_LOCALE, locale);
            context.startService(intent);
        }

        public final void fetchSearchResults(@NotNull Context context, @NotNull ResultReceiver receiver, @NotNull String query, @NotNull Locale locale, boolean z, @NotNull String avsResponseToken, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(receiver, "receiver");
            Intrinsics.checkParameterIsNotNull(query, "query");
            Intrinsics.checkParameterIsNotNull(locale, "locale");
            Intrinsics.checkParameterIsNotNull(avsResponseToken, "avsResponseToken");
            Intent intent = new Intent(context, SimbaServiceClient.class);
            intent.putExtra("android.intent.extra.RESULT_RECEIVER", receiver);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SIMBA_ACTION, 2);
            intent.putExtra(SimbaServiceClientKt.EXTRA_REQUESTED_LOCALE, locale);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SEARCH_QUERY, query);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_RESPONSE_EMPTY, z);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_RESPONSE_TOKEN, avsResponseToken);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_PROMPT_TD, str);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_VARIANT, str2);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_NAMESPACE, str3);
            context.startService(intent);
        }

        public final void fetchSuggestions(@NotNull Context context, @NotNull ResultReceiver receiver, @NotNull String query, @NotNull Locale locale) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(receiver, "receiver");
            Intrinsics.checkParameterIsNotNull(query, "query");
            Intrinsics.checkParameterIsNotNull(locale, "locale");
            Intent intent = new Intent(context, SimbaServiceClient.class);
            intent.putExtra("android.intent.extra.RESULT_RECEIVER", receiver);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SIMBA_ACTION, 1);
            intent.putExtra(SimbaServiceClientKt.EXTRA_REQUESTED_LOCALE, locale);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SUGGESTION_QUERY, query);
            context.startService(intent);
        }

        public final void updateInteraction(@NotNull Context context, @NotNull ResultReceiver receiver, @NotNull String resultId, @NotNull Locale locale) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(receiver, "receiver");
            Intrinsics.checkParameterIsNotNull(resultId, "resultId");
            Intrinsics.checkParameterIsNotNull(locale, "locale");
            Intent intent = new Intent(context, SimbaServiceClient.class);
            intent.putExtra("android.intent.extra.RESULT_RECEIVER", receiver);
            intent.putExtra(SimbaServiceClientKt.EXTRA_SIMBA_ACTION, 3);
            intent.putExtra(SimbaServiceClientKt.EXTRA_UPDATE_INTERACTION_ID, resultId);
            intent.putExtra(SimbaServiceClientKt.EXTRA_REQUESTED_LOCALE, locale);
            context.startService(intent);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: SimbaServiceClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SimbaServiceClient$SimbaServiceComponent;", "", "inject", "", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/alexa/voice/tta/simba/SimbaServiceClient;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    @Component(modules = {SimbaModule.class, DependenciesModule.class})
    @Singleton
    /* loaded from: classes11.dex */
    public interface SimbaServiceComponent {
        void inject(@Nullable SimbaServiceClient simbaServiceClient);
    }

    public SimbaServiceClient() {
        this(null, 1, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimbaServiceClient(@NotNull String name) {
        super(name);
        Intrinsics.checkParameterIsNotNull(name, "name");
    }

    private final void handleGetFrictivePrompts(Locale locale, ResultReceiver resultReceiver) {
        try {
            SimbaClient simbaClient = this.simbaClient;
            if (simbaClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("simbaClient");
            }
            FrictivePromptsSimbaResponse frictivePrompts = simbaClient.getFrictivePrompts(this.coralService, this.deviceInfo, locale);
            Bundle bundle = new Bundle();
            bundle.putParcelable(SimbaServiceClientKt.SIMBA_FRICATIVE_RESPONSE, frictivePrompts);
            sendResult(resultReceiver, bundle, 3);
        } catch (Exception unused) {
            sendError(resultReceiver, 1002);
        }
    }

    private final void handleGetSearchRequest(Intent intent, Locale locale, ResultReceiver resultReceiver) {
        String stringExtra = intent.getStringExtra(SimbaServiceClientKt.EXTRA_SEARCH_QUERY);
        boolean booleanExtra = intent.getBooleanExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_RESPONSE_EMPTY, false);
        String stringExtra2 = intent.getStringExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_RESPONSE_TOKEN);
        String stringExtra3 = intent.getStringExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_PROMPT_TD);
        String stringExtra4 = intent.getStringExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_VARIANT);
        String stringExtra5 = intent.getStringExtra(SimbaServiceClientKt.EXTRA_SEARCH_AVS_NAMESPACE);
        Bundle bundle = new Bundle();
        try {
            SimbaClient simbaClient = this.simbaClient;
            if (simbaClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("simbaClient");
            }
            CoralService coralService = this.coralService;
            DeviceInfo deviceInfo = this.deviceInfo;
            if (stringExtra == null) {
                stringExtra = "";
            }
            String str = stringExtra2 != null ? stringExtra2 : "";
            String str2 = stringExtra3 != null ? stringExtra3 : "";
            if (stringExtra5 == null) {
                stringExtra5 = "";
            }
            bundle.putParcelable(SimbaServiceClientKt.SIMBA_SEARCH_RESPONSE, simbaClient.getSearchResults(coralService, deviceInfo, stringExtra, locale, booleanExtra, str, str2, stringExtra4, stringExtra5));
            sendResult(resultReceiver, bundle, 2);
        } catch (Exception e) {
            if (e.getCause() instanceof SocketTimeoutException) {
                sendError(resultReceiver, 1003);
            } else {
                sendError(resultReceiver, 1000);
            }
        }
    }

    private final void handleGetSuggestionRequest(Intent intent, Locale locale, ResultReceiver resultReceiver) {
        String stringExtra = intent.getStringExtra(SimbaServiceClientKt.EXTRA_SUGGESTION_QUERY);
        Bundle bundle = new Bundle();
        try {
            SimbaClient simbaClient = this.simbaClient;
            if (simbaClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("simbaClient");
            }
            bundle.putParcelable(SimbaServiceClientKt.SIMBA_SUGGESTION_RESPONSE, simbaClient.getSuggestions(this.coralService, this.deviceInfo, stringExtra, locale));
            sendResult(resultReceiver, bundle, 1);
        } catch (Exception unused) {
            sendError(resultReceiver, 1001);
        }
    }

    private final void handleUpdateInteraction(Intent intent, Locale locale, ResultReceiver resultReceiver) {
        String stringExtra = intent.getStringExtra(SimbaServiceClientKt.EXTRA_UPDATE_INTERACTION_ID);
        SimbaClient simbaClient = this.simbaClient;
        if (simbaClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("simbaClient");
        }
        UpdateInteractionSimbaResponse updateInteraction = simbaClient.updateInteraction(this.coralService, this.deviceInfo, stringExtra, locale);
        Bundle bundle = new Bundle();
        bundle.putParcelable(SimbaServiceClientKt.SIMBA_INTERACTION_RESPONSE, updateInteraction);
        sendResult(resultReceiver, bundle, 4);
    }

    private final void initCoralService() {
        if (this.coralService == null) {
            this.coralService = (CoralService) GeneratedOutlineSupport1.outline21(CoralService.class);
        }
    }

    private final void initDeviceInformation() {
        if (this.deviceInfo != null) {
            return;
        }
        DeviceInformationProvider deviceInformationProvider = this.deviceInformationProvider;
        if (deviceInformationProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceInformationProvider");
        }
        this.deviceInfo = deviceInformationProvider.getDeviceInformation();
    }

    private final void injectDependencies() {
        DaggerSimbaServiceClient_SimbaServiceComponent.builder().dependenciesModule(new DependenciesModule()).simbaModule(new SimbaModule()).build().inject(this);
    }

    private final void sendError(ResultReceiver resultReceiver, int i) {
        Bundle bundle = new Bundle();
        bundle.putString(SimbaServiceClientKt.SIMBA_ERROR, SimbaServiceClientKt.SIMBA_ERROR);
        resultReceiver.send(i, bundle);
    }

    private final void sendResult(ResultReceiver resultReceiver, Bundle bundle, int i) {
        resultReceiver.send(i, bundle);
    }

    @NotNull
    public final DeviceInformationProvider getDeviceInformationProvider() {
        DeviceInformationProvider deviceInformationProvider = this.deviceInformationProvider;
        if (deviceInformationProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceInformationProvider");
        }
        return deviceInformationProvider;
    }

    @NotNull
    public final SimbaClient getSimbaClient() {
        SimbaClient simbaClient = this.simbaClient;
        if (simbaClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("simbaClient");
        }
        return simbaClient;
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
        injectDependencies();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            onHandleWork(intent);
        }
    }

    public final void onHandleWork(@NotNull Intent intent) {
        ResultReceiver resultReceiver;
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Log.i(TAG, "onHandleIntent: ");
        initCoralService();
        initDeviceInformation();
        if (this.coralService == null || this.deviceInfo == null || (resultReceiver = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER")) == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(resultReceiver, "intent.getParcelableExtr…ESULT_RECEIVER) ?: return");
        Locale locale = (Locale) intent.getSerializableExtra(SimbaServiceClientKt.EXTRA_REQUESTED_LOCALE);
        int intExtra = intent.getIntExtra(SimbaServiceClientKt.EXTRA_SIMBA_ACTION, 0);
        if (intExtra == 1) {
            handleGetSuggestionRequest(intent, locale, resultReceiver);
        } else if (intExtra == 2) {
            handleGetSearchRequest(intent, locale, resultReceiver);
        } else if (intExtra == 3) {
            handleUpdateInteraction(intent, locale, resultReceiver);
        } else if (intExtra == 4) {
            handleGetFrictivePrompts(locale, resultReceiver);
        } else {
            Log.w(TAG, "onHandleWork: Unknown action " + intExtra);
        }
    }

    public final void setDeviceInformationProvider(@NotNull DeviceInformationProvider deviceInformationProvider) {
        Intrinsics.checkParameterIsNotNull(deviceInformationProvider, "<set-?>");
        this.deviceInformationProvider = deviceInformationProvider;
    }

    public final void setSimbaClient(@NotNull SimbaClient simbaClient) {
        Intrinsics.checkParameterIsNotNull(simbaClient, "<set-?>");
        this.simbaClient = simbaClient;
    }

    public /* synthetic */ SimbaServiceClient(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? TAG : str);
    }
}
