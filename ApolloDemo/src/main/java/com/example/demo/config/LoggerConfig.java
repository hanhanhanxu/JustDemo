package com.example.demo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Set;

/**
 * 通过@PostConstruct动态修改log级别。
 * 像logLevel这样的东西，是在项目初始化的时候设置的，所以系统运行期间就不会再加载这些值了，所以如果只是在apollo上面修改值，则不会生效。
 * 必须像这样，监听apollo的修改，然后手动设置到logLevel中，才能让变更生效
 */
@Configuration
public class LoggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggerConfig.class);
    private static final String LOGGER_TAG = "logging.level.";

    @Resource
    private LoggingSystem loggingSystem;

    /**
     * @ApolloConfig：将Apollo服务端的配置注入此对象
     */
    @ApolloConfig
    private Config config;

    /**
     * @ApolloConfigChangeListener：监听配置中心配置的更新事件，若事件发生，则执行方法
     * 执行的方法中把本地的log配置修改为Apollo上的，所以修改Apollo的log配置会影响到本地。达到logLevel在Apollo上面实时更改生效
     * @param changeEvent
     */
    @ApolloConfigChangeListener
    private void configChangeListter(ConfigChangeEvent changeEvent) {
        refreshLoggingLevels();
    }

    /**
     * @PostConstruct：当项目启动时，执行方法。 执行顺序：Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
     * 执行方法时，会把apollo中的配置拉下来，若apollo配置中有log的配置，则把本地的log配置修改为Apollo上面的。
     * 所以项目启动后，系统中的logLevel会是Apollo上的那个值。
     */
    @PostConstruct
    private void refreshLoggingLevels() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            if (StringUtils.containsIgnoreCase(key, LOGGER_TAG)) {
                String strLevel = config.getProperty(key, "info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG, ""), level);
                logger.info("{}:{}", key, strLevel);
            }
        }
    }

}