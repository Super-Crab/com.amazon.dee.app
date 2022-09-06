package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
/* compiled from: JvmAnnotationNames.kt */
/* loaded from: classes3.dex */
public final class JvmAnnotationNamesKt {
    @NotNull
    private static final FqName ANDROIDX_RECENTLY_NON_NULL_ANNOTATION;
    @NotNull
    private static final FqName ANDROIDX_RECENTLY_NULLABLE_ANNOTATION;
    @NotNull
    private static final FqName COMPATQUAL_NONNULL_ANNOTATION;
    @NotNull
    private static final FqName COMPATQUAL_NULLABLE_ANNOTATION;
    @NotNull
    private static final FqName JAVAX_CHECKFORNULL_ANNOTATION;
    @NotNull
    private static final FqName JAVAX_NONNULL_ANNOTATION;
    @NotNull
    private static final List<FqName> MUTABLE_ANNOTATIONS;
    @NotNull
    private static final List<FqName> NOT_NULL_ANNOTATIONS;
    @NotNull
    private static final Set<FqName> NULLABILITY_ANNOTATIONS;
    @NotNull
    private static final List<FqName> NULLABLE_ANNOTATIONS;
    @NotNull
    private static final List<FqName> READ_ONLY_ANNOTATIONS;

    static {
        List<FqName> listOf;
        List<FqName> listOf2;
        Set plus;
        Set plus2;
        Set plus3;
        Set plus4;
        Set plus5;
        Set plus6;
        Set<FqName> plus7;
        List<FqName> listOf3;
        List<FqName> listOf4;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new FqName[]{JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION, new FqName("androidx.annotation.Nullable"), new FqName("androidx.annotation.Nullable"), new FqName("android.annotation.Nullable"), new FqName("com.android.annotations.Nullable"), new FqName("org.eclipse.jdt.annotation.Nullable"), new FqName("org.checkerframework.checker.nullness.qual.Nullable"), new FqName("javax.annotation.Nullable"), new FqName("javax.annotation.CheckForNull"), new FqName("edu.umd.cs.findbugs.annotations.CheckForNull"), new FqName("edu.umd.cs.findbugs.annotations.Nullable"), new FqName("edu.umd.cs.findbugs.annotations.PossiblyNull"), new FqName("io.reactivex.annotations.Nullable")});
        NULLABLE_ANNOTATIONS = listOf;
        JAVAX_NONNULL_ANNOTATION = new FqName("javax.annotation.Nonnull");
        JAVAX_CHECKFORNULL_ANNOTATION = new FqName("javax.annotation.CheckForNull");
        listOf2 = CollectionsKt__CollectionsKt.listOf((Object[]) new FqName[]{JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION, new FqName("edu.umd.cs.findbugs.annotations.NonNull"), new FqName("androidx.annotation.NonNull"), new FqName("androidx.annotation.NonNull"), new FqName("android.annotation.NonNull"), new FqName("com.android.annotations.NonNull"), new FqName("org.eclipse.jdt.annotation.NonNull"), new FqName("org.checkerframework.checker.nullness.qual.NonNull"), new FqName("lombok.NonNull"), new FqName("io.reactivex.annotations.NonNull")});
        NOT_NULL_ANNOTATIONS = listOf2;
        COMPATQUAL_NULLABLE_ANNOTATION = new FqName("org.checkerframework.checker.nullness.compatqual.NullableDecl");
        COMPATQUAL_NONNULL_ANNOTATION = new FqName("org.checkerframework.checker.nullness.compatqual.NonNullDecl");
        ANDROIDX_RECENTLY_NULLABLE_ANNOTATION = new FqName("androidx.annotation.RecentlyNullable");
        ANDROIDX_RECENTLY_NON_NULL_ANNOTATION = new FqName("androidx.annotation.RecentlyNonNull");
        plus = SetsKt___SetsKt.plus((Set) new LinkedHashSet(), (Iterable) NULLABLE_ANNOTATIONS);
        plus2 = SetsKt___SetsKt.plus(plus, JAVAX_NONNULL_ANNOTATION);
        plus3 = SetsKt___SetsKt.plus((Set) plus2, (Iterable) NOT_NULL_ANNOTATIONS);
        plus4 = SetsKt___SetsKt.plus(plus3, COMPATQUAL_NULLABLE_ANNOTATION);
        plus5 = SetsKt___SetsKt.plus(plus4, COMPATQUAL_NONNULL_ANNOTATION);
        plus6 = SetsKt___SetsKt.plus(plus5, ANDROIDX_RECENTLY_NULLABLE_ANNOTATION);
        plus7 = SetsKt___SetsKt.plus(plus6, ANDROIDX_RECENTLY_NON_NULL_ANNOTATION);
        NULLABILITY_ANNOTATIONS = plus7;
        listOf3 = CollectionsKt__CollectionsKt.listOf((Object[]) new FqName[]{JvmAnnotationNames.JETBRAINS_READONLY_ANNOTATION, JvmAnnotationNames.READONLY_ANNOTATION});
        READ_ONLY_ANNOTATIONS = listOf3;
        listOf4 = CollectionsKt__CollectionsKt.listOf((Object[]) new FqName[]{JvmAnnotationNames.JETBRAINS_MUTABLE_ANNOTATION, JvmAnnotationNames.MUTABLE_ANNOTATION});
        MUTABLE_ANNOTATIONS = listOf4;
    }

    @NotNull
    public static final FqName getANDROIDX_RECENTLY_NON_NULL_ANNOTATION() {
        return ANDROIDX_RECENTLY_NON_NULL_ANNOTATION;
    }

    @NotNull
    public static final FqName getANDROIDX_RECENTLY_NULLABLE_ANNOTATION() {
        return ANDROIDX_RECENTLY_NULLABLE_ANNOTATION;
    }

    @NotNull
    public static final FqName getCOMPATQUAL_NONNULL_ANNOTATION() {
        return COMPATQUAL_NONNULL_ANNOTATION;
    }

    @NotNull
    public static final FqName getCOMPATQUAL_NULLABLE_ANNOTATION() {
        return COMPATQUAL_NULLABLE_ANNOTATION;
    }

    @NotNull
    public static final FqName getJAVAX_CHECKFORNULL_ANNOTATION() {
        return JAVAX_CHECKFORNULL_ANNOTATION;
    }

    @NotNull
    public static final FqName getJAVAX_NONNULL_ANNOTATION() {
        return JAVAX_NONNULL_ANNOTATION;
    }

    @NotNull
    public static final List<FqName> getMUTABLE_ANNOTATIONS() {
        return MUTABLE_ANNOTATIONS;
    }

    @NotNull
    public static final List<FqName> getNOT_NULL_ANNOTATIONS() {
        return NOT_NULL_ANNOTATIONS;
    }

    @NotNull
    public static final List<FqName> getNULLABLE_ANNOTATIONS() {
        return NULLABLE_ANNOTATIONS;
    }

    @NotNull
    public static final List<FqName> getREAD_ONLY_ANNOTATIONS() {
        return READ_ONLY_ANNOTATIONS;
    }
}
