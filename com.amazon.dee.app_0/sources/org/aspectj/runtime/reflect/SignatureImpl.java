package org.aspectj.runtime.reflect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.SoftReference;
import java.util.StringTokenizer;
import org.aspectj.lang.Signature;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class SignatureImpl implements Signature {
    static final String INNER_SEP = ":";
    static final char SEP = '-';
    private static boolean useCache = true;
    Class declaringType;
    String declaringTypeName;
    ClassLoader lookupClassLoader;
    int modifiers;
    String name;
    Cache stringCache;
    private String stringRep;
    static String[] EMPTY_STRING_ARRAY = new String[0];
    static Class[] EMPTY_CLASS_ARRAY = new Class[0];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public interface Cache {
        String get(int i);

        void set(int i, String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class CacheImpl implements Cache {
        private SoftReference toStringCacheRef;

        public CacheImpl() {
            makeCache();
        }

        private String[] array() {
            return (String[]) this.toStringCacheRef.get();
        }

        private String[] makeCache() {
            String[] strArr = new String[3];
            this.toStringCacheRef = new SoftReference(strArr);
            return strArr;
        }

        @Override // org.aspectj.runtime.reflect.SignatureImpl.Cache
        public String get(int i) {
            String[] array = array();
            if (array == null) {
                return null;
            }
            return array[i];
        }

        @Override // org.aspectj.runtime.reflect.SignatureImpl.Cache
        public void set(int i, String str) {
            String[] array = array();
            if (array == null) {
                array = makeCache();
            }
            array[i] = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignatureImpl(int i, String str, Class cls) {
        this.modifiers = -1;
        this.lookupClassLoader = null;
        this.modifiers = i;
        this.name = str;
        this.declaringType = cls;
    }

    private ClassLoader getLookupClassLoader() {
        if (this.lookupClassLoader == null) {
            this.lookupClassLoader = getClass().getClassLoader();
        }
        return this.lookupClassLoader;
    }

    static boolean getUseCache() {
        return useCache;
    }

    static void setUseCache(boolean z) {
        useCache = z;
    }

    void addFullTypeNames(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i = 0; i < clsArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(fullTypeName(clsArr[i]));
        }
    }

    void addShortTypeNames(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i = 0; i < clsArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(shortTypeName(clsArr[i]));
        }
    }

    void addTypeArray(StringBuffer stringBuffer, Class[] clsArr) {
        addFullTypeNames(stringBuffer, clsArr);
    }

    protected abstract String createToString(StringMaker stringMaker);

    int extractInt(int i) {
        return Integer.parseInt(extractString(i), 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String extractString(int i) {
        int indexOf = this.stringRep.indexOf(45);
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                break;
            }
            i2 = indexOf + 1;
            indexOf = this.stringRep.indexOf(45, i2);
            i = i3;
        }
        if (indexOf == -1) {
            indexOf = this.stringRep.length();
        }
        return this.stringRep.substring(i2, indexOf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] extractStrings(int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(extractString(i), ":");
        int countTokens = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            strArr[i2] = stringTokenizer.nextToken();
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class extractType(int i) {
        return Factory.makeClass(extractString(i), getLookupClassLoader());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class[] extractTypes(int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(extractString(i), ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            clsArr[i2] = Factory.makeClass(stringTokenizer.nextToken(), getLookupClassLoader());
        }
        return clsArr;
    }

    String fullTypeName(Class cls) {
        return cls == null ? "ANONYMOUS" : cls.isArray() ? GeneratedOutlineSupport1.outline83(new StringBuffer(), fullTypeName(cls.getComponentType()), "[]") : cls.getName().replace('$', '.');
    }

    @Override // org.aspectj.lang.Signature
    public Class getDeclaringType() {
        if (this.declaringType == null) {
            this.declaringType = extractType(2);
        }
        return this.declaringType;
    }

    @Override // org.aspectj.lang.Signature
    public String getDeclaringTypeName() {
        if (this.declaringTypeName == null) {
            this.declaringTypeName = getDeclaringType().getName();
        }
        return this.declaringTypeName;
    }

    @Override // org.aspectj.lang.Signature
    public int getModifiers() {
        if (this.modifiers == -1) {
            this.modifiers = extractInt(0);
        }
        return this.modifiers;
    }

    @Override // org.aspectj.lang.Signature
    public String getName() {
        if (this.name == null) {
            this.name = extractString(1);
        }
        return this.name;
    }

    public void setLookupClassLoader(ClassLoader classLoader) {
        this.lookupClassLoader = classLoader;
    }

    String shortTypeName(Class cls) {
        return cls == null ? "ANONYMOUS" : cls.isArray() ? GeneratedOutlineSupport1.outline83(new StringBuffer(), shortTypeName(cls.getComponentType()), "[]") : stripPackageName(cls.getName()).replace('$', '.');
    }

    String stripPackageName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    @Override // org.aspectj.lang.Signature
    public final String toLongString() {
        return toString(StringMaker.longStringMaker);
    }

    @Override // org.aspectj.lang.Signature
    public final String toShortString() {
        return toString(StringMaker.shortStringMaker);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString(org.aspectj.runtime.reflect.StringMaker r3) {
        /*
            r2 = this;
            boolean r0 = org.aspectj.runtime.reflect.SignatureImpl.useCache
            if (r0 == 0) goto L1b
            org.aspectj.runtime.reflect.SignatureImpl$Cache r0 = r2.stringCache
            if (r0 != 0) goto L14
            org.aspectj.runtime.reflect.SignatureImpl$CacheImpl r0 = new org.aspectj.runtime.reflect.SignatureImpl$CacheImpl     // Catch: java.lang.Throwable -> L10
            r0.<init>()     // Catch: java.lang.Throwable -> L10
            r2.stringCache = r0     // Catch: java.lang.Throwable -> L10
            goto L1b
        L10:
            r0 = 0
            org.aspectj.runtime.reflect.SignatureImpl.useCache = r0
            goto L1b
        L14:
            int r1 = r3.cacheOffset
            java.lang.String r0 = r0.get(r1)
            goto L1c
        L1b:
            r0 = 0
        L1c:
            if (r0 != 0) goto L22
            java.lang.String r0 = r2.createToString(r3)
        L22:
            boolean r1 = org.aspectj.runtime.reflect.SignatureImpl.useCache
            if (r1 == 0) goto L2d
            org.aspectj.runtime.reflect.SignatureImpl$Cache r1 = r2.stringCache
            int r3 = r3.cacheOffset
            r1.set(r3, r0)
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.aspectj.runtime.reflect.SignatureImpl.toString(org.aspectj.runtime.reflect.StringMaker):java.lang.String");
    }

    public SignatureImpl(String str) {
        this.modifiers = -1;
        this.lookupClassLoader = null;
        this.stringRep = str;
    }

    @Override // org.aspectj.lang.Signature
    public final String toString() {
        return toString(StringMaker.middleStringMaker);
    }
}
