package com.facebook.imagepipeline.bitmaps;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.google.common.base.Ascii;
import java.io.IOException;
import okio.Utf8;
/* loaded from: classes2.dex */
public class EmptyJpegGenerator {
    private static final byte[] EMPTY_JPEG_PREFIX = {-1, -40, -1, -37, 0, 67, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -64, 0, 17, 8};
    private static final byte[] EMPTY_JPEG_SUFFIX = {3, 1, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 0, 2, 17, 0, 3, 17, 0, -1, -60, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, GenericAccessProfile.SERVICE_DATA_128BIT, 49, 65, 6, 19, 81, 97, 7, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 113, 20, 50, -127, -111, -95, 8, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, 66, -79, -63, 21, 82, -47, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION, GenericAccessProfile.URI, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, Ascii.SUB, GenericAccessProfile.INDOOR_POSITIONING, GenericAccessProfile.TRANSPORT_DISCOVERY_DATA, GenericAccessProfile.LE_SUPPORTED_FEATURES, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, GenericAccessProfile.PB_ADV, GenericAccessProfile.MESH_MESSAGE, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -60, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, GenericAccessProfile.SERVICE_DATA_128BIT, 49, 6, 18, 65, 81, 7, 97, 113, 19, GenericAccessProfile.LE_SECURE_CONNECTIONS_CONFIRMATION_VALUE, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, 51, 82, RequesterRelationshipsPacketV1.HEADER_MASK_VERSION, 21, 98, 114, -47, 10, 22, GenericAccessProfile.URI, 52, -31, GenericAccessProfile.INDOOR_POSITIONING, -15, 23, 24, 25, Ascii.SUB, GenericAccessProfile.TRANSPORT_DISCOVERY_DATA, GenericAccessProfile.LE_SUPPORTED_FEATURES, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, GenericAccessProfile.PB_ADV, GenericAccessProfile.MESH_MESSAGE, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, Utf8.REPLACEMENT_BYTE, 0, -114, -118, GenericAccessProfile.CHANNEL_MAP_UPDATE_INDICATION, -96, 15, -1, -39};
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public EmptyJpegGenerator(PooledByteBufferFactory pooledByteBufferFactory) {
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    public CloseableReference<PooledByteBuffer> generate(short width, short height) {
        PooledByteBufferOutputStream pooledByteBufferOutputStream = null;
        try {
            try {
                pooledByteBufferOutputStream = this.mPooledByteBufferFactory.mo6912newOutputStream(EMPTY_JPEG_PREFIX.length + EMPTY_JPEG_SUFFIX.length + 4);
                pooledByteBufferOutputStream.write(EMPTY_JPEG_PREFIX);
                pooledByteBufferOutputStream.write((byte) (height >> 8));
                pooledByteBufferOutputStream.write((byte) (height & 255));
                pooledByteBufferOutputStream.write((byte) (width >> 8));
                pooledByteBufferOutputStream.write((byte) (width & 255));
                pooledByteBufferOutputStream.write(EMPTY_JPEG_SUFFIX);
                CloseableReference<PooledByteBuffer> of = CloseableReference.of(pooledByteBufferOutputStream.mo6913toByteBuffer());
                pooledByteBufferOutputStream.close();
                return of;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            if (pooledByteBufferOutputStream != null) {
                pooledByteBufferOutputStream.close();
            }
            throw th;
        }
    }
}
