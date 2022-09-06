package com.amazon.communication.ir;

import amazon.communication.identity.IRServiceEndpoint;
import com.amazon.dp.logger.DPLogger;
import com.amazon.org.codehaus.jackson.JsonFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class IRServiceEndpointImpl implements IRServiceEndpoint {
    private static JsonFactory sJsonFactory;
    private final IRServiceEndpoint.ClearTextConnection mClearTextConnection;
    private final IRServiceEndpoint.DataCompression mDataCompression;
    private final IRServiceEndpoint.DirectConnection mDirectConnection;
    private final String mDirectorServiceName;
    private final String mDomain;
    private final String mHostname;
    private final Integer mPort;
    private final String mRealm;
    private final Integer mSecurePort;
    private final int mTimeout;
    private static final DPLogger log = new DPLogger("TComm.IRServiceEndpointImpl");
    private static final String NULL_DIRECTOR_SERVICENAME = null;

    public IRServiceEndpointImpl(String str, String str2, String str3, IRServiceEndpoint.DirectConnection directConnection, int i, Integer num, Integer num2) {
        this(str, str2, str3, NULL_DIRECTOR_SERVICENAME, directConnection, IRServiceEndpoint.DataCompression.NOT_NEEDED, IRServiceEndpoint.ClearTextConnection.ALLOWED, i, num, num2);
    }

    private static synchronized JsonFactory getJsonFactory() {
        JsonFactory jsonFactory;
        synchronized (IRServiceEndpointImpl.class) {
            if (sJsonFactory == null) {
                sJsonFactory = new JsonFactory();
            }
            jsonFactory = sJsonFactory;
        }
        return jsonFactory;
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x0163, code lost:
        if (r16 == null) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0173, code lost:
        throw new com.amazon.communication.ir.InvalidIRFileFormatException(com.amazon.dp.logger.DPFormattedMessage.toDPFormat("parse", "neither insecure nor secure port defined", new java.lang.Object[0]));
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0195, code lost:
        if (r16.intValue() <= 0) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01ad, code lost:
        throw new com.amazon.communication.ir.InvalidIRFileFormatException(com.amazon.dp.logger.DPFormattedMessage.toDPFormat("parse", "secure port must be positive", "securePort", r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static amazon.communication.identity.IRServiceEndpoint parse(java.lang.String r19, java.lang.String r20, java.lang.String r21) throws java.io.IOException, com.amazon.communication.ir.InvalidIRFileFormatException {
        /*
            Method dump skipped, instructions count: 554
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.ir.IRServiceEndpointImpl.parse(java.lang.String, java.lang.String, java.lang.String):amazon.communication.identity.IRServiceEndpoint");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof IRServiceEndpoint)) {
            return false;
        }
        IRServiceEndpointImpl iRServiceEndpointImpl = (IRServiceEndpointImpl) obj;
        String str = this.mHostname;
        if (str == null) {
            if (iRServiceEndpointImpl.mHostname != null) {
                return false;
            }
        } else if (!str.equals(iRServiceEndpointImpl.mHostname)) {
            return false;
        }
        String str2 = this.mDomain;
        if (str2 == null) {
            if (iRServiceEndpointImpl.mDomain != null) {
                return false;
            }
        } else if (!str2.equals(iRServiceEndpointImpl.mDomain)) {
            return false;
        }
        String str3 = this.mRealm;
        if (str3 == null) {
            if (iRServiceEndpointImpl.mRealm != null) {
                return false;
            }
        } else if (!str3.equals(iRServiceEndpointImpl.mRealm)) {
            return false;
        }
        String str4 = this.mDirectorServiceName;
        if (str4 == null) {
            if (iRServiceEndpointImpl.mDirectorServiceName != null) {
                return false;
            }
        } else if (!str4.equals(iRServiceEndpointImpl.mDirectorServiceName)) {
            return false;
        }
        if (this.mTimeout != iRServiceEndpointImpl.mTimeout) {
            return false;
        }
        IRServiceEndpoint.DirectConnection directConnection = this.mDirectConnection;
        if (directConnection == null) {
            if (iRServiceEndpointImpl.mDirectConnection != null) {
                return false;
            }
        } else if (!directConnection.equals(iRServiceEndpointImpl.mDirectConnection)) {
            return false;
        }
        IRServiceEndpoint.DataCompression dataCompression = this.mDataCompression;
        if (dataCompression == null) {
            if (iRServiceEndpointImpl.mDataCompression != null) {
                return false;
            }
        } else if (!dataCompression.equals(iRServiceEndpointImpl.mDataCompression)) {
            return false;
        }
        IRServiceEndpoint.ClearTextConnection clearTextConnection = this.mClearTextConnection;
        if (clearTextConnection == null) {
            if (iRServiceEndpointImpl.mClearTextConnection != null) {
                return false;
            }
        } else if (!clearTextConnection.equals(iRServiceEndpointImpl.mClearTextConnection)) {
            return false;
        }
        Integer num = this.mPort;
        if (num == null) {
            if (iRServiceEndpointImpl.mPort != null) {
                return false;
            }
        } else if (!num.equals(iRServiceEndpointImpl.mPort)) {
            return false;
        }
        Integer num2 = this.mSecurePort;
        if (num2 == null) {
            if (iRServiceEndpointImpl.mSecurePort != null) {
                return false;
            }
        } else if (!num2.equals(iRServiceEndpointImpl.mSecurePort)) {
            return false;
        }
        return true;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public IRServiceEndpoint.ClearTextConnection getClearTextConnection() {
        return this.mClearTextConnection;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public IRServiceEndpoint.DataCompression getDataCompression() {
        return this.mDataCompression;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public IRServiceEndpoint.DirectConnection getDirectConnection() {
        return this.mDirectConnection;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String getDirectorServiceName() {
        return this.mDirectorServiceName;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String getDomain() {
        return this.mDomain;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String getHostname() {
        return this.mHostname;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public Integer getPort() {
        return this.mPort;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String getRealm() {
        return this.mRealm;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public Integer getSecurePort() {
        return this.mSecurePort;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public int getTimeout() {
        return this.mTimeout;
    }

    public int hashCode() {
        String str = this.mHostname;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) + 37) * 37;
        String str2 = this.mDomain;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 37;
        String str3 = this.mRealm;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 37;
        String str4 = this.mDirectorServiceName;
        int hashCode4 = (((hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 37) + this.mTimeout) * 37;
        IRServiceEndpoint.DirectConnection directConnection = this.mDirectConnection;
        int hashCode5 = (hashCode4 + (directConnection == null ? 0 : directConnection.hashCode())) * 37;
        IRServiceEndpoint.DataCompression dataCompression = this.mDataCompression;
        int hashCode6 = (hashCode5 + (dataCompression == null ? 0 : dataCompression.hashCode())) * 37;
        IRServiceEndpoint.ClearTextConnection clearTextConnection = this.mClearTextConnection;
        int hashCode7 = (hashCode6 + (clearTextConnection == null ? 0 : clearTextConnection.hashCode())) * 37;
        Integer num = this.mPort;
        int intValue = (hashCode7 + (num == null ? 0 : num.intValue())) * 37;
        Integer num2 = this.mSecurePort;
        if (num2 != null) {
            i = num2.intValue();
        }
        return intValue + i;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String toResolvedUri(IRServiceEndpoint.Scheme scheme) {
        if (getHostname() != null) {
            boolean z = (this.mClearTextConnection == IRServiceEndpoint.ClearTextConnection.NOT_ALLOWED) || (scheme == IRServiceEndpoint.Scheme.WSS || scheme == IRServiceEndpoint.Scheme.HTTPS);
            IRServiceEndpoint.Scheme scheme2 = (!z || scheme != IRServiceEndpoint.Scheme.WS) ? scheme : IRServiceEndpoint.Scheme.WSS;
            if (z && scheme == IRServiceEndpoint.Scheme.HTTP) {
                scheme2 = IRServiceEndpoint.Scheme.HTTPS;
            }
            Integer securePort = z ? getSecurePort() : getPort();
            if (securePort != null) {
                return String.format("%s://%s:%d", scheme2.toString(), getHostname(), securePort);
            }
            throw new IRConfigurationException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Cannot create URI. No "), z ? "secure " : "", "port defined in IR config"));
        }
        throw new IRConfigurationException("Cannot create URI. No hostname defined in IR config");
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Service Endpoint on domain: ");
        outline107.append(this.mDomain);
        outline107.append(" - realm: ");
        outline107.append(this.mRealm);
        outline107.append(" - directorServiceName: ");
        outline107.append(this.mDirectorServiceName);
        outline107.append(" - hostname: ");
        outline107.append(this.mHostname);
        outline107.append(" - port: ");
        outline107.append(this.mPort);
        outline107.append(" - secure port : ");
        outline107.append(this.mSecurePort);
        outline107.append(" - Direct connection ");
        outline107.append(this.mDirectConnection);
        outline107.append(", - Data compression ");
        outline107.append(this.mDataCompression);
        outline107.append(", - Clear text connection ");
        outline107.append(this.mClearTextConnection);
        outline107.append(", ");
        return GeneratedOutlineSupport1.outline86(outline107, this.mTimeout, " timeout");
    }

    public IRServiceEndpointImpl(String str, String str2, String str3, IRServiceEndpoint.DirectConnection directConnection, IRServiceEndpoint.DataCompression dataCompression, IRServiceEndpoint.ClearTextConnection clearTextConnection, int i, Integer num, Integer num2) {
        this(str, str2, str3, NULL_DIRECTOR_SERVICENAME, directConnection, dataCompression, clearTextConnection, i, num, num2);
    }

    public IRServiceEndpointImpl(String str, String str2, String str3, String str4, IRServiceEndpoint.DirectConnection directConnection, IRServiceEndpoint.DataCompression dataCompression, IRServiceEndpoint.ClearTextConnection clearTextConnection, int i, Integer num, Integer num2) {
        this.mHostname = str;
        this.mDomain = str2;
        this.mRealm = str3;
        this.mDirectorServiceName = str4;
        this.mTimeout = i;
        this.mDirectConnection = directConnection;
        this.mDataCompression = dataCompression;
        this.mClearTextConnection = clearTextConnection;
        this.mPort = num;
        this.mSecurePort = num2;
    }
}
