package com.amazon.alexa;
/* compiled from: Errors.java */
/* loaded from: classes.dex */
public final class FGE {
    public static final pfe BIo;
    public static final pfe zQM;
    public static final pfe zZm = pfe.zZm(Iof.PLAYER_UNKNOWN, "Unknown player id", 100, false);
    public static final pfe zyO;

    static {
        pfe.zZm(Iof.PLAYER_NOT_FOUND, "Player not found", 200L, false);
        BIo = pfe.zZm(Iof.PLAYER_CONNECTION_REJECTED, "Connection rejected", 300L, false);
        zQM = pfe.zZm(Iof.PLAYER_CONNECTION_TIMEOUT, "Connection to the player timed out", 400L, false);
        zyO = pfe.zZm(Iof.OPERATION_UNSUPPORTED, "The operation was not attempted becauseit is currently not supported.", 1000L, false);
    }
}
