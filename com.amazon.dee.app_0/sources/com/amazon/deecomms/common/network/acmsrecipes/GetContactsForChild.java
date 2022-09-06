package com.amazon.deecomms.common.network.acmsrecipes;

import android.text.TextUtils;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.Contact;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class GetContactsForChild {
    private static final String ACMS_QUERY_PARAM_DEDUPE_MODE = "dedupeMode";
    private static final String ACMS_QUERY_PARAM_DEDUPE_MODE_REMOVE_CLOUD_ONLY_CONTACT_DUPLICATES = "RemoveCloudOnlyContactDuplicates";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetContactsForChild.class);
    private final ACMSClient acmsClient = new ACMSClient(MetricKeys.OP_GET_CONTACTS);

    public List<Contact> getContactsForChild(String str) throws ServiceException {
        if (TextUtils.isEmpty(str)) {
            LOG.e("Unable to get contacts, commsId is empty or null");
            return new ArrayList();
        }
        LOG.i("getContactsDebugging: Assemble getContacts request URL for owner in getContactsForChild");
        try {
            IHttpClient.Response mo3640execute = this.acmsClient.request(Utils.getContactsURLForOwner(str)).addQueryParameter(Constants.ACMS_CONTACTS_QUERY_PREFERENCE_LEVEL, Constants.ACMS_CONTACT_PREFERENCE_LEVEL_HG).addQueryParameter(Constants.ACMS_CONTACTS_QUERY_INCLUDE_HG, Boolean.TRUE.toString()).addQueryParameter("dedupeMode", "RemoveCloudOnlyContactDuplicates").authenticatedAsCurrentCommsUser().get().mo3640execute();
            Contact[] contactArr = (Contact[]) mo3640execute.convert(Contact[].class);
            if (contactArr == null || contactArr.length == 0) {
                LOG.i("No contacts in the list.");
                if (contactArr == null) {
                    contactArr = new Contact[0];
                }
            }
            List<Contact> asList = Arrays.asList(contactArr);
            mo3640execute.close();
            return asList;
        } catch (IOException e) {
            LOG.e("IOException while get cotacts for child", e);
            return new ArrayList();
        }
    }
}
