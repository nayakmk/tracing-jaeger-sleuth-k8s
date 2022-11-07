package io.nayakmk.samples.tracing.controller;

import io.nayakmk.samples.tracing.model.Author;
import io.nayakmk.samples.tracing.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableAutoConfiguration
public class BookController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${library.author.service}")
    String authorServiceUrl;

    @GetMapping(path = "/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        ResponseEntity<Author> response = restTemplate.getForEntity(authorServiceUrl +
                "/author/1", Author.class);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Book.builder().title("Core Java").author(response.getBody().getName()).build());
    }
}
