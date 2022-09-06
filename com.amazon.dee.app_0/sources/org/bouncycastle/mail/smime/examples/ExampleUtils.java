package org.bouncycastle.mail.smime.examples;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.KeyStore;
import java.util.Enumeration;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
/* loaded from: classes4.dex */
public class ExampleUtils {
    public static void dumpContent(MimeBodyPart mimeBodyPart, String str) throws MessagingException, IOException {
        PrintStream printStream = System.out;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("content type: ");
        outline107.append(mimeBodyPart.getContentType());
        printStream.println(outline107.toString());
        FileOutputStream fileOutputStream = new FileOutputStream(str);
        InputStream inputStream = mimeBodyPart.getInputStream();
        byte[] bArr = new byte[10000];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read <= 0) {
                fileOutputStream.close();
                return;
            }
            fileOutputStream.write(bArr, 0, read);
        }
    }

    public static String findKeyAlias(KeyStore keyStore, String str, char[] cArr) throws Exception {
        keyStore.load(new FileInputStream(str), cArr);
        Enumeration<String> aliases = keyStore.aliases();
        String str2 = null;
        while (aliases.hasMoreElements()) {
            String nextElement = aliases.nextElement();
            if (keyStore.isKeyEntry(nextElement)) {
                str2 = nextElement;
            }
        }
        if (str2 != null) {
            return str2;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("can't find a private key in keyStore: ", str));
    }
}
