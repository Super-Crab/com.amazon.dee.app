package com.amazon.dee.app.dependencies;

import com.amazon.alexa.viewmanagement.api.ViewManagerEventNotifier;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import com.amazon.alexa.viewmanagement.impl.ReactNativeViewManager;
import com.amazon.alexa.viewmanagement.impl.ViewControllerFactoryProducer;
import com.amazon.alexa.viewmanagement.impl.ViewManagerDelegate;
import com.amazon.alexa.viewmanagement.impl.ViewManagerRoutingAdapter;
import com.amazon.alexa.viewmanagement.impl.ViewPresenter;
import com.amazon.dee.app.R;
import com.amazon.dee.app.elements.ReactBridgeService;
import com.amazon.dee.app.ui.main.MainActivity;
import com.facebook.react.ReactInstanceManager;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Provider;
@Module
/* loaded from: classes12.dex */
public class ViewManagerModule {
    @Provides
    @MainScope
    public ReactNativeViewManager provideReactNativeViewManager(final Lazy<ReactBridgeService> lazy) {
        return new ReactNativeViewManager(new Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$ViewManagerModule$HdMeEFSg2XT840WbZ_ANzeP0Wt0
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                ReactInstanceManager instance;
                instance = ((ReactBridgeService) Lazy.this.mo358get()).instance();
                return instance;
            }
        });
    }

    @Provides
    @MainScope
    public ViewControllerFactoryProducer provideViewControllerFactoryProducer() {
        return new ViewControllerFactoryProducer();
    }

    @Provides
    @MainScope
    public ViewManagerDelegate provideViewManagerDelegate(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    @MainScope
    public ViewManagerEventNotifier provideViewManagerEventNotifier(ViewManagerDelegate viewManagerDelegate) {
        return viewManagerDelegate;
    }

    @Provides
    @MainScope
    public ViewManagerLoadingDelegate provideViewManagerLoadingDelegate(ViewManagerEventNotifier viewManagerEventNotifier) {
        return viewManagerEventNotifier;
    }

    @Provides
    @MainScope
    public ViewManagerRoutingAdapter provideViewManagerRoutingAdapter(ViewPresenter viewPresenter) {
        return new ViewManagerRoutingAdapter(viewPresenter);
    }

    @Provides
    @MainScope
    public ViewPresenter provideViewPresenter(MainActivity mainActivity) {
        return new ViewPresenter(mainActivity.getSupportFragmentManager(), R.id.primary_container);
    }
}
