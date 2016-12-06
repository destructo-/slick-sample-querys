# slick-sample-querys

Запускаем postgres в docker
> $ docker run --net=host --name develop-postgres -e POSTGRES_PASSWORD=123123 -d postgres

Создаем базу и забиваем тестовые данные
> $ sbt flywayMigrate

Устанавливаем зависимости и запускаем проект
> $ sbt update && sbt run
