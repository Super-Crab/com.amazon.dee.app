package com.amazon.alexa.handsfree.settings.event;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.metrics.factories.HandsFreeSetupMetricData;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatus;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatusManager;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.SettingsModule;
import com.amazon.alexa.handsfree.settings.contract.SettingsSetupFlowContract;
import com.amazon.alexa.handsfree.settings.contract.SetupFlow;
import com.amazon.alexa.handsfree.settings.metrics.HandsFreeSetupMetricMetadata;
import com.amazon.alexa.handsfree.settings.metrics.MetricType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes8.dex */
public class EventSubscriber {
    @VisibleForTesting
    static final String CREATE_PROFILE_FILTER_NAME = "ampd:1psv:createProfile";
    @VisibleForTesting
    static final String DELETE_PROFILE_FILTER_NAME = "ampd:1psv:deleteProfile";
    @VisibleForTesting
    static final String DELETE_PROFILE_FROM_SETTINGS_PAYLOAD = "deleteProfile";
    @VisibleForTesting
    static final String EVENT_SIGNOUT_MESSAGE = "signout";
    private static final String TAG = "EventSubscriber";
    private final Context mContext;
    private final SimpleMultiFilterSubscriber mDeleteProfileSubscriber;
    private final EnrollmentStatusManager mEnrollmentStatusManager;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private EventBus mEventBus;
    private final MetricsBuilderProvider mMetricsBuilderProvider;
    private SettingsSetupFlowContract mSettingsSetupFlowContract;

    public EventSubscriber(@NonNull Context context) {
        this(context, MetricsBuilderProvider.getInstance(context), new SimpleMultiFilterSubscriber(), null, null, EnrollmentStatusManager.getInstance(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean is1PSVDecoupling() {
        return this.mEnrollmentTypeResolverLazy.mo358get().getEnrollmentType() == EnrollmentType._1PSV_DECOUPLED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean is3PSV() {
        return this.mEnrollmentTypeResolverLazy.mo358get().getSpeakerVerificationEnrollmentType() == EnrollmentType._3PSV;
    }

    @VisibleForTesting
    UVRConnector getUVRConnector() {
        return UVRModule.INSTANCE.getUVRContract().getUVRConnector();
    }

    @VisibleForTesting
    UVRVendorSettings getUVRVendorSettings() {
        return UVRModule.INSTANCE.getUVRContract().getVendorSettings();
    }

    @VisibleForTesting
    void setAisFlowFlag() {
        this.mContext.getSharedPreferences(SettingsSetupFlowContract.PREFERENCE_FILE_NAME, 0).edit().putBoolean(SettingsSetupFlowContract.AIS_FLOW_ONGOING, true).apply();
    }

    public void subscribe() {
        this.mEventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        if (this.mEventBus == null) {
            return;
        }
        this.mSettingsSetupFlowContract = SettingsModule.INSTANCE.getSetupFlowContract();
        subscribeDeleteVoiceProfile();
        subscribeCreateVoiceProfile();
    }

    @VisibleForTesting
    void subscribeCreateVoiceProfile() {
        this.mDeleteProfileSubscriber.subscribeFilter(new EventTypeMessageFilter(CREATE_PROFILE_FILTER_NAME), new MessageHandler() { // from class: com.amazon.alexa.handsfree.settings.event.EventSubscriber.2
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public void handle(@NonNull Message message) {
                if (EventSubscriber.this.is3PSV()) {
                    return;
                }
                HandsFreeSetupMetricData handsFreeSetupMetricData = new HandsFreeSetupMetricData(HandsFreeSetupMetricMetadata.ActionType.INTENTION, HandsFreeSetupMetricMetadata.EventType.CLICK, HandsFreeSetupMetricMetadata.Component.SETTINGS_MENU, HandsFreeSetupMetricMetadata.PageType.PROFILE_SETTINGS, HandsFreeSetupMetricMetadata.SubPageType.CREATE_BUTTON);
                MetricsBuilder newBuilder = EventSubscriber.this.mMetricsBuilderProvider.newBuilder();
                newBuilder.withHandsFreeSetupMetric(EventSubscriber.TAG, handsFreeSetupMetricData);
                newBuilder.emit(EventSubscriber.this.mContext);
                if (EventSubscriber.this.mSettingsSetupFlowContract == null || !EventSubscriber.this.mSettingsSetupFlowContract.hasPendingSetup(EventSubscriber.this.mContext, SetupFlow.AIS)) {
                    return;
                }
                EventSubscriber.this.setAisFlowFlag();
                EventSubscriber.this.mContext.startActivity(EventSubscriber.this.mSettingsSetupFlowContract.getPendingSetupIntent(EventSubscriber.this.mContext, SetupFlow.AIS));
            }
        });
        this.mEventBus.subscribe(this.mDeleteProfileSubscriber);
    }

    @VisibleForTesting
    void subscribeDeleteVoiceProfile() {
        this.mDeleteProfileSubscriber.subscribeFilter(new EventTypeMessageFilter(DELETE_PROFILE_FILTER_NAME), new MessageHandler() { // from class: com.amazon.alexa.handsfree.settings.event.EventSubscriber.1
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public void handle(@NonNull final Message message) {
                boolean equals = message.getPayloadAsString().equals(EventSubscriber.EVENT_SIGNOUT_MESSAGE);
                if (EventSubscriber.this.is3PSV() || (EventSubscriber.this.is1PSVDecoupling() && !equals)) {
                    Log.i(EventSubscriber.TAG, "removeUVRModel is3PSV or is1PSVDecoupling not signout");
                    return;
                }
                ResponseCallback responseCallback = new ResponseCallback() { // from class: com.amazon.alexa.handsfree.settings.event.EventSubscriber.1.1
                    @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                    public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                        String str = EventSubscriber.TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onError: delete local voice profile from vox failed.");
                        outline107.append(callbackErrorMetadata.getErrorMessage());
                        Log.e(str, outline107.toString());
                        MetricsBuilder newBuilder = EventSubscriber.this.mMetricsBuilderProvider.newBuilder();
                        newBuilder.withPercentileMetricFailure(EventSubscriber.TAG, String.format(MetricType.DELETE_LOCAL_VOICE_PROFILE_FROM_VOX_SUCCESS.getValue(), message.getPayloadAsString()));
                        newBuilder.emit(EventSubscriber.this.mContext);
                    }

                    @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                    public void onSuccess() {
                        Log.i(EventSubscriber.TAG, "removeUVRModel onSuccess");
                        EventSubscriber.this.mEnrollmentStatusManager.setEnrollmentStatus(EnrollmentStatus.SETUP_NOT_SET);
                        ((EnrollmentTypeResolver) EventSubscriber.this.mEnrollmentTypeResolverLazy.mo358get()).check1PSVDecoupledState();
                        MetricsBuilder newBuilder = EventSubscriber.this.mMetricsBuilderProvider.newBuilder();
                        newBuilder.withPercentileMetricSuccess(EventSubscriber.TAG, String.format(MetricType.DELETE_LOCAL_VOICE_PROFILE_FROM_VOX_SUCCESS.getValue(), message.getPayloadAsString()));
                        newBuilder.emit(EventSubscriber.this.mContext);
                    }
                };
                EventSubscriber.this.getUVRConnector().startConnection(EventSubscriber.this.mContext, false);
                if (EventSubscriber.this.getUVRVendorSettings().isUVREnrolled(UserInfo.DEFAULT_USER)) {
                    Log.i(EventSubscriber.TAG, "removeUVRModel");
                    EventSubscriber.this.getUVRVendorSettings().removeUVRModel(UserInfo.DEFAULT_USER, responseCallback);
                }
                EventSubscriber.this.getUVRConnector().endConnection(EventSubscriber.this.mContext);
            }
        });
        this.mEventBus.subscribe(this.mDeleteProfileSubscriber);
    }

    @VisibleForTesting
    EventSubscriber(@NonNull Context context, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull SimpleMultiFilterSubscriber simpleMultiFilterSubscriber, @Nullable EventBus eventBus, @Nullable SettingsSetupFlowContract settingsSetupFlowContract, @NonNull EnrollmentStatusManager enrollmentStatusManager, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mContext = context;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mDeleteProfileSubscriber = simpleMultiFilterSubscriber;
        this.mEventBus = eventBus;
        this.mSettingsSetupFlowContract = settingsSetupFlowContract;
        this.mEnrollmentStatusManager = enrollmentStatusManager;
        this.mEnrollmentTypeResolverLazy = lazy;
    }
}
