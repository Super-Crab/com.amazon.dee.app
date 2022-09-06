package amazon.communication.serialize;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public interface ObjectMapper {
    @FireOsSdk
    <T> T deserialize(InputStream inputStream, TypeReference<T> typeReference) throws IOException;

    @FireOsSdk
    <T> T deserialize(InputStream inputStream, Class<T> cls) throws IOException;

    @FireOsSdk
    <T> ByteBuffer serialize(T t);
}
