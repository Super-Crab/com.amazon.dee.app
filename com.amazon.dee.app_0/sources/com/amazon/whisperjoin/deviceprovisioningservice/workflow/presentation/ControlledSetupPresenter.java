package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.FFSArcusSyncCoordinator;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.ThrottleSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoveredDevice;
import com.amazon.whisperjoin.deviceprovisioningservice.di.DaggerWrapper;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.ControlledSetupWorkflow;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.controller.UGSClientWorkflowController;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.DiscoveredDevicesViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.IdleViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.InProgressViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ProvisioningDetailsViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupCompleteViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.SetupFailureViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.WifiConnectionErrorViewModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ClientProvisioningDataModel;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import javax.inject.Inject;
/* loaded from: classes13.dex */
public class ControlledSetupPresenter extends BasePresenter<ControlledSetupPresenterContract.View> implements ControlledSetupPresenterContract.Action {
    private static final String TAG = "ControlledSetupPresenter";
    private Context mContext;
    @Inject
    FFSArcusSyncCoordinator mFFSArcusSyncCoordinator;
    private boolean mIsAttached;
    @Inject
    ProvisionerClientData mProvisionerClientData;
    private Disposable mProvisioningDisposable;
    private Disposable mStreamSubscription;
    private UGSClientWorkflowController mUGSClientWorkflowController;
    private final ControlledSetupWorkflow mWorkflow;

    public ControlledSetupPresenter(Context context, ProvisioningServiceConfiguration provisioningServiceConfiguration, WorkflowConfiguration workflowConfiguration) {
        this.mContext = context;
        DaggerWrapper.initializeBaseComponent(context);
        DaggerWrapper.ProvisionerServices provisionerServices = new DaggerWrapper.ProvisionerServices(DaggerWrapper.getBaseComponent(), provisioningServiceConfiguration, workflowConfiguration, ProvisioningMethod.MANUAL);
        this.mProvisioningDisposable = provisionerServices.initProvisioningComponent(ScanningMode.HIGH_POWER_LOW_LATENCY, false, new ThrottleSettings().getOveractiveConfiguration());
        provisionerServices.getProvisioningDependencyInjector().inject(this);
        PresenterValidator.validatePreconditions(this.mProvisionerClientData);
        this.mFFSArcusSyncCoordinator.initializeWithProvisionerClientData(this.mProvisionerClientData);
        this.mWorkflow = provisionerServices.createWorkflow(this.mFFSArcusSyncCoordinator.getFFSArcusSettings()).getControlledSetupWorkflow();
        this.mIsAttached = false;
        this.mWorkflow.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void consumeViewModel(ViewModel viewModel) {
        if (viewModel instanceof IdleViewModel) {
            ((ControlledSetupPresenterContract.View) this.mView).showIdleState((IdleViewModel) viewModel);
        } else if (viewModel instanceof InProgressViewModel) {
            ((ControlledSetupPresenterContract.View) this.mView).showInProgress((InProgressViewModel) viewModel);
        } else if (viewModel instanceof DiscoveredDevicesViewModel) {
            ((ControlledSetupPresenterContract.View) this.mView).showDiscoveredDevices((DiscoveredDevicesViewModel) viewModel);
        } else if (viewModel instanceof ProvisioningDetailsViewModel) {
            ((ControlledSetupPresenterContract.View) this.mView).showProvisioningDetails((ProvisioningDetailsViewModel) viewModel);
        } else if (viewModel instanceof WifiConnectionErrorViewModel) {
            ((ControlledSetupPresenterContract.View) this.mView).showWifiConnectionError((WifiConnectionErrorViewModel) viewModel);
        } else if (viewModel instanceof SetupFailureViewModel) {
            ((ControlledSetupPresenterContract.View) this.mView).showSetupFailure((SetupFailureViewModel) viewModel);
        } else if (viewModel instanceof SetupCompleteViewModel) {
            ((ControlledSetupPresenterContract.View) this.mView).showSetupComplete((SetupCompleteViewModel) viewModel);
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected View Model ");
            outline107.append(viewModel.getClass().toString());
            throw new IllegalStateException(outline107.toString());
        }
    }

    private void verifyAttached() {
        if (this.mIsAttached) {
            return;
        }
        throw new IllegalStateException("Can't perform action if detached");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.Action
    public void chooseDevice(DiscoveredDevice discoveredDevice) {
        verifyAttached();
        this.mUGSClientWorkflowController.chooseDevice(discoveredDevice);
    }

    public void destroy() {
        WJLog.d(TAG, "Destroy");
        UGSClientWorkflowController uGSClientWorkflowController = this.mUGSClientWorkflowController;
        if (uGSClientWorkflowController != null) {
            uGSClientWorkflowController.terminateSetup();
            this.mUGSClientWorkflowController = null;
        }
        Disposable disposable = this.mProvisioningDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.mWorkflow.stop();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenter, com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenterAction, com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.Action
    public void detachView() {
        super.detachView();
        Disposable disposable = this.mStreamSubscription;
        if (disposable != null) {
            disposable.dispose();
            this.mStreamSubscription = null;
        }
        this.mIsAttached = false;
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.Action
    public void discoverDevices() {
        verifyAttached();
        this.mUGSClientWorkflowController.discoverDevices();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.Action
    public void provisionDevice(ClientProvisioningDataModel clientProvisioningDataModel) {
        verifyAttached();
        this.mUGSClientWorkflowController.sendProvisioningData(clientProvisioningDataModel);
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.Action
    public void refreshAvailableNetworks() {
        verifyAttached();
        this.mUGSClientWorkflowController.refreshAvailableNetworks();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract.Action
    public void terminateSetup() {
        verifyAttached();
        this.mUGSClientWorkflowController.terminateSetup();
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenter, com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenterAction
    public void attachView(@NonNull ControlledSetupPresenterContract.View view) {
        super.attachView((ControlledSetupPresenter) view);
        WJLog.d(TAG, "Attaching View");
        this.mStreamSubscription = (Disposable) this.mWorkflow.getPresentationStream().subscribeWith(new DisposableObserver<WorkflowPresentationUpdate>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenter.1
            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                throw new IllegalStateException("Stream shouldn't even complete");
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(Throwable th) {
                throw new RuntimeException("Something failed", th);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onNext(WorkflowPresentationUpdate workflowPresentationUpdate) {
                ControlledSetupPresenter.this.mUGSClientWorkflowController = workflowPresentationUpdate.getController();
                ControlledSetupPresenter.this.consumeViewModel(workflowPresentationUpdate.getViewModel());
            }
        });
        this.mIsAttached = true;
    }

    ControlledSetupPresenter(ControlledSetupWorkflow controlledSetupWorkflow) {
        this.mIsAttached = false;
        this.mWorkflow = controlledSetupWorkflow;
        this.mWorkflow.start();
    }

    ControlledSetupPresenter(Context context, ControlledSetupWorkflow controlledSetupWorkflow) {
        this.mContext = context;
        this.mIsAttached = false;
        this.mWorkflow = controlledSetupWorkflow;
        this.mWorkflow.start();
    }
}
