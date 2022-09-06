package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Map;
@GwtCompatible
/* loaded from: classes3.dex */
interface FilteredMultimap<K, V> extends Multimap<K, V> {
    Predicate<? super Map.Entry<K, V>> entryPredicate();

    /* renamed from: unfiltered */
    Multimap<K, V> mo7647unfiltered();
}
