@echo off
SET API_KEY=uM6rm68FB2SB_699WImn0qomCpNMxGLC
SET BASE_URL=https://localise.biz/api/export/locale

echo [LOCO] Descargando traducciones automatizadas para TypingStandup...

:: 1. Descargar Español
curl -s "%BASE_URL%/es.xml?key=%API_KEY%&format=android" > composeApp\src\androidMain\res\values\strings.xml

:: 2. Descargar Inglés
curl -s "%BASE_URL%/en.xml?key=%API_KEY%&format=android" > composeApp\src\androidMain\res\values-en\strings.xml

:: 3. Descargar Francés
curl -s "%BASE_URL%/fr.xml?key=%API_KEY%&format=android" > composeApp\src\androidMain\res\values-fr\strings.xml

echo [LOCO] ¡Traducciones sincronizadas con éxito en las carpetas de Android!
pause