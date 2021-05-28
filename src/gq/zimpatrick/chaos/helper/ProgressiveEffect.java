package gq.zimpatrick.chaos.helper;

public abstract class ProgressiveEffect extends ChaosEffect {
    public long startTime;
    public ProgressiveEffect(String name) {
        super(name);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.startTime = System.currentTimeMillis();
    }
}
