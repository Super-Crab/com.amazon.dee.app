package com.sun.mail.smtp;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.MailLogger;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.logging.Level;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes3.dex */
public class DigestMD5 {
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private String clientResponse;
    private MailLogger logger;
    private MessageDigest md5;
    private String uri;

    public DigestMD5(MailLogger mailLogger) {
        this.logger = mailLogger.getLogger(DigestMD5.class, "DEBUG DIGEST-MD5");
        mailLogger.config("DIGEST-MD5 Loaded");
    }

    private static String toHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = b & 255;
            int i3 = i + 1;
            char[] cArr2 = digits;
            cArr[i] = cArr2[i2 >> 4];
            i = i3 + 1;
            cArr[i3] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    private Hashtable tokenize(String str) throws IOException {
        Hashtable hashtable = new Hashtable();
        byte[] bytes = str.getBytes("iso-8859-1");
        StreamTokenizer streamTokenizer = new StreamTokenizer(new InputStreamReader(new BASE64DecoderStream(new ByteArrayInputStream(bytes, 4, bytes.length - 4)), "iso-8859-1"));
        streamTokenizer.ordinaryChars(48, 57);
        streamTokenizer.wordChars(48, 57);
        while (true) {
            String str2 = null;
            while (true) {
                int nextToken = streamTokenizer.nextToken();
                if (nextToken == -1) {
                    return hashtable;
                }
                if (nextToken == -3) {
                    if (str2 != null) {
                        break;
                    }
                    str2 = streamTokenizer.sval;
                } else if (nextToken == 34) {
                    break;
                }
            }
            if (this.logger.isLoggable(Level.FINE)) {
                MailLogger mailLogger = this.logger;
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Received => ", str2, "='");
                outline115.append(streamTokenizer.sval);
                outline115.append("'");
                mailLogger.fine(outline115.toString());
            }
            if (hashtable.containsKey(str2)) {
                hashtable.put(str2, hashtable.get(str2) + "," + streamTokenizer.sval);
            } else {
                hashtable.put(str2, streamTokenizer.sval);
            }
        }
    }

    public byte[] authClient(String str, String str2, String str3, String str4, String str5) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
        try {
            SecureRandom secureRandom = new SecureRandom();
            this.md5 = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            StringBuffer stringBuffer = new StringBuffer();
            this.uri = GeneratedOutlineSupport1.outline72("smtp/", str);
            byte[] bArr = new byte[32];
            this.logger.fine("Begin authentication ...");
            Hashtable hashtable = tokenize(str5);
            if (str4 == null) {
                String str6 = (String) hashtable.get(HttpAuthHeader.Parameters.Realm);
                if (str6 != null) {
                    str = new StringTokenizer(str6, ",").nextToken();
                }
                str4 = str;
            }
            String str7 = (String) hashtable.get("nonce");
            secureRandom.nextBytes(bArr);
            bASE64EncoderStream.write(bArr);
            bASE64EncoderStream.flush();
            String byteArrayOutputStream2 = byteArrayOutputStream.toString("iso-8859-1");
            byteArrayOutputStream.reset();
            MessageDigest messageDigest = this.md5;
            messageDigest.update(messageDigest.digest(ASCIIUtility.getBytes(str2 + ":" + str4 + ":" + str3)));
            MessageDigest messageDigest2 = this.md5;
            messageDigest2.update(ASCIIUtility.getBytes(":" + str7 + ":" + byteArrayOutputStream2));
            StringBuilder sb = new StringBuilder();
            GeneratedOutlineSupport1.outline181(sb, toHex(this.md5.digest()), ":", str7, ":");
            GeneratedOutlineSupport1.outline181(sb, "00000001", ":", byteArrayOutputStream2, ":");
            this.clientResponse = GeneratedOutlineSupport1.outline91(sb, HttpClientModule.ElementsRequestKey.AUTH, ":");
            MessageDigest messageDigest3 = this.md5;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AUTHENTICATE:");
            outline107.append(this.uri);
            messageDigest3.update(ASCIIUtility.getBytes(outline107.toString()));
            MessageDigest messageDigest4 = this.md5;
            messageDigest4.update(ASCIIUtility.getBytes(this.clientResponse + toHex(this.md5.digest())));
            stringBuffer.append("username=\"" + str2 + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            stringBuffer.append(",realm=\"" + str4 + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(",qop=");
            sb2.append(HttpClientModule.ElementsRequestKey.AUTH);
            stringBuffer.append(sb2.toString());
            stringBuffer.append(",nc=00000001");
            stringBuffer.append(",nonce=\"" + str7 + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            stringBuffer.append(",cnonce=\"" + byteArrayOutputStream2 + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            stringBuffer.append(",digest-uri=\"" + this.uri + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            StringBuilder sb3 = new StringBuilder();
            sb3.append(",response=");
            sb3.append(toHex(this.md5.digest()));
            stringBuffer.append(sb3.toString());
            if (this.logger.isLoggable(Level.FINE)) {
                MailLogger mailLogger = this.logger;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Response => ");
                outline1072.append(stringBuffer.toString());
                mailLogger.fine(outline1072.toString());
            }
            bASE64EncoderStream.write(ASCIIUtility.getBytes(stringBuffer.toString()));
            bASE64EncoderStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            this.logger.log(Level.FINE, "NoSuchAlgorithmException", (Throwable) e);
            throw new IOException(e.toString());
        }
    }

    public boolean authServer(String str) throws IOException {
        Hashtable hashtable = tokenize(str);
        MessageDigest messageDigest = this.md5;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(":");
        outline107.append(this.uri);
        messageDigest.update(ASCIIUtility.getBytes(outline107.toString()));
        MessageDigest messageDigest2 = this.md5;
        messageDigest2.update(ASCIIUtility.getBytes(this.clientResponse + toHex(this.md5.digest())));
        String hex = toHex(this.md5.digest());
        if (!hex.equals((String) hashtable.get("rspauth"))) {
            if (!this.logger.isLoggable(Level.FINE)) {
                return false;
            }
            MailLogger mailLogger = this.logger;
            mailLogger.fine("Expected => rspauth=" + hex);
            return false;
        }
        return true;
    }
}
