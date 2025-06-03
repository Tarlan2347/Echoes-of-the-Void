package net.tarlan.echoes.event;

public class Event {
    private boolean cancelled = false;

    public boolean isCancellable()
    {
        return false;
    }

    public boolean isCancelled()
    {
        return this.cancelled;
    }

    public void setCancelled(boolean value)
    {
        if (!this.isCancellable())
            throw new UnsupportedOperationException("Attempted to cancel event which cannot be cancelled!");

        this.cancelled = value;
    }
}
