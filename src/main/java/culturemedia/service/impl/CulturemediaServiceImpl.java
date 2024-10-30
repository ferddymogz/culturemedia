package culturemedia.service.impl;

import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.CulturemediaService;

import java.util.ArrayList;
import java.util.List;

public class CulturemediaServiceImpl implements CulturemediaService {

    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;

    public CulturemediaServiceImpl(VideoRepository videoRepository, ViewsRepository viewsRepository) {
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
    public Video save(Video video) throws DurationNotValidException {
        validateVideoDuration(video);
        videoRepository.save(video);
        return video;
    }

    @Override
    public View save(View view) {
        viewsRepository.save(view);
        return view;
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

    private static void validateVideoDuration(Video video) throws DurationNotValidException {
        if(video.duration() <= 0){
            throw new DurationNotValidException(video.title(), video.duration());
        }
    }
}
