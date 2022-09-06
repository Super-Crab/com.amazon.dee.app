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
import com.amazon.alexa.biloba.databinding.CommsShareSetupLinkViewBinding;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.SpanBuilder;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class CommsShareSetupLinkView extends BilobaViewController {
    private static final String TAG = "CommsShareSetupLinkView";
    private Context context;
    private View view;
    private final RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    private final CommsSetupViewModel viewModel = new CommsSetupViewModel();

    public CommsShareSetupLinkView(Context context) {
        this.context = context;
    }

    public SpannableStringBuilder getShareLinkText(String str) {
        SpanBuilder spanBuilder = new SpanBuilder();
        spanBuilder.append(this.context.getString(R.string.comms_share_setup_link_step1, str), new CharacterStyle[0]).append("\n", new CharacterStyle[0]).append(this.context.getString(R.string.comms_share_setup_link_link1), AndroidUtils.createShareLinkClickableSpan(this.context, this.viewModel.getShareLinkUrl(), R.string.comms_setup_share_title, R.attr.mosaicAction10, false));
        return spanBuilder.getValue();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.comms_share_setup_link_title);
    }

    public SpannableStringBuilder getViewGuideText() {
        SpanBuilder spanBuilder = new SpanBuilder();
        spanBuilder.append(this.context.getString(R.string.comms_share_setup_link_step2), new CharacterStyle[0]).append("\n", new CharacterStyle[0]).append(this.context.getString(R.string.comms_share_setup_link_link2), AndroidUtils.createRouteClickableSpan(this.routingService, Routes.BILOBA_COMMS_SETUP_INSTRUCTIONS, this.context, R.attr.mosaicAction10, false));
        return spanBuilder.getValue();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.context = viewGroup.getContext();
        CommsShareSetupLinkViewBinding commsShareSetupLinkViewBinding = (CommsShareSetupLinkViewBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(viewGroup.getContext())), R.layout.comms_share_setup_link_view, viewGroup, false);
        commsShareSetupLinkViewBinding.setLifecycleOwner((LifecycleOwner) this.context);
        commsShareSetupLinkViewBinding.setViewmodel(this.viewModel);
        commsShareSetupLinkViewBinding.setHandler(this);
        this.view = commsShareSetupLinkViewBinding.getRoot();
        registerViewAttributes((LinearLayout) this.view.findViewById(R.id.no_connection_banner), this.viewModel);
        return this.view;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric("CommsShareSetupLinkView", "");
        this.viewModel.create(null);
        subscribeToNetworkChange();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        com.amazon.alexa.biloba.utils.ViewUtils.interruptAccessibilityIfNeeded(this.context);
        this.viewModel.destroy();
    }
}
