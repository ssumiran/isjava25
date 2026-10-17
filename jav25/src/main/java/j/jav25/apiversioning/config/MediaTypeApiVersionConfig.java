//package j.jav25.apiversioning.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MediaTypeApiVersionConfig implements WebMvcConfigurer {
//
//    @Override
//    public void configureApiVersioning(ApiVersionConfigurer configurer) {
//
//        // application/json;version=1.0
//        configurer.addSupportedVersions("1.0", "2.0", "3.0")
//                .setDefaultVersion("1.0")
//                .useMediaTypeParameter(MediaType.parseMediaType("application/json"), "version");
//    }
//}
