package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.device.information.contract.DeviceInformationContract;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType;
import com.amazon.identity.kcpsdk.common.FIRSErrorType;
import com.amazon.identity.kcpsdk.common.KindleWebserviceErrorType;
import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.Map;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ld extends WebResponseParser<lb> implements jx {
    private static final String TAG = "com.amazon.identity.auth.device.ld";
    private final kf dN;
    private final mk rW;
    private lb tT;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.ld$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] tU;
        static final /* synthetic */ int[] tV = new int[KindleWebserviceErrorType.values().length];

        static {
            try {
                tV[KindleWebserviceErrorType.KindleWebserviceErrorTypeDeviceAlreadyRegistered.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                tV[KindleWebserviceErrorType.KindleWebserviceErrorTypeDuplicateDeviceName.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                tV[KindleWebserviceErrorType.KindleWebserviceErrorTypeInternalError.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            tU = new int[FIRSErrorType.values().length];
            try {
                tU[FIRSErrorType.FIRSErrorTypeCustomerNotFound.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                tU[FIRSErrorType.FIRSErrorTypeDeviceAlreadyRegistered.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                tU[FIRSErrorType.FIRSErrorTypeDuplicateAccountName.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                tU[FIRSErrorType.FIRSErrorTypeInternalError.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                tU[FIRSErrorType.FIRSErrorTypeInvalidAccountFound.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public ld() {
        super("com.amazon.identity.kcpsdk.auth.RegisterDeviceResponseParser");
        this.rW = new mk();
        this.dN = new kf();
        this.tT = null;
    }

    private String ay(String str, String str2) {
        if (str2 == null || str2.equals("")) {
            if (str != null && !str.equals("")) {
                return GeneratedOutlineSupport1.outline72(str, "@kindle.com");
            }
            return null;
        }
        return str2;
    }

    private lb b(Document document) {
        Element documentElement = document.getDocumentElement();
        if (documentElement == null || !documentElement.getTagName().equals("response")) {
            return null;
        }
        String f = ml.f(ml.b(documentElement, "serverTime"));
        if (f != null) {
            return new lb(f);
        }
        Element b = ml.b(documentElement, "adp_token");
        Element b2 = ml.b(documentElement, "device_private_key");
        Element a = ml.a(documentElement, "name");
        Element a2 = ml.a(documentElement, "given_name");
        Element a3 = ml.a(documentElement, "user_device_name");
        Element a4 = ml.a(documentElement, "alias");
        Element a5 = ml.a(documentElement, "kindle_email_address");
        Element a6 = ml.a(documentElement, AccountManagerConstants.GetCookiesParams.COOKIES);
        Element b3 = ml.b(documentElement, "store_authentication_cookie");
        Element a7 = ml.a(documentElement, "user_directed_id");
        Element a8 = ml.a(documentElement, "account_pool");
        Element a9 = ml.a(documentElement, "home_region");
        Element a10 = ml.a(documentElement, "country_of_residence");
        Element a11 = ml.a(a10, "source_of_cor");
        Element a12 = ml.a(documentElement, "preferred_marketplace");
        Element a13 = ml.a(documentElement, "identityTokenResponse");
        Map<String, String> d = ml.d(ml.a(documentElement, DeviceInformationContract.DeviceInfo.CONTENT_DIRECTORY));
        String f2 = ml.f(b);
        String f3 = ml.f(b2);
        String f4 = ml.f(a);
        String f5 = ml.f(a2);
        String f6 = ml.f(a3);
        String f7 = ml.f(a4);
        String f8 = ml.f(a5);
        String f9 = ml.f(a7);
        String f10 = ml.f(a8);
        String f11 = ml.f(a9);
        String e = ml.e(a10);
        String f12 = ml.f(a11);
        String f13 = ml.f(a12);
        String ay = ay(f7, f8);
        if (f3 == null && f4 == null && f6 == null && f2 == null) {
            return null;
        }
        Map<String, Map<String, String>> a14 = kd.a(ml.a(documentElement, "deviceCredentials"));
        lb lbVar = new lb(f2, f6, f3, f4, f5, ay);
        String f14 = ml.f(a13);
        if (!TextUtils.isEmpty(f14)) {
            String str = TAG;
            "Received embedded Panda response: ".concat(String.valueOf(f14));
            io.dm(str);
            lr eF = lr.eF(f14);
            if (eF != null) {
                lbVar.c(eF.im());
                lbVar.p(eF.getAccessToken());
                lbVar.q(eF.fW());
                lbVar.c(eF.in());
            }
        }
        lbVar.et(ml.f(b3));
        lbVar.setDirectedId(f9);
        lbVar.j(f10);
        lbVar.k(f11);
        lbVar.l(e);
        lbVar.m(f12);
        lbVar.n(f13);
        lbVar.n(d);
        lbVar.m(this.dN.b(a6));
        lbVar.m(a14);
        return lbVar;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void a(byte[] bArr, long j) {
        this.rW.c(bArr, j);
    }

    lb c(Document document) {
        RegisterDeviceErrorType registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognized;
        lu d = lv.d(document);
        lx e = ly.e(document);
        if (d != null) {
            int i = AnonymousClass1.tU[d.iu().ordinal()];
            if (i == 1) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeCustomerNotFound;
            } else if (i == 2) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceAlreadyRegistered;
            } else if (i == 3) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeDuplicateDeviceName;
            } else if (i == 4) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeInternal;
            } else if (i != 5) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedFirs;
            } else {
                mq.b("PrimaryAccountDeregisteredWhenRegisterSecondary", new String[0]);
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypePrimaryAccountDeregisteredWhenRegisterSecondary;
            }
        } else if (e != null) {
            int i2 = AnonymousClass1.tV[e.iv().ordinal()];
            if (i2 == 1) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceAlreadyRegistered;
            } else if (i2 == 2) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeDuplicateDeviceName;
            } else if (i2 != 3) {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeUnrecognizedKindle;
            } else {
                registerDeviceErrorType = RegisterDeviceErrorType.RegisterDeviceErrorTypeInternal;
            }
        }
        io.a(TAG, "RegisterDeviceResponseParser: response received a %s error.", registerDeviceErrorType.getErrorString());
        new StringBuilder("FIRS returned error: ").append(mi.a(document));
        io.gD();
        return new lb(new la(registerDeviceErrorType));
    }

    @Override // com.amazon.identity.auth.device.jx
    public String g(HttpURLConnection httpURLConnection) {
        InputStream errorStream;
        byte[] a;
        try {
            errorStream = httpURLConnection.getInputStream();
        } catch (IOException unused) {
            errorStream = httpURLConnection.getErrorStream();
        }
        if (errorStream != null) {
            try {
                a = jd.a(errorStream);
            } catch (IOException unused2) {
                return "CannotGetError";
            }
        } else {
            a = null;
        }
        if (a == null) {
            return "CannotGetError";
        }
        mk mkVar = new mk();
        mkVar.c(a, a.length);
        Document iI = mkVar.iI();
        if (iI == null) {
            return "CannotGetError";
        }
        lu d = lv.d(iI);
        if (d != null) {
            return d.iu().getErrorCode();
        }
        return null;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    /* renamed from: hZ */
    public lb hf() {
        return this.tT;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void hg() {
        Document iI = this.rW.iI();
        if (iI == null) {
            b(ParseError.ParseErrorMalformedBody);
        } else {
            this.tT = a(iI);
        }
    }

    private lb a(Document document) {
        lb b = b(document);
        return b == null ? c(document) : b;
    }

    @Override // com.amazon.identity.auth.device.jx
    public Object a(me meVar, byte[] bArr) throws ParseErrorException, IOException {
        long iG = meVar.iG();
        if (iG != 412 && (iG < 200 || iG >= 300)) {
            io.c(TAG, "%s: HTTP Error: %d", "com.amazon.identity.kcpsdk.auth.RegisterDeviceResponseParser", Long.valueOf(iG));
            a(ParseError.ParseErrorHttpError);
        } else {
            if (bArr != null) {
                this.rW.c(bArr, bArr.length);
            }
            io.i(TAG, "Request complete");
            Document iI = this.rW.iI();
            try {
                DOMSource dOMSource = new DOMSource(iI);
                StringWriter stringWriter = new StringWriter();
                TransformerFactory.newInstance().newTransformer().transform(dOMSource, new StreamResult(stringWriter));
                String stringWriter2 = stringWriter.toString();
                io.dm(TAG);
                io.a(stringWriter2, new Object[0]);
            } catch (TransformerException unused) {
                io.e(TAG, "Cannot parse XML.");
            } catch (Exception unused2) {
                io.e(TAG, "Cannot parse XML.");
            }
            if (iI == null) {
                a(ParseError.ParseErrorMalformedBody);
            } else {
                this.tT = a(iI);
            }
        }
        return this.tT;
    }

    private void a(ParseError parseError) throws ParseErrorException {
        io.c(TAG, "Seeing parse error  %s:%s!", getParserName(), parseError.name());
        throw new ParseErrorException(parseError);
    }
}
