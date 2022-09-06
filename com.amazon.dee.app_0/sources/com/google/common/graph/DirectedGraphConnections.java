package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.ElementOrder;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    private final Map<N, Object> adjacentNodeValues;
    @NullableDecl
    private final List<NodeConnection<N>> orderedNodeConnections;
    private int predecessorCount;
    private int successorCount;

    /* renamed from: com.google.common.graph.DirectedGraphConnections$8  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass8 {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class NodeConnection<N> {
        final N node;

        /* loaded from: classes3.dex */
        static final class Pred<N> extends NodeConnection<N> {
            Pred(N n) {
                super(n);
            }

            public boolean equals(Object obj) {
                if (obj instanceof Pred) {
                    return this.node.equals(((Pred) obj).node);
                }
                return false;
            }

            public int hashCode() {
                return this.node.hashCode() + Pred.class.hashCode();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public static final class Succ<N> extends NodeConnection<N> {
            Succ(N n) {
                super(n);
            }

            public boolean equals(Object obj) {
                if (obj instanceof Succ) {
                    return this.node.equals(((Succ) obj).node);
                }
                return false;
            }

            public int hashCode() {
                return this.node.hashCode() + Succ.class.hashCode();
            }
        }

        NodeConnection(N n) {
            this.node = (N) Preconditions.checkNotNull(n);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class PredAndSucc {
        private final Object successorValue;

        PredAndSucc(Object obj) {
            this.successorValue = obj;
        }
    }

    private DirectedGraphConnections(Map<N, Object> map, @NullableDecl List<NodeConnection<N>> list, int i, int i2) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(map);
        this.orderedNodeConnections = list;
        this.predecessorCount = Graphs.checkNonNegative(i);
        this.successorCount = Graphs.checkNonNegative(i2);
        Preconditions.checkState(i <= map.size() && i2 <= map.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPredecessor(@NullableDecl Object obj) {
        return obj == PRED || (obj instanceof PredAndSucc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSuccessor(@NullableDecl Object obj) {
        return (obj == PRED || obj == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <N, V> DirectedGraphConnections<N, V> of(ElementOrder<N> elementOrder) {
        ArrayList arrayList;
        int ordinal = elementOrder.type().ordinal();
        if (ordinal == 0) {
            arrayList = null;
        } else if (ordinal == 1) {
            arrayList = new ArrayList();
        } else {
            throw new AssertionError(elementOrder.type());
        }
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), arrayList, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> DirectedGraphConnections<N, V> ofImmutable(N n, Iterable<EndpointPair<N>> iterable, Function<N, V> function) {
        Preconditions.checkNotNull(n);
        Preconditions.checkNotNull(function);
        HashMap hashMap = new HashMap();
        ImmutableList.Builder builder = ImmutableList.builder();
        int i = 0;
        int i2 = 0;
        for (EndpointPair<N> endpointPair : iterable) {
            if (endpointPair.nodeU().equals(n) && endpointPair.nodeV().equals(n)) {
                hashMap.put(n, new PredAndSucc(function.mo8172apply(n)));
                builder.mo7849add((ImmutableList.Builder) new NodeConnection.Pred(n));
                builder.mo7849add((ImmutableList.Builder) new NodeConnection.Succ(n));
                i++;
            } else if (endpointPair.nodeV().equals(n)) {
                N nodeU = endpointPair.nodeU();
                Object put = hashMap.put(nodeU, PRED);
                if (put != null) {
                    hashMap.put(nodeU, new PredAndSucc(put));
                }
                builder.mo7849add((ImmutableList.Builder) new NodeConnection.Pred(nodeU));
                i++;
            } else {
                Preconditions.checkArgument(endpointPair.nodeU().equals(n));
                N nodeV = endpointPair.nodeV();
                V mo8172apply = function.mo8172apply(nodeV);
                Object put2 = hashMap.put(nodeV, mo8172apply);
                if (put2 != null) {
                    Preconditions.checkArgument(put2 == PRED);
                    hashMap.put(nodeV, new PredAndSucc(mo8172apply));
                }
                builder.mo7849add((ImmutableList.Builder) new NodeConnection.Succ(nodeV));
            }
            i2++;
        }
        return new DirectedGraphConnections<>(hashMap, builder.mo7852build(), i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.GraphConnections
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addPredecessor(N r4, V r5) {
        /*
            r3 = this;
            java.util.Map<N, java.lang.Object> r5 = r3.adjacentNodeValues
            java.lang.Object r0 = com.google.common.graph.DirectedGraphConnections.PRED
            java.lang.Object r5 = r5.put(r4, r0)
            r0 = 0
            r1 = 1
            if (r5 != 0) goto Le
        Lc:
            r0 = r1
            goto L27
        Le:
            boolean r2 = r5 instanceof com.google.common.graph.DirectedGraphConnections.PredAndSucc
            if (r2 == 0) goto L18
            java.util.Map<N, java.lang.Object> r2 = r3.adjacentNodeValues
            r2.put(r4, r5)
            goto L27
        L18:
            java.lang.Object r2 = com.google.common.graph.DirectedGraphConnections.PRED
            if (r5 == r2) goto L27
            java.util.Map<N, java.lang.Object> r0 = r3.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r2 = new com.google.common.graph.DirectedGraphConnections$PredAndSucc
            r2.<init>(r5)
            r0.put(r4, r2)
            goto Lc
        L27:
            if (r0 == 0) goto L3d
            int r5 = r3.predecessorCount
            int r5 = r5 + r1
            r3.predecessorCount = r5
            com.google.common.graph.Graphs.checkPositive(r5)
            java.util.List<com.google.common.graph.DirectedGraphConnections$NodeConnection<N>> r5 = r3.orderedNodeConnections
            if (r5 == 0) goto L3d
            com.google.common.graph.DirectedGraphConnections$NodeConnection$Pred r0 = new com.google.common.graph.DirectedGraphConnections$NodeConnection$Pred
            r0.<init>(r4)
            r5.add(r0)
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.addPredecessor(java.lang.Object, java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031  */
    @Override // com.google.common.graph.GraphConnections
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public V addSuccessor(N r4, V r5) {
        /*
            r3 = this;
            java.util.Map<N, java.lang.Object> r0 = r3.adjacentNodeValues
            java.lang.Object r0 = r0.put(r4, r5)
            r1 = 0
            if (r0 != 0) goto Lb
        L9:
            r0 = r1
            goto L2f
        Lb:
            boolean r2 = r0 instanceof com.google.common.graph.DirectedGraphConnections.PredAndSucc
            if (r2 == 0) goto L20
            java.util.Map<N, java.lang.Object> r1 = r3.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r2 = new com.google.common.graph.DirectedGraphConnections$PredAndSucc
            r2.<init>(r5)
            r1.put(r4, r2)
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r0 = (com.google.common.graph.DirectedGraphConnections.PredAndSucc) r0
            java.lang.Object r0 = com.google.common.graph.DirectedGraphConnections.PredAndSucc.access$600(r0)
            goto L2f
        L20:
            java.lang.Object r2 = com.google.common.graph.DirectedGraphConnections.PRED
            if (r0 != r2) goto L2f
            java.util.Map<N, java.lang.Object> r0 = r3.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r2 = new com.google.common.graph.DirectedGraphConnections$PredAndSucc
            r2.<init>(r5)
            r0.put(r4, r2)
            goto L9
        L2f:
            if (r0 != 0) goto L46
            int r5 = r3.successorCount
            int r5 = r5 + 1
            r3.successorCount = r5
            com.google.common.graph.Graphs.checkPositive(r5)
            java.util.List<com.google.common.graph.DirectedGraphConnections$NodeConnection<N>> r5 = r3.orderedNodeConnections
            if (r5 == 0) goto L46
            com.google.common.graph.DirectedGraphConnections$NodeConnection$Succ r1 = new com.google.common.graph.DirectedGraphConnections$NodeConnection$Succ
            r1.<init>(r4)
            r5.add(r1)
        L46:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.addSuccessor(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        if (this.orderedNodeConnections == null) {
            return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
        }
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.adjacentNodeValues.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            /* renamed from: iterator */
            public UnmodifiableIterator<N> mo8141iterator() {
                final Iterator it2 = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                final HashSet hashSet = new HashSet();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.1.1
                    @Override // com.google.common.collect.AbstractIterator
                    /* renamed from: computeNext */
                    protected N mo8230computeNext() {
                        while (it2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it2.next();
                            if (hashSet.add(nodeConnection.node)) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N n) {
        final Iterator transform;
        Preconditions.checkNotNull(n);
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        if (list == null) {
            transform = Iterators.concat(Iterators.transform(predecessors().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.4
                @Override // com.google.common.base.Function
                /* renamed from: apply  reason: collision with other method in class */
                public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                    return mo8172apply((AnonymousClass4) obj);
                }

                @Override // com.google.common.base.Function
                /* renamed from: apply */
                public EndpointPair<N> mo8172apply(N n2) {
                    return EndpointPair.ordered(n2, n);
                }
            }), Iterators.transform(successors().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.5
                @Override // com.google.common.base.Function
                /* renamed from: apply  reason: collision with other method in class */
                public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                    return mo8172apply((AnonymousClass5) obj);
                }

                @Override // com.google.common.base.Function
                /* renamed from: apply */
                public EndpointPair<N> mo8172apply(N n2) {
                    return EndpointPair.ordered(n, n2);
                }
            }));
        } else {
            transform = Iterators.transform(list.iterator(), new Function<NodeConnection<N>, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.6
                @Override // com.google.common.base.Function
                /* renamed from: apply */
                public /* bridge */ /* synthetic */ Object mo8172apply(Object obj) {
                    return apply((NodeConnection) ((NodeConnection) obj));
                }

                public EndpointPair<N> apply(NodeConnection<N> nodeConnection) {
                    if (nodeConnection instanceof NodeConnection.Succ) {
                        return EndpointPair.ordered(n, nodeConnection.node);
                    }
                    return EndpointPair.ordered(nodeConnection.node, n);
                }
            });
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        return new AbstractIterator<EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.7
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
            @Override // com.google.common.collect.AbstractIterator
            /* renamed from: computeNext */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public com.google.common.graph.EndpointPair<N> mo8230computeNext() {
                /*
                    r3 = this;
                L0:
                    java.util.Iterator r0 = r2
                    boolean r0 = r0.hasNext()
                    if (r0 == 0) goto L28
                    java.util.Iterator r0 = r2
                    java.lang.Object r0 = r0.next()
                    com.google.common.graph.EndpointPair r0 = (com.google.common.graph.EndpointPair) r0
                    java.lang.Object r1 = r0.nodeU()
                    java.lang.Object r2 = r0.nodeV()
                    boolean r1 = r1.equals(r2)
                    if (r1 == 0) goto L27
                    java.util.concurrent.atomic.AtomicBoolean r1 = r3
                    r2 = 1
                    boolean r1 = r1.getAndSet(r2)
                    if (r1 != 0) goto L0
                L27:
                    return r0
                L28:
                    java.lang.Object r0 = r3.endOfData()
                    com.google.common.graph.EndpointPair r0 = (com.google.common.graph.EndpointPair) r0
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.AnonymousClass7.mo8230computeNext():com.google.common.graph.EndpointPair");
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            /* renamed from: iterator */
            public UnmodifiableIterator<N> mo8142iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator it2 = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.1
                        @Override // com.google.common.collect.AbstractIterator
                        /* renamed from: computeNext */
                        protected N mo8230computeNext() {
                            while (it2.hasNext()) {
                                Map.Entry entry = (Map.Entry) it2.next();
                                if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                    return (N) entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                final Iterator it3 = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.2
                    @Override // com.google.common.collect.AbstractIterator
                    /* renamed from: computeNext */
                    protected N mo8230computeNext() {
                        while (it3.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it3.next();
                            if (nodeConnection instanceof NodeConnection.Pred) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.GraphConnections
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void removePredecessor(N r4) {
        /*
            r3 = this;
            com.google.common.base.Preconditions.checkNotNull(r4)
            java.util.Map<N, java.lang.Object> r0 = r3.adjacentNodeValues
            java.lang.Object r0 = r0.get(r4)
            java.lang.Object r1 = com.google.common.graph.DirectedGraphConnections.PRED
            r2 = 1
            if (r0 != r1) goto L15
            java.util.Map<N, java.lang.Object> r0 = r3.adjacentNodeValues
            r0.remove(r4)
        L13:
            r0 = r2
            goto L26
        L15:
            boolean r1 = r0 instanceof com.google.common.graph.DirectedGraphConnections.PredAndSucc
            if (r1 == 0) goto L25
            java.util.Map<N, java.lang.Object> r1 = r3.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r0 = (com.google.common.graph.DirectedGraphConnections.PredAndSucc) r0
            java.lang.Object r0 = com.google.common.graph.DirectedGraphConnections.PredAndSucc.access$600(r0)
            r1.put(r4, r0)
            goto L13
        L25:
            r0 = 0
        L26:
            if (r0 == 0) goto L3c
            int r0 = r3.predecessorCount
            int r0 = r0 - r2
            r3.predecessorCount = r0
            com.google.common.graph.Graphs.checkNonNegative(r0)
            java.util.List<com.google.common.graph.DirectedGraphConnections$NodeConnection<N>> r0 = r3.orderedNodeConnections
            if (r0 == 0) goto L3c
            com.google.common.graph.DirectedGraphConnections$NodeConnection$Pred r1 = new com.google.common.graph.DirectedGraphConnections$NodeConnection$Pred
            r1.<init>(r4)
            r0.remove(r1)
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.removePredecessor(java.lang.Object):void");
    }

    @Override // com.google.common.graph.GraphConnections
    public V removeSuccessor(Object obj) {
        Object obj2;
        Preconditions.checkNotNull(obj);
        V v = (V) this.adjacentNodeValues.get(obj);
        if (v == null || v == (obj2 = PRED)) {
            v = null;
        } else if (v instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, obj2);
            v = (V) ((PredAndSucc) v).successorValue;
        } else {
            this.adjacentNodeValues.remove(obj);
        }
        if (v != null) {
            int i = this.successorCount - 1;
            this.successorCount = i;
            Graphs.checkNonNegative(i);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Succ(obj));
            }
        }
        return v;
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.3
            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            /* renamed from: iterator */
            public UnmodifiableIterator<N> mo8143iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator it2 = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.1
                        @Override // com.google.common.collect.AbstractIterator
                        /* renamed from: computeNext */
                        protected N mo8230computeNext() {
                            while (it2.hasNext()) {
                                Map.Entry entry = (Map.Entry) it2.next();
                                if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                    return (N) entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                final Iterator it3 = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.2
                    @Override // com.google.common.collect.AbstractIterator
                    /* renamed from: computeNext */
                    protected N mo8230computeNext() {
                        while (it3.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it3.next();
                            if (nodeConnection instanceof NodeConnection.Succ) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public V value(N n) {
        Preconditions.checkNotNull(n);
        V v = (V) this.adjacentNodeValues.get(n);
        if (v == PRED) {
            return null;
        }
        return v instanceof PredAndSucc ? (V) ((PredAndSucc) v).successorValue : v;
    }
}
