package org.hamcrest.core;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
/* loaded from: classes5.dex */
public class IsInstanceOf extends DiagnosingMatcher<Object> {
    private final Class<?> expectedClass;
    private final Class<?> matchableClass;

    public IsInstanceOf(Class<?> cls) {
        this.expectedClass = cls;
        this.matchableClass = matchableClass(cls);
    }

    @Factory
    public static <T> Matcher<T> any(Class<T> cls) {
        return new IsInstanceOf(cls);
    }

    @Factory
    public static <T> Matcher<T> instanceOf(Class<?> cls) {
        return new IsInstanceOf(cls);
    }

    private static Class<?> matchableClass(Class<?> cls) {
        return Boolean.TYPE.equals(cls) ? Boolean.class : Byte.TYPE.equals(cls) ? Byte.class : Character.TYPE.equals(cls) ? Character.class : Double.TYPE.equals(cls) ? Double.class : Float.TYPE.equals(cls) ? Float.class : Integer.TYPE.equals(cls) ? Integer.class : Long.TYPE.equals(cls) ? Long.class : Short.TYPE.equals(cls) ? Short.class : cls;
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("an instance of ").appendText(this.expectedClass.getName());
    }

    @Override // org.hamcrest.DiagnosingMatcher
    protected boolean matches(Object obj, Description description) {
        if (obj == null) {
            description.appendText("null");
            return false;
        } else if (this.matchableClass.isInstance(obj)) {
            return true;
        } else {
            Description appendValue = description.appendValue(obj);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" is a ");
            outline107.append(obj.getClass().getName());
            appendValue.appendText(outline107.toString());
            return false;
        }
    }
}
