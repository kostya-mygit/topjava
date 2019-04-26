# Онлайн проекта <a href="https://github.com/JavaWebinar/topjava">Topjava</a>

### Обновил <a href="http://topjava.herokuapp.com/">Демо приложение</a> нашей версией

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFSzlObk8tbHdtcXc">Материалы занятия</a>
#### Некоторые темы урока новые, поэтому видео есть не везде.
## Все материалы проекта (в том числе и будущие обновления) останутся доступны в [Google Drive](https://drive.google.com/drive/folders/0B9Ye2auQ_NsFflp6ZHBLSFI2OGVEZ2NQU0pzZkx4SnFmOWlzX0lzcDFjSi1SRk5OdzBYYkU)

### ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Правка

#### Apply 11_0_fix.patch
> - поправил файл локализации: Spring понимает UTF-8 и не требуются [похдод JSP c escape-кодами](https://stackoverflow.com/questions/8423025/something-wrong-with-encoding-of-properties-or-jsp)
>   - В IDEA снимите галочку с [_File->Settings->File Encoding->Transparent native-to-ascii conversion_](https://github.com/JavaOPs/topjava/wiki/IDEA#Поставить-кодировку-utf-8)
> - удалил неиспользуемый импорт 

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Разбор домашнего задания HW10

### ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 1. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFX2V5eHRsa09IWHc">HW10</a>
#### Apply 11_01_HW10_fix_encoding.patch
> - Выставлять кодировку `-Dfile.encoding=UTF-8` лучше в _Help menu -> Edit Custom VM Options_
> - Советы по дополнительным настройкам: 
>   - [Customize IntelliJ IDEA Memory](http://tomaszdziurko.com/2015/11/1-and-the-only-one-to-customize-intellij-idea-memory-settings)
>   - [Increase IDE memory limit in IntelliJ IDEA](http://tomaszdziurko.com/2015/11/1-and-the-only-one-to-customize-intellij-idea-memory-settings)
>   - [Slow startup time on Windows](https://youtrack.jetbrains.com/issue/IDEA-211178#focus=streamItem-27-3412218.0-0)

#### Apply 11_02_HW10_validation.patch
> - В [соответствии со спицификацией](http://stackoverflow.com/a/22358422/548473) для поменял `HTTP 400` (ошибка в структуре сообщения) на `HTTP 422` (ошибка в содержании)
> - Сделал тесты и проверку типа ошибки [через jsonPath](https://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-write-clean-assertions-with-jsonpath/)

#### Apply 11_03_HW10_duplicate_email.patch
> - сделал код(ключ) i18n константой (`EXCEPTION_DUPLICATE_EMAIL`)

#### Apply 11_04_HW10_duplicate_datetime.patch
> - Реализавать обработку дублирования `user.email` и `meal.dateTime` можно по разному
>   - через [поиск в сообщении `DataIntegrityViolationException` имени DB constrains](https://stackoverflow.com/a/42422568/548473)
>   - через [Controller Based Exception Handling](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc#controller-based-exception-handling)

Первый самый простой и расширяемый (хотя зависить от базы), выбрал его. Для работы с HSQLDB сделал `toLowerCase`.

> - Сделал утильный класс `MessageUtil` для работы с i18n.
> - Добавил тесты на дублирование. Отключил транзакционность в тестах на дублирование через `@Transactional(propagation = Propagation.NEVER)`.
>   - [Решение проблемы с транзакционными тестами](https://stackoverflow.com/a/46415060/548473)

#### Apply 11_05_HW10_binder_validation.patch
> - Добавил корректный способ проверки email с валидатором: проверка делается в контроллерах, а не при сохранении
>   - [Spring MVC validator example](https://www.mkyong.com/spring-mvc/spring-mvc-form-handling-example/)
 

###  ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) 2. <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFYms4YUxEMHdxZHM">HW10 Optional: change locale</a>
#### Apply 11_06_HW10_change_locale.patch
> - Добавил локализацию календаря `$.datetimepicker.setLocale(localeCode)`
> - Вместо смену локали в `lang.jsp` через javascript сделал `href=${requestScope['javax.servlet.forward.request_uri']}?lang=..`
> - Добавил [Collapsing The Navigation Bar](https://www.w3schools.com/bootstrap4/bootstrap_navbar.asp)

## Заключительное 11-е занятие

### Локализация:
#### Apply 11_07_i18n.patch
 - Добавил [локализацию Search в datatable](https://datatables.net/reference/option/language)
 - Сделал локализацию ошибок валидации:
   - Все коды для локализации конкретной ошибки валидации можно посмотреть в дебаге: `FieldError.getCodes()`
   - Коды для ее аргументов: `FieldError.getArguments()` (аргумент складывается из имени класса и поля).
 - Добавил локализацию `ErrorInfo.type` (код локализации в `ErrorType` и поле `ErrorInfo.typeMessage`)
 - В выводе AJAX ошибки вывожу `errorInfo.typeMessage`
 - [Увеличил ширину высплывающего noty](https://stackoverflow.com/a/53855189/548473) 
 
### Защита от XSS (Cross Site Scripting)
> **Попробуйте до и после патча ввести в любое текстовое поле редактирования `<script>alert('XSS')</script>` и сохранить.**
#### Apply 11_08_XSS.patch
> - `password` проверять не надо, т.к. он не выводится в html, а [email надо](https://stackoverflow.com/questions/17480809)
> - Сделал общий интерфейс валидации `View.Web` и `@Validated(View.Web.class)` вместо `@Valid` для проверки содержимого только на входе UI/REST. При сохранении проверка на безопасный html контент не делается.

- <a href="https://forum.antichat.ru/threads/20140/">XSS для новичков</a>
- <a href="https://habrahabr.ru/post/66057/">XSS глазами злоумышленника</a>
- <a href="http://stackoverflow.com/a/40644276/548473">Prevent people from doing XSS in Spring MVC</a>

### [Обработка ошибок 404 (NotFound)](https://stackoverflow.com/questions/18322279/spring-mvc-spring-security-and-error-handling)
#### Apply 11_09_404.patch

### Доступ к AuthorizedUser
#### Apply 11_10_auth_user.patch
- [Автоподстановка в контроллерах](https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#mvc-authentication-principal)
  - не стал делать автоподстановку по всем контроллерам (в абстрактных контроллерах проще работать с `SecurityUtil`, чем получать его через `@AuthenticationPrincipal` и передавать параметром)
- [В JSP: the authentication Tag](https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/taglibs.html#the-authentication-tag)
  - авторизованный пользователь доступен в JSP через tag `authentication`, интерсептор становится не нужным

### Ограничение модификации пользователей
#### Apply 11_11_restrict_modification.patch
 - В `AbstractUserController` добавилась защита от удаления-модификации `Admin/User` для профиля `HEROKU` (в `AbstractUserController` заинжектил `Environment` и сделал проверку `HEROKU`)
 - Чтобы тесты были рабочими, ввел профиль `HEROKU`, работающий так же, как и `POSTGRES`.
 - Добавил `ApplicationException` для работы с ошибками с поддержкой i18n в приложении (от него отнаследовал `NotFoundException` и `ModificationRestrictionException`)

> Для тестирования с профилем heroku добавьте в VM options: `-Dspring.profiles.active="datajpa,heroku"`

###  ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png)  <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFZkpVM19QWFBOQ2c">3. Деплой приложения в Heroku.</a>
#### Apply 11_12_heroku.patch
> - Добавил зависимости `postgres` в профиль мавена `heroku`
> - [Поменял настройки `dataSource` для профиля `heroku`](http://stackoverflow.com/questions/10684244/dbcp-validationquery-for-different-databases). 
При опускании/поднятии приложения в heroku.com портятся коннекты в пуле и необходимо их валидировать. 
> - В tomcat 9 в `META-INF\services\javax.cache.spi.CachingProvider` находится реализация провайдера Redis JCache, которая конфликтует с нашеим ehcache провайдером: `ehcache-3.6.1.jar!\META-INF\services\javax.cache.spi.CachingProvider`. 
 [Решается заменой зависимости на `webapp-runner-main`](https://github.com/jsimone/webapp-runner#excluding-memcached-and-redis-libraries)


### Приложение деплоится в ROOT: [http://localhost:8080](http://localhost:8080)

- [Деплой Java Spring приложения в PaaS-платформу Heroku](http://habrahabr.ru/post/265591)
```
Config Vars
  ERROR_PAGE_URL=...
  TOPJAVA_ROOT=/app

Datasources advanced
    ssl=true
    sslfactory=org.postgresql.ssl.NonValidatingFactory
```    

-  Ресурсы:
   -  <a href="https://www.heroku.com/">PaaS-платформа Heroku</a></h3>
   -  Конфигурирование приложения для запуска через <a href="https://devcenter.heroku.com/articles/java-webapp-runner">Tomcat-based Java Web</a>
   -  Конфигурирование <a href="https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java#using-the-database_url-in-spring-with-xml-configuration">DataSource profile для Heroku</a>
   -  <a href="http://www.jetbrains.com/idea/webhelp/run-debug-configuration-heroku-deployment.html">Интерграция с IDEA</a>
   -  <a href="http://www.paasify.it/filter">Find your Platform as a Service</a>
   -  <a href="https://devcenter.heroku.com/articles/getting-started-with-java#set-up">Getting Started with Java on Heroku</a>
   -  <a href="https://devcenter.heroku.com/articles/keys">Managing Your SSH Keys</a>
   -  <a href="https://devcenter.heroku.com/articles/getting-started-with-spring-mvc-hibernate#deploy-your-application-to-heroku">Deploy your application to Heroku</a>
   -  <a href="http://www.ibm.com/developerworks/ru/library/j-javadev2-21/">Развертывание приложений Java с помощью PaaS от Heroku</a>
   -  <a href="http://www.infoq.com/articles/paas_comparison">A Java Developer’s Guide to PaaS</a>
   -  <a href="https://dzone.com/articles/simple-paas-comparison-guide">A Simple PaaS Comparison Guide (With the Java Dev in Mind)</a>
   -  <a href="http://www.ibm.com/developerworks/library/j-paasshootout/">Java PaaS shootout</a>


###  ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png)  <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFQVc2WUdCR0xvLWM">4. Собеседование. Разработка ПО</a>
- [Темы/ресурсы тестового собеседования](http://javaops.ru/interview/test.html)
- [Составление резюме, подготовка к интервью, поиск работы](https://github.com/JavaOPs/topjava/blob/master/cv.md)
- [Слайды](https://docs.google.com/presentation/d/18o__IGRqYadi4jx2wX2rX6AChHh-KrxktD8xI7bS33k), [Книги](http://javaops.ru/view/books)
- [Jenkins/Hudson: что такое и для чего он нужен](https://habrahabr.ru/post/334730/)

###  ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png)  <a href="https://drive.google.com/open?id=1QtHfavgIeLEnKA2Yt58XzKOouiLhg6qX">5. Разбор типовых собеседований (необработанный вебинар)</a>

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png)  Домашнее задание по проекту:
- **Задеплоить свое приложение в Heroku** 
   - [Маленькие хитрости с Heroku](https://javarush.ru/groups/posts/1987-malenjhkie-khitrosti-s-heroku)
- Сделать / обновить резюме (отдать на ревью в канал #hw11 группы slack)
  - [Загрузка сайта на GitHub. Бесплатный хостинг и домен.](https://vk.com/video-58538268_456239051?list=661b165047264e7952)
  - [CSS theme for hosting your personal site, blog, or portfolio](https://mademistakes.com/work/minimal-mistakes-jekyll-theme/)
- **Заполнить в [своем профиле Java Online Projects](http://javaops.ru/view/login) ссылку на резюме и информацию по поиску работы (если конечно актуально): резюме, флаги рассматриваю работу, готов к релокации и информация для HR.**
   - Обновления базы соискателей буду рассылать по HR утром 06.05, постарайтесь успеть
- **Заполнить [форму проверки выпускного проекта](https://docs.google.com/forms/d/1G8cSGBfXIy9bNECo6L-tkxWQYWeVhfzR7te4b-Jwn-Q) (возможно доплатить за ревью отдельно из JavaOPs профиля, как за тестовое собеседование: 1950р)**
  - Для участников с проверкой выпускной проект принимаю на ревью до 16.05 (участникам [Masterjava](http://javaops.ru/view/masterjava) до 16.08)
- **Получить первое открытое занятие МНОГОПОТОЧНОСТЬ и пройти эту важную тему в [проекте Masterjava](http://javaops.ru/view/masterjava)**
   - Обучение на Masterjava сейчас идет в индивидуальном режиме, старт в любое время.
   - Проект, патчи, группа Slack, занятия и видео анологичны проекту Topjava. 
   - **По 01.05 цена минимальная: 6300 руб.**
   
## Возможные доработки приложения:
-  Для редактирования паролей сделать отдельный интерфейс с запросом старого пароля и кнопку сброса пароля для администратора.
-  Добавление и удаление ролей для пользователей в админке.
-  Перевести UI на Angular / <a href="https://vaadin.com/elements">Vaadin elements</a> /GWT /GXT /Vaadin / ZK/ [Ваш любимый фреймворк]..
-  Перевести проект на <a href="http://projects.spring.io/spring-boot/">Spring Boot 2</a>
-  Перевести шаблоны с JSP на <a href="http://www.thymeleaf.org/doc/articles/petclinic.html">Thymeleaf</a>
-  Сделать авторизацию в приложение по OAuth 2.0 (<a href="http://projects.spring.io/spring-security-oauth/">Spring Security OAuth</a>,
<a href="https://vk.com/dev/auth_mobile">VK auth</a>, <a href="https://developer.github.com/v3/oauth/">github oauth</a>, ...)
-  Сделать подтверждение регистрации пользователя по email c возможностью восстановления пароля.
-  Сделать отображение еды постранично, с поиском и сортировкой на стороне сервера.
-  Перевод проекта на https
-  Сделать desktop/mobile приложение, работающее по REST с нашим приложением.
-  <a href="http://spring.io/blog/2012/08/29/integrating-spring-mvc-with-jquery-for-validation-rules/">Показ ошибок в модальном окне редактирования таблицы так же, как и в JSP профиля</a>
-  <a href="http://www.mkyong.com/spring-security/spring-security-limit-login-attempts-example">Limit login attempts example</a>
-  Сделать авторизацию REST по <a href="https://en.wikipedia.org/wiki/JSON_Web_Token">JWT</a>

## Доработки участников прошлых выпусков:
- [Авторизация в приложение по OAuth2 через GitHub](http://rblik-topjava.herokuapp.com)
  - [GitHub, ветка oauth](https://github.com/rblik/topjava/tree/oauth)
- [Авторизация в приложение по OAuth2 через GitHub/Facebook/Google](http://tj9.herokuapp.com)
  - [GitHub](https://github.com/jacksn/topjava)
- [Angular 2 UI](https://topjava-angular2.herokuapp.com)
  - [tutorial по доработке](https://github.com/Gwulior/article/blob/master/article.md)
  - [ветка angular2 в гитхабе](https://github.com/12ozCode/topjava08-to-angular2/tree/angular2)
- [Отдельный фронтэнд на Angular 2, который работает по REST с авторизацией по JWT](https://topjava6-frontend.herokuapp.com)
  - [ветка development фронтэнда](https://github.com/evgeniycheban/topjava-frontend/tree/development)
  - [ветка development бэкэнда](https://github.com/evgeniycheban/topjava/tree/development)
  - в <a href="https://en.wikipedia.org/wiki/JSON_Web_Token">JWT токенен</a> приложение topjava передает email, name и роль admin как boolean true/false,
на клиенте он декодируется и из него получается auth-user, с которым уже работает фронтэнд

#### Жду твою доработку из списка!

### Ресурсы по Проекту
-  <a href="https://webformyself.com/urok-1-frejmvork-bootstrap-4-chto-takoe-bootstrap-otlichiya-bootstrap-3-ot-bootstrap-4/">Уроки Bootstrap 4</a>
-  <a herf="http://www.tutorialspoint.com/spring/index.htm">Spring at tutorialspoint</a>
-  <a href="http://www.codejava.net/frameworks/spring">Articles in Spring</a>
-  <a href="http://www.baeldung.com/learn-spring">Learn Spring on Baeldung</a>
-  <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/html/index.html">Spring Framework
            Reference Documentation</a>
-  <a href="http://hibernate.org/orm/documentation">Hibernate Documentation</a>
-  <a href="http://java-course.ru/student/book2/">Java Course (книга 2)</a>
-  <a href="http://design-pattern.ru/">Справочник «Паттерны проектирования»</a>
-  <a href="http://martinfowler.com/eaaCatalog/">Catalog of Patterns of Enterprise Application Architecture</a>

## Пожалуйста <a href="http://goo.gl/forms/PtFcPqEV5n">ответь на 4-6 вопросов по проекту</a>, это важно!

## ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Замечания по резюме:
- варианты размещения: google doc + linkedin + hh + ... 
- хорошо, если будет в html формате (например в https://pages.github.com/). [Например так](https://gkislin.github.io/), [на github](https://github.com/gkislin/gkislin.github.io/blob/master/index.html)
-  возраст и день рождения можно не писать
-  все упоминания Junior убрать!
-  линки делай кликабельными (если формат поддерживает)
-  если нет опыта в IT, обязательно вставь [участие в стажировке Topjava](https://github.com/JavaOPs/topjava/blob/master/cv.md#Позиционирование-проекта-topjava). Также вставь ссылку на свой сертификат (она есть в личном профиле). Весь не-IT опыт можно кратко.
- всю выгодную для себя информацию (и важную для HR) распологайте вверху. Название секций в резюме и их порядок относительно стандартный и важный
- **Внимание: резюме на hh или других ресурсах ДОЛЖНО БЫТЬ ОТКРЫТО ДЛЯ ПРОСМОТРА и иметь телефон для связи**
