package androidx.work;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public abstract class WorkerFactory {
    private static final String TAG = Logger.tagWithPrefix("WorkerFactory");

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static WorkerFactory getDefaultWorkerFactory() {
        return new WorkerFactory() { // from class: androidx.work.WorkerFactory.1
            @Override // androidx.work.WorkerFactory
            @Nullable
            public ListenableWorker createWorker(@NonNull Context context, @NonNull String str, @NonNull WorkerParameters workerParameters) {
                return null;
            }
        };
    }

    @Nullable
    public abstract ListenableWorker createWorker(@NonNull Context context, @NonNull String str, @NonNull WorkerParameters workerParameters);

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final ListenableWorker createWorkerWithDefaultFallback(@NonNull Context context, @NonNull String str, @NonNull WorkerParameters workerParameters) {
        ListenableWorker listenableWorker;
        ListenableWorker createWorker = createWorker(context, str, workerParameters);
        if (createWorker == null) {
            Class cls = null;
            try {
                cls = Class.forName(str).asSubclass(ListenableWorker.class);
            } catch (ClassNotFoundException unused) {
                Logger.get().error(TAG, GeneratedOutlineSupport1.outline72("Class not found: ", str), new Throwable[0]);
            }
            if (cls != null) {
                try {
                    listenableWorker = (ListenableWorker) cls.getDeclaredConstructor(Context.class, WorkerParameters.class).newInstance(context, workerParameters);
                } catch (Exception e) {
                    Logger.get().error(TAG, GeneratedOutlineSupport1.outline72("Could not instantiate ", str), e);
                }
                if (listenableWorker == null && listenableWorker.isUsed()) {
                    throw new IllegalStateException(String.format("WorkerFactory (%s) returned an instance of a ListenableWorker (%s) which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.", getClass().getName(), str));
                }
                return listenableWorker;
            }
        }
        listenableWorker = createWorker;
        if (listenableWorker == null) {
        }
        return listenableWorker;
    }
}
