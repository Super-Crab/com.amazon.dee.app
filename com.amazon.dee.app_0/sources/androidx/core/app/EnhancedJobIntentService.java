package androidx.core.app;

import android.util.Log;
import androidx.core.app.JobIntentService;
/* loaded from: classes.dex */
public abstract class EnhancedJobIntentService extends JobIntentService {
    private static final String TAG = EnhancedJobIntentService.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.core.app.JobIntentService
    public JobIntentService.GenericWorkItem dequeueWork() {
        try {
            return super.dequeueWork();
        } catch (SecurityException e) {
            Log.e(TAG, "Error to dequeue work", e);
            return null;
        }
    }
}
