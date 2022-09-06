package amazon.communication.identity;

import amazon.communication.identity.IRServiceEndpoint;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.URI;
/* loaded from: classes.dex */
public class EndpointIdentityFactory {
    private EndpointIdentityFactory() {
    }

    @FireOsSdk
    public static DeviceIdentity createDeviceIdentity(String str, String str2) {
        return new DeviceIdentity(null, null, null, str, str2);
    }

    @FireOsSdk
    public static EndpointIdentity createExplicitServiceIdentity(String str, String str2, String str3, String str4, Integer num, Integer num2, String str5, String str6, String str7, Integer num3) {
        return new ExplicitServiceIdentity(str, str2, str3, str4, num, num2, str5, str6, str7, num3);
    }

    @FireOsSdk
    public static EndpointIdentity createFromUrn(String str) {
        if (str != null && !"".equals(str.trim())) {
            if (DeviceIdentity.matchesUrn(str)) {
                return new DeviceIdentity(str);
            }
            if (ExplicitServiceIdentity.matchesUrn(str)) {
                return new ExplicitServiceIdentity(str);
            }
            if (ServiceIdentity.matchesUrn(str)) {
                return new ServiceIdentity(str);
            }
            try {
                URI.create(str);
                return new UrlEndpointIdentity(str);
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Invalid URN: ", str, " Urn should map to one of the sub-classes of EndpointIdentity."));
            }
        }
        throw new IllegalArgumentException("URN must be non-empty");
    }

    @FireOsSdk
    @Deprecated
    public static ServiceHostIdentity createServiceHostIdentity(String str, String str2, int i, String str3) {
        throw new UnsupportedOperationException("Deprecated method. Use createServiceIdentity instead");
    }

    @FireOsSdk
    public static ServiceIdentity createServiceIdentity(String str) {
        return createServiceIdentity(str, null, null, null, null);
    }

    @FireOsSdk
    public static UrlEndpointIdentity createUrlEndpointIdentity(String str) {
        return new UrlEndpointIdentity(str);
    }

    @FireOsSdk
    @Deprecated
    public static DeviceIdentity createDeviceIdentity(String str, String str2, String str3, String str4) {
        if (str != null && str.trim().length() != 0) {
            if (str2 != null && str2.trim().length() != 0) {
                return new DeviceIdentity(str, str2, null, str3, str4);
            }
            throw new IllegalArgumentException("customerId must not be null or empty");
        }
        throw new IllegalArgumentException("deviceAccountId must not be null or empty");
    }

    @FireOsSdk
    public static ServiceIdentity createServiceIdentity(String str, String str2) {
        return createServiceIdentity(str, null, str2, null, null);
    }

    @FireOsSdk
    @Deprecated
    public static EndpointIdentity createUrlEndpointIdentity(String str, int i, String str2) {
        return createUrlEndpointIdentity(IRServiceEndpoint.Scheme.WS, str, i, str2);
    }

    @FireOsSdk
    public static ServiceIdentity createServiceIdentity(String str, String str2, String str3) {
        return createServiceIdentity(str, str2, str3, null, null);
    }

    @FireOsSdk
    public static EndpointIdentity createUrlEndpointIdentity(IRServiceEndpoint.Scheme scheme, String str, int i, String str2) {
        if (scheme != null) {
            if (str == null || str.trim().length() == 0) {
                throw new IllegalArgumentException("hostname is null.");
            }
            if (str2 != null) {
                int length = str.length();
                StringBuilder sb = new StringBuilder(str2.length() + length + scheme.toString().length() + 3 + 6);
                sb.append(scheme.toString());
                sb.append("://");
                sb.append(str);
                sb.append(":");
                sb.append(i);
                sb.append("/");
                sb.append(str2);
                return createFromUrn(sb.toString());
            }
            throw new IllegalArgumentException("path is null");
        }
        throw new IllegalArgumentException("scheme is null");
    }

    @FireOsSdk
    public static ServiceIdentity createServiceIdentity(String str, String str2, String str3, String str4, Integer num) {
        return new ServiceIdentity(str, str2, str3, str4, num);
    }

    @FireOsSdk
    public static DeviceIdentity createDeviceIdentity(String str, String str2, String str3) {
        if (str != null && str.trim().length() != 0) {
            return new DeviceIdentity(null, str, null, str2, str3);
        }
        throw new IllegalArgumentException("customerId must not be null or empty");
    }

    @FireOsSdk
    public static DeviceIdentity createDeviceIdentity(String str, String str2, String str3, String str4, String str5) {
        if (str3 != null && str3.trim().length() != 0) {
            return new DeviceIdentity(null, null, str3, str4, str5);
        }
        throw new IllegalArgumentException("directedId must not be null or empty");
    }
}
