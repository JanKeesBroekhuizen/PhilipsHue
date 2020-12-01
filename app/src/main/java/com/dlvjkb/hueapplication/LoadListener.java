package com.dlvjkb.hueapplication;

import com.dlvjkb.hueapplication.model.groups.Group;
import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;

public interface LoadListener {
    void onLightBulbAvailable(LightBulb lightBulb);
    void onGroupAvailable(Group group);
}
