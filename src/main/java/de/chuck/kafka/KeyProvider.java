package de.chuck.kafka;

import de.chuck.ais.AisMessage;

public interface KeyProvider {
    String getKey(AisMessage value);
}
