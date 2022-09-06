package javax.mail.internet;

import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.mail.internet.HeaderTokenizer;
/* loaded from: classes3.dex */
public class ContentType {
    private ParameterList list;
    private String primaryType;
    private String subType;

    public ContentType() {
    }

    public String getBaseType() {
        if (this.primaryType == null || this.subType == null) {
            return "";
        }
        return this.primaryType + '/' + this.subType;
    }

    public String getParameter(String str) {
        ParameterList parameterList = this.list;
        if (parameterList == null) {
            return null;
        }
        return parameterList.get(str);
    }

    public ParameterList getParameterList() {
        return this.list;
    }

    public String getPrimaryType() {
        return this.primaryType;
    }

    public String getSubType() {
        return this.subType;
    }

    public boolean match(ContentType contentType) {
        String str;
        String str2;
        if (!(this.primaryType == null && contentType.getPrimaryType() == null) && ((str = this.primaryType) == null || !str.equalsIgnoreCase(contentType.getPrimaryType()))) {
            return false;
        }
        String subType = contentType.getSubType();
        String str3 = this.subType;
        if ((str3 != null && str3.startsWith("*")) || (subType != null && subType.startsWith("*"))) {
            return true;
        }
        return (this.subType == null && subType == null) || ((str2 = this.subType) != null && str2.equalsIgnoreCase(subType));
    }

    public void setParameter(String str, String str2) {
        if (this.list == null) {
            this.list = new ParameterList();
        }
        this.list.set(str, str2);
    }

    public void setParameterList(ParameterList parameterList) {
        this.list = parameterList;
    }

    public void setPrimaryType(String str) {
        this.primaryType = str;
    }

    public void setSubType(String str) {
        this.subType = str;
    }

    public String toString() {
        if (this.primaryType == null || this.subType == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.primaryType);
        stringBuffer.append('/');
        stringBuffer.append(this.subType);
        ParameterList parameterList = this.list;
        if (parameterList != null) {
            stringBuffer.append(parameterList.toString(stringBuffer.length() + 14));
        }
        return stringBuffer.toString();
    }

    public ContentType(String str, String str2, ParameterList parameterList) {
        this.primaryType = str;
        this.subType = str2;
        this.list = parameterList;
    }

    public ContentType(String str) throws ParseException {
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(str, HeaderTokenizer.MIME);
        HeaderTokenizer.Token next = headerTokenizer.next();
        if (next.getType() == -1) {
            this.primaryType = next.getValue();
            HeaderTokenizer.Token next2 = headerTokenizer.next();
            if (((char) next2.getType()) == '/') {
                HeaderTokenizer.Token next3 = headerTokenizer.next();
                if (next3.getType() == -1) {
                    this.subType = next3.getValue();
                    String remainder = headerTokenizer.getRemainder();
                    if (remainder == null) {
                        return;
                    }
                    this.list = new ParameterList(remainder);
                    return;
                }
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("In Content-Type string <", str, ">, expected MIME subtype, got ");
                outline115.append(next3.getValue());
                throw new ParseException(outline115.toString());
            }
            StringBuilder outline1152 = GeneratedOutlineSupport1.outline115("In Content-Type string <", str, ">, expected '/', got ");
            outline1152.append(next2.getValue());
            throw new ParseException(outline1152.toString());
        }
        StringBuilder outline1153 = GeneratedOutlineSupport1.outline115("In Content-Type string <", str, ">, expected MIME type, got ");
        outline1153.append(next.getValue());
        throw new ParseException(outline1153.toString());
    }

    public boolean match(String str) {
        try {
            return match(new ContentType(str));
        } catch (ParseException unused) {
            return false;
        }
    }
}
