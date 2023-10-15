mvn clean install
docker-compose down -v
docker-compose build --no-cache
docker-compose up -d
sleep 120
sh stress-test/run-test.sh