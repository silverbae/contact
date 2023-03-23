# contacts
contact정보를 crud기능을 제공하는 간단한 어플리케이션.

- java spring boot
- rest api, 
- JPA
- Mysql 
# DB 설치 및 설정
```
mysql 실행
$ docker run -itd --rm --name mysql -e TZ=Asia/Seoul -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin -e MYSQL_DATABASE=test -p 3306:3306 mysql:8.0.32
테스트용 테이블 삽입
$ docker exec -it mysql bash
$ mysql -u root -p1234
$ create database contact;
```
로컬에 설치된 mysql정보를 resouces/application.properties에 업데이트가 필요하다..
db는 contact로 생성해줘야한다.
{ip}는 localhost로 접속해서 안된다면, ifconfig 확인한 후 ip입력
```
spring.datasource.url=jdbc:mysql://{ip}:3306/contact?userSSL=false
spring.datasource.username=root
spring.datasource.password=1234
```

# jar 생성 및 실행
```
./gradlew bootjar
java -jar ./build/libs/Contact-1.0.1.jar
```

# rest api 호출
## GET
```
curl --location 'http://localhost:3306/api/all'
```
## POST
```
curl --location 'http://localhost:8086/api/create' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "will",
"phoneNumber": "010-1234-9999",
"email": "will@abc.com"
}'
```
## PUT
```
curl --location --request PUT 'http://localhost:8086/api/update/daisy2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username" : "will",
    "phoneNumber" : "010-9999-9999",
    "email" : "MrWill@abc.com"
}'
```
## DELETE
```
curl --location --request DELETE 'http://localhost:8086/api/delete/will' \
--data ''
```