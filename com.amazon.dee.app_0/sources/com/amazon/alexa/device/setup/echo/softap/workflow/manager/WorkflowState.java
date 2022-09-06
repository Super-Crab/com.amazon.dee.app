package com.amazon.alexa.device.setup.echo.softap.workflow.manager;

import com.amazon.device.setup.thrift.APConnectExtendedInfo;
import com.amazon.device.setup.thrift.APConnectInfo;
/* loaded from: classes6.dex */
public enum WorkflowState implements WorkflowActions {
    NOT_CONNECTED { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowState.1
        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void autoConnectToEcho() throws Exception {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void cancelSetup() {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToEcho() {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToWifiNetwork(APConnectInfo aPConnectInfo) throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void evaluateCaptivePortal() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void evaluateHotSpotDeviceNetworkDisconnect() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void getWifiNetworks() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToWifiNetwork(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }
    },
    CONNECTED { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowState.2
        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void autoConnectToEcho() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void cancelSetup() {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToEcho() {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToWifiNetwork(APConnectInfo aPConnectInfo) {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToWifiNetwork(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void evaluateCaptivePortal() {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void evaluateHotSpotDeviceNetworkDisconnect() throws Exception {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void getWifiNetworks() {
        }
    },
    EXECUTING { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowState.3
        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void autoConnectToEcho() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void cancelSetup() {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToEcho() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToWifiNetwork(APConnectInfo aPConnectInfo) throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void evaluateCaptivePortal() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void evaluateHotSpotDeviceNetworkDisconnect() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void getWifiNetworks() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToWifiNetwork(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }
    },
    PROVISIONING_COMPLETED { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowState.4
        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void autoConnectToEcho() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void cancelSetup() {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToEcho() throws Exception {
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToWifiNetwork(APConnectInfo aPConnectInfo) throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void evaluateCaptivePortal() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void evaluateHotSpotDeviceNetworkDisconnect() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void getWifiNetworks() throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }

        @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
        public void connectToWifiNetwork(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) throws Exception {
            throw new Exception("ACTION NOT SUPPORTED IN THIS STATE");
        }
    }
}
