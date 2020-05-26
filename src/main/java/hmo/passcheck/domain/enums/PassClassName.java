package hmo.passcheck.domain.enums;

import lombok.Getter;

@Getter
public enum PassClassName {
    NCPWR("Nine Chars Password Without Repetition");

    private String description;

    PassClassName(String description) {
        this.description = description;
    }
}
