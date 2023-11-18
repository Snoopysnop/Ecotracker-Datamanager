
## Database
Using mysql
```sql
DROP DATABASE IF EXISTS ecotracker;
CREATE database ecotracker;
CREATE USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON ecotracker.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
```

## API Documentation
Documentation available [here](http://localhost:8080/swagger-ui/index.html).