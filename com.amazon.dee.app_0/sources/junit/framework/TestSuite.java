package junit.framework;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.junit.internal.MethodSorter;
/* loaded from: classes3.dex */
public class TestSuite implements Test {
    private String fName;
    private Vector<Test> fTests;

    public TestSuite() {
        this.fTests = new Vector<>(10);
    }

    private void addTestMethod(Method method, List<String> list, Class<?> cls) {
        String name = method.getName();
        if (list.contains(name)) {
            return;
        }
        if (!isPublicTestMethod(method)) {
            if (!isTestMethod(method)) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Test method isn't public: ");
            outline107.append(method.getName());
            outline107.append("(");
            outline107.append(cls.getCanonicalName());
            outline107.append(")");
            addTest(warning(outline107.toString()));
            return;
        }
        list.add(name);
        addTest(createTest(cls, name));
    }

    private void addTestsFromTestCase(Class<?> cls) {
        this.fName = cls.getName();
        try {
            getTestConstructor(cls);
            if (!Modifier.isPublic(cls.getModifiers())) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class ");
                outline107.append(cls.getName());
                outline107.append(" is not public");
                addTest(warning(outline107.toString()));
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Class<?> cls2 = cls; Test.class.isAssignableFrom(cls2); cls2 = cls2.getSuperclass()) {
                for (Method method : MethodSorter.getDeclaredMethods(cls2)) {
                    addTestMethod(method, arrayList, cls);
                }
            }
            if (this.fTests.size() != 0) {
                return;
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("No tests found in ");
            outline1072.append(cls.getName());
            addTest(warning(outline1072.toString()));
        } catch (NoSuchMethodException unused) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Class ");
            outline1073.append(cls.getName());
            outline1073.append(" has no public constructor TestCase(String name) or TestCase()");
            addTest(warning(outline1073.toString()));
        }
    }

    public static Test createTest(Class<?> cls, String str) {
        Object newInstance;
        try {
            Constructor<?> testConstructor = getTestConstructor(cls);
            try {
                if (testConstructor.getParameterTypes().length == 0) {
                    newInstance = testConstructor.newInstance(new Object[0]);
                    if (newInstance instanceof TestCase) {
                        ((TestCase) newInstance).setName(str);
                    }
                } else {
                    newInstance = testConstructor.newInstance(str);
                }
                return (Test) newInstance;
            } catch (IllegalAccessException e) {
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Cannot access test case: ", str, " (");
                outline115.append(exceptionToString(e));
                outline115.append(")");
                return warning(outline115.toString());
            } catch (InstantiationException e2) {
                StringBuilder outline1152 = GeneratedOutlineSupport1.outline115("Cannot instantiate test case: ", str, " (");
                outline1152.append(exceptionToString(e2));
                outline1152.append(")");
                return warning(outline1152.toString());
            } catch (InvocationTargetException e3) {
                StringBuilder outline1153 = GeneratedOutlineSupport1.outline115("Exception in constructor: ", str, " (");
                outline1153.append(exceptionToString(e3.getTargetException()));
                outline1153.append(")");
                return warning(outline1153.toString());
            }
        } catch (NoSuchMethodException unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class ");
            outline107.append(cls.getName());
            outline107.append(" has no public constructor TestCase(String name) or TestCase()");
            return warning(outline107.toString());
        }
    }

    private static String exceptionToString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static Constructor<?> getTestConstructor(Class<?> cls) throws NoSuchMethodException {
        try {
            return cls.getConstructor(String.class);
        } catch (NoSuchMethodException unused) {
            return cls.getConstructor(new Class[0]);
        }
    }

    private boolean isPublicTestMethod(Method method) {
        return isTestMethod(method) && Modifier.isPublic(method.getModifiers());
    }

    private boolean isTestMethod(Method method) {
        return method.getParameterTypes().length == 0 && method.getName().startsWith("test") && method.getReturnType().equals(Void.TYPE);
    }

    private Test testCaseForClass(Class<?> cls) {
        if (TestCase.class.isAssignableFrom(cls)) {
            return new TestSuite(cls.asSubclass(TestCase.class));
        }
        return warning(cls.getCanonicalName() + " does not extend TestCase");
    }

    public static Test warning(final String str) {
        return new TestCase("warning") { // from class: junit.framework.TestSuite.1
            @Override // junit.framework.TestCase
            protected void runTest() {
                TestCase.fail(str);
            }
        };
    }

    public void addTest(Test test) {
        this.fTests.add(test);
    }

    public void addTestSuite(Class<? extends TestCase> cls) {
        addTest(new TestSuite(cls));
    }

    @Override // junit.framework.Test
    public int countTestCases() {
        Iterator<Test> it2 = this.fTests.iterator();
        int i = 0;
        while (it2.hasNext()) {
            i += it2.next().countTestCases();
        }
        return i;
    }

    public String getName() {
        return this.fName;
    }

    @Override // junit.framework.Test
    public void run(TestResult testResult) {
        Iterator<Test> it2 = this.fTests.iterator();
        while (it2.hasNext()) {
            Test next = it2.next();
            if (testResult.shouldStop()) {
                return;
            }
            runTest(next, testResult);
        }
    }

    public void runTest(Test test, TestResult testResult) {
        test.run(testResult);
    }

    public void setName(String str) {
        this.fName = str;
    }

    public Test testAt(int i) {
        return this.fTests.get(i);
    }

    public int testCount() {
        return this.fTests.size();
    }

    public Enumeration<Test> tests() {
        return this.fTests.elements();
    }

    public String toString() {
        if (getName() != null) {
            return getName();
        }
        return super.toString();
    }

    public TestSuite(Class<?> cls) {
        this.fTests = new Vector<>(10);
        addTestsFromTestCase(cls);
    }

    public TestSuite(Class<? extends TestCase> cls, String str) {
        this(cls);
        setName(str);
    }

    public TestSuite(String str) {
        this.fTests = new Vector<>(10);
        setName(str);
    }

    public TestSuite(Class<?>... clsArr) {
        this.fTests = new Vector<>(10);
        for (Class<?> cls : clsArr) {
            addTest(testCaseForClass(cls));
        }
    }

    public TestSuite(Class<? extends TestCase>[] clsArr, String str) {
        this(clsArr);
        setName(str);
    }
}
