package javax.mail.internet;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.identity.PersistentUserIdentityStorage;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.util.PropUtil;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import javax.mail.Address;
import javax.mail.Session;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class InternetAddress extends Address implements Cloneable {
    private static final boolean ignoreBogusGroupName = PropUtil.getBooleanSystemProperty("mail.mime.address.ignorebogusgroupname", true);
    private static final String rfc822phrase = HeaderTokenizer.RFC822.replace((char) Chars.SPACE, (char) 0).replace('\t', (char) 0);
    private static final long serialVersionUID = -7507595530758302903L;
    private static final String specialsNoDot = "()<>,;:\\\"[]@";
    private static final String specialsNoDotNoAt = "()<>,;:\\\"[]";
    protected String address;
    protected String encodedPersonal;
    protected String personal;

    public InternetAddress() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InternetAddress _getLocalAddress(Session session) throws SecurityException, AddressException, UnknownHostException {
        String property;
        String str;
        String str2;
        if (session == null) {
            property = null;
            str2 = System.getProperty(PersistentUserIdentityStorage.StorageKey.USER_NAME);
            str = getLocalHostName();
        } else {
            property = session.getProperty("mail.from");
            if (property == null) {
                String property2 = session.getProperty("mail.user");
                if (property2 == null || property2.length() == 0) {
                    property2 = session.getProperty(PersistentUserIdentityStorage.StorageKey.USER_NAME);
                }
                str2 = (property2 == null || property2.length() == 0) ? System.getProperty(PersistentUserIdentityStorage.StorageKey.USER_NAME) : property2;
                str = session.getProperty("mail.host");
                if (str == null || str.length() == 0) {
                    str = getLocalHostName();
                }
            } else {
                str = null;
                str2 = null;
            }
        }
        if (property == null && str2 != null && str2.length() != 0 && str != null && str.length() != 0) {
            property = MimeUtility.quote(str2.trim(), "()<>,;:\\\"[]@\t ") + "@" + str;
        }
        if (property == null) {
            return null;
        }
        return new InternetAddress(property);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x011c, code lost:
        throw new javax.mail.internet.AddressException("Domain starts with dot", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0124, code lost:
        throw new javax.mail.internet.AddressException("Missing domain", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x012c, code lost:
        throw new javax.mail.internet.AddressException("Unterminated quote", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ac, code lost:
        throw new javax.mail.internet.AddressException("Local address contains control or whitespace", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00b3, code lost:
        if (r5 != false) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b5, code lost:
        if (r4 == '@') goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b7, code lost:
        if (r13 != false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00b9, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00c1, code lost:
        throw new javax.mail.internet.AddressException("Missing final '@domain'", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00c2, code lost:
        r12 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00c3, code lost:
        if (r12 >= r0) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00cb, code lost:
        if (r11.charAt(r12) == '.') goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00cd, code lost:
        if (r12 >= r0) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00cf, code lost:
        r13 = r11.charAt(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00d5, code lost:
        if (r13 != '[') goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00d7, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00d8, code lost:
        if (r13 <= ' ') goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00da, code lost:
        if (r13 >= 127) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00e0, code lost:
        if (java.lang.Character.isLetterOrDigit(r13) != false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00e4, code lost:
        if (r13 == '-') goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00e6, code lost:
        if (r13 != '.') goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00f0, code lost:
        throw new javax.mail.internet.AddressException("Domain contains illegal character", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00f1, code lost:
        if (r13 != '.') goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x00f3, code lost:
        if (r6 == '.') goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00fd, code lost:
        throw new javax.mail.internet.AddressException("Domain contains dot-dot", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00fe, code lost:
        r12 = r12 + 1;
        r6 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0109, code lost:
        throw new javax.mail.internet.AddressException("Domain contains control or whitespace", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x010a, code lost:
        if (r6 == '.') goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x010c, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0114, code lost:
        throw new javax.mail.internet.AddressException("Domain ends with dot", r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void checkAddress(java.lang.String r11, boolean r12, boolean r13) throws javax.mail.internet.AddressException {
        /*
            Method dump skipped, instructions count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.InternetAddress.checkAddress(java.lang.String, boolean, boolean):void");
    }

    public static InternetAddress getLocalAddress(Session session) {
        try {
            return _getLocalAddress(session);
        } catch (SecurityException | UnknownHostException | AddressException unused) {
            return null;
        }
    }

    private static String getLocalHostName() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        if (localHost != null) {
            String hostName = localHost.getHostName();
            if (hostName == null || hostName.length() <= 0 || !isInetAddressLiteral(hostName)) {
                return hostName;
            }
            return JsonReaderKt.BEGIN_LIST + hostName + JsonReaderKt.END_LIST;
        }
        return null;
    }

    private static int indexOfAny(String str, String str2) {
        return indexOfAny(str, str2, 0);
    }

    private static boolean isInetAddressLiteral(String str) {
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ((charAt < '0' || charAt > '9') && charAt != '.') {
                if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                    z = true;
                } else if (charAt != ':') {
                    return false;
                } else {
                    z2 = true;
                }
            }
        }
        return !z || z2;
    }

    private boolean isSimple() {
        String str = this.address;
        return str == null || indexOfAny(str, specialsNoDotNoAt) < 0;
    }

    private static int lengthOfFirstSegment(String str) {
        int indexOf = str.indexOf("\r\n");
        return indexOf != -1 ? indexOf : str.length();
    }

    private static int lengthOfLastSegment(String str, int i) {
        int lastIndexOf = str.lastIndexOf("\r\n");
        if (lastIndexOf != -1) {
            return (str.length() - lastIndexOf) - 2;
        }
        return str.length() + i;
    }

    public static InternetAddress[] parse(String str) throws AddressException {
        return parse(str, true);
    }

    public static InternetAddress[] parseHeader(String str, boolean z) throws AddressException {
        return parse(MimeUtility.unfold(str), z, true);
    }

    private static String quotePhrase(String str) {
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != '\"' && charAt != '\\') {
                if ((charAt < ' ' && charAt != '\r' && charAt != '\n' && charAt != '\t') || charAt >= 127 || rfc822phrase.indexOf(charAt) >= 0) {
                    z = true;
                }
            } else {
                StringBuffer stringBuffer = new StringBuffer(length + 3);
                stringBuffer.append('\"');
                for (int i2 = 0; i2 < length; i2++) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == '\"' || charAt2 == '\\') {
                        stringBuffer.append('\\');
                    }
                    stringBuffer.append(charAt2);
                }
                stringBuffer.append('\"');
                return stringBuffer.toString();
            }
        }
        if (z) {
            StringBuffer stringBuffer2 = new StringBuffer(length + 2);
            stringBuffer2.append('\"');
            stringBuffer2.append(str);
            stringBuffer2.append('\"');
            return stringBuffer2.toString();
        }
        return str;
    }

    private static String unquote(String str) {
        if (!str.startsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) || !str.endsWith(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED) || str.length() <= 1) {
            return str;
        }
        String outline51 = GeneratedOutlineSupport1.outline51(str, 1, 1);
        if (outline51.indexOf(92) < 0) {
            return outline51;
        }
        StringBuffer stringBuffer = new StringBuffer(outline51.length());
        int i = 0;
        while (i < outline51.length()) {
            char charAt = outline51.charAt(i);
            if (charAt == '\\' && i < outline51.length() - 1) {
                i++;
                charAt = outline51.charAt(i);
            }
            stringBuffer.append(charAt);
            i++;
        }
        return stringBuffer.toString();
    }

    public Object clone() {
        try {
            return (InternetAddress) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // javax.mail.Address
    public boolean equals(Object obj) {
        if (!(obj instanceof InternetAddress)) {
            return false;
        }
        String address = ((InternetAddress) obj).getAddress();
        String str = this.address;
        if (address == str) {
            return true;
        }
        return str != null && str.equalsIgnoreCase(address);
    }

    public String getAddress() {
        return this.address;
    }

    public InternetAddress[] getGroup(boolean z) throws AddressException {
        int indexOf;
        String address = getAddress();
        if (address != null && address.endsWith(";") && (indexOf = address.indexOf(58)) >= 0) {
            return parseHeader(address.substring(indexOf + 1, address.length() - 1), z);
        }
        return null;
    }

    public String getPersonal() {
        String str = this.personal;
        if (str != null) {
            return str;
        }
        String str2 = this.encodedPersonal;
        if (str2 == null) {
            return null;
        }
        try {
            this.personal = MimeUtility.decodeText(str2);
            return this.personal;
        } catch (Exception unused) {
            return this.encodedPersonal;
        }
    }

    @Override // javax.mail.Address
    public String getType() {
        return "rfc822";
    }

    public int hashCode() {
        String str = this.address;
        if (str == null) {
            return 0;
        }
        return str.toLowerCase(Locale.ENGLISH).hashCode();
    }

    public boolean isGroup() {
        String str = this.address;
        return str != null && str.endsWith(";") && this.address.indexOf(58) > 0;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setPersonal(String str, String str2) throws UnsupportedEncodingException {
        this.personal = str;
        if (str != null) {
            this.encodedPersonal = MimeUtility.encodeWord(str, str2, null);
        } else {
            this.encodedPersonal = null;
        }
    }

    @Override // javax.mail.Address
    public String toString() {
        String str;
        String str2 = this.address;
        if (str2 == null) {
            str2 = "";
        }
        if (this.encodedPersonal == null && (str = this.personal) != null) {
            try {
                this.encodedPersonal = MimeUtility.encodeWord(str);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        if (this.encodedPersonal != null) {
            return GeneratedOutlineSupport1.outline93(new StringBuilder(), quotePhrase(this.encodedPersonal), " <", str2, Config.Compare.GREATER_THAN);
        }
        return (isGroup() || isSimple()) ? str2 : GeneratedOutlineSupport1.outline75(Config.Compare.LESS_THAN, str2, Config.Compare.GREATER_THAN);
    }

    public String toUnicodeString() {
        String personal = getPersonal();
        if (personal != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(quotePhrase(personal));
            sb.append(" <");
            return GeneratedOutlineSupport1.outline91(sb, this.address, Config.Compare.GREATER_THAN);
        } else if (!isGroup() && !isSimple()) {
            return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(Config.Compare.LESS_THAN), this.address, Config.Compare.GREATER_THAN);
        } else {
            return this.address;
        }
    }

    public void validate() throws AddressException {
        if (isGroup()) {
            getGroup(true);
        } else {
            checkAddress(getAddress(), true, true);
        }
    }

    public InternetAddress(String str) throws AddressException {
        InternetAddress[] parse = parse(str, true);
        if (parse.length == 1) {
            this.address = parse[0].address;
            this.personal = parse[0].personal;
            this.encodedPersonal = parse[0].encodedPersonal;
            return;
        }
        throw new AddressException("Illegal address", str);
    }

    private static int indexOfAny(String str, String str2, int i) {
        try {
            int length = str.length();
            while (i < length) {
                if (str2.indexOf(str.charAt(i)) >= 0) {
                    return i;
                }
                i++;
            }
        } catch (StringIndexOutOfBoundsException unused) {
        }
        return -1;
    }

    public static InternetAddress[] parse(String str, boolean z) throws AddressException {
        return parse(str, z, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:160:0x01e7, code lost:
        if (r8 == (-1)) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0261, code lost:
        if (r8 == (-1)) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x02a1, code lost:
        if (r3.trim().length() == 0) goto L271;
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x035c, code lost:
        if (r0.trim().length() == 0) goto L329;
     */
    /* JADX WARN: Removed duplicated region for block: B:201:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x00c4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0103  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static javax.mail.internet.InternetAddress[] parse(java.lang.String r19, boolean r20, boolean r21) throws javax.mail.internet.AddressException {
        /*
            Method dump skipped, instructions count: 978
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.InternetAddress.parse(java.lang.String, boolean, boolean):javax.mail.internet.InternetAddress[]");
    }

    public void setPersonal(String str) throws UnsupportedEncodingException {
        this.personal = str;
        if (str != null) {
            this.encodedPersonal = MimeUtility.encodeWord(str);
        } else {
            this.encodedPersonal = null;
        }
    }

    public static String toString(Address[] addressArr) {
        return toString(addressArr, 0);
    }

    public InternetAddress(String str, boolean z) throws AddressException {
        this(str);
        if (z) {
            if (isGroup()) {
                getGroup(true);
            } else {
                checkAddress(this.address, true, true);
            }
        }
    }

    public static String toString(Address[] addressArr, int i) {
        if (addressArr == null || addressArr.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < addressArr.length; i2++) {
            if (i2 != 0) {
                stringBuffer.append(", ");
                i += 2;
            }
            String address = addressArr[i2].toString();
            if (lengthOfFirstSegment(address) + i > 76) {
                stringBuffer.append("\r\n\t");
                i = 8;
            }
            stringBuffer.append(address);
            i = lengthOfLastSegment(address, i);
        }
        return stringBuffer.toString();
    }

    public InternetAddress(String str, String str2) throws UnsupportedEncodingException {
        this(str, str2, null);
    }

    public InternetAddress(String str, String str2, String str3) throws UnsupportedEncodingException {
        this.address = str;
        setPersonal(str2, str3);
    }
}
