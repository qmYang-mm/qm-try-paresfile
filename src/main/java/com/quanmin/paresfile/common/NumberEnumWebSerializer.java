package com.quanmin.paresfile.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NumberEnumWebSerializer extends JsonSerializer<INumberEnum> {
    @Override
    public void serialize(INumberEnum value, JsonGenerator jsonGenerator, SerializerProvider provider)
            throws IOException {
        if (value == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeObject(value.getValue());
            jsonGenerator.writeFieldName(jsonGenerator.getOutputContext().getCurrentName() + "Name");
            jsonGenerator.writeString(value.getDescription());
        }
    }

    @Override
    public Class<INumberEnum> handledType() {
        return INumberEnum.class;
    }
}
