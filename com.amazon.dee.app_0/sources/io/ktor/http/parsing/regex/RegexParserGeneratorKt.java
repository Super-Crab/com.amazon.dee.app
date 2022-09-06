package io.ktor.http.parsing.regex;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureName;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.parsing.AnyOfGrammar;
import io.ktor.http.parsing.AtLeastOne;
import io.ktor.http.parsing.ComplexGrammar;
import io.ktor.http.parsing.Grammar;
import io.ktor.http.parsing.ManyGrammar;
import io.ktor.http.parsing.MaybeGrammar;
import io.ktor.http.parsing.NamedGrammar;
import io.ktor.http.parsing.OrGrammar;
import io.ktor.http.parsing.Parser;
import io.ktor.http.parsing.RangeGrammar;
import io.ktor.http.parsing.RawGrammar;
import io.ktor.http.parsing.SimpleGrammar;
import io.ktor.http.parsing.StringGrammar;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: RegexParserGenerator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00022\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0002\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0000\u001a:\u0010\u000b\u001a\u00020\f*\u00020\n2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0002¨\u0006\u0011"}, d2 = {BulkOperationType.add, "", "", "", "", "", "key", "value", "buildRegexParser", "Lio/ktor/http/parsing/Parser;", "Lio/ktor/http/parsing/Grammar;", "toRegex", "Lio/ktor/http/parsing/regex/GrammarRegex;", FeatureName.GROUPS, "offset", "shouldGroup", "", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class RegexParserGeneratorKt {
    private static final void add(@NotNull Map<String, List<Integer>> map, String str, int i) {
        if (!map.containsKey(str)) {
            map.put(str, new ArrayList());
        }
        List<Integer> list = map.get(str);
        if (list == null) {
            Intrinsics.throwNpe();
        }
        list.add(Integer.valueOf(i));
    }

    @NotNull
    public static final Parser buildRegexParser(@NotNull Grammar receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        return new RegexParser(new Regex(toRegex$default(receiver$0, linkedHashMap, 0, false, 6, null).getRegex()), linkedHashMap);
    }

    private static final GrammarRegex toRegex(@NotNull Grammar grammar, Map<String, List<Integer>> map, int i, boolean z) {
        char c;
        if (grammar instanceof StringGrammar) {
            return new GrammarRegex(Regex.Companion.escape(((StringGrammar) grammar).getValue()), 0, false, 6, null);
        }
        if (grammar instanceof RawGrammar) {
            return new GrammarRegex(((RawGrammar) grammar).getValue(), 0, false, 6, null);
        }
        if (grammar instanceof NamedGrammar) {
            NamedGrammar namedGrammar = (NamedGrammar) grammar;
            GrammarRegex regex$default = toRegex$default(namedGrammar.getGrammar(), map, i + 1, false, 4, null);
            add(map, namedGrammar.getName(), i);
            return new GrammarRegex(regex$default.getRegex(), regex$default.getGroupsCount(), true);
        } else if (grammar instanceof ComplexGrammar) {
            StringBuilder sb = new StringBuilder();
            int i2 = z ? i + 1 : i;
            int i3 = 0;
            for (Object obj : ((ComplexGrammar) grammar).getGrammars()) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                GrammarRegex regex = toRegex((Grammar) obj, map, i2, true);
                if (i3 != 0 && (grammar instanceof OrGrammar)) {
                    sb.append(AccessoryMetricsConstants.DELIMITER);
                }
                sb.append(regex.getRegex());
                i2 += regex.getGroupsCount();
                i3 = i4;
            }
            int i5 = i2 - i;
            if (z) {
                i5--;
            }
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "expression.toString()");
            return new GrammarRegex(sb2, i5, z);
        } else if (grammar instanceof SimpleGrammar) {
            if (grammar instanceof MaybeGrammar) {
                c = Constants.DEFAULT_IMAGE_CHAR;
            } else if (grammar instanceof ManyGrammar) {
                c = '*';
            } else if (!(grammar instanceof AtLeastOne)) {
                throw new IllegalStateException(("Unsupported simple grammar element: " + grammar).toString());
            } else {
                c = '+';
            }
            GrammarRegex regex2 = toRegex(((SimpleGrammar) grammar).getGrammar(), map, i, true);
            return new GrammarRegex(GeneratedOutlineSupport1.outline89(new StringBuilder(), regex2.getRegex(), c), regex2.getGroupsCount(), false, 4, null);
        } else if (grammar instanceof AnyOfGrammar) {
            StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
            outline104.append(Regex.Companion.escape(((AnyOfGrammar) grammar).getValue()));
            outline104.append(JsonReaderKt.END_LIST);
            return new GrammarRegex(outline104.toString(), 0, false, 6, null);
        } else if (!(grammar instanceof RangeGrammar)) {
            throw new IllegalStateException(("Unsupported grammar element: " + grammar).toString());
        } else {
            StringBuilder outline1042 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
            RangeGrammar rangeGrammar = (RangeGrammar) grammar;
            outline1042.append(rangeGrammar.getFrom());
            outline1042.append('-');
            outline1042.append(rangeGrammar.getTo());
            outline1042.append(JsonReaderKt.END_LIST);
            return new GrammarRegex(outline1042.toString(), 0, false, 6, null);
        }
    }

    static /* synthetic */ GrammarRegex toRegex$default(Grammar grammar, Map map, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return toRegex(grammar, map, i, z);
    }
}
