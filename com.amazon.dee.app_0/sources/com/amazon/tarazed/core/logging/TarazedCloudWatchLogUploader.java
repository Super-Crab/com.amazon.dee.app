package com.amazon.tarazed.core.logging;

import com.amazon.tarazed.core.sessionclient.model.createcredentials.LoggingCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.services.logs.AmazonCloudWatchLogsClient;
import com.amazonaws.services.logs.model.InputLogEvent;
import com.amazonaws.services.logs.model.PutLogEventsRequest;
import com.amazonaws.services.logs.model.PutLogEventsResult;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedCloudWatchLogUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedCloudWatchLogUploader;", "", "appender", "Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "(Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;)V", "parser", "Lcom/amazon/tarazed/core/logging/TarazedLogParser;", "uploadLogs", "", "loggingCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedCloudWatchLogUploader {
    private final TarazedLogFileAppender appender;
    private final TarazedLogParser parser;

    public TarazedCloudWatchLogUploader(@NotNull TarazedLogFileAppender appender) {
        Intrinsics.checkParameterIsNotNull(appender, "appender");
        this.appender = appender;
        this.parser = new TarazedLogParser(this.appender);
    }

    public final void uploadLogs(@NotNull LoggingCredentials loggingCredentials) {
        Intrinsics.checkParameterIsNotNull(loggingCredentials, "loggingCredentials");
        AmazonCloudWatchLogsClient amazonCloudWatchLogsClient = new AmazonCloudWatchLogsClient(new StaticCredentialsProvider(new BasicSessionCredentials(loggingCredentials.getAccessKey(), loggingCredentials.getSecretKey(), loggingCredentials.getSessionToken())));
        amazonCloudWatchLogsClient.setEndpoint(loggingCredentials.getEndpoint());
        this.appender.stop();
        try {
            String str = null;
            for (Collection<InputLogEvent> collection : this.parser.parseLogEvents()) {
                PutLogEventsRequest logEventsRequest = new PutLogEventsRequest().withLogGroupName(loggingCredentials.getLogGroup()).withLogStreamName(loggingCredentials.getLogStream()).withLogEvents(collection);
                if (str != null) {
                    Intrinsics.checkExpressionValueIsNotNull(logEventsRequest, "logEventsRequest");
                    logEventsRequest.setSequenceToken(str);
                }
                PutLogEventsResult response = amazonCloudWatchLogsClient.putLogEvents(logEventsRequest);
                Intrinsics.checkExpressionValueIsNotNull(response, "response");
                str = response.getNextSequenceToken();
            }
            this.appender.clearLogs();
        } finally {
            this.appender.start();
        }
    }
}
