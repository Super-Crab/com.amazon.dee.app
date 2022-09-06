package com.amazon.alexa.redesign.utils;

import android.util.Log;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.ViewControllerToRoutingInterface;
import java.lang.ref.WeakReference;
/* loaded from: classes10.dex */
public class HomeChannelLifeCycleObserver implements MainActivityLifecycleObserver {
    private boolean isForegrounding = false;
    private WeakReference<HomeContract.Presenter> presenterReference;
    private WeakReference<ViewControllerToRoutingInterface> routingInterfaceReference;
    private WeakReference<HomeContract.View> viewReference;

    public HomeChannelLifeCycleObserver(HomeContract.Presenter presenter, HomeContract.View view, ViewControllerToRoutingInterface viewControllerToRoutingInterface) {
        this.presenterReference = new WeakReference<>(presenter);
        this.viewReference = new WeakReference<>(view);
        this.routingInterfaceReference = new WeakReference<>(viewControllerToRoutingInterface);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityCreated() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityDestroy() {
        HomeContract.Presenter presenter = this.presenterReference.get();
        if (presenter != null) {
            presenter.unsubscribeFromEventBus();
            presenter.onActivityDestroyed();
        }
        HomeContract.View view = this.viewReference.get();
        if (view != null) {
            view.unregisterReceivers();
            view.resetReturnPosition();
        }
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityPause() {
        HomeContract.View view = this.viewReference.get();
        HomeContract.Presenter presenter = this.presenterReference.get();
        if (view != null && presenter != null) {
            try {
                int firstVisibleItemPosition = view.getFirstVisibleItemPosition();
                presenter.onPauseHome(view.mo2380getViewItemFromPosition(firstVisibleItemPosition), firstVisibleItemPosition, view.getTotalViewItems());
            } catch (NullPointerException e) {
                Log.e("HomeChannelLifeCycleObs", "firstVisibilePosition is unavailable", e);
            }
        }
        ViewControllerToRoutingInterface viewControllerToRoutingInterface = this.routingInterfaceReference.get();
        if (viewControllerToRoutingInterface != null) {
            viewControllerToRoutingInterface.notifyRoutingAdapterOnPause();
        }
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityResume() {
        ViewControllerToRoutingInterface viewControllerToRoutingInterface;
        HomeContract.Presenter presenter = this.presenterReference.get();
        if (presenter != null) {
            presenter.onResumeHome(this.isForegrounding);
        }
        if (this.isForegrounding && (viewControllerToRoutingInterface = this.routingInterfaceReference.get()) != null) {
            viewControllerToRoutingInterface.notifyRoutingAdapterOnResume();
        }
        this.isForegrounding = true;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStart() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStop() {
    }
}
