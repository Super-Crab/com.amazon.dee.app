package kotlin.reflect.jvm.internal.impl.load.java;

import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AnnotationTypeQualifierResolver.kt */
/* loaded from: classes3.dex */
public final class AnnotationTypeQualifierResolver {
    private final boolean disabled;
    private final Jsr305State jsr305State;
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    /* loaded from: classes3.dex */
    public enum QualifierApplicabilityType {
        METHOD_RETURN_TYPE,
        VALUE_PARAMETER,
        FIELD,
        TYPE_USE
    }

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    /* loaded from: classes3.dex */
    public static final class TypeQualifierWithApplicability {
        private final int applicability;
        private final AnnotationDescriptor typeQualifier;

        public TypeQualifierWithApplicability(@NotNull AnnotationDescriptor typeQualifier, int i) {
            Intrinsics.checkParameterIsNotNull(typeQualifier, "typeQualifier");
            this.typeQualifier = typeQualifier;
            this.applicability = i;
        }

        private final boolean isApplicableConsideringMask(QualifierApplicabilityType qualifierApplicabilityType) {
            return ((1 << qualifierApplicabilityType.ordinal()) & this.applicability) != 0;
        }

        private final boolean isApplicableTo(QualifierApplicabilityType qualifierApplicabilityType) {
            return isApplicableConsideringMask(QualifierApplicabilityType.TYPE_USE) || isApplicableConsideringMask(qualifierApplicabilityType);
        }

        @NotNull
        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        @NotNull
        public final List<QualifierApplicabilityType> component2() {
            QualifierApplicabilityType[] values = QualifierApplicabilityType.values();
            ArrayList arrayList = new ArrayList();
            for (QualifierApplicabilityType qualifierApplicabilityType : values) {
                if (isApplicableTo(qualifierApplicabilityType)) {
                    arrayList.add(qualifierApplicabilityType);
                }
            }
            return arrayList;
        }
    }

    public AnnotationTypeQualifierResolver(@NotNull StorageManager storageManager, @NotNull Jsr305State jsr305State) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(jsr305State, "jsr305State");
        this.jsr305State = jsr305State;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
        this.disabled = this.jsr305State.getDisabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (!classDescriptor.mo12070getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_NICKNAME_FQNAME())) {
            return null;
        }
        for (AnnotationDescriptor annotationDescriptor : classDescriptor.mo12070getAnnotations()) {
            AnnotationDescriptor resolveTypeQualifierAnnotation = resolveTypeQualifierAnnotation(annotationDescriptor);
            if (resolveTypeQualifierAnnotation != null) {
                return resolveTypeQualifierAnnotation;
            }
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final List<QualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(@NotNull ConstantValue<?> constantValue) {
        List<QualifierApplicabilityType> emptyList;
        QualifierApplicabilityType qualifierApplicabilityType;
        List<QualifierApplicabilityType> listOfNotNull;
        if (constantValue instanceof ArrayValue) {
            ArrayList arrayList = new ArrayList();
            for (ConstantValue<?> constantValue2 : ((ArrayValue) constantValue).getValue()) {
                CollectionsKt__MutableCollectionsKt.addAll(arrayList, mapConstantToQualifierApplicabilityTypes(constantValue2));
            }
            return arrayList;
        } else if (constantValue instanceof EnumValue) {
            String identifier = ((EnumValue) constantValue).getEnumEntryName().getIdentifier();
            switch (identifier.hashCode()) {
                case -2024225567:
                    if (identifier.equals("METHOD")) {
                        qualifierApplicabilityType = QualifierApplicabilityType.METHOD_RETURN_TYPE;
                        break;
                    }
                    qualifierApplicabilityType = null;
                    break;
                case 66889946:
                    if (identifier.equals("FIELD")) {
                        qualifierApplicabilityType = QualifierApplicabilityType.FIELD;
                        break;
                    }
                    qualifierApplicabilityType = null;
                    break;
                case 107598562:
                    if (identifier.equals("TYPE_USE")) {
                        qualifierApplicabilityType = QualifierApplicabilityType.TYPE_USE;
                        break;
                    }
                    qualifierApplicabilityType = null;
                    break;
                case 446088073:
                    if (identifier.equals("PARAMETER")) {
                        qualifierApplicabilityType = QualifierApplicabilityType.VALUE_PARAMETER;
                        break;
                    }
                    qualifierApplicabilityType = null;
                    break;
                default:
                    qualifierApplicabilityType = null;
                    break;
            }
            listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(qualifierApplicabilityType);
            return listOfNotNull;
        } else {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
    }

    private final ReportLevel migrationAnnotationStatus(@NotNull ClassDescriptor classDescriptor) {
        AnnotationDescriptor mo11701findAnnotation = classDescriptor.mo12070getAnnotations().mo11701findAnnotation(AnnotationTypeQualifierResolverKt.getMIGRATION_ANNOTATION_FQNAME());
        ConstantValue<?> firstArgument = mo11701findAnnotation != null ? DescriptorUtilsKt.firstArgument(mo11701findAnnotation) : null;
        if (!(firstArgument instanceof EnumValue)) {
            firstArgument = null;
        }
        EnumValue enumValue = (EnumValue) firstArgument;
        if (enumValue != null) {
            ReportLevel migration = this.jsr305State.getMigration();
            if (migration != null) {
                return migration;
            }
            String asString = enumValue.getEnumEntryName().asString();
            int hashCode = asString.hashCode();
            if (hashCode == -2137067054) {
                if (!asString.equals("IGNORE")) {
                    return null;
                }
                return ReportLevel.IGNORE;
            } else if (hashCode != -1838656823) {
                if (hashCode != 2656902 || !asString.equals(DriveModeVoxUiConstants.WARN)) {
                    return null;
                }
                return ReportLevel.WARN;
            } else if (!asString.equals("STRICT")) {
                return null;
            } else {
                return ReportLevel.STRICT;
            }
        }
        return null;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return this.resolvedNicknames.mo12165invoke(classDescriptor);
    }

    public final boolean getDisabled() {
        return this.disabled;
    }

    @NotNull
    public final ReportLevel resolveJsr305AnnotationState(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        ReportLevel resolveJsr305CustomState = resolveJsr305CustomState(annotationDescriptor);
        return resolveJsr305CustomState != null ? resolveJsr305CustomState : this.jsr305State.getGlobal();
    }

    @Nullable
    public final ReportLevel resolveJsr305CustomState(@NotNull AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        Map<String, ReportLevel> user = this.jsr305State.getUser();
        FqName fqName = annotationDescriptor.getFqName();
        ReportLevel reportLevel = user.get(fqName != null ? fqName.asString() : null);
        if (reportLevel != null) {
            return reportLevel;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass == null) {
            return null;
        }
        return migrationAnnotationStatus(annotationClass);
    }

    @Nullable
    public final NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (!this.jsr305State.getDisabled() && (nullabilityQualifierWithApplicability = AnnotationTypeQualifierResolverKt.getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS().get(annotationDescriptor.getFqName())) != null) {
            NullabilityQualifierWithMigrationStatus component1 = nullabilityQualifierWithApplicability.component1();
            Collection<QualifierApplicabilityType> component2 = nullabilityQualifierWithApplicability.component2();
            ReportLevel resolveJsr305AnnotationState = resolveJsr305AnnotationState(annotationDescriptor);
            if (!(resolveJsr305AnnotationState != ReportLevel.IGNORE)) {
                resolveJsr305AnnotationState = null;
            }
            if (resolveJsr305AnnotationState != null) {
                return new NullabilityQualifierWithApplicability(NullabilityQualifierWithMigrationStatus.copy$default(component1, null, resolveJsr305AnnotationState.isWarning(), 1, null), component2);
            }
        }
        return null;
    }

    @Nullable
    public final AnnotationDescriptor resolveTypeQualifierAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        boolean isAnnotatedWithTypeQualifier;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (!this.jsr305State.getDisabled() && (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) != null) {
            isAnnotatedWithTypeQualifier = AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass);
            return isAnnotatedWithTypeQualifier ? annotationDescriptor : resolveTypeQualifierNickname(annotationClass);
        }
        return null;
    }

    @Nullable
    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(@NotNull AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        AnnotationDescriptor annotationDescriptor2;
        boolean z;
        List<QualifierApplicabilityType> emptyList;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (!this.jsr305State.getDisabled() && (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) != null) {
            if (!annotationClass.mo12070getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME())) {
                annotationClass = null;
            }
            if (annotationClass != null) {
                ClassDescriptor annotationClass2 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
                if (annotationClass2 == null) {
                    Intrinsics.throwNpe();
                }
                AnnotationDescriptor mo11701findAnnotation = annotationClass2.mo12070getAnnotations().mo11701findAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME());
                if (mo11701findAnnotation == null) {
                    Intrinsics.throwNpe();
                }
                Map<Name, ConstantValue<?>> allValueArguments = mo11701findAnnotation.getAllValueArguments();
                ArrayList<QualifierApplicabilityType> arrayList = new ArrayList();
                for (Map.Entry<Name, ConstantValue<?>> entry : allValueArguments.entrySet()) {
                    ConstantValue<?> value = entry.getValue();
                    if (Intrinsics.areEqual(entry.getKey(), JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                        emptyList = mapConstantToQualifierApplicabilityTypes(value);
                    } else {
                        emptyList = CollectionsKt__CollectionsKt.emptyList();
                    }
                    CollectionsKt__MutableCollectionsKt.addAll(arrayList, emptyList);
                }
                int i = 0;
                for (QualifierApplicabilityType qualifierApplicabilityType : arrayList) {
                    i |= 1 << qualifierApplicabilityType.ordinal();
                }
                Iterator<AnnotationDescriptor> it2 = annotationClass.mo12070getAnnotations().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        annotationDescriptor2 = null;
                        break;
                    }
                    annotationDescriptor2 = it2.next();
                    if (resolveTypeQualifierAnnotation(annotationDescriptor2) != null) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                AnnotationDescriptor annotationDescriptor3 = annotationDescriptor2;
                if (annotationDescriptor3 != null) {
                    return new TypeQualifierWithApplicability(annotationDescriptor3, i);
                }
            }
        }
        return null;
    }
}
