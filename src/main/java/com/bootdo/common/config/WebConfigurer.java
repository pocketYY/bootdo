package com.bootdo.common.config;

import com.bootdo.common.interceptor.RequestLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
class WebConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    BootdoConfig bootdoConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置模板资源路径
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/docs/**").addResourceLocations("classpath:/static/docs/");
        registry.addResourceHandler("/editor-app/**").addResourceLocations("classpath:/static/editor-app/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico");

        registry.addResourceHandler("/chart/**").addResourceLocations("classpath:/public/chart/");
        registry.addResourceHandler("/diagram-viewer/**").addResourceLocations("classpath:/public/diagram-viewer/");
        registry.addResourceHandler("/FontIcoList.html").addResourceLocations("classpath:/public/FontIcoList.html");
        registry.addResourceHandler("/modeler.html").addResourceLocations("classpath:/public/modeler.html");
        registry.addResourceHandler("/files/**").addResourceLocations("file:///" + bootdoConfig.getUploadPath());
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RequestLog());
//        super.addInterceptors(registry);
//    }
}