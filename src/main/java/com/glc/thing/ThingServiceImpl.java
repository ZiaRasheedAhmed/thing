package com.glc.thing;

import java.util.Optional;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyNameException;
import org.springframework.stereotype.Service;

@Service
public class ThingServiceImpl implements ThingService {
    private ThingRepository thingRepository;

    public ThingServiceImpl (ThingRepository thingRepository){
        this.thingRepository = thingRepository;
    }
    public Thing saveThing(Thing thing){
        Optional<Thing> savedThing = thingRepository.findByName(thing.getName());
        if(savedThing.isPresent()){
            return null;
            // throw new InvalidConfigurationPropertyNameException("Name", thing.getName(), "A thing named", thing.getDescription(), 10L, thing.getQuantity());
        }
        return thingRepository.save(thing);
    }
}
