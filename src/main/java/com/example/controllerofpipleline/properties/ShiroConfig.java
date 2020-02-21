package com.example.controllerofpipleline.properties;

import com.example.controllerofpipleline.Realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro的配置类
 */
@Configuration
public class ShiroConfig {

    /**
     *创建ShiroFilterFactoryBean
     * @param securityManager（导入securityManager的Bean）
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置Shiro内置过滤器
        /*
         *Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *        anon: 无需认证（登录）可以访问
         *        authc: 必须认证（登录）才可以访问
         *        user: 如果使用rememberMe的功能可以直接访问
         *        perms: 该资源必须得到资源权限才可以访问
         *        role: 该资源必须得到角色资源才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String, String>();
//        Map<String,String> filterMap = new HashMap<String, String>();
//        filterMap.put("/add","authc");
//        filterMap.put("/update","authc");
        filterMap.put("/login","anon");
        filterMap.put("/testTym","anon");
        filterMap.put("/add","perms[add]");
        filterMap.put("/update","perms[update]");
//        filterMap.put("/*","authc");


        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager(SecurityManager)
     * @param userRealm(导入useRealm的Bean)
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
