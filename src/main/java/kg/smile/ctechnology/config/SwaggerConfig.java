package kg.smile.ctechnology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("kg.smile.ctechnology.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getInfo());
    }

    private ApiInfo getInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("Ulanov Nurdin", "https://github.com/Smile-Bonchichi", "ulanovnurdin@gmail.com"))
                .description(
                        "Задача:\n" +
                                "\n" +
                                "Сделать API с возможностью авторизации пользователя и совершения платежа только после успешной авторизации. Должны быть 3 endpoint'а: login (вводим логин и пароль, при успехе выдает токен), logout (делает токен недействительным) и payment (при добавлении пользователя в БД ставим баланс 8 USD, сама операция позволяет снимать с баланса пользователя 1.1 USD при каждом вызове, все совершенные платежи хранятся в БД). Сделанный проект надо выгрузить в репозиторий на Github.\n" +
                                "\n" +
                                "Требования к функционалу (авторизация):\n" +
                                "- если логин/пароль неправильные - выводим ошибку\n" +
                                "- одновременная поддержка нескольких сессий пользователя\n" +
                                "- не хранить пароли в базе в открытом виде\n" +
                                "- защита от брутфорса (подбора пароля)\n" +
                                "\n" +
                                "Требования к функционалу (платеж):\n" +
                                "- защита от ошибочных списаний (изоляция транзакций)\n" +
                                "- отсутствие ошибок округления\n" +
                                "- корректное хранение и операции с финансовыми данными\n" +
                                "\n" +
                                "Требования к коду:\n" +
                                "- конкретный стек (фреймворки и библиотеки) не принципиален\n" +
                                "- простая реализация логики и БД"
                )
                .title("Тестовый проект")
                .version("v1.0")
                .build();
    }
}
