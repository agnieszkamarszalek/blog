package com.amarszalek.blog_server.domain.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateProviderImpl implements DateProvider {

    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDate.now().atTime(LocalTime.now());
    }
}
