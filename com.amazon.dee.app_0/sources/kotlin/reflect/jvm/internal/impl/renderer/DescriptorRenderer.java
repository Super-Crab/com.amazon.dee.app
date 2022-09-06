package kotlin.reflect.jvm.internal.impl.renderer;

import com.amazon.deecomms.calling.incallcommands.constants.CommsFocusConstants;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DescriptorRenderer.kt */
/* loaded from: classes4.dex */
public abstract class DescriptorRenderer {
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final DescriptorRenderer COMPACT_WITH_MODIFIERS = Companion.withOptions(DescriptorRenderer$Companion$COMPACT_WITH_MODIFIERS$1.INSTANCE);
    @JvmField
    @NotNull
    public static final DescriptorRenderer COMPACT = Companion.withOptions(DescriptorRenderer$Companion$COMPACT$1.INSTANCE);
    @JvmField
    @NotNull
    public static final DescriptorRenderer COMPACT_WITHOUT_SUPERTYPES = Companion.withOptions(DescriptorRenderer$Companion$COMPACT_WITHOUT_SUPERTYPES$1.INSTANCE);
    @JvmField
    @NotNull
    public static final DescriptorRenderer COMPACT_WITH_SHORT_TYPES = Companion.withOptions(DescriptorRenderer$Companion$COMPACT_WITH_SHORT_TYPES$1.INSTANCE);
    @JvmField
    @NotNull
    public static final DescriptorRenderer ONLY_NAMES_WITH_SHORT_TYPES = Companion.withOptions(DescriptorRenderer$Companion$ONLY_NAMES_WITH_SHORT_TYPES$1.INSTANCE);
    @JvmField
    @NotNull
    public static final DescriptorRenderer FQ_NAMES_IN_TYPES = Companion.withOptions(DescriptorRenderer$Companion$FQ_NAMES_IN_TYPES$1.INSTANCE);
    @JvmField
    @NotNull
    public static final DescriptorRenderer FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS = Companion.withOptions(DescriptorRenderer$Companion$FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS$1.INSTANCE);
    @JvmField
    @NotNull
    public static final DescriptorRenderer SHORT_NAMES_IN_TYPES = Companion.withOptions(DescriptorRenderer$Companion$SHORT_NAMES_IN_TYPES$1.INSTANCE);
    @JvmField
    @NotNull
    public static final DescriptorRenderer DEBUG_TEXT = Companion.withOptions(DescriptorRenderer$Companion$DEBUG_TEXT$1.INSTANCE);
    @JvmField
    @NotNull
    public static final DescriptorRenderer HTML = Companion.withOptions(DescriptorRenderer$Companion$HTML$1.INSTANCE);

    /* compiled from: DescriptorRenderer.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {

        /* loaded from: classes4.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ClassKind.values().length];

            static {
                $EnumSwitchMapping$0[ClassKind.CLASS.ordinal()] = 1;
                $EnumSwitchMapping$0[ClassKind.INTERFACE.ordinal()] = 2;
                $EnumSwitchMapping$0[ClassKind.ENUM_CLASS.ordinal()] = 3;
                $EnumSwitchMapping$0[ClassKind.OBJECT.ordinal()] = 4;
                $EnumSwitchMapping$0[ClassKind.ANNOTATION_CLASS.ordinal()] = 5;
                $EnumSwitchMapping$0[ClassKind.ENUM_ENTRY.ordinal()] = 6;
            }
        }

        private Companion() {
        }

        @NotNull
        public final String getClassifierKindPrefix(@NotNull ClassifierDescriptorWithTypeParameters classifier) {
            Intrinsics.checkParameterIsNotNull(classifier, "classifier");
            if (classifier instanceof TypeAliasDescriptor) {
                return "typealias";
            }
            if (classifier instanceof ClassDescriptor) {
                ClassDescriptor classDescriptor = (ClassDescriptor) classifier;
                if (classDescriptor.isCompanionObject()) {
                    return "companion object";
                }
                switch (WhenMappings.$EnumSwitchMapping$0[classDescriptor.getKind().ordinal()]) {
                    case 1:
                        return "class";
                    case 2:
                        return CommsFocusConstants.INTERFACE;
                    case 3:
                        return "enum class";
                    case 4:
                        return "object";
                    case 5:
                        return "annotation class";
                    case 6:
                        return "enum entry";
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
            throw new AssertionError("Unexpected classifier: " + classifier);
        }

        @NotNull
        public final DescriptorRenderer withOptions(@NotNull Function1<? super DescriptorRendererOptions, Unit> changeOptions) {
            Intrinsics.checkParameterIsNotNull(changeOptions, "changeOptions");
            DescriptorRendererOptionsImpl descriptorRendererOptionsImpl = new DescriptorRendererOptionsImpl();
            changeOptions.mo12165invoke(descriptorRendererOptionsImpl);
            descriptorRendererOptionsImpl.lock();
            return new DescriptorRendererImpl(descriptorRendererOptionsImpl);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: DescriptorRenderer.kt */
    /* loaded from: classes4.dex */
    public interface ValueParametersHandler {

        /* compiled from: DescriptorRenderer.kt */
        /* loaded from: classes4.dex */
        public static final class DEFAULT implements ValueParametersHandler {
            public static final DEFAULT INSTANCE = new DEFAULT();

            private DEFAULT() {
            }

            @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler
            public void appendAfterValueParameter(@NotNull ValueParameterDescriptor parameter, int i, int i2, @NotNull StringBuilder builder) {
                Intrinsics.checkParameterIsNotNull(parameter, "parameter");
                Intrinsics.checkParameterIsNotNull(builder, "builder");
                if (i != i2 - 1) {
                    builder.append(", ");
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler
            public void appendAfterValueParameters(int i, @NotNull StringBuilder builder) {
                Intrinsics.checkParameterIsNotNull(builder, "builder");
                builder.append(")");
            }

            @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler
            public void appendBeforeValueParameter(@NotNull ValueParameterDescriptor parameter, int i, int i2, @NotNull StringBuilder builder) {
                Intrinsics.checkParameterIsNotNull(parameter, "parameter");
                Intrinsics.checkParameterIsNotNull(builder, "builder");
            }

            @Override // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer.ValueParametersHandler
            public void appendBeforeValueParameters(int i, @NotNull StringBuilder builder) {
                Intrinsics.checkParameterIsNotNull(builder, "builder");
                builder.append("(");
            }
        }

        void appendAfterValueParameter(@NotNull ValueParameterDescriptor valueParameterDescriptor, int i, int i2, @NotNull StringBuilder sb);

        void appendAfterValueParameters(int i, @NotNull StringBuilder sb);

        void appendBeforeValueParameter(@NotNull ValueParameterDescriptor valueParameterDescriptor, int i, int i2, @NotNull StringBuilder sb);

        void appendBeforeValueParameters(int i, @NotNull StringBuilder sb);
    }

    public static /* synthetic */ String renderAnnotation$default(DescriptorRenderer descriptorRenderer, AnnotationDescriptor annotationDescriptor, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                annotationUseSiteTarget = null;
            }
            return descriptorRenderer.renderAnnotation(annotationDescriptor, annotationUseSiteTarget);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: renderAnnotation");
    }

    @NotNull
    public abstract String render(@NotNull DeclarationDescriptor declarationDescriptor);

    @NotNull
    public abstract String renderAnnotation(@NotNull AnnotationDescriptor annotationDescriptor, @Nullable AnnotationUseSiteTarget annotationUseSiteTarget);

    @NotNull
    public abstract String renderFlexibleType(@NotNull String str, @NotNull String str2, @NotNull KotlinBuiltIns kotlinBuiltIns);

    @NotNull
    public abstract String renderFqName(@NotNull FqNameUnsafe fqNameUnsafe);

    @NotNull
    public abstract String renderName(@NotNull Name name, boolean z);

    @NotNull
    public abstract String renderType(@NotNull KotlinType kotlinType);

    @NotNull
    public abstract String renderTypeProjection(@NotNull TypeProjection typeProjection);

    @NotNull
    public final DescriptorRenderer withOptions(@NotNull Function1<? super DescriptorRendererOptions, Unit> changeOptions) {
        Intrinsics.checkParameterIsNotNull(changeOptions, "changeOptions");
        DescriptorRendererOptionsImpl copy = ((DescriptorRendererImpl) this).getOptions().copy();
        changeOptions.mo12165invoke(copy);
        copy.lock();
        return new DescriptorRendererImpl(copy);
    }
}
