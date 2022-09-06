package com.amazon.alexa.accessory.repositories.calling;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public final class ATCommand {
    private final String command;
    private final List<String> parameters;

    /* loaded from: classes6.dex */
    public static final class Builder {
        String command;
        List<String> parameters;

        public ATCommand build() {
            Preconditions.notNull(this.command, "command");
            if (this.parameters == null) {
                this.parameters = Collections.emptyList();
            }
            return new ATCommand(this);
        }

        public Builder command(String str) {
            this.command = str;
            return this;
        }

        public Builder parameter(String str) {
            if (this.parameters == null) {
                this.parameters = new ArrayList();
            }
            this.parameters.add(str);
            return this;
        }
    }

    ATCommand(Builder builder) {
        this.command = builder.command;
        this.parameters = builder.parameters;
    }

    public static ATCommand from(String str) {
        return new Builder().command(str).build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ATCommand.class != obj.getClass()) {
            return false;
        }
        ATCommand aTCommand = (ATCommand) obj;
        if (this.command.equals(aTCommand.command)) {
            return this.parameters.equals(aTCommand.parameters);
        }
        return false;
    }

    public String getCommand() {
        return this.command;
    }

    public List<String> getParameters() {
        return Collections.unmodifiableList(this.parameters);
    }

    public int hashCode() {
        return this.parameters.hashCode() + (this.command.hashCode() * 31);
    }

    public String toString() {
        return this.command + StringUtils.join(this.parameters) + "\r\n";
    }
}
