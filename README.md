# Web vk authorization by oauth2
![](https://github.com/Nshmendyuk/web-vk-oauth/workflows/Java%20CI%20with%20Maven/badge.svg)
___
Авторизация в ВК с использованием OAuth2 через vk api
## Getting Started
___
Склонируйте и перейдите в каталог с проектом

    git clone https://github.com/NShmendyuk/web-vk-oauth
    cd web-vk-oauth
В application.properties требуется установить id приложения и защищенный ключ вашего vk приложения 

    vk.client_id = *id приложения*
    vk.client_secret = *защищенный ключ*
Соберите проект через систему сборки maven и запустите скомпилированный проект

    mvn compile
    mvn exec:java -Dexec.mainClass="ru.webitmo.vkapi.VkapiApplication"
    
## Built with
 - [JDK 11](https://www.oracle.com/ru/java/technologies/javase-jdk11-downloads.html) - Среда разработки
 - [Spring Boot](https://spring.io/projects/spring-boot) - Web Framework
 - [Maven](https://maven.apache.org/download.cgi) - Система сборки
 - [VK Java SDK](https://vk.com/dev/Java_SDK) - Библиотека для взаимодействия с API ВКонтакте
