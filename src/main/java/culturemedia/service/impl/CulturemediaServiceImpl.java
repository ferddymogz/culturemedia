package culturemedia.service.impl;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;

import java.util.List;

public class CulturemediaServiceImpl implements CulturemediaService{

    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;

    @Override
    public List<Video> findAll() {
        return List.of();
    }

    @Override
    public Video save(Video video) {
        return null;
    }

    @Override
    public View save(View view) {
        return null;
    }
}
