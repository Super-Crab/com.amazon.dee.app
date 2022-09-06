package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.WorkInfo;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.UUID;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class StatusRunnable<T> implements Runnable {
    private final SettableFuture<T> mFuture = SettableFuture.create();

    public static StatusRunnable<List<WorkInfo>> forStringIds(@NonNull final WorkManagerImpl workManagerImpl, @NonNull final List<String> list) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.1
            @Override // androidx.work.impl.utils.StatusRunnable
            /* renamed from: runInternal  reason: avoid collision after fix types in other method */
            public List<WorkInfo> mo239runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(WorkManagerImpl.this.getWorkDatabase().workSpecDao().getWorkStatusPojoForIds(list));
            }
        };
    }

    public static StatusRunnable<List<WorkInfo>> forTag(@NonNull final WorkManagerImpl workManagerImpl, @NonNull final String str) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.3
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.work.impl.utils.StatusRunnable
            /* renamed from: runInternal  reason: collision with other method in class */
            public List<WorkInfo> mo239runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(WorkManagerImpl.this.getWorkDatabase().workSpecDao().getWorkStatusPojoForTag(str));
            }
        };
    }

    public static StatusRunnable<WorkInfo> forUUID(@NonNull final WorkManagerImpl workManagerImpl, @NonNull final UUID uuid) {
        return new StatusRunnable<WorkInfo>() { // from class: androidx.work.impl.utils.StatusRunnable.2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.work.impl.utils.StatusRunnable
            /* renamed from: runInternal */
            public WorkInfo mo239runInternal() {
                WorkSpec.WorkInfoPojo workStatusPojoForId = WorkManagerImpl.this.getWorkDatabase().workSpecDao().getWorkStatusPojoForId(uuid.toString());
                if (workStatusPojoForId != null) {
                    return workStatusPojoForId.toWorkInfo();
                }
                return null;
            }
        };
    }

    public static StatusRunnable<List<WorkInfo>> forUniqueWork(@NonNull final WorkManagerImpl workManagerImpl, @NonNull final String str) {
        return new StatusRunnable<List<WorkInfo>>() { // from class: androidx.work.impl.utils.StatusRunnable.4
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // androidx.work.impl.utils.StatusRunnable
            /* renamed from: runInternal  reason: collision with other method in class */
            public List<WorkInfo> mo239runInternal() {
                return WorkSpec.WORK_INFO_MAPPER.apply(WorkManagerImpl.this.getWorkDatabase().workSpecDao().getWorkStatusPojoForName(str));
            }
        };
    }

    public ListenableFuture<T> getFuture() {
        return this.mFuture;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.mFuture.set(mo239runInternal());
        } catch (Throwable th) {
            this.mFuture.setException(th);
        }
    }

    @WorkerThread
    /* renamed from: runInternal */
    abstract T mo239runInternal();
}
