package com.amazon.clouddrive.extended.model.filters;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class NodeKindFilter implements NodeFilterBuilder {
    private String mNodeKind;

    private NodeKindFilter() {
    }

    public static NodeKindFilter byAsset() {
        NodeKindFilter nodeKindFilter = new NodeKindFilter();
        nodeKindFilter.mNodeKind = "ASSET";
        return nodeKindFilter;
    }

    public static NodeKindFilter byFile() {
        NodeKindFilter nodeKindFilter = new NodeKindFilter();
        nodeKindFilter.mNodeKind = "FILE";
        return nodeKindFilter;
    }

    public static NodeKindFilter byFolder() {
        NodeKindFilter nodeKindFilter = new NodeKindFilter();
        nodeKindFilter.mNodeKind = "FOLDER";
        return nodeKindFilter;
    }

    public static NodeKindFilter byGroup() {
        NodeKindFilter nodeKindFilter = new NodeKindFilter();
        nodeKindFilter.mNodeKind = "GROUP";
        return nodeKindFilter;
    }

    @Override // com.amazon.clouddrive.extended.model.filters.NodeFilterBuilder
    public String buildFilterQuery() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("kind:");
        outline107.append(this.mNodeKind);
        return outline107.toString();
    }
}
