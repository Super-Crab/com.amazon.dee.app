package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeDirectory;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.media.QuickTimeMediaDirectory;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
/* loaded from: classes2.dex */
public abstract class QuickTimeMediaHandler<T extends QuickTimeDirectory> extends QuickTimeHandler<T> {
    public QuickTimeMediaHandler(Metadata metadata) {
        super(metadata);
        if (QuickTimeHandlerFactory.HANDLER_PARAM_CREATION_TIME == null || QuickTimeHandlerFactory.HANDLER_PARAM_MODIFICATION_TIME == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(1904, 0, 1, 0, 0, 0);
        long time = calendar.getTime().getTime();
        String date = new Date((QuickTimeHandlerFactory.HANDLER_PARAM_CREATION_TIME.longValue() * 1000) + time).toString();
        String date2 = new Date((QuickTimeHandlerFactory.HANDLER_PARAM_MODIFICATION_TIME.longValue() * 1000) + time).toString();
        this.directory.setString(QuickTimeMediaDirectory.TAG_CREATION_TIME, date);
        this.directory.setString(QuickTimeMediaDirectory.TAG_MODIFICATION_TIME, date2);
    }

    protected abstract String getMediaInformation();

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    /* renamed from: processAtom  reason: collision with other method in class */
    public QuickTimeMediaHandler mo6805processAtom(@NotNull Atom atom, @Nullable byte[] bArr) throws IOException {
        if (bArr != null) {
            SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
            if (atom.type.equals(getMediaInformation())) {
                processMediaInformation(sequentialByteArrayReader, atom);
            } else if (atom.type.equals("stsd")) {
                processSampleDescription(sequentialByteArrayReader, atom);
            } else if (atom.type.equals("stts")) {
                processTimeToSample(sequentialByteArrayReader, atom);
            }
        }
        return this;
    }

    protected abstract void processMediaInformation(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException;

    protected abstract void processSampleDescription(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException;

    protected abstract void processTimeToSample(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException;

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public boolean shouldAcceptAtom(@NotNull Atom atom) {
        return atom.type.equals(getMediaInformation()) || atom.type.equals("stsd") || atom.type.equals("stts");
    }

    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    public boolean shouldAcceptContainer(@NotNull Atom atom) {
        return atom.type.equals("stbl") || atom.type.equals("minf") || atom.type.equals("gmhd") || atom.type.equals("tmcd");
    }
}
