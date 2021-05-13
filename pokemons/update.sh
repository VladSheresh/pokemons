#!/usr/bash

rm ./target/pokemons/WEB-INF/resources/css/style.css
cp ./src/main/webapp/WEB-INF/resources/css/style.css ./target/pokemons/WEB-INF/resources/css/style.css

rm ./target/pokemons/WEB-INF/resources/js/scripts.js
cp ./src/main/webapp/WEB-INF/resources/js/scripts.js ./target/pokemons/WEB-INF/resources/js/scripts.js

rm ./target/pokemons/WEB-INF/view/compare.jsp
cp ./src/main/webapp/WEB-INF/view/compare.jsp ./target/pokemons/WEB-INF/view/compare.jsp

rm ./target/pokemons/WEB-INF/view/main.jsp
cp ./src/main/webapp/WEB-INF/view/main.jsp ./target/pokemons/WEB-INF/view/main.jsp