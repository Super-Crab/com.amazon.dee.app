package com.amazon.communication;

import amazon.communication.Message;
import amazon.communication.MessageFactory;
import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Set;
/* loaded from: classes12.dex */
public class TuningMessageParser {
    private static final String DELIMITER = ";";
    public static final String HTTP_HEADER_FIELD_NAME = "x-dp-comm-tuning";
    private final ObjectMapper mJsonMapper = new ObjectMapper();

    public static String encodeProtocolListString(Set<String> set) {
        if (set != null && set.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it2 = set.iterator();
            sb.append(it2.next());
            while (it2.hasNext()) {
                sb.append(DELIMITER);
                sb.append(it2.next());
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Set is null or contains no elements");
    }

    public static String[] parseProtocolListString(String str) {
        if (str != null) {
            return str.split(DELIMITER);
        }
        throw new IllegalArgumentException("Protocol list string cannot be null");
    }

    public Message encodeProtocolParameters(TuningProtocolParameters tuningProtocolParameters) throws ProtocolException {
        if (tuningProtocolParameters != null) {
            try {
                return MessageFactory.createMessage(ByteBuffer.wrap(this.mJsonMapper.writeValueAsBytes(tuningProtocolParameters)));
            } catch (JsonGenerationException e) {
                throw new ProtocolException("parameters are: " + tuningProtocolParameters, e);
            } catch (JsonMappingException e2) {
                throw new ProtocolException("parameters are: " + tuningProtocolParameters, e2);
            } catch (IOException e3) {
                throw new ProtocolException("parameters are: " + tuningProtocolParameters, e3);
            }
        }
        throw new IllegalArgumentException("TuningProtocolParameters cannot be null");
    }

    public TuningProtocolParameters parseProtocolParameters(InputStream inputStream) throws ProtocolException {
        if (inputStream != null) {
            try {
                return (TuningProtocolParameters) this.mJsonMapper.readValue(inputStream, TuningProtocolParameters.class);
            } catch (JsonParseException e) {
                throw new ProtocolException(e);
            } catch (JsonMappingException e2) {
                throw new ProtocolException(e2);
            } catch (IOException e3) {
                throw new ProtocolException(e3);
            }
        }
        throw new IllegalArgumentException("Tuning InputStream cannot be null");
    }
}
