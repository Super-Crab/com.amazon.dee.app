package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
@DoNotMock("Implement with a lambda, or use GraphBuilder to build a Graph with the desired edges")
@Beta
/* loaded from: classes3.dex */
public interface PredecessorsFunction<N> {
    /* renamed from: predecessors */
    Iterable<? extends N> mo8170predecessors(N n);
}
