package com.amazon.alexa.accessory.transport;

import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.DataSink;
import com.amazon.alexa.accessory.io.DataSource;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
public final class TransportVersion {
    private static final int AUTHENTICATION_SUPPORT_MASK = 1;
    private static final int DEFAULT_SIMULTANEOUS_TRANSACTIONS = 255;
    private static final int ENCRYPTION_SUPPORT_MASK = 2;
    private static final int MAGIC_NUMBER = 65027;
    private static final int REQUIRE_RESPONSE_SUPPORT_MASK = 4;
    private static final int RESERVED_VERSION_NUMBER = 1;
    private static final String TAG = "TransportVersion";
    private static final int TWO_BYTES_IN_BITS = 16;
    private static final byte[] V3_RESPONSE_RESERVED_BYTES = {0, 0, 0, 0, 0, 0, 0};
    private final int major;
    private final int maxSimultaneousTransactions;
    private final int maxTransactionSize;
    private final int minor;
    private final int packetFragmentSize;
    private final Set<TransportFeature> requiredSupport;

    /* loaded from: classes6.dex */
    public static final class InvalidTransportMagicWordException extends IOException {
        private final String actualMagicWord;

        public InvalidTransportMagicWordException(String str) {
            super(String.format(Locale.US, "Invalid transport magic word %s, expected 0xfe03", str));
            this.actualMagicWord = str;
        }

        public String getActualMagicWord() {
            return this.actualMagicWord;
        }
    }

    public TransportVersion(int i, int i2, int i3, int i4, int i5, Set<TransportFeature> set) {
        Preconditions.notNull(set, "requiredSupport");
        this.major = i;
        this.minor = i2;
        this.packetFragmentSize = i3;
        this.maxTransactionSize = i4;
        this.maxSimultaneousTransactions = i5;
        this.requiredSupport = set;
    }

    private static Set<TransportFeature> parseFeatures(DataSource dataSource, int i) throws IOException {
        EnumSet noneOf = EnumSet.noneOf(TransportFeature.class);
        if (i != 3) {
            dataSource.get(new byte[11]);
        } else {
            int integer = dataSource.getInteger();
            dataSource.get(new byte[7]);
            if ((integer & 1) != 0) {
                noneOf.add(TransportFeature.AUTHENTICATION);
            }
            if ((integer & 2) != 0) {
                noneOf.add(TransportFeature.ENCRYPTION);
            }
            if ((integer & 4) != 0) {
                noneOf.add(TransportFeature.REQUIRE_RESPONSE);
            }
        }
        return Sets.immutableEnumSet(noneOf);
    }

    public static TransportVersion read(DataSource dataSource) throws IOException {
        int word = dataSource.getWord();
        Logger.v("Reading transport version {");
        Logger.v("    - magic=%d", Integer.valueOf(word));
        if (word == MAGIC_NUMBER) {
            int i = dataSource.get();
            Logger.v("    - major=%d", Integer.valueOf(i));
            int i2 = dataSource.get();
            Logger.v("    - minor=%d", Integer.valueOf(i2));
            int word2 = dataSource.getWord();
            Logger.v("    - packetFragmentSize=%d", Integer.valueOf(word2));
            int word3 = dataSource.getWord();
            Logger.v("    - maxTransactionSize=%d", Integer.valueOf(word3));
            int i3 = dataSource.get();
            Logger.v("    - maxSimultaneousTransactions=%d", Integer.valueOf(i3));
            if (i3 == 0 || i < 3) {
                i3 = 255;
            }
            Logger.v(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            return new TransportVersion(i, i2, word2, word3, i3, parseFeatures(dataSource, i));
        }
        throw new InvalidTransportMagicWordException(String.format(Locale.US, "0x%04X", Integer.valueOf(word)));
    }

    public static void sendTransportVersionResponse(TransportVersion transportVersion, DataSink dataSink, AccessorySessionOptions accessorySessionOptions) throws IOException {
        if (!transportVersion.getRequiredSupport().contains(TransportFeature.REQUIRE_RESPONSE)) {
            return;
        }
        Logger.v("%s: Sending transport version response", TAG);
        dataSink.writeBits(MAGIC_NUMBER, 16);
        dataSink.write(transportVersion.getMajor());
        dataSink.write(1);
        dataSink.writeBits(transportVersion.getPacketFragmentSize(), 16);
        dataSink.writeBits(transportVersion.getMaxTransactionSize(), 16);
        dataSink.write(transportVersion.getMaxSimultaneousTransactions());
        Set<TransportFeature> requiredSupport = transportVersion.getRequiredSupport();
        int i = requiredSupport.contains(TransportFeature.AUTHENTICATION) ? 1 : 0;
        if (requiredSupport.contains(TransportFeature.ENCRYPTION)) {
            i |= 2;
        }
        if (accessorySessionOptions.isDeviceKnown()) {
            i |= 4;
        }
        if (accessorySessionOptions.shouldForceConnection()) {
            i |= 8;
        }
        dataSink.writeInteger(i);
        Logger.v("%s: Sending feature mask (%d) in the transport version response", TAG, Integer.valueOf(i));
        dataSink.write(V3_RESPONSE_RESERVED_BYTES);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TransportVersion.class != obj.getClass()) {
            return false;
        }
        TransportVersion transportVersion = (TransportVersion) obj;
        return this.major == transportVersion.major && this.minor == transportVersion.minor && this.packetFragmentSize == transportVersion.packetFragmentSize && this.maxTransactionSize == transportVersion.maxTransactionSize && Objects.equals(this.requiredSupport, transportVersion.requiredSupport);
    }

    public int getMajor() {
        return this.major;
    }

    public int getMaxSimultaneousTransactions() {
        return this.maxSimultaneousTransactions;
    }

    public int getMaxTransactionSize() {
        return this.maxTransactionSize;
    }

    public int getMinor() {
        return this.minor;
    }

    public int getPacketFragmentSize() {
        return this.packetFragmentSize;
    }

    public Set<TransportFeature> getRequiredSupport() {
        return this.requiredSupport;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.major), Integer.valueOf(this.minor), Integer.valueOf(this.packetFragmentSize), Integer.valueOf(this.maxTransactionSize), this.requiredSupport);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransportVersion{major=");
        outline107.append(this.major);
        outline107.append(", minor=");
        outline107.append(this.minor);
        outline107.append(", packetFragmentSize=");
        outline107.append(this.packetFragmentSize);
        outline107.append(", maxTransactionSize=");
        outline107.append(this.maxTransactionSize);
        outline107.append(", requiredSupport=");
        outline107.append(this.requiredSupport);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
