
package com.tree.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tree.backend.dao.PhotoDao;
import com.tree.backend.model.Photo;
import com.tree.backend.model.User;
import com.tree.backend.service.PhotoService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @CachePut(value = "tree", key = "#result.photoId")
    public Photo save(Photo photo) {
        return photoDao.save(photo);
    }

    @Override
    public List<Photo> findByUser(User user) {
        return photoDao.findByUser(user);
    }

    @Override
    public Photo findByPhotoId(Long photoId) {
        return photoDao.findByPhotoId(photoId);
    }

    @Override
    public List<Photo> findAll() {
        return photoDao.findAll();
    }

    @Override
    public List<Photo> findByUserId(Long userId) {
        return photoDao.findByUserId(userId);
    }

}
