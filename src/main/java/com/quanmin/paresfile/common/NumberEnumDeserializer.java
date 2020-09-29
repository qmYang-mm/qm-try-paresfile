package com.quanmin.paresfile.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class NumberEnumDeserializer extends StdScalarDeserializer<INumberEnum>
        implements ContextualDeserializer {

    private Enum<?>[] enumConstants = null;

    public NumberEnumDeserializer() {
        super(INumberEnum.class);
    }

    @Override
    public INumberEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        int enumCode = p.getValueAsInt();
        for (Enum<?> enumx : enumConstants) {
            Integer value = ((INumberEnum) enumx).getValue();
            if (value == enumCode) {
                return (INumberEnum) enumx;
            }
        }
        throw new RuntimeException("Invalid enum code: " + enumCode);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        Class<Enum<?>> enumCls = (Class<Enum<?>>) property.getType().getRawClass();
        enumConstants = enumCls.getEnumConstants();

        return this;
    }
}
