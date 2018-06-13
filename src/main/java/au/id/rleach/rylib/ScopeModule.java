package au.id.rleach.rylib;

import com.google.inject.AbstractModule;

public class ScopeModule {
    class MyModule extends AbstractModule {
        public void configure() {
            ReentrantScope scope = new ReentrantScope();
            bind (ReentrantScope.class).toInstance(scope);

            // there is no getting around explitly binding classes, but
            // you can provide a way to pass in an array easily enough:

            scope.bindTypes(binder(), FooRequest.class, FooResponse.class, ...);
        }
    }

    public class TheApp {

        public void onRequest(FooRequest req) {
            FooResponse resp = new FooResponse();
            try (AutoCloseable ac = scope.enter(req, resp)) {
                // Anything with a Provider<FooRequest>, etc. will
                // get the passed request
            }
        }
    }
}
