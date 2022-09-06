package com.amazon.alexa.biloba.view.comms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.EmergencyViewBinding;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.generated.models.EmergencyAddress;
import com.amazon.alexa.biloba.generated.models.EmergencyContact;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Objects;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class EmergencyView extends BilobaViewController {
    private static final String TAG = "EmergencyView";
    private Context context;
    private EmergencyViewModel emergencyViewModel;
    private RoutingService routingService;
    @Inject
    Lazy<UrlHelper> urlHelper;

    public EmergencyView(Context context, ComponentRegistry componentRegistry) {
        BilobaDependencies.inject(this);
        this.context = context;
        this.routingService = (RoutingService) componentRegistry.get(RoutingService.class).get();
        this.emergencyViewModel = new EmergencyViewModel(NotificationManagerCompat.from(context), context);
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

    public String getCareGiverLinkText(boolean z, boolean z2, boolean z3) {
        if (!z || !z3) {
            return null;
        }
        return this.context.getString(z2 ? R.string.add_link : R.string.edit_link);
    }

    public String getEmergencyAddressChangeButtonText() {
        if (!this.emergencyViewModel.shouldShowChangeEmergencyAddressButton()) {
            return null;
        }
        return this.emergencyViewModel.isEmergencyAddressSet() ? this.context.getString(R.string.edit_link) : this.context.getString(R.string.add_link);
    }

    public String getEmergencyAddressLineOne(EmergencyAddress emergencyAddress) {
        if (emergencyAddress != null && emergencyAddress.getAddressLine1() != null) {
            return emergencyAddress.getAddressLine1();
        }
        return this.context.getString(R.string.emergency_helpline_not_set);
    }

    public String getEmergencyContactDisplayName(EmergencyContact emergencyContact) {
        if (emergencyContact == null) {
            return this.context.getString(R.string.emergency_helpline_not_set);
        }
        return AndroidUtils.getString(this.context, R.string.first_last_name, Objects.toString(emergencyContact.getFirstName(), ""), Objects.toString(emergencyContact.getLastName(), ""));
    }

    public float getEmergencyHelplineOpacity(boolean z, boolean z2) {
        int i;
        if (z && !z2) {
            i = R.fraction.emergency_helpline_disabled_opacity;
        } else {
            i = R.fraction.emergency_helpline_enabled_opacity;
        }
        return this.context.getResources().getFraction(i, 1, 1);
    }

    public String getEmergencyHelplineSecondaryText(boolean z, boolean z2) {
        int i;
        if (z && !z2) {
            i = R.string.emergency_helpline_status_no_subscription_secondary;
        } else {
            i = R.string.emergency_helpline_status;
        }
        return this.context.getString(i);
    }

    public String getEmergencyHelplineStatus(boolean z, boolean z2, boolean z3) {
        int i;
        if (z && !z2) {
            i = R.string.emergency_helpline_status_no_subscription;
        } else {
            i = z3 ? R.string.emergency_helpline_status_enabled : R.string.emergency_helpline_status_disabled;
        }
        return this.context.getString(i);
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
        EmergencyViewBinding emergencyViewBinding = (EmergencyViewBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.emergency_view, viewGroup, false);
        emergencyViewBinding.setLifecycleOwner((LifecycleOwner) this.context);
        emergencyViewBinding.setViewmodel(this.emergencyViewModel);
        emergencyViewBinding.setHandler(this);
        View root = emergencyViewBinding.getRoot();
        registerViewAttributes((LinearLayout) root.findViewById(R.id.no_connection_banner), this.emergencyViewModel);
        return root;
    }

    public void navigateToComms(@NonNull View view) {
        recordClickMetric("EmergencySettings.CommsSetupButton", MetricsConstants.CLICK_EVENT);
        this.routingService.match(String.format(Routes.COMMS_SETUP, RouteName.HOME)).addToBackStack().navigate();
    }

    public void navigateToEmergencyContact(boolean z) {
        RoutingService.RoutingBuilder addToBackStack = this.routingService.route("v2/comms/emergency-contact-selector").with("shouldReturnBackOnComplete", true).addToBackStack();
        if (this.emergencyViewModel.getIsCareGiver().getValue() == Boolean.TRUE) {
            if (this.emergencyViewModel.getCareRecipient().getValue() == null) {
                LogUtils.e(TAG, "Care Recipient is null");
                return;
            }
            addToBackStack = addToBackStack.with("primarySourceId", this.emergencyViewModel.getCareRecipient().getValue().getCommsId());
        }
        if (z) {
            if (this.emergencyViewModel.getEmergencyContact().getValue() == null) {
                LogUtils.e(TAG, "Emergency Contact is null");
                return;
            }
            addToBackStack = addToBackStack.with("externalSelectedContactCommsId", this.emergencyViewModel.getEmergencyContact().getValue().getCommsId());
        }
        addToBackStack.navigate();
    }

    public void navigateToNotificationSettings(@NonNull View view) {
        recordClickMetric(MetricsConstants.UserInteractionMetrics.ES_ENABLE_NOTIFICATIONS, MetricsConstants.CLICK_EVENT);
        AndroidUtils.navigateToGlobalNotificationSettings(this.context);
    }

    public void navigateToShareSetupLink(@NonNull View view) {
        recordClickMetric("EmergencySettings.CommsSetupLink", MetricsConstants.CLICK_EVENT);
        GeneratedOutlineSupport1.outline145(this.routingService, Routes.BILOBA_COMMS_SHARE_SETUP_LINK);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric(MetricsConstants.UserInteractionMetrics.EMERGENCY_CONTACT_VIEW, "");
        subscribeToNetworkChange();
        this.emergencyViewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
    }

    public boolean shouldEnableButton(float f) {
        return f == this.context.getResources().getFraction(R.fraction.emergency_helpline_enabled_opacity, 1, 1);
    }

    public void startEmergencyAddressWebview(@NonNull View view) {
        boolean z = true;
        boolean z2 = this.emergencyViewModel.getIsCareGiver().getValue() == Boolean.FALSE;
        if (this.emergencyViewModel.getHasSubscription().getValue() != Boolean.TRUE) {
            z = false;
        }
        if (!shouldEnableButton(getEmergencyHelplineOpacity(z2, z)) || !this.emergencyViewModel.shouldShowChangeEmergencyAddressButton()) {
            return;
        }
        recordClickMetric(this.emergencyViewModel.isEmergencyAddressSet() ? MetricsConstants.UserInteractionMetrics.ES_CHANGE_EMERGENCY_ADDRESS_BUTTON : MetricsConstants.UserInteractionMetrics.ES_ADD_EMERGENCY_ADDRESS_BUTTON, MetricsConstants.CLICK_EVENT);
        AndroidUtils.startWebview(this.urlHelper.mo358get().getEditEmergencyAddressUrl(this.context.getResources().getConfiguration()), this.context);
    }

    @VisibleForTesting
    EmergencyView(Context context, RoutingService routingService, EmergencyViewModel emergencyViewModel) {
        this.context = context;
        this.routingService = routingService;
        this.emergencyViewModel = emergencyViewModel;
    }
}
