package amazon.speech.simclient;

import android.util.SparseArray;
/* loaded from: classes.dex */
public class SimError {
    public static final int ALEXA_CONNECTION_UNAVAILABLE = 1107;
    public static final int FORBIDDEN = 1403;
    public static final int INTERNAL_SERVER_ERROR = 1500;
    public static final int INVALID_REQUEST = 1400;
    public static final int LISTEN_ERROR_BUSY = 1104;
    public static final int LISTEN_ERROR_TIMEOUT = 1105;
    public static final int LISTEN_ERROR_UNKNOWN = 1106;
    public static final int MESSENGER_EVENT_ERROR_SCL_FAILURE = 2;
    public static final int MESSENGER_EVENT_TIMEOUT = 1;
    public static final int SERVICE_UNAVAILABLE = 1503;
    public static final int SIM_COMMUNICATION_FAILURE = 1102;
    public static final int SIM_CONNECTION_UNAVAILABLE = 1101;
    public static final int SIM_INTERNAL_ERROR = 1103;
    public static final int SUCCESS = 0;
    public static final int TOO_MANY_REQUESTS = 1429;
    private static final SparseArray<Description> ERROR_DESCRIPTIONS = new SparseArray<>();
    private static final Description DEFAULT_DESCRIPTION = new Description("Unknown Error Code");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Description {
        final String text;
        boolean retryable = false;
        Type type = Type.DEVICE;

        public Description(String str) {
            this.text = str;
        }

        public Description allowsRetry() {
            this.retryable = true;
            return this;
        }

        public Description serverError() {
            this.type = Type.SERVER;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public enum Type {
        SERVER,
        DEVICE
    }

    static {
        fillErrorCache();
    }

    private static void fillErrorCache() {
        ERROR_DESCRIPTIONS.put(0, new Description("Request Succeeded"));
        ERROR_DESCRIPTIONS.put(INVALID_REQUEST, new Description("The request was invalidated").serverError());
        ERROR_DESCRIPTIONS.put(FORBIDDEN, new Description("Request is forbidden").serverError());
        ERROR_DESCRIPTIONS.put(TOO_MANY_REQUESTS, new Description("Alexa cannot accept request at the moment, please try again later").serverError().allowsRetry());
        ERROR_DESCRIPTIONS.put(1500, new Description("An unexpected error was received from Alexa").serverError().allowsRetry());
        ERROR_DESCRIPTIONS.put(SERVICE_UNAVAILABLE, new Description("Alexa is currently unavailable").serverError());
        ERROR_DESCRIPTIONS.put(SIM_CONNECTION_UNAVAILABLE, new Description("Not connected to SIM"));
        ERROR_DESCRIPTIONS.put(SIM_COMMUNICATION_FAILURE, new Description("Intermittent Binder communication failure with SIM").allowsRetry());
        ERROR_DESCRIPTIONS.put(SIM_INTERNAL_ERROR, new Description("An unexpected internal error").allowsRetry());
        ERROR_DESCRIPTIONS.put(LISTEN_ERROR_BUSY, new Description("Already listening").allowsRetry());
        ERROR_DESCRIPTIONS.put(LISTEN_ERROR_TIMEOUT, new Description("Microphone recording timed out").allowsRetry());
        ERROR_DESCRIPTIONS.put(LISTEN_ERROR_UNKNOWN, new Description("Unexpected problem attempting to stream audio").allowsRetry());
        ERROR_DESCRIPTIONS.put(ALEXA_CONNECTION_UNAVAILABLE, new Description("CSM is not connected to Alexa").allowsRetry());
        ERROR_DESCRIPTIONS.put(1, new Description("A timeout occurred waiting for a response from Alexa").serverError().allowsRetry());
        ERROR_DESCRIPTIONS.put(2, new Description("A server error was returned by Alexa based on sent Event").serverError().allowsRetry());
    }

    public static String getDescriptionFor(int i) {
        return ERROR_DESCRIPTIONS.get(i, DEFAULT_DESCRIPTION).text;
    }

    public static Type getType(int i) {
        return ERROR_DESCRIPTIONS.get(i, DEFAULT_DESCRIPTION).type;
    }

    public static boolean isRetryable(int i) {
        return ERROR_DESCRIPTIONS.get(i, DEFAULT_DESCRIPTION).retryable;
    }
}
