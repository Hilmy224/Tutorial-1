package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
class HomePageControllerTest {

    
    HomePageController homePageController = new HomePageController();
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homePageController).build();

    @Test
    void homePageControllerReturnTest() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.view().name("HomePage"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}