package com.vaka.daily.controller;

import org.springframework.ui.Model;

public interface CommonController {
    String get(Model model);

    String getById(Integer id, Model model);
}
