package com.amazon.alexa.accessory.notificationpublisher.consumption;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.R;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.reply.ReplyTransientStorage;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationFileHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public final class ReplyReadBackComponent extends BaseComponent {
    public static final int EVENT_READ_BACK_APPROVED = 6;
    public static final int EVENT_READ_BACK_COMPLETE = 5;
    public static final int EVENT_READ_BACK_ERROR = 3;
    public static final int EVENT_READ_BACK_ERROR_RETRY_AVAILABLE = 4;
    public static final int EVENT_READ_BACK_REJECTED = 7;
    public static final int EVENT_READ_BACK_REQUEST = 1;
    public static final int EVENT_READ_BACK_SUCCESS = 2;
    private static final String TAG = "ReplyReadBackComponent";
    private static ReplyReadBackComponent componentInstance;
    private String notificationUuid;
    private String replyText;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public final class CreateReadBackRequestRunnable implements Runnable {
        CreateReadBackRequestRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.d(ReplyReadBackComponent.TAG, "CreateReadBackRequestRunnable - run");
            if (!NotificationEventManager.getInstance().createReplyReadBackTts(ReplyReadBackComponent.this.notificationUuid, String.format(Locale.US, DependencyProvider.getContext().getString(R.string.read_back_tts_format), ReplyReadBackComponent.this.replyText))) {
                Log.w(ReplyReadBackComponent.TAG, "CreateReadBackRequestRunnable - Encountered an error when requesting read back audio");
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACK_REQUEST_FAILED);
                ReplyReadBackComponent.this.notificationUuid = "";
                ReplyReadBackComponent.this.replyText = "";
                ReplyReadBackComponent.this.postEventMessage(4);
                return;
            }
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_READBACK_REQUEST_SUCCESS);
        }
    }

    private ReplyReadBackComponent() {
        super(9);
    }

    public static synchronized ReplyReadBackComponent getInstance() {
        ReplyReadBackComponent replyReadBackComponent;
        synchronized (ReplyReadBackComponent.class) {
            if (componentInstance == null) {
                componentInstance = new ReplyReadBackComponent();
            }
            replyReadBackComponent = componentInstance;
        }
        return replyReadBackComponent;
    }

    public static synchronized void releaseInstance() {
        synchronized (ReplyReadBackComponent.class) {
            componentInstance = null;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.BaseComponent
    public void handleEventMessage(int i, @Nullable Object obj) {
        if (i != 1) {
            GeneratedOutlineSupport1.outline151("handleEventMessage - Unhandled eventId: ", i, TAG);
            return;
        }
        Log.i(TAG, "handleEventMessage - EVENT_READ_BACK_REQUEST");
        try {
            Map map = (Map) obj;
            if (!map.containsKey(ReplyTransientStorage.NOTIFICATION_UUID) && !map.containsKey(ReplyTransientStorage.REPLY_TEXT)) {
                Log.e(TAG, "handleEventMessage - Payload does not contain required fields");
                postEventMessage(3);
            }
            Log.d(TAG, "handleEventMessage - Call requestAudioForReplyReadBack");
            requestAudioForReplyReadBack((String) map.get(ReplyTransientStorage.NOTIFICATION_UUID), (String) map.get(ReplyTransientStorage.REPLY_TEXT));
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("handleEventMessage - Exception: ", e, TAG);
            postEventMessage(3);
        }
    }

    public boolean isExpectingReadBack(String str) {
        return !Strings.isNullOrEmpty(str) && !Strings.isNullOrEmpty(this.notificationUuid) && str.equalsIgnoreCase(this.notificationUuid);
    }

    public boolean readBackAudioFileDownloaded(String str) {
        String str2 = TAG;
        Log.i(str2, "readBackAudioFileDownloaded - Notification UUID: " + str);
        if (!isExpectingReadBack(str)) {
            Log.i(TAG, "readBackAudioFileDownloaded - Read back downloaded, but not expectinga read back.");
            new NotificationFileHelper(DependencyProvider.getContext()).removeReadBackFile();
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(ReplyTransientStorage.REPLY_TEXT, this.replyText);
        hashMap.put(ReplyTransientStorage.NOTIFICATION_UUID, str);
        postEventMessage(2, hashMap);
        this.notificationUuid = "";
        this.replyText = "";
        return true;
    }

    @VisibleForTesting
    boolean requestAudioForReplyReadBack(@NonNull String str, @NonNull String str2) {
        if (!Strings.isNullOrEmpty(str) && !Strings.isNullOrEmpty(str2)) {
            Log.i(TAG, "requestAudioForReplyReadBack - Request read back audio");
            this.notificationUuid = str;
            this.replyText = str2;
            Executors.newCachedThreadPool().submit(new CreateReadBackRequestRunnable());
            return true;
        }
        Log.w(TAG, "requestAudioForReplyReadBack - Invalid input");
        postEventMessage(3);
        this.notificationUuid = "";
        this.replyText = "";
        return false;
    }

    /* renamed from: clone */
    public ReplyReadBackComponent m339clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
