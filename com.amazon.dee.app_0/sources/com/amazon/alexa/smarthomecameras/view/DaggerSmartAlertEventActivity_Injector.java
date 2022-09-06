package com.amazon.alexa.smarthomecameras.view;

import android.content.Context;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.dependencies.components.SmartAlertEventComponent;
import com.amazon.alexa.smarthomecameras.dependencies.modules.SmartAlertEventModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.SmartAlertEventModule_ProvidesImageUrlFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.SmartAlertEventViewModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.SmartAlertEventViewModule_ProvidesSmartAlertEventViewFactory;
import com.amazon.alexa.smarthomecameras.view.SmartAlertEventActivity;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class DaggerSmartAlertEventActivity_Injector implements SmartAlertEventActivity.Injector {
    private SmartAlertEventComponent smartAlertEventComponent;
    private SmartAlertEventModule smartAlertEventModule;
    private SmartAlertEventViewModule smartAlertEventViewModule;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private SmartAlertEventComponent smartAlertEventComponent;
        private SmartAlertEventModule smartAlertEventModule;
        private SmartAlertEventViewModule smartAlertEventViewModule;

        public SmartAlertEventActivity.Injector build() {
            Preconditions.checkBuilderRequirement(this.smartAlertEventViewModule, SmartAlertEventViewModule.class);
            Preconditions.checkBuilderRequirement(this.smartAlertEventModule, SmartAlertEventModule.class);
            Preconditions.checkBuilderRequirement(this.smartAlertEventComponent, SmartAlertEventComponent.class);
            return new DaggerSmartAlertEventActivity_Injector(this);
        }

        public Builder smartAlertEventComponent(SmartAlertEventComponent smartAlertEventComponent) {
            this.smartAlertEventComponent = (SmartAlertEventComponent) Preconditions.checkNotNull(smartAlertEventComponent);
            return this;
        }

        public Builder smartAlertEventModule(SmartAlertEventModule smartAlertEventModule) {
            this.smartAlertEventModule = (SmartAlertEventModule) Preconditions.checkNotNull(smartAlertEventModule);
            return this;
        }

        public Builder smartAlertEventViewModule(SmartAlertEventViewModule smartAlertEventViewModule) {
            this.smartAlertEventViewModule = (SmartAlertEventViewModule) Preconditions.checkNotNull(smartAlertEventViewModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private SmartAlertEventView getSmartAlertEventView() {
        return SmartAlertEventViewModule_ProvidesSmartAlertEventViewFactory.proxyProvidesSmartAlertEventView(this.smartAlertEventViewModule, (Context) Preconditions.checkNotNull(this.smartAlertEventComponent.context(), "Cannot return null from a non-@Nullable component method"), SmartAlertEventModule_ProvidesImageUrlFactory.proxyProvidesImageUrl(this.smartAlertEventModule));
    }

    @CanIgnoreReturnValue
    private SmartAlertEventActivity injectSmartAlertEventActivity(SmartAlertEventActivity smartAlertEventActivity) {
        SmartAlertEventActivity_MembersInjector.injectSmartAlertEventView(smartAlertEventActivity, getSmartAlertEventView());
        SmartAlertEventActivity_MembersInjector.injectRoutingService(smartAlertEventActivity, (RoutingService) Preconditions.checkNotNull(this.smartAlertEventComponent.routingService(), "Cannot return null from a non-@Nullable component method"));
        return smartAlertEventActivity;
    }

    @Override // com.amazon.alexa.smarthomecameras.view.SmartAlertEventActivity.Injector
    public void inject(SmartAlertEventActivity smartAlertEventActivity) {
        injectSmartAlertEventActivity(smartAlertEventActivity);
    }

    private DaggerSmartAlertEventActivity_Injector(Builder builder) {
        this.smartAlertEventComponent = builder.smartAlertEventComponent;
        this.smartAlertEventModule = builder.smartAlertEventModule;
        this.smartAlertEventViewModule = builder.smartAlertEventViewModule;
    }
}
