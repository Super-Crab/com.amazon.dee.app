package com.amazon.alexa.accessorykit.interprocess;

import android.content.Context;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public class InterprocessPhoneStatePlugin implements PhoneStatePlugin {
    private final Context context;
    private final StateOuterClass.State defaultValue;
    private final StateFeature feature;
    private final PhoneStatePlugin localPhoneStatePlugin;

    public InterprocessPhoneStatePlugin(Context context, StateFeature stateFeature, StateOuterClass.State state, PhoneStatePlugin phoneStatePlugin) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(stateFeature, "feature");
        Preconditions.notNull(state, "defaultValue");
        this.context = context;
        this.feature = stateFeature;
        this.defaultValue = state;
        this.localPhoneStatePlugin = phoneStatePlugin;
    }

    private RxMapStore<String, PersistedState> getRemoteCache() {
        return RemoteStateCacheHolder.getRemoteStateCache(this.context, this.feature);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Single<StateOuterClass.State> getState() {
        Single map = getRemoteCache().get(Integer.toString(this.defaultValue.getFeature())).map(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPhoneStatePlugin$e-KNLTDtvXMBV_ZTJNc3ammU-w4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return InterprocessPhoneStatePlugin.this.lambda$getState$2$InterprocessPhoneStatePlugin((Set) obj);
            }
        });
        return this.localPhoneStatePlugin == null ? map : map.flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPhoneStatePlugin$tSciBFne_sR8YgL3dEkkTP19HEU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return InterprocessPhoneStatePlugin.this.lambda$getState$3$InterprocessPhoneStatePlugin((StateOuterClass.State) obj);
            }
        });
    }

    public /* synthetic */ StateOuterClass.State lambda$getState$2$InterprocessPhoneStatePlugin(Set set) throws Throwable {
        if (set.isEmpty()) {
            return this.defaultValue;
        }
        return ((PersistedState) set.iterator().next()).state;
    }

    public /* synthetic */ SingleSource lambda$getState$3$InterprocessPhoneStatePlugin(StateOuterClass.State state) throws Throwable {
        if (!state.equals(this.defaultValue)) {
            return Single.just(state);
        }
        return this.localPhoneStatePlugin.getState();
    }

    public /* synthetic */ StateOuterClass.State lambda$queryState$0$InterprocessPhoneStatePlugin(Map map) throws Throwable {
        Set set = (Set) map.get(Integer.toString(this.feature.toInteger()));
        if (set != null && !set.isEmpty()) {
            return ((PersistedState) set.iterator().next()).state;
        }
        return this.defaultValue;
    }

    public /* synthetic */ StateOuterClass.State lambda$queryState$1$InterprocessPhoneStatePlugin(StateOuterClass.State state, StateOuterClass.State state2) throws Throwable {
        return !this.defaultValue.equals(state2) ? state2 : state;
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Flowable<StateOuterClass.State> queryState() {
        Flowable<StateOuterClass.State> skip = getRemoteCache().queryValues().map(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPhoneStatePlugin$-pzEjAqNL30_MFqeJmjJlR3lgVY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return InterprocessPhoneStatePlugin.this.lambda$queryState$0$InterprocessPhoneStatePlugin((Map) obj);
            }
        }).toFlowable(BackpressureStrategy.BUFFER).skip(1L);
        PhoneStatePlugin phoneStatePlugin = this.localPhoneStatePlugin;
        return phoneStatePlugin == null ? skip : Flowable.combineLatest(phoneStatePlugin.queryState().startWithItem(this.defaultValue), skip.startWithItem(this.defaultValue), new BiFunction() { // from class: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPhoneStatePlugin$kIQoPNFZ3Lyfrco1NcLMn6wPIJY
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return InterprocessPhoneStatePlugin.this.lambda$queryState$1$InterprocessPhoneStatePlugin((StateOuterClass.State) obj, (StateOuterClass.State) obj2);
            }
        }).skip(1L);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStatePlugin
    public Completable setState(StateOuterClass.State state) {
        PhoneStatePlugin phoneStatePlugin = this.localPhoneStatePlugin;
        if (phoneStatePlugin != null) {
            return phoneStatePlugin.setState(state);
        }
        return Completable.error(new IOException("Not supported"));
    }

    public InterprocessPhoneStatePlugin(Context context, StateFeature stateFeature, StateOuterClass.State state) {
        this(context, stateFeature, state, null);
    }
}
