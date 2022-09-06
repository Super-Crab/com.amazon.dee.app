package com.drew.imaging.mp4;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4Directory;
import com.drew.metadata.mp4.boxes.Box;
import java.io.IOException;
/* loaded from: classes2.dex */
public abstract class Mp4Handler<T extends Mp4Directory> {
    @NotNull
    protected T directory = mo6822getDirectory();
    @NotNull
    protected Metadata metadata;

    public Mp4Handler(@NotNull Metadata metadata) {
        this.metadata = metadata;
        metadata.addDirectory(this.directory);
    }

    public void addError(@NotNull String str) {
        this.directory.addError(str);
    }

    @NotNull
    /* renamed from: getDirectory */
    protected abstract T mo6822getDirectory();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Mp4Handler processBox(@NotNull Box box, @Nullable byte[] bArr) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public Mp4Handler processContainer(@NotNull Box box) throws IOException {
        return processBox(box, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean shouldAcceptBox(@NotNull Box box);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean shouldAcceptContainer(@NotNull Box box);
}
