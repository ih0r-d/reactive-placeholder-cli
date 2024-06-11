package com.github.ih0rd.app.models;

import lombok.Builder;

@Builder
public record ToDo(int userId, int id, String title, boolean completed) {
}
