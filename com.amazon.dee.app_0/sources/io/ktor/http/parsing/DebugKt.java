package io.ktor.http.parsing;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Debug.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0016\u0010\u0006\u001a\u00020\u0001*\u00020\u00072\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\b"}, d2 = {"printlnWithOffset", "", "offset", "", "node", "", "printDebug", "Lio/ktor/http/parsing/Grammar;", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DebugKt {
    public static final void printDebug(@NotNull Grammar receiver$0, int i) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (receiver$0 instanceof StringGrammar) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("STRING[");
            outline107.append(Regex.Companion.escape(((StringGrammar) receiver$0).getValue()));
            outline107.append(JsonReaderKt.END_LIST);
            printlnWithOffset(i, outline107.toString());
        } else if (receiver$0 instanceof RawGrammar) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("STRING[");
            outline1072.append(((RawGrammar) receiver$0).getValue());
            outline1072.append(JsonReaderKt.END_LIST);
            printlnWithOffset(i, outline1072.toString());
        } else if (receiver$0 instanceof NamedGrammar) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("NAMED[");
            NamedGrammar namedGrammar = (NamedGrammar) receiver$0;
            outline1073.append(namedGrammar.getName());
            outline1073.append(JsonReaderKt.END_LIST);
            printlnWithOffset(i, outline1073.toString());
            printDebug(namedGrammar.getGrammar(), i + 2);
        } else if (receiver$0 instanceof SequenceGrammar) {
            printlnWithOffset(i, "SEQUENCE");
            for (Grammar grammar : ((SequenceGrammar) receiver$0).getGrammars()) {
                printDebug(grammar, i + 2);
            }
        } else if (receiver$0 instanceof OrGrammar) {
            printlnWithOffset(i, "OR");
            for (Grammar grammar2 : ((OrGrammar) receiver$0).getGrammars()) {
                printDebug(grammar2, i + 2);
            }
        } else if (receiver$0 instanceof MaybeGrammar) {
            printlnWithOffset(i, "MAYBE");
            printDebug(((MaybeGrammar) receiver$0).getGrammar(), i + 2);
        } else if (receiver$0 instanceof ManyGrammar) {
            printlnWithOffset(i, "MANY");
            printDebug(((ManyGrammar) receiver$0).getGrammar(), i + 2);
        } else if (receiver$0 instanceof AtLeastOne) {
            printlnWithOffset(i, "MANY_NOT_EMPTY");
            printDebug(((AtLeastOne) receiver$0).getGrammar(), i + 2);
        } else if (receiver$0 instanceof AnyOfGrammar) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("ANY_OF[");
            outline1074.append(Regex.Companion.escape(((AnyOfGrammar) receiver$0).getValue()));
            outline1074.append(JsonReaderKt.END_LIST);
            printlnWithOffset(i, outline1074.toString());
        } else if (!(receiver$0 instanceof RangeGrammar)) {
            throw new NoWhenBranchMatchedException();
        } else {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("RANGE[");
            RangeGrammar rangeGrammar = (RangeGrammar) receiver$0;
            outline1075.append(rangeGrammar.getFrom());
            outline1075.append('-');
            outline1075.append(rangeGrammar.getTo());
            outline1075.append(JsonReaderKt.END_LIST);
            printlnWithOffset(i, outline1075.toString());
        }
    }

    public static /* synthetic */ void printDebug$default(Grammar grammar, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        printDebug(grammar, i);
    }

    private static final void printlnWithOffset(int i, Object obj) {
        String repeat;
        StringBuilder sb = new StringBuilder();
        repeat = StringsKt__StringsJVMKt.repeat(" ", i);
        sb.append(repeat);
        sb.append(i / 2);
        sb.append(RealTimeTextConstants.COLON_SPACE);
        sb.append(obj);
        System.out.println((Object) sb.toString());
    }
}
