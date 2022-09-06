package com.amazon.deecomms.contacts.util;

import android.text.TextUtils;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.model.ContactUploadRequest;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.io.IOException;
import java.util.List;
/* loaded from: classes12.dex */
public class ContactUploader {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactUploader.class);
    private final String sourceDeviceId;

    public ContactUploader(String str) {
        this.sourceDeviceId = str;
    }

    public int uploadContacts(List<ContactUploadInfo> list) {
        String contactsURL = Utils.getContactsURL();
        LOG.i("getContactsDebugging: Assemble getContacts request URL in uploadContacts");
        if (contactsURL == null) {
            LOG.e("Unable to delete contacts, url is null");
            return -1;
        } else if (TextUtils.isEmpty(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPhoneNumber())) {
            LOG.e("Contacts upload aborted. No phone number associated with Comms User.");
            return -1;
        } else {
            ACMSClient aCMSClient = new ACMSClient(MetricKeys.OP_PUT_CONTACT);
            long currentTimeMillis = System.currentTimeMillis();
            ContactUploadRequest contactUploadRequest = new ContactUploadRequest();
            contactUploadRequest.setContacts(list);
            contactUploadRequest.setSourceDeviceId(this.sourceDeviceId);
            try {
                IHttpClient.Response mo3640execute = aCMSClient.request(contactsURL).authenticatedAsCurrentCommsUser().put(Constants.ACMS_CONTENT_TYPE_IMPORT_CONTACTS_V2, contactUploadRequest).mo3640execute();
                try {
                    long currentTimeMillis2 = System.currentTimeMillis();
                    CommsLogger commsLogger = LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Time taken to upload ");
                    sb.append(list.size());
                    sb.append(" contacts is: ");
                    sb.append(currentTimeMillis2 - currentTimeMillis);
                    commsLogger.d(sb.toString());
                    CommsLogger commsLogger2 = LOG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Successfully uploaded ");
                    sb2.append(list.size());
                    sb2.append(" contacts.");
                    commsLogger2.i(sb2.toString());
                    int code = mo3640execute.code();
                    mo3640execute.close();
                    return code;
                } finally {
                }
            } catch (ServiceException e) {
                LOG.e("Error occurred while uploading contacts", e);
                Integer httpResponseCode = e.getHttpResponseCode();
                if (httpResponseCode != null) {
                    return httpResponseCode.intValue();
                }
                return -1;
            } catch (IOException e2) {
                LOG.e("Error occurred while uploading contacts", e2);
                return -1;
            }
        }
    }
}
