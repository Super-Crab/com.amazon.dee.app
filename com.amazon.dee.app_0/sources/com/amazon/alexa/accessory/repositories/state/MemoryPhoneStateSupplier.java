package com.amazon.alexa.accessory.repositories.state;

import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.HashMap;
/* loaded from: classes6.dex */
public final class MemoryPhoneStateSupplier implements PhoneStateSupplier {
    private final HashMap<Integer, PhoneStatePlugin> plugins;

    /* loaded from: classes6.dex */
    public static final class Builder {
        final HashMap<Integer, PhoneStatePlugin> plugins = new HashMap<>();

        public Builder addPlugin(StateFeature stateFeature, PhoneStatePlugin phoneStatePlugin) {
            this.plugins.put(Integer.valueOf(stateFeature.toInteger()), phoneStatePlugin);
            return this;
        }

        public MemoryPhoneStateSupplier build() {
            return new MemoryPhoneStateSupplier(this);
        }
    }

    private Iterable<Flowable<StateOuterClass.State>> getStateQueries() {
        return (Iterable) Observable.fromIterable(this.plugins.keySet()).map(new Function() { // from class: com.amazon.alexa.accessory.repositories.state.-$$Lambda$MemoryPhoneStateSupplier$YHyXUFvYc1fCS_0jPS5MjglbenA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MemoryPhoneStateSupplier.this.lambda$getStateQueries$2$MemoryPhoneStateSupplier((Integer) obj);
            }
        }).toList().blockingGet();
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStateSupplier
    public Single<StateOuterClass.State> getState(final StateFeature stateFeature) {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.repositories.state.-$$Lambda$MemoryPhoneStateSupplier$f1P3nuHE3KzNtAmBVClhmZdznCY
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return MemoryPhoneStateSupplier.this.lambda$getState$1$MemoryPhoneStateSupplier(stateFeature);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$getState$1$MemoryPhoneStateSupplier(StateFeature stateFeature) throws Throwable {
        PhoneStatePlugin phoneStatePlugin = this.plugins.get(Integer.valueOf(stateFeature.toInteger()));
        if (phoneStatePlugin == null) {
            return Single.error(new IllegalArgumentException("No plugin exists for feature " + stateFeature));
        }
        return phoneStatePlugin.getState();
    }

    public /* synthetic */ Flowable lambda$getStateQueries$2$MemoryPhoneStateSupplier(Integer num) throws Throwable {
        return this.plugins.get(num).queryState();
    }

    public /* synthetic */ CompletableSource lambda$setState$0$MemoryPhoneStateSupplier(StateOuterClass.State state) throws Throwable {
        PhoneStatePlugin phoneStatePlugin = this.plugins.get(Integer.valueOf(state.getFeature()));
        if (phoneStatePlugin == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No plugin exists for feature ");
            outline107.append(state.getFeature());
            return Completable.error(new IllegalArgumentException(outline107.toString()));
        }
        return phoneStatePlugin.setState(state);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStateSupplier
    public Flowable<StateOuterClass.State> queryStates() {
        return Flowable.mergeDelayError(getStateQueries());
    }

    @Override // com.amazon.alexa.accessory.repositories.state.PhoneStateSupplier
    public Completable setState(final StateOuterClass.State state) {
        return Completable.defer(new Supplier() { // from class: com.amazon.alexa.accessory.repositories.state.-$$Lambda$MemoryPhoneStateSupplier$_C4UgpY9lOGj-aJmraLDpScXd14
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return MemoryPhoneStateSupplier.this.lambda$setState$0$MemoryPhoneStateSupplier(state);
            }
        });
    }

    private MemoryPhoneStateSupplier(Builder builder) {
        this.plugins = builder.plugins;
    }
}
