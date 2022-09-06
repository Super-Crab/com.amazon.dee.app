package com.amazon.alexa.biloba.view.confirmation;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.ConfirmationBinding;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.mosaic.view.ViewUtils;
/* loaded from: classes6.dex */
public class ConfirmationView extends BilobaViewController {
    private static final String TAG = "ConfirmationView";
    private ConfirmationBinding binding;
    private Context context;
    private View view;
    private ConfirmationViewModel viewModel;

    public ConfirmationView(Context context, @NonNull ConfirmationViewModel confirmationViewModel) {
        this.context = context;
        this.viewModel = confirmationViewModel;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return this.viewModel.getTitleText();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.binding = (ConfirmationBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.confirmation, null, false);
        this.binding.setViewModel(this.viewModel);
        this.view = this.binding.getRoot();
        if (!TextUtils.isEmpty(this.viewModel.getTtcfEventName())) {
            this.viewModel.stopRecordingTTCF();
        }
        registerViewAttributes((LinearLayout) this.view.findViewById(R.id.no_connection_banner), null);
        return this.view;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        if (this.viewModel.getIsSuccess()) {
            recordViewMetric(MetricsConstants.UserInteractionMetrics.SUCCESS_VIEW, "");
        } else {
            recordViewMetric(MetricsConstants.UserInteractionMetrics.FAILURE_VIEW, "");
        }
        subscribeToNetworkChange();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
    }
}
