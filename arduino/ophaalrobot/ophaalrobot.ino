String data = "", currentState = "idle"; //variable to store incoming data from JAVA 

void setup() {
  pinMode(12, OUTPUT);
  Serial.begin(9600);
  Serial.setTimeout(50);
}

void loop() {
  if(Serial.available()> 0){ //if data has been written to the Serial stream
    data = Serial.readString();

    if(data == "1") {
      digitalWrite(12,HIGH);
    } else if(data == "0") {
      digitalWrite(12,LOW);
    } else if(data == "start") {
      startTask();
    } else if(data == "stop") {
      stopTask();
    } else if(data == "status") {
      getState();
    }
  }
}

void startTask() {
  // start met ophalen
}

void stopTask() {
  // stop met bewegen
}

void getState() {
  Serial.println(currentState);
}



