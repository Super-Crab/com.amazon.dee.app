package com.amazon.alexa.client.crashreporting.bugsnag;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.crashreporting.BreadcrumbType;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.bugsnag.android.Bugsnag;
import java.util.Map;
import java.util.Random;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BugsnagCrashReporter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0012¢\u0006\u0002\u0010\u0002B\u000f\b\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J,\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/client/crashreporting/bugsnag/BugsnagCrashReporter;", "Lcom/amazon/alexa/client/crashreporting/CrashReporter;", "()V", "random", "Ljava/util/Random;", "(Ljava/util/Random;)V", "clearBreadcrumbs", "", "leaveBreadcrumb", "name", "", "breadcrumbType", "Lcom/amazon/alexa/client/crashreporting/BreadcrumbType;", "metadata", "", "notifyHandledException", "throwable", "", "probability", "", "putMetadata", "key", "value", "", "Companion", "AVSAndroidClient-crashreporting_bugsnagRelease"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public class BugsnagCrashReporter implements CrashReporter {
    public static final Companion Companion = new Companion(null);
    private static final String TAB_NAME = "Alexa";
    private final Random random;

    /* compiled from: BugsnagCrashReporter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/client/crashreporting/bugsnag/BugsnagCrashReporter$Companion;", "", "()V", "TAB_NAME", "", "create", "Lcom/amazon/alexa/client/crashreporting/bugsnag/BugsnagCrashReporter;", "AVSAndroidClient-crashreporting_bugsnagRelease"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final BugsnagCrashReporter create() {
            return new BugsnagCrashReporter((DefaultConstructorMarker) null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BreadcrumbType.values().length];

        static {
            $EnumSwitchMapping$0[BreadcrumbType.ERROR.ordinal()] = 1;
            $EnumSwitchMapping$0[BreadcrumbType.LOG.ordinal()] = 2;
            $EnumSwitchMapping$0[BreadcrumbType.MANUAL.ordinal()] = 3;
            $EnumSwitchMapping$0[BreadcrumbType.NAVIGATION.ordinal()] = 4;
            $EnumSwitchMapping$0[BreadcrumbType.PROCESS.ordinal()] = 5;
            $EnumSwitchMapping$0[BreadcrumbType.REQUEST.ordinal()] = 6;
            $EnumSwitchMapping$0[BreadcrumbType.STATE.ordinal()] = 7;
            $EnumSwitchMapping$0[BreadcrumbType.USER.ordinal()] = 8;
        }
    }

    @VisibleForTesting
    public BugsnagCrashReporter(@NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(random, "random");
        this.random = random;
    }

    @JvmStatic
    @NotNull
    public static final BugsnagCrashReporter create() {
        return Companion.create();
    }

    @Override // com.amazon.alexa.client.crashreporting.CrashReporter
    public void clearBreadcrumbs() {
        Bugsnag.clearBreadcrumbs();
    }

    @Override // com.amazon.alexa.client.crashreporting.CrashReporter
    public void leaveBreadcrumb(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Bugsnag.leaveBreadcrumb(name);
    }

    @Override // com.amazon.alexa.client.crashreporting.CrashReporter
    public void notifyHandledException(@NotNull Throwable throwable) {
        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
        Bugsnag.notify(throwable);
    }

    @Override // com.amazon.alexa.client.crashreporting.CrashReporter
    public void putMetadata(@NotNull String key, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Bugsnag.getMetaData().addToTab("Alexa", key, obj);
    }

    public /* synthetic */ BugsnagCrashReporter(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.amazon.alexa.client.crashreporting.CrashReporter
    public void leaveBreadcrumb(@NotNull String name, @NotNull BreadcrumbType breadcrumbType, @NotNull Map<String, String> metadata) {
        com.bugsnag.android.BreadcrumbType breadcrumbType2;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(breadcrumbType, "breadcrumbType");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        switch (WhenMappings.$EnumSwitchMapping$0[breadcrumbType.ordinal()]) {
            case 1:
                breadcrumbType2 = com.bugsnag.android.BreadcrumbType.ERROR;
                break;
            case 2:
                breadcrumbType2 = com.bugsnag.android.BreadcrumbType.LOG;
                break;
            case 3:
                breadcrumbType2 = com.bugsnag.android.BreadcrumbType.MANUAL;
                break;
            case 4:
                breadcrumbType2 = com.bugsnag.android.BreadcrumbType.NAVIGATION;
                break;
            case 5:
                breadcrumbType2 = com.bugsnag.android.BreadcrumbType.PROCESS;
                break;
            case 6:
                breadcrumbType2 = com.bugsnag.android.BreadcrumbType.REQUEST;
                break;
            case 7:
                breadcrumbType2 = com.bugsnag.android.BreadcrumbType.STATE;
                break;
            case 8:
                breadcrumbType2 = com.bugsnag.android.BreadcrumbType.USER;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        Bugsnag.leaveBreadcrumb(name, breadcrumbType2, metadata);
    }

    @Override // com.amazon.alexa.client.crashreporting.CrashReporter
    public void notifyHandledException(@NotNull Throwable throwable, double d) {
        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
        if (this.random.nextDouble() <= d) {
            notifyHandledException(throwable);
        }
    }

    private BugsnagCrashReporter() {
        this(new Random());
    }
}
