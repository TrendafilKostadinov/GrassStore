package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.CustomerDetailDTO;
import bg.softuni.grassstore.model.dto.OrderDetailDTO;
import bg.softuni.grassstore.model.dto.TraderSalesDTO;
import bg.softuni.grassstore.model.dto.UserDetailDTO;
import bg.softuni.grassstore.service.CustomerService;
import bg.softuni.grassstore.service.OrderService;
import bg.softuni.grassstore.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrderService orderService;

    @Test
    @WithMockUser
    public void testGetIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));
    }

    @Test
    @WithMockUser
    public void testGetHome() throws Exception {
        List<UserDetailDTO> users = List.of(new UserDetailDTO());
        List<CustomerDetailDTO> customers = List.of(new CustomerDetailDTO());
        List<OrderDetailDTO> orders = List.of(new OrderDetailDTO());
        List<TraderSalesDTO> sales = List.of(new TraderSalesDTO().setTrader("Trader1")
                .setSales(BigDecimal.TEN));

        Mockito.when(userService.getUsersFromSessionRegistry()).thenReturn(users);
        Mockito.when(customerService.getAllCustomersByTrader()).thenReturn(customers);
        Mockito.when(orderService.getAllActiveOrders()).thenReturn(orders);
        Mockito.when(orderService.calculateAllSum(orders)).thenReturn(orders);
        Mockito.when(orderService.getAllSales()).thenReturn(sales);

        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    @WithMockUser
    public void testGetAllUsers() throws Exception {
        List<UserDetailDTO> users = List.of(new UserDetailDTO());

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/all-users"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-users"))
                .andExpect(model().attributeExists("users"));
    }
}

