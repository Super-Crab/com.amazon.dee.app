package com.amazon.alexa.smarthomecameras.view;

import com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventComponent;
import com.amazon.alexa.smarthomecameras.dependencies.modules.SmartAlertEventModule;
import com.amazon.alexa.smarthomecameras.view.SmartAlertEventViewController;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class DaggerSmartAlertEventViewController_Injector implements SmartAlertEventViewController.Injector {

    /* loaded from: classes10.dex */
    public static final class Builder {
        private SmartAlertEventComponent smartAlertEventComponent;

        public SmartAlertEventViewController.Injector build() {
            Preconditions.checkBuilderRequirement(this.smartAlertEventComponent, SmartAlertEventComponent.class);
            return new DaggerSmartAlertEventViewController_Injector(this);
        }

        public Builder smartAlertEventComponent(SmartAlertEventComponent smartAlertEventComponent) {
            this.smartAlertEventComponent = (SmartAlertEventComponent) Preconditions.checkNotNull(smartAlertEventComponent);
            return this;
        }

        @Deprecated
        public Builder smartAlertEventModule(SmartAlertEventModule smartAlertEventModule) {
            Preconditions.checkNotNull(smartAlertEventModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // com.amazon.alexa.smarthomecameras.view.SmartAlertEventViewController.Injector
    public void inject(SmartAlertEventViewController smartAlertEventViewController) {
    }

    private DaggerSmartAlertEventViewController_Injector(Builder builder) {
    }
}
