package io.pinect.azeron.example.client.config;

import io.pinect.azeron.client.EnableAzeronClient;
import io.pinect.azeron.client.service.EventListenerRegistry;
import io.pinect.azeron.client.service.api.NatsConfigProvider;
import io.pinect.azeron.example.client.listener.FullStrategyListener;
import io.pinect.azeron.example.client.listener.NoAzeronStrategyListener;
import io.pinect.azeron.example.client.listener.SeenAsyncStrategyListener;
import io.pinect.azeron.example.client.listener.SeenFirstStrategyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@EnableAzeronClient
@Configuration
@EnableFeignClients(basePackages = "io.pinect.azeron.example.client.azeron")
public class AzeronConfiguration {
    private final FullStrategyListener fullStrategyListener;
    private final SeenAsyncStrategyListener seenAsyncStrategyListener;
    private final SeenFirstStrategyListener seenFirstStrategyListener;
    private final NoAzeronStrategyListener noAzeronStrategyListener;
    private final EventListenerRegistry eventListenerRegistry;

    @Autowired
    public AzeronConfiguration(FullStrategyListener fullStrategyListener, SeenAsyncStrategyListener seenAsyncStrategyListener, SeenFirstStrategyListener seenFirstStrategyListener, NoAzeronStrategyListener noAzeronStrategyListener, EventListenerRegistry eventListenerRegistry) {
        this.fullStrategyListener = fullStrategyListener;
        this.seenAsyncStrategyListener = seenAsyncStrategyListener;
        this.seenFirstStrategyListener = seenFirstStrategyListener;
        this.noAzeronStrategyListener = noAzeronStrategyListener;
        this.eventListenerRegistry = eventListenerRegistry;
    }


    @PostConstruct
    public void registerServices(){
        eventListenerRegistry.register(fullStrategyListener);
        eventListenerRegistry.register(seenAsyncStrategyListener);
        eventListenerRegistry.register(seenFirstStrategyListener);
        eventListenerRegistry.register(noAzeronStrategyListener);
    }
}
