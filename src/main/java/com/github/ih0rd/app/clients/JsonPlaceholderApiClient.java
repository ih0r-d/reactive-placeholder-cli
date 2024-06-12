package com.github.ih0rd.app.clients;

import com.github.ih0rd.app.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@HttpExchange
public interface JsonPlaceholderApiClient {


    @GetExchange("/users")
    Flux<ResponseEntity<List<User>>> getUsers();

    @GetExchange("/users/{userId}/posts")
    Flux<ResponseEntity<List<Post>>> getUserPosts(@PathVariable int userId);

    @GetExchange("/users/{userId}/albums")
    Flux<ResponseEntity<List<Album>>> getUserAlbums(@PathVariable int userId);

    @GetExchange("/users/{userId}/todos")
    Flux<ResponseEntity<List<ToDo>>> getUserToDos(@PathVariable int userId);

    @GetExchange("/albums/{albumId}/photos")
    Flux<ResponseEntity<List<AlbumPhoto>>> getAlbumPhotos(@PathVariable int albumId);
}

