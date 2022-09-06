package kotlin.reflect.jvm.internal.impl.utils;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Jsr305State.kt */
/* loaded from: classes4.dex */
public final class Jsr305State {
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final Jsr305State DEFAULT;
    @JvmField
    @NotNull
    public static final Jsr305State DISABLED;
    @JvmField
    @NotNull
    public static final Jsr305State STRICT;
    @NotNull
    private final Lazy description$delegate;
    private final boolean enableCompatqualCheckerFrameworkAnnotations;
    @NotNull
    private final ReportLevel global;
    @Nullable
    private final ReportLevel migration;
    @NotNull
    private final Map<String, ReportLevel> user;

    /* compiled from: Jsr305State.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Map emptyMap;
        Map emptyMap2;
        Map emptyMap3;
        ReportLevel reportLevel = ReportLevel.WARN;
        emptyMap = MapsKt__MapsKt.emptyMap();
        DEFAULT = new Jsr305State(reportLevel, null, emptyMap, false, 8, null);
        ReportLevel reportLevel2 = ReportLevel.IGNORE;
        emptyMap2 = MapsKt__MapsKt.emptyMap();
        DISABLED = new Jsr305State(reportLevel2, reportLevel2, emptyMap2, false, 8, null);
        ReportLevel reportLevel3 = ReportLevel.STRICT;
        emptyMap3 = MapsKt__MapsKt.emptyMap();
        STRICT = new Jsr305State(reportLevel3, reportLevel3, emptyMap3, false, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Jsr305State(@NotNull ReportLevel global, @Nullable ReportLevel reportLevel, @NotNull Map<String, ? extends ReportLevel> user, boolean z) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(global, "global");
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.global = global;
        this.migration = reportLevel;
        this.user = user;
        this.enableCompatqualCheckerFrameworkAnnotations = z;
        lazy = LazyKt__LazyJVMKt.lazy(new Jsr305State$description$2(this));
        this.description$delegate = lazy;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Jsr305State)) {
                return false;
            }
            Jsr305State jsr305State = (Jsr305State) obj;
            return Intrinsics.areEqual(this.global, jsr305State.global) && Intrinsics.areEqual(this.migration, jsr305State.migration) && Intrinsics.areEqual(this.user, jsr305State.user) && this.enableCompatqualCheckerFrameworkAnnotations == jsr305State.enableCompatqualCheckerFrameworkAnnotations;
        }
        return true;
    }

    public final boolean getDisabled() {
        return this == DISABLED;
    }

    public final boolean getEnableCompatqualCheckerFrameworkAnnotations() {
        return this.enableCompatqualCheckerFrameworkAnnotations;
    }

    @NotNull
    public final ReportLevel getGlobal() {
        return this.global;
    }

    @Nullable
    public final ReportLevel getMigration() {
        return this.migration;
    }

    @NotNull
    public final Map<String, ReportLevel> getUser() {
        return this.user;
    }

    public int hashCode() {
        ReportLevel reportLevel = this.global;
        int i = 0;
        int hashCode = (reportLevel != null ? reportLevel.hashCode() : 0) * 31;
        ReportLevel reportLevel2 = this.migration;
        int hashCode2 = (hashCode + (reportLevel2 != null ? reportLevel2.hashCode() : 0)) * 31;
        Map<String, ReportLevel> map = this.user;
        if (map != null) {
            i = map.hashCode();
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z = this.enableCompatqualCheckerFrameworkAnnotations;
        if (z) {
            z = true;
        }
        int i3 = z ? 1 : 0;
        int i4 = z ? 1 : 0;
        return i2 + i3;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Jsr305State(global=");
        outline107.append(this.global);
        outline107.append(", migration=");
        outline107.append(this.migration);
        outline107.append(", user=");
        outline107.append(this.user);
        outline107.append(", enableCompatqualCheckerFrameworkAnnotations=");
        return GeneratedOutlineSupport1.outline97(outline107, this.enableCompatqualCheckerFrameworkAnnotations, ")");
    }

    public /* synthetic */ Jsr305State(ReportLevel reportLevel, ReportLevel reportLevel2, Map map, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reportLevel, reportLevel2, map, (i & 8) != 0 ? true : z);
    }
}
