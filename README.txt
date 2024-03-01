Esta aplicación es una red social de notas.

!Importante!
Para que funcione la aplicación hay que ejecutar primero la aplicación de springboot y así inciar el servidor local.
Ya que el servidor no está alojado para que la aplicación móvil se pueda comunicar con el servidor hay que seguir los siguientes pasos.

1. En la barra de tareas de windows busca Windows Defender Firewall con seguridad avanzada.
2. Ve a reglas de entrada y a la derecha darle al botón de nueva regla->Puerto->Elegir TCP y en el campo Puertos locales especificos poner 9090->Permitir la conexión->Elegir los 3 campos->Ponerle el nombre que quieras.
Tendremos que repetir este mismo paso en las reglas de salida.
3.Abre el cmd y escribe el siguiente comando: ipconfig
4. Una vez ejecutado el comando copiar tu dirección IPv4 dependiendo del tipo de conexión que estes utilizando, en caso de que sea inalámbrica: Adaptador de LAN inalámbrica Wi-Fi:
5. Abre en android studio la aplicación y busca el objeto RetrofitService (Socialpics\app\src\main\java\com\example\socialpics\modelo).
6. Busca esta linea -> private const val BASE_URL = "http://tu-direccion-ip:9090" y sustituye tu-direccion-ip por la dirección que has copiado anteriormente.
7.Ejecuta la aplicación en el emulador de android.

Esta red social tiene las siguientes funcionalidades:

Registrar tu cuenta.
Iniciar sesión con tu cuenta.

//Pantalla de Home
Ahí te saldrán los posts de las cuentas a la que sigues y podrás dar y quitar likes a los posts.
En el icono de + podrás agregar un nuevo post.

//Pantalla de Buscar
Ahí podrás buscar otros usuarios de la aplicación por su nombre y empezar a seguirles o dejar de seguirles.
Si le das click al nombre de usuario podrás ver su perfil. Si sigues al usuario el nombre se muestra en verde en caso contrario en rojo.

//Pantalla de Perfil
En esta pantalla podrás consultar tus post y borrarlos.

//Otras funcionalidades
La aplicación tiene validación de formularios y notificaciones para el registro.



