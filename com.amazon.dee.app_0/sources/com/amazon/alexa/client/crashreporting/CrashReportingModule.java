package com.amazon.alexa.client.crashreporting;

import com.amazon.alexa.client.crashreporting.bugsnag.BugsnagCrashReporter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: CrashReportingModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/client/crashreporting/CrashReportingModule;", "", "()V", "providesCrashReporter", "Lcom/amazon/alexa/client/crashreporting/CrashReporter;", "AVSAndroidClient-crashreporting_bugsnagRelease"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes6.dex */
public final class CrashReportingModule {
    @Provides
    @Singleton
    @NotNull
    public final CrashReporter providesCrashReporter() {
        return BugsnagCrashReporter.Companion.create();
    }
}
