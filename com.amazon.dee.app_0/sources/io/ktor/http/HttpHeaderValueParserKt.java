package io.ktor.http;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import io.ktor.util.KtorExperimentalAPI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpHeaderValueParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u001a \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0007\u001a>\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u001c\u0010\r\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00020\u000fj\b\u0012\u0004\u0012\u00020\u0002`\u00100\u000e2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a6\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u001c\u0010\u0012\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00130\u000fj\b\u0012\u0004\u0012\u00020\u0013`\u00100\u000eH\u0002\u001a$\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00152\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0002\u001a$\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\u00152\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0002\u001a\u001c\u0010\u0018\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0002\u001a$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00150\u001bH\u0007\u001a$\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u0001\"\u0004\b\u0000\u0010\u001d*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001d0\u00010\u000eH\u0002¨\u0006\u001e"}, d2 = {"parseAndSortContentTypeHeader", "", "Lio/ktor/http/HeaderValue;", "header", "", "parseAndSortHeader", "parseHeaderValue", "text", "parametersOnly", "", "parseHeaderValueItem", "", "start", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "Lkotlin/Lazy;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "parseHeaderValueParameter", "parameters", "Lio/ktor/http/HeaderValueParam;", "parseHeaderValueParameterValue", "Lkotlin/Pair;", "value", "parseHeaderValueParameterValueQuoted", "subtrim", "end", "toHeaderParamsList", "", "valueOrEmpty", ExifInterface.GPS_DIRECTION_TRUE, "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpHeaderValueParserKt {
    @NotNull
    public static final List<HeaderValue> parseAndSortContentTypeHeader(@Nullable String str) {
        List<HeaderValue> sortedWith;
        List<HeaderValue> parseHeaderValue = parseHeaderValue(str);
        final Comparator<T> comparator = new Comparator<T>() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortContentTypeHeader$$inlined$compareByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(Double.valueOf(((HeaderValue) t2).getQuality()), Double.valueOf(((HeaderValue) t).getQuality()));
                return compareValues;
            }
        };
        final Comparator<T> comparator2 = new Comparator<T>() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortContentTypeHeader$$inlined$thenBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                int compare = comparator.compare(t, t2);
                if (compare != 0) {
                    return compare;
                }
                ContentType parse = ContentType.Companion.parse(((HeaderValue) t).getValue());
                int i = 2;
                int i2 = Intrinsics.areEqual(parse.getContentType(), "*") ? 2 : 0;
                if (Intrinsics.areEqual(parse.getContentSubtype(), "*")) {
                    i2++;
                }
                Integer valueOf = Integer.valueOf(i2);
                ContentType parse2 = ContentType.Companion.parse(((HeaderValue) t2).getValue());
                if (!Intrinsics.areEqual(parse2.getContentType(), "*")) {
                    i = 0;
                }
                if (Intrinsics.areEqual(parse2.getContentSubtype(), "*")) {
                    i++;
                }
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(valueOf, Integer.valueOf(i));
                return compareValues;
            }
        };
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(parseHeaderValue, new Comparator<T>() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortContentTypeHeader$$inlined$thenByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                int compare = comparator2.compare(t, t2);
                if (compare != 0) {
                    return compare;
                }
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((HeaderValue) t2).getParams().size()), Integer.valueOf(((HeaderValue) t).getParams().size()));
                return compareValues;
            }
        });
        return sortedWith;
    }

    @NotNull
    public static final List<HeaderValue> parseAndSortHeader(@Nullable String str) {
        List<HeaderValue> sortedWith;
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(parseHeaderValue(str), new Comparator<T>() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseAndSortHeader$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int compareValues;
                compareValues = ComparisonsKt__ComparisonsKt.compareValues(Double.valueOf(((HeaderValue) t2).getQuality()), Double.valueOf(((HeaderValue) t).getQuality()));
                return compareValues;
            }
        });
        return sortedWith;
    }

    @NotNull
    public static final List<HeaderValue> parseHeaderValue(@Nullable String str) {
        return parseHeaderValue(str, false);
    }

    /* JADX WARN: Incorrect condition in loop: B:8:0x0016 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int parseHeaderValueItem(java.lang.String r5, int r6, kotlin.Lazy<? extends java.util.ArrayList<io.ktor.http.HeaderValue>> r7, boolean r8) {
        /*
            kotlin.LazyThreadSafetyMode r0 = kotlin.LazyThreadSafetyMode.NONE
            io.ktor.http.HttpHeaderValueParserKt$parseHeaderValueItem$parameters$1 r1 = io.ktor.http.HttpHeaderValueParserKt$parseHeaderValueItem$parameters$1.INSTANCE
            kotlin.Lazy r0 = kotlin.LazyKt.lazy(r0, r1)
            if (r8 == 0) goto Lf
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            goto L10
        Lf:
            r1 = 0
        L10:
            r2 = r1
            r1 = r6
        L12:
            int r3 = kotlin.text.StringsKt.getLastIndex(r5)
            if (r1 > r3) goto L5c
            char r3 = r5.charAt(r1)
            r4 = 44
            if (r3 == r4) goto L3b
            r4 = 59
            if (r3 == r4) goto L2e
            if (r8 == 0) goto L2b
            int r1 = parseHeaderValueParameter(r5, r1, r0)
            goto L12
        L2b:
            int r1 = r1 + 1
            goto L12
        L2e:
            if (r2 != 0) goto L34
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
        L34:
            int r1 = r1 + 1
            int r1 = parseHeaderValueParameter(r5, r1, r0)
            goto L12
        L3b:
            java.lang.Object r7 = r7.getValue()
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            io.ktor.http.HeaderValue r8 = new io.ktor.http.HeaderValue
            if (r2 == 0) goto L4a
            int r2 = r2.intValue()
            goto L4b
        L4a:
            r2 = r1
        L4b:
            java.lang.String r5 = subtrim(r5, r6, r2)
            java.util.List r6 = valueOrEmpty(r0)
            r8.<init>(r5, r6)
            r7.add(r8)
            int r1 = r1 + 1
            return r1
        L5c:
            java.lang.Object r7 = r7.getValue()
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            io.ktor.http.HeaderValue r8 = new io.ktor.http.HeaderValue
            if (r2 == 0) goto L6b
            int r2 = r2.intValue()
            goto L6c
        L6b:
            r2 = r1
        L6c:
            java.lang.String r5 = subtrim(r5, r6, r2)
            java.util.List r6 = valueOrEmpty(r0)
            r8.<init>(r5, r6)
            r7.add(r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HttpHeaderValueParserKt.parseHeaderValueItem(java.lang.String, int, kotlin.Lazy, boolean):int");
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x000c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final int parseHeaderValueParameter(java.lang.String r4, int r5, kotlin.Lazy<? extends java.util.ArrayList<io.ktor.http.HeaderValueParam>> r6) {
        /*
            io.ktor.http.HttpHeaderValueParserKt$parseHeaderValueParameter$1 r0 = new io.ktor.http.HttpHeaderValueParserKt$parseHeaderValueParameter$1
            r0.<init>(r6)
            r6 = r5
        L6:
            int r1 = kotlin.text.StringsKt.getLastIndex(r4)
            java.lang.String r2 = ""
            if (r6 > r1) goto L3f
            char r1 = r4.charAt(r6)
            r3 = 44
            if (r1 == r3) goto L3b
            r3 = 59
            if (r1 == r3) goto L3b
            r2 = 61
            if (r1 == r2) goto L21
            int r6 = r6 + 1
            goto L6
        L21:
            int r1 = r6 + 1
            kotlin.Pair r1 = parseHeaderValueParameterValue(r4, r1)
            java.lang.Object r2 = r1.component1()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r1 = r1.component2()
            java.lang.String r1 = (java.lang.String) r1
            r0.invoke(r4, r5, r6, r1)
            return r2
        L3b:
            r0.invoke(r4, r5, r6, r2)
            return r6
        L3f:
            r0.invoke(r4, r5, r6, r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HttpHeaderValueParserKt.parseHeaderValueParameter(java.lang.String, int, kotlin.Lazy):int");
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0005 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final kotlin.Pair<java.lang.Integer, java.lang.String> parseHeaderValueParameterValue(java.lang.String r3, int r4) {
        /*
            r0 = r4
        L1:
            int r1 = kotlin.text.StringsKt.getLastIndex(r3)
            if (r0 > r1) goto L2e
            char r1 = r3.charAt(r0)
            r2 = 34
            if (r1 == r2) goto L27
            r2 = 44
            if (r1 == r2) goto L1a
            r2 = 59
            if (r1 == r2) goto L1a
            int r0 = r0 + 1
            goto L1
        L1a:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            java.lang.String r3 = subtrim(r3, r4, r0)
            kotlin.Pair r3 = kotlin.TuplesKt.to(r1, r3)
            return r3
        L27:
            int r0 = r0 + 1
            kotlin.Pair r3 = parseHeaderValueParameterValueQuoted(r3, r0)
            return r3
        L2e:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            java.lang.String r3 = subtrim(r3, r4, r0)
            kotlin.Pair r3 = kotlin.TuplesKt.to(r1, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HttpHeaderValueParserKt.parseHeaderValueParameterValue(java.lang.String, int):kotlin.Pair");
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0009 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final kotlin.Pair<java.lang.Integer, java.lang.String> parseHeaderValueParameterValueQuoted(java.lang.String r3, int r4) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L5:
            int r1 = kotlin.text.StringsKt.getLastIndex(r3)
            if (r4 > r1) goto L44
            char r1 = r3.charAt(r4)
            r2 = 34
            if (r1 == r2) goto L35
            r2 = 92
            if (r1 == r2) goto L1d
            r0.append(r1)
        L1a:
            int r4 = r4 + 1
            goto L5
        L1d:
            int r2 = kotlin.text.StringsKt.getLastIndex(r3)
            int r2 = r2 + (-2)
            if (r4 >= r2) goto L31
            int r1 = r4 + 1
            char r1 = r3.charAt(r1)
            r0.append(r1)
            int r4 = r4 + 2
            goto L5
        L31:
            r0.append(r1)
            goto L1a
        L35:
            int r4 = r4 + 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            java.lang.String r4 = r0.toString()
            kotlin.Pair r3 = kotlin.TuplesKt.to(r3, r4)
            return r3
        L44:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            java.lang.String r4 = r0.toString()
            kotlin.Pair r3 = kotlin.TuplesKt.to(r3, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HttpHeaderValueParserKt.parseHeaderValueParameterValueQuoted(java.lang.String, int):kotlin.Pair");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String subtrim(@NotNull String str, int i, int i2) {
        CharSequence trim;
        if (str != null) {
            String substring = str.substring(i, i2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            trim = StringsKt__StringsKt.trim((CharSequence) substring);
            return trim.toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @KtorExperimentalAPI
    @NotNull
    public static final List<HeaderValueParam> toHeaderParamsList(@NotNull Iterable<Pair<String, String>> receiver$0) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(receiver$0, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Pair<String, String> pair : receiver$0) {
            arrayList.add(new HeaderValueParam(pair.getFirst(), pair.getSecond()));
        }
        return arrayList;
    }

    private static final <T> List<T> valueOrEmpty(@NotNull Lazy<? extends List<? extends T>> lazy) {
        List<T> emptyList;
        if (lazy.isInitialized()) {
            return (List) ((List<? extends T>) lazy.getValue());
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x0014 */
    @io.ktor.util.KtorExperimentalAPI
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.util.List<io.ktor.http.HeaderValue> parseHeaderValue(@org.jetbrains.annotations.Nullable java.lang.String r3, boolean r4) {
        /*
            if (r3 != 0) goto L7
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            return r3
        L7:
            r0 = 0
            kotlin.LazyThreadSafetyMode r1 = kotlin.LazyThreadSafetyMode.NONE
            io.ktor.http.HttpHeaderValueParserKt$parseHeaderValue$items$1 r2 = io.ktor.http.HttpHeaderValueParserKt$parseHeaderValue$items$1.INSTANCE
            kotlin.Lazy r1 = kotlin.LazyKt.lazy(r1, r2)
        L10:
            int r2 = kotlin.text.StringsKt.getLastIndex(r3)
            if (r0 > r2) goto L1b
            int r0 = parseHeaderValueItem(r3, r0, r1, r4)
            goto L10
        L1b:
            java.util.List r3 = valueOrEmpty(r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HttpHeaderValueParserKt.parseHeaderValue(java.lang.String, boolean):java.util.List");
    }
}
