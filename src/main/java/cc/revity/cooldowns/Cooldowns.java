package cc.revity.cooldowns;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Cooldowns {

    private final static Map<UUID, Map<String, Long>> cooldownsMap = new HashMap<>();

    /**
     * @param uuid is the {@link UUID} you are putting on cooldown
     * @param name is the {@link String} ID of the cooldown
     * @param cooldown is the {@link Long} duration of the cooldown
     */
    public static void addCooldown(UUID uuid, String name, long cooldown) {
        Map<String, Long> cooldowns = cooldownsMap.getOrDefault(uuid, new HashMap<>());
        cooldowns.put(name, System.currentTimeMillis() + cooldown);
        cooldownsMap.put(uuid, cooldowns);
    }

    /**
     * @param uuid is the {@link UUID} you are putting on cooldown
     * @param name is the {@link String} ID of the cooldown
     * @param cooldown is the {@link Long} duration of the cooldown
     * @param timeUnit is the {@link TimeUnit} to apply to the cooldown
     */
    public static void addCooldown(UUID uuid, String name, long cooldown, TimeUnit timeUnit) {
        addCooldown(uuid, name, timeUnit.toMillis(cooldown));
    }

    /**
     * @param uuid is the {@link UUID} you are removing a cooldown from
     * @param name is the {@link String} ID of the cooldown you are removing
     */
    public static void removeCooldown(UUID uuid, String name) {
        Map<String, Long> cooldowns = cooldownsMap.get(uuid);
        if (cooldowns == null) return;
        if (cooldowns.size() > 0) {
            cooldowns.remove(name);
            cooldownsMap.put(uuid, cooldowns);
            return;
        }
        cooldownsMap.remove(uuid);
    }

    /**
     * @param uuid is the {@link UUID} you are obtaining a cooldown from
     * @param name is the {@link String} ID of the cooldown you are obtaining
     */
    public static long getCooldown(UUID uuid, String name) {
        Map<String, Long> cooldowns = cooldownsMap.get(uuid);
        if (cooldowns == null) {
            return 0L;
        }
        long cooldown = cooldowns.getOrDefault(name, 0L);
        long remaining = cooldown > 0L ? cooldown - System.currentTimeMillis() : 0L;
        if (remaining >= 0L) {
            return remaining;
        }
        cooldowns.remove(name);
        cooldownsMap.put(uuid, cooldowns);
        return 0L;
    }

    /**
     * @param uuid is the {@link UUID} you are obtaining a status of
     * @param name is the {@link String} ID of the cooldown you are obtaining status of
     */
    public static boolean isOnCooldown(UUID uuid, String name) {
        return getCooldown(uuid, name) > 0L;
    }
}
