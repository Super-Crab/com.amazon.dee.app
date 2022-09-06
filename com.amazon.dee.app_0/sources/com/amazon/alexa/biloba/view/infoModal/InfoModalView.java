package com.amazon.alexa.biloba.view.infoModal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.InfoModalViewBinding;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.CommsHelper;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.mosaic.view.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class InfoModalView extends BilobaViewController {
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CommsHelper> commsHelper;
    private Context context;
    private final InfoModalViewModel viewModel;

    public InfoModalView(Context context, @NonNull InfoModalViewModel infoModalViewModel) {
        BilobaDependencies.inject(this);
        this.context = context;
        this.viewModel = infoModalViewModel;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public String getTitle(Context context) {
        return this.viewModel.getTitleText();
    }

    public void handleOkButton(View view) {
        if (this.viewModel.getClickMetricName() != null) {
            recordClickMetric(this.viewModel.getClickMetricName(), MetricsConstants.CLICK_EVENT);
        }
        LogUtils.d("TAG", "OK Button clicked from InfoModalView");
        this.viewModel.onTapOkButton();
    }

    public boolean isDropInEnabledForCareContact() {
        return this.careActorsStore.mo358get().isDropInEnabledForCareContact();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public View makeView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.context = viewGroup.getContext();
        recordViewMetric(this.viewModel.getModalRenderedMetric(), MetricsConstants.ENTER_EVENT);
        InfoModalViewBinding infoModalViewBinding = (InfoModalViewBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.info_modal_view, viewGroup, false);
        infoModalViewBinding.setViewModel(this.viewModel);
        infoModalViewBinding.setHandler(this);
        infoModalViewBinding.setLifecycleOwner((LifecycleOwner) this.context);
        View root = infoModalViewBinding.getRoot();
        registerViewAttributes((LinearLayout) root.findViewById(R.id.no_connection_banner), null);
        return root;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(View view) {
        recordViewMetric(this.viewModel.getViewMetricName(), MetricsConstants.ENTER_EVENT);
        subscribeToNetworkChange();
    }

    public void onCallClicked(View view) {
        if (this.viewModel.getCallClickedMetric() != null) {
            recordClickMetric(this.viewModel.getCallClickedMetric(), MetricsConstants.CLICK_EVENT);
        }
        LogUtils.d("TAG", "Call Button clicked from InfoModalView");
        this.commsHelper.mo358get().onCallClicked(this.context);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(View view) {
        unSubscribeToNetworkChange();
    }

    public void onDropinClicked(View view) {
        if (this.viewModel.getDropInClickedMetric() != null) {
            recordClickMetric(this.viewModel.getDropInClickedMetric(), MetricsConstants.CLICK_EVENT);
        }
        LogUtils.d("TAG", "Drop-in Button clicked from InfoModalView");
        this.commsHelper.mo358get().onDropinClicked(this.context);
    }

    public void onMessageClicked(View view) {
        if (this.viewModel.getMessageClickedMetric() != null) {
            recordClickMetric(this.viewModel.getMessageClickedMetric(), MetricsConstants.CLICK_EVENT);
        }
        LogUtils.d("TAG", "Share Button clicked from InfoModalView");
        this.commsHelper.mo358get().onMessageClicked(this.context);
    }

    @VisibleForTesting
    public InfoModalView(Context context, @NonNull InfoModalViewModel infoModalViewModel, Lazy<CommsHelper> lazy, Lazy<CareActorsStore> lazy2) {
        this.context = context;
        this.viewModel = infoModalViewModel;
        this.commsHelper = lazy;
        this.careActorsStore = lazy2;
    }
}
