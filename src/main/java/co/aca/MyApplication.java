package co.aca;

import co.aca.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
public class MyApplication extends SpringBootServletInitializer {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }

    /**
     * Used when run as JAR
     */
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    /**
     * Used when run as WAR
     */
    //@Override
    //protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    //    return builder.sources(MyApplication.class);
    //}

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homepage() {
        return "home";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error";
    }

    @RequestMapping(value = "/googleaf6f9dbfec23e3da", method = RequestMethod.GET)
    public String googleVerif() {
        return "googleaf6f9dbfec23e3da";
    }
}            