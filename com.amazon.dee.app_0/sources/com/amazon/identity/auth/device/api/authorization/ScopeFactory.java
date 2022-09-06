package com.amazon.identity.auth.device.api.authorization;

import org.json.JSONObject;
/* loaded from: classes12.dex */
public final class ScopeFactory {

    /* loaded from: classes12.dex */
    static final class GenericScope implements Scope {
        private final String name;
        private final JSONObject scopeData;

        GenericScope(String str) {
            this(str, null);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || GenericScope.class != obj.getClass()) {
                return false;
            }
            GenericScope genericScope = (GenericScope) obj;
            String str = this.name;
            if (str == null) {
                if (genericScope.name != null) {
                    return false;
                }
            } else if (!str.equals(genericScope.name)) {
                return false;
            }
            JSONObject jSONObject = this.scopeData;
            if (jSONObject == null) {
                if (genericScope.scopeData != null) {
                    return false;
                }
            } else if (!jSONObject.equals(genericScope.scopeData)) {
                return false;
            }
            return true;
        }

        @Override // com.amazon.identity.auth.device.api.authorization.Scope
        public String getName() {
            return this.name;
        }

        @Override // com.amazon.identity.auth.device.api.authorization.Scope
        public JSONObject getScopeData() {
            return this.scopeData;
        }

        public int hashCode() {
            String str = this.name;
            int i = 0;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            JSONObject jSONObject = this.scopeData;
            if (jSONObject != null) {
                i = jSONObject.hashCode();
            }
            return hashCode + i;
        }

        GenericScope(String str, JSONObject jSONObject) {
            if (str != null) {
                this.name = str;
                this.scopeData = jSONObject;
                return;
            }
            throw new IllegalArgumentException("Scope must have a name");
        }
    }

    private ScopeFactory() {
    }

    public static Scope scopeNamed(String str) {
        return new GenericScope(str);
    }

    public static Scope scopeNamed(String str, JSONObject jSONObject) {
        return new GenericScope(str, jSONObject);
    }
}
