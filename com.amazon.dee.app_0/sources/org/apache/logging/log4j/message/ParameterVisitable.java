package org.apache.logging.log4j.message;

import org.apache.logging.log4j.util.PerformanceSensitive;
@PerformanceSensitive({"allocation"})
/* loaded from: classes4.dex */
public interface ParameterVisitable {
    <S> void forEachParameter(ParameterConsumer<S> parameterConsumer, S s);
}
