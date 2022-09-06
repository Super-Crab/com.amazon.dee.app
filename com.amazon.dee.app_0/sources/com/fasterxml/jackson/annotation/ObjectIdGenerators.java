package com.fasterxml.jackson.annotation;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import java.util.UUID;
/* loaded from: classes2.dex */
public class ObjectIdGenerators {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class Base<T> extends ObjectIdGenerator<T> {
        protected final Class<?> _scope;

        protected Base(Class<?> cls) {
            this._scope = cls;
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator.getClass() == getClass() && objectIdGenerator.getScope() == this._scope;
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        /* renamed from: generateId */
        public abstract T mo6985generateId(Object obj);

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public Class<?> getScope() {
            return this._scope;
        }
    }

    /* loaded from: classes2.dex */
    public static final class IntSequenceGenerator extends Base<Integer> {
        private static final long serialVersionUID = 1;
        protected transient int _nextValue;

        public IntSequenceGenerator() {
            this(Object.class, -1);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerators.Base, com.fasterxml.jackson.annotation.ObjectIdGenerator
        public /* bridge */ /* synthetic */ boolean canUseFor(ObjectIdGenerator objectIdGenerator) {
            return super.canUseFor(objectIdGenerator);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public ObjectIdGenerator<Integer> forScope(Class<?> cls) {
            return this._scope == cls ? this : new IntSequenceGenerator(cls, this._nextValue);
        }

        protected int initialValue() {
            return 1;
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public ObjectIdGenerator.IdKey key(Object obj) {
            if (obj == null) {
                return null;
            }
            return new ObjectIdGenerator.IdKey(IntSequenceGenerator.class, this._scope, obj);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public ObjectIdGenerator<Integer> newForSerialization(Object obj) {
            return new IntSequenceGenerator(this._scope, initialValue());
        }

        public IntSequenceGenerator(Class<?> cls, int i) {
            super(cls);
            this._nextValue = i;
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerators.Base, com.fasterxml.jackson.annotation.ObjectIdGenerator
        /* renamed from: generateId */
        public Integer mo6985generateId(Object obj) {
            if (obj == null) {
                return null;
            }
            int i = this._nextValue;
            this._nextValue = i + 1;
            return Integer.valueOf(i);
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class None extends ObjectIdGenerator<Object> {
    }

    /* loaded from: classes2.dex */
    public static abstract class PropertyGenerator extends Base<Object> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: Access modifiers changed from: protected */
        public PropertyGenerator(Class<?> cls) {
            super(cls);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerators.Base, com.fasterxml.jackson.annotation.ObjectIdGenerator
        public /* bridge */ /* synthetic */ boolean canUseFor(ObjectIdGenerator objectIdGenerator) {
            return super.canUseFor(objectIdGenerator);
        }
    }

    /* loaded from: classes2.dex */
    public static final class StringIdGenerator extends Base<String> {
        private static final long serialVersionUID = 1;

        public StringIdGenerator() {
            this(Object.class);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerators.Base, com.fasterxml.jackson.annotation.ObjectIdGenerator
        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator instanceof StringIdGenerator;
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public ObjectIdGenerator<String> forScope(Class<?> cls) {
            return this;
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public ObjectIdGenerator.IdKey key(Object obj) {
            if (obj == null) {
                return null;
            }
            return new ObjectIdGenerator.IdKey(StringIdGenerator.class, null, obj);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public ObjectIdGenerator<String> newForSerialization(Object obj) {
            return this;
        }

        private StringIdGenerator(Class<?> cls) {
            super(Object.class);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerators.Base, com.fasterxml.jackson.annotation.ObjectIdGenerator
        /* renamed from: generateId  reason: collision with other method in class */
        public String mo6985generateId(Object obj) {
            return UUID.randomUUID().toString();
        }
    }

    /* loaded from: classes2.dex */
    public static final class UUIDGenerator extends Base<UUID> {
        private static final long serialVersionUID = 1;

        public UUIDGenerator() {
            this(Object.class);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerators.Base, com.fasterxml.jackson.annotation.ObjectIdGenerator
        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator.getClass() == UUIDGenerator.class;
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public ObjectIdGenerator<UUID> forScope(Class<?> cls) {
            return this;
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public ObjectIdGenerator.IdKey key(Object obj) {
            if (obj == null) {
                return null;
            }
            return new ObjectIdGenerator.IdKey(UUIDGenerator.class, null, obj);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerator
        public ObjectIdGenerator<UUID> newForSerialization(Object obj) {
            return this;
        }

        private UUIDGenerator(Class<?> cls) {
            super(Object.class);
        }

        @Override // com.fasterxml.jackson.annotation.ObjectIdGenerators.Base, com.fasterxml.jackson.annotation.ObjectIdGenerator
        /* renamed from: generateId  reason: collision with other method in class */
        public UUID mo6985generateId(Object obj) {
            return UUID.randomUUID();
        }
    }
}
