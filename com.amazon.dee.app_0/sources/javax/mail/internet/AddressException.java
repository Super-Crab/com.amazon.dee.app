package javax.mail.internet;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes3.dex */
public class AddressException extends ParseException {
    private static final long serialVersionUID = 9134583443539323120L;
    protected int pos;
    protected String ref;

    public AddressException() {
        this.ref = null;
        this.pos = -1;
    }

    public int getPos() {
        return this.pos;
    }

    public String getRef() {
        return this.ref;
    }

    @Override // javax.mail.MessagingException, java.lang.Throwable
    public String toString() {
        String messagingException = super.toString();
        if (this.ref == null) {
            return messagingException;
        }
        String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline113(messagingException, " in string ``"), this.ref, "''");
        if (this.pos < 0) {
            return outline91;
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(outline91, " at position ");
        outline113.append(this.pos);
        return outline113.toString();
    }

    public AddressException(String str) {
        super(str);
        this.ref = null;
        this.pos = -1;
    }

    public AddressException(String str, String str2) {
        super(str);
        this.ref = null;
        this.pos = -1;
        this.ref = str2;
    }

    public AddressException(String str, String str2, int i) {
        super(str);
        this.ref = null;
        this.pos = -1;
        this.ref = str2;
        this.pos = i;
    }
}
