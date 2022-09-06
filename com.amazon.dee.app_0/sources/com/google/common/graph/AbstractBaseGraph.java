package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class AbstractBaseGraph<N> implements BaseGraph<N> {
    @Override // com.google.common.graph.BaseGraph
    public int degree(N n) {
        if (isDirected()) {
            return IntMath.saturatedAdd(mo8170predecessors((AbstractBaseGraph<N>) n).size(), mo8171successors((AbstractBaseGraph<N>) n).size());
        }
        Set<N> adjacentNodes = adjacentNodes(n);
        return IntMath.saturatedAdd(adjacentNodes.size(), (!allowsSelfLoops() || !adjacentNodes.contains(n)) ? 0 : 1);
    }

    protected long edgeCount() {
        Iterator<N> it2;
        long j = 0;
        while (nodes().iterator().hasNext()) {
            j += degree(it2.next());
        }
        Preconditions.checkState((1 & j) == 0);
        return j >>> 1;
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> edges() {
        return new AbstractSet<EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                if (!(obj instanceof EndpointPair)) {
                    return false;
                }
                EndpointPair<?> endpointPair = (EndpointPair) obj;
                return AbstractBaseGraph.this.isOrderingCompatible(endpointPair) && AbstractBaseGraph.this.nodes().contains(endpointPair.nodeU()) && AbstractBaseGraph.this.mo8171successors((AbstractBaseGraph) endpointPair.nodeU()).contains(endpointPair.nodeV());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return Ints.saturatedCast(AbstractBaseGraph.this.edgeCount());
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            /* renamed from: iterator */
            public UnmodifiableIterator<EndpointPair<N>> mo8129iterator() {
                return EndpointPairIterator.of(AbstractBaseGraph.this);
            }
        };
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n, N n2) {
        Preconditions.checkNotNull(n);
        Preconditions.checkNotNull(n2);
        return nodes().contains(n) && mo8171successors((AbstractBaseGraph<N>) n).contains(n2);
    }

    @Override // com.google.common.graph.BaseGraph
    public int inDegree(N n) {
        return isDirected() ? mo8170predecessors((AbstractBaseGraph<N>) n).size() : degree(n);
    }

    @Override // com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return ElementOrder.unordered();
    }

    @Override // com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> incidentEdges(N n) {
        Preconditions.checkNotNull(n);
        Preconditions.checkArgument(nodes().contains(n), "Node %s is not an element of this graph.", n);
        return new IncidentEdgeSet<N>(this, this, n) { // from class: com.google.common.graph.AbstractBaseGraph.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            /* renamed from: iterator */
            public UnmodifiableIterator<EndpointPair<N>> mo8130iterator() {
                if (this.graph.isDirected()) {
                    return Iterators.unmodifiableIterator(Iterators.concat(Iterators.transform(this.graph.mo8170predecessors((BaseGraph<N>) this.node).iterator(), new Function<N, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.2.1
                        @Override // com.google.common.base.Function
                        /* renamed from: apply  reason: collision with other method in class */
                        public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                            return mo8172apply((AnonymousClass1) obj);
                        }

                        @Override // com.google.common.base.Function
                        /* renamed from: apply */
                        public EndpointPair<N> mo8172apply(N n2) {
                            return EndpointPair.ordered(n2, AnonymousClass2.this.node);
                        }
                    }), Iterators.transform(Sets.difference(this.graph.mo8171successors((BaseGraph<N>) this.node), ImmutableSet.of(this.node)).iterator(), new Function<N, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.2.2
                        @Override // com.google.common.base.Function
                        /* renamed from: apply  reason: collision with other method in class */
                        public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                            return mo8172apply((C01072) obj);
                        }

                        @Override // com.google.common.base.Function
                        /* renamed from: apply */
                        public EndpointPair<N> mo8172apply(N n2) {
                            return EndpointPair.ordered(AnonymousClass2.this.node, n2);
                        }
                    })));
                }
                return Iterators.unmodifiableIterator(Iterators.transform(this.graph.adjacentNodes(this.node).iterator(), new Function<N, EndpointPair<N>>() { // from class: com.google.common.graph.AbstractBaseGraph.2.3
                    @Override // com.google.common.base.Function
                    /* renamed from: apply  reason: collision with other method in class */
                    public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                        return mo8172apply((AnonymousClass3) obj);
                    }

                    @Override // com.google.common.base.Function
                    /* renamed from: apply */
                    public EndpointPair<N> mo8172apply(N n2) {
                        return EndpointPair.unordered(AnonymousClass2.this.node, n2);
                    }
                }));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isOrderingCompatible(EndpointPair<?> endpointPair) {
        return endpointPair.isOrdered() || !isDirected();
    }

    @Override // com.google.common.graph.BaseGraph
    public int outDegree(N n) {
        return isDirected() ? mo8171successors((AbstractBaseGraph<N>) n).size() : degree(n);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void validateEndpoints(EndpointPair<?> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        Preconditions.checkArgument(isOrderingCompatible(endpointPair), "Mismatch: unordered endpoints cannot be used with directed graphs");
    }

    @Override // com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        Preconditions.checkNotNull(endpointPair);
        if (!isOrderingCompatible(endpointPair)) {
            return false;
        }
        N nodeU = endpointPair.nodeU();
        return nodes().contains(nodeU) && mo8171successors((AbstractBaseGraph<N>) nodeU).contains(endpointPair.nodeV());
    }
}
