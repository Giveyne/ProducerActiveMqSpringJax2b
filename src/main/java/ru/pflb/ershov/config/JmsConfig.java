package ru.pflb.ershov.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import javax.xml.bind.Marshaller;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class JmsConfig {

    @Bean
    public MarshallingMessageConverter createMarshallingMessageConverter(final Jaxb2Marshaller jaxb2Marshaller){
        return new MarshallingMessageConverter(jaxb2Marshaller);
    }

    @Bean
    public Jaxb2Marshaller createJaxb2Marshaller(@Value("${contex.path}") final String contexPath,
                                             @Value("${schema.location}") final Resource schemaResource) {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath(contexPath);
        jaxb2Marshaller.setSchema(schemaResource);
        Map<String, Object> properties = new HashMap<>();
        properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxb2Marshaller.setMarshallerProperties(properties);
        return jaxb2Marshaller;
    }

}
