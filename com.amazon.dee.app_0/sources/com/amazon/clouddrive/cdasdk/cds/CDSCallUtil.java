package com.amazon.clouddrive.cdasdk.cds;

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
public final class CDSCallUtil<T> extends CallUtil<T> {
    @NonNull
    private static final String TAG = "CDSCallUtil";

    public CDSCallUtil(@NonNull ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @NonNull
    protected SdkMetrics.Service getMetricsService() {
        return SdkMetrics.Service.CDS;
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    protected void initRequest(@NonNull T t) {
    }

    @Override // com.amazon.clouddrive.cdasdk.CallUtil
    @Nullable
    protected CloudDriveException parseHttpException(@NonNull HttpException httpException, @NonNull ResponseBody responseBody) {
        CDSError cDSError;
        try {
            cDSError = (CDSError) new ObjectMapper().readValue(responseBody.byteStream(), CDSError.class);
        } catch (Exception e) {
            getLogger().w(TAG, "Call response body wasn't able to be recognized/processed", e);
            cDSError = null;
        }
        if (cDSError == null) {
            return null;
        }
        return new CDSException(httpException, cDSError);
    }
}
