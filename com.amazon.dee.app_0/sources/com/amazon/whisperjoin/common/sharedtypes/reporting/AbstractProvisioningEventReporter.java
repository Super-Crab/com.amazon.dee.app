package com.amazon.whisperjoin.common.sharedtypes.reporting;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ReportEventRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ReportEventRequestBuilder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
/* loaded from: classes13.dex */
public abstract class AbstractProvisioningEventReporter {
    private static final String TAG = "AbstractProvisioningEventReporter";
    private final DSSClient mDSSClient;

    public AbstractProvisioningEventReporter(DSSClient dSSClient) {
        if (dSSClient != null) {
            this.mDSSClient = dSSClient;
            return;
        }
        throw new IllegalArgumentException("DSSClient can not be null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReportEventRequestBuilder getBuilderForSession(ReportingSesssion reportingSesssion) {
        return new ReportEventRequestBuilder().setReportingUrl(reportingSesssion.getUrl()).setSequenceNumber(reportingSesssion.getNextSequenceNumber());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reportEvent(final ReportEventRequest reportEventRequest) {
        this.mDSSClient.reportEvent(reportEventRequest).subscribeWith(new DisposableCompletableObserver() { // from class: com.amazon.whisperjoin.common.sharedtypes.reporting.AbstractProvisioningEventReporter.1
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(@NonNull Throwable th) {
                String str = AbstractProvisioningEventReporter.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("An error occurred while reporting event: ");
                outline107.append(reportEventRequest.getState().toString());
                WJLog.e(str, outline107.toString(), th);
            }
        });
    }
}
