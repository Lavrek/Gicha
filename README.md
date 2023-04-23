# Genshin Impact Characters Application _GIChA_
<img alt="GitHub" src="https://img.shields.io/github/license/Lavrek/Gicha?style=for-the-badge">
<img alt="GitHub top language" src="https://img.shields.io/github/languages/top/Lavrek/Gicha?style=for-the-badge">
<img alt="GitHub repo size" src="https://img.shields.io/github/repo-size/Lavrek/Gicha?style=for-the-badge">

GIChA is a Spring Boot application to manage your favorite characters.

### Genshin Impact
[Here you can download the game ](https://genshin.hoyoverse.com/en/ "GI home")

Genshin Impact is an open-world action RPG, developed and published by miHoYo. It was released for Android, iOS, PlayStation 4, and Windows in 2020.

The biggest factor in Genshin Impact's success is its massive roster of characters. Every character has their own design, story and character structure.

![GenshinImpact](https://cdn.mos.cms.futurecdn.net/kQsdYKP3jbnMAyGAwd3h6i.jpg)
### About GIChA
With this app you can create your own base with your favorite characters. You can not only add characters, but also update, delete and edit them.
There are no limits to your imagination, and who knows, maybe one day your character's concept will be the basis for Genshin Impact developers.

### Running GIChA
You can build a jar file and run it from the command line:

```
git clone https://github.com/Lavrek/Gicha.git
cd Gicha
./mvnw package
java -jar target/*.jar
```

You can then access GIChA here: http://localhost:8080/

You can also run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately
### Working with GIChA in your IDE
#### Prerequisites
* Java 11 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE
#### Steps:
-- On the command line
```
git clone https://github.com/Lavrek/Gicha.git
```
-- Inside your IDE (for example IntelliJ IDEA)

In the main menu, choose `File -> Open` and select the Gicha [pom.xml](pom.xml). Click on the `Open` button.

    ```CSS files are generated from the Maven build. You can either build them on the command line `./mvnw generate-resources` or right click on the `Gicha` project then `Maven -> Generates sources and Update Folders.

    A run configuration named `Gicha` should have been created for you if you're using a recent Ultimate version. Otherwise, run the application by right clicking on the `Gicha` main class and choosing `Run 'Gicha'```.
-- Visit [http://localhost:8080](http://localhost:8080 "GI home") in your browser


### Database
GIChA uses an in-memory database (H2) which gets populated at startup with data. The h2 console is automatically exposed at ```http://localhost:8080/h2-console```.

### Tests
GIChA also contains few unit tests for model creation and methods.


### Thanks
Special thanks to [@ondrahavelka](https://github.com/ondrahavelka "Github ondrahavelka")

developed by Lavrek