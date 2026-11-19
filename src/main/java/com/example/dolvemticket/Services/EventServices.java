package com.example.dolvemticket.Services;

import com.example.dolvemticket.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServices {
    @Autowired
    private EventRepository eventRepository;

}
