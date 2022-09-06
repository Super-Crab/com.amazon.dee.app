package com.amazon.deecomms.common.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.audio.RingTonePlaybackAuthority;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class IncomingPushAndroidService extends DeviceCallingAndroidService {
    private static final int HEADUP_NOTIFICATION_TIMEOUT = 58000;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, IncomingPushAndroidService.class);
    public static final int MAX_SIP_INVITE_WAITING_TIME = 15000;
    private Handler handler;
    @Inject
    AlexaAudioPlayer mAlexaAudioPlayer;
    @Inject
    RingTonePlaybackAuthority ringTonePlaybackAuthority;
    private Runnable runnable;

    public IncomingPushAndroidService() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    @RequiresApi(api = 26)
    private void createHeadupNotification(String str) {
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService(NotificationManager.class);
        if (notificationManager.getNotificationChannel(Constants.NOTIFICATIONS_CALLING_HEADUP_CHANNEL) == null) {
            notificationManager.createNotificationChannel(new NotificationChannel(Constants.NOTIFICATIONS_CALLING_HEADUP_CHANNEL, getString(R.string.alexa_call), 4));
        }
        startForeground(3, createCommsNotificationBuilder(str).build());
        LOG.d("[Comms-calling]: Fire createHeadupNotification");
    }

    public static void dismissHeadupNotification(Context context) {
        Intent intent = new Intent(context, IncomingPushAndroidService.class);
        intent.putExtra(Constants.REGISTRATION_EVENT_TYPE_KEY, 100);
        context.startService(intent);
    }

    private Spannable getActionText(@StringRes int i, @ColorRes int i2) {
        SpannableString spannableString = new SpannableString(this.mContext.getText(i));
        int i3 = Build.VERSION.SDK_INT;
        spannableString.setSpan(new ForegroundColorSpan(this.mContext.getColor(i2)), 0, spannableString.length(), 0);
        return spannableString;
    }

    private PendingIntent getAnswerIntent() {
        Intent intent = new Intent(this, NewCallActivity.class);
        intent.setFlags(268435456);
        intent.putExtra(Constants.START_FROM_SERVICE, true);
        intent.putExtra(Constants.KEY_HAVE_ANSWERED_CALL, true);
        intent.putExtra(Constants.KEY_HAVE_DECLINED_CALL, false);
        return PendingIntent.getActivity(this, 100, intent, 134217728);
    }

    private PendingIntent getDeclineIntent() {
        Intent intent = new Intent(this, NewCallActivity.class);
        intent.setFlags(268435456);
        intent.putExtra(Constants.START_FROM_SERVICE, true);
        intent.putExtra(Constants.KEY_HAVE_ANSWERED_CALL, false);
        intent.putExtra(Constants.KEY_HAVE_DECLINED_CALL, true);
        return PendingIntent.getActivity(this, 101, intent, 134217728);
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this, NewCallActivity.class);
        intent.setFlags(268435456);
        intent.putExtra(Constants.START_FROM_SERVICE, true);
        intent.putExtra(Constants.KEY_STARTED_FROM_PENDING_INTENT, true);
        return PendingIntent.getActivity(this, 0, intent, 134217728);
    }

    private void playIncomingRingToneIfNeeded() {
        if (this.ringTonePlaybackAuthority.canPlayRingTone()) {
            this.mAlexaAudioPlayer.play(R.raw.alexa_app_system_comm_call_incoming_ringtone, true, 2, true, 1);
        }
    }

    public static void showHeadupIncomingCallNotification(Context context, String str) {
        Intent intent = new Intent(context, IncomingPushAndroidService.class);
        intent.putExtra(Constants.SHOULD_START_FOREGROUND_NOTI, true);
        intent.putExtra(Constants.KEY_CALLER_NAME, str);
        if (Utils.getBooleanPreferenceFromSharedPrefs(context, Constants.IS_APP_IN_FOREGROUND, false) || !Utils.isAndroid10AndAbove()) {
            return;
        }
        LOG.d("[Comms-calling]: App is in background. startForegroundService");
        context.startForegroundService(intent);
    }

    private void startTimer(int i) {
        stopTimer();
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.amazon.deecomms.common.service.-$$Lambda$IncomingPushAndroidService$Xe_g6lgSaJC_408w3te_j8ImjK4
            @Override // java.lang.Runnable
            public final void run() {
                IncomingPushAndroidService.this.lambda$startTimer$0$IncomingPushAndroidService();
            }
        };
        this.handler.postDelayed(this.runnable, i);
        LOG.d("[Comms-calling]: startTimer");
    }

    private void stopTimer() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
            this.handler = null;
            this.runnable = null;
            LOG.d("[Comms-calling]: stopTimer");
        }
    }

    @VisibleForTesting
    NotificationCompat.Builder createCommsNotificationBuilder(String str) {
        return new NotificationCompat.Builder(this, Constants.NOTIFICATIONS_CALLING_HEADUP_CHANNEL).setSmallIcon(R.drawable.app_icon).setContentTitle(getString(R.string.alexa_call)).setContentText(str).setPriority(1).setCategory("call").setFullScreenIntent(getPendingIntent(), true).addAction(R.drawable.btn_comms_backing_answer_normal, getActionText(R.string.calling_answer, R.color.blueThemeAction10), getAnswerIntent()).addAction(R.drawable.btn_comms_backing_answer_normal, getActionText(R.string.calling_decline, R.color.error_red), getDeclineIntent()).setVisibility(1);
    }

    public /* synthetic */ void lambda$startTimer$0$IncomingPushAndroidService() {
        stopForeground(true);
        this.mAlexaAudioPlayer.stop(1);
        LOG.d("[Comms-calling]: timer is fired.");
    }

    @Override // com.amazon.deecomms.common.service.DeviceCallingAndroidService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LOG.d("[Comms-calling]: onDestroy");
        stopTimer();
    }

    @Override // com.amazon.deecomms.common.service.DeviceCallingAndroidService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LOG.d("[Comms-calling]: onStartCommand");
        if (intent == null) {
            return super.onStartCommand(intent, i, i2);
        }
        Bundle extras = intent.getExtras();
        if (extras.containsKey(Constants.SHOULD_START_FOREGROUND_NOTI) && extras.getBoolean(Constants.SHOULD_START_FOREGROUND_NOTI, false) && Utils.isAndroid10AndAbove()) {
            createHeadupNotification(extras.getString(Constants.KEY_CALLER_NAME, ""));
            playIncomingRingToneIfNeeded();
            startTimer(extras.containsKey(Constants.AMP_KEY) ? 15000 : HEADUP_NOTIFICATION_TIMEOUT);
        }
        if (extras.containsKey(Constants.REGISTRATION_EVENT_TYPE_KEY) && extras.getInt(Constants.REGISTRATION_EVENT_TYPE_KEY, 0) == 100) {
            LOG.d("[Comms-calling]: stopForeground");
            stopForeground(true);
            stopTimer();
        }
        return super.onStartCommand(intent, i, i2);
    }
}
