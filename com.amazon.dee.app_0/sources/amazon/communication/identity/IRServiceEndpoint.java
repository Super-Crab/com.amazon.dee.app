package amazon.communication.identity;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public interface IRServiceEndpoint {

    /* loaded from: classes.dex */
    public enum ClearTextConnection {
        ALLOWED("allowed"),
        NOT_ALLOWED("not-allowed");
        
        private static String REQUIRED = "required";
        private final String mName;

        ClearTextConnection(String str) {
            this.mName = str;
        }

        @FireOsSdk
        public static ClearTextConnection parse(String str) {
            ClearTextConnection[] values;
            for (ClearTextConnection clearTextConnection : values()) {
                if (clearTextConnection.toString().equalsIgnoreCase(str)) {
                    return clearTextConnection;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("str: \"", str, "\", cannot be parsed"));
        }

        @FireOsSdk
        public static ClearTextConnection parseSecureConnection(String str) {
            if (ALLOWED.toString().equalsIgnoreCase(str)) {
                return ALLOWED;
            }
            if (REQUIRED.equalsIgnoreCase(str)) {
                return NOT_ALLOWED;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("str: \"", str, "\", cannot be parsed"));
        }

        @Override // java.lang.Enum
        @FireOsSdk
        public String toString() {
            return this.mName;
        }
    }

    /* loaded from: classes.dex */
    public enum DataCompression {
        NEEDED("needed"),
        NOT_NEEDED("not-needed");
        
        private final String mName;

        DataCompression(String str) {
            this.mName = str;
        }

        @FireOsSdk
        public static DataCompression parse(String str) {
            DataCompression[] values;
            for (DataCompression dataCompression : values()) {
                if (dataCompression.toString().equalsIgnoreCase(str)) {
                    return dataCompression;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("str: \"", str, "\", cannot be parsed"));
        }

        @Override // java.lang.Enum
        @FireOsSdk
        public String toString() {
            return this.mName;
        }
    }

    /* loaded from: classes.dex */
    public enum DirectConnection {
        REQUIRED("required"),
        ALLOWED("allowed"),
        NOT_ALLOWED("not-allowed");
        
        private final String mName;

        DirectConnection(String str) {
            this.mName = str;
        }

        @FireOsSdk
        public static DirectConnection parse(String str) {
            DirectConnection[] values;
            for (DirectConnection directConnection : values()) {
                if (directConnection.toString().equalsIgnoreCase(str)) {
                    return directConnection;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("str: \"", str, "\", cannot be parsed"));
        }

        @Override // java.lang.Enum
        @FireOsSdk
        public String toString() {
            return this.mName;
        }
    }

    /* loaded from: classes.dex */
    public enum Scheme {
        WS("ws"),
        WSS("wss"),
        HTTP("http"),
        HTTPS("https");
        
        private final String mName;

        Scheme(String str) {
            this.mName = str;
        }

        @FireOsSdk
        public static Scheme parse(String str) {
            Scheme[] values;
            for (Scheme scheme : values()) {
                if (scheme.toString().equalsIgnoreCase(str)) {
                    return scheme;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("str: \"", str, "\", cannot be parsed"));
        }

        @Override // java.lang.Enum
        @FireOsSdk
        public String toString() {
            return this.mName;
        }
    }

    @FireOsSdk
    ClearTextConnection getClearTextConnection();

    @FireOsSdk
    DataCompression getDataCompression();

    @FireOsSdk
    DirectConnection getDirectConnection();

    @FireOsSdk
    String getDirectorServiceName();

    @FireOsSdk
    String getDomain();

    @FireOsSdk
    String getHostname();

    @FireOsSdk
    Integer getPort();

    @FireOsSdk
    String getRealm();

    @FireOsSdk
    Integer getSecurePort();

    @FireOsSdk
    int getTimeout();

    @FireOsSdk
    String toResolvedUri(Scheme scheme);
}
