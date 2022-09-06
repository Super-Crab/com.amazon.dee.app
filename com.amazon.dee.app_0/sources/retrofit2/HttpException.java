package retrofit2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public class HttpException extends RuntimeException {
    private final int code;
    private final String message;
    private final transient Response<?> response;

    public HttpException(Response<?> response) {
        super(getMessage(response));
        this.code = response.code();
        this.message = response.message();
        this.response = response;
    }

    private static String getMessage(Response<?> response) {
        Utils.checkNotNull(response, "response == null");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HTTP ");
        outline107.append(response.code());
        outline107.append(" ");
        outline107.append(response.message());
        return outline107.toString();
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public Response<?> response() {
        return this.response;
    }
}
