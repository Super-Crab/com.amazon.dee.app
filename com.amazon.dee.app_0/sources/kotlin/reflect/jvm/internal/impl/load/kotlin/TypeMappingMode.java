package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.core.app.FrameMetricsAggregator;
import javax.servlet.http.HttpServletResponse;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
/* compiled from: TypeMappingMode.kt */
/* loaded from: classes3.dex */
public final class TypeMappingMode {
    private final TypeMappingMode genericArgumentMode;
    private final TypeMappingMode genericContravariantArgumentMode;
    private final TypeMappingMode genericInvariantArgumentMode;
    private final boolean isForAnnotationParameter;
    private final boolean kotlinCollectionsToJavaCollections;
    private final boolean needInlineClassWrapping;
    private final boolean needPrimitiveBoxing;
    private final boolean skipDeclarationSiteWildcards;
    private final boolean skipDeclarationSiteWildcardsIfPossible;
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final TypeMappingMode GENERIC_ARGUMENT = new TypeMappingMode(false, false, false, false, false, null, false, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    @JvmField
    @NotNull
    public static final TypeMappingMode RETURN_TYPE_BOXED = new TypeMappingMode(false, true, false, false, false, null, false, null, null, 509, null);
    @JvmField
    @NotNull
    public static final TypeMappingMode DEFAULT = new TypeMappingMode(false, false, false, false, false, GENERIC_ARGUMENT, false, null, null, 476, null);
    @JvmField
    @NotNull
    public static final TypeMappingMode CLASS_DECLARATION = new TypeMappingMode(false, true, false, false, false, GENERIC_ARGUMENT, false, null, null, 476, null);
    @JvmField
    @NotNull
    public static final TypeMappingMode SUPER_TYPE = new TypeMappingMode(false, false, false, true, false, GENERIC_ARGUMENT, false, null, null, 471, null);
    @JvmField
    @NotNull
    public static final TypeMappingMode SUPER_TYPE_KOTLIN_COLLECTIONS_AS_IS = new TypeMappingMode(false, false, false, true, false, GENERIC_ARGUMENT, false, null, null, HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED, null);
    @JvmField
    @NotNull
    public static final TypeMappingMode VALUE_FOR_ANNOTATION = new TypeMappingMode(false, false, true, false, false, new TypeMappingMode(false, false, true, false, false, GENERIC_ARGUMENT, false, null, null, 475, null), false, null, null, 472, null);

    /* compiled from: TypeMappingMode.kt */
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Variance.values().length];

        static {
            $EnumSwitchMapping$0[Variance.IN_VARIANCE.ordinal()] = 1;
            $EnumSwitchMapping$0[Variance.INVARIANT.ordinal()] = 2;
        }
    }

    private TypeMappingMode(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, TypeMappingMode typeMappingMode, boolean z6, TypeMappingMode typeMappingMode2, TypeMappingMode typeMappingMode3) {
        this.needPrimitiveBoxing = z;
        this.needInlineClassWrapping = z2;
        this.isForAnnotationParameter = z3;
        this.skipDeclarationSiteWildcards = z4;
        this.skipDeclarationSiteWildcardsIfPossible = z5;
        this.genericArgumentMode = typeMappingMode;
        this.kotlinCollectionsToJavaCollections = z6;
        this.genericContravariantArgumentMode = typeMappingMode2;
        this.genericInvariantArgumentMode = typeMappingMode3;
    }

    public final boolean getKotlinCollectionsToJavaCollections() {
        return this.kotlinCollectionsToJavaCollections;
    }

    public final boolean getNeedInlineClassWrapping() {
        return this.needInlineClassWrapping;
    }

    public final boolean getNeedPrimitiveBoxing() {
        return this.needPrimitiveBoxing;
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    @NotNull
    public final TypeMappingMode toGenericArgumentMode(@NotNull Variance effectiveVariance) {
        Intrinsics.checkParameterIsNotNull(effectiveVariance, "effectiveVariance");
        int i = WhenMappings.$EnumSwitchMapping$0[effectiveVariance.ordinal()];
        if (i == 1) {
            TypeMappingMode typeMappingMode = this.genericContravariantArgumentMode;
            if (typeMappingMode != null) {
                return typeMappingMode;
            }
        } else if (i != 2) {
            TypeMappingMode typeMappingMode2 = this.genericArgumentMode;
            if (typeMappingMode2 != null) {
                return typeMappingMode2;
            }
        } else {
            TypeMappingMode typeMappingMode3 = this.genericInvariantArgumentMode;
            if (typeMappingMode3 != null) {
                return typeMappingMode3;
            }
        }
        return this;
    }

    @NotNull
    public final TypeMappingMode wrapInlineClassesMode() {
        return new TypeMappingMode(this.needPrimitiveBoxing, true, this.isForAnnotationParameter, this.skipDeclarationSiteWildcards, this.skipDeclarationSiteWildcardsIfPossible, this.genericArgumentMode, this.kotlinCollectionsToJavaCollections, this.genericContravariantArgumentMode, this.genericInvariantArgumentMode);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /* synthetic */ TypeMappingMode(boolean r10, boolean r11, boolean r12, boolean r13, boolean r14, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode r15, boolean r16, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode r17, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode r18, int r19, kotlin.jvm.internal.DefaultConstructorMarker r20) {
        /*
            r9 = this;
            r0 = r19
            r1 = r0 & 1
            r2 = 1
            if (r1 == 0) goto L9
            r1 = r2
            goto La
        L9:
            r1 = r10
        La:
            r3 = r0 & 2
            if (r3 == 0) goto L10
            r3 = r2
            goto L11
        L10:
            r3 = r11
        L11:
            r4 = r0 & 4
            r5 = 0
            if (r4 == 0) goto L18
            r4 = r5
            goto L19
        L18:
            r4 = r12
        L19:
            r6 = r0 & 8
            if (r6 == 0) goto L1f
            r6 = r5
            goto L20
        L1f:
            r6 = r13
        L20:
            r7 = r0 & 16
            if (r7 == 0) goto L25
            goto L26
        L25:
            r5 = r14
        L26:
            r7 = r0 & 32
            if (r7 == 0) goto L2c
            r7 = 0
            goto L2d
        L2c:
            r7 = r15
        L2d:
            r8 = r0 & 64
            if (r8 == 0) goto L32
            goto L34
        L32:
            r2 = r16
        L34:
            r8 = r0 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L3a
            r8 = r7
            goto L3c
        L3a:
            r8 = r17
        L3c:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L42
            r0 = r7
            goto L44
        L42:
            r0 = r18
        L44:
            r10 = r9
            r11 = r1
            r12 = r3
            r13 = r4
            r14 = r6
            r15 = r5
            r16 = r7
            r17 = r2
            r18 = r8
            r19 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode.<init>(boolean, boolean, boolean, boolean, boolean, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode, boolean, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
