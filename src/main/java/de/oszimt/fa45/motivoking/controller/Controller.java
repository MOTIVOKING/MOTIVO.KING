package de.oszimt.fa45.motivoking.controller;

/**
 * Created by RedCyberSamurai on 17.11.2016.
 */
public interface Controller {
    <T> T create();
    void read();
    void update();
    void delete();
}
