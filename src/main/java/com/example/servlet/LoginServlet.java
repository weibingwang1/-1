package com.example.servlet;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/login",loadOnStartup = 1)
public class LoginServlet extends HttpServlet {/*先继承Http类*/

    SqlSessionFactory factory;
    @SneakyThrows
    @Override
    public void init() throws ServletException{/*初始化*/
        /*获取mybatis配置文件*/
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        /*获取POST请求携带的表单数据*/
        Map<String,String[]> map =req.getParameterMap();
        /*判断表单是否完整*/
        if(map.containsKey("username")&&map.containsKey("password")){
            String username=req.getParameter("username");/*获取用户名和密码*/
            String password=req.getParameter("password");
            /*权限校验 ,防止被攻击*/
            try(SqlSession sqlSession=factory.openSession(true)){
                UserMapper userMapper=sqlSession.getMapper(UserMapper.class);/*先获取UserMapper*/
                User user=userMapper.getUser(username,password);/*调用接口中的方法，在数据库中进行查询*/
                /*查询结果不为空*/
                if(user !=null){
                    resp.getWriter().write("用户"+username+"登陆成功！");
                }else{
                    resp.getWriter().write("账号或密码错误！");
                }
            }
        }else{
            resp.getWriter().write("抱歉，您提交的表单不完善！");
        }

    }
}
