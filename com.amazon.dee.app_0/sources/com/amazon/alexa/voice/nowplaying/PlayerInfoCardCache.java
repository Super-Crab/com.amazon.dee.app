package com.amazon.alexa.voice.nowplaying;

import com.amazon.alexa.voice.ui.player.PlayerCardModel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
/* loaded from: classes11.dex */
final class PlayerInfoCardCache {
    private static final int DEFAULT_CACHE_SIZE = 5;
    private final Queue<String> audioItemIds;
    private final int cacheSize;
    private final Map<String, PlayerCardModel> playerCardsCache;

    private PlayerInfoCardCache(int i) {
        this.cacheSize = i;
        this.playerCardsCache = new HashMap();
        this.audioItemIds = new LinkedList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean contains(String str) {
        return this.playerCardsCache.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized PlayerCardModel get(String str) {
        return this.playerCardsCache.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void put(PlayerCardModel playerCardModel) {
        String audioItemId = playerCardModel.getAudioItemId();
        if (this.audioItemIds.contains(audioItemId)) {
            this.audioItemIds.remove(audioItemId);
        }
        this.audioItemIds.add(audioItemId);
        this.playerCardsCache.put(audioItemId, playerCardModel);
        if (this.playerCardsCache.size() > this.cacheSize) {
            this.playerCardsCache.remove(this.audioItemIds.poll());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlayerInfoCardCache() {
        this(5);
    }
}
