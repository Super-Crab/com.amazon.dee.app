package com.amazon.alexa.viewmanagement.impl;

import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class ViewControllerFragment_MembersInjector implements MembersInjector<ViewControllerFragment> {
    private final Provider<ViewManagerDelegate> delegateProvider;
    private final Provider<ViewControllerFactoryProducer> factoryProducerProvider;
    private final Provider<ViewManagerLoadingDelegate> loadingDelegateProvider;
    private final Provider<PermissionsService> permissionsServiceProvider;
    private final Provider<ReactNativeViewManager> rnViewManagerProvider;

    public ViewControllerFragment_MembersInjector(Provider<PermissionsService> provider, Provider<ReactNativeViewManager> provider2, Provider<ViewControllerFactoryProducer> provider3, Provider<ViewManagerDelegate> provider4, Provider<ViewManagerLoadingDelegate> provider5) {
        this.permissionsServiceProvider = provider;
        this.rnViewManagerProvider = provider2;
        this.factoryProducerProvider = provider3;
        this.delegateProvider = provider4;
        this.loadingDelegateProvider = provider5;
    }

    public static MembersInjector<ViewControllerFragment> create(Provider<PermissionsService> provider, Provider<ReactNativeViewManager> provider2, Provider<ViewControllerFactoryProducer> provider3, Provider<ViewManagerDelegate> provider4, Provider<ViewManagerLoadingDelegate> provider5) {
        return new ViewControllerFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectDelegate(ViewControllerFragment viewControllerFragment, Lazy<ViewManagerDelegate> lazy) {
        viewControllerFragment.delegate = lazy;
    }

    public static void injectFactoryProducer(ViewControllerFragment viewControllerFragment, Lazy<ViewControllerFactoryProducer> lazy) {
        viewControllerFragment.factoryProducer = lazy;
    }

    public static void injectLoadingDelegate(ViewControllerFragment viewControllerFragment, Lazy<ViewManagerLoadingDelegate> lazy) {
        viewControllerFragment.loadingDelegate = lazy;
    }

    public static void injectPermissionsService(ViewControllerFragment viewControllerFragment, Lazy<PermissionsService> lazy) {
        viewControllerFragment.permissionsService = lazy;
    }

    public static void injectRnViewManager(ViewControllerFragment viewControllerFragment, Lazy<ReactNativeViewManager> lazy) {
        viewControllerFragment.rnViewManager = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ViewControllerFragment viewControllerFragment) {
        injectPermissionsService(viewControllerFragment, DoubleCheck.lazy(this.permissionsServiceProvider));
        injectRnViewManager(viewControllerFragment, DoubleCheck.lazy(this.rnViewManagerProvider));
        injectFactoryProducer(viewControllerFragment, DoubleCheck.lazy(this.factoryProducerProvider));
        injectDelegate(viewControllerFragment, DoubleCheck.lazy(this.delegateProvider));
        injectLoadingDelegate(viewControllerFragment, DoubleCheck.lazy(this.loadingDelegateProvider));
    }
}
