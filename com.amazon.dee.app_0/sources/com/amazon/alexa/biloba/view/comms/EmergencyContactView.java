package com.amazon.alexa.biloba.view.comms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.EmergencyContactViewBinding;
import com.amazon.alexa.biloba.generated.models.EmergencyContact;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
/* loaded from: classes6.dex */
public class EmergencyContactView extends BilobaViewController {
    private static final String TAG = "EmergencyContactView";
    private Context context;
    private EmergencyContactViewModel emergencyContactViewModel;
    private RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);

    public EmergencyContactView(Context context) {
        this.context = context;
        this.emergencyContactViewModel = new EmergencyContactViewModel(NotificationManagerCompat.from(context));
    }

    public void addEmergencyContact(@NonNull View view) {
        recordClickMetric(MetricsConstants.UserInteractionMetrics.ES_ADD_EMERGENCY_CONTACT_BUTTON, MetricsConstants.CLICK_EVENT);
        navigateToEmergencyContact(false);
    }

    public void changeEmergencyContact(@NonNull View view) {
        recordClickMetric(MetricsConstants.UserInteractionMetrics.ES_CHANGE_EMERGENCY_CONTACT_BUTTON, MetricsConstants.CLICK_EVENT);
        navigateToEmergencyContact(false);
    }

    public void changeEmergencyContactNumber(@NonNull View view) {
        recordClickMetric(MetricsConstants.UserInteractionMetrics.ES_EDIT_EMERGENCY_CONTACT_LINK, MetricsConstants.CLICK_EVENT);
        navigateToEmergencyContact(true);
    }

    public String getFirstLastName(EmergencyContact emergencyContact) {
        return emergencyContact == null ? "" : AndroidUtils.getString(this.context, R.string.first_last_name, Objects.toString(emergencyContact.getFirstName(), ""), Objects.toString(emergencyContact.getLastName(), ""));
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.emergency_heading);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.context = viewGroup.getContext();
        EmergencyContactViewBinding emergencyContactViewBinding = (EmergencyContactViewBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.emergency_contact_view, viewGroup, false);
        emergencyContactViewBinding.setLifecycleOwner((LifecycleOwner) this.context);
        emergencyContactViewBinding.setViewmodel(this.emergencyContactViewModel);
        emergencyContactViewBinding.setHandler(this);
        View root = emergencyContactViewBinding.getRoot();
        registerViewAttributes((LinearLayout) root.findViewById(R.id.no_connection_banner), this.emergencyContactViewModel);
        return root;
    }

    public void navigateToComms(@NonNull View view) {
        this.routingService.match(String.format(Routes.COMMS_SETUP, RouteName.HOME)).addToBackStack().navigate();
    }

    public void navigateToEmergencyContact(boolean z) {
        RoutingService.RoutingBuilder addToBackStack = this.routingService.route("v2/comms/emergency-contact-selector").with("shouldReturnBackOnComplete", true).addToBackStack();
        if (this.emergencyContactViewModel.getIsCareGiver().getValue() == Boolean.TRUE) {
            if (this.emergencyContactViewModel.getCareRecipient().getValue() == null) {
                LogUtils.e(TAG, "Care Recipient is null");
                return;
            }
            addToBackStack = addToBackStack.with("primarySourceId", this.emergencyContactViewModel.getCareRecipient().getValue().getCommsId());
        }
        if (z) {
            if (this.emergencyContactViewModel.getEmergencyContact().getValue() == null) {
                LogUtils.e(TAG, "Emergency Contact is null");
                return;
            }
            addToBackStack = addToBackStack.with("externalSelectedContactCommsId", this.emergencyContactViewModel.getEmergencyContact().getValue().getCommsId());
        }
        addToBackStack.navigate();
    }

    public void navigateToNotificationSettings(@NonNull View view) {
        AndroidUtils.navigateToGlobalNotificationSettings(this.context);
    }

    public void navigateToShareSetupLink(@NonNull View view) {
        GeneratedOutlineSupport1.outline145(this.routingService, Routes.BILOBA_COMMS_SHARE_SETUP_LINK);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric("EmergencyContactView", "");
        subscribeToNetworkChange();
        this.emergencyContactViewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
    }
}
