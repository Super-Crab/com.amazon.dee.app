package com.drew.metadata.jpeg;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.MetadataException;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes2.dex */
public class HuffmanTablesDirectory extends Directory {
    public static final int TAG_NUMBER_OF_TABLES = 1;
    @NotNull
    protected final List<HuffmanTable> tables = new ArrayList(4);
    protected static final byte[] TYPICAL_LUMINANCE_DC_LENGTHS = {0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
    protected static final byte[] TYPICAL_LUMINANCE_DC_VALUES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    protected static final byte[] TYPICAL_CHROMINANCE_DC_LENGTHS = {0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0};
    protected static final byte[] TYPICAL_CHROMINANCE_DC_VALUES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    protected static final byte[] TYPICAL_LUMINANCE_AC_LENGTHS = {0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125};
    protected static final byte[] TYPICAL_LUMINANCE_AC_VALUES = {1, 2, 3, 0, 4, 17, 5, 18, GenericAccessProfile.SERVICE_DATA_128BIT, 49, 65, 6, 19, 81, 97, 7, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 113, 20, 50, -127, -111, -95, 8, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, 66, -79, -63, 21, 82, -47, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION, GenericAccessProfile.URI, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, Ascii.SUB, GenericAccessProfile.INDOOR_POSITIONING, GenericAccessProfile.TRANSPORT_DISCOVERY_DATA, GenericAccessProfile.LE_SUPPORTED_FEATURES, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, GenericAccessProfile.PB_ADV, GenericAccessProfile.MESH_MESSAGE, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6};
    protected static final byte[] TYPICAL_CHROMINANCE_AC_LENGTHS = {0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119};
    protected static final byte[] TYPICAL_CHROMINANCE_AC_VALUES = {0, 1, 2, 3, 17, 4, 5, GenericAccessProfile.SERVICE_DATA_128BIT, 49, 6, 18, 65, 81, 7, 97, 113, 19, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, 51, 82, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION, 21, 98, 114, -47, 10, 22, GenericAccessProfile.URI, 52, -31, GenericAccessProfile.INDOOR_POSITIONING, -15, 23, 24, 25, Ascii.SUB, GenericAccessProfile.TRANSPORT_DISCOVERY_DATA, GenericAccessProfile.LE_SUPPORTED_FEATURES, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, GenericAccessProfile.PB_ADV, GenericAccessProfile.MESH_MESSAGE, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6};
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    /* loaded from: classes2.dex */
    public static class HuffmanTable {
        private final byte[] _lengthBytes;
        private final HuffmanTableClass _tableClass;
        private final int _tableDestinationId;
        private final int _tableLength;
        private final byte[] _valueBytes;

        /* loaded from: classes2.dex */
        public enum HuffmanTableClass {
            DC,
            AC,
            UNKNOWN;

            public static HuffmanTableClass typeOf(int i) {
                return i != 0 ? i != 1 ? UNKNOWN : AC : DC;
            }
        }

        public HuffmanTable(@NotNull HuffmanTableClass huffmanTableClass, int i, @NotNull byte[] bArr, @NotNull byte[] bArr2) {
            if (bArr != null) {
                if (bArr2 == null) {
                    throw new IllegalArgumentException("valueBytes cannot be null.");
                }
                this._tableClass = huffmanTableClass;
                this._tableDestinationId = i;
                this._lengthBytes = bArr;
                this._valueBytes = bArr2;
                this._tableLength = this._valueBytes.length + 17;
                return;
            }
            throw new IllegalArgumentException("lengthBytes cannot be null.");
        }

        @NotNull
        public byte[] getLengthBytes() {
            byte[] bArr = this._lengthBytes;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }

        public HuffmanTableClass getTableClass() {
            return this._tableClass;
        }

        public int getTableDestinationId() {
            return this._tableDestinationId;
        }

        public int getTableLength() {
            return this._tableLength;
        }

        @NotNull
        public byte[] getValueBytes() {
            byte[] bArr = this._valueBytes;
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            return bArr2;
        }

        public boolean isOptimized() {
            return !isTypical();
        }

        public boolean isTypical() {
            HuffmanTableClass huffmanTableClass = this._tableClass;
            if (huffmanTableClass == HuffmanTableClass.DC) {
                if (Arrays.equals(this._lengthBytes, HuffmanTablesDirectory.TYPICAL_LUMINANCE_DC_LENGTHS) && Arrays.equals(this._valueBytes, HuffmanTablesDirectory.TYPICAL_LUMINANCE_DC_VALUES)) {
                    return true;
                }
                return Arrays.equals(this._lengthBytes, HuffmanTablesDirectory.TYPICAL_CHROMINANCE_DC_LENGTHS) && Arrays.equals(this._valueBytes, HuffmanTablesDirectory.TYPICAL_CHROMINANCE_DC_VALUES);
            } else if (huffmanTableClass != HuffmanTableClass.AC) {
                return false;
            } else {
                if (Arrays.equals(this._lengthBytes, HuffmanTablesDirectory.TYPICAL_LUMINANCE_AC_LENGTHS) && Arrays.equals(this._valueBytes, HuffmanTablesDirectory.TYPICAL_LUMINANCE_AC_VALUES)) {
                    return true;
                }
                return Arrays.equals(this._lengthBytes, HuffmanTablesDirectory.TYPICAL_CHROMINANCE_AC_LENGTHS) && Arrays.equals(this._valueBytes, HuffmanTablesDirectory.TYPICAL_CHROMINANCE_AC_VALUES);
            }
        }
    }

    static {
        _tagNameMap.put(1, "Number of Tables");
    }

    public HuffmanTablesDirectory() {
        setDescriptor(new HuffmanTablesDescriptor(this));
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "Huffman";
    }

    public int getNumberOfTables() throws MetadataException {
        return getInt(1);
    }

    @NotNull
    public HuffmanTable getTable(int i) {
        return this.tables.get(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public List<HuffmanTable> getTables() {
        return this.tables;
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    public boolean isOptimized() {
        return !isTypical();
    }

    public boolean isTypical() {
        if (this.tables.size() == 0) {
            return false;
        }
        for (HuffmanTable huffmanTable : this.tables) {
            if (!huffmanTable.isTypical()) {
                return false;
            }
        }
        return true;
    }
}
