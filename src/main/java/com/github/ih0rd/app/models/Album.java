package com.github.ih0rd.app.models;

import lombok.Builder;

@Builder
public record Album(int userId, int id, String title) {
}
