package com.amazon.deecomms.contacts.util;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.text.MessageFormat;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class ContactStatusManager {
    private static final String CONTACT_BLOCK_STATUS = "contactBlockStatus";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactStatusManager.class);
    private static final String MEDIA_TYPE = "application/json";
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    Context context;
    private final ACMSClient mACMSClient;

    public ContactStatusManager() {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mACMSClient = new ACMSClient(MetricKeys.OP_SET_CONTACT_BLOCK_STATUS);
    }

    public boolean setBlockStatus(@NonNull String str, boolean z) {
        return setBlockStatus(str, z, true);
    }

    boolean setBlockStatus(@NonNull String str, boolean z, boolean z2) {
        boolean z3 = false;
        if (!this.commsIdentityManager.isCommsIdPopulated("ContactStatusManager.setBlockStatus", false)) {
            LOG.e("Identity not present");
            return false;
        }
        if (z2) {
            ContactEntry fetchContactEntryForCommId = ContactsProviderUtils.fetchContactEntryForCommId(this.context, str);
            if (fetchContactEntryForCommId != null) {
                ContactsProviderUtils.updateContactBlockStatus(this.context, fetchContactEntryForCommId.getId(), z);
            } else {
                LOG.w("Contact is not present locally, cannot update local status.");
            }
        }
        LOG.i(String.format("Setting block status (%b), commsId: %s", Boolean.valueOf(z), LOG.sensitive(str)));
        String format = MessageFormat.format(AppUrl.GET_OR_CREATE_URL, this.commsIdentityManager.getCommsId("ContactStatusManager.setBlockStatus", false), str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(CONTACT_BLOCK_STATUS, z);
            IHttpClient.Response mo3640execute = this.mACMSClient.request(format).authenticatedAsCurrentCommsUser().patch("application/json", jSONObject.toString().getBytes()).mo3640execute();
            if (mo3640execute.code() >= 200 && mo3640execute.code() < 300) {
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("setContactBlockStatus returned success ");
                sb.append(mo3640execute.code());
                commsLogger.d(sb.toString());
                z3 = true;
            } else {
                CommsLogger commsLogger2 = LOG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Received setContactBlockStatus status code: ");
                sb2.append(mo3640execute.code());
                commsLogger2.w(sb2.toString());
            }
        } catch (ServiceException e) {
            LOG.e("Patch failed", e);
        } catch (JSONException e2) {
            LOG.e("Unable to create json body", e2);
        }
        return z3;
    }
}
