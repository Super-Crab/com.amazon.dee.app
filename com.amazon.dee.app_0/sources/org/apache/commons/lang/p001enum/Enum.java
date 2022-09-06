package org.apache.commons.lang.p001enum;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
/* renamed from: org.apache.commons.lang.enum.Enum  reason: invalid package */
/* loaded from: classes4.dex */
public abstract class Enum implements Comparable, Serializable {
    private static final Map EMPTY_MAP = Collections.unmodifiableMap(new HashMap(0));
    private static Map cEnumClasses = new WeakHashMap();
    static /* synthetic */ Class class$org$apache$commons$lang$enum$Enum = null;
    static /* synthetic */ Class class$org$apache$commons$lang$enum$ValuedEnum = null;
    private static final long serialVersionUID = -487045951170455942L;
    private final transient int iHashCode;
    private final String iName;
    protected transient String iToString = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: org.apache.commons.lang.enum.Enum$Entry */
    /* loaded from: classes4.dex */
    public static class Entry {
        final Map map = new HashMap();
        final Map unmodifiableMap = Collections.unmodifiableMap(this.map);
        final List list = new ArrayList(25);
        final List unmodifiableList = Collections.unmodifiableList(this.list);

        protected Entry() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Enum(String str) {
        init(str);
        this.iName = str;
        this.iHashCode = (str.hashCode() * 3) + getEnumClass().hashCode() + 7;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static Entry createEntry(Class cls) {
        Entry entry = new Entry();
        Class superclass = cls.getSuperclass();
        while (true) {
            if (superclass == null) {
                break;
            }
            Class cls2 = class$org$apache$commons$lang$enum$Enum;
            if (cls2 == null) {
                cls2 = class$("org.apache.commons.lang.enum.Enum");
                class$org$apache$commons$lang$enum$Enum = cls2;
            }
            if (superclass == cls2) {
                break;
            }
            Class cls3 = class$org$apache$commons$lang$enum$ValuedEnum;
            if (cls3 == null) {
                cls3 = class$("org.apache.commons.lang.enum.ValuedEnum");
                class$org$apache$commons$lang$enum$ValuedEnum = cls3;
            }
            if (superclass == cls3) {
                break;
            }
            Entry entry2 = (Entry) cEnumClasses.get(superclass);
            if (entry2 != null) {
                entry.list.addAll(entry2.list);
                entry.map.putAll(entry2.map);
                break;
            }
            superclass = superclass.getSuperclass();
        }
        return entry;
    }

    private static Entry getEntry(Class cls) {
        if (cls != null) {
            Class cls2 = class$org$apache$commons$lang$enum$Enum;
            if (cls2 == null) {
                cls2 = class$("org.apache.commons.lang.enum.Enum");
                class$org$apache$commons$lang$enum$Enum = cls2;
            }
            if (cls2.isAssignableFrom(cls)) {
                Entry entry = (Entry) cEnumClasses.get(cls);
                if (entry == null) {
                    try {
                        Class.forName(cls.getName(), true, cls.getClassLoader());
                        return (Entry) cEnumClasses.get(cls);
                    } catch (Exception unused) {
                    }
                }
                return entry;
            }
            throw new IllegalArgumentException("The Class must be a subclass of Enum");
        }
        throw new IllegalArgumentException("The Enum Class must not be null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Enum getEnum(Class cls, String str) {
        Entry entry = getEntry(cls);
        if (entry == null) {
            return null;
        }
        return (Enum) entry.map.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static List getEnumList(Class cls) {
        Entry entry = getEntry(cls);
        if (entry == null) {
            return Collections.EMPTY_LIST;
        }
        return entry.unmodifiableList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Map getEnumMap(Class cls) {
        Entry entry = getEntry(cls);
        if (entry == null) {
            return EMPTY_MAP;
        }
        return entry.unmodifiableMap;
    }

    private String getNameInOtherClassLoader(Object obj) {
        try {
            return (String) obj.getClass().getMethod("getName", null).invoke(obj, null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            throw new IllegalStateException("This should not happen");
        }
    }

    private void init(String str) {
        Entry entry;
        if (!StringUtils.isEmpty(str)) {
            Class<?> enumClass = getEnumClass();
            if (enumClass != null) {
                Class<?> cls = getClass();
                boolean z = false;
                while (true) {
                    if (cls == null) {
                        break;
                    }
                    Class<?> cls2 = class$org$apache$commons$lang$enum$Enum;
                    if (cls2 == null) {
                        cls2 = class$("org.apache.commons.lang.enum.Enum");
                        class$org$apache$commons$lang$enum$Enum = cls2;
                    }
                    if (cls == cls2) {
                        break;
                    }
                    Class<?> cls3 = class$org$apache$commons$lang$enum$ValuedEnum;
                    if (cls3 == null) {
                        cls3 = class$("org.apache.commons.lang.enum.ValuedEnum");
                        class$org$apache$commons$lang$enum$ValuedEnum = cls3;
                    }
                    if (cls == cls3) {
                        break;
                    } else if (cls == enumClass) {
                        z = true;
                        break;
                    } else {
                        cls = cls.getSuperclass();
                    }
                }
                if (z) {
                    Class cls4 = class$org$apache$commons$lang$enum$Enum;
                    if (cls4 == null) {
                        cls4 = class$("org.apache.commons.lang.enum.Enum");
                        class$org$apache$commons$lang$enum$Enum = cls4;
                    }
                    synchronized (cls4) {
                        entry = (Entry) cEnumClasses.get(enumClass);
                        if (entry == null) {
                            entry = createEntry(enumClass);
                            WeakHashMap weakHashMap = new WeakHashMap();
                            weakHashMap.putAll(cEnumClasses);
                            weakHashMap.put(enumClass, entry);
                            cEnumClasses = weakHashMap;
                        }
                    }
                    if (!entry.map.containsKey(str)) {
                        entry.map.put(str, this);
                        entry.list.add(this);
                        return;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("The Enum name must be unique, '");
                    stringBuffer.append(str);
                    stringBuffer.append("' has already been added");
                    throw new IllegalArgumentException(stringBuffer.toString());
                }
                throw new IllegalArgumentException("getEnumClass() must return a superclass of this class");
            }
            throw new IllegalArgumentException("getEnumClass() must not be null");
        }
        throw new IllegalArgumentException("The Enum name must not be empty or null");
    }

    protected static Iterator iterator(Class cls) {
        return getEnumList(cls).iterator();
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (obj.getClass() != getClass()) {
            if (obj.getClass().getName().equals(getClass().getName())) {
                return this.iName.compareTo(getNameInOtherClassLoader(obj));
            }
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Different enum class '");
            outline103.append(ClassUtils.getShortClassName(obj.getClass()));
            outline103.append("'");
            throw new ClassCastException(outline103.toString());
        }
        return this.iName.compareTo(((Enum) obj).iName);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == getClass()) {
            return this.iName.equals(((Enum) obj).iName);
        }
        if (obj.getClass().getName().equals(getClass().getName())) {
            return this.iName.equals(getNameInOtherClassLoader(obj));
        }
        return false;
    }

    public Class getEnumClass() {
        return getClass();
    }

    public final String getName() {
        return this.iName;
    }

    public final int hashCode() {
        return this.iHashCode;
    }

    protected Object readResolve() {
        Entry entry = (Entry) cEnumClasses.get(getEnumClass());
        if (entry == null) {
            return null;
        }
        return entry.map.get(getName());
    }

    public String toString() {
        if (this.iToString == null) {
            String shortClassName = ClassUtils.getShortClassName(getEnumClass());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(shortClassName);
            stringBuffer.append("[");
            stringBuffer.append(getName());
            stringBuffer.append("]");
            this.iToString = stringBuffer.toString();
        }
        return this.iToString;
    }
}
