package com.amazon.alexa.voice.tta.typing;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.tta.R;
import com.amazon.alexa.voice.tta.metrics.EventTime;
import com.amazon.alexa.voice.tta.metrics.MetricEventProcessingService;
import com.amazon.alexa.voice.tta.metrics.TtaConversationEvent;
import com.amazon.alexa.voice.tta.typing.TypingModel;
import com.amazon.alexa.voice.ui.tta.TtaMessage;
import com.amazon.alexa.voice.ui.tta.TtaResponseMessage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class MessageHistoryManager {
    private static final String TAG = "MessageHistoryManager";
    private final Context context;
    private final ConversationSessionTimer conversationSessionTimer;
    private final MetricEventProcessingService eventProcessingService;
    private TypingModel.MessageHistoryListener messageHistoryListener;
    private final PersistentStorage persistentStorage;
    private boolean shouldClearMessageHistory = false;
    private static final List<TtaMessage> MESSAGE_HISTORY = new ArrayList();
    @VisibleForTesting
    static boolean isFirstTimeUserLoggedIn = true;

    /* loaded from: classes11.dex */
    class ClearMessageHistoryTask implements Runnable {
        ClearMessageHistoryTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String unused = MessageHistoryManager.TAG;
            MessageHistoryManager.this.clearMessageHistory();
            MessageHistoryManager.this.clearConversationUi();
            if (MessageHistoryManager.this.persistentStorage.getIsUserOnTtaScreen()) {
                String unused2 = MessageHistoryManager.TAG;
                MessageHistoryManager.this.eventProcessingService.processEvent(TtaConversationEvent.MESSAGE_REFRESH_SHOWN, EventTime.now());
                MessageHistoryManager messageHistoryManager = MessageHistoryManager.this;
                messageHistoryManager.appendToMessageHistory(new TextTtaResponseMessage(messageHistoryManager.context.getString(R.string.tta_time_out_message)));
                MessageHistoryManager.this.messageHistoryListener.onMessageHistory(MessageHistoryManager.MESSAGE_HISTORY);
                MessageHistoryManager.this.messageHistoryListener.enableAudioMessage();
                MessageHistoryManager.this.conversationSessionTimer.resetTimer();
            }
        }
    }

    public MessageHistoryManager(Context context, PersistentStorage persistentStorage, ConversationSessionTimer conversationSessionTimer, MetricEventProcessingService metricEventProcessingService) {
        this.context = context;
        this.persistentStorage = persistentStorage;
        this.conversationSessionTimer = conversationSessionTimer;
        this.eventProcessingService = metricEventProcessingService;
        conversationSessionTimer.initialize(new ClearMessageHistoryTask());
    }

    private int findResponseMessageBasedOnId(TtaMessage ttaMessage) {
        if (!(ttaMessage instanceof TtaResponseMessage)) {
            return -1;
        }
        String id = ((TtaResponseMessage) ttaMessage).getId();
        for (int i = 0; i < MESSAGE_HISTORY.size(); i++) {
            TtaMessage ttaMessage2 = MESSAGE_HISTORY.get(i);
            if ((ttaMessage2 instanceof TtaResponseMessage) && ((TtaResponseMessage) ttaMessage2).getId().contentEquals(id)) {
                return i;
            }
        }
        return -1;
    }

    private List<TtaMessage> getWelcomeMessages(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (this.persistentStorage.getIsFirstTimeUser()) {
            this.eventProcessingService.processEvent(TtaConversationEvent.MESSAGE_WELCOME_SHOWN, EventTime.now());
            String[] stringArray = this.context.getResources().getStringArray(R.array.new_user_messages);
            arrayList.add(new TextTtaResponseMessage(stringArray[0]));
            if (!z && stringArray.length > 1) {
                arrayList.add(new TextTtaResponseMessage(stringArray[1]));
            }
            setShouldClearMessageHistory(true);
            this.persistentStorage.setIsFirstTimeUser(false);
        } else {
            arrayList.add(new TextTtaResponseMessage(this.context.getString(R.string.returning_user_message)));
            this.eventProcessingService.processEvent(TtaConversationEvent.MESSAGE_RETURNING_SHOWN, EventTime.now());
        }
        return arrayList;
    }

    private boolean updateToMessageHistory(TtaMessage ttaMessage) {
        int findResponseMessageBasedOnId = findResponseMessageBasedOnId(ttaMessage);
        if (findResponseMessageBasedOnId != -1) {
            MESSAGE_HISTORY.set(findResponseMessageBasedOnId, ttaMessage);
            return true;
        }
        return false;
    }

    public void appendToMessageHistory(TtaMessage ttaMessage) {
        if (!updateToMessageHistory(ttaMessage)) {
            MESSAGE_HISTORY.add(ttaMessage);
        }
    }

    public void checkIfUserLoggedOut() {
        if (this.persistentStorage.getIsFirstTimeUser() && !isFirstTimeUserLoggedIn && !MESSAGE_HISTORY.isEmpty()) {
            this.eventProcessingService.processEvent(TtaConversationEvent.DIALOG_SESSION_ENDED, EventTime.now());
        }
        isFirstTimeUserLoggedIn = false;
    }

    public void cleanUp() {
        if (this.shouldClearMessageHistory) {
            clearMessageHistory();
        }
    }

    public void clearConversationUi() {
        this.messageHistoryListener.onClearConversationUi();
    }

    public void clearMessageHistory() {
        MESSAGE_HISTORY.clear();
    }

    public TtaResponseMessage getLastTtaResponse() {
        if (!MESSAGE_HISTORY.isEmpty()) {
            TtaMessage ttaMessage = (TtaMessage) GeneratedOutlineSupport1.outline24(MESSAGE_HISTORY, -1);
            if (!(ttaMessage instanceof TtaResponseMessage)) {
                return null;
            }
            return (TtaResponseMessage) ttaMessage;
        }
        return null;
    }

    public boolean isMessageHistoryEmpty() {
        return MESSAGE_HISTORY.isEmpty();
    }

    public void setMessageHistoryListener(TypingModel.MessageHistoryListener messageHistoryListener) {
        this.messageHistoryListener = messageHistoryListener;
    }

    public void setShouldClearMessageHistory(boolean z) {
        this.shouldClearMessageHistory = z;
    }

    public void showMessageHistory(boolean z) {
        if (!MESSAGE_HISTORY.isEmpty() && !this.persistentStorage.getIsFirstTimeUser()) {
            this.eventProcessingService.processEvent(TtaConversationEvent.MESSAGE_HISTORY_SHOWN, EventTime.now());
        } else {
            clearMessageHistory();
            appendToMessageHistory(getWelcomeMessages(z));
        }
        this.messageHistoryListener.onMessageHistory(MESSAGE_HISTORY);
    }

    public void updateMessageHistoryOnResponse(TtaResponseMessage ttaResponseMessage) {
        appendToMessageHistory(ttaResponseMessage);
        if (this.persistentStorage.getIsUserOnTtaScreen()) {
            this.conversationSessionTimer.resetTimer();
        }
    }

    public void appendToMessageHistory(List<TtaMessage> list) {
        MESSAGE_HISTORY.addAll(list);
    }
}
