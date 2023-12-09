# Imagen base. Maven 3.8.3 y JDK 17
FROM maven:3.8.3-openjdk-17 AS build

# Se copian los archivos del directorio "scr" al contenedor container
COPY src /home/app/src

# Se copia el archivo pom.xml al contenedor
COPY pom.xml /home/app

# Se ejecuta el comando "mvn", ubicando el archivo pom.xml
RUN mvn -f /home/app/pom.xml clean package

# Se expone un puerto especifico del contenedor
EXPOSE 8080

# Se configuran las variables de entorno
ENV DB_HOST=bwicorg1lcmxc4xpoojv-mysql.services.clever-cloud.com
ENV DB_PORT=3306
ENV DB_NAME=bwicorg1lcmxc4xpoojv
ENV DB_USERNAME=ujp9xci3jwf8kxl8
ENV DB_PASSWORD=pM51U0dzDQzoiBP4KnuH


# Inicio del backend, ubicando el empaquetado
ENTRYPOINT ["java","-jar","/home/app/target/tienda-0.0.1-SNAPSHOT.jar"]