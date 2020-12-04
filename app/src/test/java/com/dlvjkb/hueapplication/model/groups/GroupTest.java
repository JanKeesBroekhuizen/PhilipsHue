package com.dlvjkb.hueapplication.model.groups;

import com.dlvjkb.hueapplication.model.lightbulbs.LightBulb;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GroupTest {
    private Group groupOk;
    private Group groupException1;
    private Group groupException2;

    @Before
    public void setUp() throws Exception {
        String groupJsonOk =
                "{" +
                        "\"name\":\"Group 1\"," +
                        "\"action\":{" +
                        "   \"xy\":[0.346,0.3568]," +
                        "   \"ct\":201," +
                        "   \"sat\":144," +
                        "   \"effect\":\"none\"," +
                        "   \"bri\":254," +
                        "   \"hue\":33536," +
                        "   \"colormode\":\"xy\"," +
                        "   \"on\":true" +
                        "}," +
                        "\"lights\":[" +
                        "   \"1\"," +
                        "   \"2\"" +
                        "]" +
                "}";
        JSONObject jsonObjectOk = new JSONObject(groupJsonOk);
        groupOk = new Group(1, jsonObjectOk);

        String groupJsonException1 =
                "{" +
                        "\"name\":\"Group 1\"," +
                        "\"action\":{" +
                        "   \"xy\":[Dave, Jan Kees]," +
                        "   \"ct\":201," +
                        "   \"sat\":144," +
                        "   \"effect\":\"none\"," +
                        "   \"bri\":254," +
                        "   \"hue\":33536," +
                        "   \"colormode\":\"xy\"," +
                        "   \"onn\":true" +
                        "}," +
                        "\"lights\":[" +
                        "   \"Dave\"," +
                        "   \"Jan Kees\"" +
                        "]" +
                "}";
        JSONObject jsonObjectException1 = new JSONObject(groupJsonException1);
        groupException1 = new Group(1, jsonObjectException1);

        String groupJsonException2 =
                "{" +
                        "\"name\":\"Group 1\"," +
                        "\"action\":{" +
                        "   \"xy\":[Dave, Jan Kees]," +
                        "   \"ct\":201," +
                        "   \"sat\":144," +
                        "   \"effect\":\"none\"," +
                        "   \"bri\":254," +
                        "   \"hue\":33536," +
                        "   \"colormode\":\"xy\"," +
                        "   \"onn\":true" +
                        "}," +
                        "\"lichten\":[" +
                        "   \"Dave\"," +
                        "   \"Jan Kees\"" +
                        "]" +
                "}";
        JSONObject jsonObjectException2 = new JSONObject(groupJsonException2);
        groupException2 = new Group(1, jsonObjectException2);
    }

    @Test
    public void test_group_off(){
        groupOk.action.on = false;
        assertFalse(groupOk.action.on);
    }

    @Test
    public void test_group_off_exception(){
        assertFalse(groupException1.action.on);
    }

    @Test
    public void test_group_name(){
        assertEquals("Group 1", groupException2.toString());
    }
}