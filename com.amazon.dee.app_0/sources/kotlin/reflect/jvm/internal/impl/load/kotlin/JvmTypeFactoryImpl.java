package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: methodSignatureMapping.kt */
/* loaded from: classes3.dex */
public final class JvmTypeFactoryImpl implements JvmTypeFactory<JvmType> {
    public static final JvmTypeFactoryImpl INSTANCE = new JvmTypeFactoryImpl();

    private JvmTypeFactoryImpl() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public JvmType boxType(@NotNull JvmType possiblyPrimitiveType) {
        Intrinsics.checkParameterIsNotNull(possiblyPrimitiveType, "possiblyPrimitiveType");
        if (possiblyPrimitiveType instanceof JvmType.Primitive) {
            JvmType.Primitive primitive = (JvmType.Primitive) possiblyPrimitiveType;
            if (primitive.getJvmPrimitiveType() == null) {
                return possiblyPrimitiveType;
            }
            JvmClassName byFqNameWithoutInnerClasses = JvmClassName.byFqNameWithoutInnerClasses(primitive.getJvmPrimitiveType().getWrapperFqName());
            Intrinsics.checkExpressionValueIsNotNull(byFqNameWithoutInnerClasses, "JvmClassName.byFqNameWit…mitiveType.wrapperFqName)");
            String internalName = byFqNameWithoutInnerClasses.getInternalName();
            Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byFqNameWit…apperFqName).internalName");
            return createObjectType(internalName);
        }
        return possiblyPrimitiveType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public JvmType createFromString(@NotNull String representation) {
        JvmPrimitiveType jvmPrimitiveType;
        boolean endsWith$default;
        Intrinsics.checkParameterIsNotNull(representation, "representation");
        boolean z = false;
        boolean z2 = representation.length() > 0;
        if (!_Assertions.ENABLED || z2) {
            char charAt = representation.charAt(0);
            JvmPrimitiveType[] values = JvmPrimitiveType.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    jvmPrimitiveType = null;
                    break;
                }
                jvmPrimitiveType = values[i];
                if (jvmPrimitiveType.getDesc().charAt(0) == charAt) {
                    break;
                }
                i++;
            }
            if (jvmPrimitiveType != null) {
                return new JvmType.Primitive(jvmPrimitiveType);
            }
            if (charAt == 'V') {
                return new JvmType.Primitive(null);
            }
            if (charAt == '[') {
                String substring = representation.substring(1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                return new JvmType.Array(createFromString(substring));
            }
            if (charAt == 'L') {
                endsWith$default = StringsKt__StringsKt.endsWith$default((CharSequence) representation, ';', false, 2, (Object) null);
                if (endsWith$default) {
                    z = true;
                }
            }
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError(GeneratedOutlineSupport1.outline75("Type that is not primitive nor array should be Object, but '", representation, "' was found"));
            }
            String substring2 = representation.substring(1, representation.length() - 1);
            Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return new JvmType.Object(substring2);
        }
        throw new AssertionError("empty string as JvmType");
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public JvmType createObjectType(@NotNull String internalName) {
        Intrinsics.checkParameterIsNotNull(internalName, "internalName");
        return new JvmType.Object(internalName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public JvmType getJavaLangClassType() {
        return createObjectType("java/lang/Class");
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public String toString(@NotNull JvmType type) {
        String desc;
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (type instanceof JvmType.Array) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
            outline107.append(toString(((JvmType.Array) type).getElementType()));
            return outline107.toString();
        } else if (type instanceof JvmType.Primitive) {
            JvmPrimitiveType jvmPrimitiveType = ((JvmType.Primitive) type).getJvmPrimitiveType();
            return (jvmPrimitiveType == null || (desc = jvmPrimitiveType.getDesc()) == null) ? ExifInterface.GPS_MEASUREMENT_INTERRUPTED : desc;
        } else if (!(type instanceof JvmType.Object)) {
            throw new NoWhenBranchMatchedException();
        } else {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("L");
            outline1072.append(((JvmType.Object) type).getInternalName());
            outline1072.append(";");
            return outline1072.toString();
        }
    }
}
