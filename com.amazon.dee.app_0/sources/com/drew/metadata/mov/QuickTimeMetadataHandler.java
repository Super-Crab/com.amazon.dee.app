package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.metadata.QuickTimeMetadataDirectory;
import java.io.IOException;
/* loaded from: classes2.dex */
public abstract class QuickTimeMetadataHandler extends QuickTimeHandler {
    public QuickTimeMetadataHandler(Metadata metadata) {
        super(metadata);
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    @NotNull
    /* renamed from: getDirectory */
    protected QuickTimeDirectory mo6817getDirectory() {
        return new QuickTimeMetadataDirectory();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    /* renamed from: processAtom */
    public QuickTimeHandler mo6805processAtom(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.type.equals(QuickTimeAtomTypes.ATOM_KEYS)) {
                processKeys(sequentialByteArrayReader);
            } else if (atom.type.equals("data")) {
                processData(bArr, sequentialByteArrayReader);
            }
        }
        return this;
    }

    protected abstract void processData(@NotNull byte[] bArr, @NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException;

    protected abstract void processKeys(@NotNull SequentialByteArrayReader sequentialByteArrayReader) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public boolean shouldAcceptAtom(@NotNull Atom atom) {
        return atom.type.equals("hdlr") || atom.type.equals(QuickTimeAtomTypes.ATOM_KEYS) || atom.type.equals("data");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public boolean shouldAcceptContainer(@NotNull Atom atom) {
        return atom.type.equals("ilst");
    }
}
