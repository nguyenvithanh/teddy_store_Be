package com.backend.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

@UtilityClass
public class RandomUtil {
	public String getNextId(String id, String prefix) {
        if (id == null || id.isEmpty()) {
            return prefix + String.format("%08d", 1);
        }

        var idStr = id.substring(2);
        var idNum = Integer.parseInt(idStr);
        idNum++;
        return prefix + String.format("%08d", idNum);
    }

    public String generateRandomString(int length) {
        return StringUtils
                .replace(java.util.UUID
                                 .randomUUID()
                                 .toString(), "-", "")
                .substring(0, length);
    }
}
