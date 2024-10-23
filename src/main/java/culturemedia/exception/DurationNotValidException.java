package culturemedia.exception;

public class DurationNotValidException extends CultureMediaException {
    public DurationNotValidException(String title, Double duration) {
        super("Video with title: ".concat(title).concat(", duration: ").concat(String.valueOf(duration)).concat(" not found"));
    }

}
