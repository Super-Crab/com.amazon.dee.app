package org.apache.logging.log4j.message;

import java.util.Map;
import org.apache.logging.log4j.util.PerformanceSensitive;
@AsynchronouslyFormattable
@PerformanceSensitive({"allocation"})
/* loaded from: classes4.dex */
public class StringMapMessage extends MapMessage<StringMapMessage, String> {
    private static final long serialVersionUID = 1;

    public StringMapMessage() {
    }

    public StringMapMessage(int i) {
        super(i);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public StringMapMessage newInstance(Map<String, String> map) {
        return new StringMapMessage(map);
    }

    public StringMapMessage(Map<String, String> map) {
        super(map);
    }
}
