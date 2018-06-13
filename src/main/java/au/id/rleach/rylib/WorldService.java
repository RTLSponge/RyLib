package au.id.rleach.rylib;

import org.slf4j.Logger;
import org.spongepowered.api.world.World;

import javax.inject.Inject;

public abstract class WorldService {
    World world;

    public WorldService(World world) {
        this.world = world;
    }

    abstract void tick();
    abstract void start();
    abstract void stop();

    public static class TimeTracker extends WorldService {
        private final Logger logger;
        private int count;

        @Inject
        public TimeTracker(World world, Logger logger) {
            super(world);
            this.logger = logger;
        }

        @Override
        void tick() {
            count++;
        }

        @Override
        void start() {
            count = 0;
        }

        @Override
        void stop() {
            logger.debug("World loaded for "+count+" ticks");
        }
    }
}


