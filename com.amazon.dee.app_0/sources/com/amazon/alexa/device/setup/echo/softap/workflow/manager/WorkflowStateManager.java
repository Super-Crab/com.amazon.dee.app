package com.amazon.alexa.device.setup.echo.softap.workflow.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.device.setup.echo.softap.linkcode.LinkCodeAuthorizer;
import com.amazon.alexa.device.setup.echo.softap.locale.LocaleAndEndpointConfigurator;
import com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManager;
import com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManagerFactory;
import com.amazon.alexa.device.setup.echo.softap.workflow.data.WorkflowStateData;
import com.amazon.alexa.device.setup.echo.softap.workflow.data.WorkflowStatusUpdate;
import com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.EchoSoftAPWorkflowManager;
import com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.EchoSoftAPWorkflowManagerFactory;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.device.setup.thrift.APConnectExtendedInfo;
import com.amazon.device.setup.thrift.APConnectInfo;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.apache.thrift.orig.transport.TTransportException;
/* loaded from: classes6.dex */
public class WorkflowStateManager implements WorkflowActions {
    private static final String TAG = "WorkflowStateManager";
    private CompositeDisposable currentRequest;
    private EchoSoftAPWorkflowManager echoSoftAPWorkflowManager;
    private SoftAPResponseCallback softAPResponseCallback;
    private SoftAPWifiManager softAPWifiManager;
    private WorkflowState workflowState;

    public WorkflowStateManager(Context context, LinkCodeAuthorizer linkCodeAuthorizer, LocaleAndEndpointConfigurator localeAndEndpointConfigurator, SoftAPResponseCallback softAPResponseCallback, LazyComponent<IdentityService> lazyComponent) {
        this.softAPResponseCallback = softAPResponseCallback;
        updateWorkflow(WorkflowState.NOT_CONNECTED);
        this.softAPWifiManager = SoftAPWifiManagerFactory.getSoftAPWifiManager(context, lazyComponent);
        this.echoSoftAPWorkflowManager = EchoSoftAPWorkflowManagerFactory.getEchoSoftAPWorkflowManager(this.softAPWifiManager, linkCodeAuthorizer, localeAndEndpointConfigurator);
        this.currentRequest = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    /* renamed from: handleConnectToEchoError */
    public void lambda$getWifiNetworks$1$WorkflowStateManager(Throwable th) {
        if (!th.getClass().equals(TTransportException.class) && !th.getClass().equals(SoftAPWifiManager.AutoConnectException.class)) {
            updateWorkflow(WorkflowState.CONNECTED, th);
            return;
        }
        updateWorkflow(WorkflowState.NOT_CONNECTED, th);
        this.softAPWifiManager.disassociateFromDoppler();
        this.softAPWifiManager.unbindNetwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWorkflow(WorkflowState workflowState) {
        updateWorkflow(workflowState, (WorkflowStateData) null);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
    @SuppressLint({"NewApi"})
    public void autoConnectToEcho() {
        clearCurrentRequest();
        try {
            this.workflowState.connectToEcho();
            if (this.currentRequest.add(this.softAPWifiManager.getAutoConnectSuccessSubject().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$J0b3qNLZQMYiAjeaixRy3jUrL18
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$autoConnectToEcho$15$WorkflowStateManager((Boolean) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$36cUWR9dOgaXwFBMYm7PCIbztHc
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$autoConnectToEcho$16$WorkflowStateManager((Throwable) obj);
                }
            }))) {
                this.echoSoftAPWorkflowManager.autoConnectToEchoAndSetLocaleAndEndpoints();
            } else {
                updateWorkflow(this.workflowState, new WorkflowStateData("Failed to subscribe to auto connect event", WorkflowStateData.State.ERROR));
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            updateWorkflow(this.workflowState, new WorkflowStateData(e.toString(), WorkflowStateData.State.ERROR));
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
    @RequiresApi(api = 30)
    public void cancelSetup() {
        clearCurrentRequest();
        this.workflowState.cancelSetup();
        if (!this.workflowState.equals(WorkflowState.NOT_CONNECTED)) {
            this.currentRequest.add(this.echoSoftAPWorkflowManager.cancelSetup().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$tvFeHy3djtScgFZSEVMbUvQLQCM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$cancelSetup$6$WorkflowStateManager((WorkflowStateData) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$WwPOS6nzlN__FEuvywhqA5Jaeuo
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$cancelSetup$7$WorkflowStateManager((Throwable) obj);
                }
            }, new Action() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$FWF77iDUmwj9K2V2DhFgEaDE2xM
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    WorkflowStateManager.this.lambda$cancelSetup$8$WorkflowStateManager();
                }
            }));
        }
    }

    public void clearCurrentRequest() {
        CompositeDisposable compositeDisposable = this.currentRequest;
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            this.currentRequest.dispose();
        }
        this.currentRequest = new CompositeDisposable();
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
    public void connectToEcho() {
        clearCurrentRequest();
        this.softAPWifiManager.clearAutoConnectReceivers();
        try {
            this.workflowState.connectToEcho();
            this.currentRequest.add((Disposable) this.echoSoftAPWorkflowManager.connectAndSetLocaleEndpoints().subscribeOn(Schedulers.io()).subscribeWith(new DisposableObserver<WorkflowStateData>() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowStateManager.1
                @Override // io.reactivex.rxjava3.core.Observer
                public void onComplete() {
                    WorkflowStateManager.this.updateWorkflow(WorkflowState.CONNECTED);
                }

                @Override // io.reactivex.rxjava3.core.Observer
                @RequiresApi(api = 30)
                public void onError(Throwable th) {
                    WorkflowStateManager.this.lambda$getWifiNetworks$1$WorkflowStateManager(th);
                    if (th.getClass().equals(TTransportException.class)) {
                        WorkflowStateManager.this.connectToEcho();
                    }
                }

                @Override // io.reactivex.rxjava3.core.Observer
                public void onNext(WorkflowStateData workflowStateData) {
                    WorkflowStateManager.this.updateWorkflow(WorkflowState.EXECUTING, workflowStateData);
                }
            }));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            updateWorkflow(this.workflowState, new WorkflowStateData(e.toString(), WorkflowStateData.State.ERROR));
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
    @RequiresApi(api = 30)
    public void connectToWifiNetwork(APConnectInfo aPConnectInfo) {
        try {
            clearCurrentRequest();
            this.workflowState.connectToWifiNetwork(aPConnectInfo);
            this.currentRequest.add(this.echoSoftAPWorkflowManager.connectToAP(aPConnectInfo).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$AMiMiiyyfZJlexU8Ub3Au3nYIZY
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$connectToWifiNetwork$3$WorkflowStateManager((WorkflowStateData) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$q0JxBpdv6_0whglgBtQtX3VVREw
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$connectToWifiNetwork$4$WorkflowStateManager((Throwable) obj);
                }
            }, new Action() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$83DqDdSmV5-oqq7fzxObx8hnyN8
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    WorkflowStateManager.this.lambda$connectToWifiNetwork$5$WorkflowStateManager();
                }
            }));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            updateWorkflow(this.workflowState, new WorkflowStateData(e.toString(), WorkflowStateData.State.ERROR));
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
    @RequiresApi(api = 30)
    public void evaluateCaptivePortal() {
        try {
            clearCurrentRequest();
            this.workflowState.evaluateCaptivePortal();
            this.currentRequest.add(this.echoSoftAPWorkflowManager.evaluateCaptivePortal().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$8roBXxD1xnnU9Q371nvA5ahc9i0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$evaluateCaptivePortal$9$WorkflowStateManager((WorkflowStateData) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$NOHiHrYrzmnHnAETZ8RboZjYufg
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$evaluateCaptivePortal$10$WorkflowStateManager((Throwable) obj);
                }
            }, new Action() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$dlwSfChf8U8_tXTyfEl9mh8cSW4
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    WorkflowStateManager.this.lambda$evaluateCaptivePortal$11$WorkflowStateManager();
                }
            }));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
    @SuppressLint({"NewApi"})
    public void evaluateHotSpotDeviceNetworkDisconnect() {
        try {
            clearCurrentRequest();
            this.workflowState.evaluateHotSpotDeviceNetworkDisconnect();
            this.currentRequest.add(this.echoSoftAPWorkflowManager.evaluateHotSpotDeviceNetworkDisconnect().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$owvYVeEnR2FMiVO0BN-5y94iwx0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$evaluateHotSpotDeviceNetworkDisconnect$12$WorkflowStateManager((WorkflowStateData) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$6cn4sNoNTpFozyyEtBesk7PJifA
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$evaluateHotSpotDeviceNetworkDisconnect$13$WorkflowStateManager((Throwable) obj);
                }
            }, new Action() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$uCPV38YamV7n7X4_Szi16S1JoYA
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    WorkflowStateManager.this.lambda$evaluateHotSpotDeviceNetworkDisconnect$14$WorkflowStateManager();
                }
            }));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            updateWorkflow(this.workflowState, new WorkflowStateData(e.toString(), WorkflowStateData.State.ERROR));
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
    @RequiresApi(api = 30)
    public void getWifiNetworks() {
        try {
            clearCurrentRequest();
            this.workflowState.getWifiNetworks();
            this.currentRequest.add(this.echoSoftAPWorkflowManager.getWifiNetworks().subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$CEkPGE-LnLgsbqvY1S5p91KRjbc
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$getWifiNetworks$0$WorkflowStateManager((WorkflowStateData) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$DmyKMGl5l8DDEcvOOxMaR9Op54I
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    WorkflowStateManager.this.lambda$getWifiNetworks$1$WorkflowStateManager((Throwable) obj);
                }
            }, new Action() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.-$$Lambda$WorkflowStateManager$gGzRnTEN5GlmDsH4SQ2lUF0Cyns
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    WorkflowStateManager.this.lambda$getWifiNetworks$2$WorkflowStateManager();
                }
            }));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            updateWorkflow(this.workflowState, new WorkflowStateData(e.toString(), WorkflowStateData.State.ERROR));
        }
    }

    public WorkflowState getWorkflowState() {
        return this.workflowState;
    }

    public /* synthetic */ void lambda$autoConnectToEcho$15$WorkflowStateManager(Boolean bool) throws Throwable {
        updateWorkflow(WorkflowState.NOT_CONNECTED, new WorkflowStateData(bool, WorkflowStateData.State.NETWORK_BOUND));
    }

    public /* synthetic */ void lambda$cancelSetup$6$WorkflowStateManager(WorkflowStateData workflowStateData) throws Throwable {
        updateWorkflow(WorkflowState.EXECUTING, workflowStateData);
    }

    public /* synthetic */ void lambda$cancelSetup$7$WorkflowStateManager(Throwable th) throws Throwable {
        updateWorkflow(WorkflowState.NOT_CONNECTED, th);
        this.softAPWifiManager.disassociateFromDoppler();
        this.softAPWifiManager.unbindNetwork();
    }

    public /* synthetic */ void lambda$cancelSetup$8$WorkflowStateManager() throws Throwable {
        updateWorkflow(WorkflowState.NOT_CONNECTED);
    }

    public /* synthetic */ void lambda$connectToWifiNetwork$3$WorkflowStateManager(WorkflowStateData workflowStateData) throws Throwable {
        updateWorkflow(WorkflowState.EXECUTING, workflowStateData);
    }

    public /* synthetic */ void lambda$connectToWifiNetwork$5$WorkflowStateManager() throws Throwable {
        updateWorkflow(WorkflowState.PROVISIONING_COMPLETED, new WorkflowStateData("", WorkflowStateData.State.PROVISIONING_COMPLETE));
    }

    public /* synthetic */ void lambda$evaluateCaptivePortal$11$WorkflowStateManager() throws Throwable {
        updateWorkflow(WorkflowState.CONNECTED);
    }

    public /* synthetic */ void lambda$evaluateCaptivePortal$9$WorkflowStateManager(WorkflowStateData workflowStateData) throws Throwable {
        updateWorkflow(WorkflowState.CONNECTED, workflowStateData);
    }

    public /* synthetic */ void lambda$evaluateHotSpotDeviceNetworkDisconnect$12$WorkflowStateManager(WorkflowStateData workflowStateData) throws Throwable {
        updateWorkflow(WorkflowState.EXECUTING, workflowStateData);
    }

    public /* synthetic */ void lambda$evaluateHotSpotDeviceNetworkDisconnect$13$WorkflowStateManager(Throwable th) throws Throwable {
        updateWorkflow(WorkflowState.CONNECTED, th);
    }

    public /* synthetic */ void lambda$evaluateHotSpotDeviceNetworkDisconnect$14$WorkflowStateManager() throws Throwable {
        updateWorkflow(WorkflowState.NOT_CONNECTED, new WorkflowStateData("", WorkflowStateData.State.PROVISIONING_COMPLETE));
    }

    public /* synthetic */ void lambda$getWifiNetworks$0$WorkflowStateManager(WorkflowStateData workflowStateData) throws Throwable {
        updateWorkflow(WorkflowState.EXECUTING, workflowStateData);
    }

    public /* synthetic */ void lambda$getWifiNetworks$2$WorkflowStateManager() throws Throwable {
        updateWorkflow(WorkflowState.CONNECTED);
    }

    public void setWorkflowState(WorkflowState workflowState) {
        this.workflowState = workflowState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWorkflow(WorkflowState workflowState, WorkflowStateData workflowStateData) {
        this.workflowState = workflowState;
        this.softAPResponseCallback.sendResponse(new WorkflowStatusUpdate(workflowState, workflowStateData));
    }

    private void updateWorkflow(WorkflowState workflowState, Throwable th) {
        this.workflowState = workflowState;
        this.softAPResponseCallback.sendResponse(new WorkflowStatusUpdate(workflowState, th));
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowActions
    public void connectToWifiNetwork(APConnectInfo aPConnectInfo, APConnectExtendedInfo aPConnectExtendedInfo) {
        try {
            this.workflowState.connectToWifiNetwork(aPConnectInfo, aPConnectExtendedInfo);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            updateWorkflow(this.workflowState, new WorkflowStateData(e.toString(), WorkflowStateData.State.ERROR));
        }
    }
}
