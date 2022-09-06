package org.apache.commons.collections4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.apache.commons.collections4.multimap.TransformedMultiValuedMap;
import org.apache.commons.collections4.multimap.UnmodifiableMultiValuedMap;
/* loaded from: classes4.dex */
public class MultiMapUtils {
    public static final MultiValuedMap EMPTY_MULTI_VALUED_MAP = UnmodifiableMultiValuedMap.unmodifiableMultiValuedMap(new ArrayListValuedHashMap(0, 0));

    private MultiMapUtils() {
    }

    public static <K, V> MultiValuedMap<K, V> emptyIfNull(MultiValuedMap<K, V> multiValuedMap) {
        return multiValuedMap == null ? EMPTY_MULTI_VALUED_MAP : multiValuedMap;
    }

    public static <K, V> MultiValuedMap<K, V> emptyMultiValuedMap() {
        return EMPTY_MULTI_VALUED_MAP;
    }

    public static <K, V> Collection<V> getCollection(MultiValuedMap<K, V> multiValuedMap, K k) {
        if (multiValuedMap != null) {
            return multiValuedMap.mo12740get(k);
        }
        return null;
    }

    public static <K, V> Bag<V> getValuesAsBag(MultiValuedMap<K, V> multiValuedMap, K k) {
        if (multiValuedMap != null) {
            Collection<V> mo12740get = multiValuedMap.mo12740get(k);
            if (mo12740get instanceof Bag) {
                return (Bag) mo12740get;
            }
            return new HashBag(mo12740get);
        }
        return null;
    }

    public static <K, V> List<V> getValuesAsList(MultiValuedMap<K, V> multiValuedMap, K k) {
        if (multiValuedMap != null) {
            Collection<V> mo12740get = multiValuedMap.mo12740get(k);
            if (mo12740get instanceof List) {
                return (List) mo12740get;
            }
            return new ArrayList(mo12740get);
        }
        return null;
    }

    public static <K, V> Set<V> getValuesAsSet(MultiValuedMap<K, V> multiValuedMap, K k) {
        if (multiValuedMap != null) {
            Collection<V> mo12740get = multiValuedMap.mo12740get(k);
            if (mo12740get instanceof Set) {
                return (Set) mo12740get;
            }
            return new HashSet(mo12740get);
        }
        return null;
    }

    public static boolean isEmpty(MultiValuedMap<?, ?> multiValuedMap) {
        return multiValuedMap == null || multiValuedMap.isEmpty();
    }

    public static <K, V> ListValuedMap<K, V> newListValuedHashMap() {
        return new ArrayListValuedHashMap();
    }

    public static <K, V> SetValuedMap<K, V> newSetValuedHashMap() {
        return new HashSetValuedHashMap();
    }

    public static <K, V> MultiValuedMap<K, V> transformedMultiValuedMap(MultiValuedMap<K, V> multiValuedMap, Transformer<? super K, ? extends K> transformer, Transformer<? super V, ? extends V> transformer2) {
        return TransformedMultiValuedMap.transformingMap(multiValuedMap, transformer, transformer2);
    }

    public static <K, V> MultiValuedMap<K, V> unmodifiableMultiValuedMap(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        return UnmodifiableMultiValuedMap.unmodifiableMultiValuedMap(multiValuedMap);
    }
}
