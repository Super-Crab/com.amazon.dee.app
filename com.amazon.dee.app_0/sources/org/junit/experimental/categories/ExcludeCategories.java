package org.junit.experimental.categories;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.experimental.categories.Categories;
import org.junit.runner.manipulation.Filter;
/* loaded from: classes5.dex */
public final class ExcludeCategories extends CategoryFilterFactory {

    /* loaded from: classes5.dex */
    private static class ExcludesAny extends Categories.CategoryFilter {
        public ExcludesAny(List<Class<?>> list) {
            this(new HashSet(list));
        }

        @Override // org.junit.experimental.categories.Categories.CategoryFilter, org.junit.runner.manipulation.Filter
        public String describe() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("excludes ");
            outline107.append(super.describe());
            return outline107.toString();
        }

        public ExcludesAny(Set<Class<?>> set) {
            super(true, null, true, set);
        }
    }

    @Override // org.junit.experimental.categories.CategoryFilterFactory
    protected Filter createFilter(List<Class<?>> list) {
        return new ExcludesAny(list);
    }
}
