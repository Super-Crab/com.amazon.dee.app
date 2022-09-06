package com.adobe.xmp.options;

import com.adobe.xmp.XMPException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class Options {
    private int options = 0;
    private Map optionNames = null;

    public Options() {
    }

    public Options(int i) throws XMPException {
        assertOptionsValid(i);
        setOptions(i);
    }

    private void assertOptionsValid(int i) throws XMPException {
        int i2 = (~getValidOptions()) & i;
        if (i2 == 0) {
            assertConsistency(i);
            return;
        }
        throw new XMPException(GeneratedOutlineSupport1.outline33(i2, GeneratedOutlineSupport1.outline107("The option bit(s) 0x"), " are invalid!"), 103);
    }

    private String getOptionName(int i) {
        Map procureOptionNames = procureOptionNames();
        Integer num = new Integer(i);
        String str = (String) procureOptionNames.get(num);
        if (str == null) {
            String defineOptionName = defineOptionName(i);
            if (defineOptionName == null) {
                return "<option name not defined>";
            }
            procureOptionNames.put(num, defineOptionName);
            return defineOptionName;
        }
        return str;
    }

    private Map procureOptionNames() {
        if (this.optionNames == null) {
            this.optionNames = new HashMap();
        }
        return this.optionNames;
    }

    protected void assertConsistency(int i) throws XMPException {
    }

    public void clear() {
        this.options = 0;
    }

    public boolean containsAllOptions(int i) {
        return (getOptions() & i) == i;
    }

    public boolean containsOneOf(int i) {
        return (i & getOptions()) != 0;
    }

    protected abstract String defineOptionName(int i);

    public boolean equals(Object obj) {
        return getOptions() == ((Options) obj).getOptions();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean getOption(int i) {
        return (i & this.options) != 0;
    }

    public int getOptions() {
        return this.options;
    }

    public String getOptionsString() {
        if (this.options != 0) {
            StringBuffer stringBuffer = new StringBuffer();
            int i = this.options;
            while (i != 0) {
                int i2 = (i - 1) & i;
                stringBuffer.append(getOptionName(i ^ i2));
                if (i2 != 0) {
                    stringBuffer.append(" | ");
                }
                i = i2;
            }
            return stringBuffer.toString();
        }
        return "<none>";
    }

    protected abstract int getValidOptions();

    public int hashCode() {
        return getOptions();
    }

    public boolean isExactly(int i) {
        return getOptions() == i;
    }

    public void setOption(int i, boolean z) {
        int i2;
        if (z) {
            i2 = i | this.options;
        } else {
            i2 = (~i) & this.options;
        }
        this.options = i2;
    }

    public void setOptions(int i) throws XMPException {
        assertOptionsValid(i);
        this.options = i;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline32(this.options, GeneratedOutlineSupport1.outline107("0x"));
    }
}
