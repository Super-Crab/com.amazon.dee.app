package org.apache.logging.log4j.message;

import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StringBuilderFormattable;
@PerformanceSensitive({"allocation"})
/* loaded from: classes4.dex */
public interface ReusableMessage extends Message, StringBuilderFormattable {
    short getParameterCount();

    Message memento();

    Object[] swapParameters(Object[] objArr);
}
