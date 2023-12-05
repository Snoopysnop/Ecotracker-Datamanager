## Database
Using mysql
```sql
DROP DATABASE IF EXISTS ecoTracker;
CREATE database ecoTracker;
CREATE USER IF NOT EXISTS 'ecoTrackerAdmin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON ecoTracker.* TO 'ecoTrackerAdmin'@'localhost';
FLUSH PRIVILEGES;
```

## API Documentation
Documentation available [here](http://localhost:8080/swagger-ui/index.html).
