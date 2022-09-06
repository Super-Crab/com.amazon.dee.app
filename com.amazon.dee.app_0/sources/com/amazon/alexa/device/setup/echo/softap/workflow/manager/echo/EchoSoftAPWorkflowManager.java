package com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo;

import androidx.annotation.RequiresApi;
import com.amazon.alexa.device.setup.echo.softap.linkcode.AuthorizationCallback;
import com.amazon.alexa.device.setup.echo.softap.linkcode.LinkCodeAuthorizer;
import com.amazon.alexa.device.setup.echo.softap.linkcode.PreAuthorizationCallback;
import com.amazon.alexa.device.setup.echo.softap.linkcode.PreAuthorizedLinkCode;
import com.amazon.alexa.device.setup.echo.softap.locale.LocaleAndEndpointConfigurator;
import com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient;
import com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClientFactory;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.AuthorizeLinkCodeException;
import com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManager;
import com.amazon.alexa.device.setup.echo.softap.workflow.data.WifiNetworks;
import com.amazon.alexa.device.setup.echo.softap.workflow.data.WorkflowStateData;
import com.amazon.device.setup.thrift.APConnectInfo;
import com.amazon.device.setup.thrift.DeviceCredentials;
import com.amazon.device.setup.thrift.DeviceDetails;
import com.amazon.device.setup.thrift.OTADetails;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public class EchoSoftAPWorkflowManager {
    public AuthorizationCallback authorizationCallback;
    private DeviceDetails deviceDetails;
    private PreAuthorizedLinkCode generatedPreAuthorizedLinkCode;
    private LinkCodeAuthorizer linkCodeAuthorizer;
    private LocaleAndEndpointConfigurator localeAndEndpointConfigurator;
    public PreAuthorizationCallback preAuthorizationCallback;
    private SoftAPWifiManager softAPWifiManager;
    private ThriftClient thriftClientManager = ThriftClientFactory.getThriftClient();

    public EchoSoftAPWorkflowManager(SoftAPWifiManager softAPWifiManager, LinkCodeAuthorizer linkCodeAuthorizer, LocaleAndEndpointConfigurator localeAndEndpointConfigurator) {
        this.linkCodeAuthorizer = linkCodeAuthorizer;
        this.localeAndEndpointConfigurator = localeAndEndpointConfigurator;
        this.softAPWifiManager = softAPWifiManager;
    }

    private void generateAuthorizedLinkCode() {
        this.preAuthorizationCallback = new PreAuthorizationCallback() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.EchoSoftAPWorkflowManager.2
            @Override // com.amazon.alexa.device.setup.echo.softap.linkcode.PreAuthorizationCallback
            public void authorizationFailure(Throwable th) {
            }

            @Override // com.amazon.alexa.device.setup.echo.softap.linkcode.PreAuthorizationCallback
            public void authorizationSuccess(PreAuthorizedLinkCode preAuthorizedLinkCode) {
                EchoSoftAPWorkflowManager.this.generatedPreAuthorizedLinkCode = preAuthorizedLinkCode;
            }
        };
        this.linkCodeAuthorizer.generatePreAuthorizedLinkCode(this.preAuthorizationCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ WorkflowStateData lambda$getWifiNetworks$1(List list, List list2) throws Throwable {
        return new WorkflowStateData(new WifiNetworks(list, list2), WorkflowStateData.State.GET_SCAN_LIST);
    }

    public void autoConnectToEchoAndSetLocaleAndEndpoints() {
        generateAuthorizedLinkCode();
        this.softAPWifiManager.initializeAutoConnectToEcho();
    }

    @RequiresApi(api = 30)
    public Observable<WorkflowStateData> cancelSetup() {
        return Observable.concat(this.thriftClientManager.completeSetup(), Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.-$$Lambda$EchoSoftAPWorkflowManager$l2Mkus4jOoXHSI3dZ-Iap-OPZNc
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                EchoSoftAPWorkflowManager.this.lambda$cancelSetup$6$EchoSoftAPWorkflowManager(observableEmitter);
            }
        }));
    }

    public Observable<WorkflowStateData> connectAndSetLocaleEndpoints() {
        generateAuthorizedLinkCode();
        return Observable.concat(this.thriftClientManager.ping(this.softAPWifiManager), this.thriftClientManager.getDeviceDetails().flatMap(new Function() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.-$$Lambda$EchoSoftAPWorkflowManager$YmTfxrmAy8P00nZe6fdinpb_mGw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return EchoSoftAPWorkflowManager.this.lambda$connectAndSetLocaleEndpoints$0$EchoSoftAPWorkflowManager((WorkflowStateData) obj);
            }
        }));
    }

    @RequiresApi(api = 30)
    public Observable<WorkflowStateData> connectToAP(APConnectInfo aPConnectInfo) {
        return Observable.concat(Observable.concat(this.thriftClientManager.connectToAP(aPConnectInfo, this.deviceDetails, this.generatedPreAuthorizedLinkCode).delay(1L, TimeUnit.SECONDS), this.thriftClientManager.getLinkCode().delay(1L, TimeUnit.SECONDS).flatMap(new Function() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.-$$Lambda$EchoSoftAPWorkflowManager$vY_Wphdq1jcX4t-wi9gU_ivMn24
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return EchoSoftAPWorkflowManager.this.lambda$connectToAP$3$EchoSoftAPWorkflowManager((WorkflowStateData) obj);
            }
        })), Observable.concat(this.thriftClientManager.verifyDeviceKnowsItIsRegistered().delay(1L, TimeUnit.SECONDS), this.thriftClientManager.getOtaDetails().delay(1L, TimeUnit.SECONDS).flatMap(new Function() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.-$$Lambda$EchoSoftAPWorkflowManager$Y2imDMuVt653T2XFvSFAqZuBBZA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return EchoSoftAPWorkflowManager.this.lambda$connectToAP$4$EchoSoftAPWorkflowManager((WorkflowStateData) obj);
            }
        }), Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.-$$Lambda$EchoSoftAPWorkflowManager$VH2gE3qHAEgEd2729FiTD9ym31U
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                EchoSoftAPWorkflowManager.this.lambda$connectToAP$5$EchoSoftAPWorkflowManager(observableEmitter);
            }
        })));
    }

    public Observable<WorkflowStateData> evaluateCaptivePortal() {
        return this.thriftClientManager.evaluateCaptivePortal();
    }

    @RequiresApi(api = 30)
    public Observable<WorkflowStateData> evaluateHotSpotDeviceNetworkDisconnect() {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.-$$Lambda$EchoSoftAPWorkflowManager$3EGfvmj3B1QPoiQfD4E-W6_LbU0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                EchoSoftAPWorkflowManager.this.lambda$evaluateHotSpotDeviceNetworkDisconnect$7$EchoSoftAPWorkflowManager(observableEmitter);
            }
        });
    }

    public Observable<WorkflowStateData> getWifiNetworks() {
        return Observable.zip(this.thriftClientManager.getScanList(), this.thriftClientManager.getKnownList(), $$Lambda$EchoSoftAPWorkflowManager$hcLWR_Dl_zWESoYORN3PSKfPUs8.INSTANCE);
    }

    public /* synthetic */ void lambda$cancelSetup$6$EchoSoftAPWorkflowManager(ObservableEmitter observableEmitter) throws Throwable {
        this.softAPWifiManager.unbindNetwork();
        this.softAPWifiManager.disassociateFromDoppler();
        observableEmitter.onComplete();
    }

    public /* synthetic */ ObservableSource lambda$connectAndSetLocaleEndpoints$0$EchoSoftAPWorkflowManager(WorkflowStateData workflowStateData) throws Throwable {
        DeviceDetails deviceDetails = (DeviceDetails) workflowStateData.workflowData;
        this.deviceDetails = deviceDetails;
        return Observable.concat(Observable.just(workflowStateData), this.thriftClientManager.setLocaleAndEndpointInfo(this.localeAndEndpointConfigurator.generateLocaleAndEndpointInfo(deviceDetails)));
    }

    public /* synthetic */ ObservableSource lambda$connectToAP$3$EchoSoftAPWorkflowManager(WorkflowStateData workflowStateData) throws Throwable {
        final DeviceCredentials deviceCredentials = (DeviceCredentials) workflowStateData.workflowData;
        return Observable.concat(Observable.just(workflowStateData), Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.-$$Lambda$EchoSoftAPWorkflowManager$eH6DTxG-dzoeARAPK9v2BTEn3t0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                EchoSoftAPWorkflowManager.this.lambda$null$2$EchoSoftAPWorkflowManager(deviceCredentials, observableEmitter);
            }
        }));
    }

    public /* synthetic */ ObservableSource lambda$connectToAP$4$EchoSoftAPWorkflowManager(WorkflowStateData workflowStateData) throws Throwable {
        T t = workflowStateData.workflowData;
        if (t instanceof OTADetails) {
            OTADetails oTADetails = (OTADetails) t;
            if (oTADetails.getUpdateManifestList() != null && oTADetails.getUpdateManifestList().size() > 0) {
                return Observable.just(workflowStateData);
            }
        }
        return Observable.concat(Observable.just(workflowStateData), this.thriftClientManager.verifyAlexaConnectionState().delay(1L, TimeUnit.SECONDS), this.thriftClientManager.completeSetup().delay(1L, TimeUnit.SECONDS));
    }

    public /* synthetic */ void lambda$connectToAP$5$EchoSoftAPWorkflowManager(ObservableEmitter observableEmitter) throws Throwable {
        this.softAPWifiManager.unbindNetwork();
        this.softAPWifiManager.disassociateFromDoppler();
        observableEmitter.onComplete();
    }

    public /* synthetic */ void lambda$evaluateHotSpotDeviceNetworkDisconnect$7$EchoSoftAPWorkflowManager(ObservableEmitter observableEmitter) throws Throwable {
        this.softAPWifiManager.unbindNetwork();
        this.softAPWifiManager.disassociateFromDoppler();
        observableEmitter.onComplete();
    }

    public /* synthetic */ void lambda$null$2$EchoSoftAPWorkflowManager(DeviceCredentials deviceCredentials, final ObservableEmitter observableEmitter) throws Throwable {
        this.authorizationCallback = new AuthorizationCallback() { // from class: com.amazon.alexa.device.setup.echo.softap.workflow.manager.echo.EchoSoftAPWorkflowManager.1
            @Override // com.amazon.alexa.device.setup.echo.softap.linkcode.AuthorizationCallback
            public void authorizationFailure(Throwable th) {
                observableEmitter.onError(new AuthorizeLinkCodeException(th.getMessage()));
            }

            @Override // com.amazon.alexa.device.setup.echo.softap.linkcode.AuthorizationCallback
            public void authorizationSuccess() {
                observableEmitter.onNext(new WorkflowStateData(true, WorkflowStateData.State.REGISTER_DEVICE));
                observableEmitter.onComplete();
            }
        };
        if (deviceCredentials.getCodeSource() != null) {
            this.linkCodeAuthorizer.authorizeLinkCode(deviceCredentials.getOauthToken().text, this.authorizationCallback);
        } else {
            this.linkCodeAuthorizer.authorizeLinkCode(deviceCredentials.getOauthToken().text, this.softAPWifiManager.getBoundClient(), this.authorizationCallback);
        }
    }
}
