package com.amazon.deecomms.contacts.presence;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.contacts.presence.ContactPresenceService;
import com.amazon.deecomms.contacts.presence.model.PresenceDocument;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.List;
/* loaded from: classes12.dex */
public abstract class PresenceReplyHandler extends Handler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PresenceReplyHandler.class);
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: processMessage */
    public void lambda$handleMessage$0$PresenceReplyHandler(Message message) {
        switch (message.what) {
            case 101:
                handleReplyActiveCount(message.arg1);
                return;
            case 102:
                Object obj = message.obj;
                if (!(obj instanceof ContactPresenceService.ContactActivityBannerReply)) {
                    return;
                }
                ContactPresenceService.ContactActivityBannerReply contactActivityBannerReply = (ContactPresenceService.ContactActivityBannerReply) obj;
                handleReplyActivityForBanner(contactActivityBannerReply.totalWithPresence, contactActivityBannerReply.totalActive, contactActivityBannerReply.nameForBanner, contactActivityBannerReply.contactCommsId);
                return;
            case 103:
                Object obj2 = message.obj;
                if (!(obj2 instanceof List)) {
                    return;
                }
                handleReplyActiveContacts((List) obj2);
                return;
            case 104:
                Object obj3 = message.obj;
                if (!(obj3 instanceof List)) {
                    return;
                }
                handleReplyAllContacts((List) obj3);
                return;
            case 105:
                Object obj4 = message.obj;
                if (!(obj4 instanceof ContactPresenceService.ContactActiveReply)) {
                    return;
                }
                ContactPresenceService.ContactActiveReply contactActiveReply = (ContactPresenceService.ContactActiveReply) obj4;
                handleReplyIsContactActive(contactActiveReply.hgCommsId, contactActiveReply.active);
                return;
            case 106:
                Object obj5 = message.obj;
                if (!(obj5 instanceof PresenceDocument)) {
                    return;
                }
                handleReplyGetContactPresence((PresenceDocument) obj5);
                return;
            default:
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("PresenceReplyHandler - Unsupported Message Sent - ");
                outline1.append(message.what);
                commsLogger.e(outline1.toString());
                return;
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        final Message message2 = new Message();
        message2.copyFrom(message);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.contacts.presence.-$$Lambda$PresenceReplyHandler$tbOOJ8dUUy5TCAcLoQScxmRkdVo
            @Override // java.lang.Runnable
            public final void run() {
                PresenceReplyHandler.this.lambda$handleMessage$0$PresenceReplyHandler(message2);
            }
        });
    }

    protected abstract void handleReplyActiveContacts(List<PresenceDocument> list);

    protected abstract void handleReplyActiveCount(int i);

    protected abstract void handleReplyActivityForBanner(int i, int i2, String str, String str2);

    protected abstract void handleReplyAllContacts(List<PresenceDocument> list);

    protected abstract void handleReplyGetContactPresence(PresenceDocument presenceDocument);

    protected abstract void handleReplyIsContactActive(String str, boolean z);
}
