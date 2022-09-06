package amazon.communication.identity;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.dp.logger.DPLogger;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class ServiceIdentity extends EndpointIdentity {
    private static final String DELIMITER = ":";
    private static final String DOMAIN = "domain";
    private static final String HOSTNAME = "hostname";
    private static final String PORT = "port";
    private static final String REALM = "realm";
    private static final String SERVICE_HEADER = "urn:tcomm-endpoint:service";
    private static final String SERVICE_NAME = "serviceName";
    private static final DPLogger log = new DPLogger("TComm.ServiceIdentity");
    @FireOsSdk
    protected String mDomain;
    @FireOsSdk
    protected String mHostname;
    @FireOsSdk
    protected Integer mPort;
    @FireOsSdk
    protected String mRealm;
    @FireOsSdk
    protected final String mServiceName;

    /* JADX INFO: Access modifiers changed from: package-private */
    @FireOsSdk
    public ServiceIdentity(String str, String str2, String str3, String str4, Integer num) {
        if (str != null && !str.trim().isEmpty()) {
            this.mServiceName = str;
            String str5 = null;
            if (str2 != null && str2.trim().isEmpty()) {
                str2 = null;
            }
            this.mDomain = str2;
            if (str3 != null && str3.trim().isEmpty()) {
                str3 = null;
            }
            this.mRealm = str3;
            this.mHostname = (str4 == null || !str4.trim().isEmpty()) ? str4 : str5;
            this.mPort = num;
            if (this.mHostname == null && this.mPort == null) {
                return;
            }
            if (this.mHostname != null && this.mPort != null) {
                return;
            }
            throw new IllegalArgumentException("Both hostname and port must be specified together");
        }
        throw new IllegalArgumentException("ServiceName cannot be null when constructing ServiceIdentity");
    }

    @FireOsSdk
    public static boolean matchesUrn(String str) {
        return str != null && str.startsWith(SERVICE_HEADER);
    }

    @FireOsSdk
    public String getDomain() {
        return this.mDomain;
    }

    @FireOsSdk
    public String getHostname() {
        return this.mHostname;
    }

    @FireOsSdk
    public Integer getPort() {
        return this.mPort;
    }

    @FireOsSdk
    public String getRealm() {
        return this.mRealm;
    }

    @FireOsSdk
    public String getServiceName() {
        return this.mServiceName;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public EndpointIdentity.Type getType() {
        return EndpointIdentity.Type.SERVICE;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public String toLogSafeString() {
        return toString();
    }

    @Override // amazon.communication.identity.EndpointIdentity
    @FireOsSdk
    public String toString() {
        int length = getServiceName().length() + 39;
        if (this.mDomain != null) {
            length += getDomain().length() + 8;
        }
        if (this.mRealm != null) {
            length += getRealm().length() + 7;
        }
        if (this.mHostname != null && this.mPort != null) {
            length += getPort().toString().length() + getHostname().length() + 10 + 1 + 4 + 1;
        }
        StringBuilder outline106 = GeneratedOutlineSupport1.outline106(length, SERVICE_HEADER, ":", SERVICE_NAME, ":");
        outline106.append(getServiceName());
        if (this.mDomain != null) {
            outline106.append(":");
            outline106.append("domain");
            outline106.append(":");
            outline106.append(getDomain());
        }
        if (this.mRealm != null) {
            outline106.append(":");
            outline106.append("realm");
            outline106.append(":");
            outline106.append(getRealm());
        }
        if (this.mHostname != null && this.mPort != null) {
            outline106.append(":");
            outline106.append("hostname");
            outline106.append(":");
            outline106.append(getHostname());
            outline106.append(":");
            outline106.append(PORT);
            outline106.append(":");
            outline106.append(getPort());
        }
        return outline106.toString();
    }

    @FireOsSdk
    ServiceIdentity() {
        this.mServiceName = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @FireOsSdk
    public ServiceIdentity(String str) {
        if (matchesUrn(str)) {
            String[] split = str.split(":");
            if (split.length >= 5) {
                if (split.length % 2 != 0) {
                    if (SERVICE_NAME.equals(split[3])) {
                        this.mServiceName = split[4].trim();
                        if (!"".equals(this.mServiceName)) {
                            for (int i = 5; i < split.length; i += 2) {
                                if ("domain".equals(split[i])) {
                                    this.mDomain = split[i + 1].trim();
                                    if ("".equals(this.mDomain)) {
                                        throw new IllegalArgumentException("domain field is empty");
                                    }
                                } else if ("realm".equals(split[i])) {
                                    this.mRealm = split[i + 1].trim();
                                    if ("".equals(this.mRealm)) {
                                        throw new IllegalArgumentException("realm field is empty");
                                    }
                                } else if ("hostname".equals(split[i])) {
                                    this.mHostname = split[i + 1].trim();
                                    if ("".equals(this.mHostname)) {
                                        throw new IllegalArgumentException("hostname field is empty");
                                    }
                                } else if (PORT.equals(split[i])) {
                                    try {
                                        this.mPort = Integer.valueOf(Integer.parseInt(split[i + 1]));
                                    } catch (NumberFormatException unused) {
                                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid port field, expecting integer. urn: ", str));
                                    }
                                } else {
                                    continue;
                                }
                            }
                            return;
                        }
                        throw new IllegalArgumentException("serviceName field is empty");
                    }
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Expecting first field in urn to be serviceName, urn: ", str));
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Expecting odd number of fields in service urn: ", str));
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Too few fields in service urn ", str));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("ServiceIdentity urn is invalid: ", str));
    }
}
