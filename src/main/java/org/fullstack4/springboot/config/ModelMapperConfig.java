package org.fullstack4.springboot.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 라이브러리를 갖다 쓰려면 환경설정이 필요한데, 그 환경설정을 해주는 자바 클래스야.
                // xml 파일이 아니라지금 이 자바 파일에서 그대로 갖다 쓸거야 할 때 사용
public class ModelMapperConfig {
    @Bean   //객체로 등록하고 이름을 할당하고 호출해서 쓸테니 스프링 너가 알아서 해줘
    public ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
