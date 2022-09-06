package com.google.common.graph;

import java.util.Set;
/* loaded from: classes3.dex */
abstract class ForwardingGraph<N> extends AbstractGraph<N> {
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> adjacentNodes(N n) {
        return mo8157delegate().adjacentNodes(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean allowsSelfLoops() {
        return mo8157delegate().allowsSelfLoops();
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public int degree(N n) {
        return mo8157delegate().degree(n);
    }

    /* renamed from: delegate */
    protected abstract BaseGraph<N> mo8157delegate();

    @Override // com.google.common.graph.AbstractBaseGraph
    protected long edgeCount() {
        return mo8157delegate().edges().size();
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(N n, N n2) {
        return mo8157delegate().hasEdgeConnecting(n, n2);
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public int inDegree(N n) {
        return mo8157delegate().inDegree(n);
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return mo8157delegate().incidentEdgeOrder();
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public Set<EndpointPair<N>> incidentEdges(N n) {
        return mo8157delegate().incidentEdges(n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public boolean isDirected() {
        return mo8157delegate().isDirected();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public ElementOrder<N> nodeOrder() {
        return mo8157delegate().nodeOrder();
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    public Set<N> nodes() {
        return mo8157delegate().nodes();
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public int outDegree(N n) {
        return mo8157delegate().outDegree(n);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    /* renamed from: predecessors */
    public /* bridge */ /* synthetic */ Iterable mo8170predecessors(Object obj) {
        return mo8170predecessors((ForwardingGraph<N>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    /* renamed from: successors */
    public /* bridge */ /* synthetic */ Iterable mo8171successors(Object obj) {
        return mo8171successors((ForwardingGraph<N>) obj);
    }

    @Override // com.google.common.graph.AbstractGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public boolean hasEdgeConnecting(EndpointPair<N> endpointPair) {
        return mo8157delegate().hasEdgeConnecting(endpointPair);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    /* renamed from: predecessors  reason: collision with other method in class */
    public Set<N> mo8170predecessors(N n) {
        return mo8157delegate().mo8170predecessors((BaseGraph<N>) n);
    }

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    /* renamed from: successors  reason: collision with other method in class */
    public Set<N> mo8171successors(N n) {
        return mo8157delegate().mo8171successors((BaseGraph<N>) n);
    }
}
