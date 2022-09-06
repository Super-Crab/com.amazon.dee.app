package androidx.work.impl.background.greedy;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.ArrayList;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class GreedyScheduler implements Scheduler, WorkConstraintsCallback, ExecutionListener {
    private static final String TAG = Logger.tagWithPrefix("GreedyScheduler");
    private final Context mContext;
    private Boolean mIsMainProcess;
    private boolean mRegisteredExecutionListener;
    private final WorkConstraintsTracker mWorkConstraintsTracker;
    private final WorkManagerImpl mWorkManagerImpl;
    private List<WorkSpec> mConstrainedWorkSpecs = new ArrayList();
    private final Object mLock = new Object();

    public GreedyScheduler(@NonNull Context context, @NonNull TaskExecutor taskExecutor, @NonNull WorkManagerImpl workManagerImpl) {
        this.mContext = context;
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(context, taskExecutor, this);
    }

    @Nullable
    private String getProcessName() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.isEmpty()) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    private void registerExecutionListenerIfNeeded() {
        if (!this.mRegisteredExecutionListener) {
            this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
            this.mRegisteredExecutionListener = true;
        }
    }

    private void removeConstraintTrackingFor(@NonNull String str) {
        synchronized (this.mLock) {
            int size = this.mConstrainedWorkSpecs.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                } else if (this.mConstrainedWorkSpecs.get(i).id.equals(str)) {
                    Logger.get().debug(TAG, String.format("Stopping tracking for %s", str), new Throwable[0]);
                    this.mConstrainedWorkSpecs.remove(i);
                    this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
                    break;
                } else {
                    i++;
                }
            }
        }
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(@NonNull String str) {
        if (this.mIsMainProcess == null) {
            this.mIsMainProcess = Boolean.valueOf(TextUtils.equals(this.mContext.getPackageName(), getProcessName()));
        }
        if (!this.mIsMainProcess.booleanValue()) {
            Logger.get().info(TAG, "Ignoring schedule request in non-main process", new Throwable[0]);
            return;
        }
        registerExecutionListenerIfNeeded();
        Logger.get().debug(TAG, String.format("Cancelling work ID %s", str), new Throwable[0]);
        this.mWorkManagerImpl.stopWork(str);
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(@NonNull List<String> list) {
        for (String str : list) {
            Logger.get().debug(TAG, String.format("Constraints met: Scheduling work ID %s", str), new Throwable[0]);
            this.mWorkManagerImpl.startWork(str);
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(@NonNull List<String> list) {
        for (String str : list) {
            Logger.get().debug(TAG, String.format("Constraints not met: Cancelling work ID %s", str), new Throwable[0]);
            this.mWorkManagerImpl.stopWork(str);
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull String str, boolean z) {
        removeConstraintTrackingFor(str);
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(@NonNull WorkSpec... workSpecArr) {
        if (this.mIsMainProcess == null) {
            this.mIsMainProcess = Boolean.valueOf(TextUtils.equals(this.mContext.getPackageName(), getProcessName()));
        }
        if (!this.mIsMainProcess.booleanValue()) {
            Logger.get().info(TAG, "Ignoring schedule request in non-main process", new Throwable[0]);
            return;
        }
        registerExecutionListenerIfNeeded();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (WorkSpec workSpec : workSpecArr) {
            if (workSpec.state == WorkInfo.State.ENQUEUED && !workSpec.isPeriodic() && workSpec.initialDelay == 0 && !workSpec.isBackedOff()) {
                if (workSpec.hasConstraints()) {
                    int i = Build.VERSION.SDK_INT;
                    if (workSpec.constraints.requiresDeviceIdle()) {
                        Logger.get().debug(TAG, String.format("Ignoring WorkSpec %s, Requires device idle.", workSpec), new Throwable[0]);
                    } else {
                        int i2 = Build.VERSION.SDK_INT;
                        if (workSpec.constraints.hasContentUriTriggers()) {
                            Logger.get().debug(TAG, String.format("Ignoring WorkSpec %s, Requires ContentUri triggers.", workSpec), new Throwable[0]);
                        } else {
                            arrayList.add(workSpec);
                            arrayList2.add(workSpec.id);
                        }
                    }
                } else {
                    Logger.get().debug(TAG, String.format("Starting work for %s", workSpec.id), new Throwable[0]);
                    this.mWorkManagerImpl.startWork(workSpec.id);
                }
            }
        }
        synchronized (this.mLock) {
            if (!arrayList.isEmpty()) {
                Logger.get().debug(TAG, String.format("Starting tracking for [%s]", TextUtils.join(",", arrayList2)), new Throwable[0]);
                this.mConstrainedWorkSpecs.addAll(arrayList);
                this.mWorkConstraintsTracker.replace(this.mConstrainedWorkSpecs);
            }
        }
    }

    @VisibleForTesting
    public GreedyScheduler(@NonNull Context context, @NonNull WorkManagerImpl workManagerImpl, @NonNull WorkConstraintsTracker workConstraintsTracker) {
        this.mContext = context;
        this.mWorkManagerImpl = workManagerImpl;
        this.mWorkConstraintsTracker = workConstraintsTracker;
    }
}
