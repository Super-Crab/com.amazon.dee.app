package kotlin.text;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StringNumberConversionsJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", "value", "Lkotlin/text/Regex;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ScreenFloatValueRegEx {
    public static final ScreenFloatValueRegEx INSTANCE = new ScreenFloatValueRegEx();
    @JvmField
    @NotNull
    public static final Regex value;

    static {
        String outline72 = GeneratedOutlineSupport1.outline72("[eE][+-]?", "(\\p{Digit}+)");
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("(0[xX]", "(\\p{XDigit}+)", "(\\.)?)|", "(0[xX]", "(\\p{XDigit}+)");
        outline116.append("?(\\.)");
        outline116.append("(\\p{XDigit}+)");
        outline116.append(')');
        String sb = outline116.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append('(');
        sb2.append("(\\p{Digit}+)");
        sb2.append("(\\.)?(");
        sb2.append("(\\p{Digit}+)");
        sb2.append("?)(");
        GeneratedOutlineSupport1.outline181(sb2, outline72, ")?)|", "(\\.(", "(\\p{Digit}+)");
        GeneratedOutlineSupport1.outline181(sb2, ")(", outline72, ")?)|", "((");
        sb2.append(sb);
        sb2.append(")[pP][+-]?");
        sb2.append("(\\p{Digit}+)");
        sb2.append(')');
        value = new Regex(GeneratedOutlineSupport1.outline75("[\\x00-\\x20]*[+-]?(NaN|Infinity|((", sb2.toString(), ")[fFdD]?))[\\x00-\\x20]*"));
    }

    private ScreenFloatValueRegEx() {
    }
}
