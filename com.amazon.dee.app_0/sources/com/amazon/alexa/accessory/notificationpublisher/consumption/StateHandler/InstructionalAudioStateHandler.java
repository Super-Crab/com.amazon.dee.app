package com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.consumption.NotificationQueue;
import com.amazon.alexa.accessory.notificationpublisher.consumption.Payload;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StateManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.parser.BaseCustomAppParser;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.InputBehaviorConfigProvider;
import com.amazon.alexa.accessory.notificationpublisher.renderer.RenderManager;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.GroupNotificationHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.PermissionChecker;
import com.amazon.alexa.accessory.protocol.Input;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class InstructionalAudioStateHandler extends BaseStateHandler {
    public static final int MAX_NUM_OF_RECORD_PROMPTS = 3;
    public static final int NUM_OF_OPENED_NOTIFICATION_THAT_HAS_AUDIO = 3;
    public static final int NUM_OF_REJECTED_INVITATION_THAT_HAS_DIFFERENT_AUDIO = 1;
    private static final String TAG = "InstructionalAudioStateHandler";
    private static InstructionalAudioStateHandler instance;
    @VisibleForTesting
    String currentPlayingInstruction;
    private String lastPlayingInstruction;
    private boolean shouldUpdateNumberOfRecordPrompts;

    /* renamed from: com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.InstructionalAudioStateHandler$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$ProcessNotificationModule$NotificationType = new int[ProcessNotificationModule.NotificationType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$ProcessNotificationModule$NotificationType[ProcessNotificationModule.NotificationType.COMMS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private InstructionalAudioStateHandler() {
        super(StateManager.STATE_INSTRUCTIONAL_AUDIO);
        this.currentPlayingInstruction = null;
        this.lastPlayingInstruction = null;
        this.shouldUpdateNumberOfRecordPrompts = true;
    }

    private boolean causedByInitiateReplyDuringContentRender(String str, Integer num, Integer num2) {
        Log.d(TAG, "causedByInitiateReplyDuringContentRender");
        if (!Strings.isNullOrEmpty(str) && num != null && num2 != null) {
            return str.equalsIgnoreCase(StateManager.STATE_CONTENT) && num.intValue() == 8 && num2.intValue() == 1;
        }
        Log.i(TAG, "causedByInitiateReplyDuringContentRender - Invalid params, return false");
        return false;
    }

    private boolean causedByNotificationDismiss(Integer num, Integer num2) {
        if (num == null || num2 == null || num.intValue() != 1) {
            return false;
        }
        return num2.intValue() == 2 || num2.intValue() == 3;
    }

    private boolean causedByPhoneNotificationToggleViaGesture(Integer num, Integer num2) {
        return num != null && num2 != null && num.intValue() == 2 && num2.intValue() == 5;
    }

    private boolean causedByPlayGesture(Integer num, Integer num2) {
        return num != null && num2 != null && num.intValue() == 2 && num2.intValue() == 1;
    }

    private boolean causedByRenderComplete(Integer num, Integer num2) {
        return num != null && num2 != null && num.intValue() == 3 && num2.intValue() == 3;
    }

    private boolean causedBySilentDistractionModeToggle(Integer num, Integer num2) {
        return num != null && num2 != null && num.intValue() == 2 && num2.intValue() == 4;
    }

    private boolean causedByStopGesture(Integer num, Integer num2) {
        return num != null && num2 != null && num.intValue() == 2 && num2.intValue() == 2;
    }

    private boolean didEnterFromReplyDelayToPlayGroupMessageUserEducationTts(@NonNull String str, Integer num, Integer num2, Integer num3) {
        if (!Strings.isNullOrEmpty(str) && num != null && num2 != null && num3 != null) {
            return str.equalsIgnoreCase(StateManager.STATE_REPLY_DELAY) && num.intValue() == 4 && num2.intValue() == 1 && num3.intValue() == 3;
        }
        Log.w(TAG, "didEnterFromReplyDelayToPlayGroupMessageUserEducationTts - Unexpected transition, return false");
        return false;
    }

    public static synchronized InstructionalAudioStateHandler getInstance() {
        InstructionalAudioStateHandler instructionalAudioStateHandler;
        synchronized (InstructionalAudioStateHandler.class) {
            if (instance == null) {
                instance = new InstructionalAudioStateHandler();
            }
            instructionalAudioStateHandler = instance;
        }
        return instructionalAudioStateHandler;
    }

    private void groupMessagesUserEducationComplete() {
        Log.d(TAG, "groupMessagesUserEducationComplete");
        try {
            try {
                SettingsStorageModule.getInstance().putGroupMessagesUserEducationComplete(true);
            } catch (Exception e) {
                String str = TAG;
                Log.w(str, "groupMessagesUserEducationComplete - Exception: " + e);
            }
        } finally {
            Log.i(TAG, "groupMessagesUserEducationComplete - Transit back to content state");
            transitState(StateManager.STATE_CONTENT, 3, 3, 11);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void handleAudioCompletedByUUID(String str) {
        char c;
        GeneratedOutlineSupport1.outline166("handleAudioCompletedByUUID -- uuid: ", str, TAG);
        String str2 = this.currentPlayingInstruction;
        this.currentPlayingInstruction = null;
        switch (str.hashCode()) {
            case -1800931083:
                if (str.equals(RenderManager.REPLY_SENDING_UUID)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -1791291918:
                if (str.equals(RenderManager.OTG_NOTIFICATION_SOUNDS_OFF_UUID)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1706604718:
                if (str.equals(RenderManager.ENABLE_MICROPHONE_PERMISSION_UUID)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -1468528652:
                if (str.equals(RenderManager.OTG_NOTIFICATION_SOUNDS_ON_UUID)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1395062380:
                if (str.equals(RenderManager.GROUP_UNNAMED_ACCEPTED_UUID)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1315881779:
                if (str.equals(RenderManager.GROUP_NAMED_ACCEPTED_UUID)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1298342220:
                if (str.equals(RenderManager.REPLY_ERROR_RETRY_AVAILABLE_UUID)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -1134346019:
                if (str.equals(RenderManager.CONTACT_REJECTED_UUID)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -1015339641:
                if (str.equals(RenderManager.GROUP_MESSAGES_USER_EDUCATION_UUID)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -660834018:
                if (str.equals(RenderManager.APP_REJECTED_UUID)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -407645101:
                if (str.equals(RenderManager.OTG_PHONE_NOTIFICATIONS_OFF_UUID)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -257758737:
                if (str.equals(RenderManager.REPLY_AGAIN_UUID)) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -97843601:
                if (str.equals(RenderManager.REPLY_ERROR_RETRY_LIMIT_REACHED_UUID)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 99291026:
                if (str.equals(RenderManager.MULTIPLE_NOTIFICATIONS_INSTRUCTION_UUID)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 156550761:
                if (str.equals(RenderManager.NO_RECENT_NOTIFICATION_UUID)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 485414874:
                if (str.equals(RenderManager.OPEN_NOTIFICATION_UUID)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 498533149:
                if (str.equals(RenderManager.GROUP_UNNAMED_REJECTED_UUID)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 577713750:
                if (str.equals(RenderManager.GROUP_NAMED_REJECTED_UUID)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 723229805:
                if (str.equals(RenderManager.NOTIFICATIONS_ARE_OFF_UUID)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 833029040:
                if (str.equals(RenderManager.REPLY_FEATURE_EDUCATION_UUID)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 850491037:
                if (str.equals(RenderManager.REPLY_CANCELED_INSTRUCTION_UUID)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 1051797829:
                if (str.equals(RenderManager.REPLY_PROMPT_FOR_RECORDING_UUID)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 1216648798:
                if (str.equals(RenderManager.START_REPLY_INSTRUCTION_UUID)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 1256600647:
                if (str.equals(RenderManager.REPLY_ERROR_UUID)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 1267025748:
                if (str.equals(RenderManager.CONTACT_ACCEPTED_UUID)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1428684306:
                if (str.equals(RenderManager.CALENDAR_ACCEPTED_UUID)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1485599091:
                if (str.equals(RenderManager.OTG_PHONE_NOTIFICATIONS_ON_UUID)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1740537749:
                if (str.equals(RenderManager.APP_ACCEPTED_UUID)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing multiple notifications instruction");
                multipleNotificationsInstructionComplete();
                return;
            case 1:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing group messages user education audio");
                groupMessagesUserEducationComplete();
                return;
            case 2:
            case 3:
            case 4:
            case 5:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing no notification audio - Go to next notification");
                this.currentPlayingInstruction = null;
                transitStateForNextNotification(3, 100, 3);
                return;
            case 6:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing 'Notification Alerts: OFF' - Go to next notification.");
                this.currentPlayingInstruction = null;
                transitStateForNextNotification(6, 18, null);
                return;
            case 7:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing 'Notification Alerts: ON' - Go to next notification.");
                this.currentPlayingInstruction = null;
                transitStateForNextNotification(6, 19, null);
                return;
            case '\b':
            case '\t':
            case '\n':
            case 11:
            case '\f':
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing accepted audio - Transit to Content state as user accepted");
                transitToContent();
                return;
            case '\r':
            case 14:
            case 15:
            case 16:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing rejected audio - Go to next notification as user rejected");
                this.currentPlayingInstruction = null;
                removeHeadThenTransitForNextNotification(2, 2, null);
                return;
            case 17:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing open notification audio - Go to announcement act delay");
                startReplayTimer();
                updateNumOfOpenedNotification();
                transitState(StateManager.STATE_ANNOUNCEMENT_DELAY, 3, 3, null);
                return;
            case 18:
                replyFtuFeatureEducationComplete();
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing reply FTU feature education - Go to Reply Act Delay state and wait for Swipe");
                transitState(StateManager.STATE_REPLY_DELAY);
                return;
            case 19:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing start reply instruction - Go to Reply Act Delay state and wait for Swipe");
                transitState(StateManager.STATE_REPLY_DELAY);
                return;
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                Log.i(TAG, "handleAudioCompletedByUUID - Reply was canceled, successful, or had an error - Go to next notification. UUID: " + str);
                if (str.equalsIgnoreCase(RenderManager.REPLY_CANCELED_INSTRUCTION_UUID)) {
                    Log.i(TAG, "handleAudioCompletedByUUID - User initiated cancel, remove notification");
                    NotificationQueue.getInstance().poll();
                }
                transitStateForNextNotification(3, 3, 100);
                return;
            case 25:
                Log.i(TAG, "handleAudioCompletedByUUID - Reply encountered an error that can beretried");
                promptToRecordReply(true);
                return;
            case 26:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing say your message instruction - Go to reply state");
                updateNumOfRecordPrompts();
                transitState(StateManager.STATE_REPLY);
                return;
            case 27:
                Log.i(TAG, "handleAudioCompletedByUUID - Finish playing reply again instruction - Go to reply state");
                transitState(StateManager.STATE_REPLY, 8, 12, null);
                return;
            default:
                Log.w(TAG, "handleAudioCompletedByUUID - Invalid render request uuid " + str);
                this.currentPlayingInstruction = str2;
                return;
        }
    }

    private void handleEnterFromAnnouncementActDelayStateHandler(@NonNull String str, Integer num, Integer num2, Integer num3) {
        Log.d(TAG, "handleEnterFromAnnouncementActDelayStateHandler");
        if (causedByPlayGesture(num, num2)) {
            JSONObject peek = NotificationQueue.getInstance().peek();
            if (peek == null) {
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
                Log.e(TAG, "Invitation audio cannot be played on null notification");
                transitState(StateManager.STATE_IDLE);
                return;
            }
            ProcessNotificationModule.NotificationType notificationType = ProcessNotificationModule.getNotificationType(peek);
            if (notificationType.ordinal() != 0) {
                String optString = peek.optString(BaseCustomAppParser.CUSTOM_GENERIC_APP_CATEGORY);
                if (!Strings.isNullOrEmpty(optString) && optString.equals(BaseCustomAppParser.CALENDAR_APP_CATEGORY)) {
                    Log.i(TAG, "Calendar app notification, play Calendar accepted instructional audio");
                    postEventMessageToPlayInstruction(RenderManager.CALENDAR_ACCEPTED_UUID, RenderManager.CALENDAR_ACCEPTED_FILE, 106);
                    return;
                }
                Log.i(TAG, "Generic notification, play generic app accepted instructional audio");
                postEventMessageToPlayInstruction(RenderManager.APP_ACCEPTED_UUID, RenderManager.APP_ACCEPTED_FILE, 106);
                return;
            }
            int groupMessageType = GroupNotificationHelper.getGroupMessageType(peek);
            if (groupMessageType == 0) {
                postEventMessageToPlayInstruction(RenderManager.CONTACT_ACCEPTED_UUID, RenderManager.CONTACT_ACCEPTED_FILE, 106);
            } else if (groupMessageType == 1) {
                postEventMessageToPlayInstruction(RenderManager.GROUP_NAMED_ACCEPTED_UUID, RenderManager.GROUP_NAMED_ACCEPTED_FILE, 106);
            } else if (groupMessageType != 2) {
                String str2 = TAG;
                Log.w(str2, "invalid message type: " + notificationType);
                String str3 = TAG;
                Log.w(str3, "enterState -- Invalid request - fromState: " + str + " componentId: " + num + " eventId: " + num2);
            } else if (GroupNotificationHelper.onlyUnnamedGroupSupported(peek)) {
                postEventMessageToPlayInstruction(RenderManager.GROUP_UNNAMED_ACCEPTED_UUID, RenderManager.GROUP_UNNAMED_NO_NAMING_ACCEPTED_FILE, 106);
            } else {
                postEventMessageToPlayInstruction(RenderManager.GROUP_UNNAMED_ACCEPTED_UUID, RenderManager.GROUP_UNNAMED_SUPPORT_NAMING_ACCEPTED_FILE, 106);
            }
        } else if (causedByStopGesture(num, num2)) {
            JSONObject peek2 = NotificationQueue.getInstance().peek();
            ProcessNotificationModule.NotificationType notificationType2 = ProcessNotificationModule.getNotificationType(peek2);
            if (notificationType2.ordinal() != 0) {
                if (SettingsStorageModule.getInstance().getNumOfRejectedInvitation().intValue() > 1) {
                    postEventMessageToPlayInstruction(RenderManager.APP_REJECTED_UUID, RenderManager.APP_REJECTED_FILE, 107);
                    return;
                } else {
                    postEventMessageToPlayInstruction(RenderManager.APP_REJECTED_UUID, RenderManager.FIRST_TIME_APP_REJECTED_FILE, 107);
                    return;
                }
            }
            int groupMessageType2 = GroupNotificationHelper.getGroupMessageType(peek2);
            if (groupMessageType2 == 0) {
                if (SettingsStorageModule.getInstance().getNumOfRejectedInvitation().intValue() > 1) {
                    postEventMessageToPlayInstruction(RenderManager.CONTACT_REJECTED_UUID, RenderManager.CONTACT_REJECTED_FILE, 107);
                } else {
                    postEventMessageToPlayInstruction(RenderManager.CONTACT_REJECTED_UUID, RenderManager.FIRST_TIME_CONTACT_REJECTED_FILE, 107);
                }
            } else if (groupMessageType2 == 1) {
                if (SettingsStorageModule.getInstance().getNumOfRejectedInvitation().intValue() > 1) {
                    postEventMessageToPlayInstruction(RenderManager.GROUP_NAMED_REJECTED_UUID, RenderManager.GROUP_NAMED_REJECTED_FILE, 107);
                } else {
                    postEventMessageToPlayInstruction(RenderManager.GROUP_NAMED_REJECTED_UUID, RenderManager.FIRST_TIME_GROUP_NAMED_REJECTED_FILE, 107);
                }
            } else if (groupMessageType2 != 2) {
                String str4 = TAG;
                Log.w(str4, "invalid message type: " + notificationType2);
                String str5 = TAG;
                Log.w(str5, "enterState -- Invalid request - fromState: " + str + " componentId: " + num + " eventId: " + num2);
            } else if (GroupNotificationHelper.onlyUnnamedGroupSupported(peek2)) {
                postEventMessageToPlayInstruction(RenderManager.GROUP_UNNAMED_REJECTED_UUID, RenderManager.GROUP_UNNAMED_NO_NAMING_REJECTED_FILE, 107);
            } else {
                postEventMessageToPlayInstruction(RenderManager.GROUP_UNNAMED_REJECTED_UUID, RenderManager.GROUP_UNNAMED_SUPPORT_NAMING_REJECTED_FILE, 107);
            }
        } else {
            String str6 = TAG;
            Log.w(str6, "enterState -- Invalid request - fromState: " + str + " componentId: " + num + " eventId: " + num2);
        }
    }

    private void handleEnterFromContentStateHandler(@NonNull String str, Integer num, Integer num2, Integer num3) {
        Log.d(TAG, "handleEnterFromContentStateHandler");
        if (num3 != null && num3.intValue() == 11) {
            Log.i(TAG, "enterState - Post event to play group message user education");
            playGroupMessageUserEducationTts();
        } else if (num3 != null && num3.intValue() == 12) {
            Log.i(TAG, "enterState - Post event to play multiple notifications instruction");
            postEventMessageToPlayInstruction(RenderManager.MULTIPLE_NOTIFICATIONS_INSTRUCTION_UUID, RenderManager.MULTIPLE_NOTIFICATIONS_INSTRUCTION_FILE, 100);
        } else if (causedByRenderComplete(num, num2)) {
            if (!SettingsStorageModule.getInstance().getReplyFtuFeatureEducationComplete().booleanValue()) {
                playReplyFeatureEducationWithSilenceEarcon();
            } else {
                playSwipeToReplyWithSilenceEarcon();
            }
        } else if (causedByInitiateReplyDuringContentRender(str, num, num2)) {
            Log.i(TAG, "enterState - Caused by swipe during content render for reply-able notification");
            promptToRecordReply(false);
        } else {
            String str2 = TAG;
            Log.w(str2, "enterState -- Invalid request - fromState: " + str + " componentId: " + num + " eventId: " + num2);
        }
    }

    private void handleEnterFromInstructionalAudioStateHandler(@NonNull String str, Integer num, Integer num2, Integer num3) {
        Log.d(TAG, "handleEnterFromInstructionalAudioStateHandler");
        if (causedByPlayGesture(num, num2)) {
            String str2 = this.lastPlayingInstruction;
            if (str2 != null && str2.equals(RenderManager.START_REPLY_INSTRUCTION_UUID)) {
                Log.i(TAG, "handleEnterFromInstructionalAudioStateHandler - promptToRecordReply");
                promptToRecordReply(false);
            } else if (DistractionModeProvider.getCurrentDistractionMode() == 4) {
                postEventMessageToPlayInstruction(RenderManager.NOTIFICATIONS_ARE_OFF_UUID, RenderManager.NOTIFICATIONS_ARE_OFF_FILE);
            } else {
                postEventMessageToPlayInstruction(RenderManager.NO_RECENT_NOTIFICATION_UUID, RenderManager.NO_RECENT_NOTIFICATION_FILE);
            }
        } else if (causedByStopGesture(num, num2)) {
            String str3 = this.lastPlayingInstruction;
            if (str3 == null) {
                return;
            }
            if (!str3.equals(RenderManager.START_REPLY_INSTRUCTION_UUID) && !this.lastPlayingInstruction.equals(RenderManager.REPLY_PROMPT_FOR_RECORDING_UUID) && !this.lastPlayingInstruction.equals(RenderManager.REPLY_AGAIN_UUID)) {
                return;
            }
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_CANCELLED);
            playReplyCanceledWithEarcon();
        } else {
            String str4 = TAG;
            Log.w(str4, "enterState -- Invalid request - fromState: " + str + " componentId: " + num + " eventId: " + num2);
        }
    }

    private void handleEnterStateFromReadBackOrReadBackDelay(@NonNull String str, Integer num, Integer num2, Integer num3) {
        Log.d(TAG, "handleEnterStateFromReadBackOrReadBackDelay");
        if (causedByNotificationDismiss(num, num2)) {
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_CANCELLED_NOTIF_DISMISSED);
            playReplyCanceledWithEarcon();
        } else if (didEnterForRecordingReply(str, num, num2)) {
            Log.i(TAG, "handleEnterStateFromReadBackOrReadBackDelay - Retry reply recording case");
            promptToRecordReply(true);
        } else {
            Log.w(TAG, "handleEnterStateFromReadBackOrReadBackDelay - Incorrect state transition - go to idle");
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
            transitState(StateManager.STATE_IDLE);
        }
    }

    private void handleEnterStateFromReplyActDelayStateHandler(@NonNull String str, Integer num, Integer num2, Integer num3) {
        Log.d(TAG, "handleEnterStateFromReplyActDelayStateHandler");
        if (causedByNotificationDismiss(num, num2)) {
            playReplyCanceledWithEarcon();
        } else if (causedByPlayGesture(num, num2)) {
            if (!PermissionChecker.hasRecordAudioPermission()) {
                Log.i(TAG, "handleEnterStateFromReplyActDelayStateHandler - Microphone permissionnot granted. Play permission instruction");
                playReplyErrorNoRecordPermissionWithEarcon();
                return;
            }
            Log.d(TAG, "handleEnterStateFromReplyActDelayStateHandler - Microphone permission granted");
            promptToRecordReply(false);
        } else if (!didEnterFromReplyDelayToPlayGroupMessageUserEducationTts(str, num, num2, num3)) {
        } else {
            playGroupMessageUserEducationTts();
        }
    }

    private void handleEnterStateFromReplyStateHandler(@NonNull String str, Integer num, Integer num2, Integer num3) {
        Log.d(TAG, "handleEnterStateFromReplyStateHandler");
        if (!causedByStopGesture(num, num2) && !causedByNotificationDismiss(num, num2)) {
            if (num.intValue() != 8) {
                return;
            }
            int intValue = num2.intValue();
            if (intValue == 5) {
                playReplyErrorNoRecordPermissionWithEarcon();
                return;
            } else if (intValue == 7) {
                playReplyErrorRetryAvailable();
                return;
            } else if (intValue != 9) {
                switch (intValue) {
                    case 11:
                        playReplySendingTts();
                        return;
                    case 12:
                        playInstructionReplyAgain();
                        return;
                    case 13:
                        playReplyErrorRetryLimitReached();
                        return;
                    default:
                        playReplyGenericErrorWithEarcon();
                        return;
                }
            } else {
                playReplyCanceledWithEarcon();
                return;
            }
        }
        playReplyCanceledWithEarcon();
    }

    private void multipleNotificationsInstructionComplete() {
        Log.i(TAG, "multipleNotificationsInstructionComplete - Transit back to content state");
        transitState(StateManager.STATE_CONTENT, 3, 3, 12);
    }

    private void playGroupMessageUserEducationTts() {
        postEventMessageToStopInstruction();
        postEventMessageToPlayInstruction(RenderManager.GROUP_MESSAGES_USER_EDUCATION_UUID, RenderManager.GROUP_MESSAGES_USER_EDUCATION_FILE);
    }

    private void playInstructionPromptToRecord(boolean z) {
        postEventMessageToStopInstruction();
        String str = TAG;
        Log.d(str, "playInstructionPromptToRecord - Start rendering prompt to recordaudio for reply - playForRetry = " + z);
        int intValue = SettingsStorageModule.getInstance().getNumOfRecordPrompts().intValue();
        if (intValue >= 3 || z) {
            if (z) {
                this.shouldUpdateNumberOfRecordPrompts = false;
                postEventMessageToRenderManager(102, 1, RenderManager.REPLY_PROMPT_FOR_RECORDING_UUID, RenderManager.REPLY_PROMPT_FOR_RECORDING_FILE);
                this.currentPlayingInstruction = RenderManager.REPLY_PROMPT_FOR_RECORDING_UUID;
                return;
            }
            transitState(StateManager.STATE_REPLY);
            return;
        }
        String str2 = intValue != 0 ? intValue != 1 ? intValue != 2 ? "" : RenderManager.REPLY_THIRD_PROMPT_FOR_RECORDING_FILE : RenderManager.REPLY_SECOND_PROMPT_FOR_RECORDING_FILE : RenderManager.REPLY_FIRST_PROMPT_FOR_RECORDING_FILE;
        if (Strings.isNullOrEmpty(str2)) {
            Log.w(TAG, "playInstructionPromptToRecord - Unhandled use case, transit to Reply gracefully");
            transitState(StateManager.STATE_REPLY);
            return;
        }
        String str3 = TAG;
        Log.i(str3, "playInstructionPromptToRecord - Prompt playing for instruction number: " + intValue + 1);
        postEventMessageToRenderManager(115, 1, RenderManager.REPLY_PROMPT_FOR_RECORDING_UUID, str2);
        this.shouldUpdateNumberOfRecordPrompts = true;
        this.currentPlayingInstruction = RenderManager.REPLY_PROMPT_FOR_RECORDING_UUID;
    }

    private void playInstructionReplyAgain() {
        Log.d(TAG, "playReplyAgain");
        postEventMessageToRenderManager(100, 1, RenderManager.REPLY_AGAIN_UUID, RenderManager.REPLY_AGAIN_FILE);
        this.currentPlayingInstruction = RenderManager.REPLY_AGAIN_UUID;
    }

    private void playReplyCanceledWithEarcon() {
        Log.d(TAG, "playReplyCanceledWithEarcon");
        postEventMessageToStopInstruction();
        postEventMessageToRenderManager(102, 1, RenderManager.REPLY_CANCELED_INSTRUCTION_UUID, RenderManager.REPLY_CANCELED_INSTRUCTION_FILE);
        this.currentPlayingInstruction = RenderManager.REPLY_CANCELED_INSTRUCTION_UUID;
    }

    private void playReplyErrorNoRecordPermissionWithEarcon() {
        Log.d(TAG, "playReplyErrorNoRecordPermissionWithEarcon");
        postEventMessageToStopInstruction();
        postEventMessageToRenderManager(113, 1, RenderManager.ENABLE_MICROPHONE_PERMISSION_UUID, RenderManager.ENABLE_MICROPHONE_PERMISSION_FILE);
        this.currentPlayingInstruction = RenderManager.ENABLE_MICROPHONE_PERMISSION_UUID;
    }

    private void playReplyErrorRetryAvailable() {
        Log.d(TAG, "playReplyErrorRetryAvailable");
        postEventMessageToStopInstruction();
        postEventMessageToRenderManager(113, 1, RenderManager.REPLY_ERROR_RETRY_AVAILABLE_UUID, RenderManager.REPLY_ERROR_RETRY_AVAILABLE_FILE);
        this.currentPlayingInstruction = RenderManager.REPLY_ERROR_RETRY_AVAILABLE_UUID;
    }

    private void playReplyErrorRetryLimitReached() {
        Log.d(TAG, "playReplyErrorRetryLimitReached");
        postEventMessageToStopInstruction();
        postEventMessageToRenderManager(113, 1, RenderManager.REPLY_ERROR_RETRY_LIMIT_REACHED_UUID, RenderManager.REPLY_ERROR_RETRY_AVAILABLE_FILE);
        this.currentPlayingInstruction = RenderManager.REPLY_ERROR_RETRY_LIMIT_REACHED_UUID;
    }

    private void playReplyFeatureEducationWithSilenceEarcon() {
        Log.d(TAG, "playReplyFeatureEducationWithSilenceEarcon");
        this.currentPlayingInstruction = RenderManager.REPLY_FEATURE_EDUCATION_UUID;
        postEventMessageToRenderManager(114, 1, RenderManager.REPLY_FEATURE_EDUCATION_UUID, RenderManager.REPLY_FEATURE_EDUCATION_FILE);
    }

    private void playReplyGenericErrorWithEarcon() {
        Log.d(TAG, "playReplyGenericErrorWithEarcon");
        postEventMessageToStopInstruction();
        postEventMessageToRenderManager(113, 1, RenderManager.REPLY_ERROR_UUID, RenderManager.REPLY_ERROR_FILE);
        this.currentPlayingInstruction = RenderManager.REPLY_ERROR_UUID;
    }

    private void playReplySendingTts() {
        Log.d(TAG, "playReplySendingTts");
        postEventMessageToRenderManager(100, 1, RenderManager.REPLY_SENDING_UUID, RenderManager.REPLY_SENDING_FILE);
        this.currentPlayingInstruction = RenderManager.REPLY_SENDING_UUID;
    }

    private void playSwipeToReplyWithSilenceEarcon() {
        Log.d(TAG, "playSwipeToReplyWithPauseEarcon");
        this.currentPlayingInstruction = RenderManager.START_REPLY_INSTRUCTION_UUID;
        postEventMessageToRenderManager(114, 1, RenderManager.START_REPLY_INSTRUCTION_UUID, RenderManager.START_REPLY_INSTRUCTION_FILE);
    }

    private void postEventMessageToPlayInstruction(String str, String str2) {
        postEventMessageToPlayInstruction(str, str2, 101);
    }

    private void postEventMessageToStopInstruction() {
        if (this.currentPlayingInstruction != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Stop rendering instructional audio ");
            outline107.append(this.currentPlayingInstruction);
            Log.i(str, outline107.toString());
            postEventMessageToRenderManager(101, 2, this.currentPlayingInstruction);
            this.currentPlayingInstruction = null;
        }
    }

    private void promptToRecordReply(boolean z) {
        if (!PermissionChecker.hasRecordAudioPermission()) {
            Log.i(TAG, "promptToRecordReply - Microphone permissionnot granted. Play permission instruction");
            playReplyErrorNoRecordPermissionWithEarcon();
            return;
        }
        String str = TAG;
        Log.d(str, "promptToRecordReply - Microphone permission granted - promptBecauseOfRetry = " + z);
        playInstructionPromptToRecord(z);
    }

    public static synchronized void releaseInstance() {
        synchronized (InstructionalAudioStateHandler.class) {
            instance = null;
        }
    }

    private void renderGestureEarconAndGoToNextNotification(int i) {
        String str = TAG;
        Log.i(str, "renderGestureEarconAndGoToNextNotification -- gesture eventId: " + i);
        postEventMessageToRenderManager(mapGestureEventToRenderEvent(i).intValue(), 1);
        removeHeadThenTransitForNextNotification(2, Integer.valueOf(i), null);
    }

    private void renderInstructionBasedOnNumOfOpenedNotification() {
        int intValue = SettingsStorageModule.getInstance().getNumOfOpenedNotification().intValue();
        if (intValue == 0) {
            postEventMessageToPlayInstruction(RenderManager.OPEN_NOTIFICATION_UUID, shouldPlayDTapFile() ? RenderManager.OTG_FIRST_TIME_OPEN_NOTIFICATION_FILE : RenderManager.FIRST_TIME_OPEN_NOTIFICATION_FILE, 100);
        } else if (intValue == 1) {
            postEventMessageToPlayInstruction(RenderManager.OPEN_NOTIFICATION_UUID, shouldPlayDTapFile() ? RenderManager.OTG_SECOND_TIME_OPEN_NOTIFICATION_FILE : RenderManager.SECOND_TIME_OPEN_NOTIFICATION_FILE, 100);
        } else if (intValue != 2) {
        } else {
            postEventMessageToPlayInstruction(RenderManager.OPEN_NOTIFICATION_UUID, shouldPlayDTapFile() ? RenderManager.OTG_THIRD_TIME_OPEN_NOTIFICATION_FILE : RenderManager.THIRD_TIME_OPEN_NOTIFICATION_FILE, 100);
        }
    }

    private void replyFtuFeatureEducationComplete() {
        Log.d(TAG, "replyFtuFeatureEducationComplete");
        try {
            SettingsStorageModule.getInstance().putReplyFtuFeatureEducationComplete(true);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("replyFtuFeatureEducationComplete - Exception: ", e, TAG);
        }
    }

    private boolean shouldPlayDTapFile() {
        Accessory accessory;
        List<Input.InputBehaviorConfiguration> configList;
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && (accessory = AccessoryProvider.getAccessory()) != null && (configList = InputBehaviorConfigProvider.getConfigList(accessory.getAddress())) != null) {
            for (Input.InputBehaviorConfiguration inputBehaviorConfiguration : configList) {
                if (inputBehaviorConfiguration.getAction() == Input.InputAction.INPUT_ACTION_TAP && inputBehaviorConfiguration.getBehavior().getNumber() == 22) {
                    return true;
                }
            }
        }
        return false;
    }

    @VisibleForTesting
    boolean didEnterForRecordingReply(@NonNull String str, Integer num, Integer num2) {
        try {
            if (Strings.isNullOrEmpty(str)) {
                return false;
            }
            if ((!str.equalsIgnoreCase(StateManager.STATE_REPLY_READ_BACK) && !str.equalsIgnoreCase(StateManager.STATE_REPLY_READ_BACK_ACT_DELAY)) || num == null || num.intValue() != 9 || num2 == null) {
                return false;
            }
            return num2.intValue() == 7;
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline157("didEnterForSendingReply - Exception: ", e, TAG);
            return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void enterState(String str, Integer num, Integer num2, Integer num3) {
        char c;
        super.enterState(str, num, num2, num3);
        switch (str.hashCode()) {
            case -1934380307:
                if (str.equals(StateManager.STATE_REPLY_READ_BACK)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1217121653:
                if (str.equals(StateManager.STATE_CONTENT)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -485675900:
                if (str.equals(StateManager.STATE_REPLY_READ_BACK_ACT_DELAY)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -336637355:
                if (str.equals(StateManager.STATE_ANNOUNCEMENT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -233397511:
                if (str.equals(StateManager.STATE_ANNOUNCEMENT_DELAY)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -67663648:
                if (str.equals(StateManager.STATE_REPLY_DELAY)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 243264578:
                if (str.equals(StateManager.STATE_INSTRUCTIONAL_AUDIO)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 286737122:
                if (str.equals(StateManager.STATE_IDLE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 307261820:
                if (str.equals(StateManager.STATE_REPLY)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                if (causedByPlayGesture(num, num2)) {
                    if (DistractionModeProvider.getCurrentDistractionMode() == 4) {
                        postEventMessageToPlayInstruction(RenderManager.NOTIFICATIONS_ARE_OFF_UUID, RenderManager.NOTIFICATIONS_ARE_OFF_FILE);
                        break;
                    } else {
                        postEventMessageToPlayInstruction(RenderManager.NO_RECENT_NOTIFICATION_UUID, RenderManager.NO_RECENT_NOTIFICATION_FILE);
                        break;
                    }
                } else if (causedBySilentDistractionModeToggle(num, num2)) {
                    if (DistractionModeProvider.getCurrentDistractionMode() == 3) {
                        postEventMessageToPlayInstruction(RenderManager.OTG_NOTIFICATION_SOUNDS_OFF_UUID, RenderManager.OTG_NOTIFICATION_SOUNDS_OFF_FILE);
                        break;
                    } else {
                        postEventMessageToPlayInstruction(RenderManager.OTG_NOTIFICATION_SOUNDS_ON_UUID, RenderManager.OTG_NOTIFICATION_SOUNDS_ON_FILE);
                        break;
                    }
                } else if (causedByPhoneNotificationToggleViaGesture(num, num2)) {
                    if (FeatureToggleModule.getInstance().isFeatureEnabled()) {
                        postEventMessageToPlayInstruction(RenderManager.OTG_PHONE_NOTIFICATIONS_ON_UUID, RenderManager.OTG_PHONE_NOTIFICATIONS_ON_FILE);
                        break;
                    } else {
                        postEventMessageToPlayInstruction(RenderManager.OTG_PHONE_NOTIFICATIONS_OFF_UUID, RenderManager.OTG_PHONE_NOTIFICATIONS_OFF_FILE);
                        break;
                    }
                } else {
                    Log.w(TAG, "enterState -- Invalid request - fromState: " + str + " componentId: " + num + " eventId: " + num2);
                    break;
                }
            case 1:
                if (causedByRenderComplete(num, num2)) {
                    renderInstructionBasedOnNumOfOpenedNotification();
                }
            case 2:
                handleEnterFromAnnouncementActDelayStateHandler(str, num, num2, num3);
                break;
            case 3:
                handleEnterFromContentStateHandler(str, num, num2, num3);
                break;
            case 4:
                handleEnterFromInstructionalAudioStateHandler(str, num, num2, num3);
                break;
            case 5:
                handleEnterStateFromReplyActDelayStateHandler(str, num, num2, num3);
                break;
            case 6:
                handleEnterStateFromReplyStateHandler(str, num, num2, num3);
                break;
            case 7:
            case '\b':
                handleEnterStateFromReadBackOrReadBackDelay(str, num, num2, num2);
                break;
            default:
                Log.w(TAG, "enterState -- Invalid fromState " + str);
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
                transitState(StateManager.STATE_IDLE);
                break;
        }
        this.lastPlayingInstruction = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleGestureEvent(int i, @Nullable Payload payload) {
        char c;
        super.handleGestureEvent(i, payload);
        String str = this.currentPlayingInstruction;
        switch (str.hashCode()) {
            case -1706604718:
                if (str.equals(RenderManager.ENABLE_MICROPHONE_PERMISSION_UUID)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -1395062380:
                if (str.equals(RenderManager.GROUP_UNNAMED_ACCEPTED_UUID)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1315881779:
                if (str.equals(RenderManager.GROUP_NAMED_ACCEPTED_UUID)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1298342220:
                if (str.equals(RenderManager.REPLY_ERROR_RETRY_AVAILABLE_UUID)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -1134346019:
                if (str.equals(RenderManager.CONTACT_REJECTED_UUID)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1015339641:
                if (str.equals(RenderManager.GROUP_MESSAGES_USER_EDUCATION_UUID)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -660834018:
                if (str.equals(RenderManager.APP_REJECTED_UUID)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -257758737:
                if (str.equals(RenderManager.REPLY_AGAIN_UUID)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -97843601:
                if (str.equals(RenderManager.REPLY_ERROR_RETRY_LIMIT_REACHED_UUID)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 99291026:
                if (str.equals(RenderManager.MULTIPLE_NOTIFICATIONS_INSTRUCTION_UUID)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 156550761:
                if (str.equals(RenderManager.NO_RECENT_NOTIFICATION_UUID)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 485414874:
                if (str.equals(RenderManager.OPEN_NOTIFICATION_UUID)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 498533149:
                if (str.equals(RenderManager.GROUP_UNNAMED_REJECTED_UUID)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 577713750:
                if (str.equals(RenderManager.GROUP_NAMED_REJECTED_UUID)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 723229805:
                if (str.equals(RenderManager.NOTIFICATIONS_ARE_OFF_UUID)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 833029040:
                if (str.equals(RenderManager.REPLY_FEATURE_EDUCATION_UUID)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 850491037:
                if (str.equals(RenderManager.REPLY_CANCELED_INSTRUCTION_UUID)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1051797829:
                if (str.equals(RenderManager.REPLY_PROMPT_FOR_RECORDING_UUID)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1216648798:
                if (str.equals(RenderManager.START_REPLY_INSTRUCTION_UUID)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1267025748:
                if (str.equals(RenderManager.CONTACT_ACCEPTED_UUID)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1740537749:
                if (str.equals(RenderManager.APP_ACCEPTED_UUID)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        String str2 = null;
        switch (c) {
            case 0:
                if (i != 2) {
                    return;
                }
                Log.i(TAG, "handleGestureEvent - Tap received during multiple notifications instruction.");
                multipleNotificationsInstructionComplete();
                return;
            case 1:
                if (i != 2) {
                    return;
                }
                Log.i(TAG, "handleGestureEvent - Tap received during Group Message user Education.");
                groupMessagesUserEducationComplete();
                return;
            case 2:
            case 3:
                if (i == 1) {
                    Log.i(TAG, "handleGestureEvent - Swipe received during No Recent Notification");
                    transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, 1, null);
                    return;
                } else if (i != 2) {
                    GeneratedOutlineSupport1.outline151("handleGestureEvent - Invalid gesture: ", i, TAG);
                    return;
                } else {
                    Log.i(TAG, "handleGestureEvent - Tap received during No Recent Notification");
                    transitStateForNextNotification(2, 2, null);
                    return;
                }
            case 4:
            case 5:
            case 6:
            case 7:
                if (i == 1) {
                    Log.i(TAG, "handleGestureEvent - Swipe received during accepted audio - Transit to Content state after stop accepted audio");
                    transitToContent();
                    return;
                } else if (i != 2) {
                    GeneratedOutlineSupport1.outline151("handleGestureEvent - Invalid gesture: ", i, TAG);
                    return;
                } else {
                    Log.i(TAG, "handleGestureEvent - Tap received during accepted audio - Go to next notification after stop current audio");
                    renderGestureEarconAndGoToNextNotification(i);
                    return;
                }
            case '\b':
            case '\t':
            case '\n':
            case 11:
            case '\f':
                Log.i(TAG, "handleGestureEvent - gesture received during rejected audio orduring reply canceled audio - Go to next notification after stop current audio");
                renderGestureEarconAndGoToNextNotification(i);
                return;
            case '\r':
                if (i != 1) {
                    if (i != 2) {
                        GeneratedOutlineSupport1.outline151("handleGestureEvent - Invalid gesture: ", i, TAG);
                        return;
                    }
                    Log.i(TAG, "handleGestureEvent - Tap received during open notification instruction");
                    handleStopGestureWithAnnouncementAndInstruction();
                    return;
                }
                Log.i(TAG, "handleGestureEvent - Swipe received during open notification instruction");
                if (payload != null) {
                    str2 = payload.getDeviceType();
                }
                updateNumOfOpenedNotification(str2);
                transitToContent();
                return;
            case 14:
            case 15:
                if (i != 1 && i != 2) {
                    return;
                }
                this.lastPlayingInstruction = RenderManager.START_REPLY_INSTRUCTION_UUID;
                Log.d(TAG, "handleGestureEvent - Got valid action when swipe to reply was being played. Action: " + i);
                if (i == 1) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_ACCEPT);
                } else {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.REPLY_IGNORE);
                }
                transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, Integer.valueOf(i), null);
                return;
            case 16:
            case 17:
                if (i != 1) {
                    if (i != 2) {
                        GeneratedOutlineSupport1.outline151("handleGestureEvent - Invalid gesture: ", i, TAG);
                        return;
                    }
                    Log.i(TAG, "handleGestureEvent - Tap received during Prompt to Record or reply again low confidence flow - Transit to Instructional Audio State Handler");
                    this.lastPlayingInstruction = this.currentPlayingInstruction;
                    transitState(StateManager.STATE_INSTRUCTIONAL_AUDIO, 2, 2, null);
                    return;
                } else if (this.currentPlayingInstruction.equals(RenderManager.REPLY_AGAIN_UUID)) {
                    Log.i(TAG, "handleGestureEvent - Swipe received during Reply Again");
                    transitState(StateManager.STATE_REPLY, 8, 12, null);
                    return;
                } else {
                    Log.i(TAG, "handleGestureEvent - Swipe received during Prompt to Record");
                    updateNumOfRecordPrompts();
                    transitState(StateManager.STATE_REPLY);
                    return;
                }
            case 18:
            case 19:
                Log.d(TAG, "handleGestureEvent - Gesture while retry-able error message was being played");
                if (i != 2) {
                    return;
                }
                Log.i(TAG, "handleGestureEvent - User canceled reply during retry-able error message render");
                postEventMessageToStopInstruction();
                playReplyCanceledWithEarcon();
                return;
            case 20:
                Log.i(TAG, "handleGestureEvent - No record audio permission. Transit to next notification");
                transitStateForNextNotification();
                return;
            default:
                String str3 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("handleGestureEvent - Invalid instructional audio: ");
                outline107.append(this.currentPlayingInstruction);
                Log.w(str3, outline107.toString());
                return;
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleNotificationEvent(int i, @NonNull JSONObject jSONObject) {
        try {
            boolean isSameNotification = isSameNotification(jSONObject, NotificationQueue.getInstance().peek());
            super.handleNotificationEvent(i, jSONObject);
            if (1 == i && shouldEnqueue(jSONObject) && (RenderManager.NO_RECENT_NOTIFICATION_UUID.equals(this.currentPlayingInstruction) || RenderManager.OTG_NOTIFICATION_SOUNDS_ON_UUID.equalsIgnoreCase(this.currentPlayingInstruction))) {
                Log.i(TAG, "handleNotificationEvent - Handle incoming notification during playing No Recent Notification");
                transitStateForNextNotification(1, 1, null);
                return;
            }
            char c = 3;
            if (((2 != i || !isSameNotification) && 3 != i) || Strings.isNullOrEmpty(this.currentPlayingInstruction)) {
                return;
            }
            String str = this.currentPlayingInstruction;
            switch (str.hashCode()) {
                case -1800931083:
                    if (str.equals(RenderManager.REPLY_SENDING_UUID)) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -1706604718:
                    if (str.equals(RenderManager.ENABLE_MICROPHONE_PERMISSION_UUID)) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1298342220:
                    if (str.equals(RenderManager.REPLY_ERROR_RETRY_AVAILABLE_UUID)) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case -257758737:
                    if (str.equals(RenderManager.REPLY_AGAIN_UUID)) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -97843601:
                    if (str.equals(RenderManager.REPLY_ERROR_RETRY_LIMIT_REACHED_UUID)) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 833029040:
                    if (str.equals(RenderManager.REPLY_FEATURE_EDUCATION_UUID)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1051797829:
                    if (str.equals(RenderManager.REPLY_PROMPT_FOR_RECORDING_UUID)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1216648798:
                    if (str.equals(RenderManager.START_REPLY_INSTRUCTION_UUID)) {
                        break;
                    }
                    c = 65535;
                    break;
                case 1256600647:
                    if (str.equals(RenderManager.REPLY_ERROR_UUID)) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 1267025748:
                    if (str.equals(RenderManager.CONTACT_ACCEPTED_UUID)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1740537749:
                    if (str.equals(RenderManager.APP_ACCEPTED_UUID)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                    transitStateForNextNotification(1, 2, null);
                    return;
                case 4:
                case 5:
                case 6:
                case 7:
                case '\b':
                case '\t':
                case '\n':
                    playReplyCanceledWithEarcon();
                    transitStateForNextNotification(1, Integer.valueOf(i), null);
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to handleNotificationEvent.", e);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.CE_ERROR);
            transitState(StateManager.STATE_IDLE);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleRendererEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleRendererEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        String optString = jSONObject.optString("uuid");
        if (optInt == 3) {
            String str = TAG;
            Log.i(str, "handleRendererEvent - Render completed for eventID: " + i + " uuid: " + optString);
            if (i != 100) {
                return;
            }
            handleAudioCompletedByUUID(optString);
        } else if (optInt == 4) {
            String str2 = TAG;
            Log.i(str2, "handleRendererEvent - Render stopped for eventID: " + i + " uuid: " + optString);
        } else if (optInt != 5) {
            GeneratedOutlineSupport1.outline151("handleRendererEvent - Invalid Event Type: ", optInt, TAG);
        } else {
            String str3 = TAG;
            Log.w(str3, "handleRendererEvent - Render error for eventID: " + i + " uuid: " + optString);
            transitState(StateManager.STATE_IDLE);
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler, com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseEventHandler
    public void handleTimerEvent(int i, @NonNull JSONObject jSONObject) {
        super.handleTimerEvent(i, jSONObject);
        int optInt = jSONObject.optInt("eventType", Integer.MIN_VALUE);
        if (optInt != 3) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Invalid Event Type: ", optInt, TAG);
        } else if (i != 3) {
            GeneratedOutlineSupport1.outline151("handleTimerEvent - Ignored Event Id: ", i, TAG);
        } else {
            Log.i(TAG, "handleTimerEvent - Replay timer expired");
            clearLastNotificationWithAudioFile();
            clearLastNonInvitationNotificationWithAudioFile();
        }
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.StateHandler.BaseStateHandler
    public void leaveState(String str) {
        super.leaveState(str);
        postEventMessageToStopInstruction();
    }

    void updateNumOfRecordPrompts() {
        int intValue;
        if (this.shouldUpdateNumberOfRecordPrompts && (intValue = SettingsStorageModule.getInstance().getNumOfRecordPrompts().intValue()) < 3) {
            try {
                SettingsStorageModule.getInstance().putNumOfRecordPrompts(Integer.valueOf(intValue + 1));
            } catch (Exception e) {
                Log.w(TAG, "updateNumOfRecordPrompts - Exception: ", e);
            }
        }
    }

    private void postEventMessageToPlayInstruction(String str, String str2, int i) {
        postEventMessageToStopInstruction();
        GeneratedOutlineSupport1.outline166("Start rendering instructional audio ", str, TAG);
        postEventMessageToRenderManager(i, 1, str, str2);
        this.currentPlayingInstruction = str;
    }

    /* renamed from: clone */
    public InstructionalAudioStateHandler m345clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
