package io.reactivex.rxjava3.exceptions;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
/* loaded from: classes3.dex */
public final class Exceptions {
    private Exceptions() {
        throw new IllegalStateException("No instances!");
    }

    @NonNull
    public static RuntimeException propagate(@NonNull Throwable t) {
        throw ExceptionHelper.wrapOrThrow(t);
    }

    public static void throwIfFatal(@NonNull Throwable t) {
        if (!(t instanceof VirtualMachineError)) {
            if (!(t instanceof ThreadDeath)) {
                if (t instanceof LinkageError) {
                    throw ((LinkageError) t);
                }
                return;
            }
            throw ((ThreadDeath) t);
        }
        throw ((VirtualMachineError) t);
    }
}
