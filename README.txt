Esta aplicación es una red social de notas.

!Importante!
Para que funcione la aplicación hay que ejecutar primero la aplicación de springboot y así inciar el servidor local.
Ya que el servidor no está alojado para que la aplicación móvil se pueda comunicar con el servidor hay que seguir los siguientes pasos.

1. En la barra de tareas de windows busca Windows Defender Firewall con seguridad avanzada.
2. Ve a reglas de entrada y a la derecha darle al botón de nueva regla->Puerto->Elegir TCP y en el campo Puertos locales especificos poner 9090->Permitir la conexión->Elegir los 3 campos->Ponerle el nombre que quieras.
Tendremos que repetir este mismo paso en las reglas de salida.
3.Abre el cmd y escribe el siguiente comando: ipconfig
4. Una vez ejecutado el comando copiar tu dirección IPv4 dependiendo del tipo de conexión que estes utilizando, en caso de que sea inalambrica: Adaptador de LAN inalámbrica Wi-Fi:
5. Abre en android studio la aplicación y busca el objeto RetrofitService (Socialpics\app\src\main\java\com\example\socialpics\modelo).
6. Busca esta linea -> private const val BASE_URL = "http://tu-direccion-ip:9090"  y sistituye tu-direccion-ip por la dirección que has copiado anteriormente.
7.Ejecuta la aplicación en el emulador de android.


