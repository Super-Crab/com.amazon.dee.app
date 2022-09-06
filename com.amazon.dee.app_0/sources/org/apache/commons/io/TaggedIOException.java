package org.apache.commons.io;

import java.io.IOException;
import java.io.Serializable;
/* loaded from: classes4.dex */
public class TaggedIOException extends IOExceptionWithCause {
    private static final long serialVersionUID = -6994123481142850163L;
    private final Serializable tag;

    public TaggedIOException(IOException iOException, Serializable serializable) {
        super(iOException.getMessage(), iOException);
        this.tag = serializable;
    }

    public static boolean isTaggedWith(Throwable th, Object obj) {
        return obj != null && (th instanceof TaggedIOException) && obj.equals(((TaggedIOException) th).tag);
    }

    public static void throwCauseIfTaggedWith(Throwable th, Object obj) throws IOException {
        if (!isTaggedWith(th, obj)) {
            return;
        }
        throw ((TaggedIOException) th).mo12768getCause();
    }

    public Serializable getTag() {
        return this.tag;
    }

    @Override // java.lang.Throwable
    /* renamed from: getCause */
    public IOException mo12768getCause() {
        return (IOException) super.getCause();
    }
}
