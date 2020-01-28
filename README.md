# Upnid twitter  API

### Installation

- Lein: https://leiningen.org/#install
- MySQL Server

### Configure MySQL database  
 
```  
cd <path>/upnid-twitter  
mysql --user=root --password=root -e 'create database upnid'  
lein clj-sql-up migrate  
```   

For more information, see:
https://github.com/fluxlife/clj-sql-up


### Run the application locally  
```  
cd <path>/upnid-twitter  
export DATABASE_URL="//127.0.0.1:3306/upnid"  
export DATABASE_USER="root"  
export DATABASE_PASSWORD="root"
lein ring server  
```  
  
Open the URL below and test the API directly in Swagger:  
  
[http://localhost:3000/index.html#/twitter](http://localhost:3000/index.html#/twitter)  
  
### Packaging and running as standalone jar  
  
```  
cd <path>/upnid-twitter  
export DATABASE_URL="//127.0.0.1:3306/upnid"  
export DATABASE_USER="root"  
export DATABASE_PASSWORD="root" lein do clean, ring uberjar  
java -jar target/server.jar  
```  

### Packaging as war  
```  
cd <path>/upnid-twitter  
export DATABASE_URL="//127.0.0.1:3306/upnid"  
export DATABASE_USER="root"  
export DATABASE_PASSWORD="root" lein ring uberwar  
```