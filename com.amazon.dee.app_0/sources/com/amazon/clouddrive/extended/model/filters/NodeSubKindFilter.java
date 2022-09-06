package com.amazon.clouddrive.extended.model.filters;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class NodeSubKindFilter implements NodeFilterBuilder {
    private String mNodeSubKind;

    private NodeSubKindFilter() {
    }

    public static NodeSubKindFilter bySonyBurstPhoto() {
        NodeSubKindFilter nodeSubKindFilter = new NodeSubKindFilter();
        nodeSubKindFilter.mNodeSubKind = "SONY_BURST_PHOTO";
        return nodeSubKindFilter;
    }

    @Override // com.amazon.clouddrive.extended.model.filters.NodeFilterBuilder
    public String buildFilterQuery() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("subKinds:");
        outline107.append(this.mNodeSubKind);
        return outline107.toString();
    }
}
