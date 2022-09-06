package com.amazon.alexa.biloba.view.account;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.MembershipLeaveBinding;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class MembershipLeaveView extends BilobaViewController {
    private static final String TAG = "MembershipLeaveView";
    private Context context;
    private View view;
    private RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    private MembershipViewModel viewModel = new MembershipViewModel();

    public MembershipLeaveView(Context context) {
        this.context = context;
    }

    public String getMembershipDeleteBullet1(LiveData<CareActor> liveData) {
        return this.context.getString(R.string.membership_delete_bullet_1, this.viewModel.getDisplayName(liveData));
    }

    public String getMembershipDeleteBullet2(LiveData<CareActor> liveData) {
        if (this.viewModel.isCareGiver()) {
            return this.context.getString(R.string.membership_delete_bullet_2_cg, this.viewModel.getDisplayName(liveData));
        }
        return this.context.getString(R.string.membership_delete_bullet_2_cr, this.viewModel.getDisplayName(liveData));
    }

    public String getMembershipDeleteBullet3(LiveData<CareActor> liveData) {
        if (this.viewModel.isCareGiver()) {
            return this.context.getString(R.string.membership_delete_bullet_3_cg, this.viewModel.getDisplayName(liveData));
        }
        return this.context.getString(R.string.membership_delete_bullet_3_cr, this.viewModel.getDisplayName(liveData));
    }

    public String getMembershipDeleteBullet4(LiveData<CareActor> liveData) {
        if (this.viewModel.isCareGiver()) {
            return this.context.getString(R.string.membership_delete_bullet_4_cg, this.viewModel.getDisplayName(liveData));
        }
        return this.context.getString(R.string.membership_delete_bullet_4_cr, this.viewModel.getDisplayName(liveData));
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.membership_leave_heading);
    }

    public /* synthetic */ void lambda$onConfirm$0$MembershipLeaveView(Boolean bool) {
        if (bool == Boolean.TRUE) {
            LogUtils.i(TAG, "Redirecting to delete connection success page");
            AndroidUtils.popRouteBackstackForBiloba(this.routingService);
            Context context = this.context;
            int i = R.string.membership_delete_done_headline;
            MembershipViewModel membershipViewModel = this.viewModel;
            String string = context.getString(i, membershipViewModel.getDisplayName(membershipViewModel.getCareContact()));
            recordViewMetric(MetricsConstants.UserInteractionMetrics.DELETE_CARE, MetricsConstants.SUCCESS_EVENT);
            this.viewModel.getLeaveGroupSuccess().removeObservers((LifecycleOwner) this.context);
            new ConfirmationViewModel(this.context).setIconResId(R.drawable.ic_done_inline_48dp).setIconColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction20)).setHeadlineText(string).setBodyText(this.context.getString(R.string.membership_delete_done_body)).setOkButtonText(this.context.getString(R.string.done)).setOkButtonRoute(RouteName.HOME, true).setIsSuccess(true).show();
        } else if (bool == Boolean.FALSE) {
            LogUtils.i(TAG, "Redirecting to delete connection error page");
            this.viewModel.getLeaveGroupSuccess().removeObservers((LifecycleOwner) this.context);
            new AlertDialog.Builder(this.context).setTitle(this.context.getResources().getString(R.string.membership_delete_error)).setMessage(this.context.getResources().getString(R.string.error_try_again)).setNegativeButton(R.string.ok, (DialogInterface.OnClickListener) null).show();
        } else {
            LogUtils.i(TAG, "Showing loading screen");
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        MembershipLeaveBinding membershipLeaveBinding = (MembershipLeaveBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.membership_leave, viewGroup, false);
        membershipLeaveBinding.setLifecycleOwner((LifecycleOwner) viewGroup.getContext());
        membershipLeaveBinding.setViewmodel(this.viewModel);
        membershipLeaveBinding.setHandler(this);
        this.view = membershipLeaveBinding.getRoot();
        registerViewAttributes((LinearLayout) this.view.findViewById(R.id.no_connection_banner), this.viewModel);
        return this.view;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric(MetricsConstants.UserInteractionMetrics.DELETE_CARE_VIEW, "");
        subscribeToNetworkChange();
    }

    public void onConfirm() {
        this.viewModel.getLeaveGroupSuccess().observe((LifecycleOwner) this.context, new Observer() { // from class: com.amazon.alexa.biloba.view.account.-$$Lambda$MembershipLeaveView$NOgOSkrMEgqalWvJDsZ2GKTq0eU
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MembershipLeaveView.this.lambda$onConfirm$0$MembershipLeaveView((Boolean) obj);
            }
        });
        this.viewModel.leaveGroup();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
    }
}
