package amazon.communication.rlm;
/* loaded from: classes.dex */
public final class AckCodes {
    public static final int ACK_CODE_MAX = 1999;
    public static final int ACK_CODE_MIN = 1000;
    public static final int PASSED_MESSAGE_TO_CLIENT = 1000;

    private AckCodes() {
    }

    public static boolean isAckCode(int i) {
        return 1000 <= i && i <= 1999;
    }
}
