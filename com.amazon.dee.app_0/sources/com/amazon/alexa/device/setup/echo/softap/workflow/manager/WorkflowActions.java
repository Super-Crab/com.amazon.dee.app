package com.amazon.alexa.device.setup.echo.softap.workflow.manager;

import com.amazon.device.setup.thrift.APConnectExtendedInfo;
import com.amazon.device.setup.thrift.APConnectInfo;
/* loaded from: classes6.dex */
public interface WorkflowActions {
    void autoConnectToEcho() throws Exception;

    void cancelSetup();

    void connectToEcho() throws Exception;

    void connectToWifiNetwork(APConnectInfo aPConnectInfo) throws Exception;

    void connectToWifiNetwork(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) throws Exception;

    void evaluateCaptivePortal() throws Exception;

    void evaluateHotSpotDeviceNetworkDisconnect() throws Exception;

    void getWifiNetworks() throws Exception;
}
