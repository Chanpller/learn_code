package com.chanpller.spring6_springboot3_ssm.chapter2;

public class SimpleMovieLister {
    private MovieFinder movieFinder;

    private String movieName;

    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

    // business logic that actually uses the injected MovieFinder is omitted...
}
