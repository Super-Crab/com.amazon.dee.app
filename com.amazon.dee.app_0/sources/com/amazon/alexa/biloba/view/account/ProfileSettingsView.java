package com.amazon.alexa.biloba.view.account;

import android.content.Context;
import android.content.res.Configuration;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.ProfileSettingsBinding;
import com.amazon.alexa.biloba.membership.MembershipConstants;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.utils.SpanBuilder;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public class ProfileSettingsView extends BilobaViewController {
    private static final String ANNOTATION_KEY = "type";
    private static final String ANNOTATION_VALUE_ENDPOINT = "amazon_endpoint";
    private static final String ANNOTATION_VALUE_LINK = "subscription_link";
    private static final String COMMS_CONTACT_CARD_PARAM_PREFIX = "?contactId=";
    private static final String DROPIN_HELP_PAGE_NODE_ID = "GS3WRTSRKD2U6MCK";
    private static final String TAG = "ProfileSettingsView";
    private Context context;
    private final ProfileSettingsViewModel profileSettingsViewModel;
    private final Provider<RoutingService> routingService;
    private View view;

    public ProfileSettingsView(Configuration configuration, ComponentRegistry componentRegistry) {
        this.routingService = componentRegistry.getLazy(RoutingService.class);
        this.profileSettingsViewModel = new ProfileSettingsViewModel(configuration);
    }

    private SpannableStringBuilder buildToManageClickableSpan(String str, String str2) {
        SpannableString spannableString = new SpannableString(str);
        String amazonRetailEndpoint = this.profileSettingsViewModel.getAmazonRetailEndpoint();
        ClickableSpan createWebviewClickableSpan = AndroidUtils.createWebviewClickableSpan(this.context, WebConstants.WebviewUrls.CARE_PLUS_MANAGE_SUBSCRIPTION_URL, R.attr.mosaicAction10, false);
        ClickableSpan createWebviewClickableSpan2 = AndroidUtils.createWebviewClickableSpan(this.context, amazonRetailEndpoint, R.attr.mosaicAction10, false);
        SpannedString spannedString = (SpannedString) this.context.getText(R.string.subscription_and_payment_description);
        Annotation[] annotationArr = (Annotation[]) spannedString.getSpans(0, spannedString.length(), Annotation.class);
        if (annotationArr != null && annotationArr.length > 0) {
            for (Annotation annotation : annotationArr) {
                if (annotation.getKey().equals("type")) {
                    String value = annotation.getValue();
                    if (value.equals(ANNOTATION_VALUE_LINK)) {
                        spannableString.setSpan(createWebviewClickableSpan, spannedString.getSpanStart(annotation), spannedString.getSpanEnd(annotation), 33);
                    } else if (value.equals(ANNOTATION_VALUE_ENDPOINT)) {
                        spannableString.setSpan(createWebviewClickableSpan2, spannedString.getSpanStart(annotation), str2.length() + spannedString.getSpanStart(annotation), 33);
                    }
                }
            }
        }
        return SpannableStringBuilder.valueOf(spannableString);
    }

    private void emitRelationshipManagementMetrics() {
        if (this.profileSettingsViewModel.isMCGEnabled()) {
            recordClickMetric("SettingsCircleOfSupport", MetricsConstants.CLICK_EVENT);
        } else {
            recordClickMetric(MetricsConstants.UserInteractionMetrics.SETTINGS_ACCOUNT_DETAILS_BUTTON_EXTERNAL, MetricsConstants.CLICK_EVENT);
        }
    }

    public void maybeNavigateToStartup(CareActor careActor) {
        if (careActor == null) {
            this.profileSettingsViewModel.navigateToStartup();
        }
    }

    private void routeToContactCard(String str) {
        RoutingService.RoutingBuilder match = this.routingService.mo10268get().match(GeneratedOutlineSupport1.outline72("v2/comms/contact-card?contactId=", str));
        if (match != null) {
            match.addToBackStack().navigate();
        }
    }

    public void changeCareRecipientTimeZone(View view) {
        LogUtils.i(TAG, "Routing to timezone picker page");
        GeneratedOutlineSupport1.outline145(this.routingService.mo10268get(), Routes.TIMEZONE_PICKER);
    }

    public String getCircleOfSupportLinkText() {
        if (!this.profileSettingsViewModel.isMCGEnabled()) {
            return this.context.getString(R.string.profile_settings_care_relationship_manage);
        }
        return this.context.getString(this.profileSettingsViewModel.hasManageCareCirclePermissions() ? R.string.profile_settings_care_relationship_manage : R.string.view);
    }

    public String getCircleOfSupportSubtitle(LiveData<CareActor> liveData) {
        int i;
        if (!this.profileSettingsViewModel.isMCGEnabled()) {
            return this.context.getString(R.string.profile_settings_care_relationship_name_subheading);
        }
        if (MembershipConstants.CR.equals(this.profileSettingsViewModel.getRole())) {
            i = R.string.circle_of_support_subtitle_for_cr;
        } else {
            i = this.profileSettingsViewModel.hasManageCareCirclePermissions() ? R.string.circle_of_support_subtitle_for_cg_admin : R.string.circle_of_support_subtitle_for_cg_vor;
        }
        return this.context.getString(i, this.profileSettingsViewModel.getDisplayName(liveData));
    }

    public String getCircleOfSupportTitle(LiveData<CareActor> liveData) {
        if (!this.profileSettingsViewModel.isMCGEnabled()) {
            return this.context.getString(R.string.profile_settings_care_relationship_heading);
        }
        return this.context.getString(MembershipConstants.CR.equals(this.profileSettingsViewModel.getRole()) ? R.string.circle_of_support_title_for_cr : R.string.circle_of_support_title_for_cg, this.profileSettingsViewModel.getDisplayName(liveData));
    }

    public String getCommsSettingsLinkString(LiveData<CareActor> liveData, LiveData<CareActor> liveData2) {
        if (this.profileSettingsViewModel.isCommsEnabledForCurrentActor() == Boolean.TRUE.booleanValue()) {
            return this.context.getString(R.string.profile_settings_alexa_communications_manage);
        }
        return this.context.getString(R.string.profile_settings_alexa_communications_setup);
    }

    public String getCommsSettingsString(LiveData<CareActor> liveData, LiveData<CareActor> liveData2) {
        if (this.profileSettingsViewModel.isCommsEnabledForCurrentActor() == Boolean.TRUE.booleanValue()) {
            return this.context.getString(R.string.profile_settings_alexa_communications_primary_text_enalbed);
        }
        return this.context.getString(R.string.profile_settings_alexa_communications_primary_text_disabled);
    }

    public String getDropInStateDescription(LiveData<CareActor> liveData) {
        if (this.profileSettingsViewModel.isDropInEnabledForCareContact()) {
            return this.context.getString(R.string.dropIn_description_enabled, this.profileSettingsViewModel.getDisplayName(liveData));
        }
        return this.context.getString(R.string.dropIn_description_disabled, this.profileSettingsViewModel.getDisplayName(liveData));
    }

    public String getEmergencySettingsLinkString() {
        if (this.profileSettingsViewModel.hasUpdateEmergencyPermissions()) {
            return this.context.getString(R.string.profile_settings_alexa_communications_manage);
        }
        return this.context.getString(R.string.profile_settings_alexa_communications_view);
    }

    public String getEmergencySettingsString(Boolean bool) {
        return bool == null ? "" : this.context.getString(bool == Boolean.TRUE ? R.string.emergency_helpline_status_enabled : R.string.emergency_helpline_status_disabled);
    }

    public String getTimeZoneHeading(LiveData<CareActor> liveData, LiveData<CareActor> liveData2) {
        if (this.profileSettingsViewModel.isCareGiver() == Boolean.TRUE.booleanValue()) {
            return this.context.getString(R.string.profile_settings_timezone_heading_CG, this.profileSettingsViewModel.getDisplayName(liveData2));
        }
        if (this.profileSettingsViewModel.isMCGEnabled()) {
            return this.context.getString(R.string.profile_settings_timezone_heading_cr_mcg);
        }
        return this.context.getString(R.string.profile_settings_timezone_heading_CR);
    }

    public String getTimezoneChangeButtonText() {
        if (this.profileSettingsViewModel.shouldShowTimezonePickerButton()) {
            return this.context.getString(R.string.profile_settings_timezone_change);
        }
        return null;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.profile_settings_heading);
    }

    public SpannableStringBuilder getToManageMessageText() {
        String amazonRetailHost = this.profileSettingsViewModel.getAmazonRetailHost();
        String format = String.format(this.context.getString(R.string.subscription_and_payment_description), amazonRetailHost);
        if (!this.profileSettingsViewModel.isMCGEnabled()) {
            SpanBuilder spanBuilder = new SpanBuilder();
            spanBuilder.append(format, new CharacterStyle[0]);
            return spanBuilder.getValue();
        } else if (!this.profileSettingsViewModel.shouldShowToManageHyperLinkedMessage()) {
            return null;
        } else {
            return buildToManageClickableSpan(format, amazonRetailHost);
        }
    }

    public boolean isCommsEnabledForCurrentActor() {
        return this.profileSettingsViewModel.isCommsEnabledForCurrentActor();
    }

    public boolean isDropInEnabledForCareContact(LiveData<CareActor> liveData, LiveData<CareActor> liveData2) {
        return this.profileSettingsViewModel.isDropInEnabledForCareContact();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.context = viewGroup.getContext();
        ProfileSettingsBinding profileSettingsBinding = (ProfileSettingsBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.profile_settings, viewGroup, false);
        profileSettingsBinding.setLifecycleOwner((LifecycleOwner) this.context);
        profileSettingsBinding.setViewmodel(this.profileSettingsViewModel);
        profileSettingsBinding.setProfileSettingsView(this);
        this.view = profileSettingsBinding.getRoot();
        registerViewAttributes((LinearLayout) this.view.findViewById(R.id.no_connection_banner), this.profileSettingsViewModel);
        return this.view;
    }

    public void manageCareRelationship(View view) {
        LogUtils.i(TAG, "Routing to membership view page");
        if (this.profileSettingsViewModel.shouldShowRelationshipManagementWebview()) {
            navigateToRelationshipManagementWebview();
            emitRelationshipManagementMetrics();
            return;
        }
        recordClickMetric(MetricsConstants.UserInteractionMetrics.SETTINGS_ACCOUNT_DETAILS_BUTTON, MetricsConstants.CLICK_EVENT);
        GeneratedOutlineSupport1.outline145(this.routingService.mo10268get(), Routes.BILOBA_MEMBERSHIP_MANAGE);
    }

    public void navigateToCareContactCard(View view) {
        if (TextUtils.isEmpty(this.profileSettingsViewModel.getSlicedCareContactContactId()) == Boolean.TRUE.booleanValue()) {
            LogUtils.i(TAG, "Contact Id for the carecontact is empty, can not navigate to contact card, navigating contact list instead");
            GeneratedOutlineSupport1.outline145(this.routingService.mo10268get(), "v2/comms/contact-list");
            return;
        }
        routeToContactCard(this.profileSettingsViewModel.getSlicedCareContactContactId());
    }

    public void navigateToComms(View view) {
        if (this.profileSettingsViewModel.isCommsEnabledForCurrentActor() == Boolean.FALSE.booleanValue()) {
            this.routingService.mo10268get().match(String.format(Routes.COMMS_SETUP, RouteName.HOME)).addToBackStack().navigate();
        } else if (TextUtils.isEmpty(this.profileSettingsViewModel.getSlicedCurrentActorContactId()) == Boolean.FALSE.booleanValue()) {
            routeToContactCard(this.profileSettingsViewModel.getSlicedCurrentActorContactId());
        } else {
            LogUtils.i(TAG, "Contact Id for the current actor is empty, can not navigate to contact card, navigating contact list instead");
            GeneratedOutlineSupport1.outline145(this.routingService.mo10268get(), "v2/comms/contact-list");
        }
    }

    public void navigateToCommsHelp(View view) {
        this.routingService.mo10268get().route(RouteName.HELP).with(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, DROPIN_HELP_PAGE_NODE_ID).addToBackStack().navigate();
    }

    public void navigateToEmergencyContactsPage(View view) {
        recordClickMetric("Settings.EmergencySettingsButton", MetricsConstants.CLICK_EVENT);
        GeneratedOutlineSupport1.outline145(this.routingService.mo10268get(), this.profileSettingsViewModel.shouldShowCarePlusEmergencyView() ? Routes.BILOBA_EMERGENCY : Routes.BILOBA_EMERGENCY_CONTACT);
    }

    public void navigateToRelationshipManagementWebview() {
        AndroidUtils.startWebview(this.profileSettingsViewModel.getExternalWebViewUrl(), this.context);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric(MetricsConstants.UserInteractionMetrics.SETTINGS_VIEW, "");
        subscribeToNetworkChange();
        this.profileSettingsViewModel.getCareContact().observe((LifecycleOwner) this.context, new $$Lambda$ProfileSettingsView$ffazToOK5XqEQnN7d0mL8_kf9Z4(this));
        this.profileSettingsViewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDestroy(@NonNull Context context) {
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        this.profileSettingsViewModel.getCareContact().removeObserver(new $$Lambda$ProfileSettingsView$ffazToOK5XqEQnN7d0mL8_kf9Z4(this));
    }
}
