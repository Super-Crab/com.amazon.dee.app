package com.amazon.alexa.handsfree.notification.services;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.alexa.handsfree.notification.AlexaConnectionFailureConverter;
import com.amazon.alexa.handsfree.notification.IntentActionParser;
import com.amazon.alexa.handsfree.notification.metrics.NotificationEventMetadata;
import com.amazon.alexa.handsfree.notification.notifiers.Notifier;
import com.amazon.alexa.handsfree.notification.notifiers.NotifierPriorityResolver;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.AlexaAppSignInContract;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import dagger.Lazy;
import java.util.Optional;
/* loaded from: classes8.dex */
public class UtteranceNotifierService extends NotifierService {
    private static final int JOB_ID = 30002;
    private static final String TAG = UtteranceNotifierService.class.getSimpleName();
    private MetricsBuilderProvider mMetricsBuilderProvider;

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, UtteranceNotifierService.class, (int) JOB_ID, intent);
    }

    private void reportUtteranceFailureMetric(@NonNull String str) {
        AlexaConnectionFailureConverter alexaConnectionFailureConverter = new AlexaConnectionFailureConverter();
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        Optional<NotificationEventMetadata.ConnectionFailureReason> connectionFailureReasonFromString = alexaConnectionFailureConverter.getConnectionFailureReasonFromString(str);
        if (connectionFailureReasonFromString.isPresent()) {
            newBuilder.withUtteranceMetric(TAG, connectionFailureReasonFromString.get()).emit(this);
        } else {
            Log.e(TAG, String.format("reportUtteranceFailureMetric: Unexpected failure reason received: %s", str));
        }
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        Lazy<AlexaAppSignInContract> alexaAppSignInContractLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(this, FalcoProtocolComponent.class)).alexaAppSignInContractLazy();
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this);
        IntentActionParser intentActionParser = new IntentActionParser(intent);
        Notifier utteranceBasedNotifier = new NotifierPriorityResolver(this, intent).getUtteranceBasedNotifier();
        if (intentActionParser.isUtteranceFailure()) {
            reportUtteranceFailureMetric(intentActionParser.getUtteranceFailureReason());
        }
        onHandleWork(alexaAppSignInContractLazy, utteranceBasedNotifier);
    }

    void onHandleWork(@NonNull Lazy<AlexaAppSignInContract> lazy, @NonNull Notifier notifier) {
        lazy.mo358get().setup(this, true);
        notify(notifier);
        lazy.mo358get().teardown(this);
    }
}
