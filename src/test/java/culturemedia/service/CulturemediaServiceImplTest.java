package culturemedia.service;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.repository.impl.ViewsRepositoryImpl;
import culturemedia.service.impl.CulturemediaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CulturemediaServiceImplTest {

    private CulturemediaService culturemediaService;

    @BeforeEach
    void init() {
        culturemediaService = new CulturemediaServiceImpl(new VideoRepositoryImpl(), new ViewsRepositoryImpl());
    }

    void saveVideos() {

        List<Video> videos = List.of(new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4),
                new Video("04", "Título 4", "----", 3.5),
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1));


        for (Video video : videos) {
            culturemediaService.save(video);
        }

    }


    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        saveVideos();
        List<Video> videos = culturemediaService.findAll();
        assertEquals(6, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        VideoNotFoundException videoNotFoundException = assertThrows(VideoNotFoundException.class, () -> culturemediaService.findAll());
        assertEquals("Video not found", videoNotFoundException.getMessage());
    }

    @Test
    void when_FindByTitle_only_videos_which_contains_the_word_in_the_title_should_be_returned_successfully() throws VideoNotFoundException {
        saveVideos();
        List<Video> videos = culturemediaService.find( "Clic" );
        assertEquals(2, videos.size());
    }

    @Test
    void when_FindByTitle_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        saveVideos();
        VideoNotFoundException videoNotFoundException = assertThrows(VideoNotFoundException.class, () -> culturemediaService.find("Gladiator"));
        assertEquals("Video not found", videoNotFoundException.getMessage());
    }

    @Test
    void when_FindByDuration_only_videos_between_the_range_should_be_returned_successfully() throws VideoNotFoundException {
        saveVideos();
        List<Video> videos = culturemediaService.find( 4.5, 5.5 );
        assertEquals(3, videos.size());
    }

    @Test
    void when_FindByDuration_does_not_match_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        saveVideos();
        VideoNotFoundException videoNotFoundException = assertThrows(VideoNotFoundException.class, () -> culturemediaService.find(6.5, 7.5));
        assertEquals("Video not found", videoNotFoundException.getMessage());
    }

}
