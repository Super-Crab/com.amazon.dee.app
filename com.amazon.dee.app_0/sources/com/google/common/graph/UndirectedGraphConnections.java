package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.graph.ElementOrder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes3.dex */
final class UndirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private final Map<N, V> adjacentNodeValues;

    /* renamed from: com.google.common.graph.UndirectedGraphConnections$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$graph$ElementOrder$Type = new int[ElementOrder.Type.values().length];

        static {
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[ElementOrder.Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[ElementOrder.Type.STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private UndirectedGraphConnections(Map<N, V> map) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <N, V> UndirectedGraphConnections<N, V> of(ElementOrder<N> elementOrder) {
        int ordinal = elementOrder.type().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new UndirectedGraphConnections<>(new LinkedHashMap(2, 1.0f));
            }
            throw new AssertionError(elementOrder.type());
        }
        return new UndirectedGraphConnections<>(new HashMap(2, 1.0f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <N, V> UndirectedGraphConnections<N, V> ofImmutable(Map<N, V> map) {
        return new UndirectedGraphConnections<>(ImmutableMap.copyOf((Map) map));
    }

    @Override // com.google.common.graph.GraphConnections
    public void addPredecessor(N n, V v) {
        addSuccessor(n, v);
    }

    @Override // com.google.common.graph.GraphConnections
    public V addSuccessor(N n, V v) {
        return this.adjacentNodeValues.put(n, v);
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    @Override // com.google.common.graph.GraphConnections
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N n) {
        return Iterators.transform(this.adjacentNodeValues.keySet().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.UndirectedGraphConnections.1
            @Override // com.google.common.base.Function
            /* renamed from: apply  reason: collision with other method in class */
            public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                return mo8172apply((AnonymousClass1) obj);
            }

            @Override // com.google.common.base.Function
            /* renamed from: apply */
            public EndpointPair<N> mo8172apply(N n2) {
                return EndpointPair.unordered(n, n2);
            }
        });
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return adjacentNodes();
    }

    @Override // com.google.common.graph.GraphConnections
    public void removePredecessor(N n) {
        removeSuccessor(n);
    }

    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(N n) {
        return this.adjacentNodeValues.remove(n);
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return adjacentNodes();
    }

    @Override // com.google.common.graph.GraphConnections
    public V value(N n) {
        return this.adjacentNodeValues.get(n);
    }
}
