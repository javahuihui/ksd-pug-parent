package com.pug.redis.config;

import com.pug.redis.aop.limiter.PugRateLimiterAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/5/20 13:16
 */
@Configuration
public class PugRedisConfiguration {

    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    @Primary
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String, java.lang.Object>
     * @Author 徐柯
     * @Description 改写redistemplate序列化规则
     * @Date 13:20 2021/5/20
     * @Param [redisConnectionFactory]
     **/
    @Bean
    @Deprecated
    public RedisTemplate<String, Object> redisTemplate2(RedisConnectionFactory redisConnectionFactory) {
        // 1: 开始创建一个redistemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 2:开始redis连接工厂跪安了
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 创建一个json的序列化方式
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // 设置key用string序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置value用jackjson进行处理
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash也要进行修改
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 默认调用
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    public PugRedisCacheTemplate redisCacheTemplate() {
        return new PugRedisCacheTemplate();
    }

    @Bean
    public PugRateLimiterAspect limiterAspect() {
        return new PugRateLimiterAspect();
    }

    /**
     * 将lua脚本的内容加载出来放入到DefaultRedisScript
     *
     * @return
     */
    @Bean
    public DefaultRedisScript<Boolean> ipLimitLua() {
        DefaultRedisScript<Boolean> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/iplimiter.lua")));
        defaultRedisScript.setResultType(Boolean.class);
        return defaultRedisScript;
    }

    /**
     * 将lua脚本的内容加载出来放入到DefaultRedisScript
     *
     * @return
     */
    @Bean
    public DefaultRedisScript<Boolean> ipLimiterLuaScript() {
        DefaultRedisScript<Boolean> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/iplimiter2.lua")));
        defaultRedisScript.setResultType(Boolean.class);
        return defaultRedisScript;
    }


    @Bean
    public DefaultRedisScript<Long> limitScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(limitScriptText());
        redisScript.setResultType(Long.class);
        return redisScript;
    }

    /**
     * 限流脚本
     */
    private String limitScriptText() {
        return "local key = KEYS[1]\n" +
                "local count = tonumber(ARGV[1])\n" +
                "local time = tonumber(ARGV[2])\n" +
                "local current = redis.call('get', key);\n" +
                "if current and tonumber(current) > count then\n" +
                "    return tonumber(current);\n" +
                "end\n" +
                "current = redis.call('incr', key)\n" +
                "if tonumber(current) == 1 then\n" +
                "    redis.call('expire', key, time)\n" +
                "end\n" +
                "return tonumber(current);";
    }

}