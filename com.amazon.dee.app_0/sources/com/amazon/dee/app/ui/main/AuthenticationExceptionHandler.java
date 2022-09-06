package com.amazon.dee.app.ui.main;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.alexa.identity.AccountException;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.dee.app.http.CoralServiceException;
import rx.AsyncEmitter;
import rx.Observable;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class AuthenticationExceptionHandler {
    static final String TAG = MainViewModel.class.getSimpleName();
    Context context;
    Mobilytics mobilytics;
    RoutingService routingService;
    UserMessageService userMessageService;

    public AuthenticationExceptionHandler(Context context, Mobilytics mobilytics, UserMessageService userMessageService, RoutingService routingService) {
        this.context = context;
        this.mobilytics = mobilytics;
        this.userMessageService = userMessageService;
        this.routingService = routingService;
    }

    private int getMessageForErrorCode(int i) {
        String str = TAG;
        Log.e(str, "Error received during login: " + i);
        return i == 401 ? R.string.login_401_error_message : (i < 400 || i >= 500) ? (i < 500 || i >= 600) ? R.string.main_connection_timed_out_message : R.string.login_server_error_message : R.string.login_client_error_message;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$1(AsyncEmitter asyncEmitter) {
        asyncEmitter.onNext(LoginErrorAction.RETRY);
        asyncEmitter.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showRetryOrExitDialog$3(AsyncEmitter asyncEmitter) {
        asyncEmitter.onNext(LoginErrorAction.EXIT);
        asyncEmitter.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showRetryOrExitDialog$4(AsyncEmitter asyncEmitter) {
        asyncEmitter.onNext(LoginErrorAction.RETRY);
        asyncEmitter.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showRetryOrExitDialog$5(AsyncEmitter asyncEmitter) {
        asyncEmitter.onNext(LoginErrorAction.EXIT);
        asyncEmitter.onCompleted();
    }

    private void showRetryOrExitDialog(int i, final AsyncEmitter<LoginErrorAction> asyncEmitter) {
        this.userMessageService.message(i).withDismiss(new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$AuthenticationExceptionHandler$ommdgqIANIs7a8IapSRXcvG7A3o
            @Override // java.lang.Runnable
            public final void run() {
                AuthenticationExceptionHandler.lambda$showRetryOrExitDialog$3(AsyncEmitter.this);
            }
        }).withAction(R.string.retry_button, new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$AuthenticationExceptionHandler$aQdNBDBlsNEciUbPFJgeEhzP1Wo
            @Override // java.lang.Runnable
            public final void run() {
                AuthenticationExceptionHandler.lambda$showRetryOrExitDialog$4(AsyncEmitter.this);
            }
        }).withCancel(R.string.close_button, new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$AuthenticationExceptionHandler$-mJxEoz6xIIYAt2YQcA_2AhqNCA
            @Override // java.lang.Runnable
            public final void run() {
                AuthenticationExceptionHandler.lambda$showRetryOrExitDialog$5(AsyncEmitter.this);
            }
        }).show();
    }

    public Observable<LoginErrorAction> handle(final Throwable th) {
        return Observable.fromAsync(new Action1() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$AuthenticationExceptionHandler$v--RzRK5CYR6XSZc6bItKBpp7Rg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AuthenticationExceptionHandler.this.lambda$handle$2$AuthenticationExceptionHandler(th, (AsyncEmitter) obj);
            }
        }, AsyncEmitter.BackpressureMode.LATEST);
    }

    public /* synthetic */ void lambda$handle$2$AuthenticationExceptionHandler(Throwable th, final AsyncEmitter asyncEmitter) {
        try {
            Log.e(TAG, th, "Failed to obtain user and appropriate marketplace", new Object[0]);
            if (th instanceof AccountException) {
                int i = ((AccountException) th).error;
                if (i == 4) {
                    Log.e(TAG, "ERROR_SSL: exception was handled");
                    Mobilytics mobilytics = this.mobilytics;
                    mobilytics.recordCriticalEvent(AlexaMetricsConstants.MetricEvents.AUTH_ERROR_SSL_CERTIFICATE, AlexaMetricsConstants.MetricEvents.AUTH_ERROR_SSL_CERTIFICATE, "Error_" + AccountException.class.getName(), TAG, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
                    this.userMessageService.message(R.string.main_ssl_cert_error).withDismiss(new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$AuthenticationExceptionHandler$cqWHqQCDNvjwwjzY6_W3NqgYj2o
                        @Override // java.lang.Runnable
                        public final void run() {
                            AuthenticationExceptionHandler.this.lambda$null$0$AuthenticationExceptionHandler(asyncEmitter);
                        }
                    }).show();
                } else if (i == 2) {
                    Log.e(TAG, "ERROR_NETWORK: exception was handled");
                    Mobilytics mobilytics2 = this.mobilytics;
                    mobilytics2.recordCriticalEvent(AlexaMetricsConstants.MetricEvents.AUTH_ERROR_NETWORK_FAIL, AlexaMetricsConstants.MetricEvents.AUTH_ERROR_NETWORK_FAIL, "Error_" + AccountException.class.getName(), TAG, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
                    showRetryOrExitDialog(R.string.main_no_network_message, asyncEmitter);
                } else if (i == 5) {
                    Log.e(TAG, "LOGIN_CANCELLED: exception was handled");
                    Mobilytics mobilytics3 = this.mobilytics;
                    mobilytics3.recordCriticalEvent(AlexaMetricsConstants.MetricEvents.AUTH_ERROR_LOGIN_CANCELLED, AlexaMetricsConstants.MetricEvents.AUTH_ERROR_LOGIN_CANCELLED, "Error_" + AccountException.class.getName(), TAG, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
                    asyncEmitter.onNext(LoginErrorAction.EXIT);
                    asyncEmitter.onCompleted();
                } else {
                    String str = TAG;
                    Log.e(str, "Error Code : " + i + " exception was NOT handled");
                    Mobilytics mobilytics4 = this.mobilytics;
                    mobilytics4.recordCriticalEvent("ErrorNotHandled", "ErrorNotHandled", "Error_" + AccountException.class.getName(), TAG, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
                    asyncEmitter.onNext(LoginErrorAction.EXIT);
                }
            } else if (th instanceof CoralServiceException) {
                int statusCode = ((CoralServiceException) th).getStatusCode();
                Mobilytics mobilytics5 = this.mobilytics;
                mobilytics5.recordCriticalEvent(AlexaMetricsConstants.MetricEvents.AUTH_ERROR_CORAL_SERVICE_EXCEPTION, AlexaMetricsConstants.MetricEvents.AUTH_ERROR_CORAL_SERVICE_EXCEPTION, "Error_" + CoralServiceException.class.getName(), TAG, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
                this.userMessageService.message(getMessageForErrorCode(statusCode)).withDismiss(new Runnable() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$AuthenticationExceptionHandler$3Mk7I042nWr-6xpTyUSRo7rK-PQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        AuthenticationExceptionHandler.lambda$null$1(AsyncEmitter.this);
                    }
                }).show();
            } else {
                Log.e(TAG, "Login error was not an AccountException or CoralServiceException");
                this.mobilytics.recordCriticalEvent(AlexaMetricsConstants.MetricEvents.AUTH_ERROR_UNKNOWN_CLASS, AlexaMetricsConstants.MetricEvents.AUTH_ERROR_UNKNOWN_CLASS, "Error_UnknownClass", TAG, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
                showRetryOrExitDialog(R.string.main_connection_timed_out_message, asyncEmitter);
            }
        } catch (Exception e) {
            Log.e(TAG, e, "Failed to handle exception", new Object[0]);
            this.mobilytics.recordCriticalEvent("ErrorFailedToHandleException", "ErrorFailedToHandleException", "Error_Unhandled", TAG, e, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
            asyncEmitter.onError(e);
        }
    }

    public /* synthetic */ void lambda$null$0$AuthenticationExceptionHandler(AsyncEmitter asyncEmitter) {
        try {
            Context context = this.context;
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.android.chrome")));
        } catch (ActivityNotFoundException unused) {
            Context context2 = this.context;
            context2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.android.chrome")));
        }
        asyncEmitter.onNext(LoginErrorAction.EXIT);
        asyncEmitter.onCompleted();
    }
}
