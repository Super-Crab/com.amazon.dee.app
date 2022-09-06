package kotlin.reflect.jvm.internal.impl.incremental.components;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: LookupTracker.kt */
/* loaded from: classes2.dex */
public interface LookupTracker {

    /* compiled from: LookupTracker.kt */
    /* loaded from: classes2.dex */
    public static final class DO_NOTHING implements LookupTracker {
        public static final DO_NOTHING INSTANCE = new DO_NOTHING();

        private DO_NOTHING() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker
        public boolean getRequiresPosition() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker
        public void record(@NotNull String filePath, @NotNull Position position, @NotNull String scopeFqName, @NotNull ScopeKind scopeKind, @NotNull String name) {
            Intrinsics.checkParameterIsNotNull(filePath, "filePath");
            Intrinsics.checkParameterIsNotNull(position, "position");
            Intrinsics.checkParameterIsNotNull(scopeFqName, "scopeFqName");
            Intrinsics.checkParameterIsNotNull(scopeKind, "scopeKind");
            Intrinsics.checkParameterIsNotNull(name, "name");
        }
    }

    boolean getRequiresPosition();

    void record(@NotNull String str, @NotNull Position position, @NotNull String str2, @NotNull ScopeKind scopeKind, @NotNull String str3);
}
