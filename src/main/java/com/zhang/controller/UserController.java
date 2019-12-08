package com.zhang.controller;

import com.google.code.kaptcha.Producer;
import com.zhang.entity.User;
import com.zhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(User user) throws UnsupportedEncodingException {
        try {
            userService.login(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return URLEncoder.encode(e.getMessage(), "utf-8");
        }
    }


    @Autowired
    private Producer producer;

    @RequestMapping("code")
    public void createCode(HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String text = producer.createText();
        System.out.println(text);
        BufferedImage image = producer.createImage(text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
    }
}
