package com.drew.metadata.jpeg;

import com.amazon.alexa.presence.bleconn.service.protocols.relationships.RequesterRelationshipsPacketV1;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.jpeg.HuffmanTablesDirectory;
import java.io.IOException;
import java.util.Collections;
/* loaded from: classes2.dex */
public class JpegDhtReader implements JpegSegmentMetadataReader {
    private byte[] getBytes(@NotNull SequentialReader sequentialReader, int i) throws IOException {
        byte b;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            byte b2 = sequentialReader.getByte();
            if ((b2 & 255) == 255 && (b = sequentialReader.getByte()) != 0) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Marker ");
                outline107.append(JpegSegmentType.fromByte(b));
                outline107.append(" found inside DHT segment");
                throw new IOException(outline107.toString());
            }
            bArr[i2] = b2;
        }
        return bArr;
    }

    public void extract(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        HuffmanTablesDirectory huffmanTablesDirectory = (HuffmanTablesDirectory) metadata.getFirstDirectoryOfType(HuffmanTablesDirectory.class);
        if (huffmanTablesDirectory == null) {
            huffmanTablesDirectory = new HuffmanTablesDirectory();
            metadata.addDirectory(huffmanTablesDirectory);
        }
        while (sequentialReader.available() > 0) {
            try {
                byte b = sequentialReader.getByte();
                HuffmanTablesDirectory.HuffmanTable.HuffmanTableClass typeOf = HuffmanTablesDirectory.HuffmanTable.HuffmanTableClass.typeOf((b & RequesterRelationshipsPacketV1.HEADER_MASK_VERSION) >> 4);
                int i = b & 15;
                byte[] bytes = getBytes(sequentialReader, 16);
                int i2 = 0;
                for (byte b2 : bytes) {
                    i2 += b2 & 255;
                }
                huffmanTablesDirectory.getTables().add(new HuffmanTablesDirectory.HuffmanTable(typeOf, i, bytes, getBytes(sequentialReader, i2)));
            } catch (IOException e) {
                huffmanTablesDirectory.addError(e.getMessage());
            }
        }
        huffmanTablesDirectory.setInt(1, huffmanTablesDirectory.getTables().size());
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    @NotNull
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.DHT);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            extract(new SequentialByteArrayReader(bArr), metadata);
        }
    }
}
