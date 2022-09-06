package org.apache.commons.lang.enums;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.lang.ClassUtils;
/* loaded from: classes4.dex */
public abstract class ValuedEnum extends Enum {
    private static final long serialVersionUID = -7129650521543789085L;
    private final int iValue;

    protected ValuedEnum(String str, int i) {
        super(str);
        this.iValue = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Enum getEnum(Class cls, int i) {
        if (cls != null) {
            for (ValuedEnum valuedEnum : Enum.getEnumList(cls)) {
                if (valuedEnum.getValue() == i) {
                    return valuedEnum;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("The Enum Class must not be null");
    }

    private int getValueInOtherClassLoader(Object obj) {
        try {
            return ((Integer) obj.getClass().getMethod("getValue", null).invoke(obj, null)).intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            throw new IllegalStateException("This should not happen");
        }
    }

    @Override // org.apache.commons.lang.enums.Enum, java.lang.Comparable
    public int compareTo(Object obj) {
        int i;
        int i2;
        if (obj == this) {
            return 0;
        }
        if (obj.getClass() != ValuedEnum.class) {
            if (obj.getClass().getName().equals(ValuedEnum.class.getName())) {
                i = this.iValue;
                i2 = getValueInOtherClassLoader(obj);
            } else {
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Different enum class '");
                outline103.append(ClassUtils.getShortClassName(obj.getClass()));
                outline103.append("'");
                throw new ClassCastException(outline103.toString());
            }
        } else {
            i = this.iValue;
            i2 = ((ValuedEnum) obj).iValue;
        }
        return i - i2;
    }

    public final int getValue() {
        return this.iValue;
    }

    @Override // org.apache.commons.lang.enums.Enum
    public String toString() {
        if (this.iToString == null) {
            String shortClassName = ClassUtils.getShortClassName(getEnumClass());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(shortClassName);
            stringBuffer.append("[");
            stringBuffer.append(getName());
            stringBuffer.append(Config.Compare.EQUAL_TO);
            stringBuffer.append(getValue());
            stringBuffer.append("]");
            this.iToString = stringBuffer.toString();
        }
        return this.iToString;
    }
}
