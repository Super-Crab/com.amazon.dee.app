package com.amazon.alexa.biloba;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.databinding.AlertFormBindingImpl;
import com.amazon.alexa.biloba.databinding.AlertItemBindingImpl;
import com.amazon.alexa.biloba.databinding.AlertManagementBindingImpl;
import com.amazon.alexa.biloba.databinding.CareDashboardBindingImpl;
import com.amazon.alexa.biloba.databinding.CommsSetupInstructionsViewBindingImpl;
import com.amazon.alexa.biloba.databinding.CommsShareSetupLinkViewBindingImpl;
import com.amazon.alexa.biloba.databinding.ConfirmationBindingImpl;
import com.amazon.alexa.biloba.databinding.EmergencyContactViewBindingImpl;
import com.amazon.alexa.biloba.databinding.EmergencyViewBindingImpl;
import com.amazon.alexa.biloba.databinding.GettingStartedViewV3BindingImpl;
import com.amazon.alexa.biloba.databinding.InfoModalViewBindingImpl;
import com.amazon.alexa.biloba.databinding.LoadingListItemBindingImpl;
import com.amazon.alexa.biloba.databinding.MembershipLeaveBindingImpl;
import com.amazon.alexa.biloba.databinding.MembershipViewBindingImpl;
import com.amazon.alexa.biloba.databinding.NotificationCardBindingImpl;
import com.amazon.alexa.biloba.databinding.ProfileSettingsBindingImpl;
import com.amazon.alexa.biloba.databinding.RecentActivitiesBindingImpl;
import com.amazon.alexa.biloba.databinding.RecentActivityHeaderBindingImpl;
import com.amazon.alexa.biloba.databinding.RecentActivityItemBindingImpl;
import com.amazon.alexa.biloba.databinding.RecentActivityMessageBindingImpl;
import com.amazon.alexa.biloba.databinding.RecentActivityNoticeBindingImpl;
import com.amazon.alexa.biloba.databinding.StartupViewBindingImpl;
import com.amazon.alexa.biloba.databinding.TimezoneListBindingImpl;
import com.amazon.alexa.biloba.databinding.TipsCardBindingImpl;
import com.amazon.alexa.biloba.databinding.TipsPageBindingImpl;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes6.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(25);
    private static final int LAYOUT_ALERTFORM = 1;
    private static final int LAYOUT_ALERTITEM = 2;
    private static final int LAYOUT_ALERTMANAGEMENT = 3;
    private static final int LAYOUT_CAREDASHBOARD = 4;
    private static final int LAYOUT_COMMSSETUPINSTRUCTIONSVIEW = 5;
    private static final int LAYOUT_COMMSSHARESETUPLINKVIEW = 6;
    private static final int LAYOUT_CONFIRMATION = 7;
    private static final int LAYOUT_EMERGENCYCONTACTVIEW = 8;
    private static final int LAYOUT_EMERGENCYVIEW = 9;
    private static final int LAYOUT_GETTINGSTARTEDVIEWV3 = 10;
    private static final int LAYOUT_INFOMODALVIEW = 11;
    private static final int LAYOUT_LOADINGLISTITEM = 12;
    private static final int LAYOUT_MEMBERSHIPLEAVE = 13;
    private static final int LAYOUT_MEMBERSHIPVIEW = 14;
    private static final int LAYOUT_NOTIFICATIONCARD = 15;
    private static final int LAYOUT_PROFILESETTINGS = 16;
    private static final int LAYOUT_RECENTACTIVITIES = 17;
    private static final int LAYOUT_RECENTACTIVITYHEADER = 18;
    private static final int LAYOUT_RECENTACTIVITYITEM = 19;
    private static final int LAYOUT_RECENTACTIVITYMESSAGE = 20;
    private static final int LAYOUT_RECENTACTIVITYNOTICE = 21;
    private static final int LAYOUT_STARTUPVIEW = 22;
    private static final int LAYOUT_TIMEZONELIST = 23;
    private static final int LAYOUT_TIPSCARD = 24;
    private static final int LAYOUT_TIPSPAGE = 25;

    /* loaded from: classes6.dex */
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(17);

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "handler");
            sKeys.put(2, "viewmodel");
            sKeys.put(3, "timezone_view");
            sKeys.put(4, "membershipView");
            sKeys.put(5, "activityItem");
            sKeys.put(6, "membership_view");
            sKeys.put(7, "alert");
            sKeys.put(8, "noticeItem");
            sKeys.put(9, "viewModel");
            sKeys.put(10, "activityMessage");
            sKeys.put(11, "profile_settings_view");
            sKeys.put(12, "activityHeader");
            sKeys.put(13, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD);
            sKeys.put(14, "timezoneView");
            sKeys.put(15, "profileSettingsView");
        }

        private InnerBrLookup() {
        }
    }

    /* loaded from: classes6.dex */
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(25);

        static {
            sKeys.put("layout/alert_form_0", Integer.valueOf(R.layout.alert_form));
            sKeys.put("layout/alert_item_0", Integer.valueOf(R.layout.alert_item));
            sKeys.put("layout/alert_management_0", Integer.valueOf(R.layout.alert_management));
            sKeys.put("layout/care_dashboard_0", Integer.valueOf(R.layout.care_dashboard));
            sKeys.put("layout/comms_setup_instructions_view_0", Integer.valueOf(R.layout.comms_setup_instructions_view));
            sKeys.put("layout/comms_share_setup_link_view_0", Integer.valueOf(R.layout.comms_share_setup_link_view));
            sKeys.put("layout/confirmation_0", Integer.valueOf(R.layout.confirmation));
            sKeys.put("layout/emergency_contact_view_0", Integer.valueOf(R.layout.emergency_contact_view));
            sKeys.put("layout/emergency_view_0", Integer.valueOf(R.layout.emergency_view));
            sKeys.put("layout/getting_started_view_v3_0", Integer.valueOf(R.layout.getting_started_view_v3));
            sKeys.put("layout/info_modal_view_0", Integer.valueOf(R.layout.info_modal_view));
            sKeys.put("layout/loading_list_item_0", Integer.valueOf(R.layout.loading_list_item));
            sKeys.put("layout/membership_leave_0", Integer.valueOf(R.layout.membership_leave));
            sKeys.put("layout/membership_view_0", Integer.valueOf(R.layout.membership_view));
            sKeys.put("layout/notification_card_0", Integer.valueOf(R.layout.notification_card));
            sKeys.put("layout/profile_settings_0", Integer.valueOf(R.layout.profile_settings));
            sKeys.put("layout/recent_activities_0", Integer.valueOf(R.layout.recent_activities));
            sKeys.put("layout/recent_activity_header_0", Integer.valueOf(R.layout.recent_activity_header));
            sKeys.put("layout/recent_activity_item_0", Integer.valueOf(R.layout.recent_activity_item));
            sKeys.put("layout/recent_activity_message_0", Integer.valueOf(R.layout.recent_activity_message));
            sKeys.put("layout/recent_activity_notice_0", Integer.valueOf(R.layout.recent_activity_notice));
            sKeys.put("layout/startup_view_0", Integer.valueOf(R.layout.startup_view));
            sKeys.put("layout/timezone_list_0", Integer.valueOf(R.layout.timezone_list));
            sKeys.put("layout/tips_card_0", Integer.valueOf(R.layout.tips_card));
            sKeys.put("layout/tips_page_0", Integer.valueOf(R.layout.tips_page));
        }

        private InnerLayoutIdLookup() {
        }
    }

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.alert_form, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.alert_item, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.alert_management, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.care_dashboard, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.comms_setup_instructions_view, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.comms_share_setup_link_view, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.confirmation, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.emergency_contact_view, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.emergency_view, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.getting_started_view_v3, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.info_modal_view, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.loading_list_item, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.membership_leave, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.membership_view, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.notification_card, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.profile_settings, 16);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.recent_activities, 17);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.recent_activity_header, 18);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.recent_activity_item, 19);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.recent_activity_message, 20);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.recent_activity_notice, 21);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.startup_view, 22);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.timezone_list, 23);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.tips_card, 24);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.tips_page, 25);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/alert_form_0".equals(tag)) {
                            return new AlertFormBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for alert_form is invalid. Received: ", tag));
                    case 2:
                        if ("layout/alert_item_0".equals(tag)) {
                            return new AlertItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for alert_item is invalid. Received: ", tag));
                    case 3:
                        if ("layout/alert_management_0".equals(tag)) {
                            return new AlertManagementBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for alert_management is invalid. Received: ", tag));
                    case 4:
                        if ("layout/care_dashboard_0".equals(tag)) {
                            return new CareDashboardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for care_dashboard is invalid. Received: ", tag));
                    case 5:
                        if ("layout/comms_setup_instructions_view_0".equals(tag)) {
                            return new CommsSetupInstructionsViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for comms_setup_instructions_view is invalid. Received: ", tag));
                    case 6:
                        if ("layout/comms_share_setup_link_view_0".equals(tag)) {
                            return new CommsShareSetupLinkViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for comms_share_setup_link_view is invalid. Received: ", tag));
                    case 7:
                        if ("layout/confirmation_0".equals(tag)) {
                            return new ConfirmationBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for confirmation is invalid. Received: ", tag));
                    case 8:
                        if ("layout/emergency_contact_view_0".equals(tag)) {
                            return new EmergencyContactViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for emergency_contact_view is invalid. Received: ", tag));
                    case 9:
                        if ("layout/emergency_view_0".equals(tag)) {
                            return new EmergencyViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for emergency_view is invalid. Received: ", tag));
                    case 10:
                        if ("layout/getting_started_view_v3_0".equals(tag)) {
                            return new GettingStartedViewV3BindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for getting_started_view_v3 is invalid. Received: ", tag));
                    case 11:
                        if ("layout/info_modal_view_0".equals(tag)) {
                            return new InfoModalViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for info_modal_view is invalid. Received: ", tag));
                    case 12:
                        if ("layout/loading_list_item_0".equals(tag)) {
                            return new LoadingListItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for loading_list_item is invalid. Received: ", tag));
                    case 13:
                        if ("layout/membership_leave_0".equals(tag)) {
                            return new MembershipLeaveBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for membership_leave is invalid. Received: ", tag));
                    case 14:
                        if ("layout/membership_view_0".equals(tag)) {
                            return new MembershipViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for membership_view is invalid. Received: ", tag));
                    case 15:
                        if ("layout/notification_card_0".equals(tag)) {
                            return new NotificationCardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for notification_card is invalid. Received: ", tag));
                    case 16:
                        if ("layout/profile_settings_0".equals(tag)) {
                            return new ProfileSettingsBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for profile_settings is invalid. Received: ", tag));
                    case 17:
                        if ("layout/recent_activities_0".equals(tag)) {
                            return new RecentActivitiesBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for recent_activities is invalid. Received: ", tag));
                    case 18:
                        if ("layout/recent_activity_header_0".equals(tag)) {
                            return new RecentActivityHeaderBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for recent_activity_header is invalid. Received: ", tag));
                    case 19:
                        if ("layout/recent_activity_item_0".equals(tag)) {
                            return new RecentActivityItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for recent_activity_item is invalid. Received: ", tag));
                    case 20:
                        if ("layout/recent_activity_message_0".equals(tag)) {
                            return new RecentActivityMessageBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for recent_activity_message is invalid. Received: ", tag));
                    case 21:
                        if ("layout/recent_activity_notice_0".equals(tag)) {
                            return new RecentActivityNoticeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for recent_activity_notice is invalid. Received: ", tag));
                    case 22:
                        if ("layout/startup_view_0".equals(tag)) {
                            return new StartupViewBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for startup_view is invalid. Received: ", tag));
                    case 23:
                        if ("layout/timezone_list_0".equals(tag)) {
                            return new TimezoneListBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for timezone_list is invalid. Received: ", tag));
                    case 24:
                        if ("layout/tips_card_0".equals(tag)) {
                            return new TipsCardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for tips_card is invalid. Received: ", tag));
                    case 25:
                        if ("layout/tips_page_0".equals(tag)) {
                            return new TipsPageBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for tips_page is invalid. Received: ", tag));
                    default:
                        return null;
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
