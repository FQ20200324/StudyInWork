package com.fq.config.redis;

import com.fq.constant.Const;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 如果需要更高级的可重入 推荐使用 redission
 */
@Slf4j
@Component
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class RedisLock {

    private StringRedisTemplate redisTemplate;


    public void lock(String id) {
        // 防止误删
        String uuid    = UUID.randomUUID().toString();
        String lockKey = Const.redisLockKey + id;
        // lua脚本
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        // 加锁 true/false
        Boolean getLock = redisTemplate.opsForValue().setIfAbsent(lockKey, uuid, Duration.ofSeconds(30));
        log.info("用户: {} 加锁状态: {} uuid: {}", id, getLock, uuid);
        if (Boolean.TRUE.equals(getLock)) {
            // 加锁成功
            try {
                // TODO 业务逻辑
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (Exception e) {
                log.warn("业务异常: {}", e.getMessage(), e);
            } finally {
                // 解锁
                Boolean execute = redisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), List.of(lockKey), uuid);
                log.info("解锁状态: {}", execute);
            }
        } else {
            // 自旋
            try {
                log.info("自旋中");
                TimeUnit.MILLISECONDS.sleep(2000);
                lock(id);
            } catch (Exception e) {
                log.error("自旋异常: {}", e.getMessage(), e);
                lock(id);
            }
        }
    }
}
