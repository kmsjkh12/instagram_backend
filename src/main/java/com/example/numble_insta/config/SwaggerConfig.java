package com.example.numble_insta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.extensions.Extensions;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;


@Configuration
public class SwaggerConfig {
    // 테스트 케이스 확인을 위한 전역 파라미터 설정
    @Bean
    public OperationCustomizer operationCustomizer() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            Parameter param = new Parameter()
                    .in(ParameterIn.HEADER.toString())  // 전역 헤더 설정
                    .schema(new StringSchema()._default("Test_case").name("TestID")) // default값 설정
                    .name("TestID")
                    .description("TEST 케이스를 위한 헤더값(수정금지)")
                    .required(true);
            operation.addParametersItem(param);
            return operation;
        };
    }

    @Bean
    // 회원과 관련된 Api
    public GroupedOpenApi UserApi(OperationCustomizer operationCustomizer) {
        String path = "/User/**";

        return GroupedOpenApi.builder()
                .group("① 회원 관련 API")
                .pathsToMatch(path)
                .addOperationCustomizer(operationCustomizer)
                .build();
    }

    @Bean
    // 영화와 관련된 Api
    public GroupedOpenApi PostApi(OperationCustomizer operationCustomizer) {
        String path = "/Post/**";

        return GroupedOpenApi.builder()
                .group("② 게시글 관련 API")
                .pathsToMatch(path)
                .addOperationCustomizer(operationCustomizer)
                .build();
    }

    @Bean
    // 상영정보와 관련된 Api
    public GroupedOpenApi CommentAPi(OperationCustomizer operationCustomizer) {
        String path = "/Comment/**";

        return GroupedOpenApi.builder()
                .group("③ 댓글 관련 API")
                .pathsToMatch(path)
                .addOperationCustomizer(operationCustomizer)
                .build();
    }

    @Bean
    // 극장과 관련된 Api
    public GroupedOpenApi ReplyAPi(OperationCustomizer operationCustomizer) {
        String path = "/덧글/**";

        return GroupedOpenApi.builder()
                .group("④ 덧글 관련 API")
                .pathsToMatch(path)
                .addOperationCustomizer(operationCustomizer)
                .build();
    }

    @Bean
    // 좌석과 관련된 Api
    public GroupedOpenApi MessageAPi(OperationCustomizer operationCustomizer) {
        String path = "/Message/**";

        return GroupedOpenApi.builder()
                .group("⑤ 메세지 관련 API")
                .pathsToMatch(path)
                .addOperationCustomizer(operationCustomizer)
                .build();
    }

    @Bean
    // 결제와 관련된 Api
    public GroupedOpenApi FollowAPi(OperationCustomizer operationCustomizer) {
        String path = "/Follow/**";

        return GroupedOpenApi.builder()
                .group("⑥ 팔로우 관련 API")
                .pathsToMatch(path)
                .addOperationCustomizer(operationCustomizer)
                .build();
    }

    @Bean
    // 회원의 영화 평가와 관련된 Api
    public GroupedOpenApi ChatRoomAPi(OperationCustomizer operationCustomizer) {
        String path = "/ChatRoom/**";

        return GroupedOpenApi.builder()
                .group("⑦ 채팅방 API")
                .pathsToMatch(path)
                .addOperationCustomizer(operationCustomizer)
                .build();
    }


}