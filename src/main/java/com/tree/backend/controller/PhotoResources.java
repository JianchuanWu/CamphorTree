
package com.tree.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tree.backend.model.Photo;
import com.tree.backend.model.User;
import com.tree.backend.service.PhotoService;

@RestController
@RequestMapping("/rest")
public class PhotoResources {

    private final PhotoService photoService;

    @Autowired
    public PhotoResources(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping(value = "/photo/add", method = RequestMethod.POST)
    public Photo addPhoto(@RequestBody
                                  Photo photo) {
        photo.setImageName(PhotoController.imageName);
        photo.setDeleted(0);
        return photoService.save(photo);
    }

    @RequestMapping(value = "/photo/user", method = RequestMethod.POST)
    public List<Photo> getPhotoByUser(@RequestBody
                                              User user) {
        //return photoService.findByUser(user);
        return photoService.findByUserId(user.getUserId());
    }

    @RequestMapping(value = "/photo/update", method = RequestMethod.POST)
    public void updatePhoto(@RequestBody
                                    Photo photo) {
        Photo currentPhoto = photoService.findByPhotoId(photo.getPhotoId());
        currentPhoto.setLikes(photo.getLikes());
        photoService.save(currentPhoto);
    }

    @RequestMapping(value = "/photo/delete", method = RequestMethod.POST)
    public void deletePhoto(@RequestBody
                                    Long photoId) {
        Photo currentPhoto = photoService.findByPhotoId(photoId);
        currentPhoto.setDeleted(1);
        photoService.save(currentPhoto);
    }

}
