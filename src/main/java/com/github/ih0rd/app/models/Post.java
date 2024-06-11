package com.github.ih0rd.app.models;

import lombok.Builder;

@Builder
public record Post(int userId, int id, String title, String body) {
}
