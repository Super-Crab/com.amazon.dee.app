package com.amazon.alexa.crashreporting;

import androidx.annotation.NonNull;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import java.util.Objects;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CrashReportingMetadata implements CrashMetadata {
    private Provider<CrashReportingService> crashReportingService;

    public CrashReportingMetadata(Provider<CrashReportingService> provider) {
        this.crashReportingService = provider;
    }

    @Override // com.amazon.alexa.crashreporting.api.CrashMetadata
    public void put(@NonNull String str, @NonNull String str2) {
        this.crashReportingService.mo10268get().putMetadata(str, (String) Objects.requireNonNull(str2));
    }

    @Override // com.amazon.alexa.crashreporting.api.CrashMetadata
    public void put(@NonNull String str, boolean z) {
        this.crashReportingService.mo10268get().putMetadata(str, String.valueOf(z));
    }

    @Override // com.amazon.alexa.crashreporting.api.CrashMetadata
    public void put(@NonNull String str, int i) {
        this.crashReportingService.mo10268get().putMetadata(str, String.valueOf(i));
    }

    @Override // com.amazon.alexa.crashreporting.api.CrashMetadata
    public void put(@NonNull String str, long j) {
        this.crashReportingService.mo10268get().putMetadata(str, String.valueOf(j));
    }

    @Override // com.amazon.alexa.crashreporting.api.CrashMetadata
    public void put(@NonNull String str, float f) {
        this.crashReportingService.mo10268get().putMetadata(str, String.valueOf(f));
    }

    @Override // com.amazon.alexa.crashreporting.api.CrashMetadata
    public void put(@NonNull String str, double d) {
        this.crashReportingService.mo10268get().putMetadata(str, String.valueOf(d));
    }
}
