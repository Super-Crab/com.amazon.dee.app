package org.junit.runners.parameterized;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Parameterized;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
/* loaded from: classes5.dex */
public class BlockJUnit4ClassRunnerWithParameters extends BlockJUnit4ClassRunner {
    private final String name;
    private final Object[] parameters;

    public BlockJUnit4ClassRunnerWithParameters(TestWithParameters testWithParameters) throws InitializationError {
        super(testWithParameters.getTestClass().getJavaClass());
        this.parameters = testWithParameters.getParameters().toArray(new Object[testWithParameters.getParameters().size()]);
        this.name = testWithParameters.getName();
    }

    private Object createTestUsingConstructorInjection() throws Exception {
        return getTestClass().getOnlyConstructor().newInstance(this.parameters);
    }

    private Object createTestUsingFieldInjection() throws Exception {
        List<FrameworkField> annotatedFieldsByParameter = getAnnotatedFieldsByParameter();
        if (annotatedFieldsByParameter.size() == this.parameters.length) {
            Object newInstance = getTestClass().getJavaClass().newInstance();
            for (FrameworkField frameworkField : annotatedFieldsByParameter) {
                Field field = frameworkField.getField();
                int value = ((Parameterized.Parameter) field.getAnnotation(Parameterized.Parameter.class)).value();
                try {
                    field.set(newInstance, this.parameters[value]);
                } catch (IllegalArgumentException e) {
                    throw new Exception(getTestClass().getName() + ": Trying to set " + field.getName() + " with the value " + this.parameters[value] + " that is not the right type (" + this.parameters[value].getClass().getSimpleName() + " instead of " + field.getType().getSimpleName() + ").", e);
                }
            }
            return newInstance;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Wrong number of parameters and @Parameter fields. @Parameter fields counted: ");
        outline107.append(annotatedFieldsByParameter.size());
        outline107.append(", available parameters: ");
        throw new Exception(GeneratedOutlineSupport1.outline86(outline107, this.parameters.length, "."));
    }

    private boolean fieldsAreAnnotated() {
        return !getAnnotatedFieldsByParameter().isEmpty();
    }

    private List<FrameworkField> getAnnotatedFieldsByParameter() {
        return getTestClass().getAnnotatedFields(Parameterized.Parameter.class);
    }

    @Override // org.junit.runners.ParentRunner
    protected Statement classBlock(RunNotifier runNotifier) {
        return childrenInvoker(runNotifier);
    }

    @Override // org.junit.runners.BlockJUnit4ClassRunner
    public Object createTest() throws Exception {
        if (fieldsAreAnnotated()) {
            return createTestUsingFieldInjection();
        }
        return createTestUsingConstructorInjection();
    }

    @Override // org.junit.runners.ParentRunner
    protected String getName() {
        return this.name;
    }

    @Override // org.junit.runners.ParentRunner
    protected Annotation[] getRunnerAnnotations() {
        return new Annotation[0];
    }

    @Override // org.junit.runners.BlockJUnit4ClassRunner
    protected String testName(FrameworkMethod frameworkMethod) {
        return frameworkMethod.getName() + getName();
    }

    @Override // org.junit.runners.BlockJUnit4ClassRunner
    protected void validateConstructor(List<Throwable> list) {
        validateOnlyOneConstructor(list);
        if (fieldsAreAnnotated()) {
            validateZeroArgConstructor(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.junit.runners.BlockJUnit4ClassRunner
    public void validateFields(List<Throwable> list) {
        super.validateFields(list);
        if (fieldsAreAnnotated()) {
            List<FrameworkField> annotatedFieldsByParameter = getAnnotatedFieldsByParameter();
            int[] iArr = new int[annotatedFieldsByParameter.size()];
            for (FrameworkField frameworkField : annotatedFieldsByParameter) {
                int value = ((Parameterized.Parameter) frameworkField.getField().getAnnotation(Parameterized.Parameter.class)).value();
                if (value >= 0 && value <= annotatedFieldsByParameter.size() - 1) {
                    iArr[value] = iArr[value] + 1;
                } else {
                    StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Invalid @Parameter value: ", value, ". @Parameter fields counted: ");
                    outline109.append(annotatedFieldsByParameter.size());
                    outline109.append(". Please use an index between 0 and ");
                    outline109.append(annotatedFieldsByParameter.size() - 1);
                    outline109.append(".");
                    list.add(new Exception(outline109.toString()));
                }
            }
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                if (i2 == 0) {
                    list.add(new Exception(GeneratedOutlineSupport1.outline52("@Parameter(", i, ") is never used.")));
                } else if (i2 > 1) {
                    list.add(new Exception(GeneratedOutlineSupport1.outline54("@Parameter(", i, ") is used more than once (", i2, ").")));
                }
            }
        }
    }
}
