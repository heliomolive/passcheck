package hmo.passcheck.domain.enums;

import lombok.Getter;

@Getter
public enum RuleName {
    MIN_PASS_SIZE("Minimum password size"),
    MAX_PASS_SIZE("Maximum password size"),
    MIN_DIGIT_OCCURRENCES("Minimum number of digits"),
    MIN_LOWERCASE_CHARS("Minimum number of lowercase characters"),
    MIN_UPPERCASE_CHARS("Minimum number of uppercase characters"),
    MIN_SPECIAL_CHARS("Minimum number of special characters"),
    NO_REPEATED_CHARS("Repeated characters not allowed");

    private String description;

    RuleName(String description) {
        this.description = description;
    }
}
