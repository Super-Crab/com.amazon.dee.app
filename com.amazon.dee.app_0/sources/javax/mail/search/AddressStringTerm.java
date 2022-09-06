package javax.mail.search;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
/* loaded from: classes3.dex */
public abstract class AddressStringTerm extends StringTerm {
    private static final long serialVersionUID = 3086821234204980368L;

    /* JADX INFO: Access modifiers changed from: protected */
    public AddressStringTerm(String str) {
        super(str, true);
    }

    @Override // javax.mail.search.StringTerm
    public boolean equals(Object obj) {
        if (!(obj instanceof AddressStringTerm)) {
            return false;
        }
        return super.equals(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean match(Address address) {
        if (address instanceof InternetAddress) {
            return super.match(((InternetAddress) address).toUnicodeString());
        }
        return super.match(address.toString());
    }
}
