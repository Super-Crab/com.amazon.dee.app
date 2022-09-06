package com.amazonaws.mobileconnectors.iot;

import com.amazonaws.util.Base64;
import com.amazonaws.util.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes13.dex */
final class PEM {
    private static final String BEGIN_MARKER = "-----BEGIN ";

    /* renamed from: com.amazonaws.mobileconnectors.iot.PEM$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazonaws$mobileconnectors$iot$PEMObjectType = new int[PEMObjectType.values().length];

        static {
            try {
                $SwitchMap$com$amazonaws$mobileconnectors$iot$PEMObjectType[PEMObjectType.PRIVATE_KEY_PKCS1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private PEM() {
    }

    public static List<PEMObject> readPEMObjects(InputStream inputStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StringUtils.UTF8));
        String str = null;
        StringBuffer stringBuffer = null;
        String str2 = null;
        while (true) {
            boolean z = false;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return arrayList;
                    }
                    if (z) {
                        if (readLine.indexOf(str) != -1) {
                            break;
                        }
                        stringBuffer.append(readLine.trim());
                    } else if (readLine.indexOf(BEGIN_MARKER) != -1) {
                        z = true;
                        str2 = readLine.trim();
                        str = str2.replace("BEGIN", "END");
                        stringBuffer = new StringBuffer();
                    }
                } finally {
                    bufferedReader.close();
                }
            }
            arrayList.add(new PEMObject(str2, Base64.decode(stringBuffer.toString())));
        }
    }

    public static PrivateKey readPrivateKey(InputStream inputStream) throws InvalidKeySpecException, IOException {
        for (PEMObject pEMObject : readPEMObjects(inputStream)) {
            if (pEMObject.getPEMObjectType().ordinal() == 0) {
                return RSA.privateKeyFromPKCS1(pEMObject.getDerBytes());
            }
        }
        throw new IllegalArgumentException("Found no private key");
    }
}
