package amazon.communication.rlm;
/* loaded from: classes.dex */
public final class RlmCodes {
    public static final int RELIABLE_MESSAGE = 0;
    public static final int RLM_CODE_MAX = 999;
    public static final int RLM_CODE_MIN = 0;

    private RlmCodes() {
    }

    public static boolean isRlmCode(int i) {
        return i >= 0 && i <= 999;
    }
}
