# Civilizations

**Civilizations** es un simulador de gestión de civilizaciones desarrollado en **Java**. El proyecto combina la gestión estratégica de recursos, la construcción de infraestructuras y la defensa militar frente a civilizaciones enemigas mediante una **base de datos MySQL** remota y una **interfaz web** para la gestión de datos.

<br>

## 📝 Descripción del Proyecto

En **Civilizations**, te pones al mando de una pequeña civilización en crecimiento. Tu objetivo es gestionar sabiamente tus **recursos** (Comida, Madera, Hierro y Maná), construir **edificios** clave y avanzar en **tecnologías** para fortalecer tanto tu economía como tu ejército.

Sin embargo, no estás solo; civilizaciones enemigas buscarán conquistarte constantemente. Deberás equilibrar la creación de **unidades ofensivas** (Espadachines, Lanceros, Ballestas, Cañones) y **unidades defensivas** (Torre de flechas, Catapultas, Torre de lanzacohetes) para sobrevivir.

### 🏰 Elementos Clave
* **Gestión de Recursos**: Generación automática de recursos basada en el tiempo y en los edificios construidos (Granjas, Carpinterías, Herrerías, Torres Mágicas).
* **Investigación Tecnológica**: Mejora tus capacidades ofensivas y defensivas para obtener ventaja táctica sobre el enemigo.
* **Unidades Especiales**: Utiliza Magos y Sacerdotes (estos útilmos capaces de santificar unidades para potenciar sus estadísticas).
* **Sistema de Batalla**: Una mécanica compleja donde las unidades tienen estádisticas variables, probabilidades de ataque, generación de escombros (recursos recuperables tras la batalla) y acumulación de experiencia.

<br>

## 🛠️ Instalación y Configuración

Todos los archivos necesarios para la configuración se encuentran en la carpeta **`M1`** de este repositorio.

### Paso 0: Preparación deel Entorno
Antes de empezar con los puntos principales, debes preparar tu sistema:

1. **Si usas Windows**: Descarga e instala **"Ubuntu"** desde la Microsoft Store.
   * Abre la terminal de Ubuntu y asegúrate de estar en el directorio home del usuario.
2. **Si usuas Linux/macOS**: Abre tu terminal y sitúate en tu carpeta personal.
3. **Mover archivos**: Copia el contenido de la carpeta **`M1`** del repositorio en el **home (`$HOME`)** de tu usuario.

### Paso 1: Configuración de Seguridad RSA
El accesso al servidor se realiza mediante claves públicas.
1. **Generar la clave localmente**:


   ```bash
   ssh-keygen -t rsa
   ```

2. **Configurar permisos**:

   ```bash
   chmod 600 $HOME/.ssh/id_rsa
   ```

3. **Vincular con el servidor**: Entra en https://kamehouse.ieti.site con tu cuenta de **"@iesesteveterradas.cat"**  y añade la clave pública que  obtenrás con este comando:

   ```bash
   cat $HOME/.ssh/id_rsa.pub
   ```
    **Recuerda el nombre que le pongas a la clave, ya que será tu usuario de conexión.**

### Paso 2: Configuración del Archivo de Entorno
Localiza el archivo **`proxmox/config.env`** y editalo con tus credenciales:

   ```bash
   # El DEFAULT_USER es el nombre de la clave en Kamehouse
   DEFAULT_USER="civil11"
   DEFAULT_RSA_PATH="$HOME/.ssh/id_rsa"
   DEFAULT_SERVER_PORT="3000"
   ```

**Importante(solo Windows)**: Ejecuta esto en la terminal de Linux  para corregir permisos y formatos:
   ```bash
   cd ~/nodejs_server/proxmox/
   chmod +x *.sh
   dos2unix *.sh
   dos2unix *.env
   ```
<br>

## 🎮 Utilización del proyecto

### Gestión del Túnel de Datos
Para comunicar el juego con la base de datos remota, abre el túnel.
Primero accede a la carpeta y luego ejecuta los comandos seguún necesites:

   ```bash
   cd ~/nodejs_server/proxmox/

   # Iniciar el túnel
   ./proxmoxTunelStart.sh

   # Comprobar el estado del túnel
   ./proxmoxTunelStatus.sh

   # Detener el túnel
   ./proxmoxTunelStop.sh
   ```
<br>

### Ejecución del Juego
El código principal del juego se encuentra en la carpeta `M3` del repositorio. Para iniciarlo, ejecuta el archivo `app.py`:

   ```bash
   
   ```

---