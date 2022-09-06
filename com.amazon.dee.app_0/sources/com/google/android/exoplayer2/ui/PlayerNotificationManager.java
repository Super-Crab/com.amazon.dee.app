package com.google.android.exoplayer2.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.media.app.NotificationCompat;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NotificationUtil;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class PlayerNotificationManager {
    private static final String ACTION_DISMISS = "com.google.android.exoplayer.dismiss";
    public static final String ACTION_FAST_FORWARD = "com.google.android.exoplayer.ffwd";
    public static final String ACTION_NEXT = "com.google.android.exoplayer.next";
    public static final String ACTION_PAUSE = "com.google.android.exoplayer.pause";
    public static final String ACTION_PLAY = "com.google.android.exoplayer.play";
    public static final String ACTION_PREVIOUS = "com.google.android.exoplayer.prev";
    public static final String ACTION_REWIND = "com.google.android.exoplayer.rewind";
    public static final String ACTION_STOP = "com.google.android.exoplayer.stop";
    public static final String EXTRA_INSTANCE_ID = "INSTANCE_ID";
    private static final int MSG_START_OR_UPDATE_NOTIFICATION = 0;
    private static final int MSG_UPDATE_NOTIFICATION_BITMAP = 1;
    private static int instanceIdCounter;
    private int badgeIconType;
    @Nullable
    private NotificationCompat.Builder builder;
    @Nullable
    private List<NotificationCompat.Action> builderActions;
    private final String channelId;
    private int color;
    private boolean colorized;
    private final Context context;
    private ControlDispatcher controlDispatcher;
    private int currentNotificationTag;
    @Nullable
    private final CustomActionReceiver customActionReceiver;
    private final Map<String, NotificationCompat.Action> customActions;
    private int defaults;
    private final PendingIntent dismissPendingIntent;
    private final int instanceId;
    private final IntentFilter intentFilter;
    private boolean isNotificationStarted;
    private final Handler mainHandler;
    private final MediaDescriptionAdapter mediaDescriptionAdapter;
    @Nullable
    private MediaSessionCompat.Token mediaSessionToken;
    private final NotificationBroadcastReceiver notificationBroadcastReceiver;
    private final int notificationId;
    @Nullable
    private NotificationListener notificationListener;
    private final NotificationManagerCompat notificationManager;
    private final Map<String, NotificationCompat.Action> playbackActions;
    @Nullable
    private PlaybackPreparer playbackPreparer;
    @Nullable
    private Player player;
    private final Player.EventListener playerListener;
    private int priority;
    @DrawableRes
    private int smallIconResourceId;
    private boolean useChronometer;
    private boolean useNextAction;
    private boolean useNextActionInCompactView;
    private boolean usePlayPauseActions;
    private boolean usePreviousAction;
    private boolean usePreviousActionInCompactView;
    private boolean useStopAction;
    private int visibility;
    private final Timeline.Window window;

    /* loaded from: classes2.dex */
    public final class BitmapCallback {
        private final int notificationTag;

        public void onBitmap(Bitmap bitmap) {
            if (bitmap != null) {
                PlayerNotificationManager.this.postUpdateNotificationBitmap(bitmap, this.notificationTag);
            }
        }

        private BitmapCallback(int i) {
            this.notificationTag = i;
        }
    }

    /* loaded from: classes2.dex */
    public interface CustomActionReceiver {
        Map<String, NotificationCompat.Action> createCustomActions(Context context, int i);

        List<String> getCustomActions(Player player);

        void onCustomAction(Player player, String str, Intent intent);
    }

    /* loaded from: classes2.dex */
    public interface MediaDescriptionAdapter {
        @Nullable
        PendingIntent createCurrentContentIntent(Player player);

        @Nullable
        CharSequence getCurrentContentText(Player player);

        CharSequence getCurrentContentTitle(Player player);

        @Nullable
        Bitmap getCurrentLargeIcon(Player player, BitmapCallback bitmapCallback);

        @Nullable
        default CharSequence getCurrentSubText(Player player) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class NotificationBroadcastReceiver extends BroadcastReceiver {
        private NotificationBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Player player = PlayerNotificationManager.this.player;
            if (player == null || !PlayerNotificationManager.this.isNotificationStarted || intent.getIntExtra(PlayerNotificationManager.EXTRA_INSTANCE_ID, PlayerNotificationManager.this.instanceId) != PlayerNotificationManager.this.instanceId) {
                return;
            }
            String action = intent.getAction();
            if (PlayerNotificationManager.ACTION_PLAY.equals(action)) {
                if (player.getPlaybackState() == 1) {
                    if (PlayerNotificationManager.this.playbackPreparer != null) {
                        PlayerNotificationManager.this.playbackPreparer.preparePlayback();
                    } else {
                        PlayerNotificationManager.this.controlDispatcher.dispatchPrepare(player);
                    }
                } else if (player.getPlaybackState() == 4) {
                    PlayerNotificationManager.this.controlDispatcher.dispatchSeekTo(player, player.getCurrentWindowIndex(), C.TIME_UNSET);
                }
                PlayerNotificationManager.this.controlDispatcher.dispatchSetPlayWhenReady(player, true);
            } else if (PlayerNotificationManager.ACTION_PAUSE.equals(action)) {
                PlayerNotificationManager.this.controlDispatcher.dispatchSetPlayWhenReady(player, false);
            } else if (PlayerNotificationManager.ACTION_PREVIOUS.equals(action)) {
                PlayerNotificationManager.this.controlDispatcher.dispatchPrevious(player);
            } else if (PlayerNotificationManager.ACTION_REWIND.equals(action)) {
                PlayerNotificationManager.this.controlDispatcher.dispatchRewind(player);
            } else if (PlayerNotificationManager.ACTION_FAST_FORWARD.equals(action)) {
                PlayerNotificationManager.this.controlDispatcher.dispatchFastForward(player);
            } else if (PlayerNotificationManager.ACTION_NEXT.equals(action)) {
                PlayerNotificationManager.this.controlDispatcher.dispatchNext(player);
            } else if (PlayerNotificationManager.ACTION_STOP.equals(action)) {
                PlayerNotificationManager.this.controlDispatcher.dispatchStop(player, true);
            } else if (PlayerNotificationManager.ACTION_DISMISS.equals(action)) {
                PlayerNotificationManager.this.stopNotification(true);
            } else if (action == null || PlayerNotificationManager.this.customActionReceiver == null || !PlayerNotificationManager.this.customActions.containsKey(action)) {
            } else {
                PlayerNotificationManager.this.customActionReceiver.onCustomAction(player, action, intent);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface NotificationListener {
        @Deprecated
        default void onNotificationCancelled(int i) {
        }

        default void onNotificationCancelled(int i, boolean z) {
        }

        default void onNotificationPosted(int i, Notification notification, boolean z) {
        }

        @Deprecated
        default void onNotificationStarted(int i, Notification notification) {
        }
    }

    /* loaded from: classes2.dex */
    private class PlayerListener implements Player.EventListener {
        private PlayerListener() {
        }

        @Override // com.google.android.exoplayer2.Player.EventListener
        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(5, 6, 8, 0, 13, 12, 9, 10)) {
                PlayerNotificationManager.this.postStartOrUpdateNotification();
            }
        }
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Priority {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Visibility {
    }

    public PlayerNotificationManager(Context context, String str, int i, MediaDescriptionAdapter mediaDescriptionAdapter) {
        this(context, str, i, mediaDescriptionAdapter, null, null);
    }

    private static PendingIntent createBroadcastIntent(String str, Context context, int i) {
        Intent intent = new Intent(str).setPackage(context.getPackageName());
        intent.putExtra(EXTRA_INSTANCE_ID, i);
        return PendingIntent.getBroadcast(context, i, intent, 134217728);
    }

    private static Map<String, NotificationCompat.Action> createPlaybackActions(Context context, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(ACTION_PLAY, new NotificationCompat.Action(R.drawable.exo_notification_play, context.getString(R.string.exo_controls_play_description), createBroadcastIntent(ACTION_PLAY, context, i)));
        hashMap.put(ACTION_PAUSE, new NotificationCompat.Action(R.drawable.exo_notification_pause, context.getString(R.string.exo_controls_pause_description), createBroadcastIntent(ACTION_PAUSE, context, i)));
        hashMap.put(ACTION_STOP, new NotificationCompat.Action(R.drawable.exo_notification_stop, context.getString(R.string.exo_controls_stop_description), createBroadcastIntent(ACTION_STOP, context, i)));
        hashMap.put(ACTION_REWIND, new NotificationCompat.Action(R.drawable.exo_notification_rewind, context.getString(R.string.exo_controls_rewind_description), createBroadcastIntent(ACTION_REWIND, context, i)));
        hashMap.put(ACTION_FAST_FORWARD, new NotificationCompat.Action(R.drawable.exo_notification_fastforward, context.getString(R.string.exo_controls_fastforward_description), createBroadcastIntent(ACTION_FAST_FORWARD, context, i)));
        hashMap.put(ACTION_PREVIOUS, new NotificationCompat.Action(R.drawable.exo_notification_previous, context.getString(R.string.exo_controls_previous_description), createBroadcastIntent(ACTION_PREVIOUS, context, i)));
        hashMap.put(ACTION_NEXT, new NotificationCompat.Action(R.drawable.exo_notification_next, context.getString(R.string.exo_controls_next_description), createBroadcastIntent(ACTION_NEXT, context, i)));
        return hashMap;
    }

    @Deprecated
    public static PlayerNotificationManager createWithNotificationChannel(Context context, String str, @StringRes int i, int i2, MediaDescriptionAdapter mediaDescriptionAdapter) {
        return createWithNotificationChannel(context, str, i, 0, i2, mediaDescriptionAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            Player player = this.player;
            if (player != null) {
                startOrUpdateNotification(player, null);
            }
        } else if (i != 1) {
            return false;
        } else {
            Player player2 = this.player;
            if (player2 != null && this.isNotificationStarted && this.currentNotificationTag == message.arg1) {
                startOrUpdateNotification(player2, (Bitmap) message.obj);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postStartOrUpdateNotification() {
        if (!this.mainHandler.hasMessages(0)) {
            this.mainHandler.sendEmptyMessage(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postUpdateNotificationBitmap(Bitmap bitmap, int i) {
        this.mainHandler.obtainMessage(1, i, -1, bitmap).sendToTarget();
    }

    private static void setLargeIcon(NotificationCompat.Builder builder, @Nullable Bitmap bitmap) {
        builder.setLargeIcon(bitmap);
    }

    private boolean shouldShowPauseButton(Player player) {
        return (player.getPlaybackState() == 4 || player.getPlaybackState() == 1 || !player.getPlayWhenReady()) ? false : true;
    }

    private void startOrUpdateNotification(Player player, @Nullable Bitmap bitmap) {
        boolean ongoing = getOngoing(player);
        this.builder = createNotification(player, this.builder, ongoing, bitmap);
        NotificationCompat.Builder builder = this.builder;
        boolean z = false;
        if (builder == null) {
            stopNotification(false);
            return;
        }
        Notification build = builder.build();
        this.notificationManager.notify(this.notificationId, build);
        if (!this.isNotificationStarted) {
            this.context.registerReceiver(this.notificationBroadcastReceiver, this.intentFilter);
            NotificationListener notificationListener = this.notificationListener;
            if (notificationListener != null) {
                notificationListener.onNotificationStarted(this.notificationId, build);
            }
        }
        NotificationListener notificationListener2 = this.notificationListener;
        if (notificationListener2 != null) {
            int i = this.notificationId;
            if (ongoing || !this.isNotificationStarted) {
                z = true;
            }
            notificationListener2.onNotificationPosted(i, build, z);
        }
        this.isNotificationStarted = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopNotification(boolean z) {
        if (this.isNotificationStarted) {
            this.isNotificationStarted = false;
            this.mainHandler.removeMessages(0);
            this.notificationManager.cancel(this.notificationId);
            this.context.unregisterReceiver(this.notificationBroadcastReceiver);
            NotificationListener notificationListener = this.notificationListener;
            if (notificationListener == null) {
                return;
            }
            notificationListener.onNotificationCancelled(this.notificationId, z);
            this.notificationListener.onNotificationCancelled(this.notificationId);
        }
    }

    @Nullable
    protected NotificationCompat.Builder createNotification(Player player, @Nullable NotificationCompat.Builder builder, boolean z, @Nullable Bitmap bitmap) {
        NotificationCompat.Action action;
        if (player.getPlaybackState() == 1 && player.getCurrentTimeline().isEmpty()) {
            this.builderActions = null;
            return null;
        }
        List<String> actions = getActions(player);
        ArrayList arrayList = new ArrayList(actions.size());
        for (int i = 0; i < actions.size(); i++) {
            String str = actions.get(i);
            if (this.playbackActions.containsKey(str)) {
                action = this.playbackActions.get(str);
            } else {
                action = this.customActions.get(str);
            }
            if (action != null) {
                arrayList.add(action);
            }
        }
        if (builder == null || !arrayList.equals(this.builderActions)) {
            builder = new NotificationCompat.Builder(this.context, this.channelId);
            this.builderActions = arrayList;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                builder.addAction((NotificationCompat.Action) arrayList.get(i2));
            }
        }
        NotificationCompat.MediaStyle mediaStyle = new NotificationCompat.MediaStyle();
        MediaSessionCompat.Token token = this.mediaSessionToken;
        if (token != null) {
            mediaStyle.setMediaSession(token);
        }
        mediaStyle.setShowActionsInCompactView(getActionIndicesForCompactView(actions, player));
        mediaStyle.setShowCancelButton(!z);
        mediaStyle.setCancelButtonIntent(this.dismissPendingIntent);
        builder.setStyle(mediaStyle);
        builder.setDeleteIntent(this.dismissPendingIntent);
        builder.setBadgeIconType(this.badgeIconType).setOngoing(z).setColor(this.color).setColorized(this.colorized).setSmallIcon(this.smallIconResourceId).setVisibility(this.visibility).setPriority(this.priority).setDefaults(this.defaults);
        if (Util.SDK_INT >= 21 && this.useChronometer && player.isPlaying() && !player.isPlayingAd() && !player.isCurrentWindowDynamic() && player.getPlaybackParameters().speed == 1.0f) {
            builder.setWhen(System.currentTimeMillis() - player.getContentPosition()).setShowWhen(true).setUsesChronometer(true);
        } else {
            builder.setShowWhen(false).setUsesChronometer(false);
        }
        builder.setContentTitle(this.mediaDescriptionAdapter.getCurrentContentTitle(player));
        builder.setContentText(this.mediaDescriptionAdapter.getCurrentContentText(player));
        builder.setSubText(this.mediaDescriptionAdapter.getCurrentSubText(player));
        if (bitmap == null) {
            MediaDescriptionAdapter mediaDescriptionAdapter = this.mediaDescriptionAdapter;
            int i3 = this.currentNotificationTag + 1;
            this.currentNotificationTag = i3;
            bitmap = mediaDescriptionAdapter.getCurrentLargeIcon(player, new BitmapCallback(i3));
        }
        setLargeIcon(builder, bitmap);
        builder.setContentIntent(this.mediaDescriptionAdapter.createCurrentContentIntent(player));
        return builder;
    }

    protected int[] getActionIndicesForCompactView(List<String> list, Player player) {
        int i;
        int indexOf = list.indexOf(ACTION_PAUSE);
        int indexOf2 = list.indexOf(ACTION_PLAY);
        int indexOf3 = this.usePreviousActionInCompactView ? list.indexOf(ACTION_PREVIOUS) : -1;
        int indexOf4 = this.useNextActionInCompactView ? list.indexOf(ACTION_NEXT) : -1;
        int[] iArr = new int[3];
        int i2 = 0;
        if (indexOf3 != -1) {
            iArr[0] = indexOf3;
            i2 = 1;
        }
        boolean shouldShowPauseButton = shouldShowPauseButton(player);
        if (indexOf != -1 && shouldShowPauseButton) {
            i = i2 + 1;
            iArr[i2] = indexOf;
        } else if (indexOf2 == -1 || shouldShowPauseButton) {
            i = i2;
        } else {
            i = i2 + 1;
            iArr[i2] = indexOf2;
        }
        if (indexOf4 != -1) {
            iArr[i] = indexOf4;
            i++;
        }
        return Arrays.copyOf(iArr, i);
    }

    protected List<String> getActions(Player player) {
        boolean z;
        boolean z2;
        boolean z3;
        Timeline currentTimeline = player.getCurrentTimeline();
        boolean z4 = false;
        if (currentTimeline.isEmpty() || player.isPlayingAd()) {
            z = false;
            z2 = false;
            z3 = false;
        } else {
            currentTimeline.getWindow(player.getCurrentWindowIndex(), this.window);
            Timeline.Window window = this.window;
            boolean z5 = window.isSeekable;
            z = z5 || !window.isLive() || player.hasPrevious();
            boolean z6 = z5 && this.controlDispatcher.isRewindEnabled();
            z2 = z5 && this.controlDispatcher.isFastForwardEnabled();
            if ((this.window.isLive() && this.window.isDynamic) || player.hasNext()) {
                z4 = true;
            }
            z3 = z4;
            z4 = z6;
        }
        ArrayList arrayList = new ArrayList();
        if (this.usePreviousAction && z) {
            arrayList.add(ACTION_PREVIOUS);
        }
        if (z4) {
            arrayList.add(ACTION_REWIND);
        }
        if (this.usePlayPauseActions) {
            if (shouldShowPauseButton(player)) {
                arrayList.add(ACTION_PAUSE);
            } else {
                arrayList.add(ACTION_PLAY);
            }
        }
        if (z2) {
            arrayList.add(ACTION_FAST_FORWARD);
        }
        if (this.useNextAction && z3) {
            arrayList.add(ACTION_NEXT);
        }
        CustomActionReceiver customActionReceiver = this.customActionReceiver;
        if (customActionReceiver != null) {
            arrayList.addAll(customActionReceiver.getCustomActions(player));
        }
        if (this.useStopAction) {
            arrayList.add(ACTION_STOP);
        }
        return arrayList;
    }

    protected boolean getOngoing(Player player) {
        int playbackState = player.getPlaybackState();
        return (playbackState == 2 || playbackState == 3) && player.getPlayWhenReady();
    }

    public void invalidate() {
        if (this.isNotificationStarted) {
            postStartOrUpdateNotification();
        }
    }

    public final void setBadgeIconType(int i) {
        if (this.badgeIconType == i) {
            return;
        }
        if (i != 0 && i != 1 && i != 2) {
            throw new IllegalArgumentException();
        }
        this.badgeIconType = i;
        invalidate();
    }

    public final void setColor(int i) {
        if (this.color != i) {
            this.color = i;
            invalidate();
        }
    }

    public final void setColorized(boolean z) {
        if (this.colorized != z) {
            this.colorized = z;
            invalidate();
        }
    }

    public final void setControlDispatcher(ControlDispatcher controlDispatcher) {
        if (this.controlDispatcher != controlDispatcher) {
            this.controlDispatcher = controlDispatcher;
            invalidate();
        }
    }

    public final void setDefaults(int i) {
        if (this.defaults != i) {
            this.defaults = i;
            invalidate();
        }
    }

    @Deprecated
    public final void setFastForwardIncrementMs(long j) {
        ControlDispatcher controlDispatcher = this.controlDispatcher;
        if (controlDispatcher instanceof DefaultControlDispatcher) {
            ((DefaultControlDispatcher) controlDispatcher).setFastForwardIncrementMs(j);
            invalidate();
        }
    }

    public final void setMediaSessionToken(MediaSessionCompat.Token token) {
        if (!Util.areEqual(this.mediaSessionToken, token)) {
            this.mediaSessionToken = token;
            invalidate();
        }
    }

    @Deprecated
    public final void setNotificationListener(NotificationListener notificationListener) {
        this.notificationListener = notificationListener;
    }

    @Deprecated
    public void setPlaybackPreparer(@Nullable PlaybackPreparer playbackPreparer) {
        this.playbackPreparer = playbackPreparer;
    }

    public final void setPlayer(@Nullable Player player) {
        boolean z = true;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (player != null && player.getApplicationLooper() != Looper.getMainLooper()) {
            z = false;
        }
        Assertions.checkArgument(z);
        Player player2 = this.player;
        if (player2 == player) {
            return;
        }
        if (player2 != null) {
            player2.removeListener(this.playerListener);
            if (player == null) {
                stopNotification(false);
            }
        }
        this.player = player;
        if (player == null) {
            return;
        }
        player.addListener(this.playerListener);
        postStartOrUpdateNotification();
    }

    public final void setPriority(int i) {
        if (this.priority == i) {
            return;
        }
        if (i != -2 && i != -1 && i != 0 && i != 1 && i != 2) {
            throw new IllegalArgumentException();
        }
        this.priority = i;
        invalidate();
    }

    @Deprecated
    public final void setRewindIncrementMs(long j) {
        ControlDispatcher controlDispatcher = this.controlDispatcher;
        if (controlDispatcher instanceof DefaultControlDispatcher) {
            ((DefaultControlDispatcher) controlDispatcher).setRewindIncrementMs(j);
            invalidate();
        }
    }

    public final void setSmallIcon(@DrawableRes int i) {
        if (this.smallIconResourceId != i) {
            this.smallIconResourceId = i;
            invalidate();
        }
    }

    public final void setUseChronometer(boolean z) {
        if (this.useChronometer != z) {
            this.useChronometer = z;
            invalidate();
        }
    }

    @Deprecated
    public final void setUseNavigationActions(boolean z) {
        setUseNextAction(z);
        setUsePreviousAction(z);
    }

    @Deprecated
    public final void setUseNavigationActionsInCompactView(boolean z) {
        setUseNextActionInCompactView(z);
        setUsePreviousActionInCompactView(z);
    }

    public void setUseNextAction(boolean z) {
        if (this.useNextAction != z) {
            this.useNextAction = z;
            invalidate();
        }
    }

    public void setUseNextActionInCompactView(boolean z) {
        if (this.useNextActionInCompactView != z) {
            this.useNextActionInCompactView = z;
            invalidate();
        }
    }

    public final void setUsePlayPauseActions(boolean z) {
        if (this.usePlayPauseActions != z) {
            this.usePlayPauseActions = z;
            invalidate();
        }
    }

    public void setUsePreviousAction(boolean z) {
        if (this.usePreviousAction != z) {
            this.usePreviousAction = z;
            invalidate();
        }
    }

    public void setUsePreviousActionInCompactView(boolean z) {
        if (this.usePreviousActionInCompactView != z) {
            this.usePreviousActionInCompactView = z;
            invalidate();
        }
    }

    public final void setUseStopAction(boolean z) {
        if (this.useStopAction == z) {
            return;
        }
        this.useStopAction = z;
        invalidate();
    }

    public final void setVisibility(int i) {
        if (this.visibility == i) {
            return;
        }
        if (i != -1 && i != 0 && i != 1) {
            throw new IllegalStateException();
        }
        this.visibility = i;
        invalidate();
    }

    public PlayerNotificationManager(Context context, String str, int i, MediaDescriptionAdapter mediaDescriptionAdapter, @Nullable NotificationListener notificationListener) {
        this(context, str, i, mediaDescriptionAdapter, notificationListener, null);
    }

    public static PlayerNotificationManager createWithNotificationChannel(Context context, String str, @StringRes int i, @StringRes int i2, int i3, MediaDescriptionAdapter mediaDescriptionAdapter) {
        NotificationUtil.createNotificationChannel(context, str, i, i2, 2);
        return new PlayerNotificationManager(context, str, i3, mediaDescriptionAdapter);
    }

    public PlayerNotificationManager(Context context, String str, int i, MediaDescriptionAdapter mediaDescriptionAdapter, @Nullable CustomActionReceiver customActionReceiver) {
        this(context, str, i, mediaDescriptionAdapter, null, customActionReceiver);
    }

    public PlayerNotificationManager(Context context, String str, int i, MediaDescriptionAdapter mediaDescriptionAdapter, @Nullable NotificationListener notificationListener, @Nullable CustomActionReceiver customActionReceiver) {
        Map<String, NotificationCompat.Action> emptyMap;
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        this.channelId = str;
        this.notificationId = i;
        this.mediaDescriptionAdapter = mediaDescriptionAdapter;
        this.notificationListener = notificationListener;
        this.customActionReceiver = customActionReceiver;
        this.controlDispatcher = new DefaultControlDispatcher();
        this.window = new Timeline.Window();
        int i2 = instanceIdCounter;
        instanceIdCounter = i2 + 1;
        this.instanceId = i2;
        this.mainHandler = Util.createHandler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.exoplayer2.ui.-$$Lambda$PlayerNotificationManager$ZG0s-7uOzb-YtyUlp8ahx3j4wBg
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean handleMessage;
                handleMessage = PlayerNotificationManager.this.handleMessage(message);
                return handleMessage;
            }
        });
        this.notificationManager = NotificationManagerCompat.from(applicationContext);
        this.playerListener = new PlayerListener();
        this.notificationBroadcastReceiver = new NotificationBroadcastReceiver();
        this.intentFilter = new IntentFilter();
        this.usePreviousAction = true;
        this.useNextAction = true;
        this.usePlayPauseActions = true;
        this.colorized = true;
        this.useChronometer = true;
        this.color = 0;
        this.smallIconResourceId = R.drawable.exo_notification_small_icon;
        this.defaults = 0;
        this.priority = -1;
        this.badgeIconType = 1;
        this.visibility = 1;
        this.playbackActions = createPlaybackActions(applicationContext, this.instanceId);
        for (String str2 : this.playbackActions.keySet()) {
            this.intentFilter.addAction(str2);
        }
        if (customActionReceiver != null) {
            emptyMap = customActionReceiver.createCustomActions(applicationContext, this.instanceId);
        } else {
            emptyMap = Collections.emptyMap();
        }
        this.customActions = emptyMap;
        for (String str3 : this.customActions.keySet()) {
            this.intentFilter.addAction(str3);
        }
        this.dismissPendingIntent = createBroadcastIntent(ACTION_DISMISS, applicationContext, this.instanceId);
        this.intentFilter.addAction(ACTION_DISMISS);
    }

    @Deprecated
    public static PlayerNotificationManager createWithNotificationChannel(Context context, String str, @StringRes int i, int i2, MediaDescriptionAdapter mediaDescriptionAdapter, @Nullable NotificationListener notificationListener) {
        return createWithNotificationChannel(context, str, i, 0, i2, mediaDescriptionAdapter, notificationListener);
    }

    public static PlayerNotificationManager createWithNotificationChannel(Context context, String str, @StringRes int i, @StringRes int i2, int i3, MediaDescriptionAdapter mediaDescriptionAdapter, @Nullable NotificationListener notificationListener) {
        NotificationUtil.createNotificationChannel(context, str, i, i2, 2);
        return new PlayerNotificationManager(context, str, i3, mediaDescriptionAdapter, notificationListener);
    }
}
