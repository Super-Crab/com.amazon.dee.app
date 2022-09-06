package com.amazon.alexa.accessory.notificationpublisher.renderer;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.AccessoryTtsStateManager;
import com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
/* loaded from: classes.dex */
public class Renderer {
    protected static final String DEFAULT_EARCON_FOR_DELIMITER = "/assets/delimiter.mp3";
    protected static final String DEFAULT_EARCON_FOR_INCOMING_NOTIFICATION = "/assets/inc_notif.mp3";
    protected static final String DEFAULT_EARCON_FOR_INVITATION_ACCEPTED = "/assets/invitation_accept_earcon.mp3";
    protected static final String DEFAULT_EARCON_FOR_INVITATION_ALERT = "/assets/invitation_alert_earcon.mp3";
    protected static final String DEFAULT_EARCON_FOR_INVITATION_REJECTED = "/assets/invitation_reject_earcon.mp3";
    protected static final String DEFAULT_EARCON_FOR_PLAY_GESTURE = "/assets/swipe.mp3";
    protected static final String DEFAULT_EARCON_FOR_STOP_GESTURE = "/assets/tap.mp3";
    protected static final String DEFAULT_EARCON_REPLY_INITIATE = "/assets/zef_reply_initiate.mp3";
    protected static final String DEFAULT_GENERIC_ERROR_EARCON = "/assets/zef_system_state_error_generic.mp3";
    protected static final String DEFAULT_SILENCE_EARCON = "/assets/silence_300ms.mp3";
    protected static final String DELIMITER_EARCON_REQUEST_ID = "delimiterEarcon";
    protected static final String GENERIC_ERROR_EARCON_REQUEST_ID = "genericErrorEarcon";
    protected static final String INCOMING_NOTIFICATION_EARCON_REQUEST_ID = "incomingEarcon";
    protected static final String INVITATION_ACCEPTED_EARCON_REQUEST_ID = "invitationAcceptedEarcon";
    protected static final String INVITATION_ALERT_EARCON_REQUEST_ID = "invitationAlertEarcon";
    protected static final String INVITATION_REJECTED_EARCON_REQUEST_ID = "invitationRejectedEarcon";
    protected static final String LOW_DISTRACTION_ANNOUNCEMENT_EARCON_REQUEST_ID = "lowDistractionAnnouncementEarcon";
    protected static final String LOW_DISTRACTION_INVITATION_EARCON_REQUEST_ID = "lowDistractionInvitationEarcon";
    protected static final String LOW_DISTRACTION_INVITATION_PLAY_EARCON_REQUEST_ID = "lowDistractionInvitationPlayEarcon";
    protected static final String PLAY_GESTURE_EARCON_REQUEST_ID = "swipeEarcon";
    protected static final String REPLY_INITIATE_EARCON_REQUEST_ID = "replyInitiateEarcon";
    protected static final String SILENCE_EARCON_REQUEST_ID = "silenceEarcon";
    protected static final String SILENT_DISTRACTION_SKIP_EARCON_REQUEST_ID = "silentDistractionSkipEarconTts";
    protected static final String STOP_GESTURE_EARCON_REQUEST_ID = "tapEarcon";
    private Context context;

    public Renderer(@NonNull Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void playEarcon(@NonNull String str, @NonNull String str2, @Nullable AudioFilePlayer.AudioPlaybackCompleteListener audioPlaybackCompleteListener) {
        AudioFilePlayer.getInstance().playAudioAsset(this.context, str, str2, audioPlaybackCompleteListener);
    }

    public void playTTSAudioFile(@NonNull String str, @NonNull String str2, @NonNull AudioFilePlayer.AudioPlaybackCompleteListener audioPlaybackCompleteListener) {
        AudioFilePlayer.getInstance().playAudioFile(this.context, str, str2, audioPlaybackCompleteListener);
        AccessoryTtsStateManager.getInstance().updateAccessoryTtsState(str);
        AudioInteractionScheduler.getInstance().setNotificationPlaybackState(AudioInteractionScheduler.NotificationPlaybackState.PLAYING, "playTTSAudioFile");
    }

    public void stopAllAudio() {
        AudioFilePlayer.getInstance().stopAllAudio();
        AudioInteractionScheduler.getInstance().setNotificationPlaybackState(AudioInteractionScheduler.NotificationPlaybackState.NONE, "stopAllAudio");
    }

    public void stopAudioFile(@NonNull String str) {
        AudioFilePlayer.getInstance().stopAudioPlayback(str);
    }
}
