package net.javaguides.springboot.tutorial.service;

public class EventNotFoundException extends Exception {
    public EventNotFoundException(){
        super("Event not found.");
    }
}
