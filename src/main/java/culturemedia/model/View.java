package culturemedia.model;

import java.time.LocalDate;

public record View(String userFullName, LocalDate startPlayingTime, Integer age, Video video) {
}
