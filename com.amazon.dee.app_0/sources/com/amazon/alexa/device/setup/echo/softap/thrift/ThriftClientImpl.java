package com.amazon.alexa.device.setup.echo.softap.thrift;

import android.util.Log;
import com.amazon.alexa.device.setup.echo.softap.encrypt.Encryptor;
import com.amazon.alexa.device.setup.echo.softap.linkcode.PreAuthorizedLinkCode;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.CompleteSetupException;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.ConnectToAPException;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.EvaluateCaptivePortalException;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.GetLinkCodeException;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.IncorrectPasswordException;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.NonStandardNetworkException;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.SetLocaleAndEndpointsException;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.ThriftRequestException;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.VerifyAlexaConnectionException;
import com.amazon.alexa.device.setup.echo.softap.thrift.exception.VerifyDeviceRegistrationException;
import com.amazon.alexa.device.setup.echo.softap.wifi.SoftAPWifiManager;
import com.amazon.alexa.device.setup.echo.softap.workflow.data.WorkflowRequestError;
import com.amazon.alexa.device.setup.echo.softap.workflow.data.WorkflowStateData;
import com.amazon.device.setup.thrift.APConnectExtendedInfo;
import com.amazon.device.setup.thrift.APConnectInfo;
import com.amazon.device.setup.thrift.APDetail;
import com.amazon.device.setup.thrift.AlexaConnectionState;
import com.amazon.device.setup.thrift.DeviceCredentials;
import com.amazon.device.setup.thrift.DeviceDetails;
import com.amazon.device.setup.thrift.DopplerOOBE;
import com.amazon.device.setup.thrift.LocaleAndEndpointInfo;
import com.amazon.device.setup.thrift.OTADetails;
import com.amazon.device.setup.thrift.PKCS7Type;
import com.amazon.device.setup.thrift.RegistrationState;
import com.amazon.device.setup.thrift.ReturnError;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.here.sdk.search.PlaceCategory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.orig.TException;
import org.apache.thrift.orig.transport.TTransportException;
/* loaded from: classes6.dex */
public class ThriftClientImpl implements ThriftClient {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int MAX_RETRIES = 5;
    private static final int READ_TIMEOUT = 10000;
    private static final String TAG = "ThriftClientImpl";
    private DopplerOOBE.Client client;
    private static String[] urls = {"http://10.201.126.241:8080/OOBE", "http://192.168.11.1:8080/OOBE"};
    private static String SELECTED_URL = urls[0];

    public ThriftClientImpl() {
        if (this.client == null) {
            this.client = DopplerOOBEClientFactory.getInitialEchoThriftClient(SELECTED_URL);
        }
    }

    private DopplerOOBE.Client getEchoThriftClientSwitchUrls() {
        SELECTED_URL = SELECTED_URL.equals(urls[0]) ? urls[1] : urls[0];
        return DopplerOOBEClientFactory.getEchoThriftClient(SELECTED_URL, 10000, 10000);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ WorkflowRequestError lambda$null$13(Throwable th, Integer num) throws Throwable {
        return new WorkflowRequestError(th, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$null$14(WorkflowRequestError workflowRequestError) throws Throwable {
        if (!(workflowRequestError.getThrowable() instanceof TTransportException)) {
            if (workflowRequestError.getAttempt() == 5 && (workflowRequestError.getThrowable() instanceof VerifyDeviceRegistrationException)) {
                throw new VerifyDeviceRegistrationException(((ThriftRequestException) workflowRequestError.getThrowable()).getErrorStatus());
            }
            return Observable.timer(1L, TimeUnit.SECONDS);
        }
        throw new TTransportException(workflowRequestError.getThrowable());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ WorkflowRequestError lambda$null$18(Throwable th, Integer num) throws Throwable {
        return new WorkflowRequestError(th, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$null$19(WorkflowRequestError workflowRequestError) throws Throwable {
        if (!(workflowRequestError.getThrowable() instanceof TTransportException)) {
            if (workflowRequestError.getAttempt() == 5 && (workflowRequestError.getThrowable() instanceof VerifyAlexaConnectionException)) {
                throw new VerifyAlexaConnectionException(((ThriftRequestException) workflowRequestError.getThrowable()).getErrorStatus());
            }
            return Observable.timer(1L, TimeUnit.SECONDS);
        }
        throw new TTransportException(workflowRequestError.getThrowable());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ WorkflowRequestError lambda$null$23(Throwable th, Integer num) throws Throwable {
        return new WorkflowRequestError(th, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$null$24(WorkflowRequestError workflowRequestError) throws Throwable {
        if (!(workflowRequestError.getThrowable() instanceof TTransportException)) {
            if (workflowRequestError.getAttempt() == 5 && (workflowRequestError.getThrowable() instanceof CompleteSetupException)) {
                throw new CompleteSetupException(workflowRequestError.getThrowable().getMessage());
            }
            return Observable.timer(1L, TimeUnit.SECONDS);
        }
        throw new TTransportException(workflowRequestError.getThrowable());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ WorkflowRequestError lambda$null$4(Throwable th, Integer num) throws Throwable {
        return new WorkflowRequestError(th, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$null$5(WorkflowRequestError workflowRequestError) throws Throwable {
        if (!(workflowRequestError.getThrowable() instanceof TTransportException)) {
            if (!(workflowRequestError.getThrowable() instanceof NonStandardNetworkException)) {
                if (!(workflowRequestError.getThrowable() instanceof ConnectToAPException)) {
                    if (!(workflowRequestError.getThrowable() instanceof IncorrectPasswordException)) {
                        return Observable.timer(1L, TimeUnit.SECONDS);
                    }
                    throw new IncorrectPasswordException(((ThriftRequestException) workflowRequestError.getThrowable()).getErrorStatus());
                }
                throw new ConnectToAPException(((ThriftRequestException) workflowRequestError.getThrowable()).getErrorStatus());
            }
            throw new NonStandardNetworkException(workflowRequestError.getThrowable().getMessage());
        }
        throw new TTransportException(workflowRequestError.getThrowable());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ WorkflowRequestError lambda$null$8(Throwable th, Integer num) throws Throwable {
        return new WorkflowRequestError(th, num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ObservableSource lambda$null$9(WorkflowRequestError workflowRequestError) throws Throwable {
        if (workflowRequestError.getAttempt() != 5) {
            return Observable.timer(1L, TimeUnit.SECONDS);
        }
        throw new ConnectToAPException(workflowRequestError.getThrowable().getMessage());
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> completeSetup() {
        return Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$ajlZHhXtm_6ZxTEAMFj_4G90wUM
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ThriftClientImpl.this.lambda$completeSetup$22$ThriftClientImpl();
            }
        }).retryWhen($$Lambda$ThriftClientImpl$gQhJLP7o9L5o6C_uVl1aL3zlHI.INSTANCE);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> connectToAP(final APConnectInfo aPConnectInfo) {
        DopplerOOBEClientFactory.setTimeout(HttpUrlConnectionNetworkFetcher.HTTP_DEFAULT_TIMEOUT);
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$tdzYHbGCOMgg6ALyzf3tE2ULbnk
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                ThriftClientImpl.this.lambda$connectToAP$3$ThriftClientImpl(aPConnectInfo, observableEmitter);
            }
        }).retryWhen($$Lambda$ThriftClientImpl$Drt63761CeTLkbMLn7oJ5AY3neI.INSTANCE);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> evaluateCaptivePortal() {
        return Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$5UiUNFh3wNLHJ2LZ4G66w1yq_RY
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ThriftClientImpl.this.lambda$evaluateCaptivePortal$21$ThriftClientImpl();
            }
        }).retry();
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> getDeviceDetails() {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$_sfj7g9bnxtqQysFW7aTIvktxTY
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                ThriftClientImpl.this.lambda$getDeviceDetails$1$ThriftClientImpl(observableEmitter);
            }
        }).retry(3L);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<List<APDetail>> getKnownList() {
        return Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$r5R-oR0tqK-tOTk7mq4yBdqp93E
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ThriftClientImpl.this.lambda$getKnownList$27$ThriftClientImpl();
            }
        }).retry(3L);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> getLinkCode() {
        return Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$MtC1BvaLMy9rHSB6E7ILoYCVPCo
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ThriftClientImpl.this.lambda$getLinkCode$11$ThriftClientImpl();
            }
        }).retry(3L);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> getOtaDetails() {
        return Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$NEPyd6eGRt0pqAOp40T726aorx0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ThriftClientImpl.this.lambda$getOtaDetails$16$ThriftClientImpl();
            }
        }).retry(3L);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<List<APDetail>> getScanList() {
        return Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$2oQp3gWOzqTKYoVCzxkXYsetI_0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ThriftClientImpl.this.lambda$getScanList$26$ThriftClientImpl();
            }
        }).retry(3L);
    }

    public /* synthetic */ WorkflowStateData lambda$completeSetup$22$ThriftClientImpl() throws Exception {
        try {
            ReturnError returnError = this.client.setupComplete();
            String str = "completeSetup: " + returnError;
            if (returnError.equals(ReturnError.NO_ERROR)) {
                return new WorkflowStateData(returnError, WorkflowStateData.State.COMPLETE_SETUP);
            }
            throw new CompleteSetupException(returnError.toString());
        } catch (TException e) {
            Log.e(TAG, e.toString());
            throw e;
        }
    }

    public /* synthetic */ void lambda$connectToAP$3$ThriftClientImpl(APConnectInfo aPConnectInfo, ObservableEmitter observableEmitter) throws Throwable {
        try {
            ReturnError connectToAP = this.client.connectToAP(aPConnectInfo);
            String str = "connectToAP: " + connectToAP.toString();
            if (connectToAP.equals(ReturnError.NO_ERROR)) {
                observableEmitter.onNext(new WorkflowStateData(connectToAP, WorkflowStateData.State.CONNECT_TO_AP));
                observableEmitter.onComplete();
            } else if (connectToAP != ReturnError.CAPTIVE_PORTAL_ERROR) {
                if (connectToAP == ReturnError.DELAYED_CONNECTION_STARTED) {
                    throw new NonStandardNetworkException("HOT_SPOT");
                }
                throw new ConnectToAPException(connectToAP.toString());
            } else {
                throw new NonStandardNetworkException("CAPTIVE_PORTAL");
            }
        } catch (TException e) {
            Log.e(TAG, e.toString());
            if (!e.getMessage().contains(PlaceCategory.ACCOMODATION)) {
                if (e.getMessage().contains("timeout")) {
                    throw new ConnectToAPException("Timed out connecting to network.");
                }
                throw e;
            }
            throw new IncorrectPasswordException("Incorrect Password.");
        }
    }

    public /* synthetic */ WorkflowStateData lambda$connectToAP$7$ThriftClientImpl(APConnectInfo aPConnectInfo, DeviceDetails deviceDetails, APConnectExtendedInfo aPConnectExtendedInfo) throws Exception {
        try {
            aPConnectInfo.setPassword(Encryptor.encrypt(aPConnectInfo.password.text, deviceDetails.deviceCertificate.pem_text));
            return new WorkflowStateData(this.client.connectToAPEx(aPConnectInfo, aPConnectExtendedInfo), WorkflowStateData.State.CONNECT_TO_AP);
        } catch (TException e) {
            Log.e(TAG, e.toString());
            throw e;
        }
    }

    public /* synthetic */ WorkflowStateData lambda$evaluateCaptivePortal$21$ThriftClientImpl() throws Exception {
        try {
            ReturnError evaluateCaptivePortal = this.client.evaluateCaptivePortal();
            String str = "evaluateCaptivePortal: " + evaluateCaptivePortal;
            if (evaluateCaptivePortal.equals(ReturnError.NO_ERROR)) {
                return new WorkflowStateData(evaluateCaptivePortal, WorkflowStateData.State.EVALUATE_CAPTIVE_PORTAL);
            }
            throw new EvaluateCaptivePortalException(evaluateCaptivePortal.toString());
        } catch (TException e) {
            Log.e(TAG, e.toString());
            throw e;
        }
    }

    public /* synthetic */ void lambda$getDeviceDetails$1$ThriftClientImpl(ObservableEmitter observableEmitter) throws Throwable {
        try {
            DeviceDetails deviceDetails = this.client.getDeviceDetails();
            String str = "deviceDetails: " + deviceDetails;
            WorkflowStateData workflowStateData = new WorkflowStateData(deviceDetails, WorkflowStateData.State.GET_DEVICE_DETAILS);
            if (observableEmitter.isDisposed()) {
                return;
            }
            observableEmitter.onNext(workflowStateData);
            observableEmitter.onComplete();
        } catch (TException e) {
            Log.e(TAG, e.toString());
            if (!observableEmitter.isDisposed()) {
                throw e;
            }
        }
    }

    public /* synthetic */ List lambda$getKnownList$27$ThriftClientImpl() throws Exception {
        List<APDetail> knownList;
        try {
            String str = "knownList: " + knownList.toString();
            return this.client.getKnownList();
        } catch (TException e) {
            Log.e(TAG, e.toString());
            throw e;
        }
    }

    public /* synthetic */ WorkflowStateData lambda$getLinkCode$11$ThriftClientImpl() throws Exception {
        DeviceCredentials linkCode;
        try {
            String str = "deviceCredentials: " + linkCode.toString();
            return new WorkflowStateData(this.client.getLinkCode(), WorkflowStateData.State.GET_LINK_CODE);
        } catch (TException e) {
            Log.e(TAG, e.toString());
            throw new GetLinkCodeException("Failed to fetch link code.");
        }
    }

    public /* synthetic */ WorkflowStateData lambda$getOtaDetails$16$ThriftClientImpl() throws Exception {
        OTADetails oTADetails;
        try {
            String str = "otaDetails: " + oTADetails.toString();
            return new WorkflowStateData(this.client.getOTADetails(), WorkflowStateData.State.GET_OTA_DETAILS);
        } catch (TException e) {
            Log.e(TAG, e.toString());
            return new WorkflowStateData("", WorkflowStateData.State.GET_OTA_DETAILS);
        }
    }

    public /* synthetic */ List lambda$getScanList$26$ThriftClientImpl() throws Exception {
        List<APDetail> scanList;
        try {
            String str = "scanList: " + scanList.toString();
            return this.client.getScanList();
        } catch (TException e) {
            Log.e(TAG, e.toString());
            throw e;
        }
    }

    public /* synthetic */ void lambda$ping$0$ThriftClientImpl(SoftAPWifiManager softAPWifiManager, ObservableEmitter observableEmitter) throws Throwable {
        softAPWifiManager.bindToEchoNetwork();
        this.client = getEchoThriftClientSwitchUrls();
        try {
            WorkflowStateData workflowStateData = new WorkflowStateData(Boolean.valueOf(this.client.ping()), WorkflowStateData.State.CONNECTED);
            if (observableEmitter.isDisposed()) {
                return;
            }
            observableEmitter.onNext(workflowStateData);
            observableEmitter.onComplete();
        } catch (TException e) {
            if (observableEmitter.isDisposed()) {
                return;
            }
            Log.e(TAG, e.toString());
            throw e;
        }
    }

    public /* synthetic */ void lambda$setLocaleAndEndpointInfo$2$ThriftClientImpl(LocaleAndEndpointInfo localeAndEndpointInfo, ObservableEmitter observableEmitter) throws Throwable {
        try {
            ReturnError localeAndEndpoints = this.client.setLocaleAndEndpoints(localeAndEndpointInfo);
            String str = "setLocaleAndEndpoints: " + localeAndEndpoints;
            if (localeAndEndpoints.equals(ReturnError.NO_ERROR)) {
                WorkflowStateData workflowStateData = new WorkflowStateData(localeAndEndpoints, WorkflowStateData.State.SET_LOCALE_AND_ENDPOINT);
                if (observableEmitter.isDisposed()) {
                    return;
                }
                observableEmitter.onNext(workflowStateData);
                observableEmitter.onComplete();
                return;
            }
            throw new SetLocaleAndEndpointsException(localeAndEndpoints.toString());
        } catch (TException e) {
            Log.e(TAG, e.toString());
            if (observableEmitter.isDisposed()) {
                return;
            }
            if (e.getMessage().contains(PlaceCategory.ACCOMODATION)) {
                throw new SetLocaleAndEndpointsException(e.toString());
            }
            throw e;
        }
    }

    public /* synthetic */ void lambda$verifyAlexaConnectionState$17$ThriftClientImpl(ObservableEmitter observableEmitter) throws Throwable {
        try {
            AlexaConnectionState alexaConnectionState = this.client.getAlexaConnectionState();
            String str = "alexaConnectionState: " + alexaConnectionState.toString();
            if (alexaConnectionState.equals(AlexaConnectionState.CONNECTED)) {
                observableEmitter.onNext(new WorkflowStateData(alexaConnectionState, WorkflowStateData.State.VERIFY_ALEXA_SERVICE_CONNECTION));
                observableEmitter.onComplete();
                return;
            }
            throw new VerifyAlexaConnectionException(alexaConnectionState.toString());
        } catch (TException e) {
            Log.e(TAG, e.toString());
            throw e;
        }
    }

    public /* synthetic */ void lambda$verifyDeviceKnowsItIsRegistered$12$ThriftClientImpl(ObservableEmitter observableEmitter) throws Throwable {
        try {
            RegistrationState registrationState = this.client.getRegistrationState();
            String str = "registrationState: " + registrationState.toString();
            if (registrationState.equals(RegistrationState.REGISTERED)) {
                observableEmitter.onNext(new WorkflowStateData(registrationState, WorkflowStateData.State.VERIFY_DEVICE_REGISTRATION));
                observableEmitter.onComplete();
                return;
            }
            throw new VerifyDeviceRegistrationException(registrationState.toString());
        } catch (TException e) {
            Log.e(TAG, e.toString());
            throw e;
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> ping(final SoftAPWifiManager softAPWifiManager) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$0On9ot9gX2w5dvvm56NXmSnM6VI
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                ThriftClientImpl.this.lambda$ping$0$ThriftClientImpl(softAPWifiManager, observableEmitter);
            }
        }).delay(1L, TimeUnit.SECONDS).retry();
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> setLocaleAndEndpointInfo(final LocaleAndEndpointInfo localeAndEndpointInfo) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$i007D7uNookTsX-sqkUH0UWUkNk
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                ThriftClientImpl.this.lambda$setLocaleAndEndpointInfo$2$ThriftClientImpl(localeAndEndpointInfo, observableEmitter);
            }
        }).retry(3L);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> verifyAlexaConnectionState() {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$Ijn3kDqMsJZXd1Zpzm8yW40qr_U
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                ThriftClientImpl.this.lambda$verifyAlexaConnectionState$17$ThriftClientImpl(observableEmitter);
            }
        }).retryWhen($$Lambda$ThriftClientImpl$rirodOjAMDkJ1oPRAqNT4W6ZmI.INSTANCE);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> verifyDeviceKnowsItIsRegistered() {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$Sw06HNW6MuXaGG2XKMkElVMt-Eo
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                ThriftClientImpl.this.lambda$verifyDeviceKnowsItIsRegistered$12$ThriftClientImpl(observableEmitter);
            }
        }).retryWhen($$Lambda$ThriftClientImpl$oqd3Cbz4JGUkgWHHhXG1JZm4yn0.INSTANCE);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> connectToAP(APConnectInfo aPConnectInfo, DeviceDetails deviceDetails, PreAuthorizedLinkCode preAuthorizedLinkCode) {
        if (aPConnectInfo.isTetheredNetwork) {
            aPConnectInfo.setPreauthCode(Encryptor.encrypt(preAuthorizedLinkCode.preAuthorizedLinkCode, deviceDetails.deviceCertificate.pem_text));
            aPConnectInfo.setIsTetheredNetwork(true);
        }
        PKCS7Type pKCS7Type = aPConnectInfo.password;
        if (pKCS7Type != null) {
            aPConnectInfo.setPassword(Encryptor.encrypt(pKCS7Type.text, deviceDetails.deviceCertificate.pem_text));
        }
        return connectToAP(aPConnectInfo);
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.thrift.ThriftClient
    public Observable<WorkflowStateData> connectToAP(final APConnectInfo aPConnectInfo, final APConnectExtendedInfo aPConnectExtendedInfo, final DeviceDetails deviceDetails) {
        DopplerOOBEClientFactory.setTimeout(HttpUrlConnectionNetworkFetcher.HTTP_DEFAULT_TIMEOUT);
        return Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.device.setup.echo.softap.thrift.-$$Lambda$ThriftClientImpl$oxGTuHg5Xm21n8RrP9Tdgyo7iRw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ThriftClientImpl.this.lambda$connectToAP$7$ThriftClientImpl(aPConnectInfo, deviceDetails, aPConnectExtendedInfo);
            }
        }).retryWhen($$Lambda$ThriftClientImpl$JaJ4cOoP_ReiDNfnC59taDRDpXk.INSTANCE);
    }
}
