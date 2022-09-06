package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import com.amazon.alexa.accessorykit.ModelTransformer;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import org.jetbrains.annotations.NotNull;
/* compiled from: annotationUtil.kt */
/* loaded from: classes2.dex */
public final class AnnotationUtilKt {
    private static final Name DEPRECATED_LEVEL_NAME;
    private static final Name DEPRECATED_MESSAGE_NAME;
    private static final Name DEPRECATED_REPLACE_WITH_NAME;
    private static final Name REPLACE_WITH_EXPRESSION_NAME;
    private static final Name REPLACE_WITH_IMPORTS_NAME;

    static {
        Name identifier = Name.identifier("message");
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(\"message\")");
        DEPRECATED_MESSAGE_NAME = identifier;
        Name identifier2 = Name.identifier("replaceWith");
        Intrinsics.checkExpressionValueIsNotNull(identifier2, "Name.identifier(\"replaceWith\")");
        DEPRECATED_REPLACE_WITH_NAME = identifier2;
        Name identifier3 = Name.identifier(ModelTransformer.KEY_BATTERY_LEVEL);
        Intrinsics.checkExpressionValueIsNotNull(identifier3, "Name.identifier(\"level\")");
        DEPRECATED_LEVEL_NAME = identifier3;
        Name identifier4 = Name.identifier("expression");
        Intrinsics.checkExpressionValueIsNotNull(identifier4, "Name.identifier(\"expression\")");
        REPLACE_WITH_EXPRESSION_NAME = identifier4;
        Name identifier5 = Name.identifier("imports");
        Intrinsics.checkExpressionValueIsNotNull(identifier5, "Name.identifier(\"imports\")");
        REPLACE_WITH_IMPORTS_NAME = identifier5;
    }

    @NotNull
    public static final AnnotationDescriptor createDeprecatedAnnotation(@NotNull KotlinBuiltIns createDeprecatedAnnotation, @NotNull String message, @NotNull String replaceWith, @NotNull String level) {
        List emptyList;
        Map mapOf;
        Map mapOf2;
        Intrinsics.checkParameterIsNotNull(createDeprecatedAnnotation, "$this$createDeprecatedAnnotation");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(replaceWith, "replaceWith");
        Intrinsics.checkParameterIsNotNull(level, "level");
        FqName fqName = KotlinBuiltIns.FQ_NAMES.replaceWith;
        Intrinsics.checkExpressionValueIsNotNull(fqName, "KotlinBuiltIns.FQ_NAMES.replaceWith");
        Name name = REPLACE_WITH_IMPORTS_NAME;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(REPLACE_WITH_EXPRESSION_NAME, new StringValue(replaceWith)), TuplesKt.to(name, new ArrayValue(emptyList, new AnnotationUtilKt$createDeprecatedAnnotation$replaceWithAnnotation$1(createDeprecatedAnnotation))));
        BuiltInAnnotationDescriptor builtInAnnotationDescriptor = new BuiltInAnnotationDescriptor(createDeprecatedAnnotation, fqName, mapOf);
        FqName fqName2 = KotlinBuiltIns.FQ_NAMES.deprecated;
        Intrinsics.checkExpressionValueIsNotNull(fqName2, "KotlinBuiltIns.FQ_NAMES.deprecated");
        Name name2 = DEPRECATED_LEVEL_NAME;
        ClassId classId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.deprecationLevel);
        Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(KotlinB…Q_NAMES.deprecationLevel)");
        Name identifier = Name.identifier(level);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(level)");
        mapOf2 = MapsKt__MapsKt.mapOf(TuplesKt.to(DEPRECATED_MESSAGE_NAME, new StringValue(message)), TuplesKt.to(DEPRECATED_REPLACE_WITH_NAME, new AnnotationValue(builtInAnnotationDescriptor)), TuplesKt.to(name2, new EnumValue(classId, identifier)));
        return new BuiltInAnnotationDescriptor(createDeprecatedAnnotation, fqName2, mapOf2);
    }

    public static /* synthetic */ AnnotationDescriptor createDeprecatedAnnotation$default(KotlinBuiltIns kotlinBuiltIns, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            str3 = "WARNING";
        }
        return createDeprecatedAnnotation(kotlinBuiltIns, str, str2, str3);
    }
}
