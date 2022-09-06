package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.FFSBackgroundProvisioningServiceBinder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.FFSBackgroundProvisioningServiceBinder_MembersInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.util.SharedPreferencesProvider;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DaggerBaseDependencyInjector implements BaseDependencyInjector {
    private BaseComponent baseComponent;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private BaseComponent baseComponent;

        public Builder baseComponent(BaseComponent baseComponent) {
            this.baseComponent = (BaseComponent) Preconditions.checkNotNull(baseComponent);
            return this;
        }

        public BaseDependencyInjector build() {
            Preconditions.checkBuilderRequirement(this.baseComponent, BaseComponent.class);
            return new DaggerBaseDependencyInjector(this);
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private FFSBackgroundProvisioningServiceBinder injectFFSBackgroundProvisioningServiceBinder(FFSBackgroundProvisioningServiceBinder fFSBackgroundProvisioningServiceBinder) {
        FFSBackgroundProvisioningServiceBinder_MembersInjector.injectMSharedPreferencesProvider(fFSBackgroundProvisioningServiceBinder, (SharedPreferencesProvider) Preconditions.checkNotNull(this.baseComponent.getSharedPreferencesProvider(), "Cannot return null from a non-@Nullable component method"));
        FFSBackgroundProvisioningServiceBinder_MembersInjector.injectMMapAuthenticationProvider(fFSBackgroundProvisioningServiceBinder, (MapAuthenticationProvider) Preconditions.checkNotNull(this.baseComponent.getMapAuthProvider(), "Cannot return null from a non-@Nullable component method"));
        return fFSBackgroundProvisioningServiceBinder;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseDependencyInjector
    public void inject(FFSBackgroundProvisioningServiceBinder fFSBackgroundProvisioningServiceBinder) {
        injectFFSBackgroundProvisioningServiceBinder(fFSBackgroundProvisioningServiceBinder);
    }

    private DaggerBaseDependencyInjector(Builder builder) {
        this.baseComponent = builder.baseComponent;
    }
}
