package amazon.communication.rlm;
/* loaded from: classes.dex */
public final class NackErrorCodes {
    public static final int NACK_CODE_MAX = 2999;
    public static final int NACK_CODE_MIN = 2000;
    public static final int NO_MESSAGE_HANDLER_LISTENING = 2000;

    private NackErrorCodes() {
    }

    public static boolean isNackErrorCode(int i) {
        return 2000 <= i && i <= 2999;
    }
}
