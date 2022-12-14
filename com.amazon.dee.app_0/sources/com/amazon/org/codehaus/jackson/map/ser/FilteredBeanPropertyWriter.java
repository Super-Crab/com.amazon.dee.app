package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
/* loaded from: classes13.dex */
public abstract class FilteredBeanPropertyWriter {

    /* loaded from: classes13.dex */
    private static final class MultiView extends BeanPropertyWriter {
        protected final BeanPropertyWriter _delegate;
        protected final Class<?>[] _views;

        protected MultiView(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
            super(beanPropertyWriter);
            this._delegate = beanPropertyWriter;
            this._views = clsArr;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Class<?> serializationView = serializerProvider.getSerializationView();
            if (serializationView != null) {
                int i = 0;
                int length = this._views.length;
                while (i < length && !this._views[i].isAssignableFrom(serializationView)) {
                    i++;
                }
                if (i == length) {
                    return;
                }
            }
            this._delegate.serializeAsField(obj, jsonGenerator, serializerProvider);
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter
        public BeanPropertyWriter withSerializer(JsonSerializer<Object> jsonSerializer) {
            return new MultiView(this._delegate.withSerializer(jsonSerializer), this._views);
        }
    }

    /* loaded from: classes13.dex */
    private static final class SingleView extends BeanPropertyWriter {
        protected final BeanPropertyWriter _delegate;
        protected final Class<?> _view;

        protected SingleView(BeanPropertyWriter beanPropertyWriter, Class<?> cls) {
            super(beanPropertyWriter);
            this._delegate = beanPropertyWriter;
            this._view = cls;
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Class<?> serializationView = serializerProvider.getSerializationView();
            if (serializationView == null || this._view.isAssignableFrom(serializationView)) {
                this._delegate.serializeAsField(obj, jsonGenerator, serializerProvider);
            }
        }

        @Override // com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter
        public BeanPropertyWriter withSerializer(JsonSerializer<Object> jsonSerializer) {
            return new SingleView(this._delegate.withSerializer(jsonSerializer), this._view);
        }
    }

    public static BeanPropertyWriter constructViewBased(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
        if (clsArr.length == 1) {
            return new SingleView(beanPropertyWriter, clsArr[0]);
        }
        return new MultiView(beanPropertyWriter, clsArr);
    }
}
