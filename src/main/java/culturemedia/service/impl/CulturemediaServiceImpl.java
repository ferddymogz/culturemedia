package culturemedia.service.impl;

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
    public Video save(Video video) {
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
        List<Video> filteredVideos = new ArrayList<>();
        for ( Video video : videoRepository.findAll() ) {
            if(video.title().contains(title)){
                if(filteredVideos.isEmpty()){
                    filteredVideos = new ArrayList<Video>();
                }
                filteredVideos.add(video);
            }
        }

        if (filteredVideos.isEmpty()) {
            throw new VideoNotFoundException();
        }else {
            return filteredVideos;
        }
    }

    @Override
    public List<Video> find(Double fromDuration, Double toDuration) throws VideoNotFoundException {
        List<Video> filteredVideos = new ArrayList<Video>();
        for ( Video video : videoRepository.findAll() ) {
            if(video.duration() >= fromDuration && video.duration() <= toDuration){
                filteredVideos.add(video);
            }
        }

        if(filteredVideos.isEmpty()){
            throw new VideoNotFoundException();
        }else {
            return filteredVideos;
        }
    }
}
