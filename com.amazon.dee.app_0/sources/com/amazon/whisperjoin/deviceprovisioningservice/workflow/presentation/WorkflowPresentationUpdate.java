package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.controller.UGSClientWorkflowController;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.viewmodel.ViewModel;
/* loaded from: classes13.dex */
public class WorkflowPresentationUpdate {
    private final UGSClientWorkflowController mController;
    private final ViewModel mViewModel;

    public WorkflowPresentationUpdate(ViewModel viewModel, UGSClientWorkflowController uGSClientWorkflowController) {
        this.mViewModel = viewModel;
        this.mController = uGSClientWorkflowController;
    }

    public UGSClientWorkflowController getController() {
        return this.mController;
    }

    public ViewModel getViewModel() {
        return this.mViewModel;
    }
}
