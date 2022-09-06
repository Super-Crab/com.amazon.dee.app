package com.drew.lang;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.io.PrintStream;
import java.io.PrintWriter;
/* loaded from: classes2.dex */
public class CompoundException extends Exception {
    private static final long serialVersionUID = -9207883813472069925L;
    @Nullable
    private final Throwable _innerException;

    public CompoundException(@Nullable String str) {
        this(str, null);
    }

    public CompoundException(@Nullable String str, @Nullable Throwable th) {
        super(str);
        this._innerException = th;
    }

    public CompoundException(@Nullable Throwable th) {
        this(null, th);
    }

    @Nullable
    public Throwable getInnerException() {
        return this._innerException;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        super.printStackTrace();
        if (this._innerException != null) {
            System.err.println("--- inner exception ---");
            this._innerException.printStackTrace();
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(@NotNull PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this._innerException != null) {
            printStream.println("--- inner exception ---");
            this._innerException.printStackTrace(printStream);
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(@NotNull PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this._innerException != null) {
            printWriter.println("--- inner exception ---");
            this._innerException.printStackTrace(printWriter);
        }
    }

    @Override // java.lang.Throwable
    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (this._innerException != null) {
            GeneratedOutlineSupport1.outline180(sb, "\n", "--- inner exception ---", "\n");
            sb.append(this._innerException.toString());
        }
        return sb.toString();
    }
}
