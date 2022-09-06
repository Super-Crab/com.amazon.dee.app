package org.apache.logging.log4j.message;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.StringBuilders;
/* loaded from: classes4.dex */
public class ParameterizedMessage implements Message, StringBuilderFormattable {
    private static final int DEFAULT_STRING_BUILDER_SIZE = 255;
    public static final String ERROR_MSG_SEPARATOR = ":";
    public static final String ERROR_PREFIX = "[!!!";
    public static final String ERROR_SEPARATOR = "=>";
    public static final String ERROR_SUFFIX = "!!!]";
    private static final int HASHVAL = 31;
    public static final String RECURSION_PREFIX = "[...";
    public static final String RECURSION_SUFFIX = "...]";
    private static final long serialVersionUID = -665975803997290697L;
    private static ThreadLocal<StringBuilder> threadLocalStringBuilder = new ThreadLocal<>();
    private transient Object[] argArray;
    private String formattedMessage;
    private int[] indices;
    private String messagePattern;
    private transient Throwable throwable;
    private int usedCount;

    @Deprecated
    public ParameterizedMessage(String str, String[] strArr, Throwable th) {
        this.argArray = strArr;
        this.throwable = th;
        init(str);
    }

    public static int countArgumentPlaceholders(String str) {
        return ParameterFormatter.countArgumentPlaceholders(str);
    }

    public static String deepToString(Object obj) {
        return ParameterFormatter.deepToString(obj);
    }

    public static String format(String str, Object[] objArr) {
        return ParameterFormatter.format(str, objArr);
    }

    private static StringBuilder getThreadLocalStringBuilder() {
        StringBuilder sb = threadLocalStringBuilder.get();
        if (sb == null) {
            sb = new StringBuilder(255);
            threadLocalStringBuilder.set(sb);
        }
        sb.setLength(0);
        return sb;
    }

    public static String identityToString(Object obj) {
        return ParameterFormatter.identityToString(obj);
    }

    private void init(String str) {
        this.messagePattern = str;
        int i = 0;
        this.indices = new int[Math.max(1, str == null ? 0 : str.length() >> 1)];
        int countArgumentPlaceholders2 = ParameterFormatter.countArgumentPlaceholders2(str, this.indices);
        initThrowable(this.argArray, countArgumentPlaceholders2);
        Object[] objArr = this.argArray;
        if (objArr != null) {
            i = objArr.length;
        }
        this.usedCount = Math.min(countArgumentPlaceholders2, i);
    }

    private void initThrowable(Object[] objArr, int i) {
        int length;
        if (objArr == null || i >= (length = objArr.length) || this.throwable != null) {
            return;
        }
        int i2 = length - 1;
        if (!(objArr[i2] instanceof Throwable)) {
            return;
        }
        this.throwable = (Throwable) objArr[i2];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ParameterizedMessage.class != obj.getClass()) {
            return false;
        }
        ParameterizedMessage parameterizedMessage = (ParameterizedMessage) obj;
        String str = this.messagePattern;
        if (str == null ? parameterizedMessage.messagePattern != null : !str.equals(parameterizedMessage.messagePattern)) {
            return false;
        }
        return Arrays.equals(this.argArray, parameterizedMessage.argArray);
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        String str = this.formattedMessage;
        if (str != null) {
            sb.append(str);
            return;
        }
        int[] iArr = this.indices;
        if (iArr[0] < 0) {
            ParameterFormatter.formatMessage(sb, this.messagePattern, this.argArray, this.usedCount);
        } else {
            ParameterFormatter.formatMessage2(sb, this.messagePattern, this.argArray, this.usedCount, iArr);
        }
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.messagePattern;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.formattedMessage == null) {
            StringBuilder threadLocalStringBuilder2 = getThreadLocalStringBuilder();
            formatTo(threadLocalStringBuilder2);
            this.formattedMessage = threadLocalStringBuilder2.toString();
            StringBuilders.trimToMaxSize(threadLocalStringBuilder2, Constants.MAX_REUSABLE_MESSAGE_SIZE);
        }
        return this.formattedMessage;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return this.argArray;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return this.throwable;
    }

    public int hashCode() {
        String str = this.messagePattern;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Object[] objArr = this.argArray;
        if (objArr != null) {
            i = Arrays.hashCode(objArr);
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ParameterizedMessage[messagePattern=");
        outline107.append(this.messagePattern);
        outline107.append(", stringArgs=");
        outline107.append(Arrays.toString(this.argArray));
        outline107.append(", throwable=");
        outline107.append(this.throwable);
        outline107.append(JsonReaderKt.END_LIST);
        return outline107.toString();
    }

    public ParameterizedMessage(String str, Object[] objArr, Throwable th) {
        this.argArray = objArr;
        this.throwable = th;
        init(str);
    }

    public ParameterizedMessage(String str, Object... objArr) {
        this.argArray = objArr;
        init(str);
    }

    public ParameterizedMessage(String str, Object obj) {
        this(str, obj);
    }

    public ParameterizedMessage(String str, Object obj, Object obj2) {
        this(str, obj, obj2);
    }
}
