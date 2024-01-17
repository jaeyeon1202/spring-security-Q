package study.security.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MustacheViewResolver resolver = new MustacheViewResolver();
        resolver.setCharset("UTF-8"); //인코딩은 utf-8이야
        resolver.setContentType("text/html; charset=UTF-8"); //데이터는 html파일이야. html파일은 utf-8이야
        resolver.setPrefix("classpath:/templates/"); //views 경로
        resolver.setSuffix(".html"); //.html로 만들어도 머스테치로 저장됨

        registry.viewResolver(resolver); //registry로 뷰리졸버 등록
    }
}
