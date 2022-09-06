package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting;

import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetup.common.v1.Event;
import com.amazon.devicesetupservice.reporting.Radio;
import com.amazon.devicesetupservice.reporting.State;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.reporting.ReportingDataTypesBuilder;
import com.amazon.whisperjoin.common.sharedtypes.reporting.ReportingSesssion;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.ZeroTouchWorkflowUpdate;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ReportSmartHomeEventRequest;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
/* loaded from: classes13.dex */
public class SmartHomeProvisioningEventReporter {
    public static final String PHILIPS_PRODUCT_INDEX = "wHXD";
    private static final String TAG = "SmartHomeProvisioningEventReporter";
    private String mBleMacAddress;
    private final DSSClient mDSSClient;
    private final ProvisionerInfo mProvisionerInfo;
    private ReportingSesssion mReportingSession;
    private final WJErrorMapper<Throwable> mWJErrorMapper;

    public SmartHomeProvisioningEventReporter(ProvisionerInfo provisionerInfo, WJErrorMapper<Throwable> wJErrorMapper, DSSClient dSSClient) {
        this.mProvisionerInfo = provisionerInfo;
        this.mWJErrorMapper = wJErrorMapper;
        this.mDSSClient = dSSClient;
    }

    private ErrorInfo createErrorInfo(ZeroTouchWorkflowUpdate.State state, Throwable th) {
        return ReportingDataTypesBuilder.buildErrorInfo(state.name(), th, this.mWJErrorMapper.map(th).getErrorCode());
    }

    private ReportSmartHomeEventRequest createReportSmartHomeEventRequest(String str, String str2) {
        return createReportSmartHomeEventRequest(str, str2, null);
    }

    private void reportEvent(ReportSmartHomeEventRequest reportSmartHomeEventRequest) {
        this.mDSSClient.reportSmartHomeEvent(reportSmartHomeEventRequest).subscribeWith(new DisposableCompletableObserver() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.SmartHomeProvisioningEventReporter.1
            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                WJLog.d(SmartHomeProvisioningEventReporter.TAG, "Event reported successfully");
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                WJLog.e(SmartHomeProvisioningEventReporter.TAG, "Reporting Event Failed", th);
            }
        });
    }

    private void verifyReportingSessionRegistered() {
        if (this.mReportingSession != null) {
            return;
        }
        throw new IllegalStateException("No SessionId registered");
    }

    public void registerDeviceForEventReporting(String str, String str2) {
        String str3 = TAG;
        WJLog.d(str3, "registerDeviceForEventReporting: " + str);
        this.mReportingSession = new ReportingSesssion(str);
        this.mBleMacAddress = str2;
    }

    public void reportConnectionFailure(Throwable th) {
        WJLog.d(TAG, "reportConnectionFailure");
        verifyReportingSessionRegistered();
        reportEvent(createReportSmartHomeEventRequest("CONNECTED", Event.FAILURE, createErrorInfo(ZeroTouchWorkflowUpdate.State.CONNECTING, th)));
    }

    public void reportConnectionSuccess() {
        WJLog.d(TAG, "reportConnectionSuccess");
        verifyReportingSessionRegistered();
        reportEvent(createReportSmartHomeEventRequest("CONNECTED", "SUCCESS"));
    }

    public void reportDoneSuccess() {
        WJLog.d(TAG, "reportDoneSuccess");
        verifyReportingSessionRegistered();
        reportEvent(createReportSmartHomeEventRequest("DONE", "SUCCESS"));
    }

    public void reportProvisionedFailure(Throwable th) {
        WJLog.d(TAG, "reportProvisionedFailure");
        verifyReportingSessionRegistered();
        reportEvent(createReportSmartHomeEventRequest(State.PROVISIONED, Event.FAILURE, createErrorInfo(ZeroTouchWorkflowUpdate.State.PROVISIONING, th)));
    }

    public void reportProvisionedSuccess() {
        WJLog.d(TAG, "reportProvisionedSuccess");
        verifyReportingSessionRegistered();
        reportEvent(createReportSmartHomeEventRequest(State.PROVISIONED, "SUCCESS"));
    }

    public void reportRetrievedProvisioningDetailsFailure(Throwable th) {
        WJLog.d(TAG, "reportRetrievedProvisioningDetailsFailure");
        verifyReportingSessionRegistered();
        reportEvent(createReportSmartHomeEventRequest(State.RETRIEVED_PROVISIONING_DETAILS, Event.FAILURE, createErrorInfo(ZeroTouchWorkflowUpdate.State.PROVISIONING, th)));
    }

    public void reportRetrievedProvisioningDetailsSuccess(String str) {
        WJLog.d(TAG, "reportRetrievedProvisioningDetailsSuccess");
        verifyReportingSessionRegistered();
        reportEvent(new ReportSmartHomeEventRequest.Builder().setSequenceNumber(this.mReportingSession.getNextSequenceNumber()).setReportEventIdentifier(this.mReportingSession.getUrl()).setProvisionerInfo(this.mProvisionerInfo).setEvent("SUCCESS").setState(State.RETRIEVED_PROVISIONING_DETAILS).setBleMac(this.mBleMacAddress).setZigbeeMac(str).setProductIndex(PHILIPS_PRODUCT_INDEX).setRadio(Radio.BLUETOOTH_LOW_ENERGY).createReportSmartHomeEventRequest());
    }

    private ReportSmartHomeEventRequest createReportSmartHomeEventRequest(String str, String str2, ErrorInfo errorInfo) {
        return new ReportSmartHomeEventRequest.Builder().setSequenceNumber(this.mReportingSession.getNextSequenceNumber()).setReportEventIdentifier(this.mReportingSession.getUrl()).setProvisionerInfo(this.mProvisionerInfo).setEvent(str2).setState(str).setErrorInfo(errorInfo).setBleMac(this.mBleMacAddress).setRadio(Radio.BLUETOOTH_LOW_ENERGY).setProductIndex(PHILIPS_PRODUCT_INDEX).createReportSmartHomeEventRequest();
    }
}
