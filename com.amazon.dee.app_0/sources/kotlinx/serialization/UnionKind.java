package kotlinx.serialization;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.StructureKind;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialKinds.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00042\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lkotlinx/serialization/UnionKind;", "Lkotlinx/serialization/SerialKind;", "()V", "CONTEXTUAL", "Companion", "ENUM_KIND", "Lkotlinx/serialization/UnionKind$ENUM_KIND;", "Lkotlinx/serialization/UnionKind$CONTEXTUAL;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class UnionKind extends SerialKind {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final StructureKind.OBJECT OBJECT = StructureKind.OBJECT.INSTANCE;

    /* compiled from: SerialKinds.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lkotlinx/serialization/UnionKind$CONTEXTUAL;", "Lkotlinx/serialization/UnionKind;", "()V", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class CONTEXTUAL extends UnionKind {
        public static final CONTEXTUAL INSTANCE = new CONTEXTUAL();

        private CONTEXTUAL() {
            super(null);
        }
    }

    /* compiled from: SerialKinds.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlinx/serialization/UnionKind$Companion;", "", "()V", "OBJECT", "Lkotlinx/serialization/StructureKind$OBJECT;", "OBJECT$annotations", "getOBJECT", "()Lkotlinx/serialization/StructureKind$OBJECT;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Moved out from UnionKind to StructureKind.", replaceWith = @ReplaceWith(expression = "StructureKind.OBJECT", imports = {}))
        public static /* synthetic */ void OBJECT$annotations() {
        }

        @NotNull
        public final StructureKind.OBJECT getOBJECT() {
            return UnionKind.OBJECT;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: SerialKinds.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lkotlinx/serialization/UnionKind$ENUM_KIND;", "Lkotlinx/serialization/UnionKind;", "()V", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class ENUM_KIND extends UnionKind {
        public static final ENUM_KIND INSTANCE = new ENUM_KIND();

        private ENUM_KIND() {
            super(null);
        }
    }

    private UnionKind() {
        super(null);
    }

    public /* synthetic */ UnionKind(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
