package junit.runner;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.NumberFormat;
import java.util.Properties;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;
import junit.framework.TestSuite;
/* loaded from: classes3.dex */
public abstract class BaseTestRunner implements TestListener {
    public static final String SUITE_METHODNAME = "suite";
    private static Properties fPreferences = null;
    static boolean fgFilterStack = true;
    boolean fLoading = true;
    static int fgMaxMessageLength = getPreference("maxmessage", fgMaxMessageLength);
    static int fgMaxMessageLength = getPreference("maxmessage", fgMaxMessageLength);

    static boolean filterLine(String str) {
        for (String str2 : new String[]{"junit.framework.TestCase", "junit.framework.TestResult", "junit.framework.TestSuite", "junit.framework.Assert.", "junit.swingui.TestRunner", "junit.awtui.TestRunner", "junit.textui.TestRunner", "java.lang.reflect.Method.invoke("}) {
            if (str.indexOf(str2) > 0) {
                return true;
            }
        }
        return false;
    }

    public static String getFilteredTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return getFilteredTrace(stringWriter.toString());
    }

    public static String getPreference(String str) {
        return getPreferences().getProperty(str);
    }

    protected static Properties getPreferences() {
        if (fPreferences == null) {
            fPreferences = new Properties();
            fPreferences.put("loading", "true");
            fPreferences.put("filterstack", "true");
            readPreferences();
        }
        return fPreferences;
    }

    private static File getPreferencesFile() {
        return new File(System.getProperty("user.home"), "junit.properties");
    }

    private static void readPreferences() {
        FileInputStream fileInputStream;
        Throwable th;
        try {
            fileInputStream = new FileInputStream(getPreferencesFile());
            try {
                setPreferences(new Properties(getPreferences()));
                getPreferences().load(fileInputStream);
            } catch (IOException unused) {
                if (fileInputStream == null) {
                    return;
                }
                fileInputStream.close();
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (IOException unused3) {
            fileInputStream = null;
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
        }
        try {
            fileInputStream.close();
        } catch (IOException unused4) {
        }
    }

    public static void savePreferences() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(getPreferencesFile());
        try {
            getPreferences().store(fileOutputStream, "");
        } finally {
            fileOutputStream.close();
        }
    }

    public static void setPreference(String str, String str2) {
        getPreferences().put(str, str2);
    }

    protected static void setPreferences(Properties properties) {
        fPreferences = properties;
    }

    protected static boolean showStackRaw() {
        return !getPreference("filterstack").equals("true") || !fgFilterStack;
    }

    public static String truncate(String str) {
        if (fgMaxMessageLength == -1 || str.length() <= fgMaxMessageLength) {
            return str;
        }
        return str.substring(0, fgMaxMessageLength) + "...";
    }

    @Override // junit.framework.TestListener
    public synchronized void addError(Test test, Throwable th) {
        testFailed(1, test, th);
    }

    @Override // junit.framework.TestListener
    public synchronized void addFailure(Test test, AssertionFailedError assertionFailedError) {
        testFailed(2, test, assertionFailedError);
    }

    protected void clearStatus() {
    }

    public String elapsedTimeAsString(long j) {
        return NumberFormat.getInstance().format(j / 1000.0d);
    }

    @Override // junit.framework.TestListener
    public synchronized void endTest(Test test) {
        testEnded(test.toString());
    }

    public String extractClassName(String str) {
        return str.startsWith("Default package for") ? str.substring(str.lastIndexOf(".") + 1) : str;
    }

    public Test getTest(String str) {
        Class cls;
        if (str.length() <= 0) {
            clearStatus();
            return null;
        }
        try {
            try {
                Method method = loadSuiteClass(str).getMethod(SUITE_METHODNAME, new Class[0]);
                if (!Modifier.isStatic(method.getModifiers())) {
                    runFailed("Suite() method must be static");
                    return null;
                }
                try {
                    Test test = (Test) method.invoke(null, new Object[0]);
                    if (test == null) {
                        return test;
                    }
                    clearStatus();
                    return test;
                } catch (IllegalAccessException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to invoke suite():");
                    outline107.append(e.toString());
                    runFailed(outline107.toString());
                    return null;
                } catch (InvocationTargetException e2) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Failed to invoke suite():");
                    outline1072.append(e2.getTargetException().toString());
                    runFailed(outline1072.toString());
                    return null;
                }
            } catch (Exception unused) {
                clearStatus();
                return new TestSuite(cls);
            }
        } catch (ClassNotFoundException e3) {
            String message = e3.getMessage();
            if (message != null) {
                str = message;
            }
            runFailed(GeneratedOutlineSupport1.outline75("Class not found \"", str, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED));
            return null;
        } catch (Exception e4) {
            runFailed(GeneratedOutlineSupport1.outline42(e4, GeneratedOutlineSupport1.outline107("Error: ")));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Class<?> loadSuiteClass(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    protected String processArguments(String[] strArr) {
        String str = null;
        int i = 0;
        while (i < strArr.length) {
            if (strArr[i].equals("-noloading")) {
                setLoading(false);
            } else if (strArr[i].equals("-nofilterstack")) {
                fgFilterStack = false;
            } else if (strArr[i].equals("-c")) {
                i++;
                if (strArr.length > i) {
                    str = extractClassName(strArr[i]);
                } else {
                    System.out.println("Missing Test class name");
                }
            } else {
                str = strArr[i];
            }
            i++;
        }
        return str;
    }

    protected abstract void runFailed(String str);

    public void setLoading(boolean z) {
        this.fLoading = z;
    }

    @Override // junit.framework.TestListener
    public synchronized void startTest(Test test) {
        testStarted(test.toString());
    }

    public abstract void testEnded(String str);

    public abstract void testFailed(int i, Test test, Throwable th);

    public abstract void testStarted(String str);

    protected boolean useReloadingTestSuiteLoader() {
        return getPreference("loading").equals("true") && this.fLoading;
    }

    public static int getPreference(String str, int i) {
        String preference = getPreference(str);
        if (preference == null) {
            return i;
        }
        try {
            return Integer.parseInt(preference);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static String getFilteredTrace(String str) {
        if (showStackRaw()) {
            return str;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    if (!filterLine(readLine)) {
                        printWriter.println(readLine);
                    }
                } else {
                    return stringWriter.toString();
                }
            } catch (Exception unused) {
                return str;
            }
        }
    }
}
