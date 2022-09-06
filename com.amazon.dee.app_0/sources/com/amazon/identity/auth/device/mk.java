package com.amazon.identity.auth.device;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mk {
    private static final String TAG = "com.amazon.identity.auth.device.mk";
    private final ByteArrayOutputStream vi = new ByteArrayOutputStream();

    public boolean c(byte[] bArr, long j) {
        this.vi.write(bArr, 0, (int) j);
        return true;
    }

    public Document iI() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(this.vi.toByteArray()));
        } catch (IOException e) {
            String str = TAG;
            io.e(str, "Could not parse xml because of an IOException: " + e.getMessage());
            return null;
        } catch (ParserConfigurationException e2) {
            String str2 = TAG;
            io.e(str2, "Could not parse xml because of parser configuration issue: " + e2.getMessage());
            return null;
        } catch (SAXException e3) {
            String message = e3.getMessage();
            io.e(TAG, "Could not parse xml because it was invalid: ".concat(String.valueOf(message)));
            mq.b("RegistrationError:SAXException", new String[0]);
            if (message.contains("Unexpected end of document")) {
                mq.b("RegistrationError:SAXException:UnexpectedEndOfDocument", new String[0]);
            }
            return null;
        }
    }
}
