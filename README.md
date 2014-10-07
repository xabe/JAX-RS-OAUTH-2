JAX-RS Oauth 2
======

El proyecto consiste un ejemplo de una Api Rest y poner seguridad.


Ejecución
--------------

Lo primero que tenemos es ejecutar nuestro proyecto que tiene un perfil para leventar un jetty embebido:


```sh 
git clone https://github.com/xabe/JAX-RS-OAUTH-2.git
cd JAX-RS-OAUTH-2
mvn clean -PJetty package 
```


Con esto ya tenemos nuestra api rest arrancada y escuchando por el puerto 9080



Ejemplo uso
--------------

Lo primero es obtener el token

```sh
curl  http://localhost:9080/JAX-RS-OAUTH-2/oauth/token -X POST -d "username=user1&password=user1&client_id=client1&client_secret=client1&grant_type=password" -v
```

Obtenemos lo siguiente

```sh 
{"access_token":"1f307c69-a2ec-4fb7-9dcb-bb76a26af6f7","token_type":"bearer","refresh_token":"2e546df0-5c1b-4a45-959f-0680f06494a0","expires_in":299999,"scope":"read write"}
```

Con esto ya tenemos el token y procedemos a acceder a los recursos

```sh 
curl http://localhost:9080/JAX-RS-OAUTH-2/resources/jersey-hello.spanish -H "Authorization:Bearer 1f307c69-a2ec-4fb7-9dcb-bb76a26af6f7"
```

Obtenemos la respuesta de la peticion

```sh 
hola mundo user1
```

Ahora vamos a renovar nuestro token

```sh
curl  http://localhost:9080/JAX-RS-OAUTH-2/oauth/token -X POST -d "client_id=client1&client_secret=client1&grant_type=refresh_token&refresh_token=2e546df0-5c1b-4a45-959f-0680f06494a0" -v
```

Obtenemos el nuevo token

```sh 
{"access_token":"690a8109-bd1e-4592-bd7d-8a20b751dda5","token_type":"bearer","refresh_token":"2e546df0-5c1b-4a45-959f-0680f06494a0","expires_in":300000,"scope":"read write"}
```

Para salir de la aplicación

```sh 
curl http://localhost:9080/JAX-RS-OAUTH-2/logout -X POST -H "Authorization:Bearer 690a8109-bd1e-4592-bd7d-8a20b751dda5"
```

Si te interesa saber m?s de java y programaci?n no dudes en seguir mi blog:

* [Mi blog]

[git-repo-url]:https://github.com/xabe/JAX-RS-OAUTH-2.git
[Mi blog]:http://tirandolineasdecodigo.blogspot.com.es/