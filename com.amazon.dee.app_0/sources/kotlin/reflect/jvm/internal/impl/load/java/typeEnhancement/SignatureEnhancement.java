package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNamesKt;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.AnnotationDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.NullDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.StringDefaultValue;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes3.dex */
public final class SignatureEnhancement {
    private final AnnotationTypeQualifierResolver annotationTypeQualifierResolver;
    private final Jsr305State jsr305State;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: signatureEnhancement.kt */
    /* loaded from: classes3.dex */
    public static class PartEnhancementResult {
        private final boolean containsFunctionN;
        @NotNull
        private final KotlinType type;
        private final boolean wereChanges;

        public PartEnhancementResult(@NotNull KotlinType type, boolean z, boolean z2) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.type = type;
            this.wereChanges = z;
            this.containsFunctionN = z2;
        }

        public final boolean getContainsFunctionN() {
            return this.containsFunctionN;
        }

        @NotNull
        public final KotlinType getType() {
            return this.type;
        }

        public final boolean getWereChanges() {
            return this.wereChanges;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: signatureEnhancement.kt */
    /* loaded from: classes3.dex */
    public final class SignatureParts {
        private final AnnotationTypeQualifierResolver.QualifierApplicabilityType containerApplicabilityType;
        private final LazyJavaResolverContext containerContext;
        private final Collection<KotlinType> fromOverridden;
        private final KotlinType fromOverride;
        private final boolean isCovariant;
        final /* synthetic */ SignatureEnhancement this$0;
        private final Annotated typeContainer;

        /* JADX WARN: Multi-variable type inference failed */
        public SignatureParts(@Nullable SignatureEnhancement signatureEnhancement, @NotNull Annotated annotated, @NotNull KotlinType fromOverride, Collection<? extends KotlinType> fromOverridden, @NotNull boolean z, @NotNull LazyJavaResolverContext containerContext, AnnotationTypeQualifierResolver.QualifierApplicabilityType containerApplicabilityType) {
            Intrinsics.checkParameterIsNotNull(fromOverride, "fromOverride");
            Intrinsics.checkParameterIsNotNull(fromOverridden, "fromOverridden");
            Intrinsics.checkParameterIsNotNull(containerContext, "containerContext");
            Intrinsics.checkParameterIsNotNull(containerApplicabilityType, "containerApplicabilityType");
            this.this$0 = signatureEnhancement;
            this.typeContainer = annotated;
            this.fromOverride = fromOverride;
            this.fromOverridden = fromOverridden;
            this.isCovariant = z;
            this.containerContext = containerContext;
            this.containerApplicabilityType = containerApplicabilityType;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers> computeIndexedQualifiersForOverride() {
            /*
                r14 = this;
                java.util.Collection<kotlin.reflect.jvm.internal.impl.types.KotlinType> r0 = r14.fromOverridden
                java.util.ArrayList r1 = new java.util.ArrayList
                r2 = 10
                int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r2)
                r1.<init>(r2)
                java.util.Iterator r0 = r0.iterator()
            L11:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L25
                java.lang.Object r2 = r0.next()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r2
                java.util.List r2 = r14.toIndexed(r2)
                r1.add(r2)
                goto L11
            L25:
                kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r14.fromOverride
                java.util.List r0 = r14.toIndexed(r0)
                boolean r2 = r14.isCovariant
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L5f
                java.util.Collection<kotlin.reflect.jvm.internal.impl.types.KotlinType> r2 = r14.fromOverridden
                boolean r5 = r2 instanceof java.util.Collection
                if (r5 == 0) goto L3f
                boolean r5 = r2.isEmpty()
                if (r5 == 0) goto L3f
            L3d:
                r2 = r3
                goto L5b
            L3f:
                java.util.Iterator r2 = r2.iterator()
            L43:
                boolean r5 = r2.hasNext()
                if (r5 == 0) goto L3d
                java.lang.Object r5 = r2.next()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r5 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r5
                kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker r6 = kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.DEFAULT
                kotlin.reflect.jvm.internal.impl.types.KotlinType r7 = r14.fromOverride
                boolean r5 = r6.equalTypes(r5, r7)
                r5 = r5 ^ r4
                if (r5 == 0) goto L43
                r2 = r4
            L5b:
                if (r2 == 0) goto L5f
                r2 = r4
                goto L60
            L5f:
                r2 = r3
            L60:
                if (r2 == 0) goto L64
                r5 = r4
                goto L68
            L64:
                int r5 = r0.size()
            L68:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers[] r6 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers[r5]
                r7 = r3
            L6b:
                if (r7 >= r5) goto Lc9
                if (r7 != 0) goto L71
                r8 = r4
                goto L72
            L71:
                r8 = r3
            L72:
                if (r8 != 0) goto L79
                if (r2 != 0) goto L77
                goto L79
            L77:
                r9 = r3
                goto L7a
            L79:
                r9 = r4
            L7a:
                boolean r10 = kotlin._Assertions.ENABLED
                if (r10 == 0) goto L89
                if (r9 == 0) goto L81
                goto L89
            L81:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                java.lang.String r1 = "Only head type constructors should be computed"
                r0.<init>(r1)
                throw r0
            L89:
                java.lang.Object r9 = r0.get(r7)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers r9 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers) r9
                kotlin.reflect.jvm.internal.impl.types.KotlinType r10 = r9.component1()
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r9 = r9.component2()
                java.util.ArrayList r11 = new java.util.ArrayList
                r11.<init>()
                java.util.Iterator r12 = r1.iterator()
            La0:
                boolean r13 = r12.hasNext()
                if (r13 == 0) goto Lc0
                java.lang.Object r13 = r12.next()
                java.util.List r13 = (java.util.List) r13
                java.lang.Object r13 = kotlin.collections.CollectionsKt.getOrNull(r13, r7)
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers r13 = (kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeAndDefaultQualifiers) r13
                if (r13 == 0) goto Lb9
                kotlin.reflect.jvm.internal.impl.types.KotlinType r13 = r13.getType()
                goto Lba
            Lb9:
                r13 = 0
            Lba:
                if (r13 == 0) goto La0
                r11.add(r13)
                goto La0
            Lc0:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r8 = r14.computeQualifiersForOverride(r10, r11, r9, r8)
                r6[r7] = r8
                int r7 = r7 + 1
                goto L6b
            Lc9:
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1 r0 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1
                r0.<init>(r6)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.computeIndexedQualifiersForOverride():kotlin.jvm.functions.Function1");
        }

        /* JADX WARN: Removed duplicated region for block: B:75:0x0128  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers computeQualifiersForOverride(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r11, java.util.Collection<? extends kotlin.reflect.jvm.internal.impl.types.KotlinType> r12, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r13, boolean r14) {
            /*
                Method dump skipped, instructions count: 302
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.computeQualifiersForOverride(kotlin.reflect.jvm.internal.impl.types.KotlinType, java.util.Collection, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers, boolean):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers");
        }

        public static /* synthetic */ PartEnhancementResult enhance$default(SignatureParts signatureParts, TypeEnhancementInfo typeEnhancementInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                typeEnhancementInfo = null;
            }
            return signatureParts.enhance(typeEnhancementInfo);
        }

        private final NullabilityQualifierWithMigrationStatus extractNullability(@NotNull Annotations annotations) {
            SignatureEnhancement signatureEnhancement = this.this$0;
            Iterator<AnnotationDescriptor> it2 = annotations.iterator();
            while (it2.hasNext()) {
                NullabilityQualifierWithMigrationStatus extractNullability = signatureEnhancement.extractNullability(it2.next());
                if (extractNullability != null) {
                    return extractNullability;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers extractQualifiers(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r12) {
            /*
                r11 = this;
                boolean r0 = kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt.isFlexible(r12)
                if (r0 == 0) goto L18
                kotlin.reflect.jvm.internal.impl.types.FlexibleType r0 = kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt.asFlexibleType(r12)
                kotlin.Pair r1 = new kotlin.Pair
                kotlin.reflect.jvm.internal.impl.types.SimpleType r2 = r0.getLowerBound()
                kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = r0.getUpperBound()
                r1.<init>(r2, r0)
                goto L1d
            L18:
                kotlin.Pair r1 = new kotlin.Pair
                r1.<init>(r12, r12)
            L1d:
                java.lang.Object r0 = r1.component1()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
                java.lang.Object r1 = r1.component2()
                kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r1
                kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap r2 = kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap.INSTANCE
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers r10 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers
                boolean r3 = r0.isMarkedNullable()
                r4 = 0
                if (r3 == 0) goto L38
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE
            L36:
                r5 = r3
                goto L42
            L38:
                boolean r3 = r1.isMarkedNullable()
                if (r3 != 0) goto L41
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
                goto L36
            L41:
                r5 = r4
            L42:
                boolean r0 = r2.isReadOnly(r0)
                if (r0 == 0) goto L4b
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r0 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier.READ_ONLY
                goto L55
            L4b:
                boolean r0 = r2.isMutable(r1)
                if (r0 == 0) goto L54
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier r0 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.MutabilityQualifier.MUTABLE
                goto L55
            L54:
                r0 = r4
            L55:
                kotlin.reflect.jvm.internal.impl.types.UnwrappedType r12 = r12.unwrap()
                boolean r6 = r12 instanceof kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NotNullTypeParameter
                r7 = 0
                r8 = 8
                r9 = 0
                r3 = r10
                r4 = r5
                r5 = r0
                r3.<init>(r4, r5, r6, r7, r8, r9)
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.SignatureParts.extractQualifiers(kotlin.reflect.jvm.internal.impl.types.KotlinType):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeQualifiers");
        }

        private final JavaTypeQualifiers extractQualifiersFromAnnotations(@NotNull KotlinType kotlinType, boolean z, JavaTypeQualifiers javaTypeQualifiers) {
            Annotations mo12070getAnnotations;
            Annotated annotated;
            if (z && (annotated = this.typeContainer) != null) {
                mo12070getAnnotations = AnnotationsKt.composeAnnotations(annotated.mo12070getAnnotations(), kotlinType.mo12070getAnnotations());
            } else {
                mo12070getAnnotations = kotlinType.mo12070getAnnotations();
            }
            SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1 signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1 = new SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1(mo12070getAnnotations);
            SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 = SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2.INSTANCE;
            NullabilityQualifier nullabilityQualifier = null;
            if (z) {
                JavaTypeQualifiersByElementType defaultTypeQualifiers = this.containerContext.getDefaultTypeQualifiers();
                javaTypeQualifiers = defaultTypeQualifiers != null ? defaultTypeQualifiers.get(this.containerApplicabilityType) : null;
            }
            NullabilityQualifierWithMigrationStatus extractNullability = extractNullability(mo12070getAnnotations);
            if (extractNullability == null) {
                extractNullability = (javaTypeQualifiers == null || javaTypeQualifiers.getNullability() == null) ? null : new NullabilityQualifierWithMigrationStatus(javaTypeQualifiers.getNullability(), javaTypeQualifiers.isNullabilityQualifierForWarning());
            }
            NullabilityQualifier qualifier = extractNullability != null ? extractNullability.getQualifier() : null;
            MutabilityQualifier mutabilityQualifier = (MutabilityQualifier) signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2.mo12248invoke(signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1.invoke2(JvmAnnotationNamesKt.getREAD_ONLY_ANNOTATIONS(), (List<FqName>) MutabilityQualifier.READ_ONLY), signatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1.invoke2(JvmAnnotationNamesKt.getMUTABLE_ANNOTATIONS(), (List<FqName>) MutabilityQualifier.MUTABLE));
            if (extractNullability != null) {
                nullabilityQualifier = extractNullability.getQualifier();
            }
            boolean z2 = false;
            boolean z3 = nullabilityQualifier == NullabilityQualifier.NOT_NULL && TypeUtilsKt.isTypeParameter(kotlinType);
            if (extractNullability != null && extractNullability.isForWarningOnly()) {
                z2 = true;
            }
            return new JavaTypeQualifiers(qualifier, mutabilityQualifier, z3, z2);
        }

        private final boolean isForVarargParameter() {
            Annotated annotated = this.typeContainer;
            KotlinType kotlinType = null;
            if (!(annotated instanceof ValueParameterDescriptor)) {
                annotated = null;
            }
            ValueParameterDescriptor valueParameterDescriptor = (ValueParameterDescriptor) annotated;
            if (valueParameterDescriptor != null) {
                kotlinType = valueParameterDescriptor.getVarargElementType();
            }
            return kotlinType != null;
        }

        private final List<TypeAndDefaultQualifiers> toIndexed(@NotNull KotlinType kotlinType) {
            ArrayList arrayList = new ArrayList(1);
            new SignatureEnhancement$SignatureParts$toIndexed$1(arrayList).invoke2(kotlinType, this.containerContext);
            return arrayList;
        }

        @NotNull
        public final PartEnhancementResult enhance(@Nullable TypeEnhancementInfo typeEnhancementInfo) {
            Function1<Integer, JavaTypeQualifiers> computeIndexedQualifiersForOverride = computeIndexedQualifiersForOverride();
            SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 = typeEnhancementInfo != null ? new SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1(typeEnhancementInfo, computeIndexedQualifiersForOverride) : null;
            boolean contains = TypeUtils.contains(this.fromOverride, SignatureEnhancement$SignatureParts$enhance$containsFunctionN$1.INSTANCE);
            KotlinType kotlinType = this.fromOverride;
            if (signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 == null) {
                signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 = computeIndexedQualifiersForOverride;
            }
            KotlinType enhance = TypeEnhancementKt.enhance(kotlinType, signatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1);
            if (enhance != null) {
                return new PartEnhancementResult(enhance, true, contains);
            }
            return new PartEnhancementResult(this.fromOverride, false, contains);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: signatureEnhancement.kt */
    /* loaded from: classes3.dex */
    public static final class ValueParameterEnhancementResult extends PartEnhancementResult {
        private final boolean hasDefaultValue;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValueParameterEnhancementResult(@NotNull KotlinType type, boolean z, boolean z2, boolean z3) {
            super(type, z2, z3);
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.hasDefaultValue = z;
        }

        public final boolean getHasDefaultValue() {
            return this.hasDefaultValue;
        }
    }

    public SignatureEnhancement(@NotNull AnnotationTypeQualifierResolver annotationTypeQualifierResolver, @NotNull Jsr305State jsr305State) {
        Intrinsics.checkParameterIsNotNull(annotationTypeQualifierResolver, "annotationTypeQualifierResolver");
        Intrinsics.checkParameterIsNotNull(jsr305State, "jsr305State");
        this.annotationTypeQualifierResolver = annotationTypeQualifierResolver;
        this.jsr305State = jsr305State;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x022d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x025d A[LOOP:1: B:137:0x0257->B:139:0x025d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x027e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final <D extends kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor> D enhanceSignature(@org.jetbrains.annotations.NotNull D r17, kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext r18) {
        /*
            Method dump skipped, instructions count: 647
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature(kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext):kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor");
    }

    private final NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations(AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus;
        FqName fqName = annotationDescriptor.getFqName();
        if (fqName != null) {
            if (JvmAnnotationNamesKt.getNULLABLE_ANNOTATIONS().contains(fqName)) {
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
            } else if (JvmAnnotationNamesKt.getNOT_NULL_ANNOTATIONS().contains(fqName)) {
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
            } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getJAVAX_NONNULL_ANNOTATION())) {
                nullabilityQualifierWithMigrationStatus = extractNullabilityTypeFromArgument(annotationDescriptor);
            } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NULLABLE_ANNOTATION()) && this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) {
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false, 2, null);
            } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getCOMPATQUAL_NONNULL_ANNOTATION()) && this.jsr305State.getEnableCompatqualCheckerFrameworkAnnotations()) {
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false, 2, null);
            } else if (Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NON_NULL_ANNOTATION())) {
                nullabilityQualifierWithMigrationStatus = new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, true);
            } else {
                nullabilityQualifierWithMigrationStatus = Intrinsics.areEqual(fqName, JvmAnnotationNamesKt.getANDROIDX_RECENTLY_NULLABLE_ANNOTATION()) ? new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, true) : null;
            }
            if (nullabilityQualifierWithMigrationStatus == null) {
                return null;
            }
            return (nullabilityQualifierWithMigrationStatus.isForWarningOnly() || !(annotationDescriptor instanceof PossiblyExternalAnnotationDescriptor) || !((PossiblyExternalAnnotationDescriptor) annotationDescriptor).isIdeExternalAnnotation()) ? nullabilityQualifierWithMigrationStatus : NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatus, null, true, 1, null);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0046, code lost:
        if (r5.equals("NEVER") != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004f, code lost:
        if (r5.equals("MAYBE") != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:?, code lost:
        return new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus(kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE, false, 2, null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus extractNullabilityTypeFromArgument(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor r5) {
        /*
            r4 = this;
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r5 = kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.firstArgument(r5)
            boolean r0 = r5 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue
            r1 = 0
            if (r0 != 0) goto La
            r5 = r1
        La:
            kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue r5 = (kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue) r5
            r0 = 2
            r2 = 0
            if (r5 == 0) goto L5b
            kotlin.reflect.jvm.internal.impl.name.Name r5 = r5.getEnumEntryName()
            java.lang.String r5 = r5.asString()
            int r3 = r5.hashCode()
            switch(r3) {
                case 73135176: goto L49;
                case 74175084: goto L40;
                case 433141802: goto L30;
                case 1933739535: goto L20;
                default: goto L1f;
            }
        L1f:
            goto L59
        L20:
            java.lang.String r3 = "ALWAYS"
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L59
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r5 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
            r5.<init>(r3, r2, r0, r1)
            goto L5a
        L30:
            java.lang.String r3 = "UNKNOWN"
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L59
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r5 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.FORCE_FLEXIBILITY
            r5.<init>(r3, r2, r0, r1)
            goto L5a
        L40:
            java.lang.String r3 = "NEVER"
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L59
            goto L51
        L49:
            java.lang.String r3 = "MAYBE"
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L59
        L51:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r5 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NULLABLE
            r5.<init>(r3, r2, r0, r1)
            goto L5a
        L59:
            r5 = r1
        L5a:
            return r5
        L5b:
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus r5 = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus
            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier r3 = kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier.NOT_NULL
            r5.<init>(r3, r2, r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.extractNullabilityTypeFromArgument(kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor):kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus");
    }

    private final boolean hasDefaultValueInAnnotation(@NotNull ValueParameterDescriptor valueParameterDescriptor, KotlinType kotlinType) {
        boolean declaresDefaultValue;
        AnnotationDefaultValue defaultValueFromAnnotation = UtilKt.getDefaultValueFromAnnotation(valueParameterDescriptor);
        if (defaultValueFromAnnotation instanceof StringDefaultValue) {
            declaresDefaultValue = UtilsKt.lexicalCastFrom(kotlinType, ((StringDefaultValue) defaultValueFromAnnotation).getValue()) != null;
        } else if (Intrinsics.areEqual(defaultValueFromAnnotation, NullDefaultValue.INSTANCE)) {
            declaresDefaultValue = TypeUtils.acceptsNullable(kotlinType);
        } else if (defaultValueFromAnnotation == null) {
            declaresDefaultValue = valueParameterDescriptor.declaresDefaultValue();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return declaresDefaultValue && valueParameterDescriptor.getOverriddenDescriptors().isEmpty();
    }

    private final SignatureParts parts(@NotNull CallableMemberDescriptor callableMemberDescriptor, Annotated annotated, boolean z, LazyJavaResolverContext lazyJavaResolverContext, AnnotationTypeQualifierResolver.QualifierApplicabilityType qualifierApplicabilityType, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        int collectionSizeOrDefault;
        KotlinType mo12165invoke = function1.mo12165invoke(callableMemberDescriptor);
        Collection<? extends CallableMemberDescriptor> overriddenDescriptors = callableMemberDescriptor.getOverriddenDescriptors();
        Intrinsics.checkExpressionValueIsNotNull(overriddenDescriptors, "this.overriddenDescriptors");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(overriddenDescriptors, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (CallableMemberDescriptor it2 : overriddenDescriptors) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            arrayList.add(function1.mo12165invoke(it2));
        }
        return new SignatureParts(this, annotated, mo12165invoke, arrayList, z, ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, function1.mo12165invoke(callableMemberDescriptor).mo12070getAnnotations()), qualifierApplicabilityType);
    }

    private final SignatureParts partsForValueParameter(@NotNull CallableMemberDescriptor callableMemberDescriptor, ValueParameterDescriptor valueParameterDescriptor, LazyJavaResolverContext lazyJavaResolverContext, Function1<? super CallableMemberDescriptor, ? extends KotlinType> function1) {
        LazyJavaResolverContext copyWithNewDefaultTypeQualifiers;
        return parts(callableMemberDescriptor, valueParameterDescriptor, false, (valueParameterDescriptor == null || (copyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext, valueParameterDescriptor.mo12070getAnnotations())) == null) ? lazyJavaResolverContext : copyWithNewDefaultTypeQualifiers, AnnotationTypeQualifierResolver.QualifierApplicabilityType.VALUE_PARAMETER, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final <D extends CallableMemberDescriptor> Collection<D> enhanceSignatures(@NotNull LazyJavaResolverContext c, @NotNull Collection<? extends D> platformSignatures) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(platformSignatures, "platformSignatures");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(platformSignatures, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator<T> it2 = platformSignatures.iterator();
        while (it2.hasNext()) {
            arrayList.add(enhanceSignature((CallableMemberDescriptor) it2.next(), c));
        }
        return arrayList;
    }

    @Nullable
    public final NullabilityQualifierWithMigrationStatus extractNullability(@NotNull AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        NullabilityQualifierWithMigrationStatus extractNullabilityFromKnownAnnotations2 = extractNullabilityFromKnownAnnotations(annotationDescriptor);
        if (extractNullabilityFromKnownAnnotations2 != null) {
            return extractNullabilityFromKnownAnnotations2;
        }
        AnnotationDescriptor resolveTypeQualifierAnnotation = this.annotationTypeQualifierResolver.resolveTypeQualifierAnnotation(annotationDescriptor);
        if (resolveTypeQualifierAnnotation == null) {
            return null;
        }
        ReportLevel resolveJsr305AnnotationState = this.annotationTypeQualifierResolver.resolveJsr305AnnotationState(annotationDescriptor);
        if (resolveJsr305AnnotationState.isIgnore() || (extractNullabilityFromKnownAnnotations = extractNullabilityFromKnownAnnotations(resolveTypeQualifierAnnotation)) == null) {
            return null;
        }
        return NullabilityQualifierWithMigrationStatus.copy$default(extractNullabilityFromKnownAnnotations, null, resolveJsr305AnnotationState.isWarning(), 1, null);
    }
}
