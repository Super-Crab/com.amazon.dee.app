package com.amazon.alexa.biloba.view.alerts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.AlertManagementBinding;
import com.amazon.alexa.biloba.generated.models.Message;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.routing.RouteArgumentKeys;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.service.AlertConfigurationException;
import com.amazon.alexa.biloba.storage.DevicesStore;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsViewModel;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.biloba.view.common.recycler.RecyclerViewAdapter;
import com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.mosaic.view.NoticeBannerView;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public class AlertSettingsListView extends BilobaViewController implements AlertListItemClickListener {
    private static final String TAG = "AlertSettingsListView";
    private RecyclerViewAdapter alertsListAdapter;
    private View alertsListView;
    private final Context context;
    private NoticeBannerView permissionsNoticeBannerView;
    private Observer<Throwable> errorObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.alerts.-$$Lambda$AlertSettingsListView$qZRUNh617X603ZHzu_tr_nHnxaE
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            AlertSettingsListView.this.lambda$new$0$AlertSettingsListView((Throwable) obj);
        }
    };
    private Observer<Map<String, Message>> deviceMessagesObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.alerts.-$$Lambda$AlertSettingsListView$3iHYKqLSQpKkAd2vbk8JvYQRYOc
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            AlertSettingsListView.this.lambda$new$1$AlertSettingsListView((Map) obj);
        }
    };
    private Observer<List<BaseRecyclerItem>> alertItemsObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.alerts.-$$Lambda$AlertSettingsListView$v6PCRShwrxLwh8AzA_TsnIJMtFs
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            AlertSettingsListView.this.lambda$new$4$AlertSettingsListView((List) obj);
        }
    };
    private Observer<Boolean> loadingObserver = $$Lambda$AlertSettingsListView$9nUmMa1KMslo1vq7Lp1U6BF8Xc8.INSTANCE;
    private final RoutingService routingService = (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    private AlertsListViewModel viewModel = new AlertsListViewModel();

    public AlertSettingsListView(@NonNull Context context, @Nullable Bundle bundle) {
        this.context = context;
        this.viewModel.create(bundle);
    }

    private void checkAndPromptForNotificationPermissions() {
        View view = this.alertsListView;
        if (view == null) {
            return;
        }
        this.permissionsNoticeBannerView = (NoticeBannerView) view.findViewById(R.id.alert_notifications_disabled);
        if (this.permissionsNoticeBannerView == null) {
            return;
        }
        if (!NotificationManagerCompat.from(this.context).areNotificationsEnabled()) {
            LogUtils.w(TAG, "Notifications are disabled");
            this.permissionsNoticeBannerView.setPrimaryText(this.context.getString(R.string.alert_notification_permissions_message));
            this.permissionsNoticeBannerView.setLinkText(this.context.getString(R.string.alert_notification_enable_permissions_link));
            this.permissionsNoticeBannerView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.alerts.-$$Lambda$AlertSettingsListView$0bcVkiJrkANhtjBsJ-nIeGWc0xk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AlertSettingsListView.this.lambda$checkAndPromptForNotificationPermissions$6$AlertSettingsListView(view2);
                }
            });
            this.permissionsNoticeBannerView.setVisibility(0);
            return;
        }
        LogUtils.d(TAG, "Notifications are enabled");
        this.permissionsNoticeBannerView.setVisibility(8);
    }

    private void displayDownloadErrorScreen() {
        new ConfirmationViewModel(this.context).setIconResId(R.drawable.ic_mosaic_notifications_off).setIconColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10)).setIconContentDescription(this.context.getString(R.string.alerts_off_icon)).setHeadlineText(this.context.getString(R.string.error_all_alerts_headline)).setBodyText(this.context.getString(R.string.error_all_alerts_body)).setLinkText(this.context.getString(R.string.error_tap_to_retry)).setLinkRoute(Routes.BILOBA_ALERTS_MANAGE).show();
    }

    private void displayErrorDialog(String str) {
        new AlertDialog.Builder(this.context).setMessage(this.context.getString(R.string.error_try_again)).setTitle(str).setPositiveButton(this.context.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.biloba.view.alerts.-$$Lambda$AlertSettingsListView$BrXhBLmdiqACEENLnxU2pThpltM
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AlertSettingsListView.this.lambda$displayErrorDialog$7$AlertSettingsListView(dialogInterface, i);
            }
        }).create().show();
    }

    private void displayNoDevicesBanner(Message message) {
        NoticeBannerView noticeBannerView = (NoticeBannerView) this.alertsListView.findViewById(R.id.alert_no_devices);
        if (noticeBannerView == null) {
            LogUtils.w(TAG, "The noDevicesBanner is null, cannot alert about device status.");
        } else if (message != null) {
            LogUtils.d(TAG, "showing the \"no devices\" banner");
            noticeBannerView.setPrimaryText(message.getLocalizedPrimaryMessage());
            noticeBannerView.setVisibility(0);
        } else {
            LogUtils.d(TAG, "hiding the \"no devices\" banner");
            noticeBannerView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$5(Boolean bool) {
        String str = TAG;
        LogUtils.d(str, "still loading=" + bool);
    }

    public String getAlertWarningCrMessage() {
        Context context = this.context;
        int i = R.string.alert_notification_cr_message;
        AlertsListViewModel alertsListViewModel = this.viewModel;
        return context.getString(i, alertsListViewModel.getDisplayName(alertsListViewModel.getCareContact()));
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.alerts_heading);
    }

    public /* synthetic */ void lambda$checkAndPromptForNotificationPermissions$6$AlertSettingsListView(View view) {
        LogUtils.d(TAG, "opening permissions page in settings");
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this.context.getPackageName(), null));
        this.context.startActivity(intent);
    }

    public /* synthetic */ void lambda$displayErrorDialog$7$AlertSettingsListView(DialogInterface dialogInterface, int i) {
        this.routingService.route(Routes.BILOBA_ALERTS_MANAGE).navigate();
    }

    public /* synthetic */ void lambda$new$0$AlertSettingsListView(Throwable th) {
        if (th == null) {
            return;
        }
        if (th.getClass().equals(AlertConfigurationException.class)) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got this error:\n");
            outline107.append(th.getLocalizedMessage());
            LogUtils.e(str, outline107.toString());
            int errorCode = ((AlertConfigurationException) th).getErrorCode();
            if (errorCode == -3) {
                LogUtils.e(TAG, "Got the UPDATE_FAILED error");
                displayErrorDialog(this.context.getString(R.string.error_alert_update_failure_body));
                return;
            } else if (errorCode != -2 && errorCode != -1) {
                String str2 = TAG;
                LogUtils.e(str2, "Got some other error, code=" + errorCode);
                displayDownloadErrorScreen();
                return;
            } else {
                String str3 = TAG;
                LogUtils.e(str3, "Got a download failure error, code=" + errorCode);
                displayDownloadErrorScreen();
                return;
            }
        }
        String str4 = TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("unknown exception was forwarded to the view: ");
        outline1072.append(th.getMessage());
        LogUtils.e(str4, outline1072.toString());
        displayDownloadErrorScreen();
    }

    public /* synthetic */ void lambda$new$1$AlertSettingsListView(Map map) {
        if (map == null) {
            LogUtils.e(TAG, "Bad messages response!");
            return;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received ");
        outline107.append(map.keySet().size());
        outline107.append(" messages about devices.");
        sb.append(outline107.toString());
        sb.append("Available messages are:\n");
        for (String str : map.keySet()) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("key=\"", str, "\", value=\"");
            outline115.append(map.get(str));
            outline115.append("\"\n");
            sb.append(outline115.toString());
        }
        LogUtils.v(TAG, sb.toString());
        if (map.containsKey(DevicesStore.MESSAGE_RESPONSE_KEY_ONLY_MOBILE_DEVICES)) {
            displayNoDevicesBanner((Message) map.get(DevicesStore.MESSAGE_RESPONSE_KEY_ONLY_MOBILE_DEVICES));
        } else {
            displayNoDevicesBanner((Message) map.get(DevicesStore.MESSAGE_RESPONSE_KEY_NO_DEVICES));
        }
    }

    public /* synthetic */ void lambda$new$4$AlertSettingsListView(List list) {
        if (list == null || list.isEmpty()) {
            if (list == null) {
                return;
            }
            LogUtils.w(TAG, "Got an empty alert configuration list");
            displayDownloadErrorScreen();
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received ");
        outline107.append(list.size());
        outline107.append(" alert configs");
        LogUtils.d(str, outline107.toString());
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            AlertConfigRecyclerItem alertConfigRecyclerItem = (AlertConfigRecyclerItem) it2.next();
            final AlertConfigurationViewItemModel data = alertConfigRecyclerItem.getData();
            alertConfigRecyclerItem.setClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.alerts.-$$Lambda$AlertSettingsListView$jkXisdpW0-O7pPh8SW94reTT_l0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AlertSettingsListView.this.lambda$null$2$AlertSettingsListView(data, view);
                }
            });
            if (this.viewModel.isCareGiver() == Boolean.TRUE.booleanValue()) {
                alertConfigRecyclerItem.setEnableDisableSwitchListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.alexa.biloba.view.alerts.-$$Lambda$AlertSettingsListView$gjHk54tSiq4nAwlCIStetUYE1T0
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        AlertSettingsListView.this.lambda$null$3$AlertSettingsListView(data, compoundButton, z);
                    }
                });
                alertConfigRecyclerItem.setAlertSwitchEnabled(true);
            } else {
                alertConfigRecyclerItem.setAlertSwitchEnabled(false);
            }
        }
        this.alertsListAdapter.updateItems(list);
        this.alertsListAdapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$null$2$AlertSettingsListView(AlertConfigurationViewItemModel alertConfigurationViewItemModel, View view) {
        onEditButtonClicked(alertConfigurationViewItemModel);
    }

    public /* synthetic */ void lambda$null$3$AlertSettingsListView(AlertConfigurationViewItemModel alertConfigurationViewItemModel, CompoundButton compoundButton, boolean z) {
        onEnableDisableSwitchClicked(alertConfigurationViewItemModel, z);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        LogUtils.d(TAG, "makeView");
        AlertManagementBinding alertManagementBinding = (AlertManagementBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.alert_management, viewGroup, false);
        alertManagementBinding.setLifecycleOwner((LifecycleOwner) viewGroup.getContext());
        alertManagementBinding.setViewmodel(this.viewModel);
        alertManagementBinding.setHandler(this);
        this.alertsListView = alertManagementBinding.getRoot();
        RecyclerView recyclerView = (RecyclerView) this.alertsListView.findViewById(R.id.alerts_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        LogUtils.d(TAG, "setting layout manage to  alertsRecyclerView");
        recyclerView.setLayoutManager(linearLayoutManager);
        LogUtils.d(TAG, "adding decoration to alertsRecyclerView");
        recyclerView.addItemDecoration(new DividerItemDecoration(this.context, linearLayoutManager.getOrientation()));
        this.alertsListAdapter = new RecyclerViewAdapter();
        this.alertsListAdapter.setHasStableIds(true);
        SimpleItemAnimator simpleItemAnimator = (SimpleItemAnimator) recyclerView.getItemAnimator();
        if (simpleItemAnimator != null) {
            simpleItemAnimator.setSupportsChangeAnimations(false);
        }
        recyclerView.setAdapter(this.alertsListAdapter);
        registerViewAttributes((LinearLayout) this.alertsListView.findViewById(R.id.no_connection_banner), this.viewModel);
        return this.alertsListView;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric(MetricsConstants.UserInteractionMetrics.ALERT_SETTINGS_VIEW, "");
        LogUtils.d(TAG, "onAttach");
        subscribeToNetworkChange();
        checkAndPromptForNotificationPermissions();
        this.viewModel.getDeviceMessages().observe((LifecycleOwner) this.context, this.deviceMessagesObserver);
        this.viewModel.getAlertItems().observe((LifecycleOwner) this.context, this.alertItemsObserver);
        this.viewModel.getError().observe((LifecycleOwner) this.context, this.errorObserver);
        this.viewModel.getIsLoading().observe((LifecycleOwner) this.context, this.loadingObserver);
        this.viewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        this.viewModel.getError().removeObserver(this.errorObserver);
        this.viewModel.getDeviceMessages().removeObserver(this.deviceMessagesObserver);
        this.viewModel.getAlertItems().removeObserver(this.alertItemsObserver);
        this.viewModel.getIsLoading().removeObserver(this.loadingObserver);
    }

    @Override // com.amazon.alexa.biloba.view.alerts.AlertListItemClickListener
    public void onEditButtonClicked(AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Alert configuration edit button was clicked for alert config with ID=");
        outline107.append(alertConfigurationViewItemModel.getId());
        LogUtils.d(str, outline107.toString());
        Bundle bundle = new Bundle();
        bundle.putString(AlertSettingsViewModel.ALERT_CONFIG_ID, alertConfigurationViewItemModel.getId());
        this.routingService.route(Routes.BILOBA_ALERT_FORM).with(RouteArgumentKeys.BUNDLE, bundle).addToBackStack().navigate();
    }

    @Override // com.amazon.alexa.biloba.view.alerts.AlertListItemClickListener
    public void onEnableDisableSwitchClicked(AlertConfigurationViewItemModel alertConfigurationViewItemModel, boolean z) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Alert configuration edit/disable clicked for alert config with ID=");
        outline107.append(alertConfigurationViewItemModel.getId());
        outline107.append("\nNew state = : ");
        outline107.append(z ? "enabled" : FeatureState.DISABLED);
        LogUtils.d(str, outline107.toString());
        recordClickMetric(MetricsConstants.UserInteractionMetrics.ES_ENABLE_NOTIFICATIONS, MetricsConstants.CLICK_EVENT);
        alertConfigurationViewItemModel.setEnabled(z);
        this.viewModel.updateAlertConfig(alertConfigurationViewItemModel);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onRestoreViewState(@NonNull View view, @NonNull Bundle bundle) {
        LogUtils.d(TAG, "onRestoreViewState");
    }

    @Override // com.amazon.alexa.biloba.view.common.ListItemClickListener
    public void onListItemClicked(AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Alert configuration was clicked: ");
        outline107.append(alertConfigurationViewItemModel.toString());
        LogUtils.d(str, outline107.toString());
    }
}
