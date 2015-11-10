package com.theironyard;

/**
 * Created by jessicahuffstutler on 11/9/15.
 */
public class Message {
    String text;
    int id;

    public Message(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }
}
