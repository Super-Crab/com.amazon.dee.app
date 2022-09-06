package com.amazon.alexa.drive.main;
/* loaded from: classes7.dex */
public interface DriveModeMainContract {

    /* loaded from: classes7.dex */
    public interface Presenter {
        void egress();

        void initialize(View view);

        void navigateToCommunications();

        void navigateToEntertainment();

        void navigateToHome();

        void navigateToNavigation();

        void uninitialize(boolean z);
    }

    /* loaded from: classes7.dex */
    public interface View {

        /* loaded from: classes7.dex */
        public enum ChannelName {
            HOME,
            NAVIGATION,
            COMMS,
            ENTERTAINMENT,
            SMART_DEVICE,
            DEVICES
        }

        void channelSwitched(ChannelName channelName);

        void exitingDriveModeRoutes();

        void routeNavigationFailed();

        void setNavigationBarVisibility(boolean z);

        void setTabBarVisibility(boolean z);
    }
}
