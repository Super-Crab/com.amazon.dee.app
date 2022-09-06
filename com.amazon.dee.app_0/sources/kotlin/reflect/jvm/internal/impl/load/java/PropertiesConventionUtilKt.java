package kotlin.reflect.jvm.internal.impl.load.java;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: propertiesConventionUtil.kt */
/* loaded from: classes3.dex */
public final class PropertiesConventionUtilKt {
    @NotNull
    public static final List<Name> getPropertyNamesCandidatesByAccessorName(@NotNull Name name) {
        List<Name> listOfNotNull;
        Intrinsics.checkParameterIsNotNull(name, "name");
        String asString = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
        if (JvmAbi.isGetterName(asString)) {
            listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(propertyNameByGetMethodName(name));
            return listOfNotNull;
        } else if (JvmAbi.isSetterName(asString)) {
            return propertyNamesBySetMethodName(name);
        } else {
            return BuiltinSpecialProperties.INSTANCE.getPropertyNameCandidatesBySpecialGetterName(name);
        }
    }

    @Nullable
    public static final Name propertyNameByGetMethodName(@NotNull Name methodName) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Name propertyNameFromAccessorMethodName$default = propertyNameFromAccessorMethodName$default(methodName, MetricsConstants.Method.CACHE_GET, false, null, 12, null);
        return propertyNameFromAccessorMethodName$default != null ? propertyNameFromAccessorMethodName$default : propertyNameFromAccessorMethodName$default(methodName, "is", false, null, 8, null);
    }

    @Nullable
    public static final Name propertyNameBySetMethodName(@NotNull Name methodName, boolean z) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        return propertyNameFromAccessorMethodName$default(methodName, "set", false, z ? "is" : null, 4, null);
    }

    private static final Name propertyNameFromAccessorMethodName(Name name, String str, boolean z, String str2) {
        boolean startsWith$default;
        String removePrefix;
        String removePrefix2;
        if (name.isSpecial()) {
            return null;
        }
        String identifier = name.getIdentifier();
        Intrinsics.checkExpressionValueIsNotNull(identifier, "methodName.identifier");
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(identifier, str, false, 2, null);
        if (!startsWith$default || identifier.length() == str.length()) {
            return null;
        }
        char charAt = identifier.charAt(str.length());
        if ('a' <= charAt && 'z' >= charAt) {
            return null;
        }
        if (str2 != null) {
            if (_Assertions.ENABLED && !z) {
                throw new AssertionError("Assertion failed");
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str2);
            removePrefix2 = StringsKt__StringsKt.removePrefix(identifier, (CharSequence) str);
            outline107.append(removePrefix2);
            return Name.identifier(outline107.toString());
        } else if (!z) {
            return name;
        } else {
            removePrefix = StringsKt__StringsKt.removePrefix(identifier, (CharSequence) str);
            String decapitalizeSmartForCompiler = CapitalizeDecapitalizeKt.decapitalizeSmartForCompiler(removePrefix, true);
            if (Name.isValidIdentifier(decapitalizeSmartForCompiler)) {
                return Name.identifier(decapitalizeSmartForCompiler);
            }
            return null;
        }
    }

    static /* synthetic */ Name propertyNameFromAccessorMethodName$default(Name name, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        return propertyNameFromAccessorMethodName(name, str, z, str2);
    }

    @NotNull
    public static final List<Name> propertyNamesBySetMethodName(@NotNull Name methodName) {
        List<Name> listOfNotNull;
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new Name[]{propertyNameBySetMethodName(methodName, false), propertyNameBySetMethodName(methodName, true)});
        return listOfNotNull;
    }
}
