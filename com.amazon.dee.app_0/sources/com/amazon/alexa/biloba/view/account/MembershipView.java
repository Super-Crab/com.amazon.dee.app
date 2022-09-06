package com.amazon.alexa.biloba.view.account;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.MembershipViewBinding;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.SpanBuilder;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
/* loaded from: classes6.dex */
public class MembershipView extends BilobaViewController {
    private static final String TAG = "MembershipView";
    private Context context;
    private View view;
    private RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    private MembershipViewModel membershipViewModel = new MembershipViewModel();

    public MembershipView(Context context) {
        this.context = context;
    }

    public SpannableStringBuilder getDeleteConnectionLinkText(String str) {
        SpanBuilder spanBuilder = new SpanBuilder();
        spanBuilder.append(this.context.getString(R.string.membership_disable_link, str), AndroidUtils.createRouteClickableSpan(this.routingService, Routes.BILOBA_MEMBERSHIP_LEAVE, this.context, R.attr.mosaicAction10, false));
        return spanBuilder.getValue();
    }

    public String getFirstLastName(CareActor careActor) {
        return careActor == null ? "" : AndroidUtils.getString(this.context, R.string.first_last_name, Objects.toString(careActor.getFirstName(), ""), Objects.toString(careActor.getLastName(), ""));
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.membership_heading);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.context = viewGroup.getContext();
        MembershipViewBinding membershipViewBinding = (MembershipViewBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.membership_view, viewGroup, false);
        membershipViewBinding.setLifecycleOwner((LifecycleOwner) this.context);
        membershipViewBinding.setViewmodel(this.membershipViewModel);
        membershipViewBinding.setMembershipView(this);
        this.view = membershipViewBinding.getRoot();
        registerViewAttributes((LinearLayout) this.view.findViewById(R.id.no_connection_banner), this.membershipViewModel);
        return this.view;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric(MetricsConstants.UserInteractionMetrics.CARE_MANAGEMENT_VIEW, "");
        subscribeToNetworkChange();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
    }
}
