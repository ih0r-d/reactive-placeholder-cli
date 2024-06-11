package com.github.ih0rd.app.models;

import lombok.Builder;

@Builder
public record Comment(int postId, int id, String name, String email, String body) {
}
