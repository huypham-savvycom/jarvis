package com.jarvis.android.utils;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jarvis.android.model.UnixTime;

import java.lang.reflect.Type;

/**
 * Deserializer to convert String time to Long
 * <p/>
 */
public class UnixTimeDeserializer implements JsonDeserializer<UnixTime> {

    @Override
    public UnixTime deserialize(JsonElement json, Type arg1,
                                JsonDeserializationContext arg2) throws JsonParseException {
        String timeString = json.getAsString();
        return new UnixTime(DateUtils.parseDateTimeString(timeString));
    }

}
