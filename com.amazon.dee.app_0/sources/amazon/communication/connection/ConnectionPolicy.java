package amazon.communication.connection;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface ConnectionPolicy {

    /* loaded from: classes.dex */
    public enum CompressionOption {
        REQUIRED,
        ALLOWED,
        NOT_ALLOWED
    }

    @FireOsSdk
    CompressionOption getCompressionOption();

    @FireOsSdk
    Priority getPriority();

    @FireOsSdk
    boolean isAnonymousCredentialsAllowed();

    @FireOsSdk
    boolean isClearText();

    @FireOsSdk
    boolean isLowLatencyNecessary();

    @FireOsSdk
    boolean isRequestResponseOnly();

    @FireOsSdk
    boolean isRoamingAllowed();

    @FireOsSdk
    boolean isShortLived();

    @FireOsSdk
    boolean isWiFiNecessary();
}
