package pl.robalmeister.goxysectorsplugin.pubsub;

import pl.goxy.minecraft.pubsub.PubSub;

public abstract class PubSubAbstract {
    protected final PubSub pubSub;

    public PubSubAbstract(PubSub pubSub) {
        this.pubSub = pubSub;
    }
    public abstract void load();

}
