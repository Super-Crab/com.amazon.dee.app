package com.amazon.alexa.accessory.internal.connection;

import android.annotation.SuppressLint;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.capabilities.metrics.SpeechProcessingMetricsReporter;
import com.amazon.alexa.accessory.internal.session.AccessorySessionType;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.LoggerUtils;
import com.amazon.alexa.accessory.internal.util.MetricsUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.functions.Supplier;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.io.SourceInputStream;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import com.amazon.alexa.accessory.transport.TransportReceiver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public final class UnsupportedTransportReceiver implements TransportReceiver {
    @VisibleForTesting
    static final int PACKET_DROP_METRIC_LIMIT = 1000;
    private final AccessorySessionType accessorySessionType;
    private final Supplier<DeviceRepositoryV2> deviceRepositorySupplier;
    private final TransportDispatcher dispatcher;
    private final ControlStream.MessageAuthenticationMode mode;
    private AtomicInteger packetDroppedMetricCount = new AtomicInteger(0);
    private final Set<Accessories.Command> speechCommandSet;

    public UnsupportedTransportReceiver(TransportDispatcher transportDispatcher, ControlStream.MessageAuthenticationMode messageAuthenticationMode, Supplier<DeviceRepositoryV2> supplier, AccessorySessionType accessorySessionType) {
        Preconditions.notNull(transportDispatcher, "dispatcher");
        Preconditions.notNull(messageAuthenticationMode, "mode");
        Preconditions.notNull(supplier, "deviceRepositorySupplier");
        Preconditions.notNull(accessorySessionType, "accessorySessionType");
        this.dispatcher = transportDispatcher;
        this.mode = messageAuthenticationMode;
        this.deviceRepositorySupplier = supplier;
        this.accessorySessionType = accessorySessionType;
        this.speechCommandSet = new HashSet();
        this.speechCommandSet.add(Accessories.Command.START_SPEECH);
        this.speechCommandSet.add(Accessories.Command.PROVIDE_SPEECH);
        this.speechCommandSet.add(Accessories.Command.STOP_SPEECH);
        this.speechCommandSet.add(Accessories.Command.ENDPOINT_SPEECH);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$reportPacketDropped$1(String str, Throwable th) throws Throwable {
        AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(str, "unknown", true, null);
        Logger.e("Unable to get accessory device type error: " + th.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$reportUnsupportedSpeechCommand$3(Accessories.Command command, Throwable th) throws Throwable {
        SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.UNSUPPORTED_COMMAND, "unknown", null, command);
        Logger.e("Unable to get accessory device type error: " + th.getMessage());
    }

    @SuppressLint({"CheckResult"})
    private void reportPacketDropped(int i) {
        final String str = this.accessorySessionType + ":" + MetricsConstants.Connection.PACKET_DROPPED + ":" + MetricsUtils.AccessoryStreamIdentifier.fromValue(i);
        if (this.deviceRepositorySupplier.get() == null) {
            GeneratedOutlineSupport1.outline171(str, "unknown", true, null);
        } else {
            this.deviceRepositorySupplier.get().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).map($$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$UnsupportedTransportReceiver$c88zsGKsy9S5GwwvBCMkLAcRCGw
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    GeneratedOutlineSupport1.outline171(str, (String) obj, true, null);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$UnsupportedTransportReceiver$KOu3S2c2CNwVczeUwc3WXpY8KRA
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    UnsupportedTransportReceiver.lambda$reportPacketDropped$1(str, (Throwable) obj);
                }
            });
        }
    }

    @SuppressLint({"CheckResult"})
    private void reportUnsupportedSpeechCommand(final Accessories.Command command) {
        if (this.deviceRepositorySupplier.get() == null) {
            SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.UNSUPPORTED_COMMAND, "unknown", null, command);
        } else {
            this.deviceRepositorySupplier.get().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$UnsupportedTransportReceiver$0e011vS8IvVdKZWrEBLh2VzR8RA
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    SpeechProcessingMetricsReporter.reportFailure(SpeechProcessingMetricsReporter.FailureType.UNSUPPORTED_COMMAND, ((Device.DeviceInformation) obj).getDeviceType(), null, Accessories.Command.this);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$UnsupportedTransportReceiver$JYRidbKB7pWqBUlbaVdy46WEitY
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    UnsupportedTransportReceiver.lambda$reportUnsupportedSpeechCommand$3(Accessories.Command.this, (Throwable) obj);
                }
            });
        }
    }

    @Override // com.amazon.alexa.accessory.transport.TransportReceiver
    public void onDataReceived(SizedSource sizedSource, int i) throws Exception {
        if (i != 0) {
            Logger.d("Dropping a packet, no streams to handle it: %s on stream %d", sizedSource, Integer.valueOf(i));
            if (this.packetDroppedMetricCount.get() >= 1000) {
                return;
            }
            reportPacketDropped(i);
            this.packetDroppedMetricCount.incrementAndGet();
            return;
        }
        sizedSource.reset();
        Accessories.ControlEnvelope parseFrom = Accessories.ControlEnvelope.parseFrom(new SourceInputStream(sizedSource));
        ProtobufControlMessage protobufControlMessage = new ProtobufControlMessage(parseFrom);
        LoggerUtils.received(protobufControlMessage);
        if (protobufControlMessage.isResponse()) {
            return;
        }
        Logger.d("An unsupported control message command was received. Responding with command=%d", Integer.valueOf(parseFrom.getCommandValue()));
        ControlStream.dispatch(this.dispatcher, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommandValue(parseFrom.getCommandValue()).setResponse(Accessories.Response.newBuilder().setErrorCode(Common.ErrorCode.UNSUPPORTED)).mo10084build()), this.mode);
        if (!this.speechCommandSet.contains(parseFrom.getCommand())) {
            return;
        }
        reportUnsupportedSpeechCommand(parseFrom.getCommand());
    }
}
