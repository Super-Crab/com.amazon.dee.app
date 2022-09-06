package com.amazon.dee.app.dependencies;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.location.LocationProvider;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.redesign.HomeRoutingAdapter;
import com.amazon.alexa.redesign.HomeViewDelegate;
import com.amazon.alexa.redesign.LatencyReportingDelegate;
import com.amazon.alexa.redesign.dependency.HomeDependencies;
import com.amazon.alexa.redesign.header.HeaderNavigationDelegate;
import com.amazon.alexa.routing.api.RoutingAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.dee.app.R;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.amazon.dee.app.ui.main.MainHandler;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import com.facebook.react.ReactInstanceManager;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
@Module
/* loaded from: classes12.dex */
public class HomeModule {
    public static final String HOME_ROUTING_ADAPTER = "HomeRoutingAdapter";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$provideHomeRoutingAdapter$1(Lazy lazy) {
        LatencyService.complete(LatencyService.Component.HOME, LatencyService.Completion.HOME);
        LatencyService.recordUserPerceivedColdStartLatency();
        ((TTCFCheckpoint) lazy.mo358get()).markComplete(LatencyService.Completion.HOME.value());
    }

    @Provides
    @MainScope
    @Named(HOME_ROUTING_ADAPTER)
    public RoutingAdapter provideHomeRoutingAdapter(final Lazy<Context> lazy, final Lazy<LocationProvider> lazy2, final Lazy<ReactBridgeService> lazy3, final Lazy<MainHandler> lazy4, Lazy<HomeViewDelegate> lazy5, final Lazy<BridgeStatusService> lazy6, final Lazy<IdentityService> lazy7, final Lazy<RoutingService> lazy8, final Lazy<VoiceService> lazy9, final Lazy<TTCFCheckpoint> lazy10, final Lazy<UserMessageService> lazy11, Activity activity) {
        Component component = new Component();
        component.provide((Class<? extends Class>) HeaderNavigationDelegate.class, (Class) new HeaderNavigationDelegate() { // from class: com.amazon.dee.app.dependencies.HomeModule.1
            @Override // com.amazon.alexa.redesign.header.HeaderNavigationDelegate
            public void onHelpAndFeedbackClicked() {
                ((MainHandler) lazy4.mo358get()).onHelpAndFeedbackClicked();
            }

            @Override // com.amazon.alexa.redesign.header.HeaderNavigationDelegate
            public void onMenuClicked() {
                ((MainHandler) lazy4.mo358get()).onMenuClicked();
            }
        }).register();
        component.provide(ReactInstanceManager.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$HomeModule$fv5TVpSeLLV_tQHUiSe2oWk139M
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                ReactInstanceManager instance;
                instance = ((ReactBridgeService) Lazy.this.mo358get()).instance();
                return instance;
            }
        }).register();
        lazy.getClass();
        component.provide(Context.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$uMnWtsial4wFirBPeBlAxla-np0
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return (Context) Lazy.this.mo358get();
            }
        }).register();
        lazy2.getClass();
        component.provide(LocationProvider.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$cFg-HJi6B6bloI22ZGXnU_kREuU
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return (LocationProvider) Lazy.this.mo358get();
            }
        }).register();
        component.provide((Class<? extends Class>) LatencyReportingDelegate.class, (Class) new LatencyReportingDelegate() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$HomeModule$m6324jTsRBRn3gmT9_lys8FCHBI
            @Override // com.amazon.alexa.redesign.LatencyReportingDelegate
            public final void reportHomeLaunch() {
                HomeModule.lambda$provideHomeRoutingAdapter$1(Lazy.this);
            }
        }).register();
        lazy6.getClass();
        component.provide(BridgeStatusService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$gMTn1ijAG02Ciobd-YkXP_pS0PU
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return (BridgeStatusService) Lazy.this.mo358get();
            }
        }).register();
        lazy7.getClass();
        component.provide(IdentityService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$LMlKSWUAujs2VI1b21RfujUk2FI
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return (IdentityService) Lazy.this.mo358get();
            }
        }).register();
        lazy8.getClass();
        component.provide(RoutingService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$Fzm3EpMHpHqKpzCRQpsqKC6W9H8
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return (RoutingService) Lazy.this.mo358get();
            }
        }).register();
        lazy9.getClass();
        component.provide(VoiceService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$77f9U8oDHUk-BV9K7SIRJjuIaiA
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return (VoiceService) Lazy.this.mo358get();
            }
        }).register();
        lazy11.getClass();
        component.provide(UserMessageService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$rBah7Jg69L-jyin_qWcHatuV98E
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return (UserMessageService) Lazy.this.mo358get();
            }
        }).register();
        Router router = new Router(activity, component);
        router.attach((ViewGroup) activity.findViewById(R.id.home_container));
        HomeDependencies.initialize(component);
        return new HomeRoutingAdapter(lazy5.mo358get(), router, false);
    }

    @Provides
    @MainScope
    public HomeViewDelegate provideHomeViewDelegate(Activity activity) {
        return (HomeViewDelegate) activity;
    }
}
