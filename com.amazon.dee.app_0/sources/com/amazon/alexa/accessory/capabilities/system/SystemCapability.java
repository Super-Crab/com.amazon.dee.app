package com.amazon.alexa.accessory.capabilities.system;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.davs.DavsClient;
import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.davs.DeviceArtifactsRequest;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.ErrorCodeAction;
import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.internal.repositories.MaybeResult;
import com.amazon.alexa.accessory.internal.util.Int64Util;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableStream;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import com.amazon.alexa.accessory.repositories.system.SystemProducer;
import com.amazon.alexa.accessory.repositories.system.SystemProvider;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes.dex */
public final class SystemCapability extends AccessoryCapability {
    public static final String TAG = "SystemCapability:";
    private final SystemActionHandler actionHandler;
    private final Callback callback;
    private final DavsClient davsClient;
    private final DeviceRepositoryV2 deviceRepositoryV2;
    private Disposable i18nConfigDisposable;
    private ControlStream stream;
    private final SynchronizeSettingsTask synchronizeSettingsTask;
    private final SystemProducer systemProducer;
    private final SystemProvider systemProvider;
    private final TaskManager taskManager;

    /* loaded from: classes.dex */
    public interface Callback {
        void onResetConnection(int i, boolean z, System.ResetConnection.ResetReason resetReason);
    }

    /* loaded from: classes.dex */
    final class SynchronizeSettingsTask implements TaskManager.Task {
        SynchronizeSettingsTask() {
        }

        private void synchronize() {
            long currentTimeMillis = System.currentTimeMillis();
            SystemCapability.this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SYNCHRONIZE_SETTINGS).setSynchronizeSettings(System.SynchronizeSettings.newBuilder().setTimestampHi(Int64Util.getHigh32(currentTimeMillis)).setTimestampLo(Int64Util.getLow32(currentTimeMillis))).mo10084build()));
            SystemCapability.this.taskManager.dispose(this);
        }

        @Override // com.amazon.alexa.accessory.TaskManager.Task
        public void onStateChanged(int i, int i2) {
            if (i2 == 3) {
                synchronize();
            }
        }
    }

    /* loaded from: classes.dex */
    final class SystemActionHandler implements SystemProducer.ActionHandler {
        private final ActionQueue switchUserQueue = new ActionQueue();
        private final ActionQueue getCurrentUserQueue = new ActionQueue();
        private final ActionQueue getUsersQueue = new ActionQueue();
        private final ActionQueue removeDeviceQueue = new ActionQueue();
        private final ActionQueue resetConnectionQueue = new ActionQueue();
        private final ActionQueue setLocaleQueue = new ActionQueue();
        private final ActionQueue connectUserQueue = new ActionQueue();
        private final ActionQueue disconnectUserQueue = new ActionQueue();
        private final ActionQueue unpairUserQueue = new ActionQueue();

        SystemActionHandler() {
        }

        void cancelAllActions() {
            this.switchUserQueue.cancelAll();
            this.getCurrentUserQueue.cancelAll();
            this.getUsersQueue.cancelAll();
            this.removeDeviceQueue.cancelAll();
            this.resetConnectionQueue.cancelAll();
            this.setLocaleQueue.cancelAll();
            this.connectUserQueue.cancelAll();
            this.disconnectUserQueue.cancelAll();
            this.unpairUserQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleConnectUser(String str, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notEmpty(str, "address");
            Preconditions.notNull(result, "result");
            this.connectUserQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.CONNECT_USER).setConnectUser(System.ConnectUser.newBuilder().setAddress(str)).mo10084build()), SystemCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleDisconnectUser(String str, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notEmpty(str, "address");
            Preconditions.notNull(result, "result");
            this.disconnectUserQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.DISCONNECT_USER).setDisconnectUser(System.DisconnectUser.newBuilder().setAddress(str)).mo10084build()), SystemCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleGetCurrentUser(Producer.Result<System.User> result) {
            Preconditions.notNull(result, "result");
            this.getCurrentUserQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_CURRENT_USER).setGetCurrentUser(System.GetCurrentUser.newBuilder()).mo10084build()), SystemCapability.this.stream, result, Accessories.Response.PayloadCase.USER, $$Lambda$fbErQhWhtiNBniuTuBtUVkmXaCM.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleGetI18nConfig(Producer.Result<MaybeResult.MaybeValue<DavsI18nConfig>> result) {
            Preconditions.notNull(result, "result");
            SystemCapability.this.requestAndProvideI18nConfig(result);
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleGetUsers(Producer.Result<System.Users> result) {
            Preconditions.notNull(result, "result");
            this.getUsersQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_USERS).setGetUsers(System.GetUsers.newBuilder()).mo10084build()), SystemCapability.this.stream, result, Accessories.Response.PayloadCase.USERS, $$Lambda$PNIe6drPXe1fYSBuO5kXuImhMU.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleRemoveDevice(Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(result, "result");
            this.removeDeviceQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.REMOVE_DEVICE).setRemoveDevice(System.RemoveDevice.getDefaultInstance()).mo10084build()), SystemCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleResetConnection(int i, boolean z, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNegative(i, "timeout");
            Preconditions.notNull(result, "result");
            this.resetConnectionQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.RESET_CONNECTION).setResetConnection(System.ResetConnection.newBuilder().setTimeout(i).setForceDisconnect(z)).mo10084build()), SystemCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleSetLocale(System.Locale locale, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(locale, "locale");
            Preconditions.notNull(result, "result");
            this.setLocaleQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SET_LOCALE).setSetLocale(System.SetLocale.newBuilder().setLocale(locale)).mo10084build()), SystemCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleSwitchUser(int i, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(Integer.valueOf(i), "newUser");
            Preconditions.notNull(result, "result");
            this.switchUserQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SWITCH_CURRENT_USER).setSwitchCurrentUser(System.SwitchCurrentUser.newBuilder().setUserId(i)).mo10084build()), SystemCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.system.SystemProducer.ActionHandler
        public void handleUnpairUser(String str, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notEmpty(str, "address");
            Preconditions.notNull(result, "result");
            this.unpairUserQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.UNPAIR_USER).setUnpairUser(System.UnpairUser.newBuilder().setAddress(str)).mo10084build()), SystemCapability.this.stream, result));
        }
    }

    public SystemCapability(Callback callback, TaskManager taskManager, SystemProducer systemProducer, SystemProvider systemProvider, DavsClient davsClient, DeviceRepositoryV2 deviceRepositoryV2) {
        Preconditions.notNull(systemProducer, "systemProducer");
        Preconditions.notNull(taskManager, "taskManager");
        Preconditions.notNull(callback, "callback");
        Preconditions.notNull(systemProvider, "systemProvider");
        Preconditions.notNull(davsClient, "davsClient");
        Preconditions.notNull(deviceRepositoryV2, "deviceRepositoryV2");
        this.systemProducer = systemProducer;
        this.taskManager = taskManager;
        this.callback = callback;
        this.systemProvider = systemProvider;
        this.davsClient = davsClient;
        this.deviceRepositoryV2 = deviceRepositoryV2;
        this.synchronizeSettingsTask = new SynchronizeSettingsTask();
        this.actionHandler = new SystemActionHandler();
    }

    private Maybe<String> getI18nArtifactName() {
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_DEVICE_ARTIFACTS).setGetDeviceArtifacts(Firmware.GetDeviceArtifacts.newBuilder().setArtifactRequestReason(Firmware.ArtifactRequestReason.I18N_REQUEST)).mo10084build())).filter($$Lambda$SystemCapability$8R79GQKrR8MVp0zZbsECVU1Ckg.INSTANCE).map($$Lambda$SystemCapability$bp7oD1TXQJ6qaFEpLCCTpxZDRUU.INSTANCE);
    }

    private ControlMessageHandler<System.ResetConnection> getResetConnectionHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$SystemCapability$w9-84LSXsLKlnM7MDyGIrbMJqtM
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                SystemCapability.this.lambda$getResetConnectionHandler$7$SystemCapability(controlStream, command, (System.ResetConnection) obj);
            }
        };
    }

    private ControlMessageHandler<System.UpdateUsers> getUpdateUsersHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$SystemCapability$X4DrykfrVqD77p6Uaqy34B5KSjY
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                SystemCapability.this.lambda$getUpdateUsersHandler$8$SystemCapability(controlStream, command, (System.UpdateUsers) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$getI18nArtifactName$10(Accessories.Response response) throws Throwable {
        Logger.d("%s getDeviceArtifacts response for I18N_REQUEST: %s", TAG, response.getErrorCode());
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            return response.getArtifactList().getArtifactNameList().get(0);
        }
        recordI18nConfigMetric(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("GetDeviceArtifactsAccessoryResponse:")), true);
        throw new Exception(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Response for GET_DEVICE_ARTIFACTS failed with error code in SystemCapability: ")));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getI18nArtifactName$9(Accessories.Response response) throws Throwable {
        return response.getErrorCode() != Common.ErrorCode.UNSUPPORTED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FirmwareContract.ArtifactFilter lambda$null$0(Accessories.Response response) throws Throwable {
        try {
            return (FirmwareContract.ArtifactFilter) new Gson().fromJson(response.getArtifactFilter().getArtifactFilter(), (Class<Object>) FirmwareContract.ArtifactFilter.class);
        } catch (Exception e) {
            recordI18nConfigMetric(MetricsConstants.DAVS.GET_ARTIFACT_FILTER_PARSING_ERROR, true);
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$requestAndProvideI18nConfig$4(Producer.Result result, DavsI18nConfig davsI18nConfig) throws Throwable {
        recordI18nConfigMetric(MetricsConstants.DAVS.I18N_CONFIG_ACCESSORY_SUCCESS, true);
        result.complete(MaybeResult.MaybeValue.of(davsI18nConfig));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$requestAndProvideI18nConfig$5(Producer.Result result, Throwable th) throws Throwable {
        recordI18nConfigMetric(MetricsConstants.DAVS.I18N_CONFIG_ACCESSORY_SUCCESS, false);
        Logger.e("%s Error while fetching I18n config", th, TAG);
        result.completeWithError(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$requestAndProvideI18nConfig$6(Producer.Result result) throws Throwable {
        Logger.i("%s No I18n Config present for Accessory.", TAG);
        result.complete(MaybeResult.MaybeValue.empty());
    }

    private static void recordI18nConfigMetric(String str, boolean z) {
        recordOccurrence(str, MetricsConstants.DAVS.I18N_CONFIG, z);
    }

    private static void recordOccurrence(String str, String str2, boolean z) {
        GeneratedOutlineSupport1.outline171(str, str2, z, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestAndProvideI18nConfig(final Producer.Result<MaybeResult.MaybeValue<DavsI18nConfig>> result) {
        this.i18nConfigDisposable = getI18nArtifactName().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$SystemCapability$KwHBa-luZwcF5iLXe83f44Wt7sY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return SystemCapability.this.lambda$requestAndProvideI18nConfig$1$SystemCapability((String) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$SystemCapability$Y3SIV2Wmjz0O7PZqW9xSwelvscU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return SystemCapability.this.lambda$requestAndProvideI18nConfig$3$SystemCapability((FirmwareContract.ArtifactFilter) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$SystemCapability$kcSo5xPnox-3LbGu7aaEMLKb388
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SystemCapability.lambda$requestAndProvideI18nConfig$4(Producer.Result.this, (DavsI18nConfig) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$SystemCapability$uWF7yYj-J12uEJoyv9_kR47t8vQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SystemCapability.lambda$requestAndProvideI18nConfig$5(Producer.Result.this, (Throwable) obj);
            }
        }, new Action() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$SystemCapability$Qgr8wDl9xLQ5uWisYWOqCKva64E
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                SystemCapability.lambda$requestAndProvideI18nConfig$6(Producer.Result.this);
            }
        });
    }

    @SuppressLint({"CheckResult"})
    private void requestAndProvideLocales() {
        Single<R> map = ObservableStream.dispatchSingle(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_LOCALES).setGetLocales(System.GetLocales.getDefaultInstance()).mo10084build())).map($$Lambda$EUvIBqh0MD2_LBZIOxKP7Ob4jvE.INSTANCE);
        final SystemProvider systemProvider = this.systemProvider;
        systemProvider.getClass();
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$pfxXpk9JwZ5xvckFH3N6HKYeXr4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SystemProvider.this.provideLocales((System.Locales) obj);
            }
        };
        final SystemProvider systemProvider2 = this.systemProvider;
        systemProvider2.getClass();
        map.subscribe(consumer, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$0aA5UfudtBL7mHF5mVBjYOXsfX8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SystemProvider.this.provideLocalesError((Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$getResetConnectionHandler$7$SystemCapability(ControlStream controlStream, Accessories.Command command, System.ResetConnection resetConnection) throws Exception {
        this.callback.onResetConnection(resetConnection.getTimeout(), resetConnection.getForceDisconnect(), resetConnection.getResetReason());
        controlStream.respond(Accessories.Command.RESET_CONNECTION, Common.ErrorCode.SUCCESS);
    }

    public /* synthetic */ void lambda$getUpdateUsersHandler$8$SystemCapability(ControlStream controlStream, Accessories.Command command, System.UpdateUsers updateUsers) throws Exception {
        this.systemProvider.provideUsers(updateUsers.getUsers());
        controlStream.respond(Accessories.Command.UPDATE_USERS, Common.ErrorCode.SUCCESS);
    }

    public /* synthetic */ SingleSource lambda$null$2$SystemCapability(DeviceArtifactsRequest deviceArtifactsRequest, Device.DeviceInformation deviceInformation) throws Throwable {
        return this.davsClient.getDavsI18nConfig(deviceArtifactsRequest, deviceInformation.getDeviceType(), deviceInformation.getSerialNumber());
    }

    public /* synthetic */ MaybeSource lambda$requestAndProvideI18nConfig$1$SystemCapability(String str) throws Throwable {
        return ObservableStream.dispatchSingle(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_ARTIFACT_FILTER).setGetArtifactFilter(Firmware.GetArtifactFilter.newBuilder().setArtifactName(str)).mo10084build())).map($$Lambda$SystemCapability$ESvIFieuZvTk1kGlQNHOTgat2tM.INSTANCE).toMaybe();
    }

    public /* synthetic */ MaybeSource lambda$requestAndProvideI18nConfig$3$SystemCapability(FirmwareContract.ArtifactFilter artifactFilter) throws Throwable {
        final DeviceArtifactsRequest build = new DeviceArtifactsRequest.Builder().artifactKey(artifactFilter.getArtifactKey()).artifactType(artifactFilter.getArtifactType()).filters(artifactFilter.getFilters()).build();
        return this.deviceRepositoryV2.queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.system.-$$Lambda$SystemCapability$0nbS0XNadAaoK1348TmSix-9t6M
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return SystemCapability.this.lambda$null$2$SystemCapability(build, (Device.DeviceInformation) obj);
            }
        }).toMaybe();
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.systemProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
        this.taskManager.dispose(this.synchronizeSettingsTask);
        ObservableUtils.dispose(this.i18nConfigDisposable);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.systemProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addMessageHandler(Accessories.Command.RESET_CONNECTION, getResetConnectionHandler());
        this.stream.addMessageHandler(Accessories.Command.UPDATE_USERS, getUpdateUsersHandler());
        accessoryDescriptor.add(this.stream);
        this.taskManager.schedule(this.synchronizeSettingsTask, 4);
        requestAndProvideLocales();
    }
}
