package com.sun.mail.auth;

import com.amazon.alexa.accessory.internal.bluetooth.GenericAccessProfile;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.MailLogger;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.logging.Level;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class Ntlm {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private Cipher cipher;
    private SecretKeyFactory fac;
    private String hostname;
    private MailLogger logger;
    private MD4 md4;
    private String ntdomain;
    private String password;
    private byte[] type1;
    private byte[] type3;
    private String username;

    public Ntlm(String str, String str2, String str3, String str4, MailLogger mailLogger) {
        int indexOf = str2.indexOf(46);
        str2 = indexOf != -1 ? str2.substring(0, indexOf) : str2;
        int indexOf2 = str3.indexOf(92);
        if (indexOf2 != -1) {
            str = str3.substring(0, indexOf2).toUpperCase(Locale.ENGLISH);
            str3 = str3.substring(indexOf2 + 1);
        } else if (str == null) {
            str = "";
        }
        this.ntdomain = str;
        this.hostname = str2;
        this.username = str3;
        this.password = str4;
        this.logger = mailLogger.getLogger(Ntlm.class, "DEBUG NTLM");
        init0();
    }

    private byte[] calcLMHash() throws GeneralSecurityException {
        byte[] bArr;
        byte[] bArr2 = {75, 71, 83, GenericAccessProfile.SERVICE_DATA_128BIT, 64, GenericAccessProfile.LE_SECURE_CONNECTIONS_RANDOM_VALUE, GenericAccessProfile.URI, GenericAccessProfile.INDOOR_POSITIONING};
        try {
            bArr = this.password.toUpperCase(Locale.ENGLISH).getBytes("iso-8859-1");
        } catch (UnsupportedEncodingException unused) {
            bArr = null;
        }
        int i = 14;
        byte[] bArr3 = new byte[14];
        int length = this.password.length();
        if (length <= 14) {
            i = length;
        }
        System.arraycopy(bArr, 0, bArr3, 0, i);
        DESKeySpec dESKeySpec = new DESKeySpec(makeDesKey(bArr3, 0));
        DESKeySpec dESKeySpec2 = new DESKeySpec(makeDesKey(bArr3, 7));
        SecretKey generateSecret = this.fac.generateSecret(dESKeySpec);
        SecretKey generateSecret2 = this.fac.generateSecret(dESKeySpec2);
        this.cipher.init(1, generateSecret);
        byte[] doFinal = this.cipher.doFinal(bArr2, 0, 8);
        this.cipher.init(1, generateSecret2);
        byte[] doFinal2 = this.cipher.doFinal(bArr2, 0, 8);
        byte[] bArr4 = new byte[21];
        System.arraycopy(doFinal, 0, bArr4, 0, 8);
        System.arraycopy(doFinal2, 0, bArr4, 8, 8);
        return bArr4;
    }

    private byte[] calcNTHash() throws GeneralSecurityException {
        byte[] bArr;
        try {
            bArr = this.password.getBytes("UnicodeLittleUnmarked");
        } catch (UnsupportedEncodingException unused) {
            bArr = null;
        }
        byte[] bArr2 = new byte[21];
        System.arraycopy(this.md4.digest(bArr), 0, bArr2, 0, 16);
        return bArr2;
    }

    private byte[] calcResponse(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        DESKeySpec dESKeySpec = new DESKeySpec(makeDesKey(bArr, 0));
        DESKeySpec dESKeySpec2 = new DESKeySpec(makeDesKey(bArr, 7));
        DESKeySpec dESKeySpec3 = new DESKeySpec(makeDesKey(bArr, 14));
        SecretKey generateSecret = this.fac.generateSecret(dESKeySpec);
        SecretKey generateSecret2 = this.fac.generateSecret(dESKeySpec2);
        SecretKey generateSecret3 = this.fac.generateSecret(dESKeySpec3);
        this.cipher.init(1, generateSecret);
        byte[] doFinal = this.cipher.doFinal(bArr2, 0, 8);
        this.cipher.init(1, generateSecret2);
        byte[] doFinal2 = this.cipher.doFinal(bArr2, 0, 8);
        this.cipher.init(1, generateSecret3);
        byte[] doFinal3 = this.cipher.doFinal(bArr2, 0, 8);
        byte[] bArr3 = new byte[24];
        System.arraycopy(doFinal, 0, bArr3, 0, 8);
        System.arraycopy(doFinal2, 0, bArr3, 8, 8);
        System.arraycopy(doFinal3, 0, bArr3, 16, 8);
        return bArr3;
    }

    private void copybytes(byte[] bArr, int i, String str, String str2) {
        try {
            byte[] bytes = str.getBytes(str2);
            System.arraycopy(bytes, 0, bArr, i, bytes.length);
        } catch (UnsupportedEncodingException unused) {
        }
    }

    private void init0() {
        this.type1 = new byte[256];
        this.type3 = new byte[256];
        System.arraycopy(new byte[]{78, 84, 76, 77, 83, 83, 80, 0, 1}, 0, this.type1, 0, 9);
        byte[] bArr = this.type1;
        bArr[12] = 3;
        bArr[13] = -78;
        bArr[28] = 32;
        System.arraycopy(new byte[]{78, 84, 76, 77, 83, 83, 80, 0, 3}, 0, this.type3, 0, 9);
        byte[] bArr2 = this.type3;
        bArr2[12] = 24;
        bArr2[14] = 24;
        bArr2[20] = 24;
        bArr2[22] = 24;
        bArr2[32] = 64;
        bArr2[60] = 1;
        bArr2[61] = -126;
        try {
            this.fac = SecretKeyFactory.getInstance("DES");
            this.cipher = Cipher.getInstance("DES/ECB/NoPadding");
            this.md4 = new MD4();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] makeDesKey(byte[] bArr, int i) {
        int[] iArr = new int[bArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = bArr[i2] < 0 ? bArr[i2] + 256 : bArr[i2];
        }
        int i3 = i + 0;
        int i4 = i + 1;
        int i5 = i + 2;
        int i6 = i + 3;
        int i7 = i + 4;
        int i8 = i + 5;
        int i9 = i + 6;
        return new byte[]{(byte) iArr[i3], (byte) (((iArr[i3] << 7) & 255) | (iArr[i4] >> 1)), (byte) (((iArr[i4] << 6) & 255) | (iArr[i5] >> 2)), (byte) (((iArr[i5] << 5) & 255) | (iArr[i6] >> 3)), (byte) (((iArr[i6] << 4) & 255) | (iArr[i7] >> 4)), (byte) (((iArr[i7] << 3) & 255) | (iArr[i8] >> 5)), (byte) (((iArr[i8] << 2) & 255) | (iArr[i9] >> 6)), (byte) ((iArr[i9] << 1) & 255)};
    }

    private static String toHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 3);
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(hex[(bArr[i] >> 4) & 15]);
            stringBuffer.append(hex[bArr[i] & 15]);
            stringBuffer.append(Chars.SPACE);
        }
        return stringBuffer.toString();
    }

    public String generateType1Msg(int i) {
        int length = this.ntdomain.length();
        byte[] bArr = this.type1;
        bArr[16] = (byte) (length % 256);
        bArr[17] = (byte) (length / 256);
        bArr[18] = bArr[16];
        bArr[19] = bArr[17];
        if (length == 0) {
            bArr[13] = (byte) (bArr[13] & ByteSourceJsonBootstrapper.UTF8_BOM_1);
        }
        int length2 = this.hostname.length();
        byte[] bArr2 = this.type1;
        bArr2[24] = (byte) (length2 % 256);
        bArr2[25] = (byte) (length2 / 256);
        bArr2[26] = bArr2[24];
        bArr2[27] = bArr2[25];
        copybytes(bArr2, 32, this.hostname, "iso-8859-1");
        int i2 = length2 + 32;
        copybytes(this.type1, i2, this.ntdomain, "iso-8859-1");
        byte[] bArr3 = this.type1;
        bArr3[20] = (byte) (i2 % 256);
        bArr3[21] = (byte) (i2 / 256);
        int i3 = i2 + length;
        byte[] bArr4 = new byte[i3];
        System.arraycopy(bArr3, 0, bArr4, 0, i3);
        if (this.logger.isLoggable(Level.FINE)) {
            MailLogger mailLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("type 1 message: ");
            outline107.append(toHex(bArr4));
            mailLogger.fine(outline107.toString());
        }
        try {
            return new String(BASE64EncoderStream.encode(bArr4), "iso-8859-1");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public String generateType3Msg(String str) {
        byte[] bArr;
        try {
            try {
                bArr = BASE64DecoderStream.decode(str.getBytes("us-ascii"));
            } catch (UnsupportedEncodingException unused) {
                bArr = null;
            }
            byte[] bArr2 = new byte[8];
            System.arraycopy(bArr, 24, bArr2, 0, 8);
            int length = this.username.length() * 2;
            byte[] bArr3 = this.type3;
            byte b = (byte) (length % 256);
            this.type3[38] = b;
            bArr3[36] = b;
            byte[] bArr4 = this.type3;
            byte b2 = (byte) (length / 256);
            this.type3[39] = b2;
            bArr4[37] = b2;
            int length2 = this.ntdomain.length() * 2;
            byte[] bArr5 = this.type3;
            byte b3 = (byte) (length2 % 256);
            this.type3[30] = b3;
            bArr5[28] = b3;
            byte[] bArr6 = this.type3;
            byte b4 = (byte) (length2 / 256);
            this.type3[31] = b4;
            bArr6[29] = b4;
            int length3 = this.hostname.length() * 2;
            byte[] bArr7 = this.type3;
            byte b5 = (byte) (length3 % 256);
            this.type3[46] = b5;
            bArr7[44] = b5;
            byte[] bArr8 = this.type3;
            byte b6 = (byte) (length3 / 256);
            this.type3[47] = b6;
            bArr8[45] = b6;
            copybytes(this.type3, 64, this.ntdomain, "UnicodeLittleUnmarked");
            this.type3[32] = (byte) 64;
            this.type3[33] = (byte) 0;
            int i = length2 + 64;
            copybytes(this.type3, i, this.username, "UnicodeLittleUnmarked");
            this.type3[40] = (byte) (i % 256);
            this.type3[41] = (byte) (i / 256);
            int i2 = i + length;
            copybytes(this.type3, i2, this.hostname, "UnicodeLittleUnmarked");
            this.type3[48] = (byte) (i2 % 256);
            this.type3[49] = (byte) (i2 / 256);
            int i3 = i2 + length3;
            byte[] calcResponse = calcResponse(calcLMHash(), bArr2);
            byte[] calcResponse2 = calcResponse(calcNTHash(), bArr2);
            System.arraycopy(calcResponse, 0, this.type3, i3, 24);
            this.type3[16] = (byte) (i3 % 256);
            this.type3[17] = (byte) (i3 / 256);
            int i4 = i3 + 24;
            System.arraycopy(calcResponse2, 0, this.type3, i4, 24);
            this.type3[24] = (byte) (i4 % 256);
            this.type3[25] = (byte) (i4 / 256);
            int i5 = i4 + 24;
            this.type3[56] = (byte) (i5 % 256);
            this.type3[57] = (byte) (i5 / 256);
            byte[] bArr9 = new byte[i5];
            System.arraycopy(this.type3, 0, bArr9, 0, i5);
            if (this.logger.isLoggable(Level.FINE)) {
                this.logger.fine("type 3 message: " + toHex(bArr9));
            }
            try {
                return new String(BASE64EncoderStream.encode(bArr9), "iso-8859-1");
            } catch (UnsupportedEncodingException unused2) {
                return null;
            }
        } catch (GeneralSecurityException e) {
            this.logger.log(Level.FINE, "GeneralSecurityException", (Throwable) e);
            return "";
        }
    }
}
