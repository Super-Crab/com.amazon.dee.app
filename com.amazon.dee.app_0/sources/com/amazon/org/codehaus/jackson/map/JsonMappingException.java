package com.amazon.org.codehaus.jackson.map;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.org.codehaus.jackson.JsonLocation;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public class JsonMappingException extends JsonProcessingException {
    static final int MAX_REFS_TO_LIST = 1000;
    private static final long serialVersionUID = 1;
    protected LinkedList<Reference> _path;

    public JsonMappingException(String str) {
        super(str);
    }

    public static JsonMappingException from(JsonParser jsonParser, String str) {
        return new JsonMappingException(str, jsonParser.getTokenLocation());
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, String str) {
        return wrapWithPath(th, new Reference(obj, str));
    }

    protected void _appendPathDesc(StringBuilder sb) {
        Iterator<Reference> it2 = this._path.iterator();
        while (it2.hasNext()) {
            sb.append(it2.next().toString());
            if (it2.hasNext()) {
                sb.append("->");
            }
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonProcessingException, java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (this._path == null) {
            return message;
        }
        StringBuilder sb = message == null ? new StringBuilder() : new StringBuilder(message);
        sb.append(" (through reference chain: ");
        _appendPathDesc(sb);
        sb.append(')');
        return sb.toString();
    }

    public List<Reference> getPath() {
        LinkedList<Reference> linkedList = this._path;
        if (linkedList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(linkedList);
    }

    public void prependPath(Object obj, String str) {
        prependPath(new Reference(obj, str));
    }

    @Override // com.amazon.org.codehaus.jackson.JsonProcessingException, java.lang.Throwable
    public String toString() {
        return getClass().getName() + RealTimeTextConstants.COLON_SPACE + getMessage();
    }

    /* loaded from: classes13.dex */
    public static class Reference implements Serializable {
        private static final long serialVersionUID = 1;
        protected String _fieldName;
        protected Object _from;
        protected int _index;

        protected Reference() {
            this._index = -1;
        }

        public String getFieldName() {
            return this._fieldName;
        }

        public Object getFrom() {
            return this._from;
        }

        public int getIndex() {
            return this._index;
        }

        public void setFieldName(String str) {
            this._fieldName = str;
        }

        public void setFrom(Object obj) {
            this._from = obj;
        }

        public void setIndex(int i) {
            this._index = i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Object obj = this._from;
            Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
            Package r2 = cls.getPackage();
            if (r2 != null) {
                sb.append(r2.getName());
                sb.append('.');
            }
            sb.append(cls.getSimpleName());
            sb.append(JsonReaderKt.BEGIN_LIST);
            if (this._fieldName != null) {
                sb.append('\"');
                sb.append(this._fieldName);
                sb.append('\"');
            } else {
                int i = this._index;
                if (i >= 0) {
                    sb.append(i);
                } else {
                    sb.append(Constants.DEFAULT_IMAGE_CHAR);
                }
            }
            sb.append(JsonReaderKt.END_LIST);
            return sb.toString();
        }

        public Reference(Object obj) {
            this._index = -1;
            this._from = obj;
        }

        public Reference(Object obj, String str) {
            this._index = -1;
            this._from = obj;
            if (str != null) {
                this._fieldName = str;
                return;
            }
            throw new NullPointerException("Can not pass null fieldName");
        }

        public Reference(Object obj, int i) {
            this._index = -1;
            this._from = obj;
            this._index = i;
        }
    }

    public JsonMappingException(String str, Throwable th) {
        super(str, th);
    }

    public static JsonMappingException from(JsonParser jsonParser, String str, Throwable th) {
        return new JsonMappingException(str, jsonParser.getTokenLocation(), th);
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, int i) {
        return wrapWithPath(th, new Reference(obj, i));
    }

    public JsonMappingException(String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }

    public static JsonMappingException wrapWithPath(Throwable th, Reference reference) {
        JsonMappingException jsonMappingException;
        if (th instanceof JsonMappingException) {
            jsonMappingException = (JsonMappingException) th;
        } else {
            String message = th.getMessage();
            if (message == null || message.length() == 0) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("(was ");
                outline107.append(th.getClass().getName());
                outline107.append(")");
                message = outline107.toString();
            }
            jsonMappingException = new JsonMappingException(message, null, th);
        }
        jsonMappingException.prependPath(reference);
        return jsonMappingException;
    }

    public void prependPath(Object obj, int i) {
        prependPath(new Reference(obj, i));
    }

    public JsonMappingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
    }

    public void prependPath(Reference reference) {
        if (this._path == null) {
            this._path = new LinkedList<>();
        }
        if (this._path.size() < 1000) {
            this._path.addFirst(reference);
        }
    }
}
