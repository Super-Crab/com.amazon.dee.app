package org.apache.logging.log4j.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.util.StringBuilderFormattable;
/* loaded from: classes4.dex */
public class StructuredDataCollectionMessage implements StringBuilderFormattable, MessageCollectionMessage<StructuredDataMessage> {
    private static final long serialVersionUID = 5725337076388822924L;
    private final List<StructuredDataMessage> structuredDataMessageList;

    public StructuredDataCollectionMessage(List<StructuredDataMessage> list) {
        this.structuredDataMessageList = list;
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        for (StructuredDataMessage structuredDataMessage : this.structuredDataMessageList) {
            structuredDataMessage.formatTo(sb);
        }
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        StringBuilder sb = new StringBuilder();
        for (StructuredDataMessage structuredDataMessage : this.structuredDataMessageList) {
            if (structuredDataMessage.getFormat() != null) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(structuredDataMessage.getFormat());
            }
        }
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        StringBuilder sb = new StringBuilder();
        formatTo(sb);
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        ArrayList<Object[]> arrayList = new ArrayList();
        int i = 0;
        for (StructuredDataMessage structuredDataMessage : this.structuredDataMessageList) {
            Object[] parameters = structuredDataMessage.getParameters();
            if (parameters != null) {
                arrayList.add(parameters);
                i += parameters.length;
            }
        }
        Object[] objArr = new Object[i];
        int i2 = 0;
        for (Object[] objArr2 : arrayList) {
            int length = objArr2.length;
            int i3 = i2;
            int i4 = 0;
            while (i4 < length) {
                objArr[i3] = objArr2[i4];
                i4++;
                i3++;
            }
            i2 = i3;
        }
        return objArr;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        for (StructuredDataMessage structuredDataMessage : this.structuredDataMessageList) {
            Throwable throwable = structuredDataMessage.getThrowable();
            if (throwable != null) {
                return throwable;
            }
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<StructuredDataMessage> iterator() {
        return this.structuredDataMessageList.iterator();
    }
}
