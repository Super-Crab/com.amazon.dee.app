package com.amazon.alexa.accessory.capabilities.device;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.ErrorCodeAction;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableStream;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.device.DeviceProducer;
import com.amazon.alexa.accessory.repositories.device.DeviceProvider;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* loaded from: classes.dex */
public final class DeviceCapability extends AccessoryCapability {
    private static final int PRIMARY_DEVICE_ID = 0;
    private static final String TAG = "DeviceCapability:";
    private final DeviceActionHandler actionHandler;
    private final Callback callback;
    private final DeviceProducer deviceProducer;
    private final DeviceProvider deviceProvider;
    private boolean initialDeviceInformationProvided;
    private final Set<Device.DeviceInformation> initialDeviceInformationSet;
    private final Object lock;
    private ControlStream stream;

    /* loaded from: classes.dex */
    public interface Callback {
        void onInvalidInformation();
    }

    /* loaded from: classes.dex */
    final class DeviceActionHandler implements DeviceProducer.ActionHandler {
        private final ActionQueue startSetupQueue = new ActionQueue();
        private final ActionQueue completeSetupQueue = new ActionQueue();
        private final ActionQueue overrideAssistantQueue = new ActionQueue();
        private final ActionQueue updateDeviceInformationQueue = new ActionQueue();
        private final ActionQueue getDeviceFeaturesQueue = new ActionQueue();

        DeviceActionHandler() {
        }

        void cancelAllActions() {
            this.startSetupQueue.cancelAll();
            this.completeSetupQueue.cancelAll();
            this.overrideAssistantQueue.cancelAll();
            this.updateDeviceInformationQueue.cancelAll();
            this.getDeviceFeaturesQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.device.DeviceProducer.ActionHandler
        public void handleCompleteSetup(boolean z, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(result, "result");
            this.completeSetupQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.COMPLETE_SETUP).setCompleteSetup(Device.CompleteSetup.newBuilder().setErrorCode(z ? Common.ErrorCode.SUCCESS : Common.ErrorCode.USER_CANCELLED)).mo10084build()), DeviceCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.device.DeviceProducer.ActionHandler
        public void handleOverrideAssistant(boolean z, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(result, "result");
            this.overrideAssistantQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.OVERRIDE_ASSISTANT).setOverrideAssistant(Device.OverrideAssistant.newBuilder().setErrorCode(z ? Common.ErrorCode.SUCCESS : Common.ErrorCode.USER_CANCELLED)).mo10084build()), DeviceCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.device.DeviceProducer.ActionHandler
        public void handleStartSetup(Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(result, "result");
            this.startSetupQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.START_SETUP).setStartSetup(Device.StartSetup.newBuilder()).mo10084build()), DeviceCapability.this.stream, result));
        }

        @Override // com.amazon.alexa.accessory.repositories.device.DeviceProducer.ActionHandler
        public void handleUpdateDeviceInformation(String str, int i, Producer.Result<Common.ErrorCode> result) {
            Preconditions.notNull(str, "updatedName");
            Preconditions.notNull(result, "result");
            this.updateDeviceInformationQueue.enqueue(new ErrorCodeAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.UPDATE_DEVICE_INFORMATION).setUpdateDeviceInformation(Device.UpdateDeviceInformation.newBuilder().setName(str).setDeviceId(i)).mo10084build()), DeviceCapability.this.stream, result));
        }
    }

    public DeviceCapability(DeviceProvider deviceProvider, DeviceProducer deviceProducer, Callback callback) {
        Preconditions.notNull(deviceProvider, "deviceProvider");
        Preconditions.notNull(deviceProducer, "deviceProducer");
        this.deviceProducer = deviceProducer;
        this.deviceProvider = deviceProvider;
        this.callback = callback;
        this.initialDeviceInformationSet = new HashSet();
        this.lock = new Object();
        this.actionHandler = new DeviceActionHandler();
    }

    private static void aggregateDeviceInformation(Device.DeviceInformation deviceInformation, Set<Device.DeviceInformation> set) {
        Iterator<Device.DeviceInformation> it2 = set.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Device.DeviceInformation next = it2.next();
            if (deviceInformation.getDeviceId() == next.getDeviceId()) {
                set.remove(next);
                break;
            }
        }
        set.add(deviceInformation);
    }

    private void commitInitialDeviceInformation() {
        synchronized (this.lock) {
            if (!this.initialDeviceInformationProvided) {
                this.initialDeviceInformationProvided = true;
                this.deviceProvider.provideDeviceInformationSet(this.initialDeviceInformationSet);
            } else {
                throw new IllegalStateException("Can only commit initial device info once.");
            }
        }
    }

    private ControlResponseHandler getDeviceConfigurationHandler() {
        return new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$DeviceCapability$isHsIddzejlfkQa8shDINDO9q9E
            @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
            public final void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
                DeviceCapability.this.lambda$getDeviceConfigurationHandler$5$DeviceCapability(controlStream, command, response);
            }
        };
    }

    private ControlResponseHandler getDeviceFeaturesHandler() {
        return new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$DeviceCapability$d33RochGOu6R6jZr3nAEOLqpH1Q
            @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
            public final void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
                DeviceCapability.this.lambda$getDeviceFeaturesHandler$6$DeviceCapability(controlStream, command, response);
            }
        };
    }

    private ControlMessageHandler<Device.NotifyDeviceConfiguration> getNotifyDeviceConfigurationHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$DeviceCapability$HwtwMvysDVlR9CgHdcq-b8aks-Q
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                DeviceCapability.this.lambda$getNotifyDeviceConfigurationHandler$7$DeviceCapability(controlStream, command, (Device.NotifyDeviceConfiguration) obj);
            }
        };
    }

    private ControlMessageHandler<Device.NotifyDeviceInformation> getNotifyDeviceInformationHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$DeviceCapability$SK-cmKmQryWllKTMi2yQmDsYU0o
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                DeviceCapability.this.lambda$getNotifyDeviceInformationHandler$4$DeviceCapability(controlStream, command, (Device.NotifyDeviceInformation) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Device.DeviceInformation provideDeviceInformation(Device.DeviceInformation deviceInformation) {
        synchronized (this.lock) {
            if (this.initialDeviceInformationProvided) {
                this.deviceProvider.provideDeviceInformationSet(Collections.singleton(deviceInformation));
            } else {
                aggregateDeviceInformation(deviceInformation, this.initialDeviceInformationSet);
            }
        }
        return deviceInformation;
    }

    @SuppressLint({"CheckResult"})
    private void requestAndProvideDeviceInformation() {
        requestDeviceInformation(0).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$DeviceCapability$M7Ph6sK9JthIs4z2GQqVw9tbfO0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceCapability.this.lambda$requestAndProvideDeviceInformation$1$DeviceCapability((Device.DeviceInformation) obj);
            }
        }).map($$Lambda$Xlmt0nL10_onlo2I_4ahXL4SvwI.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$DeviceCapability$p2dKmppa6dC8S_vCvBl_NpPW6MY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DeviceCapability.this.lambda$requestAndProvideDeviceInformation$2$DeviceCapability((HashSet) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$DeviceCapability$XmtKN077Q65wW9FWvZYE2d9ii6s
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DeviceCapability.this.lambda$requestAndProvideDeviceInformation$3$DeviceCapability((Throwable) obj);
            }
        });
    }

    private void requestDeviceConfiguration() {
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_DEVICE_CONFIGURATION).setGetDeviceConfiguration(Device.GetDeviceConfiguration.newBuilder()).mo10084build()));
    }

    private void requestDeviceFeatures() {
        Logger.d("%s requesting GET_DEVICE_FEATURES", TAG);
        this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_DEVICE_FEATURES).setGetDeviceFeatures(Device.GetDeviceFeatures.newBuilder()).mo10084build()));
    }

    private Single<Device.DeviceInformation> requestDeviceInformation(int i) {
        return ObservableStream.dispatchSingle(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_DEVICE_INFORMATION).setGetDeviceInformation(Device.GetDeviceInformation.newBuilder().setDeviceId(i)).mo10084build())).map($$Lambda$6fHGjtKTwhg9mS9vIfqCUjgjc0.INSTANCE).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$DeviceCapability$JZ6tL6CO4F47_XWYxnM0EdCNjW0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Device.DeviceInformation provideDeviceInformation;
                provideDeviceInformation = DeviceCapability.this.provideDeviceInformation((Device.DeviceInformation) obj);
                return provideDeviceInformation;
            }
        });
    }

    public /* synthetic */ void lambda$getDeviceConfigurationHandler$5$DeviceCapability(ControlStream controlStream, Accessories.Command command, Accessories.Response response) throws Exception {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS && response.getPayloadCase() == Accessories.Response.PayloadCase.DEVICE_CONFIGURATION) {
            this.deviceProvider.provideDeviceConfiguration(response.getDeviceConfiguration());
            return;
        }
        throw new IOException("Invalid response " + response);
    }

    public /* synthetic */ void lambda$getDeviceFeaturesHandler$6$DeviceCapability(ControlStream controlStream, Accessories.Command command, Accessories.Response response) throws Exception {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS && response.getPayloadCase() == Accessories.Response.PayloadCase.DEVICE_FEATURES) {
            this.deviceProvider.provideDeviceFeatures(response.getDeviceFeatures());
        } else if (response.getErrorCode() == Common.ErrorCode.UNSUPPORTED) {
            this.deviceProvider.provideDeviceFeatures(Device.DeviceFeatures.newBuilder().mo10084build());
        } else {
            DeviceProvider deviceProvider = this.deviceProvider;
            deviceProvider.provideDeviceFeaturesError(new IOException("Invalid response: " + response));
        }
    }

    public /* synthetic */ void lambda$getNotifyDeviceConfigurationHandler$7$DeviceCapability(ControlStream controlStream, Accessories.Command command, Device.NotifyDeviceConfiguration notifyDeviceConfiguration) throws Exception {
        this.deviceProvider.provideDeviceConfiguration(notifyDeviceConfiguration.getDeviceConfiguration());
        controlStream.respond(Accessories.Command.NOTIFY_DEVICE_CONFIGURATION, Common.ErrorCode.SUCCESS);
    }

    public /* synthetic */ void lambda$getNotifyDeviceInformationHandler$4$DeviceCapability(ControlStream controlStream, Accessories.Command command, Device.NotifyDeviceInformation notifyDeviceInformation) throws Exception {
        provideDeviceInformation(notifyDeviceInformation.getDeviceInformation());
        controlStream.respond(Accessories.Command.NOTIFY_DEVICE_INFORMATION, Common.ErrorCode.SUCCESS);
    }

    public /* synthetic */ ObservableSource lambda$null$0$DeviceCapability(Integer num) throws Throwable {
        return requestDeviceInformation(num.intValue()).toObservable();
    }

    public /* synthetic */ SingleSource lambda$requestAndProvideDeviceInformation$1$DeviceCapability(Device.DeviceInformation deviceInformation) throws Throwable {
        return Observable.just(deviceInformation).concatWith(Observable.fromIterable(deviceInformation.getAssociatedDevicesList()).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$DeviceCapability$ZQ2sb2cJXYycxMDIzT-lmArIw-g
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DeviceCapability.this.lambda$null$0$DeviceCapability((Integer) obj);
            }
        })).toList();
    }

    public /* synthetic */ void lambda$requestAndProvideDeviceInformation$2$DeviceCapability(HashSet hashSet) throws Throwable {
        commitInitialDeviceInformation();
    }

    public /* synthetic */ void lambda$requestAndProvideDeviceInformation$3$DeviceCapability(Throwable th) throws Throwable {
        Logger.e("Unable to retrieve device information for device", th);
        this.callback.onInvalidInformation();
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.Session.SESSION_RELEASED, MetricsConstants.Session.SESSION_RELEASED_REASON_INVALID_DEVICE_INFO, true, null);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.deviceProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.deviceProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addResponseHandler(Accessories.Command.GET_DEVICE_CONFIGURATION, getDeviceConfigurationHandler());
        this.stream.addResponseHandler(Accessories.Command.GET_DEVICE_FEATURES, getDeviceFeaturesHandler());
        this.stream.addMessageHandler(Accessories.Command.NOTIFY_DEVICE_CONFIGURATION, getNotifyDeviceConfigurationHandler());
        this.stream.addMessageHandler(Accessories.Command.NOTIFY_DEVICE_INFORMATION, getNotifyDeviceInformationHandler());
        accessoryDescriptor.add(this.stream);
        requestAndProvideDeviceInformation();
        requestDeviceConfiguration();
        requestDeviceFeatures();
    }
}
