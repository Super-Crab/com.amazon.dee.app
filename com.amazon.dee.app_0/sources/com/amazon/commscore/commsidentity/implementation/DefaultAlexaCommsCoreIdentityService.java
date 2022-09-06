package com.amazon.commscore.commsidentity.implementation;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.identity.CommsCoreIdentity;
import com.amazon.commscore.api.identity.CookieProvider;
import com.amazon.commscore.commsidentity.common.TimeUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;
import rx.Observable;
/* loaded from: classes12.dex */
public class DefaultAlexaCommsCoreIdentityService implements AlexaCommsCoreIdentityService, CookieProvider {
    private static final String TAG = "DefaultAlexaCommsCoreIdentityService";
    private CommsCoreIdentityViewModel commsCoreIdentityViewModel;
    private Lazy<EventBus> eventBusLazy;
    private TimeUtil timeUtil;

    @Inject
    public DefaultAlexaCommsCoreIdentityService(Lazy<EventBus> lazy, CommsCoreIdentityViewModel commsCoreIdentityViewModel, TimeUtil timeUtil) {
        this.eventBusLazy = lazy;
        this.commsCoreIdentityViewModel = commsCoreIdentityViewModel;
        this.timeUtil = timeUtil;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishEvent() {
        this.eventBusLazy.mo358get().publish(new Message.Builder().setEventType(AlexaCommsCoreIdentityService.EVENT_IDENTITY_CHANGED).setDate(this.timeUtil.getCurrentTimeMillis()).build());
    }

    private void triggerCommsIdentityRefresh() {
        this.commsCoreIdentityViewModel.refreshCommsIdentity().subscribeOn(Schedulers.computation()).subscribe(new DisposableCompletableObserver() { // from class: com.amazon.commscore.commsidentity.implementation.DefaultAlexaCommsCoreIdentityService.1
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                DefaultAlexaCommsCoreIdentityService.this.publishEvent();
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            @SuppressLint({"LongLogTag"})
            public void onError(Throwable th) {
                Log.e(DefaultAlexaCommsCoreIdentityService.TAG, th.toString());
            }
        });
    }

    @Override // com.amazon.commscore.api.identity.CookieProvider
    public Observable<String[]> getCookies(String str, String str2) {
        return this.commsCoreIdentityViewModel.getCookiesForDirectedId(str, str2);
    }

    @Override // com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService
    @Nullable
    @SuppressLint({"LongLogTag"})
    public CommsCoreIdentity getIdentity() {
        try {
            Log.e(TAG, "Getting identity from db");
            return this.commsCoreIdentityViewModel.getCommsCoreIdentity().subscribeOn(Schedulers.computation()).firstOrError().blockingGet();
        } catch (Exception e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getIdentity error: ");
            outline107.append(e.toString());
            Log.e(str, outline107.toString());
            return null;
        }
    }

    public /* synthetic */ void lambda$registerForIdentityEvents$1$DefaultAlexaCommsCoreIdentityService(Message message) {
        triggerCommsIdentityRefresh();
    }

    public /* synthetic */ void lambda$registerForIdentityEvents$3$DefaultAlexaCommsCoreIdentityService(Message message) {
        triggerCommsIdentityRefresh();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerForIdentityEvents() {
        MultiFilterSubscriber subscriber = this.eventBusLazy.mo358get().getSubscriber();
        subscriber.subscribeFilter($$Lambda$DefaultAlexaCommsCoreIdentityService$6QVsgxRHGyM5XG6Bfj5G7PyE4.INSTANCE, new MessageHandler() { // from class: com.amazon.commscore.commsidentity.implementation.-$$Lambda$DefaultAlexaCommsCoreIdentityService$ICVLQZ60lHmeDoMmoRPm2yr5oME
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultAlexaCommsCoreIdentityService.this.lambda$registerForIdentityEvents$1$DefaultAlexaCommsCoreIdentityService(message);
            }
        });
        subscriber.subscribeFilter($$Lambda$DefaultAlexaCommsCoreIdentityService$JNMKTWCSv6Q5eLdA28N17lUSs.INSTANCE, new MessageHandler() { // from class: com.amazon.commscore.commsidentity.implementation.-$$Lambda$DefaultAlexaCommsCoreIdentityService$Fq9fjYQR7VNm0qOosXM3XXRMWp0
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                DefaultAlexaCommsCoreIdentityService.this.lambda$registerForIdentityEvents$3$DefaultAlexaCommsCoreIdentityService(message);
            }
        });
        triggerCommsIdentityRefresh();
    }
}
