package androidx.work.impl.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WorkForegroundUpdater implements ForegroundUpdater {
    final ForegroundProcessor mForegroundProcessor;
    private final TaskExecutor mTaskExecutor;

    public WorkForegroundUpdater(@NonNull ForegroundProcessor foregroundProcessor, @NonNull TaskExecutor taskExecutor) {
        this.mForegroundProcessor = foregroundProcessor;
        this.mTaskExecutor = taskExecutor;
    }

    @Override // androidx.work.ForegroundUpdater
    @NonNull
    public ListenableFuture<Void> setForegroundAsync(@NonNull final Context context, @NonNull final UUID uuid, @NonNull final ForegroundInfo foregroundInfo) {
        final SettableFuture create = SettableFuture.create();
        this.mTaskExecutor.executeOnBackgroundThread(new Runnable() { // from class: androidx.work.impl.utils.WorkForegroundUpdater.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!create.isCancelled()) {
                        String uuid2 = uuid.toString();
                        WorkForegroundUpdater.this.mForegroundProcessor.startForeground(uuid2, foregroundInfo);
                        context.startService(SystemForegroundDispatcher.createNotifyIntent(context, uuid2, foregroundInfo));
                    }
                    create.set(null);
                } catch (Throwable th) {
                    create.setException(th);
                }
            }
        });
        return create;
    }
}
