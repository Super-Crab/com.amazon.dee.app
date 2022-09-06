package com.amazon.whisperjoin.deviceprovisioningservice.di;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.BaseDependencyInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.DaggerBaseComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.DaggerBaseDependencyInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.DaggerProvisionerServicesComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.DaggerProvisionerServicesDependencyInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.DaggerProvisioningComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.DaggerProvisioningDependencyInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.DaggerWorkflowComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.DaggerZeroTouchControllerComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisionerServicesDependencyInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.ProvisioningDependencyInjector;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.WorkflowComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.components.ZeroTouchControllerComponent;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ContextModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.DiscoveryConfigurationModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ProvisioningConfigurationModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.WorkflowModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
/* loaded from: classes13.dex */
public class DaggerWrapper {
    private static final String TAG = "DaggerWrapper";
    protected static BaseComponent sBaseComponent;
    protected static ProvisionerServicesComponent sOverrideProvisionerServicesComponent;
    protected static WorkflowComponent sWorkflowComponent;

    public static BaseComponent getBaseComponent() {
        WJLog.v(TAG, "getBaseComponent");
        if (sBaseComponent == null) {
            WJLog.v(TAG, "Base component not initialized");
        }
        return sBaseComponent;
    }

    public static BaseDependencyInjector getBaseDependencyInjector() {
        WJLog.v(TAG, "getBaseDependencyInjector");
        return DaggerBaseDependencyInjector.builder().baseComponent(getBaseComponent()).build();
    }

    public static ProvisionerServicesComponent getInitializedProvisioningComponent(ProvisioningServiceConfiguration provisioningServiceConfiguration, WorkflowConfiguration workflowConfiguration, ProvisioningMethod provisioningMethod) {
        if (sBaseComponent != null) {
            ProvisionerServicesComponent provisionerServicesComponent = sOverrideProvisionerServicesComponent;
            if (provisionerServicesComponent != null) {
                return provisionerServicesComponent;
            }
            WJLog.d(TAG, "Creating provisioning component");
            return DaggerProvisionerServicesComponent.builder().baseComponent(sBaseComponent).provisioningConfigurationModule(new ProvisioningConfigurationModule(provisioningServiceConfiguration, workflowConfiguration, provisioningMethod)).build();
        }
        throw new IllegalStateException("Base component must first be initiailzed");
    }

    public static ProvisionerServicesDependencyInjector getProvisioningDependencyInjector(ProvisionerServicesComponent provisionerServicesComponent) {
        return DaggerProvisionerServicesDependencyInjector.builder().provisionerServicesComponent(provisionerServicesComponent).build();
    }

    public static void initializeBaseComponent(Context context) {
        if (sBaseComponent != null) {
            WJLog.e(TAG, "Base Component already exists");
            return;
        }
        WJLog.d(TAG, "Initializing base component");
        sBaseComponent = DaggerBaseComponent.builder().contextModule(new ContextModule(context)).build();
    }

    /* loaded from: classes13.dex */
    public static class ProvisionerServices {
        private final ProvisionerServicesComponent mProvisionerServicesComponent;
        private ProvisioningComponent mProvisioningComponent;
        private ProvisioningComponent mProvisioningComponentOverride;
        private WorkflowComponent mWorkflowComponentOverride;
        private ZeroTouchControllerComponent mZeroTouchControllerComponentOverride;

        public ProvisionerServices(BaseComponent baseComponent, ProvisioningServiceConfiguration provisioningServiceConfiguration, WorkflowConfiguration workflowConfiguration, ProvisioningMethod provisioningMethod) {
            this.mProvisionerServicesComponent = DaggerProvisionerServicesComponent.builder().baseComponent(baseComponent).provisioningConfigurationModule(new ProvisioningConfigurationModule(provisioningServiceConfiguration, workflowConfiguration, provisioningMethod)).build();
        }

        public void clearProvisioningComponent() {
            this.mProvisioningComponent = null;
        }

        public WorkflowComponent createWorkflow(Single<FFSArcusSettings> single) {
            if (this.mProvisioningComponent != null) {
                WorkflowComponent workflowComponent = this.mWorkflowComponentOverride;
                return workflowComponent != null ? workflowComponent : DaggerWorkflowComponent.builder().workflowModule(new WorkflowModule(single)).provisioningComponent(this.mProvisioningComponent).build();
            }
            throw new IllegalStateException("Must init provisioning component first");
        }

        public ProvisionerServicesDependencyInjector getDependencyInjector() {
            return DaggerProvisionerServicesDependencyInjector.builder().provisionerServicesComponent(this.mProvisionerServicesComponent).build();
        }

        public ProvisioningDependencyInjector getProvisioningDependencyInjector() {
            return DaggerProvisioningDependencyInjector.builder().provisioningComponent(this.mProvisioningComponent).build();
        }

        public Disposable initProvisioningComponent(ScanningMode scanningMode, boolean z, OveractiveConfiguration overactiveConfiguration) {
            ProvisioningComponent provisioningComponent = this.mProvisioningComponentOverride;
            if (provisioningComponent != null) {
                this.mProvisioningComponent = provisioningComponent;
                return Observable.empty().subscribe();
            }
            this.mProvisioningComponent = DaggerProvisioningComponent.builder().provisionerServicesComponent(this.mProvisionerServicesComponent).discoveryConfigurationModule(new DiscoveryConfigurationModule(scanningMode, z, overactiveConfiguration)).build();
            return this.mProvisioningComponent.providesProvisioningManagerDisposable();
        }

        public void setTestProvisioningComponent(ProvisioningComponent provisioningComponent) {
            this.mProvisioningComponentOverride = provisioningComponent;
        }

        @VisibleForTesting
        public void setWorkflowComponentOverride(WorkflowComponent workflowComponent) {
            this.mWorkflowComponentOverride = workflowComponent;
        }

        @VisibleForTesting
        public void setZeroTouchControllerComponentOverride(ZeroTouchControllerComponent zeroTouchControllerComponent) {
            this.mZeroTouchControllerComponentOverride = zeroTouchControllerComponent;
        }

        public ZeroTouchControllerComponent zeroTouchController(Context context, ZeroTouchWorkflowFactory zeroTouchWorkflowFactory, FFSArcusSettings fFSArcusSettings) {
            if (this.mProvisioningComponent != null) {
                ZeroTouchControllerComponent zeroTouchControllerComponent = this.mZeroTouchControllerComponentOverride;
                return zeroTouchControllerComponent != null ? zeroTouchControllerComponent : DaggerZeroTouchControllerComponent.builder().provisioningComponent(this.mProvisioningComponent).zeroTouchProvisioningModule(new ZeroTouchProvisioningModule(context, zeroTouchWorkflowFactory, fFSArcusSettings)).build();
            }
            throw new IllegalStateException("Must init provisioning component first");
        }

        @VisibleForTesting
        public ProvisionerServices(ProvisionerServicesComponent provisionerServicesComponent) {
            this.mProvisionerServicesComponent = provisionerServicesComponent;
        }
    }
}
