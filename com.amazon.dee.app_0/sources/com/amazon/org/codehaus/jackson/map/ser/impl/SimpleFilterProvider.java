package com.amazon.org.codehaus.jackson.map.ser.impl;

import com.amazon.org.codehaus.jackson.map.ser.BeanPropertyFilter;
import com.amazon.org.codehaus.jackson.map.ser.FilterProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class SimpleFilterProvider extends FilterProvider {
    protected boolean _cfgFailOnUnknownId;
    protected BeanPropertyFilter _defaultFilter;
    protected final Map<String, BeanPropertyFilter> _filtersById;

    public SimpleFilterProvider() {
        this(new HashMap());
    }

    public SimpleFilterProvider addFilter(String str, BeanPropertyFilter beanPropertyFilter) {
        this._filtersById.put(str, beanPropertyFilter);
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.FilterProvider
    public BeanPropertyFilter findFilter(Object obj) {
        BeanPropertyFilter beanPropertyFilter = this._filtersById.get(obj);
        if (beanPropertyFilter == null && (beanPropertyFilter = this._defaultFilter) == null && this._cfgFailOnUnknownId) {
            StringBuilder sb = new StringBuilder();
            sb.append("No filter configured with id '");
            sb.append(obj);
            sb.append("' (type ");
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline46(obj, sb, ")"));
        }
        return beanPropertyFilter;
    }

    public BeanPropertyFilter getDefaultFilter() {
        return this._defaultFilter;
    }

    public BeanPropertyFilter removeFilter(String str) {
        return this._filtersById.remove(str);
    }

    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter beanPropertyFilter) {
        this._defaultFilter = beanPropertyFilter;
        return this;
    }

    public SimpleFilterProvider setFailOnUnknownId(boolean z) {
        this._cfgFailOnUnknownId = z;
        return this;
    }

    public boolean willFailOnUnknownId() {
        return this._cfgFailOnUnknownId;
    }

    public SimpleFilterProvider(Map<String, BeanPropertyFilter> map) {
        this._cfgFailOnUnknownId = true;
        this._filtersById = map;
    }
}
