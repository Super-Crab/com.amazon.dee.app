package com.amazon.alexa.device.setup.echo.softap.thrift;

import com.amazon.alexa.device.setup.echo.softap.linkcode.PreAuthorizedLinkCode;
import com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManager;
import com.amazon.alexa.device.setup.echo.softap.workflow.data.WorkflowStateData;
import com.amazon.device.setup.thrift.APConnectExtendedInfo;
import com.amazon.device.setup.thrift.APConnectInfo;
import com.amazon.device.setup.thrift.APDetail;
import com.amazon.device.setup.thrift.DeviceDetails;
import com.amazon.device.setup.thrift.LocaleAndEndpointInfo;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
/* loaded from: classes6.dex */
public interface ThriftClient {
    Observable<WorkflowStateData> completeSetup();

    Observable<WorkflowStateData> connectToAP(APConnectInfo aPConnectInfo);

    Observable<WorkflowStateData> connectToAP(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo, DeviceDetails deviceDetails);

    Observable<WorkflowStateData> connectToAP(APConnectInfo aPConnectInfo, DeviceDetails deviceDetails, PreAuthorizedLinkCode preAuthorizedLinkCode);

    Observable<WorkflowStateData> evaluateCaptivePortal();

    Observable<WorkflowStateData> getDeviceDetails();

    Observable<List<APDetail>> getKnownList();

    Observable<WorkflowStateData> getLinkCode();

    Observable<WorkflowStateData> getOtaDetails();

    Observable<List<APDetail>> getScanList();

    Observable<WorkflowStateData> ping(SoftAPWifiManager softAPWifiManager);

    Observable<WorkflowStateData> setLocaleAndEndpointInfo(LocaleAndEndpointInfo localeAndEndpointInfo);

    Observable<WorkflowStateData> verifyAlexaConnectionState();

    Observable<WorkflowStateData> verifyDeviceKnowsItIsRegistered();
}
