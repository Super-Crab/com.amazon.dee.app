package amazon.communication.identity;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class ExplicitServiceIdentity extends ServiceIdentity {
    private static final String CLEAR_TEXT_CONNECTION = "clear_text_connection";
    private static final String DATA_COMPRESSION = "data_compression";
    private static final String DELIMITER = ":";
    private static final String DIRECT_CONNECTION = "direct_connection";
    private static final String DOMAIN = "domain";
    private static final String HOSTNAME = "hostname";
    private static final String PORT = "port";
    private static final String REALM = "realm";
    private static final String SECURE_PORT = "secure_port";
    private static final String SERVICE_HEADER = "urn:tcomm-endpoint:service-explicit";
    private static final String SERVICE_NAME = "serviceName";
    private static final String TIMEOUT = "timeout";
    @FireOsSdk
    protected String mClearTextConnection;
    @FireOsSdk
    protected String mDataCompression;
    @FireOsSdk
    protected String mDirectConnection;
    @FireOsSdk
    protected Integer mSecurePort;
    @FireOsSdk
    protected Integer mTimeout;

    /* JADX INFO: Access modifiers changed from: package-private */
    @FireOsSdk
    public ExplicitServiceIdentity(String str, String str2, String str3, String str4, Integer num, Integer num2, String str5, String str6, String str7, Integer num3) {
        super(str, str2, str3, str4, num);
        if (str2 != null && str3 != null && str4 != null && num != null && num2 != null && str5 != null && str6 != null && str7 != null && num3 != null) {
            this.mSecurePort = num2;
            this.mDirectConnection = str5;
            this.mDataCompression = str6;
            this.mClearTextConnection = str7;
            this.mTimeout = num3;
            return;
        }
        throw new IllegalArgumentException("No arguments to constructor can be null");
    }

    @FireOsSdk
    public static boolean matchesUrn(String str) {
        return str != null && str.startsWith(SERVICE_HEADER);
    }

    @FireOsSdk
    public String getClearTextConnection() {
        return this.mClearTextConnection;
    }

    @FireOsSdk
    public String getDataCompression() {
        return this.mDataCompression;
    }

    @FireOsSdk
    public String getDirectConnection() {
        return this.mDirectConnection;
    }

    @FireOsSdk
    public Integer getSecurePort() {
        return this.mSecurePort;
    }

    @FireOsSdk
    public Integer getTimeout() {
        return this.mTimeout;
    }

    @Override // amazon.communication.identity.ServiceIdentity, amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public String toString() {
        return SERVICE_HEADER + ":" + SERVICE_NAME + ":" + getServiceName() + ":domain:" + getDomain() + ":realm:" + getRealm() + ":hostname:" + getHostname() + ":" + PORT + ":" + getPort() + ":" + SECURE_PORT + ":" + getSecurePort() + ":" + DIRECT_CONNECTION + ":" + getDirectConnection() + ":" + DATA_COMPRESSION + ":" + getDataCompression() + ":" + CLEAR_TEXT_CONNECTION + ":" + getClearTextConnection() + ":timeout:" + getTimeout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @FireOsSdk
    public ExplicitServiceIdentity(String str) {
        super(str);
        if (matchesUrn(str)) {
            String[] split = str.split(":");
            if (split.length >= 13) {
                for (int i = 5; i < split.length; i += 2) {
                    if (SECURE_PORT.equals(split[i])) {
                        try {
                            this.mSecurePort = Integer.valueOf(Integer.parseInt(split[i + 1]));
                        } catch (NumberFormatException unused) {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid secure port field, expecting integer. urn: ", str));
                        }
                    } else if (DIRECT_CONNECTION.equals(split[i])) {
                        this.mDirectConnection = split[i + 1].trim();
                        if ("".equals(this.mDirectConnection)) {
                            throw new IllegalArgumentException("Direct connection field is empty");
                        }
                    } else if (DATA_COMPRESSION.equals(split[i])) {
                        this.mDataCompression = split[i + 1].trim();
                        if ("".equals(this.mDataCompression)) {
                            throw new IllegalArgumentException("Data Compression field is empty");
                        }
                    } else if (CLEAR_TEXT_CONNECTION.equals(split[i])) {
                        this.mClearTextConnection = split[i + 1].trim();
                        if ("".equals(this.mClearTextConnection)) {
                            throw new IllegalArgumentException("Clear text connection field is empty");
                        }
                    } else if ("timeout".equals(split[i])) {
                        try {
                            this.mTimeout = Integer.valueOf(Integer.parseInt(split[i + 1]));
                        } catch (NumberFormatException unused2) {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid timeout field, expecting integer. urn: ", str));
                        }
                    } else {
                        continue;
                    }
                }
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Too few fields in explicit service urn ", str));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("ExplicitServiceIdentity urn is invalid: ", str));
    }
}
