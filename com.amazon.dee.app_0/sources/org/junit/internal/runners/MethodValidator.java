package org.junit.internal.runners;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
@Deprecated
/* loaded from: classes5.dex */
public class MethodValidator {
    private final List<Throwable> errors = new ArrayList();
    private TestClass testClass;

    public MethodValidator(TestClass testClass) {
        this.testClass = testClass;
    }

    private void validateTestMethods(Class<? extends Annotation> cls, boolean z) {
        for (Method method : this.testClass.getAnnotatedMethods(cls)) {
            if (Modifier.isStatic(method.getModifiers()) != z) {
                String str = z ? "should" : "should not";
                List<Throwable> list = this.errors;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Method ");
                outline107.append(method.getName());
                outline107.append("() ");
                outline107.append(str);
                outline107.append(" be static");
                list.add(new Exception(outline107.toString()));
            }
            if (!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
                List<Throwable> list2 = this.errors;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Class ");
                outline1072.append(method.getDeclaringClass().getName());
                outline1072.append(" should be public");
                list2.add(new Exception(outline1072.toString()));
            }
            if (!Modifier.isPublic(method.getModifiers())) {
                List<Throwable> list3 = this.errors;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Method ");
                outline1073.append(method.getName());
                outline1073.append(" should be public");
                list3.add(new Exception(outline1073.toString()));
            }
            if (method.getReturnType() != Void.TYPE) {
                List<Throwable> list4 = this.errors;
                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Method ");
                outline1074.append(method.getName());
                outline1074.append(" should be void");
                list4.add(new Exception(outline1074.toString()));
            }
            if (method.getParameterTypes().length != 0) {
                List<Throwable> list5 = this.errors;
                StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Method ");
                outline1075.append(method.getName());
                outline1075.append(" should have no parameters");
                list5.add(new Exception(outline1075.toString()));
            }
        }
    }

    public void assertValid() throws InitializationError {
        if (this.errors.isEmpty()) {
            return;
        }
        throw new InitializationError(this.errors);
    }

    public void validateInstanceMethods() {
        validateTestMethods(After.class, false);
        validateTestMethods(Before.class, false);
        validateTestMethods(Test.class, false);
        if (this.testClass.getAnnotatedMethods(Test.class).size() == 0) {
            this.errors.add(new Exception("No runnable methods"));
        }
    }

    public List<Throwable> validateMethodsForDefaultRunner() {
        validateNoArgConstructor();
        validateStaticMethods();
        validateInstanceMethods();
        return this.errors;
    }

    public void validateNoArgConstructor() {
        try {
            this.testClass.getConstructor();
        } catch (Exception e) {
            this.errors.add(new Exception("Test class should have public zero-argument constructor", e));
        }
    }

    public void validateStaticMethods() {
        validateTestMethods(BeforeClass.class, true);
        validateTestMethods(AfterClass.class, true);
    }
}
