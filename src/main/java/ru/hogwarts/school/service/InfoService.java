package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
// не понял что требовалось, вроде просто распаралелил или нужно что то еще?
public class InfoService {
    public int getInt() {
        int sum = Stream.iterate(1, a -> a + 1).
                limit(1_000_000).
                parallel().
                reduce(0, Integer::sum);
        return sum;
    }
}
