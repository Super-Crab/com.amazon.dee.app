package com.amazonaws.services.s3.internal;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class XmlWriter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    List<String> tags = new ArrayList();
    StringBuilder sb = new StringBuilder();

    private void appendEscapedString(String str, StringBuilder sb) {
        if (str == null) {
            str = "";
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            String str2 = charAt != '\t' ? charAt != '\n' ? charAt != '\r' ? charAt != '\"' ? charAt != '&' ? charAt != '<' ? charAt != '>' ? null : "&gt;" : "&lt;" : "&amp;" : "&quot;" : "&#13;" : "&#10;" : "&#9;";
            if (str2 != null) {
                if (i2 < i) {
                    sb.append((CharSequence) str, i2, i);
                }
                this.sb.append(str2);
                i2 = i + 1;
            }
            i++;
        }
        if (i2 < i) {
            this.sb.append((CharSequence) str, i2, i);
        }
    }

    private void writeAttr(String str, String str2) {
        StringBuilder sb = this.sb;
        sb.append(Chars.SPACE);
        sb.append(str);
        sb.append("=\"");
        appendEscapedString(str2, this.sb);
        this.sb.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    public XmlWriter end() {
        List<String> list = this.tags;
        GeneratedOutlineSupport1.outline180(this.sb, "</", list.remove(list.size() - 1), Config.Compare.GREATER_THAN);
        return this;
    }

    public byte[] getBytes() {
        return toString().getBytes(StringUtils.UTF8);
    }

    public XmlWriter start(String str) {
        GeneratedOutlineSupport1.outline180(this.sb, Config.Compare.LESS_THAN, str, Config.Compare.GREATER_THAN);
        this.tags.add(str);
        return this;
    }

    public String toString() {
        return this.sb.toString();
    }

    public XmlWriter value(String str) {
        appendEscapedString(str, this.sb);
        return this;
    }

    public XmlWriter start(String str, String str2, String str3) {
        StringBuilder sb = this.sb;
        sb.append(Config.Compare.LESS_THAN);
        sb.append(str);
        writeAttr(str2, str3);
        this.sb.append(Config.Compare.GREATER_THAN);
        this.tags.add(str);
        return this;
    }

    public XmlWriter start(String str, String[] strArr, String[] strArr2) {
        StringBuilder sb = this.sb;
        sb.append(Config.Compare.LESS_THAN);
        sb.append(str);
        for (int i = 0; i < Math.min(strArr.length, strArr2.length); i++) {
            writeAttr(strArr[i], strArr2[i]);
        }
        this.sb.append(Config.Compare.GREATER_THAN);
        this.tags.add(str);
        return this;
    }
}
