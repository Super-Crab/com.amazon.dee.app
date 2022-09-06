package com.amazon.clouddrive.cdasdk.cds;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCalls;
import com.amazon.clouddrive.cdasdk.cds.account.CDSAccountCallsImpl;
import com.amazon.clouddrive.cdasdk.cds.bulk.CDSBulkCalls;
import com.amazon.clouddrive.cdasdk.cds.bulk.CDSBulkCallsImpl;
import com.amazon.clouddrive.cdasdk.cds.child.CDSChildCalls;
import com.amazon.clouddrive.cdasdk.cds.child.CDSChildCallsImpl;
import com.amazon.clouddrive.cdasdk.cds.histogram.CDSHistogramCalls;
import com.amazon.clouddrive.cdasdk.cds.histogram.CDSHistogramCallsImpl;
import com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCalls;
import com.amazon.clouddrive.cdasdk.cds.node.CDSNodeCallsImpl;
import com.amazon.clouddrive.cdasdk.cds.search.CDSSearchCalls;
import com.amazon.clouddrive.cdasdk.cds.search.CDSSearchCallsImpl;
import com.amazon.clouddrive.cdasdk.cds.source.CDSSourceCalls;
import com.amazon.clouddrive.cdasdk.cds.source.CDSSourceCallsImpl;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class CDSCallsImpl implements CDSCalls {
    @NonNull
    private final CDSAccountCalls accountCalls;
    @NonNull
    private final CDSBulkCalls bulkCalls;
    @NonNull
    private final CDSChildCalls childCalls;
    @NonNull
    private final CDSHistogramCalls histogramCalls;
    @NonNull
    private final CDSNodeCalls nodeCalls;
    @NonNull
    private final CDSSearchCalls searchCalls;
    @NonNull
    private final CDSSourceCalls sourceCalls;

    public CDSCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        CDSCallUtil cDSCallUtil = new CDSCallUtil(clientConfig);
        CDSCallUtil cDSCallUtil2 = new CDSCallUtil(clientConfig);
        this.accountCalls = new CDSAccountCallsImpl(cDSCallUtil, retrofit);
        this.searchCalls = new CDSSearchCallsImpl(cDSCallUtil, retrofit);
        this.sourceCalls = new CDSSourceCallsImpl(cDSCallUtil, retrofit);
        this.nodeCalls = new CDSNodeCallsImpl(cDSCallUtil, cDSCallUtil2, retrofit);
        this.childCalls = new CDSChildCallsImpl(cDSCallUtil, retrofit);
        this.histogramCalls = new CDSHistogramCallsImpl(cDSCallUtil, retrofit);
        this.bulkCalls = new CDSBulkCallsImpl(cDSCallUtil, retrofit);
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.CDSCalls
    @NonNull
    public CDSAccountCalls getAccountCalls() {
        return this.accountCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.CDSCalls
    @NonNull
    public CDSBulkCalls getBulkCalls() {
        return this.bulkCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.CDSCalls
    @NonNull
    public CDSChildCalls getChildCalls() {
        return this.childCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.CDSCalls
    @NonNull
    public CDSHistogramCalls getHistogramCalls() {
        return this.histogramCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.CDSCalls
    @NonNull
    public CDSNodeCalls getNodeCalls() {
        return this.nodeCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.CDSCalls
    @NonNull
    public CDSSearchCalls getSearchCalls() {
        return this.searchCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.CDSCalls
    @NonNull
    public CDSSourceCalls getSourceCalls() {
        return this.sourceCalls;
    }
}
