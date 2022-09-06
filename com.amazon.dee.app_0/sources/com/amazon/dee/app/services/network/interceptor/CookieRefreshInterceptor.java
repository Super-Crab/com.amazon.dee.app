package com.amazon.dee.app.services.network.interceptor;

import androidx.annotation.NonNull;
import com.amazon.alexa.identity.AccountException;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import dagger.Lazy;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
/* loaded from: classes12.dex */
public class CookieRefreshInterceptor implements Interceptor {
    private static final String TAG = Log.tag(CookieRefreshInterceptor.class);
    private final Lazy<AccountService> accountService;
    private final Lazy<IdentityService> identityService;
    private final Lazy<Mobilytics> mobilyticsLazy;
    private final Lazy<RoutingService> routingService;

    public CookieRefreshInterceptor(Lazy<IdentityService> lazy, Lazy<AccountService> lazy2, Lazy<RoutingService> lazy3, Lazy<Mobilytics> lazy4) {
        this.identityService = lazy;
        this.accountService = lazy2;
        this.routingService = lazy3;
        this.mobilyticsLazy = lazy4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$2(Void r0) {
    }

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        if (this.identityService.mo358get().isAuthenticated(TAG)) {
            final Request request = chain.request();
            Response proceed = chain.proceed(request);
            if (proceed.code() == 401) {
                this.mobilyticsLazy.mo358get().recordOperationalEvent(this.mobilyticsLazy.mo358get().createOperationalEvent(AlexaMetricsConstants.MetricEvents.UNAUTHORIZED_REQUEST, OperationalEventType.DIAGNOSTIC, "Application", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
                Response response = (Response) this.identityService.mo358get().refresh(TAG).switchMap(new Func1() { // from class: com.amazon.dee.app.services.network.interceptor.-$$Lambda$CookieRefreshInterceptor$tBW2wfT_cwHkw0XWwxTajqH3oCA
                    @Override // rx.functions.Func1
                    /* renamed from: call */
                    public final Object mo13102call(Object obj) {
                        return CookieRefreshInterceptor.this.lambda$intercept$0$CookieRefreshInterceptor(chain, request, (UserIdentity) obj);
                    }
                }).onErrorReturn(new Func1() { // from class: com.amazon.dee.app.services.network.interceptor.-$$Lambda$CookieRefreshInterceptor$R7aI0ZVBs_KM0u2phbtoiNSedNE
                    @Override // rx.functions.Func1
                    /* renamed from: call */
                    public final Object mo13102call(Object obj) {
                        return CookieRefreshInterceptor.this.lambda$intercept$4$CookieRefreshInterceptor((Throwable) obj);
                    }
                }).toBlocking().single();
                if (response != null) {
                    if (proceed.body() != null) {
                        proceed.body().close();
                    }
                    return response;
                }
            }
            return proceed;
        }
        throw new IOException("User not registered");
    }

    public /* synthetic */ Observable lambda$intercept$0$CookieRefreshInterceptor(Interceptor.Chain chain, Request request, UserIdentity userIdentity) {
        try {
            this.mobilyticsLazy.mo358get().recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.INTERCEPT_REFRESH_TOKENS, true, "Reauthentication", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
            return Observable.just(chain.proceed(request.newBuilder().build()));
        } catch (IOException e) {
            this.mobilyticsLazy.mo358get().recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.INTERCEPT_REFRESH_TOKENS, false, "Reauthentication", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
            return Observable.error(e);
        }
    }

    public /* synthetic */ Response lambda$intercept$4$CookieRefreshInterceptor(Throwable th) {
        if (th instanceof AccountException) {
            if (((AccountException) th).error == 1) {
                Log.e(TAG, th, "Failed to refresh cookies due to auth error, signing out.", new Object[0]);
                this.mobilyticsLazy.mo358get().recordOperationalEvent(this.mobilyticsLazy.mo358get().createOperationalEvent(AlexaMetricsConstants.MetricEvents.SIGN_OUT_ATTEMPT, OperationalEventType.DIAGNOSTIC, "Application", TAG, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
                this.accountService.mo358get().signOut().observeOn(AndroidSchedulers.mainThread()).doOnTerminate(new Action0() { // from class: com.amazon.dee.app.services.network.interceptor.-$$Lambda$CookieRefreshInterceptor$UzZI46ZAGjOAO_59mJZBBlE9zvE
                    @Override // rx.functions.Action0
                    public final void call() {
                        CookieRefreshInterceptor.this.lambda$null$1$CookieRefreshInterceptor();
                    }
                }).subscribe($$Lambda$CookieRefreshInterceptor$qlhrnvzj448BVNBFn_TB3LHfRo.INSTANCE, $$Lambda$CookieRefreshInterceptor$dLNGo3xar6UdMkOhZT5P16ToJ1c.INSTANCE);
                return null;
            }
            Log.e(TAG, th, "Failed to refresh cookies.", new Object[0]);
            return null;
        }
        Log.e(TAG, th, "Failed to refresh cookies.", new Object[0]);
        return null;
    }

    public /* synthetic */ void lambda$null$1$CookieRefreshInterceptor() {
        this.routingService.mo358get().route(RouteName.HOME).clearBackStack().navigate();
    }
}
