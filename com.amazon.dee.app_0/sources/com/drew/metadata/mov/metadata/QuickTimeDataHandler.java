package com.drew.metadata.mov.metadata;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.ByteUtil;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.drew.metadata.mov.QuickTimeMetadataHandler;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class QuickTimeDataHandler extends QuickTimeMetadataHandler {
    private int currentIndex;
    private ArrayList<String> keys;

    public QuickTimeDataHandler(Metadata metadata) {
        super(metadata);
        this.currentIndex = 0;
        this.keys = new ArrayList<>();
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    /* renamed from: processAtom */
    protected QuickTimeHandler mo6805processAtom(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.type.equals(QuickTimeAtomTypes.ATOM_KEYS)) {
                processKeys(sequentialByteArrayReader);
            } else if (atom.type.equals("data")) {
                processData(bArr, sequentialByteArrayReader);
            }
        } else {
            int int32 = ByteUtil.getInt32(atom.type.getBytes(), 0, true);
            if (int32 > 0 && int32 < this.keys.size() + 1) {
                this.currentIndex = int32 - 1;
            }
        }
        return this;
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler
    protected void processData(@NotNull byte[] bArr, @NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
        sequentialByteArrayReader.skip(8L);
        this.directory.setString(QuickTimeMetadataDirectory._tagIntegerMap.get(this.keys.get(this.currentIndex)).intValue(), new String(sequentialByteArrayReader.getBytes(bArr.length - 8)));
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler
    protected void processKeys(@NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
        sequentialByteArrayReader.skip(4L);
        int int32 = sequentialByteArrayReader.getInt32();
        for (int i = 0; i < int32; i++) {
            int int322 = sequentialByteArrayReader.getInt32();
            sequentialByteArrayReader.skip(4L);
            this.keys.add(new String(sequentialByteArrayReader.getBytes(int322 - 8)));
        }
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptAtom(@NotNull Atom atom) {
        return atom.type.equals("hdlr") || atom.type.equals(QuickTimeAtomTypes.ATOM_KEYS) || atom.type.equals("data");
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptContainer(@NotNull Atom atom) {
        return atom.type.equals("ilst") || ByteUtil.getInt32(atom.type.getBytes(), 0, true) <= this.keys.size();
    }
}
