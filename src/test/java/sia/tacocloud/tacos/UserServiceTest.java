package sia.tacocloud.tacos;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import sia.tacocloud.tacos.controller.HomeController;
import sia.tacocloud.tacos.entity.User;
import sia.tacocloud.tacos.mapper.UserMapper;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserMapper userMapper;
//    @Autowired
//    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    private HomeController homeController;

//    @BeforeEach
//    public void initMockMvc(){
//        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        MockMvcBuilders.standaloneSetup(homeController);
//    }


    @Test
    public void testHomePage() throws Exception {
        //发送一个get请求，参数为uri
        mockMvc.perform(MockMvcRequestBuilders.post("/readingList")
                //在get方法后添加参数
        .param("title","book title")
                //设定返回值的contentType
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                //判断返回状态是否为200
//                .andExpect(MockMvcResultMatchers.status().isOk())
                //判断请求状态，这里包含了所有的请求状态码，如3xx是重定向，2xx是访问成功，4xx是clientError等
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                //判断得到的view对象的name是否为指定
                .andExpect(MockMvcResultMatchers.view().name("home"))
                //判断model中的属性是否存在
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
                //判断model中对应名称的属性的值是否符合预期，这里表示为空
                .andExpect(MockMvcResultMatchers.model().attribute("books",Matchers.is(Matchers.empty())))
                //判断content中的string内容是否匹配Matchers
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Welcome taco")))
                //判断header中的内容，是否匹配，第一个参数是name，第二个参数是value
                .andExpect(MockMvcResultMatchers.header().string("Location","/readingList"));

    }

    @Test
    public void testModelObject() throws Exception {
        //设定一个拥有目标属性的对象
//        1	Jone	18	test1@baomidou.com
        User exceptUser = new User();
        exceptUser.setId(1L);
        exceptUser.setName("Jone");
        exceptUser.setAge(18);
        exceptUser.setEmail("test1@baomidou.com");

        mockMvc.perform(MockMvcRequestBuilders.get("/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jack"));

//        mockMvc.perform(MockMvcRequestBuilders.get("/list"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("home"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name","Jone"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
//                //Matchers中的方法判断是否与model中的属性匹配。
//                .andExpect(MockMvcResultMatchers.model().attribute("users",Matchers.hasSize(5)))
//                .andExpect(MockMvcResultMatchers.model().attribute("users",
//                        //contains需要数组中的每一个对象都与期待对象相同，hasItem则只需要一项，并且在
//                        //找到一个之后就停止测试。
//                        Matchers.hasItem(Matchers.samePropertyValuesAs(exceptUser))));
    }
}
