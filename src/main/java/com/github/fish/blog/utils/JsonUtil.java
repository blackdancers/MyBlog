package com.github.fish.blog.utils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.fish.blog.exceptions.BaseBizException;
import com.github.fish.blog.enums.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * JSON的工具类
 *
 */
public final class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper = new ObjectMapper();
    /**
     * 格式化时间的string
     */
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        //设置时间格式
        mapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
        // 属性为空（“”）或者为 NULL 都不序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

    }

    private JsonUtil() {

    }

    /**
     * 返回{@link ObjectMapper ObjectMapper}对象, 用于定制性的操作
     *
     * @return {@link ObjectMapper ObjectMapper}对象
     */
    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    /**
     * 将json通过类型转换成对象
     * <p>
     * <pre>
     *     {@link JsonUtil JsonUtil}.json2Obj("{\"username\":\"username\", \"password\":\"password\"}", User.class);
     * </pre>
     *
     * @param json  json字符串
     * @param clazz 泛型类型
     */

    public static <T> T json2Obj(String json, Class<T> clazz) {
        try {
            return clazz.equals(String.class) ? (T) json : mapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error("json转对象出错==>>{},json = {} ", e.getMessage(), json);
            throw new BaseBizException(-1, "json转换为obj错误.", Module.BASE);
        }
    }

    /**
     * 将对象转换成json
     * 支持Bean、Map、Collection
     * <p>
     * <pre>
     *     {@link JsonUtil JsonUtil}.obj2Json(user);
     * </pre>
     *
     * @param src 对象
     * @return 返回json字符串
     * @throws BaseBizException
     */
    public static <T> String obj2Json(T src) {
        ObjectMapper objectMapper = getObjectMapper();
        try {
            //过滤null 输出""
            objectMapper.getSerializerProvider().setNullValueSerializer(
                new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object value, JsonGenerator jg,SerializerProvider sp) throws IOException {
                        jg.writeString("");
                    }
                });

            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (IOException e) {
            logger.debug("转换数据错误,{}",e.getMessage());
            throw new BaseBizException(-1, "转换为json错误.", Module.BASE);
        }
    }

    /**
     * 将json转换为List<map>
     *
     * @param json
     * @return
     * @throws Exception
     * @date 2013-11-1 上午11:15:43
     */
    public static List json2List(String json) {
        try {
            return mapper.readValue(json, List.class);
        } catch (IOException e) {
            logger.debug("json转换为List错误{}",e.getMessage());
            throw new BaseBizException(-1, "json转换为List错误.", Module.BASE);
        }
    }

    /**
     * 将json通过类型转换成对象
     * <p>
     * <pre>
     *     {@link JsonUtil JsonUtil}.fromJson("[{\"username\":\"username\", \"password\":\"password\"}, {\"username\":\"username\", \"password\":\"password\"}]", new TypeReference&lt;List&lt;User&gt;&gt;);
     * </pre>
     *
     * @param json          json字符串
     * @param typeReference 引用类型
     * @return 返回对象
     * @throws Exception
     */
    public static <T> T fromJson(String json, TypeReference<?> typeReference) {
        try {
            return (T) (typeReference.getType().equals(String.class) ? json : mapper.readValue(json, typeReference));
        } catch (IOException e) {
            logger.debug("将json通过类型转换成对象失败,{}",e.getMessage());
            throw new BaseBizException(-1, "将json通过类型转换成对象失败.", Module.BASE);
        }
    }


    /**
     * 将对象转换成json 可以设置时间显示格式
     *
     * @param <T>
     * @param src
     * @param dateTimeFormatString 自定义的日期/时间格式。该属性的值遵循java标准的date/time格式规范。如：yyyy-MM-dd
     * @return
     * @throws BaseBizException
     * @date 2013-11-1 下午02:02:58
     */
    public static <T> String obj2Json(T src, String dateTimeFormatString) {
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat((dateTimeFormatString)));
        try {
            return objectMapper.writeValueAsString(src);
        } catch (IOException e) {
            logger.debug("{}按时间格式转换为json错误,{}",src,e.getMessage());
            throw new BaseBizException(-1, "按" + dateTimeFormatString + "时间格式转换为json错误", Module.BASE);
        }
    }

    /**
     * 将对象转换成json, 传入配置对象
     * <p>
     * <pre>
     *     {@link ObjectMapper ObjectMapper} mapper = new ObjectMapper();
     *     mapper.configure({@link Feature Feature.FAIL_ON_UNKNOWN_PROPERTIES}, false);
     *     mapper.configure({@link Feature Feature.FAIL_ON_NUMBERS_FOR_ENUMS}, true);
     *     mapper.setDateFormat(new {@link SimpleDateFormat SimpleDateFormat}("yyyy-MM-dd HH:mm:ss"));
     *     {@link JsonUtil JsonUtil}.toJson(user, mapper);
     * </pre>
     * <p>
     * {@link ObjectMapper ObjectMapper}
     *
     * @param src    对象
     * @param mapper 配置对象
     * @return 返回json字符串
     * @throws BaseBizException
     * @see ObjectMapper
     */
    public static <T> String obj2Json(T src, ObjectMapper mapper) {
        if (null == mapper){
            return null;
        }

        if (src instanceof String) {
            return (String) src;
        } else {
            try {
                return mapper.writeValueAsString(src);
            } catch (IOException e) {
                logger.debug("将对象{}转换成json错误,{}",src,e.getMessage());
                throw new BaseBizException(-1, "将对象转换成json错误", Module.BASE);
            }
        }

    }

    /**
     * 将对象转换成json,同时忽略部分属性
     *
     * @param <T>
     * @param src           需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
     * @param propertyArray 要忽视的字段名称
     * @return
     * @throws Exception
     * @date 2013-11-18 下午02:54:53
     */
    public static <T> String obj2JsonIgnoreProperties(T src, String... propertyArray) throws Exception {
        ObjectMapper objectMapper = getObjectMapper();
        String id = AnnotationUtils.getValue(AnnotationUtils.findAnnotation(src.getClass(), JsonFilter.class)).toString();
        SimpleFilterProvider filter = new SimpleFilterProvider();
        filter.addFilter(id, SimpleBeanPropertyFilter.serializeAllExcept(propertyArray));
        objectMapper.setFilterProvider(filter);
        return obj2Json(src, objectMapper);
    }

    /**
     * 将对象转换成json,仅保留部分属性
     *
     * @param <T>
     * @param src           需要转换的对象(注意，需要在要转换的对象中定义JsonFilter注解)
     * @param propertyArray 要保留的属性
     * @return
     * @throws Exception
     * @date 2013-11-18 下午03:05:01
     */
    public static <T> String obj2JsonIncludeProperties(T src, String... propertyArray) throws Exception {
        ObjectMapper objectMapper = getObjectMapper();
        String id = AnnotationUtils.getValue(AnnotationUtils.findAnnotation(src.getClass(), JsonFilter.class)).toString();
        SimpleFilterProvider filter = new SimpleFilterProvider();
        filter.addFilter(id, SimpleBeanPropertyFilter.filterOutAllExcept(propertyArray));
        objectMapper.setFilterProvider(filter);
        //String json = obj2Json(src, objectMapper);
        return objectMapper.writeValueAsString(src);
    }


}