package com.tibiawiki.serviceinterface.config;

import com.tibiawiki.serviceinterface.*;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JerseyConfig extends ResourceConfig {

    @Value("${spring.jersey.application-path:/}")
    private String apiPath;

    @Autowired
    Environment environment;

    public JerseyConfig() {
        registerEndpoints();
    }

    @PostConstruct
    public void init() {
        // Register components where DI is needed
        this.configureSwagger();
    }

    private void registerEndpoints() {
        register(CORSResponseFilter.class);
        register(AchievementsResource.class);
        register(BooksResource.class);
        register(BuildingsResource.class);
        register(CorpsesResource.class);
        register(CreaturesResource.class);
        register(EffectsResource.class);
        register(LocationsResource.class);
        register(LootStatisticsResource.class);
        register(LootStatisticsV2Resource.class);
        register(HuntingPlacesResource.class);
        register(ItemsResource.class);
        register(KeysResource.class);
        register(MissilesResource.class);
        register(MountsResource.class);
        register(NPCsResource.class);
        register(ObjectsResource.class);
        register(OutfitsResource.class);
        register(QuestsResource.class);
        register(SpellsResource.class);
        register(StreetsResource.class);
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setConfigId("tibiawikiapi");
        beanConfig.setTitle("TibiaWikiApi");
        beanConfig.setVersion("1.8.0");
        beanConfig.setContact("B. Komen");
        beanConfig.setSchemes(new String[]{"https"});
        beanConfig.setBasePath(this.apiPath); // location where dynamically created swagger.json is reachable
        beanConfig.setHost("tibiawiki.dev");
        beanConfig.setResourcePackage("com.tibiawiki");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true); // scan packages via setResourcePackage
    }
}
