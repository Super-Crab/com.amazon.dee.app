package com.amazon.deecomms.common.network.acmsrecipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.contacts.model.ContactForSync;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.model.enums.SyncOperationType;
import com.amazon.deecomms.contacts.util.CloudContactsSynchronizer;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
/* loaded from: classes12.dex */
public class UpdateContactName {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, UpdateContactName.class);
    private final ACMSClient mACMSClient;
    private final CommsIdentityManager mCommsIdentityManager;
    private final CommsInternal mCommsInternal;

    public UpdateContactName(@NonNull CommsIdentityManager commsIdentityManager, @NonNull CommsInternal commsInternal) {
        this.mACMSClient = new ACMSClient(MetricKeys.OP_UPDATE_IDENTITY);
        this.mCommsIdentityManager = commsIdentityManager;
        this.mCommsInternal = commsInternal;
    }

    private ContactForSync getContactForNameSync(@NonNull ContactName contactName, @NonNull String str, @Nullable String str2, @NonNull List<ContactPhoneNumber> list) {
        ContactUploadInfo contactUploadInfo = new ContactUploadInfo();
        contactUploadInfo.setContactName(contactName.getFirstName(), contactName.getLastName());
        contactUploadInfo.setCompany(str2);
        contactUploadInfo.setDeviceContactId(null);
        contactUploadInfo.setServerContactId(str);
        for (ContactPhoneNumber contactPhoneNumber : list) {
            contactUploadInfo.addPhoneNumber(contactPhoneNumber.getPhoneNumber(), contactPhoneNumber.getRawType());
        }
        return new ContactForSync(contactUploadInfo, SyncOperationType.UPDATE);
    }

    public boolean updateCloudContact(@NonNull ContactName contactName, @NonNull String str, @Nullable String str2, @NonNull List<ContactPhoneNumber> list, @NonNull String str3) {
        try {
            IHttpClient.Response uploadContactsDiff = new CloudContactsSynchronizer().uploadContactsDiff(getContactForNameSync(contactName, str, str2, list), str3);
            if (uploadContactsDiff == null) {
                LOG.e("updateCloudContact response is null");
                if (uploadContactsDiff != null) {
                    uploadContactsDiff.close();
                }
                return false;
            } else if (uploadContactsDiff.isSuccessful()) {
                LOG.i("contactName updated in server");
                uploadContactsDiff.close();
                return true;
            } else {
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("CloudContactsSync response failed with ");
                sb.append(uploadContactsDiff.code());
                commsLogger.e(sb.toString());
                uploadContactsDiff.close();
                return false;
            }
        } catch (IOException e) {
            LOG.e("ContactsSync Error while updating contact name", e);
            return false;
        }
    }

    public boolean updateMyProfileContact(@NonNull String str, @NonNull ContactName contactName) {
        try {
            IHttpClient.Response mo3640execute = this.mACMSClient.request(MessageFormat.format("/users/{0}/identities", str)).authenticatedAsCurrentCommsUser().patchJson(contactName).mo3640execute();
            try {
                if (mo3640execute.isSuccessful()) {
                    LOG.i("contactName updated in server for myProfileContact");
                    this.mCommsIdentityManager.updateUserNames(contactName);
                    this.mCommsIdentityManager.onCurrentUserUpdated();
                    mo3640execute.close();
                    return true;
                }
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("ACMS updateAndRegisterIdentity response failed with ");
                sb.append(mo3640execute.code());
                commsLogger.e(sb.toString());
                mo3640execute.close();
                return false;
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
        } catch (ServiceException e) {
            e = e;
            LOG.e("Service Error occurred while updating contact name", e);
            return false;
        } catch (InvalidCommsIdentityException e2) {
            LOG.e("Attempt to update identity resulted in a failure", e2);
            return false;
        } catch (IOException e3) {
            e = e3;
            LOG.e("Service Error occurred while updating contact name", e);
            return false;
        }
    }

    public UpdateContactName(@NonNull CommsIdentityManager commsIdentityManager, @NonNull CommsInternal commsInternal, @NonNull ACMSClient aCMSClient) {
        this.mACMSClient = aCMSClient;
        this.mCommsIdentityManager = commsIdentityManager;
        this.mCommsInternal = commsInternal;
    }
}
