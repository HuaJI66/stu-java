package com.pika.validation;

import com.pika.validation.annotation.IPv4;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author pikachu
 * @since 2023/5/5 21:18
 */
@Slf4j
public class IPv4ConstraintValidator implements ConstraintValidator<IPv4, String> {
    private final String IPV4_REGEX = "^([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$";
    private Set<String> allows;

    @Override
    public void initialize(IPv4 constraintAnnotation) {
        if (Arrays.stream(constraintAnnotation.allows()).anyMatch(s -> !s.matches(IPV4_REGEX))) {
            log.warn("允许的ip设置错误");
        }
        allows = new HashSet<>(List.of(constraintAnnotation.allows()));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) {
            return false;
        }
        if (allows.size() > 0 && allows.contains(value)) {
            return true;
        } else if (value.matches(IPV4_REGEX)) {
            return true;
        }
        return false;
    }
}
