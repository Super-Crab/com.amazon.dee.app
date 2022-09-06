package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use GraphBuilder to create a real instance")
@Beta
/* loaded from: classes3.dex */
public interface Graph<N> extends BaseGraph<N> {
    @Override // 
    Set<N> adjacentNodes(N n);

    @Override // 
    boolean allowsSelfLoops();

    @Override // com.google.common.graph.BaseGraph
    int degree(N n);

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> edges();

    boolean equals(@NullableDecl Object obj);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(N n, N n2);

    int hashCode();

    @Override // com.google.common.graph.BaseGraph
    int inDegree(N n);

    @Override // com.google.common.graph.BaseGraph
    ElementOrder<N> incidentEdgeOrder();

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> incidentEdges(N n);

    @Override // 
    boolean isDirected();

    @Override // 
    ElementOrder<N> nodeOrder();

    @Override // 
    Set<N> nodes();

    @Override // com.google.common.graph.BaseGraph
    int outDegree(N n);

    @Override // 
    /* renamed from: predecessors */
    Set<N> mo8170predecessors(N n);

    @Override // 
    /* renamed from: successors */
    Set<N> mo8171successors(N n);
}
