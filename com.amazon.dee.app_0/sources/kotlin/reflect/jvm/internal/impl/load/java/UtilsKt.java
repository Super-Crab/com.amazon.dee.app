package kotlin.reflect.jvm.internal.impl.load.java;
/* compiled from: utils.kt */
/* loaded from: classes3.dex */
public final class UtilsKt {
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b8, code lost:
        if (kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isString(r4) != false) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00bb -> B:41:0x00bc). Please submit an issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue lexicalCastFrom(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.types.KotlinType r4, @org.jetbrains.annotations.NotNull java.lang.String r5) {
        /*
            java.lang.String r0 = "$this$lexicalCastFrom"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "value"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r0 = r4.mo12131getConstructor()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r0 = r0.mo12085getDeclarationDescriptor()
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            r2 = 0
            if (r1 == 0) goto L48
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r1 = r0.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r3 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ENUM_CLASS
            if (r1 != r3) goto L48
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r4 = r0.getUnsubstitutedInnerClassesScope()
            kotlin.reflect.jvm.internal.impl.name.Name r5 = kotlin.reflect.jvm.internal.impl.name.Name.identifier(r5)
            java.lang.String r0 = "Name.identifier(value)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation r0 = kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation.FROM_BACKEND
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r4 = r4.mo12030getContributedClassifier(r5, r0)
            boolean r5 = r4 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r5 == 0) goto L47
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r4 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r4
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r5 = r4.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r0 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ENUM_ENTRY
            if (r5 != r0) goto L47
            kotlin.reflect.jvm.internal.impl.load.java.EnumEntry r2 = new kotlin.reflect.jvm.internal.impl.load.java.EnumEntry
            r2.<init>(r4)
        L47:
            return r2
        L48:
            kotlin.reflect.jvm.internal.impl.types.KotlinType r4 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.makeNotNullable(r4)
            kotlin.reflect.jvm.internal.impl.utils.NumberWithRadix r0 = kotlin.reflect.jvm.internal.impl.utils.NumbersKt.extractRadix(r5)
            java.lang.String r1 = r0.component1()
            int r0 = r0.component2()
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isBoolean(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            if (r3 == 0) goto L67
            boolean r4 = java.lang.Boolean.parseBoolean(r5)     // Catch: java.lang.IllegalArgumentException -> Lbb
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            goto Lbc
        L67:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isChar(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            if (r3 == 0) goto L72
            java.lang.Character r5 = kotlin.text.StringsKt.singleOrNull(r5)     // Catch: java.lang.IllegalArgumentException -> Lbb
            goto Lbc
        L72:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isByte(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            if (r3 == 0) goto L7d
            java.lang.Byte r5 = kotlin.text.StringsKt.toByteOrNull(r1, r0)     // Catch: java.lang.IllegalArgumentException -> Lbb
            goto Lbc
        L7d:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isShort(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            if (r3 == 0) goto L88
            java.lang.Short r5 = kotlin.text.StringsKt.toShortOrNull(r1, r0)     // Catch: java.lang.IllegalArgumentException -> Lbb
            goto Lbc
        L88:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isInt(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            if (r3 == 0) goto L93
            java.lang.Integer r5 = kotlin.text.StringsKt.toIntOrNull(r1, r0)     // Catch: java.lang.IllegalArgumentException -> Lbb
            goto Lbc
        L93:
            boolean r3 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isLong(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            if (r3 == 0) goto L9e
            java.lang.Long r5 = kotlin.text.StringsKt.toLongOrNull(r1, r0)     // Catch: java.lang.IllegalArgumentException -> Lbb
            goto Lbc
        L9e:
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isFloat(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            if (r0 == 0) goto La9
            java.lang.Float r5 = kotlin.text.StringsKt.toFloatOrNull(r5)     // Catch: java.lang.IllegalArgumentException -> Lbb
            goto Lbc
        La9:
            boolean r0 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isDouble(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            if (r0 == 0) goto Lb4
            java.lang.Double r5 = kotlin.text.StringsKt.toDoubleOrNull(r5)     // Catch: java.lang.IllegalArgumentException -> Lbb
            goto Lbc
        Lb4:
            boolean r4 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isString(r4)     // Catch: java.lang.IllegalArgumentException -> Lbb
            if (r4 == 0) goto Lbb
            goto Lbc
        Lbb:
            r5 = r2
        Lbc:
            if (r5 == 0) goto Lc3
            kotlin.reflect.jvm.internal.impl.load.java.Constant r2 = new kotlin.reflect.jvm.internal.impl.load.java.Constant
            r2.<init>(r5)
        Lc3:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.UtilsKt.lexicalCastFrom(kotlin.reflect.jvm.internal.impl.types.KotlinType, java.lang.String):kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultValue");
    }
}
