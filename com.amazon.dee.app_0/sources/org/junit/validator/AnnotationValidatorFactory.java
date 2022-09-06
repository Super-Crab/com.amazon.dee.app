package org.junit.validator;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes5.dex */
public class AnnotationValidatorFactory {
    private static final ConcurrentHashMap<ValidateWith, AnnotationValidator> VALIDATORS_FOR_ANNOTATION_TYPES = new ConcurrentHashMap<>();

    public AnnotationValidator createAnnotationValidator(ValidateWith validateWith) {
        AnnotationValidator annotationValidator = VALIDATORS_FOR_ANNOTATION_TYPES.get(validateWith);
        if (annotationValidator != null) {
            return annotationValidator;
        }
        Class<? extends AnnotationValidator> value = validateWith.value();
        if (value != null) {
            try {
                VALIDATORS_FOR_ANNOTATION_TYPES.putIfAbsent(validateWith, value.newInstance());
                return VALIDATORS_FOR_ANNOTATION_TYPES.get(validateWith);
            } catch (Exception e) {
                throw new RuntimeException(GeneratedOutlineSupport1.outline38(value, GeneratedOutlineSupport1.outline107("Exception received when creating AnnotationValidator class ")), e);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can't create validator, value is null in annotation ");
        outline107.append(validateWith.getClass().getName());
        throw new IllegalArgumentException(outline107.toString());
    }
}
