package culturemedia.service.impl;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.CultureMediaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultureMediaServiceImpl implements CultureMediaService {

    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;

    public CultureMediaServiceImpl(VideoRepository videoRepository, ViewsRepository viewsRepository) {
        this.videoRepository = videoRepository;
        this.viewsRepository = viewsRepository;
    }

    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        List<Video> videos = videoRepository.findAll();
        if (videos.isEmpty()) {
            throw new VideoNotFoundException();
        }else {
            return videos;
        }
    }

    @Override
    public List<Video> find(String title) throws VideoNotFoundException {
        List<Video> videos = videoRepository.find(title);

        if (videos.isEmpty()) {
            throw new VideoNotFoundException();
        }else {
            return videos;
        }
    }

    @Override
    public List<Video> find(Double fromDuration, Double toDuration) throws VideoNotFoundException {
        List<Video> videos = videoRepository.find(fromDuration, toDuration);

        if(videos.isEmpty()){
            throw new VideoNotFoundException();
        }else {
            return videos;
        }
    }

    @Override
    public Video save(Video video) throws DurationNotValidException {
        validateVideoDuration(video);
        return this.videoRepository.save(video);
    }

    @Override
    public View save(View view) {
        return this.viewsRepository.save(view);
    }

    private static void validateVideoDuration(Video video) throws DurationNotValidException {
        if(video.duration() <= 0){
            throw new DurationNotValidException(video.title(), video.duration());
        }
    }
}
