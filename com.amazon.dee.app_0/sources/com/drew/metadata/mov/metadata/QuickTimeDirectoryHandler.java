package com.drew.metadata.mov.metadata;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMetadataHandler;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;
/* loaded from: classes2.dex */
public class QuickTimeDirectoryHandler extends QuickTimeMetadataHandler {
    private String currentData;

    public QuickTimeDirectoryHandler(Metadata metadata) {
        super(metadata);
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    /* renamed from: processAtom */
    protected QuickTimeHandler mo6805processAtom(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException {
        String str;
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.type.equals("data") && this.currentData != null) {
                processData(bArr, sequentialByteArrayReader);
                return this;
            }
            str = new String(sequentialByteArrayReader.getBytes(4));
        } else {
            str = QuickTimeMetadataDirectory._tagIntegerMap.containsKey(atom.type) ? atom.type : null;
        }
        this.currentData = str;
        return this;
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler
    protected void processData(@NotNull byte[] bArr, @NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
        sequentialByteArrayReader.skip(8L);
        this.directory.setString(QuickTimeMetadataDirectory._tagIntegerMap.get(this.currentData).intValue(), new String(sequentialByteArrayReader.getBytes(bArr.length - 8)));
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler
    protected void processKeys(@NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException {
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptAtom(@NotNull Atom atom) {
        return atom.type.equals("data");
    }

    @Override // com.drew.metadata.mov.QuickTimeMetadataHandler, com.drew.imaging.quicktime.QuickTimeHandler
    protected boolean shouldAcceptContainer(@NotNull Atom atom) {
        return QuickTimeMetadataDirectory._tagIntegerMap.containsKey(atom.type) || atom.type.equals("ilst");
    }
}
