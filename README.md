# bookstore

## Contributors:
* [Ida Milewska](https://github.com/Milida)
* [Michał Sawicki](https://github.com/mksawic)
* [Natalia Tarasiuk](https://github.com/natka853)

## Soft:
* [MySQL Installer 8.0.22](https://dev.mysql.com/downloads/installer/)
* [Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### MySQL

Add > MySQL Servers > MySQL Sever 8.0
Run commnad:
```sql
create database bookstore_db;
create user 'user' identified by 'password';
grant all on bookstore_db.* to 'user';
```

### Java Version
```
openjdk 11.0.9 2020-10-20
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.9+11)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.9+11, mixed mode)
```

### Back-end
```mvnw.cmd spring-boot:run```

### Front-end
```
cd react-frontend
npm install
npm staart
```
