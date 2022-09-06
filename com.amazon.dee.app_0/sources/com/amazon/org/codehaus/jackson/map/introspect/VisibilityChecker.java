package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.amazon.org.codehaus.jackson.annotate.JsonMethod;
import com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
/* loaded from: classes13.dex */
public interface VisibilityChecker<T extends VisibilityChecker<T>> {

    /* renamed from: com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonMethod = new int[JsonMethod.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.GETTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.SETTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.CREATOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.FIELD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.IS_GETTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$annotate$JsonMethod[JsonMethod.ALL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, isGetterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, setterVisibility = JsonAutoDetect.Visibility.ANY)
    /* loaded from: classes13.dex */
    public static class Std implements VisibilityChecker<Std> {
        protected static final Std DEFAULT = new Std((JsonAutoDetect) Std.class.getAnnotation(JsonAutoDetect.class));
        protected final JsonAutoDetect.Visibility _creatorMinLevel;
        protected final JsonAutoDetect.Visibility _fieldMinLevel;
        protected final JsonAutoDetect.Visibility _getterMinLevel;
        protected final JsonAutoDetect.Visibility _isGetterMinLevel;
        protected final JsonAutoDetect.Visibility _setterMinLevel;

        public Std(JsonAutoDetect jsonAutoDetect) {
            JsonMethod[] value = jsonAutoDetect.value();
            this._getterMinLevel = hasMethod(value, JsonMethod.GETTER) ? jsonAutoDetect.getterVisibility() : JsonAutoDetect.Visibility.NONE;
            this._isGetterMinLevel = hasMethod(value, JsonMethod.IS_GETTER) ? jsonAutoDetect.isGetterVisibility() : JsonAutoDetect.Visibility.NONE;
            this._setterMinLevel = hasMethod(value, JsonMethod.SETTER) ? jsonAutoDetect.setterVisibility() : JsonAutoDetect.Visibility.NONE;
            this._creatorMinLevel = hasMethod(value, JsonMethod.CREATOR) ? jsonAutoDetect.creatorVisibility() : JsonAutoDetect.Visibility.NONE;
            this._fieldMinLevel = hasMethod(value, JsonMethod.FIELD) ? jsonAutoDetect.fieldVisibility() : JsonAutoDetect.Visibility.NONE;
        }

        public static Std defaultInstance() {
            return DEFAULT;
        }

        private static boolean hasMethod(JsonMethod[] jsonMethodArr, JsonMethod jsonMethod) {
            for (JsonMethod jsonMethod2 : jsonMethodArr) {
                if (jsonMethod2 == jsonMethod || jsonMethod2 == JsonMethod.ALL) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isCreatorVisible(Member member) {
            return this._creatorMinLevel.isVisible(member);
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isFieldVisible(Field field) {
            return this._fieldMinLevel.isVisible(field);
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isGetterVisible(Method method) {
            return this._getterMinLevel.isVisible(method);
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isIsGetterVisible(Method method) {
            return this._isGetterMinLevel.isVisible(method);
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isSetterVisible(Method method) {
            return this._setterMinLevel.isVisible(method);
        }

        public String toString() {
            StringBuilder outline112 = GeneratedOutlineSupport1.outline112("[Visibility:", " getter: ");
            outline112.append(this._getterMinLevel);
            outline112.append(", isGetter: ");
            outline112.append(this._isGetterMinLevel);
            outline112.append(", setter: ");
            outline112.append(this._setterMinLevel);
            outline112.append(", creator: ");
            outline112.append(this._creatorMinLevel);
            outline112.append(", field: ");
            outline112.append(this._fieldMinLevel);
            outline112.append("]");
            return outline112.toString();
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isCreatorVisible(AnnotatedMember annotatedMember) {
            return isCreatorVisible(annotatedMember.getMember());
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isFieldVisible(AnnotatedField annotatedField) {
            return isFieldVisible(annotatedField.mo4213getAnnotated());
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isGetterVisible(AnnotatedMethod annotatedMethod) {
            return isGetterVisible(annotatedMethod.mo4213getAnnotated());
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isIsGetterVisible(AnnotatedMethod annotatedMethod) {
            return isIsGetterVisible(annotatedMethod.mo4213getAnnotated());
        }

        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isSetterVisible(AnnotatedMethod annotatedMethod) {
            return isSetterVisible(annotatedMethod.mo4213getAnnotated());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        /* renamed from: withCreatorVisibility */
        public Std mo4220withCreatorVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._creatorMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._creatorMinLevel == visibility2 ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, visibility2, this._fieldMinLevel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        /* renamed from: withFieldVisibility */
        public Std mo4221withFieldVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._fieldMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._fieldMinLevel == visibility2 ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, visibility2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        /* renamed from: withGetterVisibility */
        public Std mo4222withGetterVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._getterMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._getterMinLevel == visibility2 ? this : new Std(visibility2, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        /* renamed from: withIsGetterVisibility */
        public Std mo4223withIsGetterVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._isGetterMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._isGetterMinLevel == visibility2 ? this : new Std(this._getterMinLevel, visibility2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        /* renamed from: withSetterVisibility */
        public Std mo4224withSetterVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._setterMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._setterMinLevel == visibility2 ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, visibility2, this._creatorMinLevel, this._fieldMinLevel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        /* renamed from: withVisibility */
        public Std mo4225withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
            int ordinal = jsonMethod.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    return mo4224withSetterVisibility(visibility);
                }
                if (ordinal == 2) {
                    return mo4220withCreatorVisibility(visibility);
                }
                if (ordinal == 3) {
                    return mo4221withFieldVisibility(visibility);
                }
                if (ordinal == 4) {
                    return mo4223withIsGetterVisibility(visibility);
                }
                return ordinal != 6 ? this : mo4218with(visibility);
            }
            return mo4222withGetterVisibility(visibility);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        /* renamed from: with */
        public Std mo4219with(JsonAutoDetect jsonAutoDetect) {
            if (jsonAutoDetect == null) {
                return this;
            }
            JsonMethod[] value = jsonAutoDetect.value();
            return mo4222withGetterVisibility(hasMethod(value, JsonMethod.GETTER) ? jsonAutoDetect.getterVisibility() : JsonAutoDetect.Visibility.NONE).mo4223withIsGetterVisibility(hasMethod(value, JsonMethod.IS_GETTER) ? jsonAutoDetect.isGetterVisibility() : JsonAutoDetect.Visibility.NONE).mo4224withSetterVisibility(hasMethod(value, JsonMethod.SETTER) ? jsonAutoDetect.setterVisibility() : JsonAutoDetect.Visibility.NONE).mo4220withCreatorVisibility(hasMethod(value, JsonMethod.CREATOR) ? jsonAutoDetect.creatorVisibility() : JsonAutoDetect.Visibility.NONE).mo4221withFieldVisibility(hasMethod(value, JsonMethod.FIELD) ? jsonAutoDetect.fieldVisibility() : JsonAutoDetect.Visibility.NONE);
        }

        public Std(JsonAutoDetect.Visibility visibility, JsonAutoDetect.Visibility visibility2, JsonAutoDetect.Visibility visibility3, JsonAutoDetect.Visibility visibility4, JsonAutoDetect.Visibility visibility5) {
            this._getterMinLevel = visibility;
            this._isGetterMinLevel = visibility2;
            this._setterMinLevel = visibility3;
            this._creatorMinLevel = visibility4;
            this._fieldMinLevel = visibility5;
        }

        public Std(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                Std std = DEFAULT;
                this._getterMinLevel = std._getterMinLevel;
                this._isGetterMinLevel = std._isGetterMinLevel;
                this._setterMinLevel = std._setterMinLevel;
                this._creatorMinLevel = std._creatorMinLevel;
                this._fieldMinLevel = std._fieldMinLevel;
                return;
            }
            this._getterMinLevel = visibility;
            this._isGetterMinLevel = visibility;
            this._setterMinLevel = visibility;
            this._creatorMinLevel = visibility;
            this._fieldMinLevel = visibility;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.org.codehaus.jackson.map.introspect.VisibilityChecker
        /* renamed from: with */
        public Std mo4218with(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                return DEFAULT;
            }
            return new Std(visibility);
        }
    }

    boolean isCreatorVisible(AnnotatedMember annotatedMember);

    boolean isCreatorVisible(Member member);

    boolean isFieldVisible(AnnotatedField annotatedField);

    boolean isFieldVisible(Field field);

    boolean isGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isGetterVisible(Method method);

    boolean isIsGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isIsGetterVisible(Method method);

    boolean isSetterVisible(AnnotatedMethod annotatedMethod);

    boolean isSetterVisible(Method method);

    /* renamed from: with */
    T mo4218with(JsonAutoDetect.Visibility visibility);

    /* renamed from: with */
    T mo4219with(JsonAutoDetect jsonAutoDetect);

    /* renamed from: withCreatorVisibility */
    T mo4220withCreatorVisibility(JsonAutoDetect.Visibility visibility);

    /* renamed from: withFieldVisibility */
    T mo4221withFieldVisibility(JsonAutoDetect.Visibility visibility);

    /* renamed from: withGetterVisibility */
    T mo4222withGetterVisibility(JsonAutoDetect.Visibility visibility);

    /* renamed from: withIsGetterVisibility */
    T mo4223withIsGetterVisibility(JsonAutoDetect.Visibility visibility);

    /* renamed from: withSetterVisibility */
    T mo4224withSetterVisibility(JsonAutoDetect.Visibility visibility);

    /* renamed from: withVisibility */
    T mo4225withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility);
}
