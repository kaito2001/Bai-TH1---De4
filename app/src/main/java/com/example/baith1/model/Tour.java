package com.example.baith1.model;

public class Tour {
    private int img;
    private String tour, timeLine;

    public Tour(int img, String tour, String timeLine) {
        this.img = img;
        this.tour = tour;
        this.timeLine = timeLine;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(String timeLine) {
        this.timeLine = timeLine;
    }

    public Tour () {}
}
