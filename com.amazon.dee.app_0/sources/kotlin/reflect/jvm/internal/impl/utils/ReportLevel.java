package kotlin.reflect.jvm.internal.impl.utils;

import com.amazon.alexa.mobilytics.event.LogLevel;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: Jsr305State.kt */
/* loaded from: classes4.dex */
public enum ReportLevel {
    IGNORE("ignore"),
    WARN(LogLevel.WARN),
    STRICT("strict");
    
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String description;

    /* compiled from: Jsr305State.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    ReportLevel(String str) {
        this.description = str;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    public final boolean isIgnore() {
        return this == IGNORE;
    }

    public final boolean isWarning() {
        return this == WARN;
    }
}
