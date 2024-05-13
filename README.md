# HiveMQ Edge Tests
Test Automation framework to test Http Adapters of HiveMQ Edge

**Tech Stack:**</br>
Java version "21.0.3"</br>
Gradle 8.7</br>
Docker HiveMQ Edge 2024.3 (Configured docker based HiveMQ setup, broker on localhost) </br>
The whole setup is containerized. The dockerfile has set up instructions for setting up both Java and HiveMQ

**Prerequisites/Setup:**
1. Docker is installed on the local system. Using docker, setup the HiveMQ Edge locally, using the command below </br>
3. Setup is successful and broker and the HIveMQ server are running on 8080

![image](https://github.com/surya818/hivemq-edge-test/assets/7116020/76044981-f450-40c2-9712-24bbe9350f40)</br>

**How to Run : (Docker way )** 
1. Clone this repo </br>
2. Navigate to root directory of the repo </br>
3. Build the docker image with command ==> **docker build -t hivemq-edge-tests:1.0 .** </br>
4. Verify docker image is built successfully
5. Run the docker image in a container, which will run the tests and save the test reports locally, in the host machine </br>
   **docker run -v <TESTOUTPUT_PATH>:/opt/hivemq/lib/build/reports hivemq-edge-tests:1.0 </br>**
   Change the TESTOUTPUT_PATH to your desired location to save test results
4. Verify Test results in your set location </br>
 

**Steps: (Alternate way/ Non Docker )** 
1. Clone this repo </br>
2. Navigate to root directory of the repo </br>
3. Run **./gradlew test -i** </br>
4. Verify Test results in lib\build\reports\tests\test\index.html </br>
 


**Libraries used:**</br>
Java HttpClient2</br>
Paho eclipse Mqtt client</br>
junit </br>

**Details:**
The tests focus on HiveMQ Edge, http adapters functionality. Http Adapters work by listening to a polling endpoint where data is coming from and convert the json input payload to mqtt format and publish the mqtt messages to a topic. </br>
In a nutshell tests deal with creation, modification of adapters and within tests we assert the correct states of adapters as well as MQTT message translation done by the adapter. We have tests covering Create Adapters, List out adapters, Modify adapters as well as Delete adapters, basically all the CRUD operations that the rest api offers. And the tests are E2E Accpetance style tests integrating flows accross different operations of HiveMQ edge.
</br> 
And also to clarify, the tests are not just REST API tests, they are functional tests that utilize the RestAPI and in many tests, we verify the messages pub-sub mechanism for some time, hence few tests run for mmore than 5 seconds, because we wait and poll for the specified wait times. </br>
For polling endpoint which generates data, I used a free (and apikey less) rest api service from the web, it's simple and lightweight. It's https://jsonplaceholder.typicode.com/

**Test Plan** </br>
A sample test plan is attached (which consistsa subset of most critical tests ). 
***The Test plan in no means a comprehensive test plan with all scenarios, its only a subset to have a fair idea on how tests would look.

**Sample Test Reports:** </br>
</br>![image](https://github.com/surya818/HiveMQEdgeTest/assets/7116020/34376cc0-6dc0-4e8c-a16a-afa8f97b65b9)

</br>![image](https://github.com/surya818/HiveMQEdgeTest/assets/7116020/c9dcd9ff-5de7-4c14-973a-570ae1a7f172)


**Profiling** </br>
I have also perfomed some profiling while the functional tests are running. Attached the jfrs in the lib/jfrs directory
Here are some screenshots of memory and CPU from JProfiles and JMC Client. On an average, while the tests are runnign the memory consumption was around 150-350 MB and CPU load was around 1-6%.

</br>

![image](https://github.com/surya818/HiveMQEdgeTest/assets/7116020/4073634a-928c-46ae-86e5-160ad57dc254) </br>
![image](https://github.com/surya818/HiveMQEdgeTest/assets/7116020/6b5de118-542e-4115-b2e5-9e04e1504086) </br>
![image](https://github.com/surya818/HiveMQEdgeTest/assets/7116020/c5ee0def-f299-4233-b4dd-114b3143bed7) </br>



