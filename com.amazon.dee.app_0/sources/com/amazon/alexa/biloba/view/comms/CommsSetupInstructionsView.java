package com.amazon.alexa.biloba.view.comms;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.CommsSetupInstructionsViewBinding;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.SpanBuilder;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class CommsSetupInstructionsView extends BilobaViewController {
    private static final String TAG = "CommsSetupInstructionsView";
    private Context context;
    private View view;
    private RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    private CommsSetupViewModel viewModel = new CommsSetupViewModel();

    public CommsSetupInstructionsView(Context context) {
        this.context = context;
    }

    public SpannableStringBuilder getResendLinkText() {
        SpanBuilder spanBuilder = new SpanBuilder();
        spanBuilder.append(this.context.getString(R.string.comms_setup_instruct_resend_link), new CharacterStyle[0]).append(" ", new CharacterStyle[0]).append(this.context.getString(R.string.comms_setup_instruct_tap_here), AndroidUtils.createShareLinkClickableSpan(this.context, this.viewModel.getShareLinkUrl(), R.string.comms_setup_share_title, R.attr.mosaicAction10, false));
        return spanBuilder.getValue();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.comms_setup_instruct_title);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.context = viewGroup.getContext();
        CommsSetupInstructionsViewBinding commsSetupInstructionsViewBinding = (CommsSetupInstructionsViewBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(viewGroup.getContext())), R.layout.comms_setup_instructions_view, viewGroup, false);
        commsSetupInstructionsViewBinding.setLifecycleOwner((LifecycleOwner) this.context);
        commsSetupInstructionsViewBinding.setViewmodel(this.viewModel);
        commsSetupInstructionsViewBinding.setHandler(this);
        this.view = commsSetupInstructionsViewBinding.getRoot();
        registerViewAttributes((LinearLayout) this.view.findViewById(R.id.no_connection_banner), this.viewModel);
        return this.view;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric(MetricsConstants.UserInteractionMetrics.COMMS_SETUP_GUIDE_VIEW, MetricsConstants.ENTER_EVENT);
        this.viewModel.create(null);
        subscribeToNetworkChange();
    }

    public void onClickOk(@NonNull View view) {
        this.routingService.navigateBackward();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        this.viewModel.destroy();
    }
}
