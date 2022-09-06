package com.amazon.alexa.handsfree.metrics.amok;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.metricsprovider.AlexaMetricsContract;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class VoiceAppMetricsInitializer {
    private static final String TAG = "VoiceAppMetricsInitializer";
    @VisibleForTesting
    static final String VOICE_APP_METRICS = "ALEXA_HANDS_FREE_METRICS_PROVIDER";
    private final FeatureServiceV2 mFeatureServiceV2;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public VoiceAppMetricsInitializer(@Nullable FeatureServiceV2 featureServiceV2) {
        this.mFeatureServiceV2 = featureServiceV2;
    }

    @VisibleForTesting
    void checkUserIdentityHasFeature(@NonNull Context context, @NonNull AlexaMetricsContentObserver alexaMetricsContentObserver) {
        FeatureServiceV2 featureServiceV2 = this.mFeatureServiceV2;
        if (featureServiceV2 != null && featureServiceV2.hasAccess(VOICE_APP_METRICS, false)) {
            try {
                context.getContentResolver().registerContentObserver(AlexaMetricsContract.AlexaMetricsTable.CONTENT_URI, true, alexaMetricsContentObserver);
                Log.d(TAG, "registering the metrics content observer.");
            } catch (Exception unused) {
                Log.w(TAG, "no partner app with metrics provider");
            }
        } else {
            context.getContentResolver().unregisterContentObserver(alexaMetricsContentObserver);
            Log.d(TAG, "unregistering the metrics content observer.");
        }
        Log.d(TAG, "call: partner app with metrics content provider is available");
    }

    public void initialize(@NonNull Context context) {
        initialize(context, AMPDInformationProvider.getInstance(context));
    }

    @NonNull
    @VisibleForTesting
    EventBus provideEventBus() {
        return (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    }

    @VisibleForTesting
    void registerContentObserver(@NonNull final Context context, @NonNull final AlexaMetricsContentObserver alexaMetricsContentObserver) {
        try {
            if (this.mFeatureServiceV2 != null && this.mFeatureServiceV2.hasAccess(VOICE_APP_METRICS, false)) {
                context.getContentResolver().registerContentObserver(AlexaMetricsContract.AlexaMetricsTable.CONTENT_URI, true, alexaMetricsContentObserver);
                Log.d(TAG, "initialize: registering the metrics content observer.");
            } else {
                context.getContentResolver().unregisterContentObserver(alexaMetricsContentObserver);
                Log.d(TAG, "initialize: unregistering the metrics content observer.");
            }
            Log.d(TAG, "initialize: partner app with metrics content provider is available");
            provideEventBus().getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.handsfree.metrics.amok.VoiceAppMetricsInitializer.1
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public void handle(@NonNull Message message) {
                    VoiceAppMetricsInitializer.this.checkUserIdentityHasFeature(context, alexaMetricsContentObserver);
                }
            });
        } catch (Exception unused) {
            Log.d(TAG, "initialize: no partner app with metrics provider");
        }
    }

    public void initialize(@NonNull Context context, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        if (!aMPDInformationProvider.isHandsFreeMike()) {
            return;
        }
        if (this.mFeatureServiceV2 == null) {
            Log.w(TAG, "Feature Service V2 could not be found.");
        } else {
            registerContentObserver(context, new AlexaMetricsContentObserver(context, new Handler()));
        }
    }
}
