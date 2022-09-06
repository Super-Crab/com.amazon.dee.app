package com.amazon.alexa.handsfree.settings.voxsettings;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.api.AlexaAudioProviderConnection;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoAlexaAppSettingsMediatorComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class VoxSettingsJobIntentService extends SafeDequeueJobIntentService {
    static final int JOB_ID = 30033;
    public static final String REQUEST_GET = "com.amazon.alexa.handsfree.voxsettings.ACTION_GET";
    public static final String REQUEST_SET = "com.amazon.alexa.handsfree.voxsettings.ACTION_SET";
    private static final String TAG = "VoxSettingsService";
    private static final int TIMEOUT_SECS = 5;
    @VisibleForTesting
    static final String VOX_SETTINGS_REQUEST_SUCCESS = "VoxSettingsRequestSuccess";
    private AMPDInformationProvider mAMPDInformationProvider;
    private Supplier<AlexaAudioProviderConnection> mAlexaAudioProviderConnectionSupplier;
    private HandsFreeUserIdentity mHandsFreeUser;
    @Inject
    HandsFreeUserIdentityProvider mHandsFreeUserIdentityProvider;
    @Inject
    Initializer mInitializer;
    private MetricsBuilderProvider mMetricsBuilderProvider;
    private SharedPreferences mSharedPreferences;
    private VoxSettingsSetRequestRetryPolicy mVoxSettingsSetRequestRetryPolicy;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public static class ConnectionListener implements AlexaAudioProviderConnection.ConnectionListener {
        private final boolean mBlockSensitive;
        private final VoxSettingsRequestHandler mRequestHandler;
        private final CompletableFuture<String> mSuccessFuture;
        private final CompletableFuture<Boolean> mSuccessFutureLegacy;

        ConnectionListener(CompletableFuture<String> completableFuture, CompletableFuture<Boolean> completableFuture2, @NonNull VoxSettingsRequestHandler voxSettingsRequestHandler, boolean z) {
            this.mSuccessFuture = completableFuture;
            this.mSuccessFutureLegacy = completableFuture2;
            this.mRequestHandler = voxSettingsRequestHandler;
            this.mBlockSensitive = z;
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            if (this.mBlockSensitive) {
                this.mSuccessFuture.complete(this.mRequestHandler.onConnectedToService());
            } else {
                this.mSuccessFutureLegacy.complete(Boolean.valueOf(this.mRequestHandler.onConnectedToServiceLegacy()));
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
            Exception exc = new Exception("Connection failed. Reason: " + alexaConnectingFailedReason);
            Log.w(VoxSettingsJobIntentService.TAG, exc);
            if (this.mBlockSensitive) {
                this.mSuccessFuture.completeExceptionally(exc);
            } else {
                this.mSuccessFutureLegacy.completeExceptionally(exc);
            }
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            Log.v(VoxSettingsJobIntentService.TAG, "Disconnected from service");
        }
    }

    public VoxSettingsJobIntentService() {
        this.mInitializer = InitializerProvider.getInitializer();
    }

    private boolean isBlockSensitiveRequest() {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUser;
        return handsFreeUserIdentity != null && handsFreeUserIdentity.hasComponent(HandsFreeComponent.HANDS_FREE_BLOCK_SENSITIVE_REQUEST) && this.mAMPDInformationProvider.isBSR();
    }

    private void performRequest(@NonNull Intent intent, @NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull VoxSettingsRequestHandler voxSettingsRequestHandler) {
        if (!voxSettingsRequestHandler.initialize(intent)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Performing request: ");
        outline107.append(intent.getAction());
        Log.v(TAG, outline107.toString());
        CompletableFuture completableFuture = new CompletableFuture();
        CompletableFuture completableFuture2 = new CompletableFuture();
        alexaAudioProviderConnection.disableConnectionFailureBroadcastIntent(true);
        if (isBlockSensitiveRequest()) {
            alexaAudioProviderConnection.registerListener(new ConnectionListener(completableFuture, null, voxSettingsRequestHandler, true));
        } else {
            alexaAudioProviderConnection.registerListener(new ConnectionListener(null, completableFuture2, voxSettingsRequestHandler, false));
        }
        try {
            try {
                alexaAudioProviderConnection.connect();
                if (isBlockSensitiveRequest()) {
                    voxSettingsRequestHandler.onRequestSucceeded((String) completableFuture.get(5L, TimeUnit.SECONDS));
                } else {
                    voxSettingsRequestHandler.onRequestSucceeded(((Boolean) completableFuture2.get(5L, TimeUnit.SECONDS)).booleanValue());
                }
                this.mMetricsBuilderProvider.newBuilder().withPercentileMetricSuccess(TAG, VOX_SETTINGS_REQUEST_SUCCESS).emit(this);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
                voxSettingsRequestHandler.onRequestFailed(isBlockSensitiveRequest());
                this.mMetricsBuilderProvider.newBuilder().withPercentileMetricFailure(TAG, VOX_SETTINGS_REQUEST_SUCCESS).emit(this);
            }
        } finally {
            alexaAudioProviderConnection.disconnect();
            alexaAudioProviderConnection.release();
        }
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        ((FalcoAlexaAppSettingsMediatorComponent) AhfComponentsProvider.getComponent(getApplicationContext(), FalcoAlexaAppSettingsMediatorComponent.class)).inject(this);
        this.mAlexaAudioProviderConnectionSupplier = new Supplier<AlexaAudioProviderConnection>() { // from class: com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsJobIntentService.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.function.Supplier
            /* renamed from: get */
            public AlexaAudioProviderConnection mo1546get() {
                return new AlexaAudioProviderConnection(VoxSettingsJobIntentService.this);
            }
        };
        this.mSharedPreferences = getSharedPreferences(VoxSettingsRequestHandler.SHARED_PREFS_FILENAME, 0);
        this.mVoxSettingsSetRequestRetryPolicy = new VoxSettingsSetRequestRetryPolicy(this);
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this);
        this.mHandsFreeUser = this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity();
        this.mAMPDInformationProvider = AMPDInformationProvider.getInstance(getApplicationContext());
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        if (intent.getAction() == null) {
            return;
        }
        AlexaAudioProviderConnection alexaAudioProviderConnection = this.mAlexaAudioProviderConnectionSupplier.get();
        String action = intent.getAction();
        char c = 65535;
        int hashCode = action.hashCode();
        if (hashCode != -1977619841) {
            if (hashCode == -1977608309 && action.equals(REQUEST_SET)) {
                c = 1;
            }
        } else if (action.equals(REQUEST_GET)) {
            c = 0;
        }
        if (c == 0) {
            performRequest(intent, alexaAudioProviderConnection, new VoxSettingsGetRequestHandler(alexaAudioProviderConnection, this.mSharedPreferences));
        } else if (c != 1) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown action ");
            outline107.append(intent.getAction());
            Log.w(TAG, outline107.toString());
        } else {
            performRequest(intent, alexaAudioProviderConnection, new VoxSettingsSetRequestHandler(alexaAudioProviderConnection, this.mSharedPreferences, this.mVoxSettingsSetRequestRetryPolicy));
        }
    }

    @VisibleForTesting
    VoxSettingsJobIntentService(@NonNull Supplier<AlexaAudioProviderConnection> supplier, @NonNull SharedPreferences sharedPreferences, @NonNull VoxSettingsSetRequestRetryPolicy voxSettingsSetRequestRetryPolicy, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull Initializer initializer) {
        this.mAlexaAudioProviderConnectionSupplier = supplier;
        this.mSharedPreferences = sharedPreferences;
        this.mVoxSettingsSetRequestRetryPolicy = voxSettingsSetRequestRetryPolicy;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mHandsFreeUser = handsFreeUserIdentity;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mInitializer = initializer;
    }
}
