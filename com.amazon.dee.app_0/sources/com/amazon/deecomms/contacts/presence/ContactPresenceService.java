package com.amazon.deecomms.contacts.presence;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.DropInState;
import com.amazon.deecomms.contacts.presence.model.PresenceDocument;
import com.amazon.deecomms.contacts.presence.model.PresenceDocumentList;
import com.amazon.deecomms.contacts.util.ContactDownloader;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.util.List;
/* loaded from: classes12.dex */
public class ContactPresenceService extends Service {
    public static final int MSG_GET_ACTIVE_CONTACTS_FOR_BANNER = 2;
    public static final int MSG_GET_ACTIVE_COUNT = 1;
    public static final int MSG_GET_ALL_ACTIVE_CONTACTS = 3;
    public static final int MSG_GET_ALL_CONTACTS_PRESENCE = 4;
    public static final int MSG_GET_CONTACT_PRESENCE = 6;
    public static final int MSG_IS_CONTACT_ACTIVE = 5;
    public static final int MSG_REPLY_ACTIVE_CONTACTS_FOR_BANNER = 102;
    public static final int MSG_REPLY_ACTIVE_COUNT = 101;
    public static final int MSG_REPLY_ALL_ACTIVE_CONTACTS = 103;
    public static final int MSG_REPLY_ALL_CONTACTS_PRESENCE = 104;
    public static final int MSG_REPLY_CONTACT_PRESENCE = 106;
    public static final int MSG_REPLY_IS_CONTACT_ACTIVE = 105;
    private final ACMSClient acmsClient = new ACMSClient(MetricKeys.OP_CONTACT_PRESENCE_GET);
    private final Messenger messenger = new Messenger(new IncomingHandler(null));
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactPresenceService.class);
    private static final PresenceCache cache = new PresenceCache();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class ContactActiveReply {
        boolean active;
        String hgCommsId;

        ContactActiveReply() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class ContactActivityBannerReply {
        String contactCommsId;
        String nameForBanner;
        int totalActive;
        int totalWithPresence;

        ContactActivityBannerReply() {
        }
    }

    @SuppressLint({"HandlerLeak"})
    /* loaded from: classes12.dex */
    private class IncomingHandler extends Handler {
        private IncomingHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            final Message message2 = new Message();
            message2.copyFrom(message);
            new Thread() { // from class: com.amazon.deecomms.contacts.presence.ContactPresenceService.IncomingHandler.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Message message3 = message2;
                    switch (message3.what) {
                        case 1:
                            ContactPresenceService.this.refreshCacheIfNecessary();
                            ContactPresenceService.this.getActiveCount(message2);
                            return;
                        case 2:
                            ContactPresenceService.this.refreshCacheIfNecessary();
                            ContactPresenceService.this.getActiveContactsForBanner(message2);
                            return;
                        case 3:
                            ContactPresenceService.this.refreshCacheIfNecessary();
                            ContactPresenceService.this.getAllActiveContacts(message2);
                            return;
                        case 4:
                            ContactPresenceService.this.getAllContacts(message3);
                            if (message2.arg1 == 1) {
                                ContactPresenceService.this.refreshDropInFlagsFromNetwork();
                                ContactPresenceService.this.doCacheRefresh();
                            } else {
                                ContactPresenceService.this.refreshCacheIfNecessary();
                            }
                            ContactPresenceService.this.getAllContacts(message2);
                            return;
                        case 5:
                            ContactPresenceService.this.refreshCacheIfNecessary();
                            ContactPresenceService.this.isContactActive(message2);
                            return;
                        case 6:
                            ContactPresenceService.this.refreshCacheIfNecessary();
                            ContactPresenceService.this.getContactPresence(message2);
                            return;
                        default:
                            CommsLogger commsLogger = ContactPresenceService.LOG;
                            StringBuilder outline1 = GeneratedOutlineSupport.outline1("ContactPresenceService - Unsupported Message Sent - ");
                            outline1.append(message2.what);
                            commsLogger.e(outline1.toString());
                            return;
                    }
                }
            }.start();
        }

        /* synthetic */ IncomingHandler(AnonymousClass1 anonymousClass1) {
        }
    }

    public static void clearCachedPresence() {
        cache.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCacheRefresh() {
        try {
            IHttpClient.Response mo3640execute = this.acmsClient.request(AppUrl.GET_CONTACT_PRESENCE).authenticatedAsCurrentCommsUser().get().mo3640execute();
            try {
                cache.cacheDocuments(getApplicationContext(), (PresenceDocumentList) mo3640execute.convert(PresenceDocumentList.class));
                LOG.i("Refreshing contact presence succeeded");
                mo3640execute.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (mo3640execute != null) {
                        try {
                            mo3640execute.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (ServiceException | IOException e) {
            cache.clear();
            doPresenceFreeCacheRefresh();
            LOG.e("Refreshing contact presence failed, flushing the expired data", e);
        }
    }

    private void doPresenceFreeCacheRefresh() {
        PresenceDocumentList presenceDocumentList = new PresenceDocumentList();
        presenceDocumentList.setDocuments(new PresenceDocument[0]);
        cache.cacheDocuments(getApplicationContext(), presenceDocumentList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getActiveContactsForBanner(Message message) {
        PresenceDocument presenceDocument;
        try {
            ContactActivityBannerReply contactActivityBannerReply = new ContactActivityBannerReply();
            contactActivityBannerReply.totalWithPresence = cache.getTotalCount();
            contactActivityBannerReply.totalActive = cache.getActiveCount();
            if (contactActivityBannerReply.totalActive == 1) {
                presenceDocument = cache.getActiveContacts().get(0);
            } else {
                presenceDocument = contactActivityBannerReply.totalWithPresence == 1 ? cache.getAllContacts().get(0) : null;
            }
            if (presenceDocument != null) {
                contactActivityBannerReply.nameForBanner = ContactUtils.getFullName(new ContactName(presenceDocument.getContactName(), presenceDocument.getContactLastName()));
                contactActivityBannerReply.contactCommsId = presenceDocument.getContactHomegroupId();
            }
            message.replyTo.send(Message.obtain(null, 102, contactActivityBannerReply));
        } catch (RemoteException unused) {
            LOG.w("Attempted to reply to Presence request but the sender was no longer available");
        } catch (NullPointerException unused2) {
            LOG.e("No replyTo provided by the Activity. Please populate this field. Use the abstract PresenceReplyHandler to help.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getActiveCount(Message message) {
        try {
            message.replyTo.send(Message.obtain(null, 101, cache.getActiveCount(), 0));
        } catch (RemoteException unused) {
            LOG.w("Attempted to reply to Presence request but the sender was no longer available");
        } catch (NullPointerException unused2) {
            LOG.e("No replyTo provided by the Activity. Please populate this field. Use the abstract PresenceReplyHandler to help.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAllActiveContacts(Message message) {
        try {
            message.replyTo.send(Message.obtain(null, 103, cache.getActiveContacts()));
        } catch (RemoteException unused) {
            LOG.w("Attempted to reply to Presence request but the sender was no longer available");
        } catch (NullPointerException unused2) {
            LOG.e("No replyTo provided by the Activity. Please populate this field. Use the abstract PresenceReplyHandler to help.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAllContacts(Message message) {
        try {
            message.replyTo.send(Message.obtain(null, 104, cache.getAllContacts()));
        } catch (RemoteException unused) {
            LOG.w("Attempted to reply to Presence request but the sender was no longer available");
        } catch (NullPointerException unused2) {
            LOG.e("No replyTo provided by the Activity. Please populate this field. Use the abstract PresenceReplyHandler to help.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getContactPresence(Message message) {
        try {
            if (!(message.obj instanceof String)) {
                return;
            }
            message.replyTo.send(Message.obtain(null, 106, cache.getContact((String) message.obj)));
        } catch (RemoteException unused) {
            LOG.w("Attempted to reply to Presence request but the sender was no longer available");
        } catch (NullPointerException unused2) {
            LOG.e("No replyTo provided by the Activity. Please populate this field. Use the abstract PresenceReplyHandler to help.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isContactActive(Message message) {
        try {
            if (!(message.obj instanceof String)) {
                return;
            }
            String str = (String) message.obj;
            ContactActiveReply contactActiveReply = new ContactActiveReply();
            contactActiveReply.hgCommsId = str;
            contactActiveReply.active = cache.isContactActive(str);
            message.replyTo.send(Message.obtain(null, 105, contactActiveReply));
        } catch (RemoteException unused) {
            LOG.w("Attempted to reply to Presence request but the sender was no longer available");
        } catch (NullPointerException unused2) {
            LOG.e("No replyTo provided by the Activity. Please populate this field. Use the abstract PresenceReplyHandler to help.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void refreshCacheIfNecessary() {
        if (cache.isExpired()) {
            doCacheRefresh();
        } else if (cache.is5SecondsExpired()) {
            doPresenceFreeCacheRefresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDropInFlagsFromNetwork() {
        ContactDownloader contactDownloader = new ContactDownloader();
        LOG.i("getContactsDebugging: download contacts in refreshDropInFlagsFromNetwork");
        if (contactDownloader.downloadContacts()) {
            for (Contact contact : contactDownloader.getContactsAndHomeGroups().getContacts()) {
                if (contact.getId() == null) {
                    List<String> commsIds = contact.getCommsIds();
                    String str = (commsIds == null || commsIds.size() == 0) ? null : commsIds.get(0);
                    CommsLogger commsLogger = LOG;
                    commsLogger.d("Can't updateDropInState because the contactId is null, commsId: " + str);
                    CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.MISSING_CONTACT_ID);
                    generateOperational.getMetadata().put("errorSource", "getContacts");
                    MetricsHelper.recordSingleOccurrence(generateOperational);
                }
                ContactsProviderUtils.updateDropInState(getApplicationContext(), contact.getId(), contact.canDropInOnMe() == 1 ? DropInState.ON : DropInState.OFF, contact.canIDropInOnHim() == 1 ? DropInState.ON : DropInState.OFF);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.messenger.getBinder();
    }
}
