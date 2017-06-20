package org.game;

import org.map.objects.Criminal;

/**
 * Created by Krulvis on 20-Jun-17.
 */
public class CriminalTurn extends Turn<Criminal> {

    public CriminalTurn(Criminal entity, boolean isBot) {
        super(entity, isBot);
    }

    @Override
    public boolean perform() {
        return false;
    }
}
