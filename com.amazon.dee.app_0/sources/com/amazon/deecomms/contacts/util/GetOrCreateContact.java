package com.amazon.deecomms.contacts.util;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.platform.identity.CommunicableEntity;
import com.amazon.deecomms.platform.identity.Exceptions.MalformedCommsIDException;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.text.MessageFormat;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class GetOrCreateContact {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetOrCreateContact.class);
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    CommsInternal commsInternal;
    @Inject
    ContactsOperationsManager contactsManager;
    @Inject
    Context context;
    private final ACMSClient mACMSClient;
    private final String mSource;

    public GetOrCreateContact(@NonNull String str) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mACMSClient = new ACMSClient(MetricKeys.OP_GET_OR_CREATE_CONTACT);
        this.mSource = str;
    }

    private void performInsert(Contact contact, String str) {
        if (this.commsInternal.isLowInternalStorage()) {
            LOG.i("Cannot insert contact due to low internal storage");
            return;
        }
        ContactsProviderUtils.insertOrUpdateContact(this.context, contact, ContactsProviderUtils.ContentProviderOperationType.INSERT);
        if (contact.isHomeGroup()) {
            this.contactsManager.syncHomeGroupMembers(str);
        } else if (contact.getHomeGroupIdentity() == null || contact.getHomeGroupIdentity().getId() == null) {
        } else {
            getContactEntry(contact.getHomeGroupIdentity().getId(), true);
        }
    }

    public ContactEntry getContactEntry(String str) {
        return getContactEntry(str, true);
    }

    boolean isValidCommsId(@Nullable String str) {
        if (str == null) {
            LOG.e("Identity not present");
            return false;
        }
        try {
            if (!CommunicableEntity.ENTITY_ID_PROVIDER_PSTN.equals(CommunicableEntity.fromCommsID(str).getEntityIDProvider())) {
                return true;
            }
            LOG.w("Cannot get a PSTN contact");
            return false;
        } catch (MalformedCommsIDException | IllegalArgumentException e) {
            LOG.e("Malformed commsId", e);
            return false;
        }
    }

    @Nullable
    public ContactEntry getContactEntry(String str, boolean z) {
        ContactEntry fetchContactEntryForCommId;
        if (TextUtils.isEmpty(str)) {
            LOG.e("Comms ID is empty.");
            return null;
        } else if (z && (fetchContactEntryForCommId = ContactsProviderUtils.fetchContactEntryForCommId(this.context, str)) != null) {
            return fetchContactEntryForCommId;
        } else {
            if (ContactsProviderUtils.isCommsIdUngettable(this.context, str)) {
                LOG.i("CommsId is ungettable. Not doing the server call.");
                return null;
            }
            String commsId = this.commsIdentityManager.getCommsId("GetOrCreateContact.getContactEntry", false);
            if (!isValidCommsId(commsId)) {
                return null;
            }
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Getting or creating contact from server, commsId:");
            outline1.append(LOG.sensitive(str));
            commsLogger.i(outline1.toString());
            try {
                Contact contact = (Contact) this.mACMSClient.request(MessageFormat.format(AppUrl.GET_OR_CREATE_URL, commsId, str)).addMetricMetadata("source", this.mSource).addQueryParameter(Constants.ACMS_CONTACTS_QUERY_PREFERENCE_LEVEL, Constants.ACMS_CONTACT_PREFERENCE_LEVEL_HG).authenticatedAsCurrentCommsUser().get().mo3640execute().convert(Contact.class);
                if (contact != null) {
                    if (contact.getCommsIds() != null && !contact.getCommsIds().isEmpty()) {
                        performInsert(contact, str);
                        return new ContactEntry(contact);
                    }
                    ContactsProviderUtils.markCommsIdUngettable(this.context, str);
                }
            } catch (ServiceException e) {
                LOG.e("Get or create failed", e);
                if (e.getHttpResponseCode() != null && (e.getHttpResponseCode().intValue() == 400 || e.getHttpResponseCode().intValue() == 403)) {
                    LOG.i("Marking comms id ungettable");
                    ContactsProviderUtils.markCommsIdUngettable(this.context, str);
                }
            }
            LOG.e("Contact returned from server is null");
            return null;
        }
    }

    GetOrCreateContact(@NonNull String str, @NonNull ACMSClient aCMSClient) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mACMSClient = aCMSClient;
        this.mSource = str;
    }
}
