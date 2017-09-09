
package com.tree.backend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Administrator
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String headImage;

    @CreationTimestamp
    private Date created;

    /**
     * @OneToMany:描述一个一对多的关联,该属性应该为集体类型, 在数据库中并没有实际字段.
     * <p>
     * mappedBy
     * <p>
     * cascade:表示级联操作策略,对于OneToMany类型的关联非常重要,
     * 通常该实体更新或删除时,其关联的实体也应当被更新删除
     * <p>
     * fetch:表示抓取策略,默认为FetchType.LAZY,
     * 因为关联的多个对象通常不必从数据库预先读取到内存
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Photo> photoList;

    @ManyToMany
    private List<Photo> likedPhotoList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public List<Photo> getLikedPhotoList() {
        return likedPhotoList;
    }

    public void setLikedPhotoList(List<Photo> likedPhotoList) {
        this.likedPhotoList = likedPhotoList;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

}
