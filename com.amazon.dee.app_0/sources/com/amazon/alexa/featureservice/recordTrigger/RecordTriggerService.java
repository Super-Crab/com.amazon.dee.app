package com.amazon.alexa.featureservice.recordTrigger;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.constants.FeatureServiceMetrics;
import com.amazon.alexa.featureservice.recordTrigger.RecordTriggerNetworkClient;
import com.amazon.alexa.featureservice.recordTrigger.TriggerQueue;
import com.amazon.alexa.featureservice.util.ExceptionUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes7.dex */
public class RecordTriggerService {
    @VisibleForTesting
    static final int BATCH_UPLOAD_FREQUENCY_IN_SECONDS = 5;
    private static final String TAG = "RecordTriggerService";
    @VisibleForTesting
    static final int THROTTLE_DURATION_IN_SECONDS = 1;
    private ScheduledExecutorService executor;
    private Lazy<Mobilytics> mobilyticsLazy;
    private Runnable moveToBatchTask;
    private final RecordTriggerNetworkClient networkClient;
    @VisibleForTesting
    TriggerQueue nextBatch;
    private RecordTriggerServiceHelper recordTriggerServiceHelper;
    @VisibleForTesting
    TriggerThrottler requestThrottler;
    private Runnable uploadBatchTask;
    @VisibleForTesting
    AtomicBoolean moveToBatchTaskScheduled = new AtomicBoolean(false);
    @VisibleForTesting
    AtomicBoolean uploadBatchTaskScheduled = new AtomicBoolean(false);
    @VisibleForTesting
    RecordTriggerNetworkClient.Listener networkClientListener = new RecordTriggerNetworkClient.Listener() { // from class: com.amazon.alexa.featureservice.recordTrigger.RecordTriggerService.1
        @Override // com.amazon.alexa.featureservice.recordTrigger.RecordTriggerNetworkClient.Listener
        public void onFailure(List<RequestTreatment> list, int i, Exception exc) {
            if (ExceptionUtil.isNonActionableHTTPError(exc)) {
                RecordTriggerService.this.recordMetricsEvent("non_actionable_http_error", GeneratedOutlineSupport1.outline49(FeatureServiceMetrics.HTTP_ERROR_PREFIX, i), exc);
            } else {
                RecordTriggerService.this.recordMetricsEvent(FeatureServiceMetrics.EventType.TRIGGER_HTTP_ERROR, GeneratedOutlineSupport1.outline49(FeatureServiceMetrics.HTTP_ERROR_PREFIX, i), exc);
            }
            Log.e(RecordTriggerService.TAG, "Error making recording triggers, re-adding to the batch", exc);
        }

        @Override // com.amazon.alexa.featureservice.recordTrigger.RecordTriggerNetworkClient.Listener
        public void onSuccess(@NonNull String str) {
            RecordTriggerService.this.recordMetricsEvent(FeatureServiceMetrics.EventType.TRIGGER_SUCCESS, FeatureServiceMetrics.EventType.TRIGGER_SUCCESS, null);
            RecordTriggerService.this.handleNetworkResponse(str);
        }
    };
    private TriggerQueue.ChangeListener triggerQueueListener = new TriggerQueue.ChangeListener() { // from class: com.amazon.alexa.featureservice.recordTrigger.RecordTriggerService.2
        @Override // com.amazon.alexa.featureservice.recordTrigger.TriggerQueue.ChangeListener
        public void onAdd(@NonNull Collection<RequestTreatment> collection) {
            RecordTriggerService.this.scheduleUploadBatchTaskIfRequired();
        }

        @Override // com.amazon.alexa.featureservice.recordTrigger.TriggerQueue.ChangeListener
        public void onInsert(int i, @NonNull Collection<RequestTreatment> collection) {
            RecordTriggerService.this.scheduleUploadBatchTaskIfRequired();
        }
    };

    @VisibleForTesting
    RecordTriggerService(@NonNull RecordTriggerNetworkClient recordTriggerNetworkClient, Lazy<Mobilytics> lazy) {
        this.networkClient = recordTriggerNetworkClient;
        this.mobilyticsLazy = lazy;
        init();
    }

    private void init() {
        this.requestThrottler = new TriggerThrottler();
        this.nextBatch = new TriggerQueue(this.triggerQueueListener);
        this.recordTriggerServiceHelper = new RecordTriggerServiceHelper(this.mobilyticsLazy);
        this.executor = Executors.newScheduledThreadPool(2);
        this.moveToBatchTask = new Runnable() { // from class: com.amazon.alexa.featureservice.recordTrigger.-$$Lambda$RecordTriggerService$uu0U95LYyBeVFtMsTFZTsfclu5Q
            @Override // java.lang.Runnable
            public final void run() {
                RecordTriggerService.this.lambda$init$0$RecordTriggerService();
            }
        };
        this.uploadBatchTask = new Runnable() { // from class: com.amazon.alexa.featureservice.recordTrigger.-$$Lambda$RecordTriggerService$WnzFrRTfIXAPuxR6jEMm1FTc1WE
            @Override // java.lang.Runnable
            public final void run() {
                RecordTriggerService.this.lambda$init$1$RecordTriggerService();
            }
        };
    }

    private void scheduleMoveToBatchTaskIfRequired() {
        if (this.requestThrottler.isEmpty() || !this.moveToBatchTaskScheduled.compareAndSet(false, true)) {
            return;
        }
        this.executor.schedule(this.moveToBatchTask, 1L, TimeUnit.SECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleUploadBatchTaskIfRequired() {
        if (this.nextBatch.isEmpty() || !this.uploadBatchTaskScheduled.compareAndSet(false, true)) {
            return;
        }
        this.executor.schedule(this.uploadBatchTask, 5L, TimeUnit.SECONDS);
    }

    @VisibleForTesting
    void handleNetworkResponse(@Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            List<ResponseTreatment> fromResponseJson = this.recordTriggerServiceHelper.fromResponseJson(str);
            if (fromResponseJson.size() > 0) {
                processTreatments(fromResponseJson);
                return;
            }
            Log.i(TAG, "Empty response received from server for feature access");
            recordMetricsEvent(FeatureServiceMetrics.EventType.TRIGGER_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.EMPTY_RESPONSE, null);
            return;
        }
        Log.i(TAG, "Empty response received from server for feature access");
        recordMetricsEvent(FeatureServiceMetrics.EventType.TRIGGER_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.EMPTY_RESPONSE, null);
    }

    public /* synthetic */ void lambda$init$0$RecordTriggerService() {
        this.nextBatch.add(this.requestThrottler.removeAll());
        this.moveToBatchTaskScheduled.set(false);
        scheduleMoveToBatchTaskIfRequired();
    }

    public /* synthetic */ void lambda$init$1$RecordTriggerService() {
        this.networkClient.makeCall(this.nextBatch.popAll(), this.recordTriggerServiceHelper);
        this.uploadBatchTaskScheduled.set(false);
        scheduleUploadBatchTaskIfRequired();
    }

    @VisibleForTesting
    void processTreatments(@NonNull List<ResponseTreatment> list) {
        for (ResponseTreatment responseTreatment : list) {
            String.format("Treatment received for feature %s is %s", responseTreatment.getFeatureName(), responseTreatment.getTreatment());
            if (responseTreatment.getRecordStatus() == null) {
                recordMetricsEvent(FeatureServiceMetrics.EventType.TRIGGER_PAYLOAD_ERROR, FeatureServiceMetrics.EventName.MODEL_ERROR, null);
            } else {
                String.format("Record status for feature %s is %s", responseTreatment.getFeatureName(), responseTreatment.getRecordStatus());
            }
        }
    }

    public void record(@NonNull RequestTreatment requestTreatment) {
        String.format("Recording trigger for: %s", requestTreatment.getFeatureName());
        this.requestThrottler.add(requestTreatment);
        scheduleMoveToBatchTaskIfRequired();
    }

    @VisibleForTesting
    void recordMetricsEvent(@NonNull String str, @NonNull String str2, @Nullable Exception exc) {
        this.mobilyticsLazy.mo358get().recordCriticalEvent(GeneratedOutlineSupport1.outline72(FeatureServiceMetrics.METRICS_NAME_PREFIX, str), GeneratedOutlineSupport1.outline72(FeatureServiceMetrics.METRICS_NAME_PREFIX, str), str2, FeatureServiceMetrics.Subcomponent.RECORD_TRIGGER, exc);
    }

    public RecordTriggerService(@NonNull Lazy<CoralService> lazy, Lazy<Mobilytics> lazy2) {
        this.networkClient = new RecordTriggerNetworkClient(lazy, this.networkClientListener);
        this.mobilyticsLazy = lazy2;
        init();
    }
}
