package com.amazon.deecomms.settings;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.CommsInternal;
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
public class IdentityPreferencesProvider {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, IdentityPreferencesProvider.class);
    private AuthenticationType authenticationType;

    /* loaded from: classes12.dex */
    public enum AuthenticationType {
        AS_LOGGED_ON_USER { // from class: com.amazon.deecomms.settings.IdentityPreferencesProvider.AuthenticationType.1
            @Override // com.amazon.deecomms.settings.IdentityPreferencesProvider.AuthenticationType
            public IHttpClient.Request authenticate(@NonNull IHttpClient.Request request) {
                IdentityPreferencesProvider.LOG.i(String.format("Authenticating request '%s' as logged on user", request.getRequestId()));
                return request.authenticated();
            }
        },
        AS_COMMS_USER { // from class: com.amazon.deecomms.settings.IdentityPreferencesProvider.AuthenticationType.2
            @Override // com.amazon.deecomms.settings.IdentityPreferencesProvider.AuthenticationType
            public IHttpClient.Request authenticate(@NonNull IHttpClient.Request request) {
                IdentityPreferencesProvider.LOG.i(String.format("Authenticating request '%s' as current Comms user '%s'", request.getRequestId(), IdentityPreferencesProvider.LOG.sensitive(CommsInternal.getInstance().getCommsId())));
                return request.authenticatedAsCurrentCommsUser();
            }
        };

        public abstract IHttpClient.Request authenticate(@NonNull IHttpClient.Request request);

        /* synthetic */ AuthenticationType(AnonymousClass1 anonymousClass1) {
        }
    }

    /* loaded from: classes12.dex */
    private static class GetIdentityPreferencesResponse {
        @JsonProperty("key")
        private String key;
        @JsonProperty("namespace")
        private String namespace;
        @JsonProperty("set")
        private String set;
        @JsonProperty("value")
        private String value;

        private GetIdentityPreferencesResponse() {
        }

        public String getKey() {
            return this.key;
        }

        public String getNamespace() {
            return this.namespace;
        }

        public String getSet() {
            return this.set;
        }

        public String getValue() {
            return this.value;
        }
    }

    public IdentityPreferencesProvider() {
        this(AuthenticationType.AS_LOGGED_ON_USER);
    }

    public String get(String str, String str2) throws ServiceException {
        IHttpClient.Request request = new ACMSClient(MetricKeys.OP_GET_IDENTITY_PREFERENCES).request(MessageFormat.format(AppUrl.IDENTITY_PREFERENCES, str, str2));
        CommsLogger commsLogger = LOG;
        commsLogger.d(String.format("Fetching value of preference %s, requestId=%s", commsLogger.sensitive(str2), request.getRequestId()));
        try {
            IHttpClient.Response mo3640execute = this.authenticationType.authenticate(request).addMetricMetadata("source", str2).get().mo3640execute();
            try {
                String value = ((GetIdentityPreferencesResponse) mo3640execute.convert(GetIdentityPreferencesResponse.class)).getValue();
                LOG.d(String.format("Fetched value of preference %s, requestId=%s, value=%s", LOG.sensitive(str2), request.getRequestId(), LOG.sensitive(value)));
                mo3640execute.close();
                return value;
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
            LOG.e(String.format("Error while fetching value of preference %s, requestId=%s", LOG.sensitive(str2), request.getRequestId()), e);
            throw e;
        } catch (IOException e2) {
            String format = String.format("Error while fetching value of preference %s, requestId=%s", LOG.sensitive(str2), request.getRequestId());
            LOG.e(format, e2);
            throw new ServiceException(format, e2);
        }
    }

    public void set(String str, String str2, Object obj) throws ServiceException {
        IHttpClient.Request request = new ACMSClient(MetricKeys.OP_UPDATE_IDENTITY_PREFERENCES).request(MessageFormat.format(AppUrl.IDENTITY_PREFERENCES, str, str2));
        CommsLogger commsLogger = LOG;
        Object[] objArr = new Object[3];
        objArr[0] = commsLogger.sensitive(str2);
        String str3 = "null";
        objArr[1] = LOG.sensitive(obj != null ? obj.toString() : str3);
        objArr[2] = request.getRequestId();
        commsLogger.d(String.format("Saving value of preference %s to %s, requestId=%s", objArr));
        try {
            IHttpClient.Response mo3640execute = this.authenticationType.authenticate(request).addMetricMetadata("source", str2).patchJson(obj).mo3640execute();
            try {
                CommsLogger commsLogger2 = LOG;
                Object[] objArr2 = new Object[3];
                objArr2[0] = LOG.sensitive(str2);
                CommsLogger commsLogger3 = LOG;
                if (obj != null) {
                    str3 = obj.toString();
                }
                objArr2[1] = commsLogger3.sensitive(str3);
                objArr2[2] = request.getRequestId();
                commsLogger2.d(String.format("Saved value of preference %s to %s, requestId=%s", objArr2));
                mo3640execute.close();
                mo3640execute.close();
            } finally {
            }
        } catch (ServiceException e) {
            LOG.e(String.format("Error while saving value of preference %s, requestId=%s", LOG.sensitive(str2), request.getRequestId()), e);
            throw e;
        } catch (IOException e2) {
            String format = String.format("Error while saving value of preference %s, requestId=%s", LOG.sensitive(str2), request.getRequestId());
            LOG.e(format, e2);
            throw new ServiceException(format, e2);
        }
    }

    public IdentityPreferencesProvider(@NonNull AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }
}
