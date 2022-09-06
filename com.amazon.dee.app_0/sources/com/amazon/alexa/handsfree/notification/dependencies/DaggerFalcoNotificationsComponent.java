package com.amazon.alexa.handsfree.notification.dependencies;

import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import dagger.internal.Preconditions;
/* loaded from: classes8.dex */
public final class DaggerFalcoNotificationsComponent implements FalcoNotificationsComponent {
    private FalcoNotificationsModule falcoNotificationsModule;

    /* loaded from: classes8.dex */
    public static final class Builder {
        private FalcoNotificationsModule falcoNotificationsModule;

        public FalcoNotificationsComponent build() {
            if (this.falcoNotificationsModule == null) {
                this.falcoNotificationsModule = new FalcoNotificationsModule();
            }
            return new DaggerFalcoNotificationsComponent(this);
        }

        public Builder falcoNotificationsModule(FalcoNotificationsModule falcoNotificationsModule) {
            this.falcoNotificationsModule = (FalcoNotificationsModule) Preconditions.checkNotNull(falcoNotificationsModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static FalcoNotificationsComponent create() {
        return new Builder().build();
    }

    @Override // com.amazon.alexa.handsfree.notification.dependencies.FalcoNotificationsComponent
    public NotificationModule notificationModule() {
        return FalcoNotificationsModule_ProvideNotificationModuleFactory.proxyProvideNotificationModule(this.falcoNotificationsModule);
    }

    @Override // com.amazon.alexa.handsfree.notification.dependencies.FalcoNotificationsComponent
    public NotificationOccurrenceCounter notificationOccurrenceCounter() {
        return FalcoNotificationsModule_ProvideNotificationOccurrenceCounterFactory.proxyProvideNotificationOccurrenceCounter(this.falcoNotificationsModule);
    }

    private DaggerFalcoNotificationsComponent(Builder builder) {
        this.falcoNotificationsModule = builder.falcoNotificationsModule;
    }
}
