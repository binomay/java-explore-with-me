package ru.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatData {
    String app;
    String uri;
    Long hits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatData statData = (StatData) o;

        if (!getApp().equals(statData.getApp())) return false;
        return getUri().equals(statData.getUri());
    }

    @Override
    public int hashCode() {
        int result = getApp().hashCode();
        result = 31 * result + getUri().hashCode();
        return result;
    }
}
