package com.amarszalek.blog_server.domain.utils;

import java.time.LocalDateTime;

public interface DateProvider {
    LocalDateTime getCurrentDateTime();
}
