package amazon.communication.serialize;

import com.amazon.communication.serialize.IonObjectMapper;
import com.amazon.communication.serialize.JsonObjectMapper;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.util.List;
/* loaded from: classes.dex */
public final class ObjectMapperFactory {

    /* renamed from: amazon.communication.serialize.ObjectMapperFactory$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$communication$serialize$ObjectMapperFactory$ContentType = new int[ContentType.values().length];

        static {
            try {
                $SwitchMap$amazon$communication$serialize$ObjectMapperFactory$ContentType[ContentType.JSON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$communication$serialize$ObjectMapperFactory$ContentType[ContentType.ION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum ContentType {
        JSON("application/json"),
        ION("application/ion");
        
        private final String mContentType;

        ContentType(String str) {
            this.mContentType = str;
        }

        @Override // java.lang.Enum
        @FireOsSdk
        public String toString() {
            return this.mContentType;
        }
    }

    private ObjectMapperFactory() {
    }

    @FireOsSdk
    public static ObjectMapper newObjectMapper(ContentType contentType) {
        if (contentType != null) {
            int ordinal = contentType.ordinal();
            if (ordinal == 0) {
                return new JsonObjectMapper();
            }
            if (ordinal == 1) {
                return new IonObjectMapper();
            }
            throw new IllegalArgumentException("Unsupported contentType: " + contentType);
        }
        throw new IllegalArgumentException("null contentType passed.");
    }

    public static ObjectMapper newObjectMapper(ContentType contentType, List<String> list) {
        if (contentType != null) {
            if (list != null) {
                int ordinal = contentType.ordinal();
                if (ordinal == 0) {
                    return new JsonObjectMapper(list);
                }
                if (ordinal == 1) {
                    return new IonObjectMapper(list);
                }
                throw new IllegalArgumentException("Unsupported contentType: " + contentType);
            }
            throw new IllegalArgumentException("null ignorableFields passed.");
        }
        throw new IllegalArgumentException("null contentType passed.");
    }
}
