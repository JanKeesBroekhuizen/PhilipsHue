package com.dlvjkb.hueapplication.model.lightbulbs;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class LightBulbTest {
    private LightBulb lightBulbOk;
    private LightBulb lightBulbException;

    @Before
    public void setUp() throws JSONException {
        String lightJsonOk =
                "{" +
                "   \"modelid\":\"LCT001\"," +
                "   \"name\":\"Hue Lamp 1\"," +
                "   \"swversion\":\"65003148\"," +
                "   \"state\":{" +
                "       \"xy\":[0,0]," +
                "       \"ct\":0," +
                "       \"alert\":\"none\"," +
                "       \"sat\":254," +
                "       \"effect\":\"none\"," +
                "       \"bri\":254," +
                "       \"hue\":4444," +
                "       \"colormode\":\"hs\"," +
                "       \"reachable\":true," +
                "       \"on\":true" +
                "   }," +
                "   \"type\":\"Extended color light\"," +
                "   \"pointsymbol\":{" +
                "       \"1\":\"none\"," +
                "       \"2\":\"none\"," +
                "       \"3\":\"none\"," +
                "       \"4\":\"none\"," +
                "       \"5\":\"none\"," +
                "       \"6\":\"none\"," +
                "       \"7\":\"none\"," +
                "       \"8\":\"none" +
                "   \"}," +
                "   \"uniqueid\":\"00:17:88:01:00:d4:12:08-0a" +
                "\"}";
        JSONObject jsonObjectOk = new JSONObject(lightJsonOk);
        lightBulbOk = new LightBulb(1, jsonObjectOk);

        String lightJsonException =
                "{" +
                        "   \"modelid\":\"LCT001\"," +
                        "   \"name\":\"Hue Lamp 1\"," +
                        "   \"swversion\":\"65003148\"," +
                        "   \"state\":{" +
                        "       \"xy\":[Jan Kees,Dave]," +
                        "       \"ct\":0," +
                        "       \"alert\":\"none\"," +
                        "       \"sat\":254," +
                        "       \"effect\":\"none\"," +
                        "       \"bri\":254," +
                        "       \"hue\":4444," +
                        "       \"colormode\":\"hs\"," +
                        "       \"reachable\":true," +
                        "       \"onn\":true" +
                        "   }," +
                        "   \"type\":\"Extended color light\"," +
                        "   \"pointsymbol\":{" +
                        "       \"1\":\"none\"," +
                        "       \"2\":\"none\"," +
                        "       \"3\":\"none\"," +
                        "       \"4\":\"none\"," +
                        "       \"5\":\"none\"," +
                        "       \"6\":\"none\"," +
                        "       \"7\":\"none\"," +
                        "       \"8\":\"none" +
                        "   \"}," +
                        "   \"uniueid\":\"00:17:88:01:00:d4:12:08-0a" +
                        "\"}";
        JSONObject jsonObjectException = new JSONObject(lightJsonException);
        lightBulbException = new LightBulb(1, jsonObjectException);
    }

    @Test
    public void test_lightbulb_off(){
        lightBulbOk.state.setOn(false);
        assertFalse(lightBulbOk.state.on);
    }

    @Test
    public void test_lightbulb_off_exception(){
        assertFalse(lightBulbException.state.on);
    }
}