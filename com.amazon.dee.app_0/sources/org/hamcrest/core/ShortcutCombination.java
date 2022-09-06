package org.hamcrest.core;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
/* loaded from: classes5.dex */
abstract class ShortcutCombination<T> extends BaseMatcher<T> {
    private final Iterable<Matcher<? super T>> matchers;

    public ShortcutCombination(Iterable<Matcher<? super T>> iterable) {
        this.matchers = iterable;
    }

    @Override // org.hamcrest.SelfDescribing
    public abstract void describeTo(Description description);

    public void describeTo(Description description, String str) {
        description.appendList("(", GeneratedOutlineSupport1.outline75(" ", str, " "), ")", this.matchers);
    }

    @Override // org.hamcrest.Matcher
    public abstract boolean matches(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean matches(Object obj, boolean z) {
        for (Matcher<? super T> matcher : this.matchers) {
            if (matcher.matches(obj) == z) {
                return z;
            }
        }
        return !z;
    }
}
