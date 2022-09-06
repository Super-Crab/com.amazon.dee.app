package com.amazon.clouddrive.cdasdk.aps.message;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.aps.APSCallUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class APSMessageCallsImpl implements APSMessageCalls {
    @NonNull
    private final APSCallUtil callUtil;
    @NonNull
    private final APSMessageCallsRetrofitBinding retrofitBinding;

    public APSMessageCallsImpl(@NonNull APSCallUtil aPSCallUtil, @NonNull Retrofit retrofit) {
        this.callUtil = aPSCallUtil;
        this.retrofitBinding = (APSMessageCallsRetrofitBinding) retrofit.create(APSMessageCallsRetrofitBinding.class);
    }

    public /* synthetic */ Single lambda$sendNotification$0$APSMessageCallsImpl(SendNotificationInput sendNotificationInput, Map map) throws Throwable {
        return this.retrofitBinding.sendNotification(sendNotificationInput.getResourceVersion().name(), map);
    }

    @Override // com.amazon.clouddrive.cdasdk.aps.message.APSMessageCalls
    @NonNull
    public Single<SendNotificationOutput> sendNotification(@NonNull final SendNotificationInput sendNotificationInput) {
        return this.callUtil.createCallWithQueryParameters("sendNotification", sendNotificationInput, new Function() { // from class: com.amazon.clouddrive.cdasdk.aps.message.-$$Lambda$APSMessageCallsImpl$JUzJZ9YUt6xY9C_N1quYNE1g7Ek
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return APSMessageCallsImpl.this.lambda$sendNotification$0$APSMessageCallsImpl(sendNotificationInput, (Map) obj);
            }
        });
    }
}
