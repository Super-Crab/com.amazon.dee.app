package com.amazon.communication;

import amazon.communication.CommunicationBaseException;
/* loaded from: classes12.dex */
public class TuningFailedException extends CommunicationBaseException {
    public TuningFailedException(Exception exc) {
        super(exc);
    }

    public TuningFailedException(String str, Exception exc) {
        super(str, exc);
    }

    public TuningFailedException(String str) {
        super(str);
    }
}
