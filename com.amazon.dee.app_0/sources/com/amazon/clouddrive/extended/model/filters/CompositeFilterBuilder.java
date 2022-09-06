package com.amazon.clouddrive.extended.model.filters;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class CompositeFilterBuilder implements NodeFilterBuilder {
    private static final String AND = " AND ";
    private List<NodeFilterBuilder> mNodeFilterBuilders = new ArrayList();

    private CompositeFilterBuilder() {
    }

    public static CompositeFilterBuilder with(NodeFilterBuilder nodeFilterBuilder) {
        CompositeFilterBuilder compositeFilterBuilder = new CompositeFilterBuilder();
        compositeFilterBuilder.mNodeFilterBuilders.add(nodeFilterBuilder);
        return compositeFilterBuilder;
    }

    public CompositeFilterBuilder and(NodeFilterBuilder nodeFilterBuilder) {
        this.mNodeFilterBuilders.add(nodeFilterBuilder);
        return this;
    }

    @Override // com.amazon.clouddrive.extended.model.filters.NodeFilterBuilder
    public String buildFilterQuery() {
        boolean z = false;
        if (this.mNodeFilterBuilders.size() == 1) {
            return this.mNodeFilterBuilders.get(0).buildFilterQuery();
        }
        StringBuilder sb = new StringBuilder();
        for (NodeFilterBuilder nodeFilterBuilder : this.mNodeFilterBuilders) {
            if (z) {
                sb.append(AND);
            } else {
                z = true;
            }
            sb.append(nodeFilterBuilder.buildFilterQuery());
        }
        return sb.toString();
    }
}
