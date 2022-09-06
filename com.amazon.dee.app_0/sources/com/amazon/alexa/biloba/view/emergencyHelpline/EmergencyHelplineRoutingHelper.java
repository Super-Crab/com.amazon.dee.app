package com.amazon.alexa.biloba.view.emergencyHelpline;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.infoModal.InfoModalView;
import com.amazon.alexa.biloba.view.infoModal.InfoModalViewModel;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
/* loaded from: classes6.dex */
public class EmergencyHelplineRoutingHelper {
    public static final String EMERGENCY_HELPLINE_CRNAME_PARAMETER_KEY = "cr_name";
    public static final String EMERGENCY_HELPLINE_DEVICE_NAME_PARAMETER_KEY = "device_name";
    private static final Set<String> EMERGENCY_HELPLINE_ROUTES = ImmutableSet.of(Routes.BILOBA_EMERGENCY_HELPLINE_CALL_STARTED, Routes.BILOBA_EMERGENCY_HELPLINE_CALL_ENDED, Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_AND_UNVERIFIED_FALL, Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_NO_FALL);
    public static final String EMERGENCY_HELPLINE_TIME_PARAMETER_KEY = "time";
    public static final String TAG = "EmergencyHelplineRoutingHelper";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private InfoModalView getInfoModalView(Context context, String str, String str2, String str3, String str4) {
        char c;
        switch (str.hashCode()) {
            case -1732934144:
                if (str.equals(Routes.BILOBA_EMERGENCY_HELPLINE_CALL_STARTED)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -197235405:
                if (str.equals(Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_AND_UNVERIFIED_FALL)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 921495241:
                if (str.equals(Routes.BILOBA_EMERGENCY_HELPLINE_VERIFIED_NO_FALL)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1947100153:
                if (str.equals(Routes.BILOBA_EMERGENCY_HELPLINE_CALL_ENDED)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            LogUtils.d(TAG, "creating EmergencyHelplineCallStartedView");
            return new InfoModalView(context, getCallStartedModel(context, str3, str2, str4));
        } else if (c == 1) {
            LogUtils.d(TAG, "creating EmergencyHelplineCallEndedView");
            return new InfoModalView(context, getCallEndedModel(context, str3, str2));
        } else if (c == 2) {
            LogUtils.d(TAG, "creating EmergencyHelplineVerifiedAndUnverifiedFallView");
            return new InfoModalView(context, getVerifiedAndUnverifiedFallModel(context, str3, str2, str4));
        } else if (c != 3) {
            return null;
        } else {
            LogUtils.d(TAG, "creating EmergencyHelplineVerifiedNoFallView");
            return new InfoModalView(context, getVerifiedNoFallModel(context, str3, str2, str4));
        }
    }

    @VisibleForTesting
    InfoModalViewModel getCallEndedModel(Context context, String str, String str2) {
        return new InfoModalViewModel().setCaptionHeadlineText(str2).setHeadlineText(context.getString(R.string.emergency_helpline_call_ended_header)).setTitleText(context.getString(R.string.emergency_helpline_call_ended_title)).setDescription1(context.getString(R.string.emergency_helpline_call_ended_desc_1, str)).setShowCommsSection(true).setCommsSectionTitle(context.getString(R.string.emergency_helpline_call_ended_comms_title, str)).setCommsSectionDescription1(context.getString(R.string.emergency_helpline_call_ended_comms_desc_1, str)).setOkButtonText(context.getString(R.string.emergency_helpline_call_ended_button)).setOkButtonRoute(Routes.BILOBA_DASHBOARD).setViewMetricName(MetricsConstants.UserInteractionMetrics.EH_CALL_ENDED_CARD_RENDERED).setClickMetricName("LearnEmergencyHelplineCallStarted").setModalRenderedMetric(MetricsConstants.UserInteractionMetrics.EH_CALL_ENDED_MODAL_RENDERED).setCommsMetrics(MetricsConstants.UserInteractionMetrics.EH_CALL_ENDED_COMMS_DROP_IN_BUTTON, MetricsConstants.UserInteractionMetrics.EH_CALL_ENDED_COMMS_CALL_BUTTON, MetricsConstants.UserInteractionMetrics.EH_CALL_ENDED_COMMS_MESSAGE_BUTTON);
    }

    @VisibleForTesting
    InfoModalViewModel getCallStartedModel(Context context, String str, String str2, String str3) {
        return new InfoModalViewModel().setCaptionHeadlineText(str2).setHeadlineText(context.getString(R.string.emergency_helpline_call_started_header)).setTitleText(context.getString(R.string.emergency_helpline_call_started_title)).setDescription1(context.getString(R.string.emergency_helpline_call_started_desc_1, str, str3)).setDescription2(context.getString(R.string.emergency_helpline_call_started_desc_2)).setDescription3(context.getString(R.string.emergency_helpline_call_started_desc_3, str)).setOkButtonText(context.getString(R.string.emergency_helpline_call_started_button)).setOkButtonRoute(Routes.BILOBA_DASHBOARD).setViewMetricName(MetricsConstants.UserInteractionMetrics.EH_CALL_STARTED_CARD_RENDERED).setModalRenderedMetric(MetricsConstants.UserInteractionMetrics.EH_CALL_STARTED_MODAL_RENDERED).setClickMetricName("LearnEmergencyHelplineCallStarted");
    }

    public ViewController getEmergencyHelplineViewController(Context context, String str, RouteContext routeContext) {
        RouteContext routeContext2 = (RouteContext) routeContext.getParcelable(DeferredRoutingHelper.DEFERRED_ROUTE_CONTEXT);
        if (routeContext2 != null) {
            routeContext = routeContext2;
        }
        String parameterValue = BilobaRouteUtil.getParameterValue(routeContext, "time");
        String parameterValue2 = BilobaRouteUtil.getParameterValue(routeContext, EMERGENCY_HELPLINE_CRNAME_PARAMETER_KEY);
        String parameterValue3 = BilobaRouteUtil.getParameterValue(routeContext, "device_name");
        if (TextUtils.isEmpty(parameterValue2)) {
            parameterValue2 = context.getString(R.string.your_loved_one);
        }
        String str2 = parameterValue2;
        if (TextUtils.isEmpty(parameterValue3)) {
            parameterValue3 = context.getString(R.string.alexa);
        }
        return getInfoModalView(context, str, parameterValue, str2, parameterValue3);
    }

    @VisibleForTesting
    InfoModalViewModel getVerifiedAndUnverifiedFallModel(Context context, String str, String str2, String str3) {
        return new InfoModalViewModel().setCaptionHeadlineText(str2).setHeadlineText(context.getString(R.string.verified_and_unverified_fall_header, str, str3)).setTitleText(context.getString(R.string.verified_and_unverified_fall_title)).setDescription1(context.getString(R.string.verified_and_unverified_fall_desc_1, str)).setDescription2(context.getString(R.string.verified_and_unverified_fall_desc_2)).setDescription3(context.getString(R.string.verified_and_unverified_fall_desc_3, str)).setOkButtonText(context.getString(R.string.verified_and_unverified_fall_button)).setOkButtonRoute(Routes.BILOBA_DASHBOARD).setViewMetricName(MetricsConstants.UserInteractionMetrics.EH_VERIFIED_AND_UNVERIFIED_FALL_CARD_RENDERED).setModalRenderedMetric(MetricsConstants.UserInteractionMetrics.EH_VERIFIED_AND_UNVERIFIED_FALL_MODAL_RENDERED).setClickMetricName(MetricsConstants.UserInteractionMetrics.EH_VERIFIED_AND_UNVERIFIED_FALL_ACTION_CLICK);
    }

    @VisibleForTesting
    InfoModalViewModel getVerifiedNoFallModel(Context context, String str, String str2, String str3) {
        return new InfoModalViewModel().setCaptionHeadlineText(str2).setHeadlineText(context.getString(R.string.verified_no_fall_header, str, str3)).setTitleText(context.getString(R.string.verified_no_fall_title)).setDescription1(context.getString(R.string.verified_no_fall_desc_1, str)).setShowCommsSection(true).setCommsSectionTitle(context.getString(R.string.verified_no_fall_comms_title, str)).setCommsSectionDescription1(context.getString(R.string.verified_no_fall_comms_desc_1, str)).setOkButtonText(context.getString(R.string.verified_no_fall_button)).setOkButtonRoute(Routes.BILOBA_DASHBOARD).setViewMetricName(MetricsConstants.UserInteractionMetrics.EH_VERIFIED_NO_FALL_CARD_RENDERED).setClickMetricName(MetricsConstants.UserInteractionMetrics.EH_VERIFIED_NO_FALL_ACTION_CLICK).setModalRenderedMetric(MetricsConstants.UserInteractionMetrics.EH_VERIFIED_NO_FALL_MODAL_RENDERED).setCommsMetrics(MetricsConstants.UserInteractionMetrics.EH_VERIFIED_NO_FALL_COMMS_DROP_IN_BUTTON, MetricsConstants.UserInteractionMetrics.EH_VERIFIED_NO_FALL_COMMS_CALL_BUTTON, MetricsConstants.UserInteractionMetrics.EH_VERIFIED_NO_FALL_COMMS_MESSAGE_BUTTON);
    }

    public boolean isEmergencyHelplineRoute(String str) {
        return EMERGENCY_HELPLINE_ROUTES.contains(str);
    }
}
