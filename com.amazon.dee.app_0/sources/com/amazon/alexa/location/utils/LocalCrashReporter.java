package com.amazon.alexa.location.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* loaded from: classes9.dex */
public class LocalCrashReporter implements CrashReporter {
    private static LocalCrashReporter theInstance;
    @NonNull
    private final Context context;
    @NonNull
    private final CrashReporter crashReporter;

    public LocalCrashReporter(@NonNull CrashReporter crashReporter, @NonNull Context context) {
        this.crashReporter = crashReporter;
        this.context = context;
    }

    public static LazyComponent<CrashReporter> getFactory(final LazyComponent<CrashReporter> lazyComponent, @NonNull final Context context) {
        return new LazyComponent() { // from class: com.amazon.alexa.location.utils.-$$Lambda$LocalCrashReporter$uNgEyc55lIiOu2KJ0qOujhP6LfM
            @Override // com.amazon.alexa.protocols.service.api.LazyComponent, javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return LocalCrashReporter.lambda$getFactory$0(LazyComponent.this, context);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CrashReporter lambda$getFactory$0(LazyComponent lazyComponent, Context context) {
        if (theInstance == null) {
            theInstance = new LocalCrashReporter((CrashReporter) lazyComponent.mo10268get(), context);
        }
        return theInstance;
    }

    @NonNull
    public Context getContext() {
        return this.context;
    }

    @Override // com.amazon.alexa.crashreporting.api.CrashReporter
    public void reportNonFatal(Throwable th) {
        this.crashReporter.reportNonFatal(th);
    }

    @Override // com.amazon.alexa.crashreporting.api.CrashReporter
    public void reportNonFatal(Throwable th, int i) {
        this.crashReporter.reportNonFatal(th, i);
    }
}
