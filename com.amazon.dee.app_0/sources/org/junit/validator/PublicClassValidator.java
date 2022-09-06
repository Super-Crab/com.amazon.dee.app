package org.junit.validator;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.List;
import org.junit.runners.model.TestClass;
/* loaded from: classes5.dex */
public class PublicClassValidator implements TestClassValidator {
    private static final List<Exception> NO_VALIDATION_ERRORS = Collections.emptyList();

    @Override // org.junit.validator.TestClassValidator
    public List<Exception> validateTestClass(TestClass testClass) {
        if (testClass.isPublic()) {
            return NO_VALIDATION_ERRORS;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The class ");
        outline107.append(testClass.getName());
        outline107.append(" is not public.");
        return Collections.singletonList(new Exception(outline107.toString()));
    }
}
