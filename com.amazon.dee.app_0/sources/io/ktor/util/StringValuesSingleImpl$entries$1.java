package io.ktor.util;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: StringValues.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001J\b\u0010\n\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"io/ktor/util/StringValuesSingleImpl$entries$1", "", "", "", "key", "getKey", "()Ljava/lang/String;", "value", "getValue", "()Ljava/util/List;", "toString", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class StringValuesSingleImpl$entries$1 implements Map.Entry<String, List<? extends String>>, KMappedMarker {
    @NotNull
    private final String key;
    final /* synthetic */ StringValuesSingleImpl this$0;
    @NotNull
    private final List<String> value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StringValuesSingleImpl$entries$1(StringValuesSingleImpl stringValuesSingleImpl) {
        this.this$0 = stringValuesSingleImpl;
        this.key = stringValuesSingleImpl.getName();
        this.value = stringValuesSingleImpl.getValues();
    }

    @Override // java.util.Map.Entry
    public /* synthetic */ List<? extends String> setValue(List<? extends String> list) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: setValue  reason: avoid collision after fix types in other method */
    public List<String> setValue2(List<String> list) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    public String toString() {
        return getKey() + Chars.EQ + getValue();
    }

    @Override // java.util.Map.Entry
    @NotNull
    public String getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    @NotNull
    public List<? extends String> getValue() {
        return this.value;
    }
}
