package com.drew.imaging.quicktime;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.atoms.Atom;
import java.io.IOException;
/* loaded from: classes2.dex */
public abstract class QuickTimeHandler<T extends QuickTimeDirectory> {
    @NotNull
    protected T directory = mo6817getDirectory();
    @NotNull
    protected Metadata metadata;

    public QuickTimeHandler(@NotNull Metadata metadata) {
        this.metadata = metadata;
        metadata.addDirectory(this.directory);
    }

    public void addError(@NotNull String str) {
        this.directory.addError(str);
    }

    @NotNull
    /* renamed from: getDirectory */
    protected abstract T mo6817getDirectory();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: processAtom */
    public abstract QuickTimeHandler mo6805processAtom(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public QuickTimeHandler processContainer(@NotNull Atom atom) throws IOException {
        return mo6805processAtom(atom, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean shouldAcceptAtom(@NotNull Atom atom);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean shouldAcceptContainer(@NotNull Atom atom);
}
