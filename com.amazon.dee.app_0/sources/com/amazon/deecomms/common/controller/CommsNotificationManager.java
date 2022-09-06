package com.amazon.deecomms.common.controller;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.ui.CallActivity;
import com.amazon.deecomms.calling.ui.CallingInitiationActivity;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.contacts.model.IdentityRawData;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.messaging.model.ConversationNotification;
import com.amazon.deecomms.messaging.model.Message;
import com.amazon.deecomms.notifications.DirectReplyAnnouncementNotification;
import com.amazon.deecomms.notifications.DirectReplyTextMessageNotification;
import com.amazon.deecomms.notifications.NotificationActivatedReceiver;
import com.amazon.deecomms.notifications.NotificationDismissedReceiver;
import com.amazon.deecomms.notifications.model.announcement.AnnouncementPushNotification;
import com.amazon.deecomms.notifications.util.NotificationUtils;
import com.amazon.deecomms.platform.identity.CommunicableEntity;
import com.amazon.deecomms.platform.identity.Exceptions.MalformedCommsIDException;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.TreeSet;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public class CommsNotificationManager {
    private static final int ANNOUNCEMENT_NOTIFICATION_ID = 2147483646;
    public static final int CALL_NOTIFICATION_ID = 2;
    private static final int CONVERSATION_NOTIFICATION_ID = 10;
    public static final int HEADUP_CALL_NOTIFICATION_ID = 3;
    private static final int MAX_NUMBER_OF_STACKABLE_MESSAGES = 5;
    private static final int MAX_NUMBER_OF_STACKABLE_NOTIFICATIONS = 5;
    private Integer announcementSummaryId;
    private Comparator<ConversationNotification> dateComparator;
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    private Comparator<AnnouncementPushNotification> newestFirstComparator;
    private final SipClientState sipClientState;
    private NavigableSet<AnnouncementPushNotification> visibleAnnouncementNotifications;
    private final NavigableSet<ConversationNotification> visibleConversationNotifications;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsNotificationManager.class);
    private static int conversationNotificationIdPrefix = 11;

    /* renamed from: com.amazon.deecomms.common.controller.CommsNotificationManager$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState = new int[SipClientState.CallState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.CALLING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.OUTBOUND_RINGING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.ACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.INBOUND_RINGING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public CommsNotificationManager(Context context, NotificationManager notificationManager, SipClientState sipClientState) {
        this.dateComparator = new Comparator<ConversationNotification>() { // from class: com.amazon.deecomms.common.controller.CommsNotificationManager.1
            @Override // java.util.Comparator
            public int compare(ConversationNotification conversationNotification, ConversationNotification conversationNotification2) {
                if (conversationNotification.getConversationId().equals(conversationNotification2.getConversationId())) {
                    return 0;
                }
                if (conversationNotification.getMessage() == null) {
                    return 1;
                }
                if (conversationNotification2.getMessage() != null) {
                    return conversationNotification2.getMessage().getTime().compareTo(conversationNotification.getMessage().getTime());
                }
                return -1;
            }
        };
        this.newestFirstComparator = $$Lambda$CommsNotificationManager$57WS7EoeE8tIyXiy2XTIQu_u4.INSTANCE;
        this.visibleConversationNotifications = new TreeSet(this.dateComparator);
        this.mContext = context.getApplicationContext();
        this.mNotificationManager = notificationManager;
        this.sipClientState = sipClientState;
        this.visibleAnnouncementNotifications = new TreeSet(this.newestFirstComparator);
    }

    private void addIncomingToExistingNotification(@NonNull ConversationNotification conversationNotification) {
        Iterator it2 = new ArrayList(this.visibleConversationNotifications).iterator();
        while (it2.hasNext()) {
            ConversationNotification conversationNotification2 = (ConversationNotification) it2.next();
            if (conversationNotification2.equals(conversationNotification)) {
                conversationNotification2.addMessage(conversationNotification.getMessage());
                conversationNotification2.setReplySent(false);
                conversationNotification2.setReplySuccess(false);
            }
        }
    }

    private void addSummaryNotificationStyle(NotificationCompat.Builder builder, ArrayList<String> arrayList, String str) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        StringBuilder sb = new StringBuilder();
        Iterator<String> it2 = arrayList.iterator();
        int i = 0;
        while (it2.hasNext()) {
            String next = it2.next();
            sb.append(next);
            sb.append("\n");
            inboxStyle.addLine(next);
            i++;
            if (i > 5) {
                break;
            }
        }
        inboxStyle.setBigContentTitle(str);
        builder.setStyle(inboxStyle);
        builder.setContentText(sb);
    }

    private NotificationCompat.Style applyStyleToIndividualConversation(String str, ConversationNotification conversationNotification) {
        String[] linesForConversation = getLinesForConversation(conversationNotification);
        if (conversationNotification.containsLongMessage(this.mContext)) {
            return createBigTextStyle(str, linesForConversation);
        }
        return createInboxStyle(str, linesForConversation);
    }

    private NotificationCompat.BigTextStyle createBigTextStyle(String str, String[] strArr) {
        CharSequence buildBigText = buildBigText(strArr);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(str);
        bigTextStyle.bigText(buildBigText);
        return bigTextStyle;
    }

    private PendingIntent createClickedAnnouncementIntent(String str, int i) {
        Intent intent = new Intent(this.mContext, NotificationActivatedReceiver.class);
        String pathTolaunchAnnouncementList = NotificationUtils.pathTolaunchAnnouncementList(str);
        intent.putExtra("target", Constants.NOTIFICATION_TARGET_ANNOUNCEMENT);
        intent.putExtra("notification_redirect_path", pathTolaunchAnnouncementList);
        return PendingIntent.getBroadcast(this.mContext, i, intent, 268435456);
    }

    private PendingIntent createDismissAnnouncementNotificationIntent(int i) {
        Intent intent = new Intent(this.mContext, NotificationDismissedReceiver.class);
        intent.putExtra("target", Constants.NOTIFICATION_TARGET_ANNOUNCEMENT);
        intent.putExtra(Constants.BUNDLE_KEY_NOTIFICATION_ID, i);
        return PendingIntent.getBroadcast(this.mContext, i, intent, 0);
    }

    private NotificationCompat.InboxStyle createInboxStyle(String str, String[] strArr) {
        NotificationCompat.InboxStyle bigContentTitle = new NotificationCompat.InboxStyle().setBigContentTitle(str);
        for (int i = 0; i < 5; i++) {
            String str2 = strArr[i];
            if (str2 != null) {
                bigContentTitle.addLine(str2);
            }
        }
        return bigContentTitle;
    }

    private NotificationCompat.Builder createIndividualAnnouncementBuilder(int i, boolean z) {
        NotificationCompat.Builder createCommsGroupMemberNotification = createCommsGroupMemberNotification(Constants.NOTIFICATIONS_COMMS_ANNOUNCEMENT_GROUP);
        createCommsGroupMemberNotification.setDeleteIntent(createDismissAnnouncementNotificationIntent(i));
        if (z) {
            createCommsGroupMemberNotification.setOnlyAlertOnce(true);
        }
        if (CommsDaggerWrapper.getComponent().getAudioRecorder().isAudioMessageRecording()) {
            createCommsGroupMemberNotification.setDefaults(2);
        } else {
            createCommsGroupMemberNotification.setDefaults(-1);
        }
        return createCommsGroupMemberNotification;
    }

    private Notification createReplySentNotification(NotificationCompat.Builder builder, int i, ConversationNotification conversationNotification, String str, String str2, String str3) {
        String string;
        int color = ContextCompat.getColor(this.mContext, R.color.notification_icon_background);
        boolean isReplySuccess = conversationNotification.isReplySuccess();
        Resources resources = this.mContext.getResources();
        if (isReplySuccess) {
            string = resources.getString(R.string.notification_send_reply_success);
        } else {
            string = resources.getString(R.string.notification_send_reply_failure);
        }
        return builder.setContentTitle(str).setColor(color).setContentText(string).setSortKey(String.valueOf(i)).setContentIntent(createClickedMessageNotificationIntent(str3, str2, conversationNotification.getNotificationId())).build();
    }

    private void displaySummaryAndNotificationsInOrder(ArrayList<Notification> arrayList, Notification notification, int i) {
        setUpNotificationChannels();
        CommsLogger commsLogger = LOG;
        commsLogger.i("Notify summary notification: " + i);
        for (int size = arrayList.size() + (-1); size >= 0; size += -1) {
            Notification notification2 = arrayList.get(size);
            int i2 = notification2.extras.getInt(Constants.BUNDLE_KEY_NOTIFICATION_ID);
            this.mNotificationManager.notify(i2, notification2);
            CommsLogger commsLogger2 = LOG;
            commsLogger2.i("Notify individual notification: " + i2);
        }
        this.mNotificationManager.notify(i, notification);
    }

    private PendingIntent preparePendingIntent(Intent intent, int i) {
        return PendingIntent.getBroadcast(this.mContext, i, intent, 134217728);
    }

    private boolean removeNotificationMessageInternal(String str) {
        NavigableSet<ConversationNotification> navigableSet;
        if (TextUtils.isEmpty(str) || (navigableSet = this.visibleConversationNotifications) == null) {
            return false;
        }
        for (ConversationNotification conversationNotification : navigableSet) {
            if (str.equals(conversationNotification.getConversationId())) {
                this.visibleConversationNotifications.remove(conversationNotification);
                return true;
            }
        }
        return false;
    }

    private boolean removeNotificationMessageMatch(int i) {
        NavigableSet<ConversationNotification> navigableSet = this.visibleConversationNotifications;
        if (navigableSet != null) {
            for (ConversationNotification conversationNotification : navigableSet) {
                if (i == conversationNotification.getNotificationId()) {
                    this.visibleConversationNotifications.remove(conversationNotification);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private void removeSameAnnouncementInternal(String str) {
        NavigableSet<AnnouncementPushNotification> navigableSet;
        if (!TextUtils.isEmpty(str) && (navigableSet = this.visibleAnnouncementNotifications) != null) {
            for (AnnouncementPushNotification announcementPushNotification : navigableSet) {
                if (str.equals(announcementPushNotification.getAnnouncementId())) {
                    this.visibleAnnouncementNotifications.remove(announcementPushNotification);
                    return;
                }
            }
        }
    }

    public void addAnnouncementPushNotification(@NonNull AnnouncementPushNotification announcementPushNotification) {
        removeSameAnnouncementInternal(announcementPushNotification.getAnnouncementId());
        if (this.visibleAnnouncementNotifications.size() == 5) {
            AnnouncementPushNotification last = this.visibleAnnouncementNotifications.last();
            this.mNotificationManager.cancel(last.getNotificationId().intValue());
            this.visibleAnnouncementNotifications.remove(last);
        }
        if (this.visibleAnnouncementNotifications.add(announcementPushNotification)) {
            displayRecentAnnouncementNotifications(false);
        }
    }

    public NotificationCompat.Action addDirectReplyToAnnouncement(String str, String str2, int i, String str3) {
        String string = this.mContext.getResources().getString(R.string.reply_label);
        RemoteInput build = new RemoteInput.Builder(Constants.REMOTE_INPUT_REPLY).setLabel(string).build();
        Intent intent = new Intent(this.mContext, DirectReplyAnnouncementNotification.class);
        intent.putExtra(Constants.DEFAULT_ANNOUNCEMENT_NOTIFICATION_ID, i);
        intent.putExtra(Constants.DIRECTED_ID_PREF, str);
        intent.putExtra(Constants.FIRST_NAME_PREF, str2);
        intent.putExtra(Constants.REMOTE_INPUT_ANNOUNCEMENT_ID, str3);
        return new NotificationCompat.Action.Builder(R.drawable.ic_reply_icon, string, PendingIntent.getBroadcast(this.mContext, (int) System.currentTimeMillis(), intent, 134217728)).addRemoteInput(build).build();
    }

    public NotificationCompat.Builder addDirectReplyToMessageNotification(NotificationCompat.Builder builder, String str, String str2, int i) {
        String string = this.mContext.getResources().getString(R.string.reply_label);
        RemoteInput build = new RemoteInput.Builder(Constants.REMOTE_INPUT_REPLY).setLabel(string).build();
        Intent intent = new Intent(this.mContext, DirectReplyTextMessageNotification.class);
        intent.putExtra(Constants.BUNDLE_KEY_CONVERSATION_ID, str);
        intent.putExtra(Constants.BUNDLE_KEY_NOTIFICATION_ID, i);
        intent.putExtra("PATH", str2);
        return builder.addAction(new NotificationCompat.Action.Builder(R.drawable.ic_reply_icon, string, PendingIntent.getBroadcast(this.mContext, (int) System.currentTimeMillis(), intent, 134217728)).addRemoteInput(build).setAllowGeneratedReplies(true).build());
    }

    public void addNotificationMessage(@NonNull ConversationNotification conversationNotification) {
        conversationNotification.setNotificationId(conversationNotificationIdPrefix);
        conversationNotificationIdPrefix++;
        if (!this.visibleConversationNotifications.add(conversationNotification)) {
            addIncomingToExistingNotification(conversationNotification);
        }
        displayRecentConversationNotifications(false);
    }

    @VisibleForTesting
    @NotNull
    CharSequence buildBigText(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            if (str != null) {
                sb.append(str);
                sb.append("\n");
            }
        }
        return sb.subSequence(0, sb.length() - 1);
    }

    public void cancelNotification(int i) {
        this.mNotificationManager.cancel(i);
    }

    @VisibleForTesting
    public boolean checkQuickReplyValidMessageType(String str) {
        return "message/text".equals(str) || "message/audio".equals(str);
    }

    public void clearAnnouncements() {
        this.visibleAnnouncementNotifications.clear();
        this.mNotificationManager.cancel(ANNOUNCEMENT_NOTIFICATION_ID);
    }

    public void clearMessages() {
        conversationNotificationIdPrefix = 11;
        if (!this.visibleConversationNotifications.isEmpty()) {
            this.visibleConversationNotifications.clear();
        }
        this.mNotificationManager.cancel(10);
    }

    @VisibleForTesting
    @NotNull
    NotificationCompat.Builder commsInternalConversationNotification(int i, String str, boolean z) {
        NotificationCompat.Builder createCommsMessagingGroupBuilder = createCommsMessagingGroupBuilder(i, str, z);
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.BUNDLE_KEY_NOTIFICATION_ID, i);
        bundle.putString(Constants.BUNDLE_KEY_CONVERSATION_ID, str);
        createCommsMessagingGroupBuilder.addExtras(bundle);
        return createCommsMessagingGroupBuilder;
    }

    @VisibleForTesting
    protected PendingIntent createClickedMessageNotificationIntent(String str, @Nullable String str2, int i) {
        Intent intent = new Intent(this.mContext, NotificationActivatedReceiver.class);
        intent.putExtra("notification_redirect_path", str);
        intent.putExtra("target", "MESSAGE");
        intent.putExtra(Constants.BUNDLE_KEY_NOTIFICATION_ID, i);
        intent.putExtra(Constants.BUNDLE_KEY_CONVERSATION_ID, str2);
        return PendingIntent.getBroadcast(this.mContext, i, intent, 268435456);
    }

    NotificationCompat.Builder createCommsGroupMemberNotification(String str) {
        return createCommsNotificationBuilder(Constants.NOTIFICATIONS_COMMS_CHANNEL).setAutoCancel(true).setGroup(str);
    }

    @VisibleForTesting
    NotificationCompat.Builder createCommsMessagingGroupBuilder(int i, @Nullable String str, boolean z) {
        NotificationCompat.Builder deleteIntent = createCommsGroupMemberNotification(Constants.NOTIFICATIONS_COMMS_MESSAGES_GROUP).setDeleteIntent(createDismissMessageNotificationIntent(i, str));
        if (z) {
            deleteIntent.setOnlyAlertOnce(true);
        }
        if (CommsDaggerWrapper.getComponent().getAudioRecorder().isAudioMessageRecording()) {
            deleteIntent.setDefaults(2);
        } else {
            deleteIntent.setDefaults(-1);
        }
        return deleteIntent;
    }

    @VisibleForTesting
    NotificationCompat.Builder createCommsNotificationBuilder(String str) {
        return new NotificationCompat.Builder(this.mContext, str).setChannelId(str).setSmallIcon(R.drawable.ic_comms_notification).setColor(ContextCompat.getColor(this.mContext, R.color.notification_icon_background)).setShowWhen(true).setWhen(System.currentTimeMillis());
    }

    @VisibleForTesting
    protected PendingIntent createDismissMessageNotificationIntent(int i, String str) {
        Intent intent = new Intent(this.mContext, NotificationDismissedReceiver.class);
        intent.putExtra("target", "MESSAGE");
        intent.putExtra(Constants.BUNDLE_KEY_NOTIFICATION_ID, i);
        intent.putExtra(Constants.BUNDLE_KEY_CONVERSATION_ID, str);
        return PendingIntent.getBroadcast(this.mContext, i, intent, 0);
    }

    NotificationCompat.Builder createHighPriorityCommsNotification() {
        return createCommsNotificationBuilder(Constants.NOTIFICATIONS_COMMS_CHANNEL).setCategory("msg").setPriority(1).setAutoCancel(true);
    }

    @VisibleForTesting
    NotificationCompat.Builder createMultiNotificationsBuilder(NotificationCompat.Builder builder) {
        builder.setContentIntent(createClickedMessageNotificationIntent(NotificationUtils.pathTolaunchConversationList(), null, 10));
        int i = 0;
        CharSequence quantityString = this.mContext.getResources().getQuantityString(R.plurals.notifications_new_messages, this.visibleConversationNotifications.size(), Integer.valueOf(this.visibleConversationNotifications.size()));
        builder.setContentTitle(quantityString);
        NotificationCompat.InboxStyle bigContentTitle = new NotificationCompat.InboxStyle().setBigContentTitle(quantityString);
        for (ConversationNotification conversationNotification : this.visibleConversationNotifications) {
            String notificationText = conversationNotification.getMessage().getPayload().getNotificationText(this.mContext);
            String displayName = conversationNotification.getDisplayName();
            bigContentTitle.addLine(displayName + "   " + notificationText);
            i++;
            if (i >= 5) {
                break;
            }
        }
        builder.setStyle(bigContentTitle);
        return builder;
    }

    @VisibleForTesting
    void displayNotificationUI(int i, @NonNull Notification notification) {
        setUpNotificationChannels();
        this.mNotificationManager.notify(i, notification);
    }

    public void displayRecentAnnouncementNotifications(boolean z) {
        boolean isEmpty = this.visibleAnnouncementNotifications.isEmpty();
        int i = ANNOUNCEMENT_NOTIFICATION_ID;
        if (isEmpty) {
            this.mNotificationManager.cancel(ANNOUNCEMENT_NOTIFICATION_ID);
            return;
        }
        ArrayList<Notification> arrayList = new ArrayList<>(5);
        String string = this.mContext.getResources().getString(R.string.notification_new_announcement_from);
        NotificationCompat.Builder priority = createIndividualAnnouncementBuilder(ANNOUNCEMENT_NOTIFICATION_ID, z).setContentIntent(createClickedAnnouncementIntent("", ANNOUNCEMENT_NOTIFICATION_ID)).setPriority(1);
        AnnouncementPushNotification first = this.visibleAnnouncementNotifications.first();
        boolean z2 = false;
        int i2 = 0;
        for (AnnouncementPushNotification announcementPushNotification : this.visibleAnnouncementNotifications) {
            NotificationCompat.Builder createIndividualAnnouncementBuilder = createIndividualAnnouncementBuilder(announcementPushNotification.getNotificationId().intValue(), z);
            if (first.getAnnouncementId().equals(announcementPushNotification.getAnnouncementId()) && CommsDaggerWrapper.getComponent().getCapabilitiesManager().isEnhancedNotificationEnabled()) {
                CommsIdentityManager commsIdentityManager = CommsDaggerWrapper.getComponent().getCommsIdentityManager();
                createIndividualAnnouncementBuilder.addAction(addDirectReplyToAnnouncement(commsIdentityManager.getDirectedId("updateAnnouncementNotification", z2), commsIdentityManager.getFirstName(), i, first.getAnnouncementId()));
            }
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.BUNDLE_KEY_NOTIFICATION_ID, announcementPushNotification.getNotificationId().intValue());
            Object[] objArr = new Object[1];
            String sourceName = announcementPushNotification.getSourceName();
            char c = z2 ? 1 : 0;
            char c2 = z2 ? 1 : 0;
            objArr[c] = sourceName;
            String format = String.format(string, objArr);
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
            bigTextStyle.setBigContentTitle(format);
            bigTextStyle.bigText(announcementPushNotification.getDisplayText());
            createIndividualAnnouncementBuilder.setContentIntent(createClickedAnnouncementIntent(announcementPushNotification.getAnnouncementId(), announcementPushNotification.getNotificationId().intValue())).setContentTitle(format).setStyle(bigTextStyle).setContentText(announcementPushNotification.getDisplayText()).addExtras(bundle).setSortKey(String.valueOf(i2));
            i2++;
            arrayList.add(createIndividualAnnouncementBuilder.build());
            i = ANNOUNCEMENT_NOTIFICATION_ID;
            z2 = false;
        }
        priority.setContentText(this.mContext.getResources().getQuantityString(R.plurals.notifications_new_announcements, this.visibleAnnouncementNotifications.size(), Integer.valueOf(this.visibleAnnouncementNotifications.size())));
        displaySummaryAndNotificationsInOrder(arrayList, priority.setGroupSummary(true).build(), ANNOUNCEMENT_NOTIFICATION_ID);
    }

    @VisibleForTesting
    public void displayRecentConversationNotifications(boolean z) {
        if (this.visibleConversationNotifications.isEmpty()) {
            this.mNotificationManager.cancel(10);
            return;
        }
        int i = 5;
        ArrayList<Notification> arrayList = new ArrayList<>(5);
        NotificationCompat.Builder priority = createCommsMessagingGroupBuilder(10, null, z).setContentIntent(createClickedMessageNotificationIntent(NotificationUtils.pathTolaunchConversationList(), null, 10)).setPriority(1);
        ArrayList<String> arrayList2 = new ArrayList<>();
        int i2 = 0;
        for (ConversationNotification conversationNotification : this.visibleConversationNotifications) {
            if (i2 > i) {
                break;
            }
            String conversationId = conversationNotification.getConversationId();
            int notificationId = conversationNotification.getNotificationId();
            NotificationCompat.Builder commsInternalConversationNotification = commsInternalConversationNotification(notificationId, conversationId, z);
            long timeAsLong = conversationNotification.getTimeAsLong();
            if (timeAsLong != -1) {
                commsInternalConversationNotification.setWhen(timeAsLong);
            }
            String displayName = conversationNotification.getDisplayName();
            String conversationPath = conversationNotification.getConversationPath();
            if (conversationNotification.isReplySent()) {
                i2++;
                arrayList.add(createReplySentNotification(commsInternalConversationNotification, i2, conversationNotification, displayName, conversationId, conversationPath));
            } else {
                int i3 = i2;
                PendingIntent createClickedMessageNotificationIntent = createClickedMessageNotificationIntent(conversationPath, conversationId, notificationId);
                commsInternalConversationNotification.setContentIntent(createClickedMessageNotificationIntent);
                priority.setContentIntent(createClickedMessageNotificationIntent);
                Message message = conversationNotification.getMessage();
                if (CommsDaggerWrapper.getComponent().getCapabilitiesManager().isEnhancedNotificationEnabled() && checkQuickReplyValidMessageType(message.getType())) {
                    if (conversationId == null) {
                        LOG.e("Conversation Id is null, can't attach reply button");
                    } else {
                        commsInternalConversationNotification = addDirectReplyToMessageNotification(commsInternalConversationNotification, conversationId, conversationPath, conversationNotification.getNotificationId());
                    }
                }
                NotificationCompat.Style applyStyleToIndividualConversation = applyStyleToIndividualConversation(displayName, conversationNotification);
                String payloadNotificationText = message.getPayloadNotificationText(this.mContext);
                if (payloadNotificationText != null) {
                    arrayList2.add(displayName + " " + payloadNotificationText);
                }
                commsInternalConversationNotification.setContentTitle(displayName).setStyle(applyStyleToIndividualConversation).setContentText(payloadNotificationText).setSortKey(String.valueOf(i3));
                i2 = i3 + 1;
                arrayList.add(commsInternalConversationNotification.build());
            }
            i = 5;
        }
        String quantityString = this.mContext.getResources().getQuantityString(R.plurals.notifications_new_messages, this.visibleConversationNotifications.size(), Integer.valueOf(this.visibleConversationNotifications.size()));
        addSummaryNotificationStyle(priority, arrayList2, quantityString);
        priority.setContentTitle(quantityString);
        displaySummaryAndNotificationsInOrder(arrayList, priority.setGroupSummary(true).build(), 10);
    }

    @VisibleForTesting
    String[] getLinesForConversation(ConversationNotification conversationNotification) {
        String[] strArr = new String[5];
        int i = 0;
        for (Message message : conversationNotification.getMessages()) {
            if (i >= 5) {
                break;
            } else if (!TextUtils.isEmpty(message.getType())) {
                strArr[i] = message.getPayloadNotificationText(this.mContext);
                i++;
            }
        }
        return strArr;
    }

    public Comparator<AnnouncementPushNotification> getNewestFirstComparator() {
        return this.newestFirstComparator;
    }

    public Notification getPendingNotificationForFGService(String str) {
        Intent intent;
        String string;
        String string2;
        String string3;
        NotificationCompat.Builder createCommsNotificationBuilder = createCommsNotificationBuilder(Constants.NOTIFICATIONS_CALLING_CHANNEL);
        String callProvider = this.sipClientState.getCallProvider();
        if (CommsDaggerWrapper.getComponent().getCallInitiationAuthority().isNewCallInitiationUIFlowEnabled(Optional.fromNullable(callProvider), Optional.fromNullable(this.sipClientState.getCspId()))) {
            intent = new Intent(this.mContext, NewCallActivity.class);
        } else {
            intent = new Intent(this.mContext, CallActivity.class);
        }
        intent.putExtra("COMMS_ID", this.sipClientState.getRemoteParticipantId());
        SipClientState.CallState callState = this.sipClientState.getCallState();
        CallType callType = this.sipClientState.getCallType();
        String remoteParticipantName = this.sipClientState.getRemoteParticipantName();
        if (remoteParticipantName == null) {
            remoteParticipantName = this.mContext.getResources().getString(R.string.unknown_contact);
        }
        intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, remoteParticipantName);
        intent.putExtra(Constants.CALL_TYPE, callType.toString());
        intent.putExtra(Constants.CALL_PROVIDER, callProvider);
        int ordinal = callState.ordinal();
        if (ordinal != 2) {
            if (ordinal == 4) {
                intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, Constants.FRAGMENT_INCOMING_CALL_KEY);
                if (callType.isVideo()) {
                    string2 = this.mContext.getResources().getString(R.string.incoming_video_call);
                } else {
                    string2 = this.mContext.getResources().getString(R.string.call_from);
                }
                createCommsNotificationBuilder.setContentTitle(string2);
                createCommsNotificationBuilder.setContentText(remoteParticipantName);
            } else if (ordinal != 5) {
                if (ordinal != 6) {
                    CommsLogger commsLogger = LOG;
                    commsLogger.e("[Comms-calling]: Invalid SIP client state : " + callState + ", Returning null notification");
                    return null;
                }
                intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, Constants.FRAGMENT_ACTIVE_CALL_KEY);
                if (callType.isVideo()) {
                    string3 = this.mContext.getResources().getString(R.string.active_video_call_duration);
                } else {
                    string3 = this.mContext.getResources().getString(R.string.active_call_duration);
                }
                createCommsNotificationBuilder.setContentTitle(String.format(Locale.getDefault(), string3, str));
                createCommsNotificationBuilder.setContentText(remoteParticipantName);
                createCommsNotificationBuilder.setOnlyAlertOnce(true);
            }
            intent.setFlags(272629760);
            PendingIntent activity = PendingIntent.getActivity(this.mContext, (int) System.currentTimeMillis(), intent, 268435456);
            createCommsNotificationBuilder.setOngoing(true);
            createCommsNotificationBuilder.setWhen(System.currentTimeMillis());
            createCommsNotificationBuilder.setContentIntent(activity);
            return createCommsNotificationBuilder.build();
        }
        intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, Constants.FRAGMENT_OUTGOING_CALL_KEY);
        if (callType.isVideo()) {
            string = this.mContext.getResources().getString(R.string.outgoing_video_call);
        } else {
            string = this.mContext.getResources().getString(R.string.outgoing_Call);
        }
        createCommsNotificationBuilder.setContentTitle(string);
        createCommsNotificationBuilder.setContentText(remoteParticipantName);
        intent.setFlags(272629760);
        PendingIntent activity2 = PendingIntent.getActivity(this.mContext, (int) System.currentTimeMillis(), intent, 268435456);
        createCommsNotificationBuilder.setOngoing(true);
        createCommsNotificationBuilder.setWhen(System.currentTimeMillis());
        createCommsNotificationBuilder.setContentIntent(activity2);
        return createCommsNotificationBuilder.build();
    }

    @VisibleForTesting
    NavigableSet<AnnouncementPushNotification> getVisibleAnnouncementNotifications() {
        return this.visibleAnnouncementNotifications;
    }

    @VisibleForTesting
    NavigableSet<ConversationNotification> getVisibleConversationNotifications() {
        return this.visibleConversationNotifications;
    }

    @VisibleForTesting
    boolean isAudioMessageRecording() {
        return CommsDaggerWrapper.getComponent().getAudioRecorder().isAudioMessageRecording();
    }

    public /* synthetic */ void lambda$showMissedCallNotification$1$CommsNotificationManager(String str, Call call, CallType callType, String str2) {
        String uri;
        String callerNameForCommsId = CallUtils.getCallerNameForCommsId(str, "MissedCallNotif");
        if (callerNameForCommsId == null) {
            LOG.w("Unable to get caller name; not showing missed call notification");
            return;
        }
        IdentityRawData fetchIdentityRawDataForCommsId = ContactsProviderUtils.fetchIdentityRawDataForCommsId(this.mContext, str);
        if (fetchIdentityRawDataForCommsId != null && !TextUtils.isEmpty(fetchIdentityRawDataForCommsId.getAor())) {
            LOG.d("Able to find IdentityRawData, using sip aor as device gruu");
            uri = fetchIdentityRawDataForCommsId.getAor();
        } else {
            LOG.d("Fall back to the remote participants aor");
            uri = call.getRemoteParticipant().getUri();
        }
        LOG.i("Successfully retrieved a name; proceeding with displaying a missed call notification");
        showMissedCallNotification(callType, str2, str, callerNameForCommsId, uri);
    }

    public void removeAnnouncementNotification(int i) {
        AnnouncementPushNotification announcementPushNotification = null;
        for (AnnouncementPushNotification announcementPushNotification2 : this.visibleAnnouncementNotifications) {
            if (i == announcementPushNotification2.getNotificationId().intValue()) {
                announcementPushNotification = announcementPushNotification2;
            }
        }
        if (announcementPushNotification == null) {
            return;
        }
        this.mNotificationManager.cancel(announcementPushNotification.getNotificationId().intValue());
        this.visibleAnnouncementNotifications.remove(announcementPushNotification);
    }

    public void removeNotificationMessage(int i) {
        if (removeNotificationMessageMatch(i)) {
            displayRecentConversationNotifications(true);
        }
    }

    public void removeNotificationMessageByConversationId(String str) {
        if (removeNotificationMessageInternal(str)) {
            displayRecentConversationNotifications(true);
        }
    }

    public void setReplySentToExistingNotification(String str, boolean z) {
        Iterator it2 = new ArrayList(this.visibleConversationNotifications).iterator();
        while (it2.hasNext()) {
            ConversationNotification conversationNotification = (ConversationNotification) it2.next();
            if (conversationNotification.getConversationId().equals(str)) {
                conversationNotification.setReplySent(true);
                conversationNotification.setReplySuccess(z);
                conversationNotification.clearMessages();
            }
        }
    }

    @VisibleForTesting
    public void setUpNotificationChannels() {
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        if (notificationManager != null) {
            int i = Build.VERSION.SDK_INT;
            NotificationChannel notificationChannel = new NotificationChannel(Constants.NOTIFICATIONS_CALLING_CHANNEL, this.mContext.getResources().getString(R.string.comms_notifications_channel_name_calling), 2);
            NotificationChannel notificationChannel2 = new NotificationChannel(Constants.NOTIFICATIONS_COMMS_CHANNEL, this.mContext.getResources().getString(R.string.comms_notifications_channel_name_messaging), 4);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.createNotificationChannel(notificationChannel2);
        }
    }

    public void showMissedCallNotification(@NonNull final Call call) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Requested to show missed call notification for call id ");
        outline1.append(call.getCallId());
        commsLogger.i(outline1.toString());
        final CallType callType = call.isVideoRequested() ? CallType.VIDEO : CallType.AUDIO;
        final String fromString = CallProvider.fromString(call.getProvider());
        final String providerSpecifiedId = call.getRemoteParticipant().getProviderSpecifiedId();
        if (TextUtils.isEmpty(providerSpecifiedId)) {
            LOG.w("Unable to get commsId from call; not showing missed call notification");
        } else {
            AsyncTask.execute(new Runnable() { // from class: com.amazon.deecomms.common.controller.-$$Lambda$CommsNotificationManager$HW1IyGDiRwK-HTwwn9s_vcYgHhs
                @Override // java.lang.Runnable
                public final void run() {
                    CommsNotificationManager.this.lambda$showMissedCallNotification$1$CommsNotificationManager(providerSpecifiedId, call, callType, fromString);
                }
            });
        }
    }

    public void updateDirectReplyToMessageNotification(String str, boolean z) {
        setReplySentToExistingNotification(str, z);
        displayRecentConversationNotifications(false);
    }

    private void showMissedCallNotification(@NonNull CallType callType, @NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        Intent intent;
        String pathTolaunchConversation;
        String string;
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Creating missed call notification for ");
        outline1.append(LOG.sensitive(str2));
        commsLogger.i(outline1.toString());
        try {
            boolean isHomegroup = CommunicableEntity.fromCommsID(str2).isHomegroup();
            int hashCode = str2.hashCode();
            if (CommsDaggerWrapper.getComponent().getCallInitiationAuthority().isNewCallInitiationUIFlowEnabled(Optional.fromNullable(str), Optional.absent())) {
                intent = new Intent(this.mContext, CallingInitiationActivity.class);
            } else {
                intent = new Intent(this.mContext, CallActivity.class);
            }
            intent.putExtra("COMMS_ID", str2);
            intent.putExtra(Constants.DEVICE_GRUU, str4);
            intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, str3);
            intent.putExtra(Constants.CALL_START_TIME, System.currentTimeMillis());
            intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, Constants.FRAGMENT_OUTGOING_CALL_KEY);
            intent.putExtra(Constants.CALL_ID, CallUtils.generateCallId());
            intent.putExtra(Constants.CALL_TYPE, callType.toString());
            intent.putExtra(Constants.CALL_PROVIDER, str);
            intent.putExtra(Constants.NOTIFICATION_ID, hashCode);
            intent.putExtra(Constants.SET_SIP_CLIENT_STATE, true);
            intent.setFlags(335544320);
            NotificationCompat.Action action = new NotificationCompat.Action(0, this.mContext.getString(R.string.call_back), PendingIntent.getActivity(this.mContext, hashCode, intent, 268435456));
            if (isHomegroup) {
                pathTolaunchConversation = NotificationUtils.pathTolaunchConversationList();
            } else {
                pathTolaunchConversation = NotificationUtils.pathTolaunchConversation("UNKNOWN", str2);
            }
            PendingIntent createClickedMessageNotificationIntent = createClickedMessageNotificationIntent(pathTolaunchConversation, null, hashCode);
            NotificationCompat.Builder createCommsNotificationBuilder = createCommsNotificationBuilder(Constants.NOTIFICATIONS_CALLING_CHANNEL);
            if (callType == CallType.VIDEO) {
                string = this.mContext.getString(R.string.missed_video_call);
            } else {
                string = this.mContext.getString(R.string.missed_audio_call);
            }
            createCommsNotificationBuilder.setContentTitle(string).setContentText(str3).setCategory("msg").setPriority(1).setOngoing(false).setAutoCancel(true).setContentIntent(createClickedMessageNotificationIntent).addAction(action);
            displayNotificationUI(hashCode, createCommsNotificationBuilder.build());
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline12 = GeneratedOutlineSupport.outline1("Missed call notification shown for comms id ");
            outline12.append(LOG.sensitive(str2));
            commsLogger2.i(outline12.toString());
        } catch (MalformedCommsIDException e) {
            LOG.e("Unable to parse comms id; not showing notification", e);
        }
    }

    public CommsNotificationManager(Context context, NotificationManager notificationManager, SipClientState sipClientState, NavigableSet<AnnouncementPushNotification> navigableSet) {
        this.dateComparator = new Comparator<ConversationNotification>() { // from class: com.amazon.deecomms.common.controller.CommsNotificationManager.1
            @Override // java.util.Comparator
            public int compare(ConversationNotification conversationNotification, ConversationNotification conversationNotification2) {
                if (conversationNotification.getConversationId().equals(conversationNotification2.getConversationId())) {
                    return 0;
                }
                if (conversationNotification.getMessage() == null) {
                    return 1;
                }
                if (conversationNotification2.getMessage() != null) {
                    return conversationNotification2.getMessage().getTime().compareTo(conversationNotification.getMessage().getTime());
                }
                return -1;
            }
        };
        this.newestFirstComparator = $$Lambda$CommsNotificationManager$57WS7EoeE8tIyXiy2XTIQu_u4.INSTANCE;
        this.visibleConversationNotifications = new TreeSet(this.dateComparator);
        this.mNotificationManager = notificationManager;
        this.mContext = context;
        this.sipClientState = sipClientState;
        this.visibleAnnouncementNotifications = navigableSet;
    }
}
