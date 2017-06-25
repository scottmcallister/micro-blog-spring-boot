package com.example.microblogspringboot.web;

import com.example.microblogspringboot.domain.Entry;
import com.example.microblogspringboot.domain.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by scottmcallister on 2017-06-22.
 */
@Controller
public class HomeController {

    @Autowired
    EntryRepository entryRepository;

    @RequestMapping("/")
    public String home(Model model){
        Pageable pageable = new PageRequest( 1, 10 );
        List<Entry> allEntries = entryRepository.findAll();
        model.addAttribute("message", "Blog Entries");
        model.addAttribute("entries", allEntries);
        return "home";
    }
}
