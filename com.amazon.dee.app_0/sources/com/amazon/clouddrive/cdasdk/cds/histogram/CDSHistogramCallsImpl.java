package com.amazon.clouddrive.cdasdk.cds.histogram;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.CDSCallUtil;
import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDSHistogramCallsImpl implements CDSHistogramCalls {
    @NonNull
    private final CDSCallUtil<CloudDriveRequest> callUtil;
    @NonNull
    private final CDSHistogramRetrofitBinding retrofitBinding;

    public CDSHistogramCallsImpl(@NonNull CDSCallUtil<CloudDriveRequest> cDSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = cDSCallUtil;
        this.retrofitBinding = (CDSHistogramRetrofitBinding) retrofit.create(CDSHistogramRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.histogram.CDSHistogramCalls
    @NonNull
    public Single<GetHistogramResponse> getHistogram(@NonNull GetHistogramRequest getHistogramRequest) {
        CDSCallUtil<CloudDriveRequest> cDSCallUtil = this.callUtil;
        final CDSHistogramRetrofitBinding cDSHistogramRetrofitBinding = this.retrofitBinding;
        cDSHistogramRetrofitBinding.getClass();
        return cDSCallUtil.createCallWithQueryParameters("getHistogram", getHistogramRequest, new Function() { // from class: com.amazon.clouddrive.cdasdk.cds.histogram.-$$Lambda$IZeeadbwhCorkPj_eCDNQcXvkgM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDSHistogramRetrofitBinding.this.getHistogram((Map) obj);
            }
        });
    }
}
