package com.amazon.deecomms.smsmessaging.model;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class Conversation {
    public String id;
    public int unreadMessageCount;
    public List<CommunicableEntity> participants = new ArrayList();
    public List<Message> messages = new ArrayList();
}
