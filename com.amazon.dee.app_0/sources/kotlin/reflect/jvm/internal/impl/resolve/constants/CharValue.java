package kotlin.reflect.jvm.internal.impl.resolve.constants;

import com.amazon.alexa.biloba.utils.WebConstants;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* compiled from: constantValues.kt */
/* loaded from: classes4.dex */
public final class CharValue extends IntegerValueConstant<Character> {
    public CharValue(char c) {
        super(Character.valueOf(c));
    }

    private final String getPrintablePart(char c) {
        switch (c) {
            case '\b':
                return "\\b";
            case '\t':
                return "\\t";
            case '\n':
                return "\\n";
            case 11:
            default:
                return isPrintableUnicode(c) ? String.valueOf(c) : WebConstants.UriConstants.QUESTIONMARK_KEY;
            case '\f':
                return "\\f";
            case '\r':
                return "\\r";
        }
    }

    private final boolean isPrintableUnicode(char c) {
        byte type = (byte) Character.getType(c);
        return (type == 0 || type == 13 || type == 14 || type == 15 || type == 16 || type == 18 || type == 19) ? false : true;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    public String toString() {
        Object[] objArr = {Integer.valueOf(getValue().charValue()), getPrintablePart(getValue().charValue())};
        String format = String.format("\\u%04X ('%s')", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return format;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    @NotNull
    /* renamed from: getType  reason: collision with other method in class */
    public SimpleType mo12026getType(@NotNull ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        SimpleType charType = module.getBuiltIns().getCharType();
        Intrinsics.checkExpressionValueIsNotNull(charType, "module.builtIns.charType");
        return charType;
    }
}
