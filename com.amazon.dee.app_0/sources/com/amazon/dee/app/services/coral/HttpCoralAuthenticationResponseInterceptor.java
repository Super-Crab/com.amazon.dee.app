package com.amazon.dee.app.services.coral;

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
import com.dee.app.http.CoralServiceException;
import com.dee.app.http.HttpCoralService;
import dagger.Lazy;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
/* loaded from: classes12.dex */
public class HttpCoralAuthenticationResponseInterceptor implements HttpCoralService.ResponseInterceptor {
    private static final String TAG = Log.tag(HttpCoralAuthenticationResponseInterceptor.class);
    private final Lazy<AccountService> accountService;
    private final OkHttpClient httpClient;
    private final Lazy<IdentityService> identityService;
    private final Lazy<Mobilytics> mobilyticsLazy;
    private final Lazy<RoutingService> routingService;

    public HttpCoralAuthenticationResponseInterceptor(OkHttpClient okHttpClient, Lazy<IdentityService> lazy, Lazy<AccountService> lazy2, Lazy<RoutingService> lazy3, Lazy<Mobilytics> lazy4) {
        this.httpClient = okHttpClient;
        this.identityService = lazy;
        this.accountService = lazy2;
        this.routingService = lazy3;
        this.mobilyticsLazy = lazy4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$2(Void r0) {
    }

    @Override // com.dee.app.http.HttpCoralService.ResponseInterceptor
    public <T> T intercept(final HttpCoralService.ResponseInterceptor.Chain<T> chain) throws CoralServiceException {
        if (!this.identityService.mo358get().isRegistered(TAG)) {
            return null;
        }
        Response response = chain.response();
        if (response.code() == 401) {
            this.mobilyticsLazy.mo358get().recordOperationalEvent(this.mobilyticsLazy.mo358get().createOperationalEvent(AlexaMetricsConstants.MetricEvents.UNAUTHORIZED_REQUEST, OperationalEventType.DIAGNOSTIC, "Application", HttpCoralAuthenticationResponseInterceptor.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
            Response response2 = (Response) this.identityService.mo358get().refresh(TAG).switchMap(new Func1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$HttpCoralAuthenticationResponseInterceptor$c0sOF53fB5eoqLKvY1CfaJlBXEM
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return HttpCoralAuthenticationResponseInterceptor.this.lambda$intercept$0$HttpCoralAuthenticationResponseInterceptor(chain, (UserIdentity) obj);
                }
            }).onErrorReturn(new Func1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$HttpCoralAuthenticationResponseInterceptor$sCwnrT9tt7wzgeANfzmoTz2w9fo
                @Override // rx.functions.Func1
                /* renamed from: call */
                public final Object mo13102call(Object obj) {
                    return HttpCoralAuthenticationResponseInterceptor.this.lambda$intercept$4$HttpCoralAuthenticationResponseInterceptor((Throwable) obj);
                }
            }).toBlocking().single();
            if (response2 != null) {
                response.close();
                return chain.proceed(response2);
            }
        }
        return chain.proceed(response);
    }

    public /* synthetic */ Observable lambda$intercept$0$HttpCoralAuthenticationResponseInterceptor(HttpCoralService.ResponseInterceptor.Chain chain, UserIdentity userIdentity) {
        try {
            this.mobilyticsLazy.mo358get().recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.INTERCEPT_REFRESH_TOKENS, true, "Reauthentication", getClass().getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
            return Observable.just(this.httpClient.newCall(chain.request().build()).execute());
        } catch (IOException e) {
            this.mobilyticsLazy.mo358get().recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.INTERCEPT_REFRESH_TOKENS, false, "Reauthentication", HttpCoralAuthenticationResponseInterceptor.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
            return Observable.error(e);
        }
    }

    public /* synthetic */ Response lambda$intercept$4$HttpCoralAuthenticationResponseInterceptor(Throwable th) {
        if (th instanceof AccountException) {
            if (((AccountException) th).error == 1) {
                Log.e(TAG, th, "Failed to refresh cookies due to auth error, signing out.", new Object[0]);
                this.mobilyticsLazy.mo358get().recordOperationalEvent(this.mobilyticsLazy.mo358get().createOperationalEvent(AlexaMetricsConstants.MetricEvents.SIGN_OUT_ATTEMPT, OperationalEventType.DIAGNOSTIC, TAG, HttpCoralAuthenticationResponseInterceptor.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
                this.accountService.mo358get().signOut().observeOn(AndroidSchedulers.mainThread()).doOnTerminate(new Action0() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$HttpCoralAuthenticationResponseInterceptor$kqr4mo_NQu21YY5XW04NijJOn28
                    @Override // rx.functions.Action0
                    public final void call() {
                        HttpCoralAuthenticationResponseInterceptor.this.lambda$null$1$HttpCoralAuthenticationResponseInterceptor();
                    }
                }).subscribe($$Lambda$HttpCoralAuthenticationResponseInterceptor$17l3wKkzAiZ1NtiJi6qwlR5tWlg.INSTANCE, $$Lambda$HttpCoralAuthenticationResponseInterceptor$NAMe_69121SdY3gEHq0xcSHKweI.INSTANCE);
                return null;
            }
            Log.e(TAG, th, "Failed to refresh cookies.", new Object[0]);
            return null;
        }
        Log.e(TAG, th, "Failed to refresh cookies.", new Object[0]);
        return null;
    }

    public /* synthetic */ void lambda$null$1$HttpCoralAuthenticationResponseInterceptor() {
        this.routingService.mo358get().route(RouteName.HOME).clearBackStack().navigate();
    }
}
