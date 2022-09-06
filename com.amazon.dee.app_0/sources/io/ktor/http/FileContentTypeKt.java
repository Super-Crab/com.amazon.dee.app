package io.ktor.http;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.routing.api.RouteParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.ContentType;
import io.ktor.util.CharsetKt;
import io.ktor.util.CollectionsKt;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: FileContentType.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0006\u001a\u00020\u0004*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002\u001a\u0012\u0010\t\u001a\u00020\u0004*\u00020\u00072\u0006\u0010\n\u001a\u00020\u0002\u001a\u0010\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003*\u00020\u0004\u001a\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00072\u0006\u0010\r\u001a\u00020\u0002\u001a\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00072\u0006\u0010\n\u001a\u00020\u0002\u001a<\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u00030\u0001\"\u0004\b\u0000\u0010\u0010\"\u0004\b\u0001\u0010\u0011*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00110\u00130\u0012H\u0000\u001a\u0012\u0010\u0014\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000\u001a\f\u0010\u0015\u001a\u00020\u0004*\u00020\u0002H\u0000\" \u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"contentTypesByExtensions", "", "", "", "Lio/ktor/http/ContentType;", "extensionsByContentType", "defaultForFileExtension", "Lio/ktor/http/ContentType$Companion;", "extension", "defaultForFilePath", RouteParameter.PATH, "fileExtensions", "fromFileExtension", "ext", "fromFilePath", "groupByPairs", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "Lkotlin/sequences/Sequence;", "Lkotlin/Pair;", "selectDefault", "toContentType", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class FileContentTypeKt {
    private static final Map<String, List<ContentType>> contentTypesByExtensions;
    private static final Map<ContentType, List<String>> extensionsByContentType;

    static {
        Sequence asSequence;
        Sequence asSequence2;
        Sequence map;
        Map<String, List<ContentType>> caseInsensitiveMap = CollectionsKt.caseInsensitiveMap();
        asSequence = CollectionsKt___CollectionsKt.asSequence(MimesKt.getMimes());
        caseInsensitiveMap.putAll(groupByPairs(asSequence));
        contentTypesByExtensions = caseInsensitiveMap;
        asSequence2 = CollectionsKt___CollectionsKt.asSequence(MimesKt.getMimes());
        map = SequencesKt___SequencesKt.map(asSequence2, FileContentTypeKt$extensionsByContentType$1.INSTANCE);
        extensionsByContentType = groupByPairs(map);
    }

    @NotNull
    public static final ContentType defaultForFileExtension(@NotNull ContentType.Companion receiver$0, @NotNull String extension) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(extension, "extension");
        return selectDefault(fromFileExtension(ContentType.Companion, extension));
    }

    @NotNull
    public static final ContentType defaultForFilePath(@NotNull ContentType.Companion receiver$0, @NotNull String path) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(path, "path");
        return selectDefault(fromFilePath(ContentType.Companion, path));
    }

    @NotNull
    public static final List<String> fileExtensions(@NotNull ContentType receiver$0) {
        List<String> emptyList;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        List<String> list = extensionsByContentType.get(receiver$0);
        if (list == null) {
            list = extensionsByContentType.get(receiver$0.withoutParameters());
        }
        if (list != null) {
            return list;
        }
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @NotNull
    public static final List<ContentType> fromFileExtension(@NotNull ContentType.Companion receiver$0, @NotNull String ext) {
        String removePrefix;
        List<ContentType> emptyList;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(ext, "ext");
        removePrefix = StringsKt__StringsKt.removePrefix(ext, (CharSequence) ".");
        while (true) {
            if (removePrefix.length() > 0) {
                List<ContentType> list = contentTypesByExtensions.get(removePrefix);
                if (list != null) {
                    return list;
                }
                removePrefix = StringsKt__StringsKt.substringAfter(removePrefix, ".", "");
            } else {
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                return emptyList;
            }
        }
    }

    @NotNull
    public static final List<ContentType> fromFilePath(@NotNull ContentType.Companion receiver$0, @NotNull String path) {
        int lastIndexOfAny$default;
        int indexOf$default;
        List<ContentType> emptyList;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(path, "path");
        lastIndexOfAny$default = StringsKt__StringsKt.lastIndexOfAny$default((CharSequence) path, CharsetKt.toCharArray("/\\"), 0, false, 6, (Object) null);
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) path, '.', lastIndexOfAny$default + 1, false, 4, (Object) null);
        if (indexOf$default == -1) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
        String substring = path.substring(indexOf$default + 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return fromFileExtension(receiver$0, substring);
    }

    @NotNull
    public static final <A, B> Map<A, List<B>> groupByPairs(@NotNull Sequence<? extends Pair<? extends A, ? extends B>> receiver$0) {
        int mapCapacity;
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Pair<? extends A, ? extends B> pair : receiver$0) {
            A first = pair.getFirst();
            Object obj = linkedHashMap.get(first);
            if (obj == null) {
                obj = GeneratedOutlineSupport1.outline131(linkedHashMap, first);
            }
            ((List) obj).add(pair);
        }
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(linkedHashMap.size());
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(mapCapacity);
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Object key = entry.getKey();
            Iterable<Pair> iterable = (Iterable) entry.getValue();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (Pair pair2 : iterable) {
                arrayList.add(pair2.getSecond());
            }
            linkedHashMap2.put(key, arrayList);
        }
        return linkedHashMap2;
    }

    @NotNull
    public static final ContentType selectDefault(@NotNull List<ContentType> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ContentType contentType = (ContentType) kotlin.collections.CollectionsKt.firstOrNull((List<? extends Object>) receiver$0);
        if (contentType == null) {
            contentType = ContentType.Application.INSTANCE.getOctetStream();
        }
        return (!Intrinsics.areEqual(contentType.getContentType(), "text") || ContentTypesKt.charset(contentType) != null) ? contentType : ContentTypesKt.withCharset(contentType, Charsets.UTF_8);
    }

    @NotNull
    public static final ContentType toContentType(@NotNull String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        try {
            return ContentType.Companion.parse(receiver$0);
        } catch (Throwable th) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Failed to parse ", receiver$0), th);
        }
    }
}
