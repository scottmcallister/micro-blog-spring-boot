package com.example.microblogspringboot.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.microblogspringboot.domain.Entry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


import javax.swing.text.html.parser.Entity;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

/**
 * Created by scottmcallister on 2017-06-27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EntityControllerTest {

    @Autowired
    private EntityController controller;

    @Autowired
    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void newEntry() throws Exception {
        mockMvc.perform(get("/entry")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addEntry() throws Exception {
        String newEntry = json(new Entry("blah", "blah blah blah"));
        mockMvc.perform(post("/entry")
                        .param("title", "blah")
                        .param("content", "blah")
                        .content(newEntry)
                        .contentType(contentType))
                        .andExpect(redirectedUrl("/"));
    }

    @Test
    public void editEntry() throws Exception {
        mockMvc.perform(get("/entry/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void updateEntry() throws Exception {
        // TODO:
        String entryUpdate = json(new Entry("blah", "blah blah blah"));
        mockMvc.perform(post("/entry/1")
                .param("title", "blah")
                .param("content", "blah")
                .content(entryUpdate)
                .contentType(contentType))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void deleteEntry() throws Exception {
        mockMvc.perform(get("/entry/1")).andDo(print()).andExpect(status().isOk());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}