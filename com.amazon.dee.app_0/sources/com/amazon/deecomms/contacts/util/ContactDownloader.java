package com.amazon.deecomms.contacts.util;

import android.text.TextUtils;
import android.util.ArrayMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.contacts.model.Contacts;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
@Deprecated
/* loaded from: classes12.dex */
public class ContactDownloader {
    private static final String ACMS_QUERY_PARAM_BULK_IMPORT = "bulkImportOnly";
    private static final String ACMS_QUERY_PARAM_BULK_IMPORT_VALUE = "true";
    public static final String ACMS_QUERY_PARAM_DEDUPE_MODE = "dedupeMode";
    public static final String ACMS_QUERY_PARAM_DEDUPE_MODE_REMOVE_CLOUD_ONLY_CONTACT_DUPLICATES = "RemoveCloudOnlyContactDuplicates";
    public static final String ACMS_QUERY_PARAM_HOMEGROUP = "homeGroupId";
    public static final String ACMS_QUERY_PARAM_INCLUDE_NON_ALEXA_CONTACTS = "includeNonAlexaContacts";
    public static final String ACMS_QUERY_PARAM_INCLUDE_NON_ALEXA_CONTACTS_VALUE = "true";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactDownloader.class);
    @Inject
    CapabilitiesManager capabilitiesManager;
    private Contacts mBulkImportedContacts = null;
    private Contacts mAlexaContactsAndHGContacts = null;
    private Contacts mContactsAndHGContacts = null;
    private final ACMSClient mContactsServiceClient = new ACMSClient(MetricKeys.OP_GET_CONTACTS);

    public ContactDownloader() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    @NonNull
    private Set<String> getCommsIdsAsSet(@Nullable List<String> list) {
        HashSet newHashSet = Sets.newHashSet();
        if (list != null) {
            newHashSet.addAll(list);
        }
        return newHashSet;
    }

    public boolean downloadContacts() {
        String homeGroupId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getHomeGroupId("ContactDownloader.downloadContacts", false);
        if (TextUtils.isEmpty(homeGroupId)) {
            LOG.e("Unable to download contacts. homegroup is null");
            return false;
        }
        String bool = (this.capabilitiesManager.isLightyearEnabled() ? Boolean.TRUE : Boolean.FALSE).toString();
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(Constants.ACMS_CONTACTS_QUERY_PREFERENCE_LEVEL, Constants.ACMS_CONTACT_PREFERENCE_LEVEL_HG);
        arrayMap.put("homeGroupId", homeGroupId);
        arrayMap.put(Constants.ACMS_CONTACTS_QUERY_INCLUDE_HG, Boolean.TRUE.toString());
        arrayMap.put(ACMS_QUERY_PARAM_DEDUPE_MODE, ACMS_QUERY_PARAM_DEDUPE_MODE_REMOVE_CLOUD_ONLY_CONTACT_DUPLICATES);
        arrayMap.put(ACMS_QUERY_PARAM_INCLUDE_NON_ALEXA_CONTACTS, Boolean.TRUE.toString());
        arrayMap.put(Constants.ACMS_CONTACTS_QUERY_INCLUDE_MERGED_CONTACTS, bool);
        LOG.i("getContactsDebugging: download contacts in ContactsDownloader");
        return downloadContacts(arrayMap);
    }

    @Nullable
    public Contacts getAlexaContactsAndHomeGroups() {
        return this.mAlexaContactsAndHGContacts;
    }

    @Nullable
    public Contacts getBulkImportedContacts() {
        return this.mBulkImportedContacts;
    }

    @Nullable
    public Contacts getContactsAndHomeGroups() {
        return this.mContactsAndHGContacts;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:23|24|(13:29|30|31|32|33|34|35|36|37|(8:40|(2:46|47)|53|(1:55)|56|(3:65|66|(2:67|(2:69|(3:71|72|73))(0)))(5:58|59|(1:61)|62|63)|64|38)|75|76|77)|86|87|88|(1:90)|34|35|36|37|(1:38)|75|76|77) */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0197, code lost:
        r14 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x019b, code lost:
        com.amazon.deecomms.contacts.util.ContactDownloader.LOG.e("Exception in ContactDownloader", r14);
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e0 A[Catch: Exception -> 0x0197, TryCatch #6 {Exception -> 0x0197, blocks: (B:38:0x00b3, B:39:0x00b6, B:40:0x00da, B:42:0x00e0, B:44:0x00f8, B:46:0x0106, B:48:0x010c, B:51:0x0115, B:53:0x0119, B:54:0x011c, B:56:0x0122, B:57:0x0125, B:59:0x012b, B:60:0x012f, B:62:0x0135, B:64:0x0141, B:65:0x014b, B:67:0x0151, B:68:0x0154, B:69:0x0158, B:84:0x018f, B:86:0x0193), top: B:96:0x00b3, inners: #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean downloadContacts(java.util.Map<java.lang.String, java.lang.String> r14) {
        /*
            Method dump skipped, instructions count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.contacts.util.ContactDownloader.downloadContacts(java.util.Map):boolean");
    }
}
