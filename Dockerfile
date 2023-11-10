# Usar una imagen base de Java
FROM openjdk:18

# Etiqueta del autor
LABEL authors="Aquamantop"

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/clinica_dental.jar /app/clinica_dental.jar

# Establecer el directorio de trabajo
WORKDIR /app

# Exponer el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "clinica_dental.jar"]
