package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.ClientConfiguration;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.internal.RequestPathGenerator;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class ExtendedRequestPathGenerator extends RequestPathGenerator {

    /* loaded from: classes11.dex */
    class ExtendedRequestPath extends RequestPathGenerator.RequestPath {
        private static final String FACES_ENDPOINT = "FACES";
        private static final String GROUPS_ENDPOINT = "GROUPS";
        private static final String REUPDATE_NODES_ENDPOINT = "REUPDATE_NODES";

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.amazon.clouddrive.internal.RequestPathGenerator.RequestPath
        public String getPath() throws CloudDriveException, InterruptedException {
            String str = this.mEndpointKind;
            if (((str.hashCode() == 2110836180 && str.equals(GROUPS_ENDPOINT)) ? (char) 0 : (char) 65535) != 0) {
                return super.getPath();
            }
            String groupEndpoint = ExtendedRequestPathGenerator.this.getInitializedEndpoints().getGroupEndpoint();
            QueryPathBuilder queryPathBuilder = this.mQueryBuilder;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(groupEndpoint);
            outline107.append(this.mOperation);
            return queryPathBuilder.addQueryToPath(outline107.toString());
        }

        private ExtendedRequestPath(String str, String str2) {
            super(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtendedRequestPathGenerator(ClientConfiguration clientConfiguration, AccountConfiguration accountConfiguration, OperationFactory operationFactory) {
        super(clientConfiguration, accountConfiguration, operationFactory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPathGenerator.RequestPath createFacesEndpointRequestPath(String str) {
        return new ExtendedRequestPath("FACES", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPathGenerator.RequestPath createGroupsEndpointRequestPath(String str) {
        return new ExtendedRequestPath("GROUPS", str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestPathGenerator.RequestPath createReUpdateEndpointRequestPath(String str) {
        return new ExtendedRequestPath("REUPDATE_NODES", str);
    }
}
