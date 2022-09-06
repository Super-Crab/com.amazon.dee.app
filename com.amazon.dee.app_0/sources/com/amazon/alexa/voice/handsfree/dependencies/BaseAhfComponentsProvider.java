package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.dependencies.AlexaMobileMetricsComponent;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentProtocol;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.settings.contract.dependencies.FalcoSettingContractComponent;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoAlexaAppSettingsMediatorComponent;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.storage.dependencies.FalcoStorageComponent;
import com.amazon.alexa.handsfree.vendor.bridge.dependencies.FalcoVendorBridgeComponent;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes11.dex */
public abstract class BaseAhfComponentsProvider extends AhfComponentsProvider {
    private final Context mApplicationContext;
    private volatile AhfComponent mAhfComponent = null;
    private final Map<Class<?>, ComponentFactory<?>> componentFactoryMap = Collections.unmodifiableMap(factoryMap());
    private final Map<Class<?>, Object> instanceMap = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface ComponentFactory<T> {
        @NonNull
        T create();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAhfComponentsProvider(Context context) {
        this.mApplicationContext = context;
    }

    private <T extends AhfComponentProtocol> void ensureComponentIsInMap(@NonNull Class<T> cls) {
        if (!this.instanceMap.containsKey(cls)) {
            synchronized (this.instanceMap) {
                if (!this.instanceMap.containsKey(cls)) {
                    ComponentFactory<?> componentFactory = this.componentFactoryMap.get(cls);
                    if (componentFactory != null) {
                        T cast = cls.cast(componentFactory.create());
                        Preconditions.checkNotNull(cast);
                        this.instanceMap.put(cls, cast);
                    } else {
                        throw new IllegalArgumentException("Factory not found: " + cls);
                    }
                }
            }
        }
    }

    private Map<Class<?>, ComponentFactory<?>> factoryMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(AhfComponent.class, new ComponentFactory() { // from class: com.amazon.alexa.voice.handsfree.dependencies.-$$Lambda$BaseAhfComponentsProvider$ZjEqFXXGDVWA_4bppWp3ygauqSQ
            @Override // com.amazon.alexa.voice.handsfree.dependencies.BaseAhfComponentsProvider.ComponentFactory
            public final Object create() {
                return BaseAhfComponentsProvider.this.lambda$factoryMap$0$BaseAhfComponentsProvider();
            }
        });
        hashMap.put(FalcoProtocolComponent.class, new ComponentFactory() { // from class: com.amazon.alexa.voice.handsfree.dependencies.-$$Lambda$BaseAhfComponentsProvider$2JS7v-J_4QNkhQUynKbnLLRPe_g
            @Override // com.amazon.alexa.voice.handsfree.dependencies.BaseAhfComponentsProvider.ComponentFactory
            public final Object create() {
                return BaseAhfComponentsProvider.this.lambda$factoryMap$1$BaseAhfComponentsProvider();
            }
        });
        hashMap.put(FalcoVendorBridgeComponent.class, new ComponentFactory() { // from class: com.amazon.alexa.voice.handsfree.dependencies.-$$Lambda$BaseAhfComponentsProvider$4EgtFPd4PJsRxsOEefK-Pk_vNng
            @Override // com.amazon.alexa.voice.handsfree.dependencies.BaseAhfComponentsProvider.ComponentFactory
            public final Object create() {
                return BaseAhfComponentsProvider.this.lambda$factoryMap$2$BaseAhfComponentsProvider();
            }
        });
        hashMap.put(FalcoSettingContractComponent.class, new ComponentFactory() { // from class: com.amazon.alexa.voice.handsfree.dependencies.-$$Lambda$BaseAhfComponentsProvider$QlUX6qjetTjvvqzCs9TUKD8hNu4
            @Override // com.amazon.alexa.voice.handsfree.dependencies.BaseAhfComponentsProvider.ComponentFactory
            public final Object create() {
                return BaseAhfComponentsProvider.this.lambda$factoryMap$3$BaseAhfComponentsProvider();
            }
        });
        hashMap.put(FalcoSettingsComponent.class, new ComponentFactory() { // from class: com.amazon.alexa.voice.handsfree.dependencies.-$$Lambda$BaseAhfComponentsProvider$VZezIZSgMirhTKGYHbZAqLd9eQY
            @Override // com.amazon.alexa.voice.handsfree.dependencies.BaseAhfComponentsProvider.ComponentFactory
            public final Object create() {
                return BaseAhfComponentsProvider.this.lambda$factoryMap$4$BaseAhfComponentsProvider();
            }
        });
        hashMap.put(FalcoAlexaAppSettingsMediatorComponent.class, new ComponentFactory() { // from class: com.amazon.alexa.voice.handsfree.dependencies.-$$Lambda$BaseAhfComponentsProvider$GmaErzHeCdKhUEjDwuh_xeZvOV8
            @Override // com.amazon.alexa.voice.handsfree.dependencies.BaseAhfComponentsProvider.ComponentFactory
            public final Object create() {
                return BaseAhfComponentsProvider.this.lambda$factoryMap$5$BaseAhfComponentsProvider();
            }
        });
        hashMap.put(FalcoStorageComponent.class, new ComponentFactory() { // from class: com.amazon.alexa.voice.handsfree.dependencies.-$$Lambda$BaseAhfComponentsProvider$ABO043wGNvDgQ0EhMcmYrjOjfZY
            @Override // com.amazon.alexa.voice.handsfree.dependencies.BaseAhfComponentsProvider.ComponentFactory
            public final Object create() {
                return BaseAhfComponentsProvider.this.lambda$factoryMap$6$BaseAhfComponentsProvider();
            }
        });
        hashMap.put(AlexaMobileMetricsComponent.class, new ComponentFactory() { // from class: com.amazon.alexa.voice.handsfree.dependencies.-$$Lambda$BaseAhfComponentsProvider$vX_rvG42HHakxIzqWMsTJKRNrSY
            @Override // com.amazon.alexa.voice.handsfree.dependencies.BaseAhfComponentsProvider.ComponentFactory
            public final Object create() {
                return BaseAhfComponentsProvider.this.lambda$factoryMap$7$BaseAhfComponentsProvider();
            }
        });
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getAhfComponent */
    public AhfComponent lambda$factoryMap$0$BaseAhfComponentsProvider() {
        AhfComponent ahfComponent = this.mAhfComponent;
        if (ahfComponent == null) {
            synchronized (this) {
                ahfComponent = this.mAhfComponent;
                if (ahfComponent == null) {
                    ahfComponent = DaggerAhfComponent.builder().ahfModule(new AhfModule(this.mApplicationContext)).build();
                    this.mAhfComponent = ahfComponent;
                }
            }
        }
        return ahfComponent;
    }

    @Override // com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider
    public <T extends AhfComponentProtocol> T getComponent(@NonNull Class<T> cls) {
        ensureComponentIsInMap(cls);
        return cls.cast(this.instanceMap.get(cls));
    }

    public /* synthetic */ Object lambda$factoryMap$1$BaseAhfComponentsProvider() {
        return lambda$factoryMap$0$BaseAhfComponentsProvider().falcoProtocolComponent();
    }

    public /* synthetic */ Object lambda$factoryMap$2$BaseAhfComponentsProvider() {
        return lambda$factoryMap$0$BaseAhfComponentsProvider().falcoVendorBridgeComponent();
    }

    public /* synthetic */ Object lambda$factoryMap$3$BaseAhfComponentsProvider() {
        return lambda$factoryMap$0$BaseAhfComponentsProvider().falcoSettingContractComponent();
    }

    public /* synthetic */ Object lambda$factoryMap$4$BaseAhfComponentsProvider() {
        return lambda$factoryMap$0$BaseAhfComponentsProvider().falcoSettingsComponent();
    }

    public /* synthetic */ Object lambda$factoryMap$5$BaseAhfComponentsProvider() {
        return lambda$factoryMap$0$BaseAhfComponentsProvider().falcoAlexaAppSettingsMediatorComponent();
    }

    public /* synthetic */ Object lambda$factoryMap$6$BaseAhfComponentsProvider() {
        return lambda$factoryMap$0$BaseAhfComponentsProvider().falcoStorageComponent();
    }

    public /* synthetic */ Object lambda$factoryMap$7$BaseAhfComponentsProvider() {
        return lambda$factoryMap$0$BaseAhfComponentsProvider().alexaMobileMetricsComponent();
    }
}
