package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
/* loaded from: classes4.dex */
public class FormattedMessage implements Message {
    private static final int HASHVAL = 31;
    private static final long serialVersionUID = -665975803997290697L;
    private transient Object[] argArray;
    private transient String formattedMessage;
    private final Locale locale;
    private Message message;
    private String messagePattern;
    private String[] stringArgs;
    private final Throwable throwable;
    private static final String FORMAT_SPECIFIER = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";
    private static final Pattern MSG_PATTERN = Pattern.compile(FORMAT_SPECIFIER);

    public FormattedMessage(Locale locale, String str, Object obj) {
        this(locale, str, new Object[]{obj}, (Throwable) null);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.formattedMessage = objectInputStream.readUTF();
        this.messagePattern = objectInputStream.readUTF();
        int readInt = objectInputStream.readInt();
        this.stringArgs = new String[readInt];
        for (int i = 0; i < readInt; i++) {
            this.stringArgs[i] = objectInputStream.readUTF();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        getFormattedMessage();
        objectOutputStream.writeUTF(this.formattedMessage);
        objectOutputStream.writeUTF(this.messagePattern);
        objectOutputStream.writeInt(this.argArray.length);
        Object[] objArr = this.argArray;
        this.stringArgs = new String[objArr.length];
        int i = 0;
        for (Object obj : objArr) {
            String valueOf = String.valueOf(obj);
            this.stringArgs[i] = valueOf;
            objectOutputStream.writeUTF(valueOf);
            i++;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FormattedMessage.class != obj.getClass()) {
            return false;
        }
        FormattedMessage formattedMessage = (FormattedMessage) obj;
        String str = this.messagePattern;
        if (str == null ? formattedMessage.messagePattern != null : !str.equals(formattedMessage.messagePattern)) {
            return false;
        }
        return Arrays.equals(this.stringArgs, formattedMessage.stringArgs);
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.messagePattern;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.formattedMessage == null) {
            if (this.message == null) {
                this.message = getMessage(this.messagePattern, this.argArray, this.throwable);
            }
            this.formattedMessage = this.message.getFormattedMessage();
        }
        return this.formattedMessage;
    }

    protected Message getMessage(String str, Object[] objArr, Throwable th) {
        try {
            Format[] formats = new MessageFormat(str).getFormats();
            if (formats != null && formats.length > 0) {
                return new MessageFormatMessage(this.locale, str, objArr);
            }
        } catch (Exception unused) {
        }
        try {
            if (MSG_PATTERN.matcher(str).find()) {
                return new StringFormattedMessage(this.locale, str, objArr);
            }
        } catch (Exception unused2) {
        }
        return new ParameterizedMessage(str, objArr, th);
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        Object[] objArr = this.argArray;
        return objArr != null ? objArr : this.stringArgs;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        Throwable th = this.throwable;
        if (th != null) {
            return th;
        }
        if (this.message == null) {
            this.message = getMessage(this.messagePattern, this.argArray, null);
        }
        return this.message.getThrowable();
    }

    public int hashCode() {
        String str = this.messagePattern;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String[] strArr = this.stringArgs;
        if (strArr != null) {
            i = Arrays.hashCode(strArr);
        }
        return hashCode + i;
    }

    public String toString() {
        return getFormattedMessage();
    }

    public FormattedMessage(Locale locale, String str, Object obj, Object obj2) {
        this(locale, str, obj, obj2);
    }

    public FormattedMessage(Locale locale, String str, Object... objArr) {
        this(locale, str, objArr, (Throwable) null);
    }

    public FormattedMessage(Locale locale, String str, Object[] objArr, Throwable th) {
        this.locale = locale;
        this.messagePattern = str;
        this.argArray = objArr;
        this.throwable = th;
    }

    public FormattedMessage(String str, Object obj) {
        this(str, new Object[]{obj}, (Throwable) null);
    }

    public FormattedMessage(String str, Object obj, Object obj2) {
        this(str, obj, obj2);
    }

    public FormattedMessage(String str, Object... objArr) {
        this(str, objArr, (Throwable) null);
    }

    public FormattedMessage(String str, Object[] objArr, Throwable th) {
        this.locale = Locale.getDefault(Locale.Category.FORMAT);
        this.messagePattern = str;
        this.argArray = objArr;
        this.throwable = th;
    }
}
