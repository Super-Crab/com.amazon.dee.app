package org.hamcrest;

import org.hamcrest.internal.ReflectiveTypeFinder;
/* loaded from: classes5.dex */
public abstract class TypeSafeMatcher<T> extends BaseMatcher<T> {
    private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("matchesSafely", 1, 0);
    private final Class<?> expectedType;

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeSafeMatcher() {
        this(TYPE_FINDER);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.hamcrest.BaseMatcher, org.hamcrest.Matcher
    public final void describeMismatch(Object obj, Description description) {
        if (obj == 0) {
            super.describeMismatch(obj, description);
        } else if (!this.expectedType.isInstance(obj)) {
            description.appendText("was a ").appendText(obj.getClass().getName()).appendText(" (").appendValue(obj).appendText(")");
        } else {
            describeMismatchSafely(obj, description);
        }
    }

    protected void describeMismatchSafely(T t, Description description) {
        super.describeMismatch(t, description);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.hamcrest.Matcher
    public final boolean matches(Object obj) {
        return obj != 0 && this.expectedType.isInstance(obj) && matchesSafely(obj);
    }

    protected abstract boolean matchesSafely(T t);

    protected TypeSafeMatcher(Class<?> cls) {
        this.expectedType = cls;
    }

    protected TypeSafeMatcher(ReflectiveTypeFinder reflectiveTypeFinder) {
        this.expectedType = reflectiveTypeFinder.findExpectedType(getClass());
    }
}
