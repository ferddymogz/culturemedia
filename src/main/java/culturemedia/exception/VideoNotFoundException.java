package culturemedia.exception;

public class VideoNotFoundException extends Exception {

    public VideoNotFoundException() {
        super("Video not found");
    }

    public VideoNotFoundException(String title) {
        super("Video with title ".concat(title).concat(" not found"));
    }



}
