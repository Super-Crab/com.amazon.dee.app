package com.amazon.photos.discovery;

import android.util.Log;
import androidx.annotation.AnyThread;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.photos.discovery.dedupe.DedupeStage;
import com.amazon.photos.discovery.dedupe.Deduplicator;
import com.amazon.photos.discovery.internal.Injectable;
import com.amazon.photos.discovery.internal.dagger.component.DaggerDiscoveryComponent;
import com.amazon.photos.discovery.internal.dagger.component.DiscoveryComponent;
import com.amazon.photos.discovery.internal.dagger.module.DataModule;
import com.amazon.photos.discovery.internal.dagger.module.DiscoveryModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Discovery.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001e\u001a\u00020\u001fH\u0017J\b\u0010 \u001a\u00020\u000eH\u0017J\b\u0010!\u001a\u00020\u001fH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0007\u001a\u00020\b8F¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0016\u001a\u00020\u00178F¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u001a\u001a\u00020\u001b8F¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006#"}, d2 = {"Lcom/amazon/photos/discovery/Discovery;", "Ljavax/security/auth/Destroyable;", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "(Lcom/amazon/photos/discovery/DiscoveryConfiguration;)V", "getConfiguration", "()Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "daos", "Lcom/amazon/photos/discovery/DiscoveryDaos;", "getDaos", "()Lcom/amazon/photos/discovery/DiscoveryDaos;", "dataModule", "Lcom/amazon/photos/discovery/internal/dagger/module/DataModule;", "<set-?>", "", "destroyed", "getDestroyed", "()Z", "hashedDirectedId", "", "getHashedDirectedId", "()Ljava/lang/String;", "liveWorkInfo", "Lcom/amazon/photos/discovery/DiscoveryLiveWorkInfo;", "getLiveWorkInfo", "()Lcom/amazon/photos/discovery/DiscoveryLiveWorkInfo;", "operations", "Lcom/amazon/photos/discovery/DiscoveryOperations;", "getOperations", "()Lcom/amazon/photos/discovery/DiscoveryOperations;", "destroy", "", "isDestroyed", "throwDestroyedIfNeeded", "Companion", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Discovery implements Destroyable {
    public static final Companion Companion = new Companion(null);
    private static final ConcurrentHashMap<String, DiscoveryComponent> components = new ConcurrentHashMap<>();
    @NotNull
    private final DiscoveryConfiguration configuration;
    @NotNull
    private final DiscoveryDaos daos;
    private final DataModule dataModule;
    private boolean destroyed;
    @NotNull
    private final String hashedDirectedId;
    @NotNull
    private final DiscoveryLiveWorkInfo liveWorkInfo;
    @NotNull
    private final DiscoveryOperations operations;

    /* compiled from: Discovery.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001f\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0015\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0001¢\u0006\u0002\b\u000fJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0012J\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014H\u0000¢\u0006\u0002\b\u0015J%\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\u0019J\u001d\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0006H\u0001¢\u0006\u0002\b\u001dJ\u0010\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0005H\u0007J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0005H\u0007J\u0010\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\rH\u0002J\u0016\u0010\"\u001a\u00020\b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/amazon/photos/discovery/Discovery$Companion;", "", "()V", "components", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/amazon/photos/discovery/internal/dagger/component/DiscoveryComponent;", "clearComponents", "", "clearComponents$AndroidPhotosDiscovery_release", "createInstance", "Lcom/amazon/photos/discovery/Discovery;", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "createUninjectedDiscovery", "createUninjectedDiscovery$AndroidPhotosDiscovery_release", "discoveryComponentForAccount", "hashedDirectedId", "discoveryComponentForAccount$AndroidPhotosDiscovery_release", "getAllDiscoveryComponents", "", "getAllDiscoveryComponents$AndroidPhotosDiscovery_release", "injectComponents", JsonFields.COMPONENT, "discovery", "injectComponents$AndroidPhotosDiscovery_release", "putComponent", "directedId", "discoveryComponent", "putComponent$AndroidPhotosDiscovery_release", "shutdown", "shutdownAllExcluding", "validateConfiguration", "discoveryConfiguration", "validateDedupeStages", "dedupeStages", "", "Lcom/amazon/photos/discovery/dedupe/DedupeStage;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        private final void validateConfiguration(DiscoveryConfiguration discoveryConfiguration) {
            if (discoveryConfiguration.getScanBatchSize() >= 10) {
                validateDedupeStages(discoveryConfiguration.getDedupeStages());
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid scan batch size specified: ");
            outline107.append(discoveryConfiguration.getScanBatchSize());
            throw new IllegalArgumentException(outline107.toString().toString());
        }

        private final void validateDedupeStages(List<DedupeStage> list) {
            if (list.isEmpty()) {
                Log.w("Discovery", "No de-duplication stages configured.");
                return;
            }
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (DedupeStage dedupeStage : list) {
                int component2 = dedupeStage.component2();
                int component3 = dedupeStage.component3();
                int component4 = dedupeStage.component4();
                boolean z = false;
                if (component2 >= 1) {
                    if (!hashSet.contains(Integer.valueOf(component2))) {
                        if (!(!hashSet2.contains(Integer.valueOf(component3)))) {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("De-duplication operator stage ids cannot be reused: ", component3).toString());
                        }
                        if (component4 >= 10) {
                            z = true;
                        }
                        if (z) {
                            hashSet.add(Integer.valueOf(component2));
                            hashSet2.add(Integer.valueOf(component3));
                        } else {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Invalid de-duplication batch size specified: ", component4).toString());
                        }
                    } else {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("De-duplication stage ids cannot be reused: ", component2).toString());
                    }
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Invalid de-duplication stage id specified: ", component2).toString());
                }
            }
        }

        @VisibleForTesting
        public final void clearComponents$AndroidPhotosDiscovery_release() {
            Discovery.components.clear();
        }

        @AnyThread
        @NotNull
        public final Discovery createInstance(@NotNull DiscoveryConfiguration configuration) {
            Intrinsics.checkParameterIsNotNull(configuration, "configuration");
            validateConfiguration(configuration);
            if (!Discovery.components.containsKey(configuration.getHashedDirectedId())) {
                Discovery discovery = new Discovery(configuration, null);
                DiscoveryComponent component = DaggerDiscoveryComponent.builder().discoveryModule(new DiscoveryModule(discovery, configuration)).dataModule(discovery.dataModule).build();
                Intrinsics.checkExpressionValueIsNotNull(component, "component");
                injectComponents$AndroidPhotosDiscovery_release(component, configuration, discovery);
                return discovery;
            }
            throw new IllegalStateException(DiscoveryKt.DISCOVERY_DESTROYED_MSG.toString());
        }

        @VisibleForTesting
        @NotNull
        public final Discovery createUninjectedDiscovery$AndroidPhotosDiscovery_release(@NotNull DiscoveryConfiguration configuration) {
            Intrinsics.checkParameterIsNotNull(configuration, "configuration");
            throw new IllegalStateException("createUninjectedDiscovery should never be called in Release".toString());
        }

        @Nullable
        public final DiscoveryComponent discoveryComponentForAccount$AndroidPhotosDiscovery_release(@NotNull String hashedDirectedId) {
            Intrinsics.checkParameterIsNotNull(hashedDirectedId, "hashedDirectedId");
            return (DiscoveryComponent) Discovery.components.get(hashedDirectedId);
        }

        @NotNull
        public final Collection<DiscoveryComponent> getAllDiscoveryComponents$AndroidPhotosDiscovery_release() {
            Collection<DiscoveryComponent> values = Discovery.components.values();
            Intrinsics.checkExpressionValueIsNotNull(values, "components.values");
            return values;
        }

        @VisibleForTesting
        public final void injectComponents$AndroidPhotosDiscovery_release(@NotNull DiscoveryComponent component, @NotNull DiscoveryConfiguration configuration, @NotNull Discovery discovery) {
            Intrinsics.checkParameterIsNotNull(component, "component");
            Intrinsics.checkParameterIsNotNull(configuration, "configuration");
            Intrinsics.checkParameterIsNotNull(discovery, "discovery");
            component.inject(discovery.getDaos());
            component.inject(discovery.getOperations());
            component.inject(discovery.getLiveWorkInfo());
            for (DedupeStage dedupeStage : configuration.getDedupeStages()) {
                Deduplicator deduplicator = dedupeStage.getDeduplicator();
                if (!(deduplicator instanceof Injectable)) {
                    deduplicator = null;
                }
                Injectable injectable = (Injectable) deduplicator;
                if (injectable != null) {
                    injectable.inject(component);
                }
            }
            Discovery.components.put(configuration.getHashedDirectedId(), component);
        }

        @VisibleForTesting
        public final void putComponent$AndroidPhotosDiscovery_release(@NotNull String directedId, @NotNull DiscoveryComponent discoveryComponent) {
            Intrinsics.checkParameterIsNotNull(directedId, "directedId");
            Intrinsics.checkParameterIsNotNull(discoveryComponent, "discoveryComponent");
            Discovery.components.put(String.valueOf(directedId.hashCode()), discoveryComponent);
        }

        @WorkerThread
        public final void shutdown(@NotNull String directedId) {
            Discovery discovery;
            Intrinsics.checkParameterIsNotNull(directedId, "directedId");
            DiscoveryComponent discoveryComponent = (DiscoveryComponent) Discovery.components.get(String.valueOf(directedId.hashCode()));
            if (discoveryComponent == null || (discovery = discoveryComponent.getDiscovery()) == null) {
                return;
            }
            discovery.destroy();
        }

        @WorkerThread
        public final void shutdownAllExcluding(@NotNull String directedId) {
            Intrinsics.checkParameterIsNotNull(directedId, "directedId");
            String valueOf = String.valueOf(directedId.hashCode());
            ConcurrentHashMap concurrentHashMap = Discovery.components;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : concurrentHashMap.entrySet()) {
                if (!Intrinsics.areEqual((String) entry.getKey(), valueOf)) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            for (DiscoveryComponent discoveryComponent : linkedHashMap.values()) {
                discoveryComponent.getDiscovery().destroy();
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private Discovery(DiscoveryConfiguration discoveryConfiguration) {
        this.configuration = discoveryConfiguration;
        this.hashedDirectedId = this.configuration.getHashedDirectedId();
        this.operations = new DiscoveryOperations(this.configuration);
        this.daos = new DiscoveryDaos();
        this.liveWorkInfo = new DiscoveryLiveWorkInfo();
        this.dataModule = new DataModule(this.configuration);
    }

    private final void throwDestroyedIfNeeded() {
        if (!this.destroyed) {
            return;
        }
        throw new IllegalStateException("Discovery was destroyed.".toString());
    }

    @Override // javax.security.auth.Destroyable
    @WorkerThread
    public void destroy() {
        components.remove(this.hashedDirectedId);
        getOperations().cancel();
        getOperations().cancelMonitor();
        getOperations().clearObservers$AndroidPhotosDiscovery_release();
        getDaos().destroy$AndroidPhotosDiscovery_release();
        this.dataModule.destroy();
        this.destroyed = true;
    }

    @NotNull
    public final DiscoveryConfiguration getConfiguration() {
        return this.configuration;
    }

    @NotNull
    public final DiscoveryDaos getDaos() {
        throwDestroyedIfNeeded();
        return this.daos;
    }

    public final boolean getDestroyed() {
        return this.destroyed;
    }

    @NotNull
    public final String getHashedDirectedId() {
        return this.hashedDirectedId;
    }

    @NotNull
    public final DiscoveryLiveWorkInfo getLiveWorkInfo() {
        throwDestroyedIfNeeded();
        return this.liveWorkInfo;
    }

    @NotNull
    public final DiscoveryOperations getOperations() {
        throwDestroyedIfNeeded();
        return this.operations;
    }

    @Override // javax.security.auth.Destroyable
    @AnyThread
    public boolean isDestroyed() {
        return this.destroyed;
    }

    public /* synthetic */ Discovery(DiscoveryConfiguration discoveryConfiguration, DefaultConstructorMarker defaultConstructorMarker) {
        this(discoveryConfiguration);
    }
}
