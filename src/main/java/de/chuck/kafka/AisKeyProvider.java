package de.chuck.kafka;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;

import de.chuck.ais.AisMessage;

@Default
@ApplicationScoped
public class AisKeyProvider implements KeyProvider {

    @Override
    public String getKey(AisMessage value) {
        return value.getMmsi();
    }
    
}
