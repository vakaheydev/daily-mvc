package com.vaka.daily_mvc.controller.admin;

import org.springframework.ui.Model;

public interface AdminController<T> {
    String get(Model model);

    String getById(Integer id, Model model);

    String put(Integer id, T entity);

    String post(T entity);

    String delete(Integer id);
}
