#wob.test.remake

##backend

###properties
```
application.properties ->
    
    spring.datasource.url
    spring.datasource.username
    spring.datasource.password
    
    ftp.url
    ftp.user
    ftp.password
```

###endpoints
```
 /sync
    returns boolean

 /api/report
    returns statistics

 /api/listings
    returns all listings

 /api/marketplaces
    returns all marketplaces

 /api/locations
    returns all locations

 /api/listingstats
    returns all listing stats
```