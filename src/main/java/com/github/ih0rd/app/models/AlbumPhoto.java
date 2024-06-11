package com.github.ih0rd.app.models;

import lombok.Builder;

@Builder
public record AlbumPhoto(int userId, int id, String title, String url, String thumbnailUrl) {
}
