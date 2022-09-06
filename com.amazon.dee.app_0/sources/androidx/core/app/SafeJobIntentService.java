package androidx.core.app;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes.dex */
public abstract class SafeJobIntentService extends JobIntentService {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SafeJobIntentService.class);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.core.app.JobIntentService
    public JobIntentService.GenericWorkItem dequeueWork() {
        try {
            JobIntentService.GenericWorkItem dequeueWork = super.dequeueWork();
            if (dequeueWork != null) {
                return new WrappedGenericWorkItem(dequeueWork, null);
            }
        } catch (SecurityException e) {
            LOG.e("Error to dequeue work", e);
        }
        return null;
    }

    /* loaded from: classes.dex */
    private final class WrappedGenericWorkItem implements JobIntentService.GenericWorkItem {
        @NonNull
        private final JobIntentService.GenericWorkItem original;

        private WrappedGenericWorkItem(@NonNull JobIntentService.GenericWorkItem genericWorkItem) {
            this.original = genericWorkItem;
        }

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public void complete() {
            try {
                this.original.complete();
            } catch (IllegalArgumentException e) {
                e = e;
                SafeJobIntentService.LOG.e("Error while completing work item", e);
            } catch (SecurityException e2) {
                e = e2;
                SafeJobIntentService.LOG.e("Error while completing work item", e);
            } catch (RuntimeException e3) {
                SafeJobIntentService.LOG.e("Error while executing background task ", e3);
            }
        }

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public Intent getIntent() {
            return this.original.getIntent();
        }

        /* synthetic */ WrappedGenericWorkItem(JobIntentService.GenericWorkItem genericWorkItem, AnonymousClass1 anonymousClass1) {
            this.original = genericWorkItem;
        }
    }
}
