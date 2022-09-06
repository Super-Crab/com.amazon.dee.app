package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.aps.APSCalls;
import com.amazon.clouddrive.cdasdk.cdds.CDDSCalls;
import com.amazon.clouddrive.cdasdk.cdp.CDPCalls;
import com.amazon.clouddrive.cdasdk.cdrs.CDRSCalls;
import com.amazon.clouddrive.cdasdk.cds.CDSCalls;
import com.amazon.clouddrive.cdasdk.cdts.CDTSCalls;
import com.amazon.clouddrive.cdasdk.cdus.CDUSCalls;
import com.amazon.clouddrive.cdasdk.dps.DPSCalls;
import com.amazon.clouddrive.cdasdk.onelens.OneLensCalls;
import com.amazon.clouddrive.cdasdk.prompto.PromptoCalls;
import java.security.InvalidParameterException;
/* loaded from: classes11.dex */
public interface CDClient {
    @NonNull
    static CDClient createClient(@NonNull ClientConfig clientConfig) throws InvalidParameterException {
        return new CDClientImpl(clientConfig);
    }

    @NonNull
    APSCalls getAPSCalls();

    @NonNull
    CDDSCalls getCDDSCalls();

    @NonNull
    CDPCalls getCDPCalls();

    @NonNull
    CDRSCalls getCDRSCalls();

    @NonNull
    CDSCalls getCDSCalls();

    @NonNull
    CDUSCalls getCDUSCalls();

    @NonNull
    DPSCalls getDPSCalls();

    @NonNull
    OneLensCalls getOneLensCalls();

    @NonNull
    PromptoCalls getPromptoCalls();

    @NonNull
    CDTSCalls getThumbnailCalls();

    void updateEndpointConfiguration(@NonNull EndpointConfiguration endpointConfiguration);
}
