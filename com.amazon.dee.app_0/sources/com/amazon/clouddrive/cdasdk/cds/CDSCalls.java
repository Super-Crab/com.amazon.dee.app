package com.amazon.clouddrive.cdasdk.cds;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls;
import com.amazon.clouddrive.cdasdk.cds.bulk.CDSBulkCalls;
import com.amazon.clouddrive.cdasdk.cds.child.CDSChildCalls;
import com.amazon.clouddrive.cdasdk.cds.histogram.CDSHistogramCalls;
import com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCalls;
import com.amazon.clouddrive.cdasdk.cds.search.CDSSearchCalls;
import com.amazon.clouddrive.cdasdk.cds.source.CDSSourceCalls;
/* loaded from: classes11.dex */
public interface CDSCalls {
    @NonNull
    CDSAccountCalls getAccountCalls();

    @NonNull
    CDSBulkCalls getBulkCalls();

    @NonNull
    CDSChildCalls getChildCalls();

    @NonNull
    CDSHistogramCalls getHistogramCalls();

    @NonNull
    CDSNodeCalls getNodeCalls();

    @NonNull
    CDSSearchCalls getSearchCalls();

    @NonNull
    CDSSourceCalls getSourceCalls();
}
