package org.apache.logging.log4j.message;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StringBuilders;
@PerformanceSensitive({"allocation"})
/* loaded from: classes4.dex */
public class ReusableParameterizedMessage implements ReusableMessage, ParameterVisitable, Clearable {
    private static final int MAX_PARMS = 10;
    private static final int MIN_BUILDER_SIZE = 512;
    private static final long serialVersionUID = 7800075879295123856L;
    private int argCount;
    private transient ThreadLocal<StringBuilder> buffer;
    private String messagePattern;
    private transient Throwable throwable;
    private int usedCount;
    private transient Object[] varargs;
    private final int[] indices = new int[256];
    private transient Object[] params = new Object[10];
    transient boolean reserved = false;

    private static int count(String str, int[] iArr) {
        try {
            return ParameterFormatter.countArgumentPlaceholders2(str, iArr);
        } catch (Exception unused) {
            return ParameterFormatter.countArgumentPlaceholders(str);
        }
    }

    private StringBuilder getBuffer() {
        if (this.buffer == null) {
            this.buffer = new ThreadLocal<>();
        }
        StringBuilder sb = this.buffer.get();
        if (sb == null) {
            String str = this.messagePattern;
            StringBuilder sb2 = new StringBuilder(Math.max(512, (str == null ? 0 : str.length()) * 2));
            this.buffer.set(sb2);
            sb = sb2;
        }
        sb.setLength(0);
        return sb;
    }

    private Object[] getParams() {
        Object[] objArr = this.varargs;
        return objArr == null ? this.params : objArr;
    }

    private Object[] getTrimmedParams() {
        Object[] objArr = this.varargs;
        return objArr == null ? Arrays.copyOf(this.params, this.argCount) : objArr;
    }

    private void init(String str, int i, Object[] objArr) {
        this.varargs = null;
        this.messagePattern = str;
        this.argCount = i;
        int count = count(str, this.indices);
        initThrowable(objArr, i, count);
        this.usedCount = Math.min(count, i);
    }

    private void initThrowable(Object[] objArr, int i, int i2) {
        if (i2 < i) {
            int i3 = i - 1;
            if (objArr[i3] instanceof Throwable) {
                this.throwable = (Throwable) objArr[i3];
                return;
            }
        }
        this.throwable = null;
    }

    @Override // org.apache.logging.log4j.message.Clearable
    public void clear() {
        this.reserved = false;
        this.varargs = null;
        this.messagePattern = null;
        this.throwable = null;
    }

    @Override // org.apache.logging.log4j.message.ParameterVisitable
    public <S> void forEachParameter(ParameterConsumer<S> parameterConsumer, S s) {
        Object[] params = getParams();
        for (short s2 = 0; s2 < this.argCount; s2 = (short) (s2 + 1)) {
            parameterConsumer.accept(params[s2], s2, s);
        }
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        if (this.indices[0] < 0) {
            ParameterFormatter.formatMessage(sb, this.messagePattern, getParams(), this.argCount);
        } else {
            ParameterFormatter.formatMessage2(sb, this.messagePattern, getParams(), this.usedCount, this.indices);
        }
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.messagePattern;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        StringBuilder buffer = getBuffer();
        formatTo(buffer);
        String sb = buffer.toString();
        StringBuilders.trimToMaxSize(buffer, Constants.MAX_REUSABLE_MESSAGE_SIZE);
        return sb;
    }

    @Override // org.apache.logging.log4j.message.ReusableMessage
    public short getParameterCount() {
        return (short) this.argCount;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return getTrimmedParams();
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return this.throwable;
    }

    @Override // org.apache.logging.log4j.message.ReusableMessage
    public Message memento() {
        return new ParameterizedMessage(this.messagePattern, getTrimmedParams());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage reserve() {
        this.reserved = true;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object... objArr) {
        init(str, objArr == null ? 0 : objArr.length, objArr);
        this.varargs = objArr;
        return this;
    }

    @Override // org.apache.logging.log4j.message.ReusableMessage
    public Object[] swapParameters(Object[] objArr) {
        if (this.varargs == null) {
            Object[] objArr2 = this.params;
            if (objArr.length >= 10) {
                this.params = objArr;
            } else {
                int i = this.argCount;
                if (i <= objArr.length) {
                    System.arraycopy(objArr2, 0, objArr, 0, i);
                    for (int i2 = 0; i2 < this.argCount; i2++) {
                        this.params[i2] = null;
                    }
                    return objArr;
                }
                this.params = new Object[10];
            }
            return objArr2;
        }
        int i3 = this.argCount;
        if (i3 > objArr.length) {
            objArr = new Object[i3];
        }
        System.arraycopy(this.varargs, 0, objArr, 0, this.argCount);
        return objArr;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReusableParameterizedMessage[messagePattern=");
        outline107.append(getFormat());
        outline107.append(", stringArgs=");
        outline107.append(Arrays.toString(getParameters()));
        outline107.append(", throwable=");
        outline107.append(getThrowable());
        outline107.append(JsonReaderKt.END_LIST);
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        init(str, 1, objArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        init(str, 2, objArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        init(str, 3, objArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        init(str, 4, objArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        init(str, 5, objArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        init(str, 6, objArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        objArr[6] = obj7;
        init(str, 7, objArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        objArr[6] = obj7;
        objArr[7] = obj8;
        init(str, 8, objArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        objArr[6] = obj7;
        objArr[7] = obj8;
        objArr[8] = obj9;
        init(str, 9, objArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage set(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        Object[] objArr = this.params;
        objArr[0] = obj;
        objArr[1] = obj2;
        objArr[2] = obj3;
        objArr[3] = obj4;
        objArr[4] = obj5;
        objArr[5] = obj6;
        objArr[6] = obj7;
        objArr[7] = obj8;
        objArr[8] = obj9;
        objArr[9] = obj10;
        init(str, 10, objArr);
        return this;
    }
}
