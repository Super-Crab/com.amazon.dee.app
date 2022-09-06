package com.amazon.alexa.viewmanagement.impl;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.amazon.alexa.routing.api.RouteContext;
/* loaded from: classes10.dex */
public class ViewPresenter {
    @VisibleForTesting
    static final String TAG = "ViewPresenter";
    private FragmentManager fragmentManager;
    @IdRes
    private int rootContainerId;

    public ViewPresenter(@NonNull FragmentManager fragmentManager, @NonNull @IdRes int i) {
        this.rootContainerId = -1;
        this.fragmentManager = fragmentManager;
        this.rootContainerId = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushView(@NonNull String str, @NonNull RouteContext routeContext) {
        this.fragmentManager.beginTransaction().replace(this.rootContainerId, ViewControllerFragment.newInstance(str, routeContext), TAG).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeView() {
        Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag(TAG);
        if (findFragmentByTag != null) {
            this.fragmentManager.beginTransaction().remove(findFragmentByTag).commitAllowingStateLoss();
        }
    }
}
