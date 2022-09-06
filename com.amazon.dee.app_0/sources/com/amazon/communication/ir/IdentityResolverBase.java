package com.amazon.communication.ir;

import amazon.communication.connection.Purpose;
import amazon.communication.identity.IRServiceEndpoint;
import amazon.communication.identity.IdentityResolver;
import amazon.communication.identity.ServiceIdentity;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.dp.logger.DPLogger;
import com.amazon.org.codehaus.jackson.JsonFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes12.dex */
public class IdentityResolverBase implements IdentityResolver {
    protected static final String IR_FILE_SUFFIX = ".txt";
    public static final String IR_MASTER_FILE_NAME = "ir-master.txt";
    public static final String OVERRIDE_FILE_NAME = "override.txt";
    private static final String SERVICE_ENDPOINT_KEY_DELIMITER = ".";
    protected static final String WILDCARD = "*";
    private List<String> mAllowedDomains;
    private String mDomain;
    private String mPath;
    private String mRealm;
    private static final DPLogger log = new DPLogger("TComm.IdentityResolver");
    protected static final Set<String> LOCALHOST_ALIASES = new HashSet<String>() { // from class: com.amazon.communication.ir.IdentityResolverBase.1
        {
            add(AndroidInfoHelpers.DEVICE_LOCALHOST);
            add("127.0.0.1");
        }
    };
    protected JsonFactory mJFactory = new JsonFactory();
    protected Map<String, IRServiceEndpoint> mServiceEndpointCache = GeneratedOutlineSupport1.outline136();

    public IdentityResolverBase(String str) {
        this.mPath = str;
    }

    private static String buildServiceEndpointKey(String str, String str2, String str3) {
        return GeneratedOutlineSupport1.outline77(str, ".", str2, ".", str3);
    }

    private IRServiceEndpoint getEndpointForServiceNameDomainAndRealm(String str, String str2, String str3) {
        if (str2 == null) {
            str2 = getDomain();
        }
        if (str3 == null) {
            str3 = getRealm();
        }
        IRServiceEndpoint iRServiceEndpoint = this.mServiceEndpointCache.get(buildServiceEndpointKey(str, str2, str3));
        if (iRServiceEndpoint == null && str2.equals("master")) {
            iRServiceEndpoint = this.mServiceEndpointCache.get(buildServiceEndpointKey(str, "prod", str3));
        }
        if (iRServiceEndpoint == null) {
            iRServiceEndpoint = this.mServiceEndpointCache.get(buildServiceEndpointKey(str, str2, "*"));
        }
        if (iRServiceEndpoint == null && str2.equals("master")) {
            iRServiceEndpoint = this.mServiceEndpointCache.get(buildServiceEndpointKey(str, "prod", "*"));
        }
        if (iRServiceEndpoint == null) {
            iRServiceEndpoint = this.mServiceEndpointCache.get(buildServiceEndpointKey(str, "*", str3));
        }
        return iRServiceEndpoint == null ? this.mServiceEndpointCache.get(buildServiceEndpointKey(str, "*", "*")) : iRServiceEndpoint;
    }

    private void verifyValidDomain(String str) throws IRConfigurationException {
        if (LOCALHOST_ALIASES.contains(str)) {
            return;
        }
        for (String str2 : this.mAllowedDomains) {
            if (str.endsWith(str2)) {
                return;
            }
        }
        throw new IRConfigurationException(GeneratedOutlineSupport1.outline72("hostname domain is not allowed: ", str));
    }

    public String getDomain() {
        return this.mDomain;
    }

    @Override // amazon.communication.identity.IdentityResolver
    public IRServiceEndpoint getEndpointForServiceName(String str) {
        return getEndpointForServiceNameDomainAndRealm(str, null, null);
    }

    public String getRealm() {
        return this.mRealm;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v39, types: [com.amazon.dp.logger.DPLoggerBase, com.amazon.dp.logger.DPLogger] */
    /* JADX WARN: Type inference failed for: r0v42, types: [com.amazon.dp.logger.DPLoggerBase, com.amazon.dp.logger.DPLogger] */
    /* JADX WARN: Type inference failed for: r0v45, types: [com.amazon.dp.logger.DPLoggerBase, com.amazon.dp.logger.DPLogger] */
    /* JADX WARN: Type inference failed for: r0v53, types: [com.amazon.dp.logger.DPLoggerBase, com.amazon.dp.logger.DPLogger] */
    /* JADX WARN: Type inference failed for: r10v8, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v13, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v9, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r12v17, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r18v0, types: [com.amazon.communication.ir.IdentityResolverBase] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v7 */
    public void initialize() {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        ?? r2 = "file";
        log.verbose("initialize", "initializing IdentityResolver map", RouteParameter.PATH, this.mPath, "domain", getDomain(), HttpAuthHeader.Parameters.Realm, getRealm());
        File[] listFiles = new File(this.mPath).listFiles();
        if (listFiles != null) {
            log.verbose("initialize", "found IdentityResolver files", "fileList.length", Integer.valueOf(listFiles.length));
            Arrays.sort(listFiles);
            File file = null;
            int i = 2;
            int i2 = 0;
            while (i2 < listFiles.length) {
                if (listFiles[i2].getName().equals(OVERRIDE_FILE_NAME)) {
                    File file2 = listFiles[i2];
                    log.verbose("initialize", "override file found", new Object[0]);
                    file = file2;
                } else if (listFiles[i2].getName().endsWith(IR_FILE_SUFFIX) && !listFiles[i2].getName().equals(IR_MASTER_FILE_NAME)) {
                    try {
                        fileInputStream2 = new FileInputStream(listFiles[i2]);
                        try {
                            try {
                                DPLogger dPLogger = log;
                                Object[] objArr = new Object[i];
                                objArr[0] = "fileName";
                                objArr[1] = listFiles[i2].getName();
                                dPLogger.verbose("initialize", "about to parse file", objArr);
                                parseConfig(fileInputStream2, this.mServiceEndpointCache);
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e) {
                                    ?? r0 = log;
                                    ?? r12 = new Object[3];
                                    r12[0] = r2;
                                    r12[1] = listFiles[i2].getName();
                                    r12[i] = e;
                                    r0.warn("initialize", "error closing FileInputStream", r12);
                                }
                            } catch (Throwable th) {
                                th = th;
                                Throwable th2 = th;
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e2) {
                                        log.warn("initialize", "error closing FileInputStream", new Object[]{r2, listFiles[i2].getName(), e2});
                                    }
                                }
                                throw th2;
                            }
                        } catch (InvalidIRFileFormatException e3) {
                            e = e3;
                            log.error("initialize", "error parsing file", "fileName", listFiles[i2].getName(), e);
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e4) {
                                    log.warn("initialize", "error closing FileInputStream", new Object[]{r2, listFiles[i2].getName(), e4});
                                }
                            }
                            i2++;
                            i = 2;
                        } catch (IOException e5) {
                            e = e5;
                            log.error("initialize", "error parsing file", "fileName", listFiles[i2].getName(), e);
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e6) {
                                    log.warn("initialize", "error closing FileInputStream", new Object[]{r2, listFiles[i2].getName(), e6});
                                }
                            }
                            i2++;
                            i = 2;
                        }
                    } catch (InvalidIRFileFormatException e7) {
                        e = e7;
                        fileInputStream2 = null;
                    } catch (IOException e8) {
                        e = e8;
                        fileInputStream2 = null;
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream2 = null;
                    }
                }
                i2++;
                i = 2;
            }
            try {
                if (file == null) {
                    return;
                }
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        log.verbose("initialize", "about to parse override file.", new Object[0]);
                        parseConfig(fileInputStream, this.mServiceEndpointCache);
                        try {
                            fileInputStream.close();
                            r2 = fileInputStream;
                        } catch (IOException e9) {
                            IOException iOException = e9;
                            log.warn("initialize", "error closing override FileInputStream", iOException);
                            r2 = iOException;
                        }
                    } catch (InvalidIRFileFormatException e10) {
                        e = e10;
                        log.error("initialize", "error parsing file", "fileName", OVERRIDE_FILE_NAME, e);
                        r2 = fileInputStream;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                                r2 = fileInputStream;
                            } catch (IOException e11) {
                                IOException iOException2 = e11;
                                log.warn("initialize", "error closing override FileInputStream", iOException2);
                                r2 = iOException2;
                            }
                        }
                    } catch (IOException e12) {
                        e = e12;
                        log.error("initialize", "error parsing file", "fileName", OVERRIDE_FILE_NAME, e);
                        r2 = fileInputStream;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                                r2 = fileInputStream;
                            } catch (IOException e13) {
                                IOException iOException3 = e13;
                                log.warn("initialize", "error closing override FileInputStream", iOException3);
                                r2 = iOException3;
                            }
                        }
                    }
                } catch (InvalidIRFileFormatException e14) {
                    e = e14;
                    fileInputStream = null;
                } catch (IOException e15) {
                    e = e15;
                    fileInputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    r2 = 0;
                    Throwable th5 = th;
                    if (r2 != 0) {
                        try {
                            r2.close();
                        } catch (IOException e16) {
                            log.warn("initialize", "error closing override FileInputStream", e16);
                        }
                    }
                    throw th5;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("Expecting directory to exist at "), this.mPath, " as part of identity resolver setup"));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x018d, code lost:
        if (r6 == amazon.communication.identity.IRServiceEndpoint.DirectConnection.NOT_ALLOWED) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x018f, code lost:
        if (r13 != null) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0191, code lost:
        com.amazon.communication.ir.IdentityResolverBase.log.warn("parseConfig", "no hostname for endpoint", "serviceName", r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01a5, code lost:
        if (r9 != null) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01a7, code lost:
        if (r14 != null) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01a9, code lost:
        com.amazon.communication.ir.IdentityResolverBase.log.warn("parseConfig", "no insecure or secure port for endpoint", "serviceName", r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01bc, code lost:
        r30.put(buildServiceEndpointKey(r7, r8, r5), new com.amazon.communication.ir.IRServiceEndpointImpl(r13, r8, r5, r6, r11, r12, r15, r9, r14));
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01df, code lost:
        r1 = r28;
        r8 = r9;
        r2 = r27;
        r9 = r6;
        r6 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parseConfig(java.io.InputStream r29, java.util.Map<java.lang.String, amazon.communication.identity.IRServiceEndpoint> r30) throws com.amazon.org.codehaus.jackson.JsonParseException, java.io.IOException, com.amazon.communication.ir.InvalidIRFileFormatException {
        /*
            Method dump skipped, instructions count: 553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.ir.IdentityResolverBase.parseConfig(java.io.InputStream, java.util.Map):void");
    }

    @Override // amazon.communication.identity.IdentityResolver
    public IRServiceEndpoint resolveServiceEndpoint(ServiceIdentity serviceIdentity) {
        return getEndpointForServiceNameDomainAndRealm(serviceIdentity.getServiceName(), serviceIdentity.getDomain(), serviceIdentity.getRealm());
    }

    public void setAllowedDomains(List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.mAllowedDomains = list;
            return;
        }
        throw new IllegalArgumentException("AllowedDomains cannot be null or empty");
    }

    public void setDomain(String str) {
        this.mDomain = str;
    }

    public void setRealm(String str) {
        this.mRealm = str;
    }

    @Override // amazon.communication.identity.IdentityResolver
    public IRServiceEndpoint resolveServiceEndpoint(ServiceIdentity serviceIdentity, Purpose purpose) {
        log.warn("resolveServiceEndpoint", "In this implementation, we'll ignore the purpose", "endpoint", serviceIdentity, "purpose", purpose);
        return resolveServiceEndpoint(serviceIdentity);
    }
}
