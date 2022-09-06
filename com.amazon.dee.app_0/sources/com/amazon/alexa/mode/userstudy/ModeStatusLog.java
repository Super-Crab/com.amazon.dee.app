package com.amazon.alexa.mode.userstudy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.mode.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
/* loaded from: classes9.dex */
public class ModeStatusLog {
    private static final String ACTION_DRIVE_MODE_STATUS_LOG = "com.amazon.drive.time.DRIVE_MODE_LOG";
    private static final String EXTRA_DRIVE_MODE_LOG_DATA = "drive_mode_log_data";
    private static final int MSG_LOG_DRIVE_MODE_STATUS = 256;
    private static final String PACKAGE_DRIVE_MODE_LOG_SERVICE = "com.amazon.drive.time";
    private static final String TAG = "ModeStatusLog";
    @VisibleForTesting
    static boolean sFeatureEnabled = false;
    @VisibleForTesting
    final ServiceConnection connection = new ServiceConnection() { // from class: com.amazon.alexa.mode.userstudy.ModeStatusLog.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ModeStatusLog.this.messenger = new Messenger(iBinder);
            ModeStatusLog.this.isBound = true;
            Log.i(ModeStatusLog.TAG, "Service connected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ModeStatusLog modeStatusLog = ModeStatusLog.this;
            modeStatusLog.isBound = false;
            modeStatusLog.messenger = null;
            Log.i(ModeStatusLog.TAG, "Service disconnected");
        }
    };
    private final Context context;
    SimpleMultiFilterSubscriber driveModeUserStudyLogSubscriber;
    @VisibleForTesting
    boolean isBound;
    @VisibleForTesting
    Messenger messenger;
    @VisibleForTesting
    Handler workThreadHandler;

    public ModeStatusLog(Context context) {
        this.context = context;
    }

    private static boolean isPackageExisted(Context context) {
        try {
            context.getPackageManager().getPackageInfo(PACKAGE_DRIVE_MODE_LOG_SERVICE, 128);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Log service not found : ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
            return false;
        }
    }

    private void subscribeToUserStudyLog() {
        EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(eventBus);
        this.driveModeUserStudyLogSubscriber = new SimpleMultiFilterSubscriber();
        this.driveModeUserStudyLogSubscriber.subscribe(new EventTypeMessageFilter(Constants.DRIVE_MODE_USER_STUDY_LOG), new MessageHandler() { // from class: com.amazon.alexa.mode.userstudy.-$$Lambda$ModeStatusLog$HUgnGmJsYmx1bpcEr97ky4HXPqA
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                ModeStatusLog.this.lambda$subscribeToUserStudyLog$1$ModeStatusLog(message);
            }
        });
        eventBus.subscribe(this.driveModeUserStudyLogSubscriber);
    }

    private void unSubscribeToUserStudyLog() {
        if (this.driveModeUserStudyLogSubscriber != null) {
            EventBus eventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
            Preconditions.checkNotNull(eventBus);
            eventBus.unsubscribe(this.driveModeUserStudyLogSubscriber);
            this.driveModeUserStudyLogSubscriber = null;
        }
    }

    public void doBindService() {
        if (!sFeatureEnabled || !isPackageExisted(this.context)) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread(EXTRA_DRIVE_MODE_LOG_DATA);
        handlerThread.start();
        this.workThreadHandler = new Handler(handlerThread.getLooper());
        try {
            Intent intent = new Intent(ACTION_DRIVE_MODE_STATUS_LOG);
            intent.setPackage(PACKAGE_DRIVE_MODE_LOG_SERVICE);
            this.context.bindService(intent, this.connection, 1);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Bind service error: "), TAG);
        }
        subscribeToUserStudyLog();
    }

    public void doUnbindService() {
        if (!sFeatureEnabled || !isPackageExisted(this.context)) {
            return;
        }
        unSubscribeToUserStudyLog();
        Handler handler = this.workThreadHandler;
        if (handler != null) {
            handler.removeCallbacks(null);
            this.workThreadHandler = null;
        }
        if (!this.isBound) {
            return;
        }
        try {
            this.context.unbindService(this.connection);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Unbind service error: "), TAG);
        }
        this.isBound = false;
    }

    public /* synthetic */ void lambda$logStatus$0$ModeStatusLog(String str) {
        String str2 = "Mode log data : " + str;
        try {
            this.messenger.send(android.os.Message.obtain(null, 256, GeneratedOutlineSupport1.outline11(EXTRA_DRIVE_MODE_LOG_DATA, str)));
        } catch (RemoteException e) {
            String str3 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Send log error: ");
            outline107.append(e.getMessage());
            Log.e(str3, outline107.toString());
        }
    }

    public /* synthetic */ void lambda$subscribeToUserStudyLog$1$ModeStatusLog(Message message) {
        logStatus(message.getPayloadAsString() + "\n");
    }

    public void logStatus(String str, String str2, String str3, String str4) {
        if (!sFeatureEnabled) {
            return;
        }
        logStatus(String.format("%s,%s,%s,%s\n", str, str2, str3, str4));
    }

    private void logStatus(final String str) {
        Handler handler;
        if (this.isBound && (handler = this.workThreadHandler) != null && this.messenger != null) {
            handler.post(new Runnable() { // from class: com.amazon.alexa.mode.userstudy.-$$Lambda$ModeStatusLog$NH_I8LHqmSollfNSSq6-YxTUoi0
                @Override // java.lang.Runnable
                public final void run() {
                    ModeStatusLog.this.lambda$logStatus$0$ModeStatusLog(str);
                }
            });
            return;
        }
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Mode status: isBound : ");
        outline107.append(this.isBound);
        outline107.append(" | workThreadHandler : ");
        outline107.append(this.workThreadHandler);
        outline107.append(" | messenger : ");
        outline107.append(this.messenger);
        Log.i(str2, outline107.toString());
    }
}
