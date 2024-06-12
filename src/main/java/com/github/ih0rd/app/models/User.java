package com.github.ih0rd.app.models;

import lombok.Builder;

@Builder
public record User(
        int id, String name, String username, String email, String phone, String website) {
}
