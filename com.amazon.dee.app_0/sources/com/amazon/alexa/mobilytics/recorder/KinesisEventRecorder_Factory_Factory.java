package com.amazon.alexa.mobilytics.recorder;

import android.content.Context;
import com.amazon.alexa.mobilytics.recorder.KinesisEventRecorder;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class KinesisEventRecorder_Factory_Factory implements Factory<KinesisEventRecorder.Factory> {
    private final Provider<Context> contextProvider;

    public KinesisEventRecorder_Factory_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static KinesisEventRecorder_Factory_Factory create(Provider<Context> provider) {
        return new KinesisEventRecorder_Factory_Factory(provider);
    }

    public static KinesisEventRecorder.Factory newFactory(Context context) {
        return new KinesisEventRecorder.Factory(context);
    }

    public static KinesisEventRecorder.Factory provideInstance(Provider<Context> provider) {
        return new KinesisEventRecorder.Factory(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KinesisEventRecorder.Factory mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
