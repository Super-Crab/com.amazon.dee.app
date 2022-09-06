package com.amazon.deecomms.smsmessaging.service.fetchsms;

import android.content.Context;
import android.database.Cursor;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.deecomms.smsmessaging.model.CommunicableEntity;
import com.amazon.deecomms.smsmessaging.model.Conversation;
import com.amazon.deecomms.smsmessaging.model.Message;
import com.amazon.deecomms.smsmessaging.model.MessagePayload;
import com.amazon.deecomms.smsmessaging.util.CountryToPhonePrefix;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class SMSReadManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SMSReadManager.class);
    private Context mContext;
    private SMSFetchManager mSMSFetchManager;
    private Set<String> readMessageIdSet;

    public SMSReadManager(@NonNull Context context) {
        this.mContext = context;
        this.mSMSFetchManager = new SMSFetchManager(context);
    }

    private String formatAddressToE164(@NonNull String str) {
        String simCountryIso = ((TelephonyManager) this.mContext.getSystemService("phone")).getSimCountryIso();
        String formatNumberToE164 = PhoneNumberUtils.formatNumberToE164(str, simCountryIso);
        if (formatNumberToE164 != null) {
            return formatNumberToE164;
        }
        String formatNumberToE1642 = PhoneNumberUtils.formatNumberToE164("+" + str, simCountryIso);
        if (formatNumberToE1642 != null) {
            return formatNumberToE1642;
        }
        String prefixFor = CountryToPhonePrefix.prefixFor(simCountryIso.toUpperCase());
        String formatNumberToE1643 = PhoneNumberUtils.formatNumberToE164(prefixFor + str, simCountryIso);
        return formatNumberToE1643 != null ? formatNumberToE1643 : str;
    }

    private void getConversationContacts(@NonNull Conversation conversation, @NonNull Cursor cursor, boolean z) {
        cursor.moveToFirst();
        LinkedList linkedList = new LinkedList();
        if (z) {
            linkedList.add(new CommunicableEntity(formatAddressToE164(cursor.getString(cursor.getColumnIndex("address"))), CommunicableEntity.EntityFormat.PhoneNumberAddress));
        } else {
            getMMSContacts(cursor.getInt(cursor.getColumnIndex("_id")), linkedList);
        }
        conversation.participants = linkedList;
    }

    private JSONObject getConversationJSON(@NonNull Conversation conversation) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", conversation.id);
            JSONArray jSONArray = new JSONArray();
            for (CommunicableEntity communicableEntity : conversation.participants) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("address", communicableEntity.address);
                jSONObject2.put("addressType", communicableEntity.addressType.toString());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY, "");
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_OTHER_PARTICIPANTS_KEY, jSONArray);
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_MESSAGES_KEY, getMessagesJSONArray(conversation.messages));
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_UNREAD_COUNT_KEY, conversation.unreadMessageCount);
        } catch (JSONException e) {
            LOG.e("getConversationJSON error, ", e);
        }
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00bd A[EDGE_INSN: B:21:0x00bd->B:19:0x00bd ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getConversationMessages(@androidx.annotation.NonNull com.amazon.deecomms.smsmessaging.model.Conversation r9, @androidx.annotation.NonNull android.database.Cursor r10, int r11, int r12, boolean r13) {
        /*
            r8 = this;
            r10.moveToFirst()
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
        L8:
            com.amazon.deecomms.smsmessaging.model.Message r1 = new com.amazon.deecomms.smsmessaging.model.Message
            r1.<init>()
            com.amazon.deecomms.smsmessaging.model.Message$MessageStatus r2 = com.amazon.deecomms.smsmessaging.model.Message.MessageStatus.unread
            r1.status = r2
            java.lang.String r2 = "date"
            java.lang.String r3 = "_id"
            if (r13 == 0) goto L6d
            int r3 = r10.getColumnIndex(r3)
            int r3 = r10.getInt(r3)
            java.lang.String r4 = "SMS"
            java.lang.String r3 = com.android.tools.r8.GeneratedOutlineSupport1.outline49(r4, r3)
            r1.id = r3
            java.util.Set<java.lang.String> r3 = r8.readMessageIdSet
            java.lang.String r4 = r1.id
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto L33
            goto Lb5
        L33:
            int r2 = r10.getColumnIndex(r2)
            long r2 = r10.getLong(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r1.lastStatusChangedTime = r2
            com.amazon.deecomms.smsmessaging.model.MessagePayload r2 = new com.amazon.deecomms.smsmessaging.model.MessagePayload
            java.lang.String r3 = "body"
            int r3 = r10.getColumnIndex(r3)
            java.lang.String r3 = r10.getString(r3)
            r2.<init>(r3)
            r1.payload = r2
            java.lang.String r2 = "address"
            int r2 = r10.getColumnIndex(r2)
            java.lang.String r2 = r10.getString(r2)
            com.amazon.deecomms.smsmessaging.model.CommunicableEntity r3 = new com.amazon.deecomms.smsmessaging.model.CommunicableEntity
            java.lang.String r2 = r8.formatAddressToE164(r2)
            com.amazon.deecomms.smsmessaging.model.CommunicableEntity$EntityFormat r4 = com.amazon.deecomms.smsmessaging.model.CommunicableEntity.EntityFormat.PhoneNumberAddress
            r3.<init>(r2, r4)
            r1.sender = r3
            r0.add(r1)
            goto Lb3
        L6d:
            int r3 = r10.getColumnIndex(r3)
            int r3 = r10.getInt(r3)
            java.lang.String r4 = "MMS"
            java.lang.String r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline49(r4, r3)
            r1.id = r4
            java.util.Set<java.lang.String> r4 = r8.readMessageIdSet
            java.lang.String r5 = r1.id
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L88
            goto Lb5
        L88:
            int r2 = r10.getColumnIndex(r2)
            long r4 = r10.getLong(r2)
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r6
            java.lang.Long r2 = java.lang.Long.valueOf(r4)
            r1.lastStatusChangedTime = r2
            com.amazon.deecomms.smsmessaging.model.MessagePayload r2 = r8.getMMSPayload(r3)
            r1.payload = r2
            com.amazon.deecomms.smsmessaging.model.MessagePayload r2 = r1.payload
            if (r2 == 0) goto Lb5
            com.amazon.deecomms.smsmessaging.model.CommunicableEntity r2 = new com.amazon.deecomms.smsmessaging.model.CommunicableEntity
            java.lang.String r3 = r8.getMMSSender(r3)
            com.amazon.deecomms.smsmessaging.model.CommunicableEntity$EntityFormat r4 = com.amazon.deecomms.smsmessaging.model.CommunicableEntity.EntityFormat.PhoneNumberAddress
            r2.<init>(r3, r4)
            r1.sender = r2
            r0.add(r1)
        Lb3:
            int r11 = r11 + 1
        Lb5:
            if (r11 >= r12) goto Lbd
            boolean r1 = r10.moveToNext()
            if (r1 != 0) goto L8
        Lbd:
            r9.messages = r0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.smsmessaging.service.fetchsms.SMSReadManager.getConversationMessages(com.amazon.deecomms.smsmessaging.model.Conversation, android.database.Cursor, int, int, boolean):int");
    }

    private JSONArray getConversationsJSONArray(@NonNull List<Conversation> list) {
        JSONArray jSONArray = new JSONArray();
        for (Conversation conversation : list) {
            jSONArray.put(getConversationJSON(conversation));
        }
        return jSONArray;
    }

    private void getMMSContacts(int i, @NonNull List<CommunicableEntity> list) {
        Throwable th;
        Cursor cursor;
        try {
            cursor = this.mSMSFetchManager.getMMSAddresses(i);
            if (cursor != null) {
                try {
                    if (cursor.getCount() > 0) {
                        cursor.moveToLast();
                        do {
                            list.add(new CommunicableEntity(formatAddressToE164(cursor.getString(cursor.getColumnIndex("address"))), CommunicableEntity.EntityFormat.PhoneNumberAddress));
                        } while (cursor.moveToPrevious());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor == null) {
                return;
            }
            cursor.close();
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    private MessagePayload getMMSPayload(int i) {
        Throwable th;
        Cursor cursor;
        try {
            cursor = this.mSMSFetchManager.getMMSPayload(i);
            if (cursor != null) {
                try {
                    if (cursor.getCount() > 0) {
                        cursor.moveToLast();
                        do {
                            if (cursor.getString(cursor.getColumnIndex("ct")).equalsIgnoreCase(Constants.TEXT_PLAIN_MEDIA_TYPE)) {
                                MessagePayload messagePayload = new MessagePayload(cursor.getString(cursor.getColumnIndex("text")));
                                cursor.close();
                                return messagePayload;
                            }
                        } while (cursor.moveToPrevious());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    private String getMMSSender(int i) {
        Throwable th;
        Cursor cursor;
        try {
            cursor = this.mSMSFetchManager.getMMSSenderAddress(i);
            if (cursor != null) {
                try {
                    if (cursor.getCount() > 0) {
                        cursor.moveToLast();
                        String string = cursor.getString(cursor.getColumnIndex("address"));
                        String formatAddressToE164 = formatAddressToE164(string);
                        if (formatAddressToE164 != null) {
                            cursor.close();
                            return formatAddressToE164;
                        }
                        cursor.close();
                        return string;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor == null) {
                return "";
            }
            cursor.close();
            return "";
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
    }

    private JSONObject getMessageJSON(@NonNull Message message) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", message.id);
            jSONObject.put("payload", getMessagePayloadsJSONObject(message.payload));
            jSONObject.put("status", message.status.toString());
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_CREATED_TIME_KEY, timestampToISO8601(message.lastStatusChangedTime));
            jSONObject.put("sender", getSenderJSON(message.sender));
        } catch (JSONException e) {
            LOG.e("getMessageJSON error, ", e);
        }
        return jSONObject;
    }

    private JSONObject getMessagePayloadsJSONObject(@NonNull MessagePayload messagePayload) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE, messagePayload.type.toString());
            jSONObject.put("text", messagePayload.text);
        } catch (JSONException e) {
            LOG.e("getMessagePayloadsJSONArray error, ", e);
        }
        return jSONObject;
    }

    private boolean getMessages(@NonNull Cursor cursor, @NonNull List<Conversation> list, @NonNull Set<Integer> set, int i, boolean z) {
        int i2;
        if (z) {
            i2 = cursor.getInt(cursor.getColumnIndex("thread_id"));
        } else {
            i2 = cursor.getInt(cursor.getColumnIndex("thread_id"));
        }
        if (set.contains(Integer.valueOf(i2))) {
            return false;
        }
        set.add(Integer.valueOf(i2));
        return getUnreadMessagesInConversation(i2, list, i, z);
    }

    private JSONArray getMessagesJSONArray(@NonNull List<Message> list) {
        JSONArray jSONArray = new JSONArray();
        for (Message message : list) {
            jSONArray.put(getMessageJSON(message));
        }
        return jSONArray;
    }

    private JSONObject getSenderJSON(@NonNull CommunicableEntity communicableEntity) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("address", communicableEntity.address);
            jSONObject.put("addressType", communicableEntity.addressType.toString());
        } catch (JSONException e) {
            LOG.e("getSenderJSON error, ", e);
        }
        return jSONObject;
    }

    private boolean getUnreadMessagesInConversation(int i, @NonNull List<Conversation> list, int i2, boolean z) {
        boolean z2 = false;
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += list.get(i4).unreadMessageCount;
        }
        if (i3 >= i2) {
            return true;
        }
        Cursor cursor = null;
        try {
            Cursor conversationUnreadMessages = this.mSMSFetchManager.getConversationUnreadMessages(i, z);
            if (conversationUnreadMessages != null) {
                try {
                    if (conversationUnreadMessages.getCount() != 0) {
                        Conversation conversation = new Conversation();
                        conversation.id = Integer.toString(i);
                        conversation.unreadMessageCount = conversationUnreadMessages.getCount();
                        int conversationMessages = getConversationMessages(conversation, conversationUnreadMessages, i3, i2, z);
                        if (conversationMessages == i3) {
                            conversationUnreadMessages.close();
                            return false;
                        }
                        getConversationContacts(conversation, conversationUnreadMessages, z);
                        list.add(conversation);
                        if (conversationMessages >= i2) {
                            z2 = true;
                        }
                        conversationUnreadMessages.close();
                        return z2;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = conversationUnreadMessages;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (conversationUnreadMessages != null) {
                conversationUnreadMessages.close();
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public JSONObject getFullSyncPayload(@NonNull List<Conversation> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("conversations", getConversationsJSONArray(list));
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_UPLOAD_MODE_JSON_KEY, MessagingControllerConstant.ConversationUploadMode.DELETE_ALL_AND_STORE.toString());
        } catch (JSONException e) {
            LOG.e("getFullSyncPayload error, ", e);
        }
        return jSONObject;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.amazon.deecomms.smsmessaging.model.Conversation> getUnreadSMSMMSMessages(int r14) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.smsmessaging.service.fetchsms.SMSReadManager.getUnreadSMSMMSMessages(int):java.util.List");
    }

    public void setReadMessageIdSet(@NonNull Set<String> set) {
        this.readMessageIdSet = set;
    }

    @VisibleForTesting
    public String timestampToISO8601(@NonNull Long l) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(com.amazon.dee.sdk.iotsoftap.Constants.UTC));
        return simpleDateFormat.format(new Date(l.longValue())) + "Z";
    }
}
