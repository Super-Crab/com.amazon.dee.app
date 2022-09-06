package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.internal.ReflectiveTypeFinder;
/* loaded from: classes5.dex */
public abstract class TypeSafeDiagnosingMatcher<T> extends BaseMatcher<T> {
    private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("matchesSafely", 2, 0);
    private final Class<?> expectedType;

    protected TypeSafeDiagnosingMatcher(Class<?> cls) {
        this.expectedType = cls;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.hamcrest.BaseMatcher, org.hamcrest.Matcher
    public final void describeMismatch(Object obj, Description description) {
        if (obj != 0 && this.expectedType.isInstance(obj)) {
            matchesSafely(obj, description);
        } else {
            super.describeMismatch(obj, description);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.hamcrest.Matcher
    public final boolean matches(Object obj) {
        return obj != 0 && this.expectedType.isInstance(obj) && matchesSafely(obj, new Description.NullDescription());
    }

    protected abstract boolean matchesSafely(T t, Description description);

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeSafeDiagnosingMatcher(ReflectiveTypeFinder reflectiveTypeFinder) {
        this.expectedType = reflectiveTypeFinder.findExpectedType(getClass());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeSafeDiagnosingMatcher() {
        this(TYPE_FINDER);
    }
}
