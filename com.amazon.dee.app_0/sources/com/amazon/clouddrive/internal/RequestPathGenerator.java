package com.amazon.clouddrive.internal;

import android.net.Uri;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.configuration.Endpoints;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.model.GetAccountEndpointRequest;
import com.amazon.clouddrive.model.GetAccountEndpointResponse;
import com.amazon.clouddrive.model.PaginatedCloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URI;
import java.net.URISyntaxException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class RequestPathGenerator {
    private static final String GROUPS_ENDPOINT_PATH = "/drive/v2/photosGroups/";
    private static final String HTTPS_SCHEME = "https";
    private static final String RESOURCE_VERSION_NAME = "resourceVersion";
    private final ClientConfiguration mClientConfiguration;
    private final EndpointsCache mEndpointsCache;
    private final OperationFactory mOperationFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class RequestPath {
        private static final String CONTENT_ENDPOINT = "CONTENT";
        private static final String CONTENT_V1_ENDPOINT = "CONTENT_V1";
        private static final String CONTENT_V2_ENDPOINT = "CONTENT_V2";
        private static final String MASTER_ENDPOINT = "MASTER";
        private static final String META_DATA_ENDPOINT = "META_DATA";
        protected final String mEndpointKind;
        protected final String mOperation;
        protected final QueryPathBuilder mQueryBuilder = new QueryPathBuilder();

        /* JADX INFO: Access modifiers changed from: protected */
        public RequestPath(String str, String str2) {
            this.mEndpointKind = str;
            this.mOperation = str2;
        }

        private String endpointVersionReplace(String str, String str2, String... strArr) {
            for (String str3 : strArr) {
                if (str.contains(str3)) {
                    return str.replace(str3, str2);
                }
            }
            return !str.contains(str2) ? GeneratedOutlineSupport1.outline75(str, str2, "/") : str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void addParameter(String str, String str2) {
            this.mQueryBuilder.addParameter(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void addRequestParameters(PaginatedCloudDriveRequest paginatedCloudDriveRequest) {
            this.mQueryBuilder.addRequestParameters(paginatedCloudDriveRequest);
        }

        void addUTF8Parameter(String str, String str2) {
            this.mQueryBuilder.addUTF8Parameter(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public String getPath() throws CloudDriveException, InterruptedException {
            char c;
            String masterEndpoint;
            String str = this.mEndpointKind;
            switch (str.hashCode()) {
                case -2027938206:
                    if (str.equals(MASTER_ENDPOINT)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 749675585:
                    if (str.equals(CONTENT_V1_ENDPOINT)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 749675586:
                    if (str.equals(CONTENT_V2_ENDPOINT)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1669513305:
                    if (str.equals(CONTENT_ENDPOINT)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1746374052:
                    if (str.equals(META_DATA_ENDPOINT)) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                masterEndpoint = RequestPathGenerator.this.mClientConfiguration.getMasterEndpoint();
            } else if (c == 1) {
                masterEndpoint = RequestPathGenerator.this.getInitializedEndpoints().getContentEndpoint();
            } else if (c == 2) {
                masterEndpoint = endpointVersionReplace(RequestPathGenerator.this.getInitializedEndpoints().getContentEndpoint(), "v1", "v2", "cdproxy");
            } else if (c != 3) {
                masterEndpoint = RequestPathGenerator.this.getInitializedEndpoints().getMetaDataEndpoint();
            } else {
                masterEndpoint = endpointVersionReplace(RequestPathGenerator.this.getInitializedEndpoints().getContentEndpoint(), "v2", "v1", "cdproxy");
            }
            QueryPathBuilder queryPathBuilder = this.mQueryBuilder;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(masterEndpoint);
            outline107.append(this.mOperation);
            return queryPathBuilder.addQueryToPath(outline107.toString());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void addParameter(String str, Boolean bool) {
            this.mQueryBuilder.addParameter(str, bool);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPathGenerator(ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, OperationFactory operationFactory) {
        this.mClientConfiguration = clientConfiguration;
        this.mEndpointsCache = accountConfiguration.getEndpointsCache();
        this.mOperationFactory = operationFactory;
    }

    private boolean areEndpointsValid(Endpoints endpoints) {
        return (endpoints == null || endpoints.getMetaDataEndpoint() == null || endpoints.getContentEndpoint() == null || endpoints.getGroupEndpoint() == null) ? false : true;
    }

    String buildGroupsEndpoint(String str) {
        if (str == null) {
            return "";
        }
        try {
            return new Uri.Builder().scheme("https").authority(new URI(str).getHost()).path(GROUPS_ENDPOINT_PATH).toString();
        } catch (URISyntaxException unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPath createContentEndpointRequestPath(String str) {
        return new RequestPath("CONTENT", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPath createContentV1EndpointRequestPath(String str) {
        return new RequestPath("CONTENT_V1", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPath createContentV2EndpointRequestPath(String str) {
        return new RequestPath("CONTENT_V2", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPath createMasterEndpointRequestPath(String str) {
        return new RequestPath("MASTER", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPath createMetaDataEndpointRequestPath(String str) {
        return new RequestPath("META_DATA", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized Endpoints getInitializedEndpoints() throws CloudDriveException, InterruptedException {
        Endpoints endpoints;
        endpoints = this.mEndpointsCache.getEndpoints();
        if (!areEndpointsValid(endpoints) || this.mEndpointsCache.cacheHasExpired()) {
            GetAccountEndpointResponse call = this.mOperationFactory.newGetAccountEndpointOperation(new GetAccountEndpointRequest()).call();
            Endpoints endpoints2 = new Endpoints(call.getMetadataUrl(), call.getContentUrl(), buildGroupsEndpoint(call.getMetadataUrl()), call.getMarketplaceAtSignup());
            this.mEndpointsCache.setEndpoints(endpoints2);
            endpoints = endpoints2;
        }
        return endpoints;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPath createContentEndpointRequestPath(String str, String str2) {
        RequestPath requestPath = new RequestPath("CONTENT", str);
        requestPath.addUTF8Parameter(RESOURCE_VERSION_NAME, str2);
        return requestPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPath createMetaDataEndpointRequestPath(String str, String str2) {
        RequestPath requestPath = new RequestPath("META_DATA", str);
        requestPath.addUTF8Parameter(RESOURCE_VERSION_NAME, str2);
        return requestPath;
    }
}
