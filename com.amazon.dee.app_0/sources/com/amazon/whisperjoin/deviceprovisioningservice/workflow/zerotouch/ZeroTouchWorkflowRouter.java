package com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch;

import com.amazon.whisperjoin.common.sharedtypes.devices.AbstractPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.ThirdPartyPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.state.ZeroTouchWorkflowUpdate;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes13.dex */
public class ZeroTouchWorkflowRouter implements ObservableTransformer<AbstractPeripheralDeviceDetails, ZeroTouchWorkflowUpdate> {
    private static final String TAG = "ZeroTouchWorkflowRouter";
    private final FFSArcusSettings mFFSArcusSettings;
    private final WJDeviceSetupModeSupportedPredicate mWJDeviceSetupModeSupportedPredicate;
    private final ZeroTouchWorkflowFactory mZeroTouchWorkflowFactory;

    public ZeroTouchWorkflowRouter(ZeroTouchWorkflowFactory zeroTouchWorkflowFactory, FFSArcusSettings fFSArcusSettings, WJDeviceSetupModeSupportedPredicate wJDeviceSetupModeSupportedPredicate) {
        this.mZeroTouchWorkflowFactory = zeroTouchWorkflowFactory;
        this.mFFSArcusSettings = fFSArcusSettings;
        this.mWJDeviceSetupModeSupportedPredicate = wJDeviceSetupModeSupportedPredicate;
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<ZeroTouchWorkflowUpdate> apply(Observable<AbstractPeripheralDeviceDetails> observable) {
        return observable.concatMap(new Function<AbstractPeripheralDeviceDetails, ObservableSource<? extends ZeroTouchWorkflowUpdate>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowRouter.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<? extends ZeroTouchWorkflowUpdate> mo10358apply(AbstractPeripheralDeviceDetails abstractPeripheralDeviceDetails) throws Exception {
                WJLog.i(ZeroTouchWorkflowRouter.TAG, "Device discovered, routing to workflow.");
                if (abstractPeripheralDeviceDetails instanceof WhisperJoinPeripheralDeviceDetails) {
                    WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails = (WhisperJoinPeripheralDeviceDetails) abstractPeripheralDeviceDetails;
                    if (!ZeroTouchWorkflowRouter.this.mWJDeviceSetupModeSupportedPredicate.test(whisperJoinPeripheralDeviceDetails)) {
                        WJLog.i(ZeroTouchWorkflowRouter.TAG, "Setup mode not supported for discovered device");
                        return Observable.empty();
                    }
                    if (!ZeroTouchWorkflowRouter.this.mFFSArcusSettings.getFFRSettings().getProvisionerSettings().isEnabled()) {
                        WJLog.d(ZeroTouchWorkflowRouter.TAG, "FFR disabled according to Arcus. Checking isDistressed().");
                        if (whisperJoinPeripheralDeviceDetails.isDistressed()) {
                            WJLog.i(ZeroTouchWorkflowRouter.TAG, "FFR has been disabled for discovered device");
                            return Observable.empty();
                        }
                    }
                    WJLog.d(ZeroTouchWorkflowRouter.TAG, "Routing to Wifi Simple Setup workflow");
                    return ZeroTouchWorkflowRouter.this.mZeroTouchWorkflowFactory.createNewWifiSimpleSetupWorkflow().run(whisperJoinPeripheralDeviceDetails);
                } else if (!(abstractPeripheralDeviceDetails instanceof ThirdPartyPeripheralDeviceDetails) || !ZeroTouchWorkflowRouter.this.mFFSArcusSettings.getZeroTouchProvisioningSettings().isPhilipsBLEWorkflowEnabled()) {
                    WJLog.w(ZeroTouchWorkflowRouter.TAG, "Couldn't find workflow matching device");
                    return Observable.empty();
                } else {
                    WJLog.d(ZeroTouchWorkflowRouter.TAG, "Routing to Philips Zigbee BleWorkflow");
                    return ZeroTouchWorkflowRouter.this.mZeroTouchWorkflowFactory.createNewPhilipsZigbeeBleWorkflow().run((ThirdPartyPeripheralDeviceDetails) abstractPeripheralDeviceDetails);
                }
            }
        });
    }
}
