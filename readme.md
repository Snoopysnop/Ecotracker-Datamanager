
## Database
Using mysql
```sql
CREATE database ecoTracker;
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON ecoTracker.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
```

## API Documentation
Documentation available [here](http://localhost:8080/swagger-ui/index.html).