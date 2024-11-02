package culturemedia.service;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.impl.CultureMediaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CulturemediaServiceTest {

    private CultureMediaService cultureMediaService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ViewsRepository viewsRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        doReturn(List.of(new Video("01", "Título 1", "----", 4.5),
                        new Video("02", "Título 2", "----", 5.5),
                        new Video("03", "Título 3", "----", 4.4),
                        new Video("04", "Título 4", "----", 3.5),
                        new Video("05", "Clic 5", "----", 5.7),
                        new Video("06", "Clic 6", "----", 5.1)))
                .when(videoRepository)
                .findAll();
        List<Video> videos = cultureMediaService.findAll();
        assertEquals(6, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        doReturn(List.of())
                .when(videoRepository)
                .findAll();
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.findAll());
    }

    @Test
    void when_FindByTitle_only_videos_which_contains_the_word_in_the_title_should_be_returned_successfully() throws VideoNotFoundException {
        doReturn(List.of(new Video("05", "Clic 5", "----", 5.7),
                        new Video("06", "Clic 6", "----", 5.1)))
                .when(videoRepository)
                .find("Clic");
        List<Video> videos = cultureMediaService.find("Clic");
        assertTrue(videos.stream().allMatch(video -> video.title().contains("Clic")));
    }

    @Test
    void when_FindByTitle_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        doReturn(List.of())
                .when(videoRepository)
                .find("Gladiator");
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.find("Gladiator"));
    }

    @Test
    void when_FindByDuration_only_videos_between_the_range_should_be_returned_successfully() throws VideoNotFoundException {
        doReturn(List.of(new Video("05", "Clic 5", "----", 5.7),
                        new Video("06", "Clic 6", "----", 5.1)))
                .when(videoRepository)
                .find(5.0, 6.0);
        List<Video> videos = cultureMediaService.find(5.0, 6.0);
        assertTrue(videos.stream().allMatch(video -> video.duration() >= 5.0 && video.duration() <= 6.0));
    }

    @Test
    void when_FindByDuration_does_not_match_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        doReturn(List.of())
                .when(videoRepository)
                .find(10.0, 15.0);
        assertThrows(VideoNotFoundException.class, () -> cultureMediaService.find(10.0, 15.0));
    }

    @Test
    void when_save_video_with_valid_duration(){
        doReturn(new Video("07", "Título 7", "----", 6.3))
                .when(videoRepository)
                .save(new Video("07", "Título 7", "----", 6.3));
        Video video = new Video("07", "Título 7", "----", 6.3);
        assertEquals(video, videoRepository.save(video));
    }

    @Test
    void when_save_video_with_not_valid_duration(){
        doReturn(null)
                .when(videoRepository)
                .save(new Video("07", "Título 7", "----", -6.3));
        Video video = new Video("07", "Título 7", "----", -6.3);
        assertThrows(DurationNotValidException.class, () -> cultureMediaService.save(video));
    }

    @Test
    void when_save_view(){
        doReturn(new View("Pepito", LocalDate.now(), 29, new Video("08", "Título 8", "----", 3.5)))
                .when(viewsRepository)
                .save(new View("Pepito", LocalDate.now(), 29, new Video("08", "Título 8", "----", 3.5)));
        View view = new View("Pepito", LocalDate.now(), 29, new Video("08", "Título 8", "----", 3.5));
        assertEquals(view, viewsRepository.save(view));
    }


}
