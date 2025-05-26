Mini Proyecto 3 - Pokémon MVC
Integrantes:
- Rigoberto Ospina Martinez - 2459734-3743
Descripción:
Este proyecto implementa un simulador de batalla Pokémon en Java, organizado bajo el patrón de diseño MVC (Modelo - Vista - Controlador). Es una adaptación del Mini Proyecto 2, pero con interfaz gráfica y una arquitectura más limpia y modular.
Estructura del Proyecto:
1. Modelo (modelo/)
Contiene la lógica principal del juego:
- Clases: `Pokemon`, `Ataque`, `Entrenador`, `Batalla`, etc.
- Se incluyen los atributos, ataques, lógica de turnos, cálculo de daño y condiciones para ganar.
2. Vista (vista/)
Contiene las interfaces gráficas del usuario, divididas en archivos separados para cada funcionalidad:
- **VistaInicio.java**  
  Permite al usuario ingresar los nombres de los entrenadores antes de comenzar la partida.
- **VistaEquipos.java**  
  Muestra los equipos Pokémon que fueron asignados aleatoriamente a cada entrenador.
- **VistaBatalla.java**
  Representa la batalla Pokémon, permitiendo elegir ataques, turnos y el progreso del combate.
3. Controlador (controlador/)
Actúa como puente entre el Modelo y la Vista:
- Se encarga de recibir las acciones desde la Vista y traducirlas en acciones sobre el Modelo.
- También se encarga de actualizar la interfaz según los resultados de la lógica del juego.
4. Main (main/Main.java)
Punto de entrada del programa. Inicializa el controlador y lanza la interfaz principal.
Características:
- Sistema de combate por turnos.
- Pokémon con atributos como ataque, defensa, velocidad y ataques especiales.
- Interfaz gráfica amigable con separación de responsabilidades (formulario, selección de equipos y batalla).
- Código limpio, comentado y modular siguiendo el patrón MVC.
Extras:
- Organización del proyecto en GitHub con historial de commits.
- Uso de Swing para construir la interfaz.
- Posibilidad de agregar imágenes de los Pokémon (opcional).
Disculpas:
Pido disculpas por haber realizado los commits recientemente. Estuve trabajando en el proyecto desde antes, pero por descuido olvidé registrar los avances en el repositorio en su momento. También ofrezco disculpas por la entrega tardía del proyecto; debido a compromisos laborales no me fue posible terminarlo a tiempo como hubiera querido, prometo que para el 3 proyecto no habrá este problema. Agradezco mucho su comprensión.
Instrucciones para ejecución:
- Abrir el proyecto en NetBeans o cualquier IDE Java compatible.
- Ejecutar el archivo `Main.java` desde el paquete `main`.
Autor(es):
- Rigoberto Ospina Martinez - rigoberto.ospina@correounivalle.edu.co
