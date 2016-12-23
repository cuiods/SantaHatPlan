package com.santahat.service;

import com.santahat.bean.Face;
import com.santahat.bean.Hat;

import java.util.List;

/**
 * operation on pictures
 */
public interface PictureService {
    /**
     * Find all the faces in the picture
     * @param imageUrl image url
     * @return all the {@link Face}s
     */
    List<Face> findFaces(String imageUrl);

    /**
     * choose hats for faces
     * @param face face list
     * @return hat list
     */
    List<Hat> chooseSantaHat(List<Face> face);

    /**
     * choose a hat for the face
     * @param face {@link Face} object
     * @return {@link Hat} instance
     */
    Hat chooseSantaHat(Face face);
}