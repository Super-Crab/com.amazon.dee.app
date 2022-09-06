package com.amazon.whisperjoin.common.sharedtypes.reporting;

import com.amazon.devicesetupservice.reporting.State;
import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.WifiNetworkInfo;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
/* loaded from: classes13.dex */
public class ProvisionableEventReporterImpl extends AbstractProvisioningEventReporter implements ProvisionableEventReporter {
    private final ProvisionableInfo mProvisionableInfo;
    private final ReportingSesssion mReportingSesssion;

    public ProvisionableEventReporterImpl(DSSClient dSSClient, String str, ProvisionableInfo provisionableInfo) {
        super(dSSClient);
        if (str != null) {
            if (provisionableInfo != null) {
                this.mReportingSesssion = new ReportingSesssion(str);
                this.mProvisionableInfo = provisionableInfo;
                return;
            }
            throw new IllegalArgumentException("provisionableInfo can not be null");
        }
        throw new IllegalArgumentException("reportingUrl can not be null");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionableEventReporter
    public void reportDoneSuccess() {
        reportEvent(getBuilderForSession(this.mReportingSesssion).setState("DONE").setEvent("SUCCESS").setProvisionableInfo(this.mProvisionableInfo).createReportEventRequest());
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionableEventReporter
    public void reportNetworkedSuccess(WifiNetworkInfo wifiNetworkInfo) {
        reportEvent(getBuilderForSession(this.mReportingSesssion).setState(State.NETWORKED).setEvent("SUCCESS").setProvisionableInfo(this.mProvisionableInfo).setWifiNetworkInfo(wifiNetworkInfo).createReportEventRequest());
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.reporting.ProvisionableEventReporter
    public void reportRegisteredSuccess(String str) {
        reportEvent(getBuilderForSession(this.mReportingSesssion).setState("REGISTERED").setEvent("SUCCESS").setProvisionableInfo(this.mProvisionableInfo).setRegistrationState(str).createReportEventRequest());
    }
}
