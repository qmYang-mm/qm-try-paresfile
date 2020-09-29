package com.quanmin.paresfile.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.CharsetUtil.UTF_8;

public class JacksonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Bean 转换为 Json 字符串
     *
     * @param bean bean
     * @return json字符串
     */
    public static String beanToJson(Object bean) {
        try {
            return mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json字符串转换为Bean
     *
     * @param jsonStr   json字符串
     * @param beanClass bean的类型Class
     * @param <T>       bean的类型
     * @return bean对象
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> beanClass) {
        try {
            return mapper.readValue(jsonStr, beanClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析json字符串为JsonNode，如果出现异常，则返回null。
     *
     * @param jsonStr json字符串
     * @return jsonNode
     */
    public static JsonNode parseJson(String jsonStr) {
        try {
            return mapper.readValue(jsonStr, JsonNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从文件解析json字符串为JsonNode，如果出现异常，则返回null。
     *
     * @param jsonFile json字符串
     * @return jsonNode
     */
    public static JsonNode parseJsonFromFile(File jsonFile) {
        try {
            return mapper.readValue(jsonFile, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从流数据解析JSON， 如果出现异常则，返回null
     *
     * @param stream 输入流
     * @return jsonNode
     */
    public static JsonNode parseJsonFromStream(InputStream stream) {
        try {
            return mapper.readValue(stream, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析json字符串为JsonNode，如果出现异常，则抛出异常。
     *
     * @param jsonStr json字符串
     * @return jsonNode
     */
    public static JsonNode parseJsonEx(String jsonStr) throws JsonProcessingException {
        return mapper.readValue(jsonStr, JsonNode.class);
    }

    public static ObjectNode objectNode() {
        return mapper.createObjectNode();
    }


    public static JsonNode parseMaptoJson(Map<String, Object> params) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.valueToTree(params);
    }

    /**
     * 将json转化成List
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> toList(String json, Class<? extends List> collectionClass, Class<T> elementClass)
            throws IOException {
        JavaType javaType = OBJECT_MAPPER.get().getTypeFactory().constructCollectionType(collectionClass, elementClass);
        return OBJECT_MAPPER.get().readValue(json, javaType);
    }

    /**
     * 使用ThreadLocal创建对象，防止出现线程安全问题
     */
    private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER = new ThreadLocal<ObjectMapper>() {
        @Override
        protected ObjectMapper initialValue() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 忽略不存在的字段
            return objectMapper;
        }

    };

    /**
     * @param fileName 输入mock文件下的json文件名
     * @return 输出JsonNode读取文件的对象
     */
    public static JsonNode getJsonByJsonFile(String fileName) {
        JacksonUtils jacksonUtils = new JacksonUtils();
        InputStream inputStream = jacksonUtils.getClass().getClassLoader().getResourceAsStream("mocks/" + fileName);
        String jsonStr = IoUtil.read(inputStream, UTF_8);
        return JacksonUtils.parseJson(jsonStr);
    }

    /**
     * 返回json对象
     * @param status 类型
     * @param message 提示
     * @return jsonObject
     */
    public static JSONObject returnJsonObj(String status, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("message", message);
        return jsonObject;
    }
}