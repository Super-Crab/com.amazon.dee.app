package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.amazon.alexa.routing.data.RouteName;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.text.StringsKt__StringsKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: JvmPackagePartSource.kt */
/* loaded from: classes3.dex */
public final class JvmPackagePartSource implements DeserializedContainerSource {
    @NotNull
    private final JvmClassName className;
    @Nullable
    private final JvmClassName facadeClassName;
    @Nullable
    private final IncompatibleVersionErrorData<JvmMetadataVersion> incompatibility;
    private final boolean isPreReleaseInvisible;
    @Nullable
    private final KotlinJvmBinaryClass knownJvmBinaryClass;
    @NotNull
    private final String moduleName;

    public JvmPackagePartSource(@NotNull JvmClassName className, @Nullable JvmClassName jvmClassName, @NotNull ProtoBuf.Package packageProto, @NotNull NameResolver nameResolver, @Nullable IncompatibleVersionErrorData<JvmMetadataVersion> incompatibleVersionErrorData, boolean z, @Nullable KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        String str;
        Intrinsics.checkParameterIsNotNull(className, "className");
        Intrinsics.checkParameterIsNotNull(packageProto, "packageProto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        this.className = className;
        this.facadeClassName = jvmClassName;
        this.incompatibility = incompatibleVersionErrorData;
        this.isPreReleaseInvisible = z;
        this.knownJvmBinaryClass = kotlinJvmBinaryClass;
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Package, Integer> generatedExtension = JvmProtoBuf.packageModuleName;
        Intrinsics.checkExpressionValueIsNotNull(generatedExtension, "JvmProtoBuf.packageModuleName");
        Integer num = (Integer) ProtoBufUtilKt.getExtensionOrNull(packageProto, generatedExtension);
        this.moduleName = (num == null || (str = nameResolver.getString(num.intValue())) == null) ? RouteName.MAIN : str;
    }

    @NotNull
    public final ClassId getClassId() {
        return new ClassId(this.className.getPackageFqName(), getSimpleName());
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
    @NotNull
    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
        return sourceFile;
    }

    @Nullable
    public final JvmClassName getFacadeClassName() {
        return this.facadeClassName;
    }

    @Nullable
    public final KotlinJvmBinaryClass getKnownJvmBinaryClass() {
        return this.knownJvmBinaryClass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource
    @NotNull
    public String getPresentableString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class '");
        outline107.append(getClassId().asSingleFqName().asString());
        outline107.append(Chars.QUOTE);
        return outline107.toString();
    }

    @NotNull
    public final Name getSimpleName() {
        String substringAfterLast$default;
        String internalName = this.className.getInternalName();
        Intrinsics.checkExpressionValueIsNotNull(internalName, "className.internalName");
        substringAfterLast$default = StringsKt__StringsKt.substringAfterLast$default(internalName, '/', (String) null, 2, (Object) null);
        Name identifier = Name.identifier(substringAfterLast$default);
        Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(classNam….substringAfterLast('/'))");
        return identifier;
    }

    @NotNull
    public String toString() {
        return JvmPackagePartSource.class.getSimpleName() + RealTimeTextConstants.COLON_SPACE + this.className;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public JvmPackagePartSource(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass r10, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package r11, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r12, @org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData<kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion> r13, boolean r14) {
        /*
            r9 = this;
            java.lang.String r0 = "kotlinClass"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            java.lang.String r0 = "packageProto"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            kotlin.reflect.jvm.internal.impl.name.ClassId r0 = r10.getClassId()
            kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r2 = kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName.byClassId(r0)
            java.lang.String r0 = "JvmClassName.byClassId(kotlinClass.classId)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)
            kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader r0 = r10.getClassHeader()
            java.lang.String r0 = r0.getMultifileClassName()
            r1 = 0
            if (r0 == 0) goto L36
            int r3 = r0.length()
            if (r3 <= 0) goto L2f
            r3 = 1
            goto L30
        L2f:
            r3 = 0
        L30:
            if (r3 == 0) goto L36
            kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName r1 = kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName.byInternalName(r0)
        L36:
            r3 = r1
            r1 = r9
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            r8 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource.<init>(kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf$Package, kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver, kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData, boolean):void");
    }
}
