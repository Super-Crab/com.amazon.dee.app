package com.amazon.alexa.mode.statemachine;
/* loaded from: classes9.dex */
public final class StateContext {
    private final boolean mAccessoryConnected;
    private final boolean mAutoBluetoothConnected;
    private final boolean mLaunchAppFromDeepLink;
    private final boolean mManualIngressMode;
    private final boolean mNotificationSent;
    private final int mPrimaryNotificationDisplayCount;
    private final boolean mScreenOn;
    private final boolean mSuppressAutoIngress;

    /* loaded from: classes9.dex */
    public static class Builder {
        private boolean mAccessoryConnected;
        private boolean mAutoBluetoothConnected;
        private boolean mLaunchAppFromDeepLink;
        private boolean mManualIngressMode;
        private boolean mNotificationSent;
        private int mPrimaryNotificationDisplayCount;
        private boolean mScreenOn;
        private boolean mSuppressAutoIngress;

        public Builder() {
        }

        public StateContext build() {
            return new StateContext(this.mAccessoryConnected, this.mLaunchAppFromDeepLink, this.mPrimaryNotificationDisplayCount, this.mScreenOn, this.mSuppressAutoIngress, this.mNotificationSent, this.mAutoBluetoothConnected, this.mManualIngressMode);
        }

        public Builder setAccessoryConnected(boolean z) {
            this.mAccessoryConnected = z;
            return this;
        }

        public Builder setAutoBluetoothConnected(boolean z) {
            this.mAutoBluetoothConnected = z;
            return this;
        }

        public Builder setLaunchAppFromDeepLink(boolean z) {
            this.mLaunchAppFromDeepLink = z;
            return this;
        }

        public Builder setManualIngressMode(boolean z) {
            this.mManualIngressMode = z;
            return this;
        }

        public Builder setNotificationSent(boolean z) {
            this.mNotificationSent = z;
            return this;
        }

        public Builder setPrimaryNotificationDisplayCount(int i) {
            this.mPrimaryNotificationDisplayCount = i;
            return this;
        }

        public Builder setScreenOn(boolean z) {
            this.mScreenOn = z;
            return this;
        }

        public Builder setSuppressAutoIngress(boolean z) {
            this.mSuppressAutoIngress = z;
            return this;
        }

        public Builder(StateContext stateContext) {
            this.mAccessoryConnected = stateContext.isAccessoryConnected();
            this.mLaunchAppFromDeepLink = stateContext.isLaunchAppFromDeepLink();
            this.mPrimaryNotificationDisplayCount = stateContext.getPrimaryNotificationDisplayCount();
            this.mScreenOn = stateContext.isScreenOn();
            this.mSuppressAutoIngress = stateContext.isSuppressAutoIngress();
            this.mNotificationSent = stateContext.isNotificationSent();
            this.mAutoBluetoothConnected = stateContext.isAutoBluetoothConnected();
            this.mManualIngressMode = stateContext.isManualIngressMode();
        }
    }

    public int getPrimaryNotificationDisplayCount() {
        return this.mPrimaryNotificationDisplayCount;
    }

    public boolean isAccessoryConnected() {
        return this.mAccessoryConnected;
    }

    public boolean isAutoBluetoothConnected() {
        return this.mAutoBluetoothConnected;
    }

    public boolean isLaunchAppFromDeepLink() {
        return this.mLaunchAppFromDeepLink;
    }

    public boolean isManualIngressMode() {
        return this.mManualIngressMode;
    }

    public boolean isNotificationSent() {
        return this.mNotificationSent;
    }

    public boolean isScreenOn() {
        return this.mScreenOn;
    }

    public boolean isSuppressAutoIngress() {
        return this.mSuppressAutoIngress;
    }

    private StateContext(boolean z, boolean z2, int i, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.mAccessoryConnected = z;
        this.mLaunchAppFromDeepLink = z2;
        this.mPrimaryNotificationDisplayCount = i;
        this.mScreenOn = z3;
        this.mSuppressAutoIngress = z4;
        this.mNotificationSent = z5;
        this.mAutoBluetoothConnected = z6;
        this.mManualIngressMode = z7;
    }
}
