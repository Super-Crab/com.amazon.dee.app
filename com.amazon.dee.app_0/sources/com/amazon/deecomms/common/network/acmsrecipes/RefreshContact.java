package com.amazon.deecomms.common.network.acmsrecipes;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.contacts.model.Contact;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class RefreshContact {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, RefreshContact.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_GET_CONTACT_BY_ID);
    private final String mContactId;
    private final String mCurrentCommsId;

    public RefreshContact(String str, String str2) {
        this.mCurrentCommsId = str;
        this.mContactId = str2;
    }

    @Nullable
    public Contact getContact() {
        if (TextUtils.isEmpty(this.mContactId)) {
            LOG.e("ContactId is empty");
            return null;
        } else if (this.mCurrentCommsId == null) {
            LOG.e("CommsId is null");
            return null;
        } else {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Getting contact from server, contactId:");
            outline1.append(LOG.sensitive(this.mContactId));
            commsLogger.i(outline1.toString());
            try {
                Contact contact = (Contact) this.mClient.request(MessageFormat.format(AppUrl.GET_CONTACT_URL, this.mCurrentCommsId, this.mContactId)).authenticatedAsCurrentCommsUser().get().mo3640execute().convert(Contact.class);
                if (contact != null) {
                    return contact;
                }
                LOG.e("Contact returned from server is null");
                return null;
            } catch (ServiceException e) {
                LOG.e("Get contact failed", e);
                return null;
            }
        }
    }
}
