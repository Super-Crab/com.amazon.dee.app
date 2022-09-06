package org.apache.commons.collections4.trie;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.trie.analyzer.StringKeyAnalyzer;
/* loaded from: classes4.dex */
public class PatriciaTrie<E> extends AbstractPatriciaTrie<String, E> {
    private static final long serialVersionUID = 4446367780901817838L;

    public PatriciaTrie() {
        super(new StringKeyAnalyzer());
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, java.util.SortedMap
    public /* bridge */ /* synthetic */ Comparator comparator() {
        return super.comparator();
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public /* bridge */ /* synthetic */ boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, java.util.AbstractMap, java.util.Map, java.util.SortedMap, org.apache.commons.collections4.Get
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public /* bridge */ /* synthetic */ Object mo12663get(Object obj) {
        return super.mo12663get(obj);
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, java.util.AbstractMap, java.util.Map, java.util.SortedMap, org.apache.commons.collections4.Get
    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, org.apache.commons.collections4.OrderedMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator */
    public /* bridge */ /* synthetic */ OrderedMapIterator mo12767mapIterator() {
        return super.mo12767mapIterator();
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public /* bridge */ /* synthetic */ Object mo12668remove(Object obj) {
        return super.mo12668remove(obj);
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override // org.apache.commons.collections4.trie.AbstractPatriciaTrie, java.util.AbstractMap, java.util.Map, java.util.SortedMap, org.apache.commons.collections4.Get
    /* renamed from: values */
    public /* bridge */ /* synthetic */ Collection mo12691values() {
        return super.mo12691values();
    }

    public PatriciaTrie(Map<? extends String, ? extends E> map) {
        super(new StringKeyAnalyzer(), map);
    }
}
