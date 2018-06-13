package au.id.rleach.rylib;

import com.google.inject.AbstractModule;
import com.mastfrog.giulius.scope.ReentrantScope;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.world.World;

public class ScopeModule {
    ReentrantScope scope;
    class MyModule extends AbstractModule {
        public void configure() {
            ReentrantScope scope = new ReentrantScope();
            bind (ReentrantScope.class).toInstance(scope);

            // there is no getting around explitly binding classes, but
            // you can provide a way to pass in an array easily enough:

            scope.bindTypes(binder(), World.class);
        }
    }

    public class TheApp {

        public void contextually(Runnable run) {
            Sponge.getGame().getCauseStackManager()
                    .getContext(EventContextKeys.)
            try (AutoCloseable ac = scope.enter()) {
                // Anything with a Provider<FooRequest>, etc. will
                // get the passed request
            }
            run
        }

        public void onRequest(FooRequest req) {
            FooResponse resp = new FooResponse();

        }
    }
}
