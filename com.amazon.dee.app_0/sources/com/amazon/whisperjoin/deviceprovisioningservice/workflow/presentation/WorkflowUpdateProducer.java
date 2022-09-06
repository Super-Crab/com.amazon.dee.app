package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevices;
import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisionableWifiNetworkConnectionError;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.controller.WorkflowControllerBuilder;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.DiscoveredDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.IdleViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.InProgressViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ProvisioningDetailsViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupCompleteViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupFailureViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.WifiConnectionErrorViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceActionCreator;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.DiscoveredProvisionable;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorUtils;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.DeviceSession;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WJWorkflowStateStore;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.WorkflowStep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.exception.ExceptionUtils;
/* loaded from: classes13.dex */
public class WorkflowUpdateProducer {
    private final DeviceActionCreator mActionController;
    private final WJErrorMapper<Throwable> mWJErrorMapper;

    public WorkflowUpdateProducer(DeviceActionCreator deviceActionCreator, WJErrorMapper<Throwable> wJErrorMapper) {
        if (deviceActionCreator != null) {
            if (wJErrorMapper != null) {
                this.mActionController = deviceActionCreator;
                this.mWJErrorMapper = wJErrorMapper;
                return;
            }
            throw new IllegalArgumentException("wjErrorMapper can not be null");
        }
        throw new IllegalArgumentException("DeviceActionCreator can't be null");
    }

    private WorkflowControllerBuilder getWorkflowControllerBuilder(WJWorkflowStateStore wJWorkflowStateStore) {
        return new WorkflowControllerBuilder(this.mActionController, wJWorkflowStateStore);
    }

    public WorkflowPresentationUpdate awaitingDeviceSelection(WJWorkflowStateStore wJWorkflowStateStore, TrustProvider.TrustState trustState) {
        List<DiscoveredProvisionable> discoveredDevices = wJWorkflowStateStore.getDiscoveredDevices();
        HashMap hashMap = new HashMap(discoveredDevices.size());
        ArrayList arrayList = new ArrayList(discoveredDevices.size());
        for (DiscoveredProvisionable discoveredProvisionable : discoveredDevices) {
            DiscoveredDevice discoveredDevice = new DiscoveredDevice(discoveredProvisionable.getWJProvisionee());
            hashMap.put(discoveredDevice, discoveredProvisionable.getWJProvisionee());
            arrayList.add(discoveredDevice);
        }
        return new WorkflowPresentationUpdate(new DiscoveredDevicesViewModel(new DiscoveredDevices(arrayList)), getWorkflowControllerBuilder(wJWorkflowStateStore).connectToDiscoveredDevice(hashMap, trustState).build());
    }

    public WorkflowPresentationUpdate awaitingProvisioningData(WJWorkflowStateStore wJWorkflowStateStore, WJProvisionee wJProvisionee) {
        return new WorkflowPresentationUpdate(new ProvisioningDetailsViewModel(wJWorkflowStateStore.getSession(wJProvisionee).getProvisioningDetails()), getWorkflowControllerBuilder(wJWorkflowStateStore).sendProvisioningData(wJProvisionee).refreshAvailableNetworks(wJProvisionee).build());
    }

    public WorkflowPresentationUpdate fixupWifiConnectionError(WJWorkflowStateStore wJWorkflowStateStore, WJProvisionee wJProvisionee) {
        DeviceSession session = wJWorkflowStateStore.getSession(wJProvisionee);
        ProvisionableWifiNetworkConnectionError lastWifiConnectionError = session.getLastWifiConnectionError();
        return new WorkflowPresentationUpdate(new WifiConnectionErrorViewModel(lastWifiConnectionError, session.getProvisioningDetails(), this.mWJErrorMapper.map(lastWifiConnectionError)), getWorkflowControllerBuilder(wJWorkflowStateStore).refreshAvailableNetworks(wJProvisionee).sendProvisioningData(wJProvisionee).build());
    }

    public WorkflowPresentationUpdate idle(WJWorkflowStateStore wJWorkflowStateStore) {
        return new WorkflowPresentationUpdate(new IdleViewModel(), new WorkflowControllerBuilder(this.mActionController, wJWorkflowStateStore).discoverDevices().build());
    }

    public WorkflowPresentationUpdate inProgress(WJWorkflowStateStore wJWorkflowStateStore, WorkflowStep workflowStep) {
        return new WorkflowPresentationUpdate(new InProgressViewModel(workflowStep), getWorkflowControllerBuilder(wJWorkflowStateStore).build());
    }

    public WorkflowPresentationUpdate setupFailure(WJWorkflowStateStore wJWorkflowStateStore, Throwable th, WorkflowStep workflowStep) {
        Throwable rootCause = WJErrorUtils.getRootCause(th);
        return new WorkflowPresentationUpdate(new SetupFailureViewModel(workflowStep, rootCause.getClass().getSimpleName(), ExceptionUtils.getFullStackTrace(rootCause), this.mWJErrorMapper.map(rootCause)), getWorkflowControllerBuilder(wJWorkflowStateStore).build());
    }

    public WorkflowPresentationUpdate setupSuccess(WJWorkflowStateStore wJWorkflowStateStore) {
        return new WorkflowPresentationUpdate(new SetupCompleteViewModel(), getWorkflowControllerBuilder(wJWorkflowStateStore).build());
    }
}
