
#### Escuela Colombiana de Ingeniería
#### Procesos de Desarrollo de Software - PDSW
#### Autores:
#### Sergio Andres Rodriguez
#### Jonathan Prieto
---
### Badges

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d2b4f2b4a9b94af2a190e343308965e4)](https://www.codacy.com/app/SergioRt2/MyBatisGuice_Integration_VideoRental?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=LabPDSW/MyBatisGuice_Integration_VideoRental&amp;utm_campaign=Badge_Grade)

[![CircleCI](https://circleci.com/gh/LabPDSW/MyBatisGuice_Integration_VideoRental.svg?style=svg)](https://circleci.com/gh/LabPDSW/MyBatisGuice_Integration_VideoRental)
#### [Link Heroku](https://integracionheroku.herokuapp.com)
---
## Integración contínua con CircleCI y Heroku

[Documentación CircleCI para Heroku:](https://circleci.com/docs/continuous-deployment-with-heroku)


Para este ejercicio haga uso de la versión funcional de su aplicación: la rama 'master' con la aplicación basada en un 'mock' de la lógica, o la versión completa (en caso de que ya la tenga) ya mezclada en la rama 'master'.

### Parte II.

1. Cree (si no la tiene aún) una cuenta en el proveedor PAAS Heroku ([www.heroku.com](www.heroku.com)).
2. Acceda a su cuenta en Heroku y cree una nueva aplicación:

	![](img/HerokuCreateApp.png)

3. Después de crear su cuenta en Heroku y la respectiva aplicación, genere una llave de API: Opción Manage Account:

	![](img/ManageAccount.png)
	
	Opción API Key:
	![](img/GenerateKey.png)

4. Ingrese a la plataforma de integración contínua Circle.CI ([www.circleci.com](www.circleci.com)). Para ingresar, basta que se autentique con su usuario de GitHUB.
5. Seleccione la opción de agregar proyectos. En la organización o usuario de GitHub, seleccione el proyecto al que le va hacer despliegue continuo, y haga clic en "Build Project":

	![](img/AppBuild.png) 

	Una vez se inicie la primera construcción del proyecto, seleccione las opciones del proyecto (project settings)

	![](img/ProjectSettings2.png):

	Vaya a CONTINUOUS DEPLOYMENT/Heroku Deployment, y realice los dos pasos indicados: (1) registrar la llave de Heroku, y (2) asociar su usuario para el despliegue:

	![](img/SetDeployUser.png)



8. Si todo queda correctamente configurado, cada vez que hagan un PUSH al repositorio, CircleCI ejecutará la fase de construcción del proyecto. Para que cuando las pruebas pasen automáticamente se despliegue en Heroku, debe definir en el archivo circle.yml (ubicado en la raíz del proyecto):
	* La rama del repositorio de GitHUB que se desplegará en Heroku. o El nombre de la aplicación de Heroku en la que se hará el
despliegue.
	* La ejecución de la fase ‘site’ de Maven, para generar la
documentación de pruebas, cubrimiento de pruebas y análisis estático (cuando las mismas sean habilitadas).

	Ejemplo:
	[https://github.com/PDSW-ECI/base-proyectos/blob/master/circle.yml](https://github.com/PDSW-ECI/base-proyectos/blob/master/circle.yml)


9. Rectifique que en el pom.xml, en la fase de construcción, se tenga el siguiente plugin (es decir, dentro de \<build>\<plugins>):

	```xml
	<!-- Plugin configuration for Heroku compatibility. -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>2.1</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>copy</goal>
                        </goals>
                        <configuration>
                            <artifactItems>
                                <artifactItem>
                                    <groupId>com.github.jsimone</groupId>
                                    <artifactId>webapp-runner</artifactId>
                                    <version>8.0.30.2</version>
                                    <destFileName>webapp-runner.jar</destFileName>
                                </artifactItem>
                            </artifactItems>

                        </configuration>
                    </execution>

                </executions>
            </plugin>
 	```           		
	
	Nota: Si en el pom.xml ya hay otro plugin con el mismo <groupId> y <artifactId>, reemplácelo por el anteriormente mostrado.

10. Heroku requiere los siguientes archivos de configuración (con sus respectivos contenidos) en el directorio raíz del proyecto, de manera que sea qué versión de Java utilizar, y cómo iniciar la aplicación, respectivamente:

	system.properties

	```
	java.runtime.version=1.8
	```

	Procfile 

	```
	web:    java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
	```

10. El ambiente de despliegue contínuo requiere también un archivo de configuración 'circle.yml' en la raíz del proyecto, en el cual se indica (entre otras cosas) en qué aplicación de Heroku se debe desplegar la aplicación que está en GitHUB. Puede basarse en el siguiente archivo, teniendo en cuenta que se debe ajustar el parámetro 'appname': [https://github.com/PDSW-ECI/base-proyectos/blob/master/circle.yml](https://github.com/PDSW-ECI/base-proyectos/blob/master/circle.yml)

11. Haga commit y push de su repositorio local a GitHub. Abra la consola de CircleCI y verifique que el de descarga, compilación, y despliegue. Igualmente, verifique que la aplicación haya sido desplegada en Heroku.

12. Ahora, va a integrar un entorno de Análisis de Calidad de Código a su proyecto, el cual detecte contínuamente defectos asociados al mismo. Autentíquese en [CODACY](https://www.codacy.com ) con su cuenta de GitHUB, y agregue el proyecto antes creado.

13. Cree un archivo README.md para su proyecto, y asocie al mismo dos 'badges', que permitan conocer el estado del proyecto en cualquier momento: uno para [Circle.CI](https://circleci.com/docs/1.0/status-badges/), y otro para [CODACY](https://support.codacy.com/hc/en-us/articles/212799365-Badges). El proyecto usado como referencia, ya incluye dichos 'badges' en su archivo README: [https://github.com/PDSW-ECI/base-proyectos](https://github.com/PDSW-ECI/base-proyectos)

----

![](img/cmodel.png)

# Parte I. (Inicio en clase, terminado para el Jueves)

1. Actualice el presente proyecto con los Mappers realizados en el ejercicio anterior.

3. Usando como referencia la implementación de ItemDAO: MyBATISItemDao, cree el DAO MyBATIS de Cliente.

4. Declare en la implementación de la capa lógica (ServiciosAlquilerItemsImpl), que a ServiciosItemsImpl se le inyectará un ItemDAO y un ClienteDAO:

    @Inject
    private ItemDAO daoItem;

    @Inject
    private ClienteDAO daoCliente;

5. Modifique la clase ServiciosAlquilerFactory para que además de hacer 'bind' de la interfaz ServiciosAlquier con la implementación ServiciosAlquilerItemsImpl, haga 'bind' de:
	* ItemDAO con MyBATISItemDAO
	* ClienteDAO con MyBATISClienteDAO

6. Pruebe el programa 'Main' suministrado, y con este rectifique que a través de la capa lógica se pueda consultar un cliente.

7. Implemente el método load de la clase MyBATISDAOCliente, y el método 'consultarCliente' (haciendo uso del DAOCLiente inyectado). Haga un programa para comprobar que la consulta de un cliente se haga correctamente, a través de la capa lógica.


## Parte II (para el Jueves)

1. Agregue los elementos desarrollados en el punto I (dependencias, mappers, DAOs, etc) en el proyecto que sólo tiene la capa de presentación.
2. Ajuste los casos de prueba, para que ahora haga uso de la capa lógica que hace uso de la base de datos volátil:

	```java
	ServiciosAlquiler sa=ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting()
	```

3. Implemente las operaciones de la lógica que hagan falta para satisfacer los requerido por la capa de presentación, teniendo en cuenta, lo cual eventualmente requerirá agregar más operaciones a los DAOs -y por ende- más mappers de MyBATIS.
4. Tenga en cuenta, a las operaciones que impliquen registrar o actualizar registros, demarcar la transaccionalidad con la anotación @Transactional.
