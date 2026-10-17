package j.jav25.apiversioning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UriPathApiVersionConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {

        // /api/v{version}/products
        // /api/v1.0/products
        // /api/v2.0/products
        // /api/v3.0/products
        configurer.addSupportedVersions("1.0", "2.0", "3.0")
                .setDefaultVersion(null)
                .usePathSegment(1);  // path segment (index 0 is /api; index 1 is /v{version}
    }
}
