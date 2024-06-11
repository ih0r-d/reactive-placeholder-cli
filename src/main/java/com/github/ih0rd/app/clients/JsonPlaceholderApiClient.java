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
    Flux<ResponseEntity<Album>> getUserAlbums(@PathVariable int userId);

    @GetExchange("/users/{userId}/todos")
    Flux<ResponseEntity<ToDo>> getUserToDos(@PathVariable int userId);

    @GetExchange("/comments")
    Flux<ResponseEntity<Comment>> getCommentsByPostId(@RequestParam int postId);

    @GetExchange("/albums/{albumId}/photos")
    Flux<ResponseEntity<Album>> getAlbumPhotos(@PathVariable int albumId);
}

