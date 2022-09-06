package com.amazon.deecomms.contacts.util;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import java.io.IOException;
/* loaded from: classes12.dex */
public class ContactDeleter {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactDeleter.class);

    public boolean deleteContacts() {
        LOG.i("deleting contacts");
        String contactsURL = Utils.getContactsURL();
        LOG.i("getContactsDebugging: Assemble getContacts request URL in deleteContacts");
        if (contactsURL == null) {
            LOG.e("Unable to delete contacts, url is null");
            return false;
        }
        try {
            IHttpClient.Response mo3640execute = new ACMSClient(MetricKeys.OP_DELETE_CONTACT).request(contactsURL).authenticatedAsCurrentCommsUser().delete().mo3640execute();
            try {
                LOG.i("deleting contacts succeeded");
                if (mo3640execute != null) {
                    mo3640execute.close();
                }
                return true;
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
            LOG.e("Delete contacts failed", e);
            return false;
        }
    }
}
