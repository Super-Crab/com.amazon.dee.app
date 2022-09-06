package org.apache.commons.lang3.builder;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.SystemUtils;
/* loaded from: classes4.dex */
class MultilineRecursiveToStringStyle extends RecursiveToStringStyle {
    private static final long serialVersionUID = 1;
    private int indent = 2;
    private int spaces = 2;

    public MultilineRecursiveToStringStyle() {
        resetIndent();
    }

    private void resetIndent() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        outline107.append(SystemUtils.LINE_SEPARATOR);
        outline107.append((Object) spacer(this.spaces));
        setArrayStart(outline107.toString());
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(",");
        outline1072.append(SystemUtils.LINE_SEPARATOR);
        outline1072.append((Object) spacer(this.spaces));
        setArraySeparator(outline1072.toString());
        setArrayEnd(SystemUtils.LINE_SEPARATOR + ((Object) spacer(this.spaces - this.indent)) + EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("[");
        outline1073.append(SystemUtils.LINE_SEPARATOR);
        outline1073.append((Object) spacer(this.spaces));
        setContentStart(outline1073.toString());
        StringBuilder outline1074 = GeneratedOutlineSupport1.outline107(",");
        outline1074.append(SystemUtils.LINE_SEPARATOR);
        outline1074.append((Object) spacer(this.spaces));
        setFieldSeparator(outline1074.toString());
        setContentEnd(SystemUtils.LINE_SEPARATOR + ((Object) spacer(this.spaces - this.indent)) + "]");
    }

    private StringBuilder spacer(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(" ");
        }
        return sb;
    }

    @Override // org.apache.commons.lang3.builder.RecursiveToStringStyle, org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, Object obj) {
        if (!ClassUtils.isPrimitiveWrapper(obj.getClass()) && !String.class.equals(obj.getClass()) && accept(obj.getClass())) {
            this.spaces += this.indent;
            resetIndent();
            stringBuffer.append(ReflectionToStringBuilder.toString(obj, this));
            this.spaces -= this.indent;
            resetIndent();
            return;
        }
        super.appendDetail(stringBuffer, str, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void reflectionAppendArrayDetail(StringBuffer stringBuffer, String str, Object obj) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, obj);
        this.spaces -= this.indent;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, Object[] objArr) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, objArr);
        this.spaces -= this.indent;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, long[] jArr) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, jArr);
        this.spaces -= this.indent;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, int[] iArr) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, iArr);
        this.spaces -= this.indent;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, short[] sArr) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, sArr);
        this.spaces -= this.indent;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, byte[] bArr) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, bArr);
        this.spaces -= this.indent;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, char[] cArr) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, cArr);
        this.spaces -= this.indent;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, double[] dArr) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, dArr);
        this.spaces -= this.indent;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, float[] fArr) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, fArr);
        this.spaces -= this.indent;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, boolean[] zArr) {
        this.spaces += this.indent;
        resetIndent();
        super.appendDetail(stringBuffer, str, zArr);
        this.spaces -= this.indent;
        resetIndent();
    }
}
