package com.amazon.alexa.accessory.notificationpublisher.renderer;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.AccessoryTtsStateManager;
import com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer;
import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
import com.amazon.alexa.accessory.notificationpublisher.consumption.BaseComponent;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class RenderManager extends BaseComponent {
    public static final int ACTIVE_EVENT_RENDERER_CANCEL = 2;
    public static final int ACTIVE_EVENT_RENDERER_PLAY = 1;
    public static final String APP_ACCEPTED_FILE = "/assets/app_accepted.mp3";
    public static final String APP_ACCEPTED_UUID = "APP_ACCEPTED_UUID";
    public static final String APP_REJECTED_FILE = "/assets/app_rejected.mp3";
    public static final String APP_REJECTED_UUID = "APP_REJECTED_UUID";
    private static final String ASSETS_DIRECTORY = "/assets/";
    public static final String CALENDAR_ACCEPTED_FILE = "/assets/calendar_accepted.mp3";
    public static final String CALENDAR_ACCEPTED_UUID = "CALENDAR_ACCEPTED_UUID";
    public static final int CALLBACK_EVENT_RENDERER_COMPLETED = 3;
    public static final int CALLBACK_EVENT_RENDERER_ERROR = 5;
    public static final int CALLBACK_EVENT_RENDERER_STOPPED = 4;
    public static final String CONTACT_ACCEPTED_FILE = "/assets/contact_accepted.mp3";
    public static final String CONTACT_ACCEPTED_UUID = "CONTACT_ACCEPTED_UUID";
    public static final String CONTACT_REJECTED_FILE = "/assets/contact_rejected.mp3";
    public static final String CONTACT_REJECTED_UUID = "CONTACT_REJECTED_UUID";
    public static final String ENABLE_MICROPHONE_PERMISSION_FILE = "/assets/zef_reply_permission.mp3";
    public static final String ENABLE_MICROPHONE_PERMISSION_UUID = "ENABLE_MICROPHONE_PERMISSION_UUID";
    public static final int EVENT_RENDERER_DELIMITER_EARCON = 4;
    public static final int EVENT_RENDERER_DELIMITER_EARCON_THEN_TTS = 104;
    public static final int EVENT_RENDERER_GENERIC_ERROR_EARCON = 13;
    public static final int EVENT_RENDERER_GENERIC_ERROR_EARCON_THEN_TTS = 113;
    public static final int EVENT_RENDERER_GESTURE_PLAY_EARCON = 1;
    public static final int EVENT_RENDERER_GESTURE_PLAY_EARCON_THEN_TTS = 101;
    public static final int EVENT_RENDERER_GESTURE_STOP_EARCON = 2;
    public static final int EVENT_RENDERER_GESTURE_STOP_EARCON_THEN_TTS = 102;
    public static final int EVENT_RENDERER_GROUP_MESSAGES_USER_EDUCATION_TTS = 11;
    public static final int EVENT_RENDERER_INCOMING_EARCON = 3;
    public static final int EVENT_RENDERER_INCOMING_EARCON_THEN_TTS = 103;
    public static final int EVENT_RENDERER_INVITATION_ACCEPT_EARCON = 6;
    public static final int EVENT_RENDERER_INVITATION_ACCEPT_EARCON_THEN_TTS = 106;
    public static final int EVENT_RENDERER_INVITATION_ALERT_EARCON = 5;
    public static final int EVENT_RENDERER_INVITATION_ALERT_EARCON_THEN_TTS = 105;
    public static final int EVENT_RENDERER_INVITATION_REJECT_EARCON = 7;
    public static final int EVENT_RENDERER_INVITATION_REJECT_EARCON_THEN_TTS = 107;
    public static final int EVENT_RENDERER_LOW_DISTRACTION_ANNOUNCEMENT_EARCON = 8;
    public static final int EVENT_RENDERER_LOW_DISTRACTION_INVITATION_EARCON = 9;
    public static final int EVENT_RENDERER_LOW_DISTRACTION_INVITATION_PLAY_EARCON = 10;
    public static final int EVENT_RENDERER_LOW_DISTRACTION_INVITATION_PLAY_EARCON_THEN_TTS = 110;
    public static final int EVENT_RENDERER_MULTIPLE_NOTIFICATIONS_INSTRUCTION_TTS = 12;
    public static final int EVENT_RENDERER_REPLY_INITIATE_EARCON = 15;
    public static final int EVENT_RENDERER_REPLY_INITIATE_EARCON_THEN_TTS = 115;
    public static final int EVENT_RENDERER_SILENCE_EARCON = 14;
    public static final int EVENT_RENDERER_SILENCE_EARCON_THEN_TTS = 114;
    public static final int EVENT_RENDERER_TTS = 100;
    public static final String FIRST_TIME_APP_REJECTED_FILE = "/assets/app_rejected_first_time.mp3";
    public static final String FIRST_TIME_CONTACT_REJECTED_FILE = "/assets/contact_rejected_first_time.mp3";
    public static final String FIRST_TIME_GROUP_NAMED_REJECTED_FILE = "/assets/group_named_rejected_first_time.mp3";
    public static final String FIRST_TIME_OPEN_NOTIFICATION_FILE = "/assets/first_time_open_instruction.mp3";
    public static final String GROUP_MESSAGES_USER_EDUCATION_FILE = "/assets/group_message_prompt.mp3";
    public static final String GROUP_MESSAGES_USER_EDUCATION_UUID = "GROUP_MESSAGES_USER_EDUCATION";
    public static final String GROUP_NAMED_ACCEPTED_FILE = "/assets/group_named_accepted.mp3";
    public static final String GROUP_NAMED_ACCEPTED_UUID = "GROUP_NAMED_ACCEPTED_UUID";
    public static final String GROUP_NAMED_REJECTED_FILE = "/assets/group_named_rejected.mp3";
    public static final String GROUP_NAMED_REJECTED_UUID = "GROUP_NAMED_REJECTED_UUID";
    public static final String GROUP_UNNAMED_ACCEPTED_UUID = "GROUP_UNNAMED_ACCEPTED_UUID";
    public static final String GROUP_UNNAMED_NO_NAMING_ACCEPTED_FILE = "/assets/group_unnamed_accepted_no_naming.mp3";
    public static final String GROUP_UNNAMED_NO_NAMING_REJECTED_FILE = "/assets/group_unnamed_rejected_no_naming.mp3";
    public static final String GROUP_UNNAMED_REJECTED_UUID = "GROUP_UNNAMED_REJECTED_UUID";
    public static final String GROUP_UNNAMED_SUPPORT_NAMING_ACCEPTED_FILE = "/assets/group_unnamed_accepted_support_naming.mp3";
    public static final String GROUP_UNNAMED_SUPPORT_NAMING_REJECTED_FILE = "/assets/group_unnamed_rejected_support_naming.mp3";
    public static final String MULTIPLE_NOTIFICATIONS_INSTRUCTION_FILE = "/assets/multiple_notifications_instructions.mp3";
    public static final String MULTIPLE_NOTIFICATIONS_INSTRUCTION_UUID = "MULTIPLE_NOTIFICATIONS_INSTRUCTION_UUID";
    public static final String NOTIFICATIONS_ARE_OFF_FILE = "/assets/zef_notif_off.mp3";
    public static final String NOTIFICATIONS_ARE_OFF_UUID = "NOTIFICATIONS_ARE_OFF_UUID";
    public static final String NO_RECENT_NOTIFICATION_FILE = "/assets/no_recent_notif.mp3";
    public static final String NO_RECENT_NOTIFICATION_UUID = "NO_RECENT_NOTIFICATION_UUID";
    public static final String OPEN_NOTIFICATION_UUID = "OPEN_NOTIFICATION_UUID";
    public static final String OTG_FIRST_TIME_OPEN_NOTIFICATION_FILE = "/assets/otg_first_time_open_instruction.mp3";
    public static final String OTG_NOTIFICATION_SOUNDS_OFF_FILE = "/assets/otg_notification_sounds_off.mp3";
    public static final String OTG_NOTIFICATION_SOUNDS_OFF_UUID = "NOTIFICATION_SOUNDS_OFF_UUID";
    public static final String OTG_NOTIFICATION_SOUNDS_ON_FILE = "/assets/otg_notification_sounds_on.mp3";
    public static final String OTG_NOTIFICATION_SOUNDS_ON_UUID = "NOTIFICATION_SOUNDS_ON_UUID";
    public static final String OTG_PHONE_NOTIFICATIONS_OFF_FILE = "/assets/otg_phone_notifications_off.mp3";
    public static final String OTG_PHONE_NOTIFICATIONS_OFF_UUID = "PHONE_NOTIFICATIONS_OFF_UUID";
    public static final String OTG_PHONE_NOTIFICATIONS_ON_FILE = "/assets/otg_phone_notifications_on.mp3";
    public static final String OTG_PHONE_NOTIFICATIONS_ON_UUID = "PHONE_NOTIFICATIONS_ON_UUID";
    public static final String OTG_SECOND_TIME_OPEN_NOTIFICATION_FILE = "/assets/otg_second_time_open_instruction.mp3";
    public static final String OTG_THIRD_TIME_OPEN_NOTIFICATION_FILE = "/assets/otg_third_time_open_instruction.mp3";
    public static final String REPLY_AGAIN_FILE = "/assets/zef_reply_again.mp3";
    public static final String REPLY_AGAIN_UUID = "REPLY_AGAIN_UUID";
    public static final String REPLY_CANCELED_INSTRUCTION_FILE = "/assets/zef_reply_cancelled.mp3";
    public static final String REPLY_CANCELED_INSTRUCTION_UUID = "REPLY_CANCELED_INSTRUCTION_UUID";
    public static final String REPLY_ERROR_FILE = "/assets/zef_reply_error.mp3";
    public static final String REPLY_ERROR_RETRY_AVAILABLE_FILE = "/assets/zef_encountered_error.mp3";
    public static final String REPLY_ERROR_RETRY_AVAILABLE_UUID = "REPLY_ERROR_RETRY_AVAILABLE_UUID";
    public static final String REPLY_ERROR_RETRY_LIMIT_REACHED_UUID = "REPLY_ERROR_RETRY_LIMIT_REACHED_UUID";
    public static final String REPLY_ERROR_UUID = "REPLY_ERROR_UUID";
    public static final String REPLY_FEATURE_EDUCATION_FILE = "/assets/zef_reply_ftu_education.mp3";
    public static final String REPLY_FEATURE_EDUCATION_UUID = "REPLY_FEATURE_EDUCATION_UUID";
    public static final String REPLY_FIRST_PROMPT_FOR_RECORDING_FILE = "/assets/zef_say_message_after_tone_first.mp3";
    public static final String REPLY_PROMPT_FOR_RECORDING_FILE = "/assets/zef_say_message_after_tone.mp3";
    public static final String REPLY_PROMPT_FOR_RECORDING_UUID = "REPLY_PROMPT_FOR_RECORDING_UUID";
    public static final String REPLY_READ_BACK_PROCESSING_FILE = "/assets/zef_reply_read_back_cue.mp3";
    public static final String REPLY_READ_BACK_PROCESSING_UUID = "REPLY_READ_BACK_PROCESSING_UUID";
    public static final String REPLY_SECOND_PROMPT_FOR_RECORDING_FILE = "/assets/zef_say_message_after_tone_second.mp3";
    public static final String REPLY_SENDING_FILE = "/assets/zef_reply_sending.mp3";
    public static final String REPLY_SENDING_UUID = "REPLY_SENDING_UUID";
    public static final String REPLY_THIRD_PROMPT_FOR_RECORDING_FILE = "/assets/zef_say_message_after_tone_third.mp3";
    public static final String SECOND_TIME_OPEN_NOTIFICATION_FILE = "/assets/second_time_open_instruction.mp3";
    public static final String START_REPLY_INSTRUCTION_FILE = "/assets/zef_reply_instruction.mp3";
    public static final String START_REPLY_INSTRUCTION_UUID = "START_REPLY_INSTRUCTION_UUID";
    private static final String TAG = "RenderManager";
    public static final String THIRD_TIME_OPEN_NOTIFICATION_FILE = "/assets/third_time_open_instruction.mp3";
    private static RenderManager instance;
    private Renderer rendererModule;
    @VisibleForTesting
    static final ImmutableMap<Integer, String> EARCON_EVENT_ID_ASSET_PATH_MAP = new ImmutableMap.Builder().mo7828put(1, "/assets/swipe.mp3").mo7828put(2, "/assets/tap.mp3").mo7828put(3, "/assets/inc_notif.mp3").mo7828put(4, "/assets/delimiter.mp3").mo7828put(5, "/assets/invitation_alert_earcon.mp3").mo7828put(6, "/assets/invitation_accept_earcon.mp3").mo7828put(7, "/assets/invitation_reject_earcon.mp3").mo7828put(13, "/assets/zef_system_state_error_generic.mp3").mo7828put(14, "/assets/silence_300ms.mp3").mo7828put(8, "/assets/inc_notif.mp3").mo7828put(9, "/assets/invitation_alert_earcon.mp3").mo7828put(10, "/assets/swipe.mp3").mo7828put(15, "/assets/zef_reply_initiate.mp3").mo7826build();
    @VisibleForTesting
    static final BiMap<String, Integer> REQUEST_ID_EVENT_ID_BI_MAP = HashBiMap.create(new ImmutableMap.Builder().mo7828put("incomingEarcon", 3).mo7828put("delimiterEarcon", 4).mo7828put("swipeEarcon", 1).mo7828put("tapEarcon", 2).mo7828put("invitationAlertEarcon", 5).mo7828put("invitationAcceptedEarcon", 6).mo7828put("invitationRejectedEarcon", 7).mo7828put("genericErrorEarcon", 13).mo7828put("silenceEarcon", 14).mo7828put("lowDistractionAnnouncementEarcon", 8).mo7828put("lowDistractionInvitationEarcon", 9).mo7828put("lowDistractionInvitationPlayEarcon", 10).mo7828put("replyInitiateEarcon", 15).mo7826build());

    private RenderManager(@NonNull Context context) {
        super(3);
        this.rendererModule = new Renderer(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createAndPostEventMessage(String str, int i) {
        createAndPostEventMessage(REQUEST_ID_EVENT_ID_BI_MAP.get(str).intValue(), i, null);
    }

    private AudioFilePlayer.AudioPlaybackCompleteListener createEarconListener() {
        return new AudioFilePlayer.AudioPlaybackCompleteListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager.1
            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackCompleted(String str) {
                GeneratedOutlineSupport1.outline165("Play Earcon Completed: ", str, RenderManager.TAG);
                RenderManager.this.createAndPostEventMessage(str, 3);
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackError(String str) {
                GeneratedOutlineSupport1.outline165("Play Earcon Error : ", str, RenderManager.TAG);
                RenderManager.this.createAndPostEventMessage(str, 5);
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackInterrupted(String str) {
                GeneratedOutlineSupport1.outline165("Play Earcon Interrupted : ", str, RenderManager.TAG);
                RenderManager.this.createAndPostEventMessage(str, 4);
            }
        };
    }

    private AudioFilePlayer.AudioPlaybackCompleteListener createEarconListenerAndPlayTTS(@Nullable final JSONObject jSONObject, final int i) {
        return new AudioFilePlayer.AudioPlaybackCompleteListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager.2
            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackCompleted(String str) {
                GeneratedOutlineSupport1.outline165("Play Earcon Completed : ", str, RenderManager.TAG);
                RenderManager.this.createAndPostEventMessage(str, 3);
                JSONObject jSONObject2 = jSONObject;
                if (jSONObject2 != null) {
                    RenderManager.this.playTTS(jSONObject2, Integer.valueOf(i));
                }
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackError(String str) {
                GeneratedOutlineSupport1.outline165("Play Earcon Error : ", str, RenderManager.TAG);
                RenderManager.this.createAndPostEventMessage(str, 5);
                AudioInteractionScheduler.getInstance().setNotificationPlaybackState(AudioInteractionScheduler.NotificationPlaybackState.NONE, "playEarconError");
            }

            @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
            public void onAudioPlaybackInterrupted(String str) {
                GeneratedOutlineSupport1.outline165("Play Earcon Interrupted : ", str, RenderManager.TAG);
                RenderManager.this.createAndPostEventMessage(str, 4);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateCallbackForTTS(int i, String str, @Nullable Integer num) {
        createAndPostEventMessage(100, i, str);
        if (num == null || num.intValue() <= 100) {
            return;
        }
        createAndPostEventMessage(num.intValue(), i, str);
    }

    public static synchronized RenderManager getInstance() throws IllegalStateException {
        RenderManager renderManager;
        synchronized (RenderManager.class) {
            if (instance != null) {
                renderManager = instance;
            } else {
                Log.e(TAG, "getInstance called before calling initRenderManagerInstance. Throw an exception.");
                throw new IllegalStateException("RenderManager is not initialized, initRenderManagerInstance must be called before calling this method.");
            }
        }
        return renderManager;
    }

    private void handleEarconEvent(int i, @NonNull Object obj) {
        try {
            int optInt = ((JSONObject) JSONObject.class.cast(obj)).optInt("eventType", Integer.MIN_VALUE);
            String str = REQUEST_ID_EVENT_ID_BI_MAP.mo7998inverse().get(Integer.valueOf(i));
            String str2 = TAG;
            Log.d(str2, "handleEarconEvent - eventId: " + i + " eventType: " + optInt + " Payload: " + obj);
            if (optInt == 1) {
                String str3 = TAG;
                Log.d(str3, "handleEarconEvent - ACTIVE_EVENT_RENDERER_PLAY - requestId: " + str);
                this.rendererModule.playEarcon(EARCON_EVENT_ID_ASSET_PATH_MAP.mo7740get(Integer.valueOf(i)), str, createEarconListener());
            } else if (optInt != 2) {
                String str4 = TAG;
                Log.w(str4, "handleEarconEvent - Invalid event Type: " + optInt);
            } else {
                String str5 = TAG;
                Log.d(str5, "handleEarconEvent - ACTIVE_EVENT_RENDERER_CANCEL - requestId: " + str);
                this.rendererModule.stopAudioFile(str);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to handleEarconEvent.", e);
        }
    }

    private void handleEarconTTSEvent(int i, @NonNull Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) JSONObject.class.cast(obj);
            int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
            int i2 = i - 100;
            String str = REQUEST_ID_EVENT_ID_BI_MAP.mo7998inverse().get(Integer.valueOf(i2));
            Log.d(TAG, "handleEarconTTSEvent - eventId: " + i + " eventType: " + optInt + " Payload: " + obj);
            if (optInt == 1) {
                Log.d(TAG, "handleEarconTTSEvent - ACTIVE_EVENT_RENDERER_PLAY - requestId: " + str);
                String mo7740get = EARCON_EVENT_ID_ASSET_PATH_MAP.mo7740get(Integer.valueOf(i2));
                this.rendererModule.playEarcon(mo7740get, str, createEarconListenerAndPlayTTS(jSONObject, i));
                AccessoryTtsStateManager.getInstance().updateAccessoryTtsState(mo7740get);
            } else if (optInt != 2) {
                Log.w(TAG, "handleEarconTTSEvent - Invalid event Type: " + optInt);
            } else {
                Log.d(TAG, "handleEarconTTSEvent - ACTIVE_EVENT_RENDERER_CANCEL - requestId: " + str);
                this.rendererModule.stopAudioFile(str);
                this.rendererModule.stopAudioFile(jSONObject.optString("uuid"));
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to handleEarconTTSEvent.", e);
        }
    }

    private void handleTTSEvent(int i, @NonNull Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) JSONObject.class.cast(obj);
            int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
            String str = TAG;
            Log.d(str, "handleTTSEvent - : " + optInt + " Payload: " + obj);
            if (optInt == 1) {
                playTTS(jSONObject);
            } else if (optInt != 2) {
                String str2 = TAG;
                Log.w(str2, "handleTTSEvent - Invalid event Type: " + optInt);
            } else {
                this.rendererModule.stopAudioFile(jSONObject.optString("uuid"));
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to handleTTSEvent.", e);
        }
    }

    public static synchronized void initRenderManagerInstance(@NonNull Context context) throws IllegalArgumentException {
        synchronized (RenderManager.class) {
            if (instance == null) {
                Log.d(TAG, "initRenderManagerInstance - First time init");
                if (context != null) {
                    instance = new RenderManager(context);
                } else {
                    Log.e(TAG, "initRenderManagerInstance - Context is null, throw exception");
                    throw new IllegalArgumentException("Cannot initialize RenderManager with a null Context.");
                }
            }
        }
    }

    private void playTTS(@NonNull JSONObject jSONObject) {
        playTTS(jSONObject, null);
    }

    public static synchronized void releaseInstance() {
        synchronized (RenderManager.class) {
            if (instance != null) {
                instance.rendererModule = null;
            }
            instance = null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.BaseComponent
    public void handleEventMessage(int i, @Nullable Object obj) {
        if (obj == null) {
            GeneratedOutlineSupport1.outline151("handleEventMessage - missing payload, discard. eventId: ", i, TAG);
            return;
        }
        String str = TAG;
        Log.d(str, "handleEventMessage - eventId: " + i + " payload: " + obj);
        if (i != 13 && i != 14) {
            if (i != 110) {
                switch (i) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        break;
                    default:
                        switch (i) {
                            case 100:
                                handleTTSEvent(i, obj);
                                return;
                            case 101:
                            case 102:
                            case 103:
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                                break;
                            default:
                                switch (i) {
                                    case 113:
                                    case 114:
                                    case 115:
                                        break;
                                    default:
                                        GeneratedOutlineSupport1.outline151("handleEventMessage - Invalid event ID: ", i, TAG);
                                        return;
                                }
                        }
                }
            }
            handleEarconTTSEvent(i, obj);
            return;
        }
        handleEarconEvent(i, obj);
    }

    public void stopAllAudio() {
        this.rendererModule.stopAllAudio();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playTTS(@NonNull JSONObject jSONObject, @Nullable final Integer num) {
        if (!jSONObject.isNull("fileToPlay") && !jSONObject.isNull("uuid")) {
            this.rendererModule.playTTSAudioFile(jSONObject.optString("fileToPlay"), jSONObject.optString("uuid"), new AudioFilePlayer.AudioPlaybackCompleteListener() { // from class: com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager.3
                @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
                public void onAudioPlaybackCompleted(String str) {
                    GeneratedOutlineSupport1.outline165("playTTS - onAudioPlaybackCompleted: ", str, RenderManager.TAG);
                    RenderManager.this.generateCallbackForTTS(3, str, num);
                }

                @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
                public void onAudioPlaybackError(String str) {
                    String str2 = RenderManager.TAG;
                    Log.w(str2, "playTTS - onAudioPlaybackError: " + str);
                    RenderManager.this.generateCallbackForTTS(5, str, num);
                    AudioInteractionScheduler.getInstance().setNotificationPlaybackState(AudioInteractionScheduler.NotificationPlaybackState.NONE, "playTTSError");
                }

                @Override // com.amazon.alexa.accessory.notificationpublisher.audio.AudioFilePlayer.AudioPlaybackCompleteListener
                public void onAudioPlaybackInterrupted(String str) {
                    GeneratedOutlineSupport1.outline165("playTTS -  onAudioPlaybackInterrupted: ", str, RenderManager.TAG);
                    RenderManager.this.generateCallbackForTTS(4, str, num);
                }
            });
            return;
        }
        String str = TAG;
        Log.d(str, "playTTS - Invalid payload " + jSONObject);
    }

    /* renamed from: clone */
    public RenderManager m359clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }

    private void createAndPostEventMessage(int i, int i2, String str) {
        try {
            postEventMessage(i, new JSONObject().put("eventType", i2).put("uuid", str));
        } catch (JSONException e) {
            Log.e(TAG, "createAndPostEventMessage - JSONException.", e);
        }
    }
}
