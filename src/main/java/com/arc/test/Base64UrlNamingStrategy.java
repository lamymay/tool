package com.arc.test;
/**
 * @author may
 * @since 2019/6/17 11:48
 */

import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public class Base64UrlNamingStrategy {
    private static final int SIXTEEN = 16;
    public static final Base64UrlNamingStrategy DEFAULT = new Base64UrlNamingStrategy();
    private final String prefix;

    public Base64UrlNamingStrategy() {
        this("spring.gen-");
    }

    public Base64UrlNamingStrategy(String prefix) {
        Assert.notNull(prefix, "'prefix' cannot be null; use an empty String ");
        this.prefix = prefix;
    }

    public String generateName() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits());
        return this.prefix + Base64Utils.encodeToUrlSafeString(bb.array()).replaceAll("=", "");
    }
}
