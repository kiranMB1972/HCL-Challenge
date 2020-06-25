package com.db.awmd.challenge;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.db.awmd.challenge.service.AccountsService;
import com.db.awmd.challenge.web.AccountsController;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AccountsControllerAmountTransferTest {
        private MockMvc mockMvc;
        @Autowired
        private AccountsService accountsService;
        
        @Autowired
        private WebApplicationContext webApplicationContext;
        
        @Before
        public void setUp() {
         mockMvc = MockMvcBuilders.standaloneSetup(new AccountsController(accountsService)).build();
        }
        
        @Test
        public void testTransferAmounts() throws Exception{
        	MockHttpServletRequestBuilder builder =
        			MockMvcRequestBuilders.put("/v2/accounts/kiran/raja/100")
                    .contentType(MediaType.APPLICATION_XML_VALUE)
                    .accept(MediaType.APPLICATION_XML)
                    .characterEncoding("UTF-8");
        	
        	this.mockMvc.perform(builder)
            .andExpect(MockMvcResultMatchers.status()
                                            .isOk()).andReturn().equals("Successfully transferred amount 100 from 'kiran' to 'raja'\n" + 
                                            		"Currently balances are: 4900, 5000 respectively.........");
        }
        
        @Test
        public void testTransferAmountsAgain() throws Exception{
        	MockHttpServletRequestBuilder builder =
        			MockMvcRequestBuilders.put("/v2/accounts/Id-123/1000/Id-321/10/100")
                    .contentType(MediaType.APPLICATION_XML_VALUE)
                    .accept(MediaType.APPLICATION_XML)
                    .characterEncoding("UTF-8");
        	
        	this.mockMvc.perform(builder)
            .andExpect(MockMvcResultMatchers.status()
                                            .isOk()).andReturn().equals("Successfully transferred amount 100 from 'Id-123' to 'Id-321'\n" + 
                                            		"Currently balances are: 900, 110 respectively.........");
        }
}