package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.CallUtil;
import com.amazon.clouddrive.cdasdk.SdkMetrics;
import com.amazon.clouddrive.cdasdk.util.Logger;
import com.amazon.clouddrive.cdasdk.util.SystemUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
/* loaded from: classes11.dex */
public abstract class CallUtil<T> {
    @NonNull
    private static final String TAG = "CallUtil";
    @NonNull
    private final ClientConfig clientConfig;
    @NonNull
    private final SystemUtil systemUtil = CDClientImpl.getAppComponent().getSystemUtil();
    @NonNull
    private final Logger logger = CDClientImpl.getAppComponent().getLogger();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.clouddrive.cdasdk.CallUtil$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public class AnonymousClass1 implements SingleTransformer<O, O> {
        private long callStartTime;
        final /* synthetic */ String val$callName;

        AnonymousClass1(String str) {
            this.val$callName = str;
        }

        @Override // io.reactivex.rxjava3.core.SingleTransformer
        public SingleSource<O> apply(Single<O> single) {
            Single doOnSubscribe = single.doOnSubscribe(new Consumer() { // from class: com.amazon.clouddrive.cdasdk.-$$Lambda$CallUtil$1$idX8jYXxafVRj58JyHsffY4lXS0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    CallUtil.AnonymousClass1.this.lambda$apply$0$CallUtil$1((Disposable) obj);
                }
            });
            final String str = this.val$callName;
            Single<T> doAfterSuccess = doOnSubscribe.doAfterSuccess(new Consumer() { // from class: com.amazon.clouddrive.cdasdk.-$$Lambda$CallUtil$1$fwUjQUXaVn4DL9PFI1DkXHXomO8
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    CallUtil.AnonymousClass1.this.lambda$apply$1$CallUtil$1(str, obj);
                }
            });
            final String str2 = this.val$callName;
            return doAfterSuccess.onErrorResumeNext(new Function() { // from class: com.amazon.clouddrive.cdasdk.-$$Lambda$CallUtil$1$z0FGwZ3X6dJfHyTQu7F0jJuZgDs
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return CallUtil.AnonymousClass1.this.lambda$apply$2$CallUtil$1(str2, (Throwable) obj);
                }
            });
        }

        public /* synthetic */ void lambda$apply$0$CallUtil$1(Disposable disposable) throws Throwable {
            this.callStartTime = CallUtil.this.systemUtil.elapsedRealtime();
        }

        public /* synthetic */ void lambda$apply$1$CallUtil$1(String str, Object obj) throws Throwable {
            CallUtil.this.clientConfig.getSdkMetrics().recordCallSuccess(CallUtil.this.getMetricsService(), str, CallUtil.this.systemUtil.elapsedRealtime() - this.callStartTime);
        }

        public /* synthetic */ SingleSource lambda$apply$2$CallUtil$1(String str, Throwable th) throws Throwable {
            if (th instanceof HttpException) {
                th = CallUtil.this.parseHttpException((HttpException) th);
            }
            if (th instanceof Exception) {
                CallUtil.this.clientConfig.getSdkMetrics().recordCallError(CallUtil.this.getMetricsService(), str, (Exception) th);
            }
            return Single.error(th);
        }
    }

    public CallUtil(@NonNull ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @NonNull
    private Single<Map<String, String>> convertObjectToQueryMap(@NonNull final Object obj) {
        return Single.fromCallable(new Callable() { // from class: com.amazon.clouddrive.cdasdk.-$$Lambda$CallUtil$iZtoyVGY1Rp1KFrK-34AVz3PvJE
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CallUtil.this.lambda$convertObjectToQueryMap$0$CallUtil(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public CloudDriveException parseHttpException(@NonNull HttpException httpException) {
        ResponseBody errorBody = httpException.response().errorBody();
        if (errorBody == null) {
            return new CloudDriveException(httpException);
        }
        CloudDriveException parseHttpException = parseHttpException(httpException, errorBody);
        return parseHttpException == null ? new CloudDriveException(httpException) : parseHttpException;
    }

    @NonNull
    private <O> Single<O> wrapCallForMetrics(@NonNull String str, @NonNull Single<O> single) {
        return (Single<O>) single.compose(new AnonymousClass1(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public <I extends T, O> Single<O> createCall(@NonNull String str, @NonNull I i, @NonNull Function<I, Single<O>> function) {
        try {
            return createCall(str, (String) i, (Single) function.mo10358apply(i));
        } catch (Throwable th) {
            return Single.error(th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public <O> Single<O> createCallWithQueryParameters(@NonNull String str, @NonNull T t, @NonNull Function<Map<String, String>, Single<O>> function) {
        return createCall(str, (String) t, (Single) convertObjectToQueryMap(t).flatMap(function));
    }

    public ClientConfig getClientConfig() {
        return this.clientConfig;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public Logger getLogger() {
        return this.logger;
    }

    @NonNull
    protected abstract SdkMetrics.Service getMetricsService();

    protected abstract void initRequest(@NonNull T t);

    public /* synthetic */ Map lambda$convertObjectToQueryMap$0$CallUtil(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return (Map) objectMapper.readValue(objectMapper.writeValueAsString(obj), new TypeReference<HashMap<String, String>>() { // from class: com.amazon.clouddrive.cdasdk.CallUtil.2
        });
    }

    @Nullable
    protected CloudDriveException parseHttpException(@NonNull HttpException httpException, @NonNull ResponseBody responseBody) {
        return null;
    }

    @NonNull
    private <O> Single<O> createCall(@NonNull String str, @NonNull T t, @NonNull Single<O> single) {
        initRequest(t);
        return wrapCallForMetrics(str, single);
    }
}
