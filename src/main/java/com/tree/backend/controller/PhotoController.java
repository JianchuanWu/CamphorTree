
package com.tree.backend.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tree.backend.model.Photo;
import com.tree.backend.service.PhotoService;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    static String imageName;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletResponse response,
                         HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(it.next());
        String fileName = multipartFile.getOriginalFilename();
        imageName = fileName;

        String path = new File(ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/static/images")
                .getAbsolutePath() + "/" + fileName;

        try {
            multipartFile.transferTo(new File(path));
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageName;
    }

    private final PhotoService photoService;

    @RequestMapping("/allPhotos")
    public List<Photo> getAllPhotos() {
        return photoService.findAll();
    }

    @RequestMapping(value = "/photoId", method = RequestMethod.POST)
    public Photo getPhotoById(@RequestBody
                                      Long photoId) {
        return photoService.findByPhotoId(photoId);
    }

}
