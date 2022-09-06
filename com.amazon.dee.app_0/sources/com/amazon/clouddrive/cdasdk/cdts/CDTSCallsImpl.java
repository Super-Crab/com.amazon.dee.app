package com.amazon.clouddrive.cdasdk.cdts;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.io.InputStream;
import java.util.Locale;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDTSCallsImpl implements CDTSCalls {
    @NonNull
    private final CDTSCallUtil callUtil;
    @NonNull
    private final CDTSRetrofitBinding retrofitBinding;

    public CDTSCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        this.callUtil = new CDTSCallUtil(clientConfig);
        this.retrofitBinding = (CDTSRetrofitBinding) retrofit.create(CDTSRetrofitBinding.class);
    }

    @Override // com.amazon.clouddrive.cdasdk.cdts.CDTSCalls
    @NonNull
    public Single<InputStream> getImageThumbnail(@NonNull ThumbnailRequest thumbnailRequest) {
        final String str;
        final String str2;
        ViewBox viewBox = thumbnailRequest.getViewBox();
        final String format = viewBox != null ? String.format(Locale.getDefault(), "%d,%d", Integer.valueOf(viewBox.getWidth()), Integer.valueOf(viewBox.getHeight())) : null;
        final String value = thumbnailRequest.getFitType() != null ? thumbnailRequest.getFitType().getValue() : null;
        CropBox cropBox = thumbnailRequest.getCropBox();
        if (cropBox != null) {
            String format2 = String.format(Locale.getDefault(), "%d,%d", Long.valueOf(cropBox.getOffsetX()), Long.valueOf(cropBox.getOffsetY()));
            str2 = String.format(Locale.getDefault(), "%d,%d", Long.valueOf(cropBox.getCropWidth()), Long.valueOf(cropBox.getCropHeight()));
            str = format2;
        } else {
            str = null;
            str2 = null;
        }
        return this.callUtil.createCall("getThumbnail", (String) thumbnailRequest, (Function<String, Single<O>>) new Function() { // from class: com.amazon.clouddrive.cdasdk.cdts.-$$Lambda$CDTSCallsImpl$y6qCXjHKBm4PrGmIywf0pqulwKo
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CDTSCallsImpl.this.lambda$getImageThumbnail$0$CDTSCallsImpl(format, value, str, str2, (ThumbnailRequest) obj);
            }
        });
    }

    public /* synthetic */ Single lambda$getImageThumbnail$0$CDTSCallsImpl(String str, String str2, String str3, String str4, ThumbnailRequest thumbnailRequest) throws Throwable {
        return this.retrofitBinding.getImageThumbnail(thumbnailRequest.getNodeId(), thumbnailRequest.getOwnerId(), thumbnailRequest.getGroupShareToken(), str, str2, str3, str4).map($$Lambda$UJJIKEFczutnvzizVh3kRkgDwg.INSTANCE);
    }
}
