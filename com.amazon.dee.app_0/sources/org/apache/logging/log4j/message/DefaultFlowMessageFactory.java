package org.apache.logging.log4j.message;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes4.dex */
public class DefaultFlowMessageFactory implements FlowMessageFactory, Serializable {
    private static final String ENTRY_DEFAULT_PREFIX = "Enter";
    private static final String EXIT_DEFAULT_PREFIX = "Exit";
    private static final long serialVersionUID = 8578655591131397576L;
    private final String entryText;
    private final String exitText;

    /* loaded from: classes4.dex */
    private static class AbstractFlowMessage implements FlowMessage {
        private static final long serialVersionUID = 1;
        private final Message message;
        private final String text;

        AbstractFlowMessage(String str, Message message) {
            this.message = message;
            this.text = str;
        }

        @Override // org.apache.logging.log4j.message.Message
        public String getFormat() {
            if (this.message != null) {
                return this.text + RealTimeTextConstants.COLON_SPACE + this.message.getFormat();
            }
            return this.text;
        }

        @Override // org.apache.logging.log4j.message.Message
        public String getFormattedMessage() {
            if (this.message != null) {
                return this.text + " " + this.message.getFormattedMessage();
            }
            return this.text;
        }

        @Override // org.apache.logging.log4j.message.FlowMessage
        public Message getMessage() {
            return this.message;
        }

        @Override // org.apache.logging.log4j.message.Message
        public Object[] getParameters() {
            Message message = this.message;
            if (message != null) {
                return message.getParameters();
            }
            return null;
        }

        @Override // org.apache.logging.log4j.message.FlowMessage
        public String getText() {
            return this.text;
        }

        @Override // org.apache.logging.log4j.message.Message
        public Throwable getThrowable() {
            Message message = this.message;
            if (message != null) {
                return message.getThrowable();
            }
            return null;
        }
    }

    /* loaded from: classes4.dex */
    private static final class SimpleEntryMessage extends AbstractFlowMessage implements EntryMessage {
        private static final long serialVersionUID = 1;

        SimpleEntryMessage(String str, Message message) {
            super(str, message);
        }
    }

    public DefaultFlowMessageFactory() {
        this("Enter", "Exit");
    }

    private Message makeImmutable(Message message) {
        return !(message instanceof ReusableMessage) ? message : new SimpleMessage(message.getFormattedMessage());
    }

    public String getEntryText() {
        return this.entryText;
    }

    public String getExitText() {
        return this.exitText;
    }

    @Override // org.apache.logging.log4j.message.FlowMessageFactory
    public EntryMessage newEntryMessage(Message message) {
        return new SimpleEntryMessage(this.entryText, makeImmutable(message));
    }

    @Override // org.apache.logging.log4j.message.FlowMessageFactory
    public ExitMessage newExitMessage(EntryMessage entryMessage) {
        return new SimpleExitMessage(this.exitText, entryMessage);
    }

    public DefaultFlowMessageFactory(String str, String str2) {
        this.entryText = str;
        this.exitText = str2;
    }

    @Override // org.apache.logging.log4j.message.FlowMessageFactory
    public ExitMessage newExitMessage(Object obj, EntryMessage entryMessage) {
        return new SimpleExitMessage(this.exitText, obj, entryMessage);
    }

    /* loaded from: classes4.dex */
    private static final class SimpleExitMessage extends AbstractFlowMessage implements ExitMessage {
        private static final long serialVersionUID = 1;
        private final boolean isVoid;
        private final Object result;

        SimpleExitMessage(String str, EntryMessage entryMessage) {
            super(str, entryMessage.getMessage());
            this.result = null;
            this.isVoid = true;
        }

        @Override // org.apache.logging.log4j.message.DefaultFlowMessageFactory.AbstractFlowMessage, org.apache.logging.log4j.message.Message
        public String getFormattedMessage() {
            String formattedMessage = super.getFormattedMessage();
            if (this.isVoid) {
                return formattedMessage;
            }
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(formattedMessage, RealTimeTextConstants.COLON_SPACE);
            outline113.append(this.result);
            return outline113.toString();
        }

        SimpleExitMessage(String str, Object obj, EntryMessage entryMessage) {
            super(str, entryMessage.getMessage());
            this.result = obj;
            this.isVoid = false;
        }

        SimpleExitMessage(String str, Object obj, Message message) {
            super(str, message);
            this.result = obj;
            this.isVoid = false;
        }
    }

    @Override // org.apache.logging.log4j.message.FlowMessageFactory
    public ExitMessage newExitMessage(Object obj, Message message) {
        return new SimpleExitMessage(this.exitText, obj, message);
    }
}
