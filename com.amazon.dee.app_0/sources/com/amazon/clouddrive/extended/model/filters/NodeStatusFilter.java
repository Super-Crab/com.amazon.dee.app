package com.amazon.clouddrive.extended.model.filters;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class NodeStatusFilter implements NodeFilterBuilder {
    private String mObjectStatus;

    private NodeStatusFilter() {
    }

    public static NodeStatusFilter byAvailable() {
        NodeStatusFilter nodeStatusFilter = new NodeStatusFilter();
        nodeStatusFilter.mObjectStatus = "AVAILABLE";
        return nodeStatusFilter;
    }

    public static NodeStatusFilter byPending() {
        NodeStatusFilter nodeStatusFilter = new NodeStatusFilter();
        nodeStatusFilter.mObjectStatus = "PENDING";
        return nodeStatusFilter;
    }

    public static NodeStatusFilter byPurged() {
        NodeStatusFilter nodeStatusFilter = new NodeStatusFilter();
        nodeStatusFilter.mObjectStatus = "PURGED";
        return nodeStatusFilter;
    }

    public static NodeStatusFilter byTrash() {
        NodeStatusFilter nodeStatusFilter = new NodeStatusFilter();
        nodeStatusFilter.mObjectStatus = "TRASH";
        return nodeStatusFilter;
    }

    @Override // com.amazon.clouddrive.extended.model.filters.NodeFilterBuilder
    public String buildFilterQuery() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("status:");
        outline107.append(this.mObjectStatus);
        return outline107.toString();
    }
}
