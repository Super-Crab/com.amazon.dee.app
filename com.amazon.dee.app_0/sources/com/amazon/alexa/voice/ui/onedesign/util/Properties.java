package com.amazon.alexa.voice.ui.onedesign.util;

import com.amazon.alexa.voice.ui.util.AlexaState;
import com.amazon.alexa.voice.ui.util.AlexaStateProperty;
import com.amazon.alexa.voice.ui.util.BaseProperty;
import com.amazon.alexa.voice.ui.util.BooleanProperty;
import com.amazon.alexa.voice.ui.util.FloatProperty;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
/* loaded from: classes11.dex */
public final class Properties {
    private Properties() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(FloatProperty floatProperty, ObservableEmitter observableEmitter) {
        if (floatProperty.hasValue()) {
            observableEmitter.onNext(Float.valueOf(floatProperty.get()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$3(BooleanProperty booleanProperty, ObservableEmitter observableEmitter) {
        if (booleanProperty.hasValue()) {
            observableEmitter.onNext(Boolean.valueOf(booleanProperty.get()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$6(AlexaStateProperty alexaStateProperty, ObservableEmitter observableEmitter) {
        if (alexaStateProperty.hasValue()) {
            observableEmitter.onNext(alexaStateProperty.get());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$toObservable$2(final FloatProperty floatProperty, final ObservableEmitter observableEmitter) throws Throwable {
        final BaseProperty.OnChangedListener onChangedListener = new BaseProperty.OnChangedListener() { // from class: com.amazon.alexa.voice.ui.onedesign.util.-$$Lambda$Properties$Pz5P_0XiYx5LQe7kGZaFKPc2QYc
            @Override // com.amazon.alexa.voice.ui.util.BaseProperty.OnChangedListener
            public final void onPropertyChanged() {
                Properties.lambda$null$0(FloatProperty.this, observableEmitter);
            }
        };
        floatProperty.addOnChangedListener(onChangedListener);
        observableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.voice.ui.onedesign.util.-$$Lambda$Properties$StKK-E_Oi7aZXZd0EgDgeiFp5BQ
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                FloatProperty.this.removeOnChangedListener(onChangedListener);
            }
        });
        if (floatProperty.hasValue()) {
            observableEmitter.onNext(Float.valueOf(floatProperty.get()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$toObservable$5(final BooleanProperty booleanProperty, final ObservableEmitter observableEmitter) throws Throwable {
        final BaseProperty.OnChangedListener onChangedListener = new BaseProperty.OnChangedListener() { // from class: com.amazon.alexa.voice.ui.onedesign.util.-$$Lambda$Properties$gOxraNnswYYK9TTlwoOJI04AKew
            @Override // com.amazon.alexa.voice.ui.util.BaseProperty.OnChangedListener
            public final void onPropertyChanged() {
                Properties.lambda$null$3(BooleanProperty.this, observableEmitter);
            }
        };
        booleanProperty.addOnChangedListener(onChangedListener);
        observableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.voice.ui.onedesign.util.-$$Lambda$Properties$-ZmD3j94VojS3L3yCtlqrxWC9c0
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                BooleanProperty.this.removeOnChangedListener(onChangedListener);
            }
        });
        if (booleanProperty.hasValue()) {
            observableEmitter.onNext(Boolean.valueOf(booleanProperty.get()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$toObservable$8(final AlexaStateProperty alexaStateProperty, final ObservableEmitter observableEmitter) throws Throwable {
        final BaseProperty.OnChangedListener onChangedListener = new BaseProperty.OnChangedListener() { // from class: com.amazon.alexa.voice.ui.onedesign.util.-$$Lambda$Properties$ifkRmfcLjJBFTT2tGi1XIatnv5Y
            @Override // com.amazon.alexa.voice.ui.util.BaseProperty.OnChangedListener
            public final void onPropertyChanged() {
                Properties.lambda$null$6(AlexaStateProperty.this, observableEmitter);
            }
        };
        alexaStateProperty.addOnChangedListener(onChangedListener);
        observableEmitter.setCancellable(new Cancellable() { // from class: com.amazon.alexa.voice.ui.onedesign.util.-$$Lambda$Properties$T8ZKwgCxKE5lNa5mTW-PPmBZewE
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                AlexaStateProperty.this.removeOnChangedListener(onChangedListener);
            }
        });
        if (alexaStateProperty.hasValue()) {
            observableEmitter.onNext(alexaStateProperty.get());
        }
    }

    public static Observable<Float> toObservable(final FloatProperty floatProperty) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.voice.ui.onedesign.util.-$$Lambda$Properties$IEJpY6IjzaVl6OD1tv0Fp2mHKrw
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                Properties.lambda$toObservable$2(FloatProperty.this, observableEmitter);
            }
        });
    }

    public static Observable<Boolean> toObservable(final BooleanProperty booleanProperty) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.voice.ui.onedesign.util.-$$Lambda$Properties$KN0iGDbubAikJTP3GF6w1mPtb_Q
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                Properties.lambda$toObservable$5(BooleanProperty.this, observableEmitter);
            }
        });
    }

    public static Observable<AlexaState> toObservable(final AlexaStateProperty alexaStateProperty) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.voice.ui.onedesign.util.-$$Lambda$Properties$oe8ZKOKWDZ-sGoaJZuuZ4lzk_vs
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                Properties.lambda$toObservable$8(AlexaStateProperty.this, observableEmitter);
            }
        });
    }
}
