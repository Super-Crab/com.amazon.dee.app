package com.amazon.device.messaging;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.fireospowersupport.FireOSPowerSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes12.dex */
public abstract class JobIntentService extends Service {
    static final boolean DEBUG = false;
    static final String HIGH_PRIORITY = "HIGH";
    static final String NORMAL_PRIORITY = "NORMAL";
    static final String TAG = "JobIntentService";
    WorkEnqueuer mCompatWorkEnqueuer;
    CommandProcessor mCurProcessor;
    CompatJobEngine mJobImpl;
    static final Object S_LOCK = new Object();
    static final HashMap<ComponentName, WorkEnqueuer> S_CLASS_WORK_ENQUEUER = new HashMap<>();
    static final HashMap<ComponentName, WorkEnqueuer> S_PRIORITY_CLASS_WORK_ENQUEUER = new HashMap<>();
    boolean mInterruptIfStopped = false;
    boolean mStopped = false;
    boolean mDestroyed = false;
    final ArrayList<CompatWorkItem> mCompatQueue = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        CommandProcessor() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            while (true) {
                GenericWorkItem dequeueWork = JobIntentService.this.dequeueWork();
                if (dequeueWork != null) {
                    JobIntentService.this.onHandleWork(dequeueWork.getIntent());
                    dequeueWork.complete();
                } else {
                    return null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(Void r1) {
            JobIntentService.this.processorFinished();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            JobIntentService.this.processorFinished();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static final class CompatWorkEnqueuer extends WorkEnqueuer {
        private final Context mContext;
        private final PowerManager.WakeLock mLaunchWakeLock;
        boolean mLaunchingService;
        private final PowerManager.WakeLock mRunWakeLock;
        boolean mServiceProcessing;

        CompatWorkEnqueuer(Context context, ComponentName componentName) {
            super(componentName);
            this.mContext = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            this.mLaunchWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.mLaunchWakeLock.setReferenceCounted(false);
            this.mRunWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.mRunWakeLock.setReferenceCounted(false);
        }

        @Override // com.amazon.device.messaging.JobIntentService.WorkEnqueuer
        void enqueueWork(Intent intent) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(this.mComponentName);
            if (this.mContext.startService(intent2) != null) {
                synchronized (this) {
                    if (!this.mLaunchingService) {
                        this.mLaunchingService = true;
                        if (!this.mServiceProcessing) {
                            this.mLaunchWakeLock.acquire(60000L);
                        }
                    }
                }
            }
        }

        @Override // com.amazon.device.messaging.JobIntentService.WorkEnqueuer
        public void serviceProcessingFinished() {
            synchronized (this) {
                if (this.mServiceProcessing) {
                    if (this.mLaunchingService) {
                        this.mLaunchWakeLock.acquire(60000L);
                    }
                    this.mServiceProcessing = false;
                    this.mRunWakeLock.release();
                }
            }
        }

        @Override // com.amazon.device.messaging.JobIntentService.WorkEnqueuer
        public void serviceProcessingStarted() {
            synchronized (this) {
                if (!this.mServiceProcessing) {
                    this.mServiceProcessing = true;
                    this.mRunWakeLock.acquire(FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS);
                    this.mLaunchWakeLock.release();
                }
            }
        }

        @Override // com.amazon.device.messaging.JobIntentService.WorkEnqueuer
        public void serviceStartReceived() {
            synchronized (this) {
                this.mLaunchingService = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public final class CompatWorkItem implements GenericWorkItem {
        final Intent mIntent;
        final int mStartId;

        CompatWorkItem(Intent intent, int i) {
            this.mIntent = intent;
            this.mStartId = i;
        }

        @Override // com.amazon.device.messaging.JobIntentService.GenericWorkItem
        public void complete() {
            JobIntentService.this.stopSelf(this.mStartId);
        }

        @Override // com.amazon.device.messaging.JobIntentService.GenericWorkItem
        public Intent getIntent() {
            return this.mIntent;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    @TargetApi(26)
    /* loaded from: classes12.dex */
    static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {
        static final boolean DEBUG = false;
        static final String TAG = "JobServiceEngineImpl";
        final Object mLock;
        JobParameters mParams;
        final JobIntentService mService;

        /* loaded from: classes12.dex */
        final class WrapperWorkItem implements GenericWorkItem {
            final JobWorkItem mJobWork;

            WrapperWorkItem(JobWorkItem jobWorkItem) {
                this.mJobWork = jobWorkItem;
            }

            @Override // com.amazon.device.messaging.JobIntentService.GenericWorkItem
            public void complete() {
                synchronized (JobServiceEngineImpl.this.mLock) {
                    if (JobServiceEngineImpl.this.mParams != null) {
                        JobServiceEngineImpl.this.mParams.completeWork(this.mJobWork);
                    }
                }
            }

            @Override // com.amazon.device.messaging.JobIntentService.GenericWorkItem
            public Intent getIntent() {
                return this.mJobWork.getIntent();
            }
        }

        JobServiceEngineImpl(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.mLock = new Object();
            this.mService = jobIntentService;
        }

        @Override // com.amazon.device.messaging.JobIntentService.CompatJobEngine
        public IBinder compatGetBinder() {
            return getBinder();
        }

        @Override // com.amazon.device.messaging.JobIntentService.CompatJobEngine
        public GenericWorkItem dequeueWork() {
            synchronized (this.mLock) {
                if (this.mParams == null) {
                    return null;
                }
                JobWorkItem dequeueWork = this.mParams.dequeueWork();
                if (dequeueWork == null) {
                    return null;
                }
                dequeueWork.getIntent().setExtrasClassLoader(this.mService.getClassLoader());
                return new WrapperWorkItem(dequeueWork);
            }
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.mParams = jobParameters;
            this.mService.ensureProcessorRunningLocked(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean doStopCurrentWork = this.mService.doStopCurrentWork();
            synchronized (this.mLock) {
                this.mParams = null;
            }
            return doStopCurrentWork;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(26)
    /* loaded from: classes12.dex */
    public static final class JobWorkEnqueuer extends WorkEnqueuer {
        private final JobInfo mJobInfo;
        private final JobScheduler mJobScheduler;

        JobWorkEnqueuer(Context context, ComponentName componentName, int i) {
            super(componentName);
            ensureJobId(i);
            this.mJobInfo = new JobInfo.Builder(i, this.mComponentName).setOverrideDeadline(0L).build();
            this.mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        @Override // com.amazon.device.messaging.JobIntentService.WorkEnqueuer
        void enqueueWork(Intent intent) {
            this.mJobScheduler.enqueue(this.mJobInfo, new JobWorkItem(intent));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static abstract class WorkEnqueuer {
        final ComponentName mComponentName;
        boolean mHasJobId;
        int mJobId;

        WorkEnqueuer(ComponentName componentName) {
            this.mComponentName = componentName;
        }

        abstract void enqueueWork(Intent intent);

        void ensureJobId(int i) {
            if (!this.mHasJobId) {
                this.mHasJobId = true;
                this.mJobId = i;
            } else if (this.mJobId == i) {
            } else {
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Given job ID ", i, " is different than previous ");
                outline109.append(this.mJobId);
                throw new IllegalArgumentException(outline109.toString());
            }
        }

        public void serviceProcessingFinished() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceStartReceived() {
        }
    }

    public static void enqueueWork(Context context, Class cls, int i, Intent intent) {
        if (cls != null && context != null) {
            enqueueWork(context, new ComponentName(context, cls), i, intent);
            return;
        }
        throw new IllegalArgumentException("parameters must not be null");
    }

    static WorkEnqueuer getWorkEnqueuer(Context context, ComponentName componentName, boolean z, int i) {
        return getWorkEnqueuer(context, componentName, z, i, "NORMAL");
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(24)
    static boolean isTempWhitelisted(Context context) {
        if (context.getPackageManager().hasSystemFeature("com.fireos.sdk.fireos_power_support")) {
            return FireOSPowerSupport.getFireOSPowerSupport(context).isTempWhitelisted(context.getPackageName());
        }
        return false;
    }

    GenericWorkItem dequeueWork() {
        synchronized (this.mCompatQueue) {
            if (this.mCompatQueue.size() > 0) {
                return this.mCompatQueue.remove(0);
            } else if (this.mJobImpl == null) {
                return null;
            } else {
                return this.mJobImpl.dequeueWork();
            }
        }
    }

    boolean doStopCurrentWork() {
        CommandProcessor commandProcessor = this.mCurProcessor;
        if (commandProcessor != null) {
            commandProcessor.cancel(this.mInterruptIfStopped);
        }
        this.mStopped = true;
        return onStopCurrentWork();
    }

    void ensureProcessorRunningLocked(boolean z) {
        if (this.mCurProcessor == null) {
            this.mCurProcessor = new CommandProcessor();
            WorkEnqueuer workEnqueuer = this.mCompatWorkEnqueuer;
            if (workEnqueuer != null && z) {
                workEnqueuer.serviceProcessingStarted();
            }
            this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public boolean isStopped() {
        return this.mStopped;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        CompatJobEngine compatJobEngine;
        if (intent == null || (compatJobEngine = this.mJobImpl) == null) {
            return null;
        }
        return compatJobEngine.compatGetBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        this.mJobImpl = new JobServiceEngineImpl(this);
        this.mCompatWorkEnqueuer = getWorkEnqueuer(this, new ComponentName(this, getClass()), false, 0, "HIGH");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<CompatWorkItem> arrayList = this.mCompatQueue;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.mDestroyed = true;
                this.mCompatWorkEnqueuer.serviceProcessingFinished();
            }
        }
    }

    protected abstract void onHandleWork(Intent intent);

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (this.mCompatQueue != null) {
            this.mCompatWorkEnqueuer.serviceStartReceived();
            synchronized (this.mCompatQueue) {
                ArrayList<CompatWorkItem> arrayList = this.mCompatQueue;
                if (intent == null) {
                    intent = new Intent();
                }
                arrayList.add(new CompatWorkItem(intent, i2));
                ensureProcessorRunningLocked(true);
            }
            return 3;
        }
        return 2;
    }

    public boolean onStopCurrentWork() {
        return true;
    }

    void processorFinished() {
        ArrayList<CompatWorkItem> arrayList = this.mCompatQueue;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.mCurProcessor = null;
                if (!this.mCompatQueue.isEmpty()) {
                    ensureProcessorRunningLocked(false);
                } else if (!this.mDestroyed) {
                    this.mCompatWorkEnqueuer.serviceProcessingFinished();
                }
            }
        }
    }

    public void setInterruptIfStopped(boolean z) {
        this.mInterruptIfStopped = z;
    }

    static WorkEnqueuer getWorkEnqueuer(Context context, ComponentName componentName, boolean z, int i, String str) {
        WorkEnqueuer workEnqueuer = S_CLASS_WORK_ENQUEUER.get(componentName);
        if (str.equals("HIGH") && isTempWhitelisted(context)) {
            int i2 = Build.VERSION.SDK_INT;
            WorkEnqueuer workEnqueuer2 = S_PRIORITY_CLASS_WORK_ENQUEUER.get(componentName);
            if (workEnqueuer2 != null) {
                return workEnqueuer2;
            }
            CompatWorkEnqueuer compatWorkEnqueuer = new CompatWorkEnqueuer(context, componentName);
            S_PRIORITY_CLASS_WORK_ENQUEUER.put(componentName, compatWorkEnqueuer);
            return compatWorkEnqueuer;
        } else if (workEnqueuer != null) {
            return workEnqueuer;
        } else {
            int i3 = Build.VERSION.SDK_INT;
            if (z) {
                JobWorkEnqueuer jobWorkEnqueuer = new JobWorkEnqueuer(context, componentName, i);
                S_CLASS_WORK_ENQUEUER.put(componentName, jobWorkEnqueuer);
                return jobWorkEnqueuer;
            }
            throw new IllegalArgumentException("Can't be here without a job id");
        }
    }

    public static void enqueueWork(Context context, ComponentName componentName, int i, Intent intent) {
        if (intent != null && context != null && componentName != null) {
            synchronized (S_LOCK) {
                String string = intent.getExtras().getString(ADMConstants.EXTRA_PRIORITY);
                if (string == null) {
                    string = "NORMAL";
                }
                WorkEnqueuer workEnqueuer = getWorkEnqueuer(context, componentName, true, i, string);
                workEnqueuer.ensureJobId(i);
                workEnqueuer.enqueueWork(intent);
            }
            return;
        }
        throw new IllegalArgumentException("parameters must not be null");
    }
}
