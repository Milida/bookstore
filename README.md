# bookstore

## Potrzebne programy:
* [MySQL Installer 8.0.22](https://dev.mysql.com/downloads/installer/)
* [Java SE Development Kit 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### MySQL

Odpalamy Installer, klikamy Add > MySQL Servers > MySQL Sever 8.0 > wybieramy najnowszą wersję (u mnie akurat 8.0.22)
Podczas instalacji w którymś tam momencie poprosi o ustwienie hasła do roota. Trzeba to hasło zapamiętać bo później będzie lipa :P
Jak już się zainstaluje to odpalamy MySQL 8.0 Command Line Client i lecimy po kolei polecenia:
```sql
create database bookstore_db;
create user 'user' identified by 'password';
grant all on bookstore_db.* to 'user';
```
Można zamknąć Command Line'a

### Java

Sprawdźcie sobie czy wcześniej nie maciej już jakiejś Javy zainstalowanej (polecenie `Java --version` w cmd). U mnie obecnie:
```
openjdk 11.0.9 2020-10-20
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.9+11)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.9+11, mixed mode)
```
Jak macie inną no to trzeba usunąć ją i zainstalować 11 bo inaczej się spring nawet nie zbuduje.

### Odpalamy
Dobra no to teraz odpalamy projekt w edytorze z którego korzystacie (u mnie to VS Code). Następnie Terminal i polecenie: `mvnw.cmd spring-boot:run`
