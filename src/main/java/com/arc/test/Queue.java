package com.arc.test;


import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author may
 * @since 2019/6/17 11:47
 */

public class Queue {
    public static final String X_QUEUE_MASTER_LOCATOR = "x-queue-master-locator";
    private final String name;
    private final boolean durable;
    private final boolean exclusive;
    private final boolean autoDelete;
    private final Map<String, Object> arguments;
    private volatile String actualName;

    public Queue(String name) {
        this(name, true, false, false);
    }

    public Queue(String name, boolean durable) {
        this(name, durable, false, false, (Map) null);
    }

    public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete) {
        this(name, durable, exclusive, autoDelete, (Map) null);
    }

    public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) {
        Assert.notNull(name, "'name' cannot be null");
        this.name = name;
        this.actualName = StringUtils.hasText(name) ? name : Base64UrlNamingStrategy.DEFAULT.generateName() + "_awaiting_declaration";
        this.durable = durable;
        this.exclusive = exclusive;
        this.autoDelete = autoDelete;
        this.arguments = (Map) (arguments != null ? arguments : new HashMap());
    }

    public String getName() {
        return this.name;
    }

    public boolean isDurable() {
        return this.durable;
    }

    public boolean isExclusive() {
        return this.exclusive;
    }

    public boolean isAutoDelete() {
        return this.autoDelete;
    }

    public Map<String, Object> getArguments() {
        return this.arguments;
    }

    public void setActualName(String name) {
        this.actualName = name;
    }

    public String getActualName() {
        return this.actualName;
    }

    public final void setMasterLocator(@Nullable String locator) {
        if (locator == null) {
            this.arguments.remove("x-queue-master-locator");
        } else {
            this.arguments.put("x-queue-master-locator", locator);
        }

    }

    public String toString() {
        return "Queue [name=" + this.name + ", durable=" + this.durable + ", autoDelete=" + this.autoDelete + ", exclusive=" + this.exclusive + ", arguments=" + this.arguments + ", actualName=" + this.actualName + "]";
    }
}
