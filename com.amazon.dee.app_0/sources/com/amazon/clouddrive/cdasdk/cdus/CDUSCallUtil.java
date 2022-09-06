package com.amazon.clouddrive.cdasdk.cdus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.CallUtil;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
/* loaded from: classes11.dex */
public class CDUSCallUtil extends CallUtil<ServiceRequest> {
    @NonNull
    private static final String TAG = "CDUSCallUtil";

    public CDUSCallUtil(@NonNull ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @NonNull
    protected SdkMetrics.Service getMetricsService() {
        return SdkMetrics.Service.CDUS;
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @Nullable
    protected CloudDriveException parseHttpException(@NonNull HttpException httpException, @NonNull ResponseBody responseBody) {
        CDUSError cDUSError;
        try {
            cDUSError = (CDUSError) new ObjectMapper().readValue(responseBody.byteStream(), CDUSError.class);
        } catch (Exception e) {
            getLogger().w(TAG, "Call response body wasn't able to be recognized/processed", e);
            cDUSError = null;
        }
        if (cDUSError == null) {
            return null;
        }
        return new CDUSException(httpException, cDUSError);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    public void initRequest(@NonNull ServiceRequest serviceRequest) {
        serviceRequest.setClientName(getClientConfig().getClientName());
    }
}
