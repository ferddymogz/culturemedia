package culturemedia.exception;

public class DurationNotValidException extends CultureMediaException {
    public DurationNotValidException(String title, Double duration) {
        super("Video with title: " + title + ", duration: " + duration + " not found");
    }
}
