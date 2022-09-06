package com.amazon.alexa.biloba.view.dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.generated.models.Card;
import com.amazon.alexa.biloba.generated.models.LocalizedDateTime;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.Action;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.utils.RemoteAssistHelper;
import com.amazon.alexa.biloba.view.cards.DashboardBottomSheet;
import com.amazon.alexa.biloba.view.cards.DashboardCard;
import com.amazon.alexa.biloba.view.cards.NotificationCard;
import com.amazon.alexa.biloba.view.cards.TipsCard;
import com.amazon.alexa.biloba.view.emergencyHelpline.EmergencyHelplineRoutingHelper;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableMap;
import dagger.Lazy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class CardTransformer extends BilobaViewWithMetrics {
    private static final ImmutableMap<Action.TargetEnum, String> ROUTE_NAMES = new ImmutableMap.Builder().mo7828put(Action.TargetEnum.SETUP_TIMEZONE, Routes.BILOBA_PROFILE_SETTINGS).mo7828put(Action.TargetEnum.LEARN_NOTIFICATION, Routes.BILOBA_ALERTS_MANAGE).mo7828put(Action.TargetEnum.SETUP_NOTIFICATION, Routes.BILOBA_ALERTS_MANAGE).mo7828put(Action.TargetEnum.SETUP_EC, Routes.BILOBA_EMERGENCY_CONTACT).mo7828put(Action.TargetEnum.LEARN_MORE_EC, Routes.BILOBA_EMERGENCY).mo7828put(Action.TargetEnum.FINISH_COMMS_SETUP, Routes.BILOBA_FINISH_COMMS_SETUP).mo7828put(Action.TargetEnum.FINISH_EMERGENCY_ADDRESS_SETUP, Routes.BILOBA_FINISH_EMERGENCY_ADDRESS_SETUP).mo7828put(Action.TargetEnum.FINISH_CARE_PLUS_SETUP, Routes.BILOBA_FINISH_EMERGENCY_ADDRESS_SETUP).mo7828put(Action.TargetEnum.ENABLE_REMOTE_MANAGEMENT, "biloba/external/dashboard").mo7828put(Action.TargetEnum.REMOTE_MANAGEMENT, Routes.REMOTE_MANAGEMENT_ROUTE).mo7828put(Action.TargetEnum.TEST_EMERGENCY_HELPLINE, Routes.BILOBA_TEST_EMERGENCY_HELPLINE).mo7828put(Action.TargetEnum.CARE_PLUS_UPSELL, Routes.BILOBA_CARE_PLUS_UPSELL).mo7828put(Action.TargetEnum.INVITE_CARE_GIVER, Routes.BILOBA_INVITE_CARE_GIVER).mo7828put(Action.TargetEnum.CARE_PLUS_WELCOME, Routes.BILOBA_CARE_PLUS_WELCOME).mo7828put(Action.TargetEnum.EMERGENCY_HELPLINE_CALL_ENDED, Routes.BILOBA_EMERGENCY_HELPLINE_CALL_ENDED).mo7828put(Action.TargetEnum.EMERGENCY_HELPLINE_CALL_STARTED, Routes.BILOBA_EMERGENCY_HELPLINE_CALL_STARTED).mo7828put(Action.TargetEnum.VERIFIED_AND_UNVERIFIED_FALL, Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_AND_UNVERIFIED_FALL).mo7828put(Action.TargetEnum.VERIFIED_NO_FALL, Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_NO_FALL).mo7826build();
    private static final String TAG = "CardTransformer";
    private final Lazy<CareActorsStore> careActorsStore;
    private final Lazy<CommsHandler> commsHandler;
    private final Lazy<EmergencyHelplineRoutingHelper> emergencyHelplineRoutingHelper;
    private final Lazy<RemoteAssistHelper> remoteAssistHelper;
    private final Lazy<RoutingService> routingService;

    @Inject
    public CardTransformer(Lazy<CareActorsStore> lazy, Lazy<RoutingService> lazy2, Lazy<EmergencyHelplineRoutingHelper> lazy3, Lazy<RemoteAssistHelper> lazy4, Lazy<CommsHandler> lazy5) {
        this.careActorsStore = lazy;
        this.commsHandler = lazy5;
        this.routingService = lazy2;
        this.emergencyHelplineRoutingHelper = lazy3;
        this.remoteAssistHelper = lazy4;
    }

    private View.OnClickListener getClickListenerForCall(final Card card, final String str) {
        return new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$CardTransformer$LIA_CDm-f7AeJcDr6XzmtGJCBO0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardTransformer.this.lambda$getClickListenerForCall$1$CardTransformer(card, str, view);
            }
        };
    }

    private View.OnClickListener getClickListenerForDropIn(final Card card, final String str) {
        return new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$CardTransformer$7r_zKX_n8u52Kzz3vnHp2EmCGAE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardTransformer.this.lambda$getClickListenerForDropIn$2$CardTransformer(card, str, view);
            }
        };
    }

    private Map<Action, View.OnClickListener> getClickListenersForActions(List<Action> list, Card card, String str) {
        HashMap hashMap = new HashMap();
        for (Action action : list) {
            if (!isValidAction(action)) {
                BilobaMetricsService bilobaMetricsService = this.bilobaMetricsService;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SkippedCard.");
                outline107.append(card.getType());
                bilobaMetricsService.recordCounter(outline107.toString());
                return null;
            } else if (Action.TypeEnum.ROUTE.equals(action.getType())) {
                hashMap.put(action, getClickListenerForRoute(getRouteName(action.getTarget()), card, str));
            } else if (Action.TypeEnum.COMMAND.equals(action.getType())) {
                if (Action.TargetEnum.INITIATE_CALL.equals(action.getTarget())) {
                    hashMap.put(action, getClickListenerForCall(card, str));
                } else if (Action.TargetEnum.INITIATE_DROPIN.equals(action.getTarget())) {
                    hashMap.put(action, getClickListenerForDropIn(card, str));
                } else if (Action.TargetEnum.INITIATE_REMOTE_ASSIST.equals(action.getTarget())) {
                    hashMap.put(action, getClickListenerForRemoteAssist(card, str));
                }
            }
        }
        return hashMap;
    }

    private String getTimeString(LocalizedDateTime localizedDateTime) {
        return localizedDateTime != null ? AndroidUtils.getFormattedTimeString(localizedDateTime.getDate(), localizedDateTime.getTime(), localizedDateTime.getZone()) : "";
    }

    private boolean isValidAction(Action action) {
        if (action == null || action.getType() == null || action.getTarget() == null) {
            return false;
        }
        return !action.getType().equals(Action.TypeEnum.ROUTE) || getRouteName(action.getTarget()) != null;
    }

    protected void displayErrorDialog(Context context, String str) {
        LogUtils.i(TAG, "Display alert dialog because Call/Drop In is not available");
        new AlertDialog.Builder(context).setMessage(context.getString(R.string.comms_dialog_body)).setTitle(str).setPositiveButton(context.getString(R.string.comms_dialog_setup), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$CardTransformer$LMTrRn3gXGPoQBIXzpIh708nAnQ
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CardTransformer.this.lambda$displayErrorDialog$5$CardTransformer(dialogInterface, i);
            }
        }).setNegativeButton(context.getString(R.string.comms_dialog_close), (DialogInterface.OnClickListener) null).create().show();
    }

    @VisibleForTesting
    Activity getActivity(View view) {
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    @VisibleForTesting
    View.OnClickListener getClickListenerForDismiss(final String str, final SharedPreferences sharedPreferences) {
        if (sharedPreferences == null) {
            return null;
        }
        return new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$CardTransformer$RWavwgu655umIOQLBtqQiEM0XjU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardTransformer.this.lambda$getClickListenerForDismiss$4$CardTransformer(str, sharedPreferences, view);
            }
        };
    }

    @VisibleForTesting
    View.OnClickListener getClickListenerForRemoteAssist(final Card card, final String str) {
        return new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$CardTransformer$OkMK5KSKKokYPNO1TJhtyuhp6r8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardTransformer.this.lambda$getClickListenerForRemoteAssist$3$CardTransformer(card, str, view);
            }
        };
    }

    @VisibleForTesting
    View.OnClickListener getClickListenerForRoute(final String str, final Card card, final String str2) {
        return new View.OnClickListener() { // from class: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$CardTransformer$QzwDiHYhoiz8XAaNqL0-l4yw2Pc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardTransformer.this.lambda$getClickListenerForRoute$0$CardTransformer(card, str2, str, view);
            }
        };
    }

    @VisibleForTesting
    String getRouteName(Action.TargetEnum targetEnum) {
        return ROUTE_NAMES.mo7740get(targetEnum);
    }

    public /* synthetic */ void lambda$displayErrorDialog$5$CardTransformer(DialogInterface dialogInterface, int i) {
        GeneratedOutlineSupport1.outline145(this.routingService.mo358get(), Routes.BILOBA_EMERGENCY_CONTACT);
    }

    public /* synthetic */ void lambda$getClickListenerForCall$1$CardTransformer(Card card, String str, View view) {
        recordCardsClickMetric(card, str);
        if (this.careActorsStore.mo358get().isCommsProvisionedWithContactId()) {
            LogUtils.i(TAG, "Publishing message for CALL");
            recordCommsCall(MetricsConstants.UserInteractionMetrics.ALERT_CARD_COMMS_CALL_BUTTON, "");
            this.commsHandler.mo358get().withMessage(CommsHandler.INITIATE_CALL).withPayload(this.careActorsStore.mo358get().getSlicedCareContactContactId()).publish();
            return;
        }
        Context context = view.getContext();
        if (context == null) {
            return;
        }
        displayErrorDialog(context, context.getString(R.string.comms_dialog_title_call));
    }

    public /* synthetic */ void lambda$getClickListenerForDismiss$4$CardTransformer(String str, SharedPreferences sharedPreferences, View view) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity(view);
        if (appCompatActivity != null) {
            new DashboardBottomSheet(str, sharedPreferences).show(appCompatActivity.getSupportFragmentManager(), "care_bottom_sheet");
        }
    }

    public /* synthetic */ void lambda$getClickListenerForDropIn$2$CardTransformer(Card card, String str, View view) {
        recordCardsClickMetric(card, str);
        if (this.careActorsStore.mo358get().isCommsProvisionedWithCommsId() && this.careActorsStore.mo358get().isDropInEnabledForCareContact()) {
            LogUtils.i(TAG, "Publishing message for INITIATE_DROP_IN");
            recordCommsCall(MetricsConstants.UserInteractionMetrics.ALERT_CARD_COMMS_DROP_IN_BUTTON, "");
            this.commsHandler.mo358get().withMessage(CommsHandler.INITITATE_DROP_IN).withPayload(this.careActorsStore.mo358get().getCareContact().getValue().getCommsId()).publish();
            return;
        }
        Context context = view.getContext();
        if (context == null) {
            return;
        }
        displayErrorDialog(context, context.getString(R.string.comms_dialog_title_dropIn));
    }

    public /* synthetic */ void lambda$getClickListenerForRemoteAssist$3$CardTransformer(Card card, String str, View view) {
        recordCardsClickMetric(card, str);
        this.remoteAssistHelper.mo358get().startRemoteAssist(view.getContext());
    }

    public /* synthetic */ void lambda$getClickListenerForRoute$0$CardTransformer(Card card, String str, String str2, View view) {
        String str3 = "";
        recordClickMetric(MetricsConstants.UserInteractionMetrics.ALERT_CARD_ACTION_BUTTON, str3);
        recordCardsClickMetric(card, str);
        if (str2 != null) {
            if (this.emergencyHelplineRoutingHelper.mo358get().isEmergencyHelplineRoute(str2)) {
                String timeString = getTimeString(card.getLocalizedDateTime());
                if (card.getDeviceName() != null) {
                    str3 = card.getDeviceName();
                }
                this.routingService.mo358get().route(str2).with(EmergencyHelplineRoutingHelper.EMERGENCY_HELPLINE_CRNAME_PARAMETER_KEY, CareActorUtil.getDisplayName(this.careActorsStore.mo358get().getCareRecipient().getValue())).with("time", timeString).with("device_name", str3).addToBackStack().navigate();
                return;
            }
            GeneratedOutlineSupport1.outline145(this.routingService.mo358get(), str2);
            return;
        }
        LogUtils.d(TAG, "Route was null. No-op.");
    }

    public DashboardCard map(Card card, SharedPreferences sharedPreferences, String str) {
        List<Action> actions = card.getActions();
        if (actions != null && actions.size() != 0) {
            Map<Action, View.OnClickListener> clickListenersForActions = getClickListenersForActions(actions, card, str);
            if (clickListenersForActions == null) {
                return null;
            }
            View.OnClickListener clickListenerForDismiss = getClickListenerForDismiss(card.getId(), sharedPreferences);
            if (Card.CategoryEnum.FEATURE_DISCOVERY_TIP.equals(card.getCategory())) {
                return new TipsCard(card, clickListenersForActions, clickListenerForDismiss);
            }
            if (!Card.CategoryEnum.NOTIFICATION_ALERT.equals(card.getCategory())) {
                return null;
            }
            return new NotificationCard(card, clickListenersForActions, clickListenerForDismiss);
        }
        BilobaMetricsService bilobaMetricsService = this.bilobaMetricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SkippedCard.");
        outline107.append(card.getType());
        bilobaMetricsService.recordCounter(outline107.toString());
        LogUtils.w(TAG, String.format("Received a card %s with no actions", card.getId()));
        return null;
    }

    @VisibleForTesting
    public CardTransformer(Lazy<CareActorsStore> lazy, Lazy<RoutingService> lazy2, Lazy<CommsHandler> lazy3, BilobaMetricsService bilobaMetricsService, Lazy<EmergencyHelplineRoutingHelper> lazy4, Lazy<RemoteAssistHelper> lazy5) {
        this.careActorsStore = lazy;
        this.routingService = lazy2;
        this.commsHandler = lazy3;
        this.bilobaMetricsService = bilobaMetricsService;
        this.emergencyHelplineRoutingHelper = lazy4;
        this.remoteAssistHelper = lazy5;
    }
}
