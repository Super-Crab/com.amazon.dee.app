package com.amazon.alexa.viewmanagement.impl;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import com.google.common.base.Optional;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class ViewControllerFragment extends Fragment {
    @VisibleForTesting
    static final String EXTRA_FACTORY_CLASS_NAME = "factoryClassName";
    @VisibleForTesting
    static final String EXTRA_ROUTE_CONTEXT = "routeContext";
    private static final String TAG = ViewControllerFragment.class.getSimpleName();
    @Inject
    Lazy<ViewManagerDelegate> delegate;
    @Inject
    Lazy<ViewControllerFactoryProducer> factoryProducer;
    @VisibleForTesting
    Integer initialOrientation;
    @Inject
    Lazy<ViewManagerLoadingDelegate> loadingDelegate;
    @Inject
    Lazy<PermissionsService> permissionsService;
    @Inject
    Lazy<ReactNativeViewManager> rnViewManager;
    @VisibleForTesting
    Optional<ViewController> viewController = Optional.absent();
    @VisibleForTesting
    Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() { // from class: com.amazon.alexa.viewmanagement.impl.-$$Lambda$ViewControllerFragment$sgpu1oZfB6XQfTIHWa85NddG2jg
        @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            return ViewControllerFragment.this.lambda$new$0$ViewControllerFragment(menuItem);
        }
    };

    public static ViewControllerFragment newInstance(@NonNull String str, @NonNull RouteContext routeContext) {
        ViewControllerFragment viewControllerFragment = new ViewControllerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_FACTORY_CLASS_NAME, str);
        bundle.putParcelable(EXTRA_ROUTE_CONTEXT, routeContext);
        viewControllerFragment.setArguments(bundle);
        return viewControllerFragment;
    }

    @VisibleForTesting
    void initViewController() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            Log.e(TAG, "Fragment did not receive any args; could not create ViewController");
            return;
        }
        String string = arguments.getString(EXTRA_FACTORY_CLASS_NAME);
        if (TextUtils.isEmpty(string)) {
            Log.e(TAG, "Factory class name missing; could not create ViewController");
            return;
        }
        RouteContext routeContext = (RouteContext) arguments.getParcelable(EXTRA_ROUTE_CONTEXT);
        if (routeContext == null) {
            Log.e(TAG, "RouteContext missing; could not create ViewController");
            return;
        }
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.viewController = this.factoryProducer.mo358get().getViewController(context, this.permissionsService.mo358get(), this.rnViewManager.mo358get(), routeContext, this.loadingDelegate.mo358get(), string);
    }

    @VisibleForTesting
    void initialize() {
        initViewController();
        setHeaderTitle();
        setHeaderMenu();
    }

    public /* synthetic */ boolean lambda$new$0$ViewControllerFragment(MenuItem menuItem) {
        if (this.viewController.isPresent()) {
            String str = "Received menu click - " + menuItem;
            return this.viewController.get().onMenuItemClick(menuItem);
        }
        Log.w(TAG, "Received menu item click, but no viewController being displayed");
        return false;
    }

    public boolean onBackPressed() {
        return this.viewController.get().onBackPressed();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.viewController.isPresent()) {
            this.viewController.get().onConfigurationChanged(configuration);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initialize();
        if (this.viewController.isPresent()) {
            this.viewController.get().onCreate(getContext());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.viewController.isPresent()) {
            return this.viewController.get().makeView(layoutInflater, viewGroup);
        }
        Log.w(TAG, "Could not render ViewController as it was not available");
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.delegate.mo358get().resetHeaderMenu();
        if (this.viewController.isPresent()) {
            this.viewController.get().onDestroy(getContext());
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        Integer num;
        if (this.viewController.isPresent()) {
            this.viewController.get().onDestroyView(getView());
        }
        super.onDestroyView();
        FragmentActivity activity = getActivity();
        if (activity == null || (num = this.initialOrientation) == null) {
            return;
        }
        activity.setRequestedOrientation(num.intValue());
        this.initialOrientation = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        if (this.viewController.isPresent()) {
            this.viewController.get().onPause(getContext());
            this.viewController.get().onApplicationBackground(getContext());
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.viewController.isPresent()) {
            this.viewController.get().onResume(getContext());
            this.viewController.get().onApplicationForeground(getContext());
            View view = getView();
            if (view == null) {
                return;
            }
            String title = this.viewController.get().getTitle(getContext());
            if (TextUtils.isEmpty(title)) {
                return;
            }
            view.announceForAccessibility(title);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.viewController.isPresent()) {
            this.viewController.get().onSaveViewState(getView(), bundle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.viewController.isPresent()) {
            this.viewController.get().onAttach(getView());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        if (this.viewController.isPresent()) {
            this.viewController.get().onDetach(getView());
        }
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.viewController.isPresent()) {
            this.viewController.get().onRestoreViewState(view, bundle);
            FragmentActivity activity = getActivity();
            int orientation = this.viewController.get().getOrientation();
            if (activity == null || orientation == -1) {
                return;
            }
            this.initialOrientation = Integer.valueOf(activity.getRequestedOrientation());
            activity.setRequestedOrientation(orientation);
        }
    }

    @VisibleForTesting
    void setHeaderMenu() {
        int menuResourceId;
        if (!this.viewController.isPresent() || (menuResourceId = this.viewController.get().getMenuResourceId()) == -1) {
            return;
        }
        this.delegate.mo358get().setHeaderMenu(menuResourceId, this.onMenuItemClickListener);
    }

    @VisibleForTesting
    void setHeaderTitle() {
        if (this.viewController.isPresent()) {
            this.delegate.mo358get().setHeaderTitle(this.viewController.get().getTitle(getContext()));
        }
    }
}
