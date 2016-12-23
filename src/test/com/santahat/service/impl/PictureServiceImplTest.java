package com.santahat.service.impl;

import com.santahat.service.PictureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class PictureServiceImplTest {

    @Resource
    private PictureService pictureService;

    @Test
    public void findFaces() throws Exception {
        pictureService.findFaces("");
    }

    @Test
    public void chooseSantaHat() throws Exception {

    }

}