package culturemedia.controllers;

import java.util.List;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.impl.CulturemediaServiceImpl;

public class CultureMediaController {

    private final CulturemediaServiceImpl cultureMediaService;


    public CultureMediaController(CulturemediaServiceImpl cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }


    public List<Video> findAllVideos() throws VideoNotFoundException {
        List<Video> videos = cultureMediaService.findAll();
        return videos;
    }


}