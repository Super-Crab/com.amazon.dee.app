package io.ktor.http;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.mobilytics.configuration.Config;
import io.ktor.http.Parameters;
import io.ktor.util.KtorExperimentalAPI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.io.charsets.CharsetJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpUrlEncoded.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\n\u0010\u0007\u001a\u00060\bj\u0002`\t\u001a*\u0010\u0005\u001a\u00020\u0006*\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00040\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\t\u001a$\u0010\n\u001a\u00020\u0002*\u00020\u00012\f\b\u0002\u0010\u000b\u001a\u00060\fj\u0002`\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¨\u0006\u0010"}, d2 = {"formUrlEncode", "", "Lio/ktor/http/Parameters;", "", "Lkotlin/Pair;", "formUrlEncodeTo", "", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "parseUrlEncodedParameters", "defaultEncoding", "Ljava/nio/charset/Charset;", "Lkotlinx/io/charsets/Charset;", MetricsUtil.LegacyMetricTypes.LIMIT, "", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpUrlEncodedKt {
    @NotNull
    public static final String formUrlEncode(@NotNull List<Pair<String, String>> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        StringBuilder sb = new StringBuilder();
        formUrlEncodeTo(receiver$0, sb);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply {\n…codeTo(this)\n}.toString()");
        return sb2;
    }

    public static final void formUrlEncodeTo(@NotNull List<Pair<String, String>> receiver$0, @NotNull Appendable out) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        CollectionsKt___CollectionsKt.joinTo$default(receiver$0, out, WebConstants.UriConstants.AMPERSAND_KEY, null, null, 0, null, HttpUrlEncodedKt$formUrlEncodeTo$1.INSTANCE, 60, null);
    }

    @KtorExperimentalAPI
    @NotNull
    public static final Parameters parseUrlEncodedParameters(@NotNull String receiver$0, @NotNull Charset defaultEncoding, int i) {
        List<String> split$default;
        int collectionSizeOrDefault;
        Object obj;
        String name;
        String substringBefore$default;
        String substringAfter;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(defaultEncoding, "defaultEncoding");
        split$default = StringsKt__StringsKt.split$default((CharSequence) receiver$0, new String[]{WebConstants.UriConstants.AMPERSAND_KEY}, false, i, 2, (Object) null);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10);
        ArrayList<Pair> arrayList = new ArrayList(collectionSizeOrDefault);
        for (String str : split$default) {
            substringBefore$default = StringsKt__StringsKt.substringBefore$default(str, Config.Compare.EQUAL_TO, (String) null, 2, (Object) null);
            substringAfter = StringsKt__StringsKt.substringAfter(str, Config.Compare.EQUAL_TO, "");
            arrayList.add(TuplesKt.to(substringBefore$default, substringAfter));
        }
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (Intrinsics.areEqual((String) ((Pair) obj).getFirst(), "_charset_")) {
                break;
            }
        }
        Pair pair = (Pair) obj;
        if (pair == null || (name = (String) pair.getSecond()) == null) {
            name = CharsetJVMKt.getName(defaultEncoding);
        }
        Charset charset = Charset.forName(name);
        Parameters.Companion companion = Parameters.Companion;
        ParametersBuilder parametersBuilder = new ParametersBuilder(0, 1, null);
        for (Pair pair2 : arrayList) {
            Intrinsics.checkExpressionValueIsNotNull(charset, "charset");
            parametersBuilder.append(CodecsKt.decodeURLQueryComponent$default((String) pair2.component1(), 0, 0, false, charset, 7, null), CodecsKt.decodeURLQueryComponent$default((String) pair2.component2(), 0, 0, false, charset, 7, null));
        }
        return parametersBuilder.mo10292build();
    }

    @KtorExperimentalAPI
    @NotNull
    public static /* synthetic */ Parameters parseUrlEncodedParameters$default(String str, Charset charset, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i2 & 2) != 0) {
            i = 1000;
        }
        return parseUrlEncodedParameters(str, charset, i);
    }

    public static final void formUrlEncodeTo(@NotNull Parameters receiver$0, @NotNull Appendable out) {
        int collectionSizeOrDefault;
        List list;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(out, "out");
        Set<Map.Entry<String, List<String>>> entries = receiver$0.entries();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = entries.iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            if (((List) entry.getValue()).isEmpty()) {
                list = CollectionsKt__CollectionsJVMKt.listOf(TuplesKt.to(entry.getKey(), null));
            } else {
                Iterable<String> iterable = (Iterable) entry.getValue();
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
                for (String str : iterable) {
                    arrayList2.add(TuplesKt.to(entry.getKey(), str));
                }
                list = arrayList2;
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, list);
        }
        formUrlEncodeTo(arrayList, out);
    }

    @NotNull
    public static final String formUrlEncode(@NotNull Parameters receiver$0) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Set<Map.Entry<String, List<String>>> entries = receiver$0.entries();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it2 = entries.iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            Iterable<String> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (String str : iterable) {
                arrayList2.add(TuplesKt.to(entry.getKey(), str));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList2);
        }
        return formUrlEncode(arrayList);
    }
}
