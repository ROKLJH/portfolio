package com.KoreaItAcdemy.OurNotion.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
@Slf4j
public class IndexController {

    @GetMapping("index")
    public void index() {

    }
}