#! /bin/bash
nohup /opt/hivemq/hivemq-edge-2024.3/bin/run.sh > tests.log 2>&1 </dev/null &
echo "Waiting for HiveMQ Edge server to start.."
sleep 10
curlOut=`curl http://localhost:8080 -v`
echo $curlOut
if [[ $curlOut == *"HTTP/1.1 307"* ]]; then
  echo "HiveMQ Started"
fi
/opt/hivemq/gradlew test -i

