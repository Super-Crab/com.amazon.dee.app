package org.junit.experimental.categories;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.experimental.categories.Categories;
import org.junit.runner.manipulation.Filter;
/* loaded from: classes5.dex */
public final class IncludeCategories extends CategoryFilterFactory {

    /* loaded from: classes5.dex */
    private static class IncludesAny extends Categories.CategoryFilter {
        public IncludesAny(List<Class<?>> list) {
            this(new HashSet(list));
        }

        @Override // org.junit.experimental.categories.Categories.CategoryFilter, org.junit.runner.manipulation.Filter
        public String describe() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("includes ");
            outline107.append(super.describe());
            return outline107.toString();
        }

        public IncludesAny(Set<Class<?>> set) {
            super(true, set, true, null);
        }
    }

    @Override // org.junit.experimental.categories.CategoryFilterFactory
    protected Filter createFilter(List<Class<?>> list) {
        return new IncludesAny(list);
    }
}
