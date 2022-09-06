package androidx.core.app;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilder;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes.dex */
public abstract class SafeDequeueJobIntentService extends JobIntentService {
    @VisibleForTesting
    static final String COMPLETE_WORK_METRIC_NAME = "JobIntentServiceCompleteWorkSuccess";
    @VisibleForTesting
    static final String DEQUEUE_METRIC_NAME = "JobIntentServiceSecurityException";
    private static final String TAG = SafeDequeueJobIntentService.class.getSimpleName();
    private final boolean mCanEmitMetrics;
    private MetricsBuilderProvider mMetricsBuilderProvider;

    /* loaded from: classes.dex */
    private final class WrappedGenericWorkItem implements JobIntentService.GenericWorkItem {
        @NonNull
        private final JobIntentService.GenericWorkItem mWrappedWorkItem;

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public void complete() {
            boolean z = false;
            try {
                this.mWrappedWorkItem.complete();
                z = true;
            } catch (SecurityException e) {
                Log.e(SafeDequeueJobIntentService.TAG, "Error while completing work item: ", e, new Object[0]);
            }
            if (SafeDequeueJobIntentService.this.mCanEmitMetrics) {
                SafeDequeueJobIntentService.this.recordOperationMetric(SafeDequeueJobIntentService.COMPLETE_WORK_METRIC_NAME, z);
            }
        }

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public Intent getIntent() {
            return this.mWrappedWorkItem.getIntent();
        }

        private WrappedGenericWorkItem(@NonNull JobIntentService.GenericWorkItem genericWorkItem) {
            this.mWrappedWorkItem = genericWorkItem;
        }
    }

    public SafeDequeueJobIntentService() {
        this(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordOperationMetric(@NonNull String str, boolean z) {
        MetricsBuilder newBuilder = this.mMetricsBuilderProvider.newBuilder();
        if (z) {
            newBuilder.withPercentileMetricSuccess(TAG, str);
        } else {
            newBuilder.withPercentileMetricFailure(TAG, str);
        }
        newBuilder.emit(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.core.app.JobIntentService
    public JobIntentService.GenericWorkItem dequeueWork() {
        boolean z = false;
        WrappedGenericWorkItem wrappedGenericWorkItem = null;
        try {
            JobIntentService.GenericWorkItem dequeueWork = super.dequeueWork();
            if (dequeueWork != null) {
                wrappedGenericWorkItem = new WrappedGenericWorkItem(dequeueWork);
            }
            z = true;
        } catch (SecurityException e) {
            Log.e(TAG, "Error while dequeuing work from SafeDequeueJobIntentService: ", e, new Object[0]);
        }
        if (this.mCanEmitMetrics) {
            recordOperationMetric(DEQUEUE_METRIC_NAME, z);
        }
        return wrappedGenericWorkItem;
    }

    @Override // androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mMetricsBuilderProvider = MetricsBuilderProvider.getInstance(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SafeDequeueJobIntentService(boolean z) {
        this.mCanEmitMetrics = z;
    }
}
