package acntech.domain;

import javax.validation.constraints.Pattern;

public class Bar {

    @Pattern(regexp = "[a-c]{3}")
    private String value;

    public Bar() {
    }

    public Bar(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
