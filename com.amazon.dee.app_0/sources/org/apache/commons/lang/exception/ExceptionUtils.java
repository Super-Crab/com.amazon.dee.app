package org.apache.commons.lang.exception;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
/* loaded from: classes4.dex */
public class ExceptionUtils {
    private static final Method THROWABLE_CAUSE_METHOD;
    private static final Method THROWABLE_INITCAUSE_METHOD;
    static final String WRAPPED_MARKER = " [wrapped] ";
    static /* synthetic */ Class class$java$lang$Throwable;
    private static final Object CAUSE_METHOD_NAMES_LOCK = new Object();
    private static String[] CAUSE_METHOD_NAMES = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};

    static {
        Method method;
        Class cls;
        Class<?> cls2;
        Class cls3;
        Method method2 = null;
        try {
            if (class$java$lang$Throwable == null) {
                cls3 = class$("java.lang.Throwable");
                class$java$lang$Throwable = cls3;
            } else {
                cls3 = class$java$lang$Throwable;
            }
            method = cls3.getMethod("getCause", null);
        } catch (Exception unused) {
            method = null;
        }
        THROWABLE_CAUSE_METHOD = method;
        try {
            if (class$java$lang$Throwable == null) {
                cls = class$("java.lang.Throwable");
                class$java$lang$Throwable = cls;
            } else {
                cls = class$java$lang$Throwable;
            }
            Class<?>[] clsArr = new Class[1];
            if (class$java$lang$Throwable == null) {
                cls2 = class$("java.lang.Throwable");
                class$java$lang$Throwable = cls2;
            } else {
                cls2 = class$java$lang$Throwable;
            }
            clsArr[0] = cls2;
            method2 = cls.getMethod("initCause", clsArr);
        } catch (Exception unused2) {
        }
        THROWABLE_INITCAUSE_METHOD = method2;
    }

    public static void addCauseMethodName(String str) {
        if (!StringUtils.isNotEmpty(str) || isCauseMethodName(str)) {
            return;
        }
        ArrayList causeMethodNameList = getCauseMethodNameList();
        if (!causeMethodNameList.add(str)) {
            return;
        }
        synchronized (CAUSE_METHOD_NAMES_LOCK) {
            CAUSE_METHOD_NAMES = toArray(causeMethodNameList);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static Throwable getCause(Throwable th) {
        Throwable cause;
        synchronized (CAUSE_METHOD_NAMES_LOCK) {
            cause = getCause(th, CAUSE_METHOD_NAMES);
        }
        return cause;
    }

    private static ArrayList getCauseMethodNameList() {
        ArrayList arrayList;
        synchronized (CAUSE_METHOD_NAMES_LOCK) {
            arrayList = new ArrayList(Arrays.asList(CAUSE_METHOD_NAMES));
        }
        return arrayList;
    }

    private static Throwable getCauseUsingFieldName(Throwable th, String str) {
        Field field;
        try {
            field = th.getClass().getField(str);
        } catch (NoSuchFieldException | SecurityException unused) {
            field = null;
        }
        if (field != null) {
            Class cls = class$java$lang$Throwable;
            if (cls == null) {
                cls = class$("java.lang.Throwable");
                class$java$lang$Throwable = cls;
            }
            if (cls.isAssignableFrom(field.getType())) {
                try {
                    return (Throwable) field.get(th);
                } catch (IllegalAccessException | IllegalArgumentException unused2) {
                }
            }
        }
        return null;
    }

    private static Throwable getCauseUsingMethodName(Throwable th, String str) {
        Method method;
        try {
            method = th.getClass().getMethod(str, null);
        } catch (NoSuchMethodException | SecurityException unused) {
            method = null;
        }
        if (method != null) {
            Class cls = class$java$lang$Throwable;
            if (cls == null) {
                cls = class$("java.lang.Throwable");
                class$java$lang$Throwable = cls;
            }
            if (cls.isAssignableFrom(method.getReturnType())) {
                try {
                    return (Throwable) method.invoke(th, ArrayUtils.EMPTY_OBJECT_ARRAY);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
                }
            }
        }
        return null;
    }

    private static Throwable getCauseUsingWellKnownTypes(Throwable th) {
        if (th instanceof Nestable) {
            return ((Nestable) th).getCause();
        }
        if (th instanceof SQLException) {
            return ((SQLException) th).getNextException();
        }
        if (!(th instanceof InvocationTargetException)) {
            return null;
        }
        return ((InvocationTargetException) th).getTargetException();
    }

    public static String getFullStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter((Writer) stringWriter, true);
        Throwable[] throwables = getThrowables(th);
        for (int i = 0; i < throwables.length; i++) {
            throwables[i].printStackTrace(printWriter);
            if (isNestedThrowable(throwables[i])) {
                break;
            }
        }
        return stringWriter.getBuffer().toString();
    }

    public static String getMessage(Throwable th) {
        if (th == null) {
            return "";
        }
        String shortClassName = ClassUtils.getShortClassName(th, null);
        String message = th.getMessage();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(shortClassName);
        stringBuffer.append(RealTimeTextConstants.COLON_SPACE);
        stringBuffer.append(StringUtils.defaultString(message));
        return stringBuffer.toString();
    }

    public static Throwable getRootCause(Throwable th) {
        List throwableList = getThrowableList(th);
        if (throwableList.size() < 2) {
            return null;
        }
        return (Throwable) GeneratedOutlineSupport1.outline24(throwableList, -1);
    }

    public static String getRootCauseMessage(Throwable th) {
        Throwable rootCause = getRootCause(th);
        if (rootCause != null) {
            th = rootCause;
        }
        return getMessage(th);
    }

    public static String[] getRootCauseStackTrace(Throwable th) {
        List list;
        if (th == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        Throwable[] throwables = getThrowables(th);
        int length = throwables.length;
        ArrayList arrayList = new ArrayList();
        int i = length - 1;
        List stackFrameList = getStackFrameList(throwables[i]);
        while (true) {
            length--;
            if (length >= 0) {
                if (length != 0) {
                    list = getStackFrameList(throwables[length - 1]);
                    removeCommonFrames(stackFrameList, list);
                } else {
                    list = stackFrameList;
                }
                if (length == i) {
                    arrayList.add(throwables[length].toString());
                } else {
                    StringBuffer outline103 = GeneratedOutlineSupport1.outline103(WRAPPED_MARKER);
                    outline103.append(throwables[length].toString());
                    arrayList.add(outline103.toString());
                }
                for (int i2 = 0; i2 < stackFrameList.size(); i2++) {
                    arrayList.add(stackFrameList.get(i2));
                }
                stackFrameList = list;
            } else {
                return (String[]) arrayList.toArray(new String[0]);
            }
        }
    }

    static List getStackFrameList(Throwable th) {
        StringTokenizer stringTokenizer = new StringTokenizer(getStackTrace(th), SystemUtils.LINE_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf("at");
            if (indexOf != -1 && nextToken.substring(0, indexOf).trim().length() == 0) {
                z = true;
                arrayList.add(nextToken);
            } else if (z) {
                break;
            }
        }
        return arrayList;
    }

    public static String[] getStackFrames(Throwable th) {
        if (th == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return getStackFrames(getStackTrace(th));
    }

    public static String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter((Writer) stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    public static int getThrowableCount(Throwable th) {
        return getThrowableList(th).size();
    }

    public static List getThrowableList(Throwable th) {
        ArrayList arrayList = new ArrayList();
        while (th != null && !arrayList.contains(th)) {
            arrayList.add(th);
            th = getCause(th);
        }
        return arrayList;
    }

    public static Throwable[] getThrowables(Throwable th) {
        List throwableList = getThrowableList(th);
        return (Throwable[]) throwableList.toArray(new Throwable[throwableList.size()]);
    }

    private static int indexOf(Throwable th, Class cls, int i, boolean z) {
        if (th != null && cls != null) {
            if (i < 0) {
                i = 0;
            }
            Throwable[] throwables = getThrowables(th);
            if (i >= throwables.length) {
                return -1;
            }
            if (z) {
                while (i < throwables.length) {
                    if (cls.isAssignableFrom(throwables[i].getClass())) {
                        return i;
                    }
                    i++;
                }
            } else {
                while (i < throwables.length) {
                    if (cls.equals(throwables[i].getClass())) {
                        return i;
                    }
                    i++;
                }
            }
        }
        return -1;
    }

    public static int indexOfThrowable(Throwable th, Class cls) {
        return indexOf(th, cls, 0, false);
    }

    public static int indexOfType(Throwable th, Class cls) {
        return indexOf(th, cls, 0, true);
    }

    public static boolean isCauseMethodName(String str) {
        boolean z;
        synchronized (CAUSE_METHOD_NAMES_LOCK) {
            z = ArrayUtils.indexOf(CAUSE_METHOD_NAMES, str) >= 0;
        }
        return z;
    }

    public static boolean isNestedThrowable(Throwable th) {
        Class cls;
        if (th == null) {
            return false;
        }
        if ((th instanceof Nestable) || (th instanceof SQLException) || (th instanceof InvocationTargetException) || isThrowableNested()) {
            return true;
        }
        Class<?> cls2 = th.getClass();
        synchronized (CAUSE_METHOD_NAMES_LOCK) {
            int length = CAUSE_METHOD_NAMES.length;
            for (int i = 0; i < length; i++) {
                try {
                    Method method = cls2.getMethod(CAUSE_METHOD_NAMES[i], null);
                    if (method == null) {
                        continue;
                    } else {
                        if (class$java$lang$Throwable == null) {
                            cls = class$("java.lang.Throwable");
                            class$java$lang$Throwable = cls;
                        } else {
                            cls = class$java$lang$Throwable;
                        }
                        if (cls.isAssignableFrom(method.getReturnType())) {
                            return true;
                        }
                    }
                } catch (NoSuchMethodException | SecurityException unused) {
                }
            }
            return cls2.getField(MessagingControllerConstant.MESSAGING_CONTROLLER_DETAIL_KEY) != null;
        }
    }

    public static boolean isThrowableNested() {
        return THROWABLE_CAUSE_METHOD != null;
    }

    public static void printRootCauseStackTrace(Throwable th) {
        printRootCauseStackTrace(th, System.err);
    }

    public static void removeCauseMethodName(String str) {
        if (StringUtils.isNotEmpty(str)) {
            ArrayList causeMethodNameList = getCauseMethodNameList();
            if (!causeMethodNameList.remove(str)) {
                return;
            }
            synchronized (CAUSE_METHOD_NAMES_LOCK) {
                CAUSE_METHOD_NAMES = toArray(causeMethodNameList);
            }
        }
    }

    public static void removeCommonFrames(List list, List list2) {
        if (list != null && list2 != null) {
            int size = list.size() - 1;
            for (int size2 = list2.size() - 1; size >= 0 && size2 >= 0; size2--) {
                if (((String) list.get(size)).equals((String) list2.get(size2))) {
                    list.remove(size);
                }
                size--;
            }
            return;
        }
        throw new IllegalArgumentException("The List must not be null");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e A[Catch: IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0033, TryCatch #1 {IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0033, blocks: (B:8:0x0012, B:10:0x001e, B:12:0x0029, B:11:0x0027), top: B:21:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0027 A[Catch: IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0033, TryCatch #1 {IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0033, blocks: (B:8:0x0012, B:10:0x001e, B:12:0x0029, B:11:0x0027), top: B:21:0x0012 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean setCause(java.lang.Throwable r7, java.lang.Throwable r8) {
        /*
            if (r7 == 0) goto L34
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
            r1[r2] = r8
            java.lang.reflect.Method r8 = org.apache.commons.lang.exception.ExceptionUtils.THROWABLE_INITCAUSE_METHOD
            if (r8 == 0) goto L11
            r8.invoke(r7, r1)     // Catch: java.lang.Throwable -> L11
            r8 = r0
            goto L12
        L11:
            r8 = r2
        L12:
            java.lang.Class r3 = r7.getClass()     // Catch: java.lang.Throwable -> L33
            java.lang.String r4 = "setCause"
            java.lang.Class[] r5 = new java.lang.Class[r0]     // Catch: java.lang.Throwable -> L33
            java.lang.Class r6 = org.apache.commons.lang.exception.ExceptionUtils.class$java$lang$Throwable     // Catch: java.lang.Throwable -> L33
            if (r6 != 0) goto L27
            java.lang.String r6 = "java.lang.Throwable"
            java.lang.Class r6 = class$(r6)     // Catch: java.lang.Throwable -> L33
            org.apache.commons.lang.exception.ExceptionUtils.class$java$lang$Throwable = r6     // Catch: java.lang.Throwable -> L33
            goto L29
        L27:
            java.lang.Class r6 = org.apache.commons.lang.exception.ExceptionUtils.class$java$lang$Throwable     // Catch: java.lang.Throwable -> L33
        L29:
            r5[r2] = r6     // Catch: java.lang.Throwable -> L33
            java.lang.reflect.Method r2 = r3.getMethod(r4, r5)     // Catch: java.lang.Throwable -> L33
            r2.invoke(r7, r1)     // Catch: java.lang.Throwable -> L33
            r8 = r0
        L33:
            return r8
        L34:
            org.apache.commons.lang.NullArgumentException r7 = new org.apache.commons.lang.NullArgumentException
            java.lang.String r8 = "target"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.exception.ExceptionUtils.setCause(java.lang.Throwable, java.lang.Throwable):boolean");
    }

    private static String[] toArray(List list) {
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static int indexOfThrowable(Throwable th, Class cls, int i) {
        return indexOf(th, cls, i, false);
    }

    public static int indexOfType(Throwable th, Class cls, int i) {
        return indexOf(th, cls, i, true);
    }

    public static void printRootCauseStackTrace(Throwable th, PrintStream printStream) {
        if (th == null) {
            return;
        }
        if (printStream != null) {
            for (String str : getRootCauseStackTrace(th)) {
                printStream.println(str);
            }
            printStream.flush();
            return;
        }
        throw new IllegalArgumentException("The PrintStream must not be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] getStackFrames(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, SystemUtils.LINE_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return toArray(arrayList);
    }

    public static Throwable getCause(Throwable th, String[] strArr) {
        String str;
        if (th == null) {
            return null;
        }
        Throwable causeUsingWellKnownTypes = getCauseUsingWellKnownTypes(th);
        if (causeUsingWellKnownTypes != null) {
            return causeUsingWellKnownTypes;
        }
        if (strArr == null) {
            synchronized (CAUSE_METHOD_NAMES_LOCK) {
                strArr = CAUSE_METHOD_NAMES;
            }
        }
        for (int i = 0; i < strArr.length && ((str = strArr[i]) == null || (causeUsingWellKnownTypes = getCauseUsingMethodName(th, str)) == null); i++) {
        }
        return causeUsingWellKnownTypes == null ? getCauseUsingFieldName(th, MessagingControllerConstant.MESSAGING_CONTROLLER_DETAIL_KEY) : causeUsingWellKnownTypes;
    }

    public static void printRootCauseStackTrace(Throwable th, PrintWriter printWriter) {
        if (th == null) {
            return;
        }
        if (printWriter != null) {
            for (String str : getRootCauseStackTrace(th)) {
                printWriter.println(str);
            }
            printWriter.flush();
            return;
        }
        throw new IllegalArgumentException("The PrintWriter must not be null");
    }
}
