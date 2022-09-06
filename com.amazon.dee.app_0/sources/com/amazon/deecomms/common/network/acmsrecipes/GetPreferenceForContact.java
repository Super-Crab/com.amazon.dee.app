package com.amazon.deecomms.common.network.acmsrecipes;

import android.text.TextUtils;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class GetPreferenceForContact {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, GetPreferenceForContact.class);
    private final ACMSClient mClient = new ACMSClient(MetricKeys.OP_GET_PREF_FOR_CONTACTS);
    private final String mCommsId;
    private final String mContactHGId;
    private final String mPreferenceName;

    /* loaded from: classes12.dex */
    private static class GetPreferenceForContactResponse {
        @JsonProperty("key")
        private String key;
        @JsonProperty("namespace")
        private String namespace;
        @JsonProperty("value")
        private String value;

        private GetPreferenceForContactResponse() {
        }

        public String getKey() {
            return this.key;
        }

        public String getNamespace() {
            return this.namespace;
        }

        public String getValue() {
            return this.value;
        }
    }

    public GetPreferenceForContact(String str, String str2, String str3) {
        this.mPreferenceName = str;
        this.mCommsId = str2;
        this.mContactHGId = str3;
    }

    public Boolean getPreferenceForContact() throws ServiceException {
        IHttpClient.Request request = this.mClient.request(MessageFormat.format(AppUrl.GET_PREFERENCE_FOR_CONTACT, this.mCommsId, this.mContactHGId, this.mPreferenceName));
        LOG.d(String.format("Fetching value of contact preference %s, requestId %s", this.mPreferenceName, request.getRequestId()));
        try {
            IHttpClient.Response mo3640execute = request.authenticatedAsCurrentCommsUser().get().mo3640execute();
            GetPreferenceForContactResponse getPreferenceForContactResponse = (GetPreferenceForContactResponse) mo3640execute.convert(GetPreferenceForContactResponse.class);
            LOG.d(String.format("Fetched value of contact preference %s, requestId %s", this.mPreferenceName, request.getRequestId()));
            String value = getPreferenceForContactResponse.getValue();
            if (TextUtils.isEmpty(value)) {
                mo3640execute.close();
                return null;
            }
            char c = 65535;
            int hashCode = value.hashCode();
            if (hashCode != -1609594047) {
                if (hashCode == 270940796 && value.equals(FeatureState.DISABLED)) {
                    c = 1;
                }
            } else if (value.equals("enabled")) {
                c = 0;
            }
            if (c == 0) {
                mo3640execute.close();
                return true;
            } else if (c != 1) {
                mo3640execute.close();
                return null;
            } else {
                mo3640execute.close();
                return false;
            }
        } catch (ServiceException e) {
            LOG.e(String.format("Error while fetching value of contact preference %s, requestId %s", this.mPreferenceName, request.getRequestId()), e);
            throw e;
        } catch (IOException e2) {
            String format = String.format("Error while fetching value of contact preference %s, requestId %s", this.mPreferenceName, request.getRequestId());
            LOG.e(format, e2);
            throw new ServiceException(format, e2);
        }
    }
}
