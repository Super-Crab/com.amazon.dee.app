package com.amazon.dee.app.dependencies;

import com.amazon.alexa.accessory.Accessories;
import com.amazon.alexa.api.AlexaServicesConnection;
import dagger.internal.DoubleCheck;
import dagger.internal.MemoizedSentinel;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class DaggerAccessoriesComponent implements AccessoriesComponent {
    private volatile Object accessories;
    private AccessoriesAlexaModule accessoriesAlexaModule;
    private AccessoryModule accessoryModule;
    private volatile Object namedAlexaServicesConnection;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private AccessoriesAlexaModule accessoriesAlexaModule;
        private AccessoryModule accessoryModule;

        public Builder accessoriesAlexaModule(AccessoriesAlexaModule accessoriesAlexaModule) {
            this.accessoriesAlexaModule = (AccessoriesAlexaModule) Preconditions.checkNotNull(accessoriesAlexaModule);
            return this;
        }

        public Builder accessoryModule(AccessoryModule accessoryModule) {
            this.accessoryModule = (AccessoryModule) Preconditions.checkNotNull(accessoryModule);
            return this;
        }

        public AccessoriesComponent build() {
            Preconditions.checkBuilderRequirement(this.accessoryModule, AccessoryModule.class);
            if (this.accessoriesAlexaModule == null) {
                this.accessoriesAlexaModule = new AccessoriesAlexaModule();
            }
            return new DaggerAccessoriesComponent(this);
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private AlexaServicesConnection getNamedAlexaServicesConnection() {
        Object obj;
        Object obj2 = this.namedAlexaServicesConnection;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.namedAlexaServicesConnection;
                if (obj instanceof MemoizedSentinel) {
                    obj = AccessoriesAlexaModule_ProvideAlexaServicesConnectionFactory.proxyProvideAlexaServicesConnection(this.accessoriesAlexaModule, AccessoryModule_ProvideContextFactory.proxyProvideContext(this.accessoryModule));
                    this.namedAlexaServicesConnection = DoubleCheck.reentrantCheck(this.namedAlexaServicesConnection, obj);
                }
            }
            obj2 = obj;
        }
        return (AlexaServicesConnection) obj2;
    }

    @Override // com.amazon.dee.app.dependencies.AccessoriesComponent
    public Accessories accessories() {
        Object obj;
        Object obj2 = this.accessories;
        if (obj2 instanceof MemoizedSentinel) {
            synchronized (obj2) {
                obj = this.accessories;
                if (obj instanceof MemoizedSentinel) {
                    obj = AccessoryModule_ProvideAccessoriesFactory.proxyProvideAccessories(this.accessoryModule, AccessoryModule_ProvideContextFactory.proxyProvideContext(this.accessoryModule), getNamedAlexaServicesConnection());
                    this.accessories = DoubleCheck.reentrantCheck(this.accessories, obj);
                }
            }
            obj2 = obj;
        }
        return (Accessories) obj2;
    }

    private DaggerAccessoriesComponent(Builder builder) {
        this.namedAlexaServicesConnection = new MemoizedSentinel();
        this.accessories = new MemoizedSentinel();
        this.accessoryModule = builder.accessoryModule;
        this.accessoriesAlexaModule = builder.accessoriesAlexaModule;
    }
}
