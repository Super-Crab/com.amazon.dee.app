package com.amazon.alexa.accessory.metrics;

import com.amazon.alexa.accessory.AccessoryConnection;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.metrics.Stopwatch;
import com.amazon.alexa.accessory.transport.TransportVersion;
import com.amazon.alexa.accessory.transport.codecs.V2.V2TransportCodec;
import com.amazon.alexa.accessory.transport.codecs.muffin.MuffinTransportCodec;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.InterruptedIOException;
/* loaded from: classes.dex */
public final class ConnectionMetricsReporter {
    private final AccessoryMetricsService accessoryMetricsService;
    private final Stopwatch timeOpenStopwatch;
    private final Stopwatch timeToOpenStopwatch;

    public ConnectionMetricsReporter(AccessoryMetricsService accessoryMetricsService, Stopwatch.CurrentTimeSupplier currentTimeSupplier) {
        Preconditions.notNull(accessoryMetricsService, "accessoryMetricsService");
        Preconditions.notNull(currentTimeSupplier, "currentTimeSupplier");
        this.accessoryMetricsService = accessoryMetricsService;
        this.timeToOpenStopwatch = new Stopwatch(currentTimeSupplier);
        this.timeOpenStopwatch = new Stopwatch(currentTimeSupplier);
    }

    private void recordException(String str) {
        this.accessoryMetricsService.recordOccurrence(MetricsConstants.Connection.CONNECTION_ERROR_EVENT, str, true, null);
    }

    private void recordInvalidControlPacketLength(V2TransportCodec.InvalidControlPacketLengthException invalidControlPacketLengthException) {
        AccessoryMetricsService accessoryMetricsService = this.accessoryMetricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transportVersion:");
        outline107.append(String.valueOf(invalidControlPacketLengthException.getTransportVersion()));
        accessoryMetricsService.recordOccurrence(MetricsConstants.Connection.INVALID_CONTROL_PACKET_LENGTH, outline107.toString(), true, null);
        AccessoryMetricsService accessoryMetricsService2 = this.accessoryMetricsService;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("controlPacketLength:");
        outline1072.append(String.valueOf(invalidControlPacketLengthException.getLength()));
        accessoryMetricsService2.recordOccurrence(MetricsConstants.Connection.INVALID_CONTROL_PACKET_LENGTH, outline1072.toString(), true, null);
    }

    private void recordInvalidTransactionType(V2TransportCodec.InvalidTransactionTypeException invalidTransactionTypeException) {
        AccessoryMetricsService accessoryMetricsService = this.accessoryMetricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transportVersion:");
        outline107.append(String.valueOf(invalidTransactionTypeException.getTransportVersion()));
        accessoryMetricsService.recordOccurrence(MetricsConstants.Connection.INVALID_TRANSACTION_TYPE, outline107.toString(), true, null);
        AccessoryMetricsService accessoryMetricsService2 = this.accessoryMetricsService;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("transactionType:");
        outline1072.append(String.valueOf(invalidTransactionTypeException.getTransactionType()));
        accessoryMetricsService2.recordOccurrence(MetricsConstants.Connection.INVALID_TRANSACTION_TYPE, outline1072.toString(), true, null);
    }

    private void recordInvalidTransportFlags(MuffinTransportCodec.InvalidTransportFlagsException invalidTransportFlagsException) {
        AccessoryMetricsService accessoryMetricsService = this.accessoryMetricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transportVersion:");
        outline107.append(String.valueOf(invalidTransportFlagsException.getTransportVersion()));
        accessoryMetricsService.recordOccurrence(MetricsConstants.Connection.INVALID_TRANSPORT_FLAGS, outline107.toString(), true, null);
        AccessoryMetricsService accessoryMetricsService2 = this.accessoryMetricsService;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("actualTransportFlags:");
        outline1072.append(String.valueOf(invalidTransportFlagsException.getActualFlags()));
        accessoryMetricsService2.recordOccurrence(MetricsConstants.Connection.INVALID_TRANSPORT_FLAGS, outline1072.toString(), true, null);
        AccessoryMetricsService accessoryMetricsService3 = this.accessoryMetricsService;
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("expectedTransportFlags:");
        outline1073.append(String.valueOf(invalidTransportFlagsException.getExpectedFlags()));
        accessoryMetricsService3.recordOccurrence(MetricsConstants.Connection.INVALID_TRANSPORT_FLAGS, outline1073.toString(), true, null);
    }

    private void recordInvalidTransportMagicWord(TransportVersion.InvalidTransportMagicWordException invalidTransportMagicWordException) {
        AccessoryMetricsService accessoryMetricsService = this.accessoryMetricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transportMagicWord:");
        outline107.append(String.valueOf(invalidTransportMagicWordException.getActualMagicWord()));
        accessoryMetricsService.recordOccurrence(MetricsConstants.Connection.INVALID_TRANSPORT_MAGIC_WORD, outline107.toString(), true, null);
    }

    private void recordInvalidTransportVersion(MuffinTransportCodec.InvalidTransportVersionException invalidTransportVersionException) {
        AccessoryMetricsService accessoryMetricsService = this.accessoryMetricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("actualTransportVersion:");
        outline107.append(String.valueOf(invalidTransportVersionException.getActualVersion()));
        accessoryMetricsService.recordOccurrence(MetricsConstants.Connection.INVALID_TRANSPORT_VERSION, outline107.toString(), true, null);
        AccessoryMetricsService accessoryMetricsService2 = this.accessoryMetricsService;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("transportVersion:");
        outline1072.append(String.valueOf(invalidTransportVersionException.getTransportVersion()));
        accessoryMetricsService2.recordOccurrence(MetricsConstants.Connection.INVALID_TRANSPORT_VERSION, outline1072.toString(), true, null);
    }

    private void recordTransactionCollision(V2TransportCodec.TransactionCollisionException transactionCollisionException) {
        AccessoryMetricsService accessoryMetricsService = this.accessoryMetricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transportVersion:");
        outline107.append(String.valueOf(transactionCollisionException.getTransportVersion()));
        accessoryMetricsService.recordOccurrence(MetricsConstants.Connection.TRANSACTION_COLLISION, outline107.toString(), true, null);
        AccessoryMetricsService accessoryMetricsService2 = this.accessoryMetricsService;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("transactionStream:");
        outline1072.append(String.valueOf(transactionCollisionException.getStream()));
        accessoryMetricsService2.recordOccurrence(MetricsConstants.Connection.TRANSACTION_COLLISION, outline1072.toString(), true, null);
    }

    private void recordUnexpectedTransaction(V2TransportCodec.UnexpectedTransactionException unexpectedTransactionException) {
        AccessoryMetricsService accessoryMetricsService = this.accessoryMetricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transportVersion:");
        outline107.append(String.valueOf(unexpectedTransactionException.getTransportVersion()));
        accessoryMetricsService.recordOccurrence(MetricsConstants.Connection.UNEXPECTED_TRANSACTION, outline107.toString(), true, null);
        AccessoryMetricsService accessoryMetricsService2 = this.accessoryMetricsService;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("transactionStream:");
        outline1072.append(String.valueOf(unexpectedTransactionException.getStream()));
        accessoryMetricsService2.recordOccurrence(MetricsConstants.Connection.UNEXPECTED_TRANSACTION, outline1072.toString(), true, null);
    }

    private void recordUnknownException(Throwable th) {
        this.accessoryMetricsService.recordCriticalEvent(MetricsConstants.Connection.CONNECTION_ERROR_EVENT, MetricsConstants.Connection.CONNECTION_ERROR_EVENT, th.getClass().getSimpleName(), th);
    }

    private void recordUnsupportedTransportVersion(AccessoryConnection.UnsupportedTransportVersionException unsupportedTransportVersionException) {
        AccessoryMetricsService accessoryMetricsService = this.accessoryMetricsService;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("transportVersion:");
        outline107.append(String.valueOf(unsupportedTransportVersionException.getTransportVersion().getMajor()));
        accessoryMetricsService.recordOccurrence(MetricsConstants.Connection.UNSUPPORTED_TRANSPORT_VERSION, outline107.toString(), true, null);
    }

    public void connectionClosed() {
        this.timeOpenStopwatch.stop();
        this.accessoryMetricsService.recordTime(MetricsConstants.Connection.CONNECTION_CLOSED_EVENT, MetricsConstants.Connection.TIME_OPEN, this.timeOpenStopwatch.getElapsedTimeMillis(), null);
    }

    public void connectionError(Throwable th) {
        Preconditions.notNull(th, "throwable");
        this.timeOpenStopwatch.stop();
        this.accessoryMetricsService.recordTime(MetricsConstants.Connection.CONNECTION_ERROR_EVENT, MetricsConstants.Connection.TIME_OPEN, this.timeOpenStopwatch.getElapsedTimeMillis(), null);
        if (th instanceof AccessoryConnection.UnsupportedTransportVersionException) {
            recordUnsupportedTransportVersion((AccessoryConnection.UnsupportedTransportVersionException) th);
        } else if (th instanceof TransportVersion.InvalidTransportMagicWordException) {
            recordInvalidTransportMagicWord((TransportVersion.InvalidTransportMagicWordException) th);
        } else if (th instanceof MuffinTransportCodec.InvalidTransportVersionException) {
            recordInvalidTransportVersion((MuffinTransportCodec.InvalidTransportVersionException) th);
        } else if (th instanceof MuffinTransportCodec.InvalidTransportFlagsException) {
            recordInvalidTransportFlags((MuffinTransportCodec.InvalidTransportFlagsException) th);
        } else if (th instanceof V2TransportCodec.InvalidTransactionTypeException) {
            recordInvalidTransactionType((V2TransportCodec.InvalidTransactionTypeException) th);
        } else if (th instanceof V2TransportCodec.InvalidControlPacketLengthException) {
            recordInvalidControlPacketLength((V2TransportCodec.InvalidControlPacketLengthException) th);
        } else if (th instanceof V2TransportCodec.TransactionCollisionException) {
            recordTransactionCollision((V2TransportCodec.TransactionCollisionException) th);
        } else if (th instanceof V2TransportCodec.UnexpectedTransactionException) {
            recordUnexpectedTransaction((V2TransportCodec.UnexpectedTransactionException) th);
        } else if (th instanceof InterruptedException) {
            recordException(MetricsConstants.Connection.INTERRUPTED_EXCEPTION);
        } else if (th instanceof InterruptedIOException) {
            recordException(MetricsConstants.Connection.INTERRUPTED_IO_EXCEPTION);
        } else {
            recordUnknownException(th);
        }
    }

    public void connectionOpened() {
        this.timeToOpenStopwatch.stop();
        this.timeOpenStopwatch.startOrResume();
        this.accessoryMetricsService.recordTime(MetricsConstants.Connection.CONNECTION_OPENED_EVENT, MetricsConstants.Connection.TIME_TO_OPEN, this.timeToOpenStopwatch.getElapsedTimeMillis(), null);
    }

    public void onOpenCalled() {
        this.timeToOpenStopwatch.startOrResume();
    }
}
