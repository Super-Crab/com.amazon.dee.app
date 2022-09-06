package org.apache.logging.log4j.message;

import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StringBuilders;
@PerformanceSensitive({"allocation"})
/* loaded from: classes4.dex */
public class ReusableObjectMessage implements ReusableMessage, ParameterVisitable, Clearable {
    private static final long serialVersionUID = 6922476812535519960L;
    private transient Object obj;

    @Override // org.apache.logging.log4j.message.Clearable
    public void clear() {
        this.obj = null;
    }

    @Override // org.apache.logging.log4j.message.ParameterVisitable
    public <S> void forEachParameter(ParameterConsumer<S> parameterConsumer, S s) {
        parameterConsumer.accept(this.obj, 0, s);
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        StringBuilders.appendValue(sb, this.obj);
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        Object obj = this.obj;
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        return String.valueOf(this.obj);
    }

    public Object getParameter() {
        return this.obj;
    }

    @Override // org.apache.logging.log4j.message.ReusableMessage
    public short getParameterCount() {
        return (short) 1;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return new Object[]{this.obj};
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        Object obj = this.obj;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        return null;
    }

    @Override // org.apache.logging.log4j.message.ReusableMessage
    public Message memento() {
        return new ObjectMessage(this.obj);
    }

    public void set(Object obj) {
        this.obj = obj;
    }

    @Override // org.apache.logging.log4j.message.ReusableMessage
    public Object[] swapParameters(Object[] objArr) {
        if (objArr.length == 0) {
            Object[] objArr2 = new Object[10];
            objArr2[0] = this.obj;
            return objArr2;
        }
        objArr[0] = this.obj;
        return objArr;
    }

    public String toString() {
        return getFormattedMessage();
    }
}
