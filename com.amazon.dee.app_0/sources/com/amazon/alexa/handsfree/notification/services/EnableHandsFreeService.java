package com.amazon.alexa.handsfree.notification.services;

import android.content.Context;
import android.content.Intent;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.JobIntentService;
import androidx.core.app.SafeDequeueJobIntentService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.handsfree.notification.R;
import com.amazon.alexa.handsfree.notification.notifiers.EnableHandsFreeNotifier;
import com.amazon.alexa.handsfree.notification.services.EnableHandsFreeService;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class EnableHandsFreeService extends SafeDequeueJobIntentService {
    public static final String ENABLE_HANDS_FREE_EVENT = "ampd:handsfree:enable";
    private static final int JOB_ID = 30009;
    private static final long PUBLISH_EVENT_DELAY = 1000;
    private static final String SETTINGS_INTENT = "com.amazon.alexa.handsfree.SETTINGS";
    private static final String TAG = EnableHandsFreeService.class.getSimpleName();
    private EventBus mEventBus;
    private Handler mHandler;
    private EnableHandsFreeNotifier.PreferenceManager mPreferenceManager;
    private WakeWordSettingsManager mWakeWordSettingsManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.handsfree.notification.services.EnableHandsFreeService$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    public class AnonymousClass1 implements ResponseCallback {
        final /* synthetic */ ConditionVariable val$conditionVariable;

        AnonymousClass1(ConditionVariable conditionVariable) {
            this.val$conditionVariable = conditionVariable;
        }

        public /* synthetic */ void lambda$onSuccess$0$EnableHandsFreeService$1(ConditionVariable conditionVariable) {
            EnableHandsFreeService.this.showToast();
            EnableHandsFreeService.this.mEventBus.publish(new Message.Builder().setEventType(EnableHandsFreeService.ENABLE_HANDS_FREE_EVENT).build());
            conditionVariable.open();
        }

        @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
        public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
            Log.d(EnableHandsFreeService.TAG, "onError");
            this.val$conditionVariable.open();
        }

        @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
        public void onSuccess() {
            Log.d(EnableHandsFreeService.TAG, "onSuccess");
            EnableHandsFreeService.this.startSettingsActivity();
            Handler handler = EnableHandsFreeService.this.mHandler;
            final ConditionVariable conditionVariable = this.val$conditionVariable;
            handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.handsfree.notification.services.-$$Lambda$EnableHandsFreeService$1$Gr-lHBlY8T5fEBrpOqR_KCxmDSI
                @Override // java.lang.Runnable
                public final void run() {
                    EnableHandsFreeService.AnonymousClass1.this.lambda$onSuccess$0$EnableHandsFreeService$1(conditionVariable);
                }
            }, 1000L);
        }
    }

    public EnableHandsFreeService() {
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        JobIntentService.enqueueWork(context, EnableHandsFreeService.class, (int) JOB_ID, intent);
    }

    @Override // androidx.core.app.SafeDequeueJobIntentService, androidx.core.app.JobIntentService, android.app.Service
    public void onCreate() {
        InitializerProvider.getInitializer().initialize(this);
        super.onCreate();
        this.mWakeWordSettingsManager = WakeWordSettingsManagerProvider.getInstance().get();
        this.mPreferenceManager = new EnableHandsFreeNotifier.PreferenceManager();
        this.mEventBus = (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override // androidx.core.app.JobIntentService
    protected void onHandleWork(@NonNull Intent intent) {
        this.mPreferenceManager.disableEnableHandsFreeNotification(this);
        ConditionVariable conditionVariable = new ConditionVariable();
        this.mWakeWordSettingsManager.setHandsFreeState(true, new AnonymousClass1(conditionVariable));
        try {
            conditionVariable.block(10000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Done");
    }

    @VisibleForTesting
    void showToast() {
        Toast.makeText(this, R.string.enable_hands_free_notification_toast_text, 1).show();
    }

    @VisibleForTesting
    void startSettingsActivity() {
        startActivity(new Intent("com.amazon.alexa.handsfree.SETTINGS").setFlags(268435456));
    }

    EnableHandsFreeService(@NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull EnableHandsFreeNotifier.PreferenceManager preferenceManager, @NonNull EventBus eventBus, @NonNull Handler handler) {
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
        this.mPreferenceManager = preferenceManager;
        this.mEventBus = eventBus;
        this.mHandler = handler;
    }
}
