package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;

@RestController
public class InfoController {
    private final int port;

    private final InfoService service;

    public InfoController(@Value("${server.port}") int port, InfoService service) {
        this.port = port;
        this.service = service;
    }

    @GetMapping("/getPort")
    private int port() {
        return port;
    }


    @GetMapping("/getInt")
    public int getInt() {
        return service.getInt();
    }

}
