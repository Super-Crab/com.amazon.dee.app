package io.ktor.util;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.dee.app.metrics.MetricsConstants;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CaseInsensitiveMap.kt */
@InternalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0016J\u0015\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0096\u0002J\u0018\u0010!\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001a\u001a\u00020\u0003H\u0096\u0002¢\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020\u000fH\u0016J\b\u0010$\u001a\u00020\u0019H\u0016J\u001f\u0010%\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010&J\u001e\u0010'\u001a\u00020\u00172\u0014\u0010(\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000)H\u0016J\u0017\u0010*\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001a\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\"R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u00000\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006+"}, d2 = {"Lio/ktor/util/CaseInsensitiveMap;", MAPCookie.KEY_VALUE, "", "", "()V", "delegate", "Lio/ktor/util/CaseInsensitiveString;", "entries", "", "", "getEntries", "()Ljava/util/Set;", QuickTimeAtomTypes.ATOM_KEYS, "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", MetricsConstants.Method.CACHE_CLEAR, "", "containsKey", "", "key", "containsValue", "value", "(Ljava/lang/Object;)Z", "equals", "other", "", MetricsConstants.Method.CACHE_GET, "(Ljava/lang/String;)Ljava/lang/Object;", "hashCode", "isEmpty", MetricsConstants.Method.CACHE_PUT, "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", ADMConstants.EXTRA_FROM, "", BulkOperationType.remove, "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class CaseInsensitiveMap<Value> implements Map<String, Value>, KMutableMap {
    private final Map<CaseInsensitiveString, Value> delegate = new LinkedHashMap();

    @Override // java.util.Map
    public void clear() {
        this.delegate.clear();
    }

    @Override // java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return containsKey((String) obj);
        }
        return false;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.delegate.containsValue(obj);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<String, Value>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof CaseInsensitiveMap)) {
            return false;
        }
        return Intrinsics.areEqual(((CaseInsensitiveMap) obj).delegate, this.delegate);
    }

    @Override // java.util.Map
    public final /* bridge */ Value get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    @NotNull
    public Set<Map.Entry<String, Value>> getEntries() {
        return new DelegatingMutableSet(this.delegate.entrySet(), CaseInsensitiveMap$entries$1.INSTANCE, CaseInsensitiveMap$entries$2.INSTANCE);
    }

    @NotNull
    public Set<String> getKeys() {
        return new DelegatingMutableSet(this.delegate.keySet(), CaseInsensitiveMap$keys$1.INSTANCE, CaseInsensitiveMap$keys$2.INSTANCE);
    }

    public int getSize() {
        return this.delegate.size();
    }

    @NotNull
    public Collection<Value> getValues() {
        return this.delegate.values();
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.delegate.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override // java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object put(String str, Object obj) {
        return put2(str, (String) obj);
    }

    @Override // java.util.Map
    public void putAll(@NotNull Map<? extends String, ? extends Value> from) {
        Intrinsics.checkParameterIsNotNull(from, "from");
        for (Map.Entry<? extends String, ? extends Value> entry : from.entrySet()) {
            put2(entry.getKey(), (String) entry.getValue());
        }
    }

    @Override // java.util.Map
    public final /* bridge */ Value remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<Value> values() {
        return getValues();
    }

    public boolean containsKey(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.delegate.containsKey(new CaseInsensitiveString(key));
    }

    @Nullable
    public Value get(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.delegate.get(TextKt.caseInsensitive(key));
    }

    @Nullable
    /* renamed from: put  reason: avoid collision after fix types in other method */
    public Value put2(@NotNull String key, Value value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.delegate.put(TextKt.caseInsensitive(key), value);
    }

    @Nullable
    public Value remove(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.delegate.remove(TextKt.caseInsensitive(key));
    }
}
