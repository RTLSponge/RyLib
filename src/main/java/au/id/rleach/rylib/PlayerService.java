package au.id.rleach.rylib;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.common.util.concurrent.MoreExecutors;
import org.spongepowered.api.scheduler.SpongeExecutorService;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

abstract class SpongePlayerService extends AbstractScheduledService {

    public ScheduledExecutorService executor;

    private UUID player;

    private SpongePlayerService(SpongeExecutorService executor, UUID player) {
        this.executor = executor;
        this.player = player;
    }

    @Override
    protected ScheduledExecutorService executor() {
        this.addListener(
                new Listener() {

                    public void terminated(State from) {
                        SpongePlayerService.this.executor.shutdown();
                    }

                    public void failed(State from, Throwable failure) {
                        SpongePlayerService.this.executor.shutdown();
                    }
                },
                MoreExecutors.directExecutor());

        return executor;
    }

    protected abstract void startUp() throws Exception ;

    protected abstract void runOneIteration() throws Exception ;

    protected abstract void shutDown() throws Exception ;

    public UUID getPlayer() {
        return player;
    }
}