package com.amazon.alexa.biloba.view;

import android.widget.LinearLayout;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import rx.Subscription;
import rx.functions.Action1;
/* loaded from: classes6.dex */
public abstract class BilobaViewController extends BilobaViewWithMetrics implements ViewController {
    private static final String TAG = "BilobaViewController";
    private LinearLayout internetBanner;
    private final NetworkService networkService = (NetworkService) GeneratedOutlineSupport1.outline20(NetworkService.class);
    private Subscription networkSubscription;
    private BilobaViewModel viewModel;

    public void onNetworkChanged(boolean z) {
        BilobaViewModel bilobaViewModel;
        if (z && (bilobaViewModel = this.viewModel) != null && bilobaViewModel.getError() != null && this.viewModel.getError().getValue() != null) {
            this.viewModel.sendRequest();
        }
        if (this.internetBanner == null) {
            LogUtils.i(TAG, "Internet Banner is null, No internet banner will not be shown");
        } else {
            this.internetBanner.setVisibility(z ? 8 : 0);
        }
    }

    public void registerViewAttributes(LinearLayout linearLayout, BilobaViewModel bilobaViewModel) {
        this.internetBanner = linearLayout;
        this.viewModel = bilobaViewModel;
    }

    public void subscribeToNetworkChange() {
        this.networkSubscription = this.networkService.onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.alexa.biloba.view.-$$Lambda$3fcj4PGzZPkFfzLOHFciCg7BfcY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BilobaViewController.this.onNetworkChanged(((Boolean) obj).booleanValue());
            }
        });
    }

    public void unSubscribeToNetworkChange() {
        Subscription subscription = this.networkSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.networkSubscription = null;
        }
    }
}
