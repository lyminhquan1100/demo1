package vn.vnpay.demo1.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class YamlPropertySourceFactory implements PropertySourceFactory {

    /**
     * Create a {@link PropertySource} that wraps the given resource.
     *
     * @param name     the name of the property source
     * @param resource the resource (potentially encoded) to wrap
     * @return the new {@link PropertySource} (never {@code null})
     * @throws IOException if resource resolution failed
     */
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource)
            throws IOException {
        log.info("Loading resource {}", resource.getResource().getFilename());

        Properties properties = load(resource);
        return new PropertiesPropertySource(resource.getResource().getFilename(), properties);
    }

    /**
     * Load properties from the YAML file.
     *
     * @param resource Instance of {@link EncodedResource}
     * @return instance of properties
     */
    private Properties load(EncodedResource resource) throws FileNotFoundException {
        try {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(resource.getResource());
            factory.afterPropertiesSet();

            return factory.getObject();
        } catch (IllegalStateException ex) {
            /*
             * Ignore resource not found.
             */
            Throwable cause = ex.getCause();
            if (cause instanceof FileNotFoundException) throw (FileNotFoundException) cause;
            throw ex;
        }
    }
}
