package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import java.util.List;
/* loaded from: classes13.dex */
public class BeanSerializerBuilder {
    private static final BeanPropertyWriter[] NO_PROPERTIES = new BeanPropertyWriter[0];
    protected AnyGetterWriter _anyGetter;
    protected final BasicBeanDescription _beanDesc;
    protected Object _filterId;
    protected BeanPropertyWriter[] _filteredProperties;
    protected List<BeanPropertyWriter> _properties;

    public BeanSerializerBuilder(BasicBeanDescription basicBeanDescription) {
        this._beanDesc = basicBeanDescription;
    }

    public JsonSerializer<?> build() {
        BeanPropertyWriter[] beanPropertyWriterArr;
        List<BeanPropertyWriter> list = this._properties;
        if (list != null && !list.isEmpty()) {
            List<BeanPropertyWriter> list2 = this._properties;
            beanPropertyWriterArr = (BeanPropertyWriter[]) list2.toArray(new BeanPropertyWriter[list2.size()]);
        } else if (this._anyGetter == null) {
            return null;
        } else {
            beanPropertyWriterArr = NO_PROPERTIES;
        }
        return new BeanSerializer(this._beanDesc.getType(), beanPropertyWriterArr, this._filteredProperties, this._anyGetter, this._filterId);
    }

    public BeanSerializer createDummy() {
        return BeanSerializer.createDummy(this._beanDesc.getBeanClass());
    }

    public BasicBeanDescription getBeanDescription() {
        return this._beanDesc;
    }

    public BeanPropertyWriter[] getFilteredProperties() {
        return this._filteredProperties;
    }

    public List<BeanPropertyWriter> getProperties() {
        return this._properties;
    }

    public boolean hasProperties() {
        List<BeanPropertyWriter> list = this._properties;
        return list != null && list.size() > 0;
    }

    public void setAnyGetter(AnyGetterWriter anyGetterWriter) {
        this._anyGetter = anyGetterWriter;
    }

    public void setFilterId(Object obj) {
        this._filterId = obj;
    }

    public void setFilteredProperties(BeanPropertyWriter[] beanPropertyWriterArr) {
        this._filteredProperties = beanPropertyWriterArr;
    }

    public void setProperties(List<BeanPropertyWriter> list) {
        this._properties = list;
    }

    protected BeanSerializerBuilder(BeanSerializerBuilder beanSerializerBuilder) {
        this._beanDesc = beanSerializerBuilder._beanDesc;
        this._properties = beanSerializerBuilder._properties;
        this._filteredProperties = beanSerializerBuilder._filteredProperties;
        this._anyGetter = beanSerializerBuilder._anyGetter;
        this._filterId = beanSerializerBuilder._filterId;
    }
}
