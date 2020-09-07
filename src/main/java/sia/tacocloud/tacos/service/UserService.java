package sia.tacocloud.tacos.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.tacocloud.tacos.entity.User;
import sia.tacocloud.tacos.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    public List<User> list(){
        return mapper.selectList(new QueryWrapper<User>());
    }
}
