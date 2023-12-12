. Agregué al POM la dependencia de Spring Security:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

2. Creación de la entidad User en `entity/User.java`. Esta entidad es la que se va a utilizar para el login de los usuarios.
Almacena el nombre, apellido, password, email y rol (enum Role)

Cada vez que Spring Security inicia y configura la aplicación, usa un objeto UserDetails que es una interface que contiene
métodos que Spring Security usa para obtener información de los usuarios que se van a autenticar. Por lo tanto, la clase User
debe implementar la interface UserDetails.

Luego se creó el dao de User en `dao/IUserDao.java` que extiende de `JpaRepository<User, Integer>`. Este dao se va a utilizar para
obtener los datos del usuario que se va a autenticar. 

3. El primer filtro que se ejecuta es el filtro de autenticación. Este filtro se construye en `config/JwtAuthenticationFilter` y
extiende de `OncePerRequestFilter`. Este filtro se ejecuta una vez por cada request. En este filtro se obtiene el token del header
y se valida que sea correcto. Si es correcto, se obtiene el usuario del token y se lo agrega al contexto de seguridad de Spring Security.

El payload del token va a lucir de la siguiente forma:
```
{
  "sub": "admin@mail.com",
  "authorities": [
    {
      "authority": "ADMIN | USER"
    }
  ],
  "iat": 1620233281,
  "exp": 1620236881 
}
```

