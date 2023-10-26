## Prerequisites
The images folders defined in [application.properties](src/main/resources/application.properties) must be exisiting paths.

## Database
Using mysql
```sql
CREATE database ecotracker;
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON ecotracker.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
```

## API Documentation
Documentation available [here](http://localhost:8080/swagger-ui/index.html).
